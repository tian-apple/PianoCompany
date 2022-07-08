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
			System.out.println("�������û�id: ");
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

