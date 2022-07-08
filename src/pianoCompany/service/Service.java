package pianoCompany.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.text.ParseException;
import pianoCompany.entity.Users;
import pianoCompany.dao.BaseDao;

public class Service extends BaseDao{
	Scanner input = new Scanner(System.in);
    //Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Users u = null;
    public Login l =null;
	public void giveService()
	{
		this.l = new Login();
		try {
			l.u.service();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
    public void RegisterService() {
    	String[] param;
    	int id;
		String passwd;
		String name;
		String sql;
		String tel;
		String address;
		double cust_money=0.00;
		int kind;
		String[] userkinds=new String[2];
		userkinds[0]="�˿�";
		userkinds[1]="ѧ��";
		System.out.println("������id: ");
		do{
			id = input.nextInt();
			sql = "select id from Users where id=?";
	        try {
				stmt = conn.prepareStatement(sql);
	        stmt.setString(1,String.valueOf(id));
	        rs  = stmt.executeQuery();
        	if(!rs.next())
        		break;
        	else
        		System.out.println("���û�id�Ѵ��ڣ�����������!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}while(true);
		System.out.println("����������: ");
		do{
			passwd = input.next();
			if(passwd.length()>20)
				System.out.println("�û��������������������!");
			else
				break;
		}while(true);
		System.out.println("�������������: ");
		do {
			name = input.next();
			if(name.length()>20)
				System.out.println("���ֹ���������������(������ҪС��20����)!");
			else
				break;
    	}while(true);
		System.out.println("��ȷ���û�����: 1:�˿�\t2:ѧ��\t");
		kinds:
		do{
			kind = input.nextInt();
			switch(kind)
			{
			case 1://��ע�����Ϊ�˿�ʱ
				System.out.println("������绰: ");
				do {
					tel = input.next();
					if(tel.length()>20)
						System.out.println("�绰����������������(�绰��ҪС��20������)!");
					else
						break;
				}while(true);
				System.out.println("�������ͻ���ַ: ");
				do {
					address = input.next();
					if(address.length()>20)
						System.out.println("�ͻ���ַ����������������(��ַ��ҪС��20����)!");
					else
						break;
				}while(true);
				sql = "insert into users values(?, ?, ?, ?)";
				param=new String[4];
				param[0] = String.valueOf(id);
				param[1] = name;
				param[2] = userkinds[kind-1];
				param[3] = passwd;
				executeSQL(sql, param);
				param=null;
				sql="insert into customers values(?,?,?,?,?,?)";
				param=new String[6];
				param[0]=String.valueOf(id);
				param[1]=name;
				param[2]="�˿�";
				param[3]=tel;
				param[4]=String.valueOf(cust_money);
				param[5]=address;
				executeSQL(sql,param);
				System.out.println("�˿�ע��ɹ�");
				param=null;
				break kinds;
			case 2://��ע�����Ϊѧ��ʱ
				System.out.println("������绰: ");
				do {
					tel = input.next();
					if(tel.length()>20)
						System.out.println("�绰����������������(�绰��ҪС��20������)!");
					else
						break;
				}while(true);
				sql = "insert into users values(?, ?, ?, ?)";
				param=new String[4];
				param[0] = String.valueOf(id);
				param[1] = name;
				param[2] = userkinds[kind-1];
				param[3] = passwd;
				executeSQL(sql, param);
				param=null;
				sql="insert into duters values(?,?,?,?)";
				param=new String[4];
				param[0]=String.valueOf(id);
				param[1]=name;
				param[2]="ѧ��";
				param[3]=tel;
				executeSQL(sql,param);
				System.out.println("ѧ��ע��ɹ�");
				break kinds;
				default:
					System.out.println("������Ϸ��ַ�");
					break;
			}
		}while(true);
    }
    public void LoginService() {
		Users u;
		int id;
		String passwd;
		boolean flag =false;
		while(!flag)
		{
			System.out.println("������id: ");
			id=input.nextInt();
			System.out.println("����������: ");
			passwd=input.next();
			u = findUsers(id,passwd);
			if(u!=null) {
				System.out.println("�����û� "+u.getUser_name()+" ��¼�ɹ�!");
				this.u = u;
				flag=true;
			}
			else
				System.out.println("�û��������ڻ�������������µ�¼!");
		}
	}
    
    public Users findUsers(int id, String password){
        try{
        	Users u = null;
        	UserFactory p = new UserFactory();
            //conn  = getConn();

            String Sql = "select * from Users where id=? and password=?"; // Ԥ����
            stmt = conn.prepareStatement(Sql);
            // ���ʺŸ�ֵ
            stmt.setString(1,String.valueOf(id));
            stmt.setString(2,password);
            rs  = stmt.executeQuery();
            while(rs.next()) {
            	String User_identity = rs.getString("User_identity");
	            u = p.CreateUser(User_identity);
	            u.setId(rs.getInt("id"));
	            u.setUser_name(rs.getString("User_name"));
	            u.setUser_identity(rs.getString("User_identity"));
	            u = p.PerfectUsers(u);
	            return u;
            	}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            close(stmt, rs);
        }
        return u;
    }
}

