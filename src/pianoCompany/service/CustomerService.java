package pianoCompany.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import pianoCompany.entity.*;
import pianoCompany.dao.Impl.*;

public class CustomerService {
	public void BuyPiano(Customers c) {
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		CustomersDaoImpl cm=new CustomersDaoImpl();
		List<Instrument> instrumentList;
		Scanner input = new Scanner(System.in);
		String sql = "select * from instrument where id not in (select instrument_id from buying)";
		String[] param = {String.valueOf(c.getId())};
		instrumentList = im.getInstrument(sql, null);
		for(int i=0;i<instrumentList.size();i++)
			instrumentList.get(i).print();
		int inst_id;
		double deta = 0;
		Calendar calendar=Calendar.getInstance();
		Date date=calendar.getTime();
		System.out.println("请选择你想购买的乐器的编号:（输入-1返回上级菜单） ");
		do {
			inst_id = input.nextInt();
			boolean flag = false;
			for(int i=0;i<instrumentList.size();i++)
				if(inst_id == instrumentList.get(i).getId()) {
					flag = true;
				 deta=instrumentList.get(i).getPrice()+c.getMoney();
					break;
				}
				else if (inst_id == -1)
				{
					System.out.print("已返回上级菜单,请输入服务: \n1: 购买乐器\t"
							+ "2: 查看在售乐器\t"
							+ "3: 查看已购买的乐器\t"
							+ "4: 退货\t"
							+ "5: 个人信息\t"
							+ "6: 退出登录\n");
					return;
				}
			if(flag)
				break;
			else
				System.out.println("此乐器不存在或已被购买，请重新输入: ");
		}while(true);
		sql = "insert into buying values(?, ?,?)";
		param = new String[3];
		param[0] = String.valueOf(c.getId());
		param[1] = String.valueOf(inst_id);
		param[2]=String.valueOf(date);
		im.updateInstrument(sql, param);
		sql="update Customers set cust_money=? where id=?";
		param=new String[2];
		param[0]=String.valueOf(deta);
		param[1]=String.valueOf(c.getId());
		cm.updateCustomers(sql, param);
		System.out.println("已成功购买该琴!购买时间："+date);
		System.out.print("已返回上级菜单,请输入服务: \n1: 购买乐器\t"
				+ "2: 查看在售乐器\t"
				+ "3: 查看已购买的乐器\t"
				+ "4: 退货\t"
				+ "5: 个人信息\t"
				+ "6: 退出登录\n");
	}
	public void GetExistsPiano() {
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		List<Instrument> instrumentList;
		String sql = "select * from instrument where id not in (select instrument_id from buying)";
		String[] param = null;
		instrumentList = im.getInstrument(sql, param);
		System.out.println("现有在售的乐器如下: ");
		for(int i=0;i<instrumentList.size();i++)
			instrumentList.get(i).print();
		System.out.print("已返回上级菜单,请输入服务: \n1: 购买乐器\t"
				+ "2: 查看在售乐器\t"
				+ "3: 查看已购买的乐器\t"
				+ "4: 退货\t"
				+ "5: 个人信息\t"
				+ "6: 退出登录\n");
	}
	public void GetPiano(Customers c) {
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		List<Instrument> instrumentList;
		String sql = "select * from instrument where id in (select instrument_id from buying where cust_id=?)";
		String[] param = {String.valueOf(c.getId())};
		instrumentList = im.getInstrument(sql, param);
		System.out.println("已购买的琴如下: ");
		for(int i=0;i<instrumentList.size();i++)
			{
			instrumentList.get(i).print();
			
			}
		System.out.print("已返回上级菜单,请输入服务: \n1: 购买琴\t"
				+ "2: 查看现有琴\t"
				+ "3: 查看已购买的琴\t"
				+ "4: 退货\t"
				+ "5: 个人信息\t"
				+ "6: 退出登录\n");
	}
	public void QuitPiano(Customers c) {
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		CustomersDaoImpl cm=new CustomersDaoImpl();
		List<Instrument> instrumentList;
		Scanner input = new Scanner(System.in);
		String sql = "select * from instrument where id in (select instrument_id from buying where cust_id=?)";
		String[] param = {String.valueOf(c.getId())};
		instrumentList = im.getInstrument(sql, param);
		int inst_id;
		double delta=0;
		for(int i=0;i<instrumentList.size();i++)
			instrumentList.get(i).print();
		System.out.println("请选择你想退的乐器的编号: （输入-1返回上级菜单）");
		do {
			inst_id = input.nextInt();
			boolean flag = false;
			for(int i=0;i<instrumentList.size();i++)
				if(inst_id == instrumentList.get(i).getId()) {
					flag = true;
					delta=c.getMoney()-instrumentList.get(i).getPrice();
					break;
				}
				if (inst_id == -1)
				{
					System.out.print("已返回上级菜单,请输入服务: \n1: 购买乐器\t"
							+ "2: 查看在售乐器\t"
							+ "3: 查看已购买的乐器\t"
							+ "4: 退货\t"
							+ "5: 个人信息\t"
							+ "6: 退出登录\n");
					return;
				}
			if(flag)
				break;
			else
				System.out.println("此乐器不存在或已被购买，请重新输入: ");
		}while(true);
		sql = "delete from buying where cust_id=? and instrument_id=?";
		param = new String[2];
		param[0] = String.valueOf(c.getId());
		param[1] = String.valueOf(inst_id);
		im.updateInstrument(sql, param);
		sql="update Customers set cust_money=? where id=?";
		param=new String[2];
		param[0]=String.valueOf(delta);
		param[1]=String.valueOf(c.getId());
		cm.updateCustomers(sql, param);
		System.out.println("已成功退货!");
		System.out.print("已返回上级菜单,请输入服务: \n1: 购买乐器\t"
				+ "2: 查看在售乐器\t"
				+ "3: 查看已购买的乐器\t"
				+ "4: 退货\t"
				+ "5: 个人信息\t"
				+ "6: 退出登录\n");
	}
	public Customers getAccount(Customers c) {
		Customers Cust;
		CustomersDaoImpl m = new CustomersDaoImpl();
		String sql = "select * from Customers where id=?";
		String[] param = {String.valueOf(c.getId())};
		Cust = m.getCustomer(sql,param);
		Cust.print();
		System.out.println(" ");
		System.out.print("已返回上级菜单,请输入服务: \n1: 购买乐器\t"
				+ "2: 查看在售乐器\t"
				+ "3: 查看已购买的乐器\t"
				+ "4: 退货\t"
				+ "5: 个人信息\t"
				+ "6: 退出登录\n");
		return Cust;
	}
	public boolean IsLogOut() {
		System.out.println("您是否继续其它操作若是请输入y,退出请按任意键");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y")) {
			System.out.print("已返回上级菜单,请输入服务: \n1: 购买乐器\t"
					+ "2: 查看在售乐器\t"
					+ "3: 查看已购买的乐器\t"
					+ "4: 退货\t"
					+ "5: 个人信息\t"
					+ "6: 退出登录\n");
			return false;}
		else {
			System.out.println("您已成功退出登录");
			return true;
		}
	}
}

