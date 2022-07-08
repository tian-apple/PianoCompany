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
		System.out.println("��ѡ�����빺��������ı��:������-1�����ϼ��˵��� ");
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
					System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��������\t"
							+ "2: �鿴��������\t"
							+ "3: �鿴�ѹ��������\t"
							+ "4: �˻�\t"
							+ "5: ������Ϣ\t"
							+ "6: �˳���¼\n");
					return;
				}
			if(flag)
				break;
			else
				System.out.println("�����������ڻ��ѱ���������������: ");
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
		System.out.println("�ѳɹ��������!����ʱ�䣺"+date);
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��������\t"
				+ "2: �鿴��������\t"
				+ "3: �鿴�ѹ��������\t"
				+ "4: �˻�\t"
				+ "5: ������Ϣ\t"
				+ "6: �˳���¼\n");
	}
	public void GetExistsPiano() {
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		List<Instrument> instrumentList;
		String sql = "select * from instrument where id not in (select instrument_id from buying)";
		String[] param = null;
		instrumentList = im.getInstrument(sql, param);
		System.out.println("�������۵���������: ");
		for(int i=0;i<instrumentList.size();i++)
			instrumentList.get(i).print();
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��������\t"
				+ "2: �鿴��������\t"
				+ "3: �鿴�ѹ��������\t"
				+ "4: �˻�\t"
				+ "5: ������Ϣ\t"
				+ "6: �˳���¼\n");
	}
	public void GetPiano(Customers c) {
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		List<Instrument> instrumentList;
		String sql = "select * from instrument where id in (select instrument_id from buying where cust_id=?)";
		String[] param = {String.valueOf(c.getId())};
		instrumentList = im.getInstrument(sql, param);
		System.out.println("�ѹ����������: ");
		for(int i=0;i<instrumentList.size();i++)
			{
			instrumentList.get(i).print();
			
			}
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ������\t"
				+ "2: �鿴������\t"
				+ "3: �鿴�ѹ������\t"
				+ "4: �˻�\t"
				+ "5: ������Ϣ\t"
				+ "6: �˳���¼\n");
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
		System.out.println("��ѡ�������˵������ı��: ������-1�����ϼ��˵���");
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
					System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��������\t"
							+ "2: �鿴��������\t"
							+ "3: �鿴�ѹ��������\t"
							+ "4: �˻�\t"
							+ "5: ������Ϣ\t"
							+ "6: �˳���¼\n");
					return;
				}
			if(flag)
				break;
			else
				System.out.println("�����������ڻ��ѱ���������������: ");
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
		System.out.println("�ѳɹ��˻�!");
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��������\t"
				+ "2: �鿴��������\t"
				+ "3: �鿴�ѹ��������\t"
				+ "4: �˻�\t"
				+ "5: ������Ϣ\t"
				+ "6: �˳���¼\n");
	}
	public Customers getAccount(Customers c) {
		Customers Cust;
		CustomersDaoImpl m = new CustomersDaoImpl();
		String sql = "select * from Customers where id=?";
		String[] param = {String.valueOf(c.getId())};
		Cust = m.getCustomer(sql,param);
		Cust.print();
		System.out.println(" ");
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��������\t"
				+ "2: �鿴��������\t"
				+ "3: �鿴�ѹ��������\t"
				+ "4: �˻�\t"
				+ "5: ������Ϣ\t"
				+ "6: �˳���¼\n");
		return Cust;
	}
	public boolean IsLogOut() {
		System.out.println("���Ƿ����������������������y,�˳��밴�����");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y")) {
			System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��������\t"
					+ "2: �鿴��������\t"
					+ "3: �鿴�ѹ��������\t"
					+ "4: �˻�\t"
					+ "5: ������Ϣ\t"
					+ "6: �˳���¼\n");
			return false;}
		else {
			System.out.println("���ѳɹ��˳���¼");
			return true;
		}
	}
}

