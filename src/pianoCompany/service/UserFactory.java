package pianoCompany.service;

import pianoCompany.entity.*;
import pianoCompany.dao.Impl.*;

public class UserFactory {
	public Users CreateUser(String User_identity) {
		if(User_identity.equals("老师")) {
			return new Teachers();
		}
		else if(User_identity.equals("学生")) {
			return new Duters();
		}
		else if(User_identity.equals("顾客")) {
			return new Customers();
		}
		else if(User_identity.equals("管理员")) {
			return new Manager();
		}
		else
			return null;
	}
	public Users PerfectUsers(Users u) {
		if(u.getUser_identity().equals("老师")) {
			Teachers tea;
			TeachersDaoImpl m = new TeachersDaoImpl();
			String sql = "select * from Teachers where id=?";
			String[] param = {String.valueOf(u.getId())};
			tea = m.getTeacher(sql,param);
			return tea;
		}
		else if(u.getUser_identity().equals("学生")){
			Duters Dut;
			DutersDaoImpl m = new DutersDaoImpl();
			String sql = "select * from duters where id=?";
			String[] param = {String.valueOf(u.getId())};
			Dut = m.getDuter(sql,param);
			return Dut;
		}
		else if(u.getUser_identity().equals("顾客")) {
			Customers Cust;
			CustomersDaoImpl m = new CustomersDaoImpl();
			String sql = "select * from Customers where id=?";
			String[] param = {String.valueOf(u.getId())};
			Cust = m.getCustomer(sql,param);
			return Cust;
		}
		else if(u.getUser_identity().equals("管理员")) {
			Manager Mana;
			ManagerDaoImpl m = new ManagerDaoImpl();
			String sql = "select * from Manager where id=?";
			String[] param = {String.valueOf(u.getId())};
			Mana = m.getManager(sql,param);
			return Mana;
		}
		else
			return u;
	}
}
