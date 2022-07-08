package pianoCompany.service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import pianoCompany.dao.Impl.CustomersDaoImpl;
import pianoCompany.dao.Impl.InstrumentDaoImpl;
import pianoCompany.dao.Impl.TeachersDaoImpl;
import pianoCompany.entity.Customers;
import pianoCompany.entity.Instrument;
import pianoCompany.entity.Teachers;

public class ManagerService{

	public void enrollteacher()
	{
		TeachersDaoImpl tm = new TeachersDaoImpl();
		Scanner input = new Scanner(System.in);
		String sql = "insert into teachers values(?,?,?,?,?)";
		String sql_users = "insert into users values(?,?,?,?)";
		String[] param_users = new String [4];
		String []param = new String[5];
		System.out.println("请输入新聘教师id:(输入-1返回上级菜单)");
		param[0] = input.next();
		param_users[0] = param[0];
		if(param[0].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.println("请输入新聘教师姓名:");
		param[1] = input.next();
		param_users[1] = param[1];
		if(param[1].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.println("请输入新聘教师联系方式:");
		param[2] = "老师";
		param_users[2] = "老师";
		param[3] = input.next();
		if(param[3].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.println("请输入新聘教师薪水:");
		param[4] = input.next();
		if(param[4].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.println("请设置新聘教师密码:");
		param_users[3] = input.next();
		tm.updateTeachers(sql_users,param_users);
		tm.updateTeachers(sql, param);
		System.out.println("新聘教师: "+param[1]+"信息已录入");
		System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
				+ "2: 辞退教师\t"
				+ "3: 引进商品\t"
				+ "4: 下架商品\t"
				+ "5: 修改工资\t"
				+ "6: 查询账目\t"
				+ "7: 退出登录");
	}
	public void showAccount()
	{
		CustomersDaoImpl cm = new CustomersDaoImpl();
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		Scanner input = new Scanner(System.in);
		String sql = "select * from customers join buying on customers.id = buying.cust_id order by cust_id ";
		List<Customers> customersList;
		List<Date> dateList;
		List<Instrument> instrumentList;
		customersList = cm.getCustomers(sql, null);
		dateList = cm.getBuyingDates();
		String sql_inst = "select * from instrument join buying on instrument.id = buying.instrument_id order by cust_id,instrument_id";
		instrumentList = im.getInstrument(sql_inst, null);
		for(int i = 0;i< dateList.size();i++)
		{
			Date buydate = dateList.get(i);
			System.out.println("***************************************************************************************");
			customersList.get(i).print();
			System.out.println(" ");
			instrumentList.get(i).print();
			System.out.println("购买时间： "+buydate);
			System.out.println("***************************************************************************************");
		}
		System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
				+ "2: 辞退教师\t"
				+ "3: 引进商品\t"
				+ "4: 下架商品\t"
				+ "5: 修改工资\t"
				+ "6: 查询账目\t"
				+ "7: 退出登录");
	}
	public void updateSalary()
	{
		TeachersDaoImpl tm = new TeachersDaoImpl();
		Scanner input = new Scanner(System.in);
		List<Teachers> teachersList;
		teachersList = tm.getAllTeachers();
		System.out.println("在职教师如下: ");
		for(int i = 0;i<teachersList.size();i++)
			teachersList.get(i).print();
		System.out.println("请选择你想要修改工资的教师id: （输入-1返回上级菜单）"); 
		int tea_id;
		do {
			tea_id = input.nextInt();
			boolean flag = false;
			for(int i=0;i<teachersList.size();i++)
			{
				if(tea_id == teachersList.get(i).getId())
				{
					flag = true;
					break;
				}
				else if(tea_id == -1)
				{
					System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
							+ "2: 辞退教师\t"
							+ "3: 引进商品\t"
							+ "4: 下架商品\t"
							+ "5: 修改工资\t"
							+ "6: 查询账目\t"
							+ "7: 退出登录");
					return;
				}
			}
			if(flag)
				break;
			else 
				System.out.println("未找到所找教师id,请重新输入: ");
		}while(true);
		String sql = "update teachers set salary = ? where id = ?";
		System.out.println("请输入新工资: ");
		double newsalary = input.nextDouble();
		String[] param = {String.valueOf(newsalary),String.valueOf(tea_id)};
		tm.updateTeachers(sql, param);
		System.out.println("已成功修改工资\n");
		System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
				+ "2: 辞退教师\t"
				+ "3: 引进商品\t"
				+ "4: 下架商品\t"
				+ "5: 修改工资\t"
				+ "6: 查询账目\t"
				+ "7: 退出登录");
	}
	public void quitTeacher()
	{
		TeachersDaoImpl tm = new TeachersDaoImpl();
		Scanner input = new Scanner(System.in);
		List<Teachers> teachersList;
		teachersList = tm.getAllTeachers();
		System.out.println("在职教师如下: ");
		for(int i = 0;i<teachersList.size();i++)
			teachersList.get(i).print();
		System.out.println("请选择你想要辞退的教师id: （输入-1返回上级菜单）"); 
		int tea_id;
		do {
			tea_id = input.nextInt();
			boolean flag = false;
			for(int i=0;i<teachersList.size();i++)
			{
				if(tea_id == teachersList.get(i).getId())
				{
					flag = true;
					break;
				}
				if(tea_id == -1)
				{
					System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
							+ "2: 辞退教师\t"
							+ "3: 引进商品\t"
							+ "4: 下架商品\t"
							+ "5: 修改工资\t"
							+ "6: 查询账目\t"
							+ "7: 退出登录");
					return;
				}
			}
			if(flag)
				break;
			else 
				System.out.println("未找到所找教师id,请重新输入: ");
		}while(true);
		String sql = "delete from teachers where id = ?";
		String sql_users = "delete from users where id = ?";
		String sql_teaches = "delete from teaches where tea_id = ?";
		String [] param = {String.valueOf(tea_id)};
		tm.updateTeachers(sql_teaches, param);
		tm.updateTeachers(sql, param);
		tm.updateTeachers(sql_users, param);
		System.out.println("已成功辞退教师\n");
		System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
				+ "2: 辞退教师\t"
				+ "3: 引进商品\t"
				+ "4: 下架商品\t"
				+ "5: 修改工资\t"
				+ "6: 查询账目\t"
				+ "7: 退出登录");
	}
	public void importPiano()
	{
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		Scanner input = new Scanner(System.in);
		String sql = "insert into instrument values(?,?,?,?,?)";
		String []param = new String[5];
		System.out.print("请输入整数乐器标识符:(输入\"-1\"返回上级菜单)");
		param[0] = input.next();
		if(param[0].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.print("请输入乐器品牌:");
		param[1] = input.next();
		if(param[1].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.print("请输入乐器种类:");
		param[2] = input.next();
		if(param[2].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.print("请输入双精度类型乐器价格:");
		param[3] = input.next();
		if(param[3].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		System.out.print("请输入乐器型号:");
		param[4] = input.next();
		if(param[4].equals("-1"))
		{
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return;
		}	
		im.updateInstrument(sql, param);
		System.out.println("已成功进货!\n");
		System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
				+ "2: 辞退教师\t"
				+ "3: 引进商品\t"
				+ "4: 下架商品\t"
				+ "5: 修改工资\t"
				+ "6: 查询账目\t"
				+ "7: 退出登录");
	}
	public void deletePiano()
	{
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		Scanner input = new Scanner(System.in);
		List<Instrument> instrumentList;
		String sql_show = "select * from instrument where id not in (select instrument_id from buying)";
		String[] param_show = null;
		instrumentList = im.getInstrument(sql_show, param_show);
		System.out.println("现有在售的乐器如下: ");
		for(int i=0;i<instrumentList.size();i++)
			instrumentList.get(i).print();
		
		System.out.println("请输入下架商品的商品ID:（输入-1返回上级菜单） ");
		int inst_id;
		
		do {
			inst_id = input.nextInt();
			boolean flag = false;
			for(int i = 0;i<instrumentList.size();i++)
			{
				if(inst_id == instrumentList.get(i).getId())
				{
					flag = true;
					break;
				}
				else if (inst_id==-1)
				{
					System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
							+ "2: 辞退教师\t"
							+ "3: 引进商品\t"
							+ "4: 下架商品\t"
							+ "5: 修改工资\t"
							+ "6: 查询账目\t"
							+ "7: 退出登录");
					return;
				}
			}
			if(flag)
				break;
			else
				System.out.println("此商品不在售，无法下架,请重新输入:");
		}while(true);
		String sql = "delete from instrument where instrument.id = ? ";
		String param[] = {String.valueOf(inst_id)};
		im.updateInstrument(sql, param);
		System.out.println("已成功下架商品\n");
		System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
				+ "2: 辞退教师\t"
				+ "3: 引进商品\t"
				+ "4: 下架商品\t"
				+ "5: 修改工资\t"
				+ "6: 查询账目\t"
				+ "7: 退出登录");
	}
	public boolean IsLogOut() {
		System.out.println("您是否继续其它操作若是请输入y,退出请按任意键");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y")) {
			System.out.println("已返回上级菜单,请输入服务：1: 聘用教师\t"
					+ "2: 辞退教师\t"
					+ "3: 引进商品\t"
					+ "4: 下架商品\t"
					+ "5: 修改工资\t"
					+ "6: 查询账目\t"
					+ "7: 退出登录");
			return false;}
		else {
			System.out.println("您已成功退出登录");
			return true;
		}
	}
}