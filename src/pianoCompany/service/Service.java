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
		userkinds[0]="顾客";
		userkinds[1]="学生";
		System.out.println("请输入id: ");
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
        		System.out.println("此用户id已存在，请重新输入!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}while(true);
		System.out.println("请输入密码: ");
		do{
			passwd = input.next();
			if(passwd.length()>20)
				System.out.println("用户密码过长，请重新输入!");
			else
				break;
		}while(true);
		System.out.println("请输入你的名字: ");
		do {
			name = input.next();
			if(name.length()>20)
				System.out.println("名字过长，请重新输入(名字需要小于20个字)!");
			else
				break;
    	}while(true);
		System.out.println("请确定用户类型: 1:顾客\t2:学生\t");
		kinds:
		do{
			kind = input.nextInt();
			switch(kind)
			{
			case 1://当注册身份为顾客时
				System.out.println("请输入电话: ");
				do {
					tel = input.next();
					if(tel.length()>20)
						System.out.println("电话过长，请重新输入(电话需要小于20个数字)!");
					else
						break;
				}while(true);
				System.out.println("请输入送货地址: ");
				do {
					address = input.next();
					if(address.length()>20)
						System.out.println("送货地址过长，请重新输入(地址需要小于20个字)!");
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
				param[2]="顾客";
				param[3]=tel;
				param[4]=String.valueOf(cust_money);
				param[5]=address;
				executeSQL(sql,param);
				System.out.println("顾客注册成功");
				param=null;
				break kinds;
			case 2://当注册身份为学生时
				System.out.println("请输入电话: ");
				do {
					tel = input.next();
					if(tel.length()>20)
						System.out.println("电话过长，请重新输入(电话需要小于20个数字)!");
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
				param[2]="学生";
				param[3]=tel;
				executeSQL(sql,param);
				System.out.println("学生注册成功");
				break kinds;
				default:
					System.out.println("请输入合法字符");
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
			System.out.println("请输入id: ");
			id=input.nextInt();
			System.out.println("请输入密码: ");
			passwd=input.next();
			u = findUsers(id,passwd);
			if(u!=null) {
				System.out.println("尊贵的用户 "+u.getUser_name()+" 登录成功!");
				this.u = u;
				flag=true;
			}
			else
				System.out.println("用户名不存在或密码错误，请重新登录!");
		}
	}
    
    public Users findUsers(int id, String password){
        try{
        	Users u = null;
        	UserFactory p = new UserFactory();
            //conn  = getConn();

            String Sql = "select * from Users where id=? and password=?"; // 预编译
            stmt = conn.prepareStatement(Sql);
            // 给问号赋值
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

