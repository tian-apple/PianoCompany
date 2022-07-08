package pianoCompany.entity;

import java.text.ParseException;

/**
 * 
 * 用户实体类
 *
 */
public class Users {
	private int id;//用户标识符
	private String User_name;//用户姓名
	private String User_identity;//用户身份
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public String getUser_identity() {
		return User_identity;
	}
	public void setUser_identity(String user_identity) {
		User_identity = user_identity;
	}
	public void print() {
		System.out.print("账号: "+id+"\t姓名: "+User_name+"\t身份: "+User_identity);
	}
	public void service() throws ParseException {
		return;
	};
}
