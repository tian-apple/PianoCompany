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
		System.out.println("��������Ƹ��ʦid:(����-1�����ϼ��˵�)");
		param[0] = input.next();
		param_users[0] = param[0];
		if(param[0].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.println("��������Ƹ��ʦ����:");
		param[1] = input.next();
		param_users[1] = param[1];
		if(param[1].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.println("��������Ƹ��ʦ��ϵ��ʽ:");
		param[2] = "��ʦ";
		param_users[2] = "��ʦ";
		param[3] = input.next();
		if(param[3].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.println("��������Ƹ��ʦнˮ:");
		param[4] = input.next();
		if(param[4].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.println("��������Ƹ��ʦ����:");
		param_users[3] = input.next();
		tm.updateTeachers(sql_users,param_users);
		tm.updateTeachers(sql, param);
		System.out.println("��Ƹ��ʦ: "+param[1]+"��Ϣ��¼��");
		System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
				+ "2: ���˽�ʦ\t"
				+ "3: ������Ʒ\t"
				+ "4: �¼���Ʒ\t"
				+ "5: �޸Ĺ���\t"
				+ "6: ��ѯ��Ŀ\t"
				+ "7: �˳���¼");
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
			System.out.println("����ʱ�䣺 "+buydate);
			System.out.println("***************************************************************************************");
		}
		System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
				+ "2: ���˽�ʦ\t"
				+ "3: ������Ʒ\t"
				+ "4: �¼���Ʒ\t"
				+ "5: �޸Ĺ���\t"
				+ "6: ��ѯ��Ŀ\t"
				+ "7: �˳���¼");
	}
	public void updateSalary()
	{
		TeachersDaoImpl tm = new TeachersDaoImpl();
		Scanner input = new Scanner(System.in);
		List<Teachers> teachersList;
		teachersList = tm.getAllTeachers();
		System.out.println("��ְ��ʦ����: ");
		for(int i = 0;i<teachersList.size();i++)
			teachersList.get(i).print();
		System.out.println("��ѡ������Ҫ�޸Ĺ��ʵĽ�ʦid: ������-1�����ϼ��˵���"); 
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
					System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
							+ "2: ���˽�ʦ\t"
							+ "3: ������Ʒ\t"
							+ "4: �¼���Ʒ\t"
							+ "5: �޸Ĺ���\t"
							+ "6: ��ѯ��Ŀ\t"
							+ "7: �˳���¼");
					return;
				}
			}
			if(flag)
				break;
			else 
				System.out.println("δ�ҵ����ҽ�ʦid,����������: ");
		}while(true);
		String sql = "update teachers set salary = ? where id = ?";
		System.out.println("�������¹���: ");
		double newsalary = input.nextDouble();
		String[] param = {String.valueOf(newsalary),String.valueOf(tea_id)};
		tm.updateTeachers(sql, param);
		System.out.println("�ѳɹ��޸Ĺ���\n");
		System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
				+ "2: ���˽�ʦ\t"
				+ "3: ������Ʒ\t"
				+ "4: �¼���Ʒ\t"
				+ "5: �޸Ĺ���\t"
				+ "6: ��ѯ��Ŀ\t"
				+ "7: �˳���¼");
	}
	public void quitTeacher()
	{
		TeachersDaoImpl tm = new TeachersDaoImpl();
		Scanner input = new Scanner(System.in);
		List<Teachers> teachersList;
		teachersList = tm.getAllTeachers();
		System.out.println("��ְ��ʦ����: ");
		for(int i = 0;i<teachersList.size();i++)
			teachersList.get(i).print();
		System.out.println("��ѡ������Ҫ���˵Ľ�ʦid: ������-1�����ϼ��˵���"); 
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
					System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
							+ "2: ���˽�ʦ\t"
							+ "3: ������Ʒ\t"
							+ "4: �¼���Ʒ\t"
							+ "5: �޸Ĺ���\t"
							+ "6: ��ѯ��Ŀ\t"
							+ "7: �˳���¼");
					return;
				}
			}
			if(flag)
				break;
			else 
				System.out.println("δ�ҵ����ҽ�ʦid,����������: ");
		}while(true);
		String sql = "delete from teachers where id = ?";
		String sql_users = "delete from users where id = ?";
		String sql_teaches = "delete from teaches where tea_id = ?";
		String [] param = {String.valueOf(tea_id)};
		tm.updateTeachers(sql_teaches, param);
		tm.updateTeachers(sql, param);
		tm.updateTeachers(sql_users, param);
		System.out.println("�ѳɹ����˽�ʦ\n");
		System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
				+ "2: ���˽�ʦ\t"
				+ "3: ������Ʒ\t"
				+ "4: �¼���Ʒ\t"
				+ "5: �޸Ĺ���\t"
				+ "6: ��ѯ��Ŀ\t"
				+ "7: �˳���¼");
	}
	public void importPiano()
	{
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		Scanner input = new Scanner(System.in);
		String sql = "insert into instrument values(?,?,?,?,?)";
		String []param = new String[5];
		System.out.print("����������������ʶ��:(����\"-1\"�����ϼ��˵�)");
		param[0] = input.next();
		if(param[0].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.print("����������Ʒ��:");
		param[1] = input.next();
		if(param[1].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.print("��������������:");
		param[2] = input.next();
		if(param[2].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.print("������˫�������������۸�:");
		param[3] = input.next();
		if(param[3].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		System.out.print("�����������ͺ�:");
		param[4] = input.next();
		if(param[4].equals("-1"))
		{
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return;
		}	
		im.updateInstrument(sql, param);
		System.out.println("�ѳɹ�����!\n");
		System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
				+ "2: ���˽�ʦ\t"
				+ "3: ������Ʒ\t"
				+ "4: �¼���Ʒ\t"
				+ "5: �޸Ĺ���\t"
				+ "6: ��ѯ��Ŀ\t"
				+ "7: �˳���¼");
	}
	public void deletePiano()
	{
		InstrumentDaoImpl im = new InstrumentDaoImpl();
		Scanner input = new Scanner(System.in);
		List<Instrument> instrumentList;
		String sql_show = "select * from instrument where id not in (select instrument_id from buying)";
		String[] param_show = null;
		instrumentList = im.getInstrument(sql_show, param_show);
		System.out.println("�������۵���������: ");
		for(int i=0;i<instrumentList.size();i++)
			instrumentList.get(i).print();
		
		System.out.println("�������¼���Ʒ����ƷID:������-1�����ϼ��˵��� ");
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
					System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
							+ "2: ���˽�ʦ\t"
							+ "3: ������Ʒ\t"
							+ "4: �¼���Ʒ\t"
							+ "5: �޸Ĺ���\t"
							+ "6: ��ѯ��Ŀ\t"
							+ "7: �˳���¼");
					return;
				}
			}
			if(flag)
				break;
			else
				System.out.println("����Ʒ�����ۣ��޷��¼�,����������:");
		}while(true);
		String sql = "delete from instrument where instrument.id = ? ";
		String param[] = {String.valueOf(inst_id)};
		im.updateInstrument(sql, param);
		System.out.println("�ѳɹ��¼���Ʒ\n");
		System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
				+ "2: ���˽�ʦ\t"
				+ "3: ������Ʒ\t"
				+ "4: �¼���Ʒ\t"
				+ "5: �޸Ĺ���\t"
				+ "6: ��ѯ��Ŀ\t"
				+ "7: �˳���¼");
	}
	public boolean IsLogOut() {
		System.out.println("���Ƿ����������������������y,�˳��밴�����");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y")) {
			System.out.println("�ѷ����ϼ��˵�,���������1: Ƹ�ý�ʦ\t"
					+ "2: ���˽�ʦ\t"
					+ "3: ������Ʒ\t"
					+ "4: �¼���Ʒ\t"
					+ "5: �޸Ĺ���\t"
					+ "6: ��ѯ��Ŀ\t"
					+ "7: �˳���¼");
			return false;}
		else {
			System.out.println("���ѳɹ��˳���¼");
			return true;
		}
	}
}