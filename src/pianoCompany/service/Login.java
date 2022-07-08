package pianoCompany.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import pianoCompany.entity.Users;
import pianoCompany.dao.BaseDao;

public class Login extends BaseDao{
	Scanner input = new Scanner(System.in);
    //Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Users u = null;
    public Login() {
		Users u;
		int id;
		String passwd;
		boolean flag =false;
		while(!flag)
		{
			System.out.println("请输入用户id: ");
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

