package pianoCompany.entity;

import java.text.ParseException;

/**
 * 
 * �û�ʵ����
 *
 */
public class Users {
	private int id;//�û���ʶ��
	private String User_name;//�û�����
	private String User_identity;//�û����
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
		System.out.print("�˺�: "+id+"\t����: "+User_name+"\t���: "+User_identity);
	}
	public void service() throws ParseException {
		return;
	};
}
