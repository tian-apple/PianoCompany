package pianoCompany.service;

import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import pianoCompany.dao.Impl.*;
import pianoCompany.entity.*;

public class TeacherService {
	public void GetCourse(Teachers t) {
		CourseDaoImpl m =new CourseDaoImpl();
		String sql = "select * from course where course_id in (select course_id from teaches where tea_id=?)";
		String[] param = {String.valueOf(t.getId())};
		List<Course> courseList = m.getCourse(sql, param);
		System.out.println("教授课程如下：");
		for(int i=0;i<courseList.size();i++)
			courseList.get(i).print();
		System.out.print("已返回上级菜单,请输入服务: \n1: 查询教授课程\t"
				+ "2: 新建课程\t"
				+ "3: 个人信息\t"
				+ "4: 退出登录\n");
	}
	public void CreateCourse(Teachers t) {
		CourseDaoImpl m =new CourseDaoImpl();
		ClassroomsDaoImpl cl = new ClassroomsDaoImpl();
		Scanner input = new Scanner(System.in);
		String sql = "select * from course";
		String[] param = null;
		List<Course> courseList = m.getCourse(sql, param);
		Course c = new Course();
		SimpleDateFormat s =new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("请输入所要新建的课程号、课程类型、课程级别、课程价格、课程开始时间与结束时间:（输入-1返回上级菜单） ");
		do {
			c.setCourseid(input.next());
			boolean flag = false;
			for(int i=0;i<courseList.size();i++)
				if(c.getCourseid().equals(courseList.get(i).getCourseid())) {
					flag = true;
					break;
				}
			if(c.getCourseid().equals("-1")){
				System.out.print("已返回上级菜单,请输入服务: \n1: 查询教授课程\t"
						+ "2: 新建课程\t"
						+ "3: 个人信息\t"
						+ "4: 退出登录\n");
				return;
			}
			if(!flag)
				break;
			else
				System.out.println("此课程已存在，请重新输入: ");
		}while(true);
		c.setSubject(input.next());
		c.setLevel(input.next());
		c.setPrice(input.nextDouble());
		try {
			c.setStarttime(s.parse(input.next()));
			c.setEndtime(s.parse(input.next()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.check();
		sql = "insert into Course values(?, ?, ?, ?, ?, ?)";
		param = new String[6];
		param[0] = c.getCourseid();
		param[1] = c.getSubject();
		param[2] = c.getLevel();
		param[3] = String.valueOf(c.getPrice());
		param[4] = String.valueOf(c.getStarttime());
		param[5] =  String.valueOf(c.getEndtime());
		m.updateCourse(sql, param);
		sql = "insert into Teaches values(?, ?)";
		param = new String[2];
		param[0] = c.getCourseid();
		param[1] = String.valueOf(t.getId());
		cl.updateClassrooms(sql, param);
		System.out.println("已成功添加课程!");
		System.out.print("已返回上级菜单,请输入服务: \n1: 查询教授课程\t"
				+ "2: 新建课程\t"
				+ "3: 个人信息\t"
				+ "4: 退出登录\n");
	}
	public void getAccount(Teachers t) {
		t.print();
		System.out.print("已返回上级菜单,请输入服务: \n1: 查询教授课程\t"
				+ "2: 新建课程\t"
				+ "3: 个人信息\t"
				+ "4: 退出登录\n");
	}
	public boolean IsLogOut() {
		System.out.println("您是否继续其它操作若是请输入y,退出请按任意键");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y"))
			{
			System.out.print("已返回上级菜单,请输入服务: \n1: 查询教授课程\t"
					+ "2: 新建课程\t"
					+ "3: 个人信息\t"
					+ "4: 退出登录\n");
			return false;
			}
		else {
			System.out.println("您已成功退出系统");
			return true;
		}
	}
}