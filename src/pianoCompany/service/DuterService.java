package pianoCompany.service;

import java.util.List;
import java.util.Scanner;

import pianoCompany.dao.Impl.*;
import pianoCompany.entity.*;

public class DuterService {
	public void GetAllCourse() {
		CourseDaoImpl m =new CourseDaoImpl();
		List<Course> courseList = m.getAllCourse();
		System.out.println("全部课程如下: ");
		for(int i=0;i<courseList.size();i++)
			courseList.get(i).print();
		System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
				+ "2: 加入课程\t"
				+ "3: 查看已选课程\t"
				+ "4: 查找本课程学生\t"
				+ "5: 退出课程\t"
				+ "6: 个人信息\t"
				+"7:退出登录\n");
	}
	public void JoinCourse(Duters u) {
		ClassroomsDaoImpl c = new ClassroomsDaoImpl();
		Scanner input = new Scanner(System.in);
		String course_id;
		String sql = "select course_id from course where course_id not in (select course_id from takes where dut_id=?)";
		String[] param = new String[1];
		param[0] = Integer.toString(u.getId());
		List<String> courseidList = c.getCourseid(sql, param);
		for(int i=0;i<courseidList.size();i++)
			System.out.println(courseidList.get(i));
		System.out.println("请选择你想加入的课程号:（输入-1返回上级菜单） ");
		do {
			course_id = input.next();
			boolean flag = false;
			for(int i=0;i<courseidList.size();i++)
				if(course_id.equals(courseidList.get(i))) {
					flag = true;
					break;
				}
			  if (course_id.equals("-1"))
				{
					System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
							+ "2: 加入课程\t"
							+ "3: 查看已选课程\t"
							+ "4: 查找本课程学生\t"
							+ "5: 退出课程\t"
							+ "6: 个人信息\t"
							+"7:退出登录\n");
					return;
				}
			if(flag)
				break;
			else
				System.out.println("此课程不存在或已经加入，请重新输入: ");
		}while(true);
		try{
			sql = "select tea_id from teaches where course_id=?";
			param[0] = course_id;
			int teaid = c.getTeaid(sql, param).get(0);
			sql = "insert into takes values(?, ?)";
			param = new String[2];
			param[0] = course_id;
			param[1] = Integer.toString(u.getId());
			c.updateClassrooms(sql,param);
			System.out.println("已成功加入课程!");
			System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
					+ "2: 加入课程\t"
					+ "3: 查看已选课程\t"
					+ "4: 查找本课程学生\t"
					+ "5: 退出课程\t"
					+ "6: 个人信息\t"
					+"7:退出登录\n");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("此课程无教师教授!");
		}
	}
	public void GetCourse(Duters u) {
		CourseDaoImpl m =new CourseDaoImpl();
		String sql = "select * from course where course_id in (select course_id from takes where dut_id=?)";
		String[] param = new String[1];
		param[0] = Integer.toString(u.getId());
		List<Course> courseList = m.getCourse(sql, param);
		System.out.println("已加入的课程如下: ");
		for(int i=0;i<courseList.size();i++)
			courseList.get(i).print();
		System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
				+ "2: 加入课程\t"
				+ "3: 查看已选课程\t"
				+ "4: 查找本课程学生\t"
				+ "5: 退出课程\t"
				+ "6: 个人信息\t"
				+"7:退出登录\n");
	}
	public void GetDuter(Duters u) {
		ClassroomsDaoImpl c = new ClassroomsDaoImpl();
		DutersDaoImpl d = new DutersDaoImpl();
		Scanner input = new Scanner(System.in);
		String course_id;
		String sql = "select course_id from course where course_id in (select course_id from takes where dut_id=?)";
		String[] param = new String[1];
		param[0] = Integer.toString(u.getId());
		List<String> courseidList = c.getCourseid(sql, param);
		for(int i=0;i<courseidList.size();i++)
			System.out.println(courseidList.get(i));
		System.out.println("请选择你想查询的课程号:（输入-1返回上级菜单） ");
		do {
			course_id = input.next();
			boolean flag = false;
			for(int i=0;i<courseidList.size();i++)
				if(course_id.equals(courseidList.get(i))) {
					flag = true;
					break;
				}
				else if (course_id.equals("-1"))
			{
				System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
						+ "2: 加入课程\t"
						+ "3: 查看已选课程\t"
						+ "4: 查找本课程学生\t"
						+ "5: 退出课程\t"
						+ "6: 个人信息\t"
						+"7:退出登录\n");
				return;
			}
			if(flag)
				break;
			else
				System.out.println("此课程不存在或未加入，请重新输入: ");
		}while(true);
		sql = "select id, dut_name, dut_identity, dut_tel from takes join duters on takes.dut_id=duters.id where course_id =?";
		param[0] = course_id;
		List<Duters> DutersList = d.getDuters(sql, param);
		for(int i=0;i<DutersList.size();i++)
			DutersList.get(i).print();
		System.out.print("查询结果如上,已返回上级菜单,请输入服务: \n1: 查询课程\t"
				+ "2: 加入课程\t"
				+ "3: 查看已选课程\t"
				+ "4: 查找本课程学生\t"
				+ "5: 退出课程\t"
				+ "6: 个人信息\t"
				+"7:退出登录\n");
	}
	public void QuitCourse(Duters u) {
		ClassroomsDaoImpl c = new ClassroomsDaoImpl();
		Scanner input = new Scanner(System.in);
		String course_id;
		String sql = "select course_id from course where course_id in (select course_id from takes where dut_id=?)";
		String[] param = new String[1];
		param[0] = Integer.toString(u.getId());
		List<String> courseidList = c.getCourseid(sql, param);
		for(int i=0;i<courseidList.size();i++)
			System.out.println(courseidList.get(i));
		System.out.println("请选择你想退出的课程号:（输入-1返回上级菜单） ");
		do {
			course_id = input.next();
			boolean flag = false;
			for(int i=0;i<courseidList.size();i++)
				if(course_id.equals(courseidList.get(i))) {
					flag = true;
					break;
				}
			if (course_id.equals("-1"))
			{
				System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
						+ "2: 加入课程\t"
						+ "3: 查看已选课程\t"
						+ "4: 查找本课程学生\t"
						+ "5: 退出课程\t"
						+ "6: 个人信息\t"
						+"7:退出登录\n");
				return;
			}
			if(flag)
				break;
			else
				System.out.println("此课程不存在或已经退出，请重新输入: ");
		}while(true);
		sql = "delete from takes where course_id=? and dut_id=?";
		param = new String[2];
		param[0] = course_id;
		param[1] = String.valueOf(u.getId());
		c.updateClassrooms(sql,param);
		System.out.println("已成功删除课程!");
		System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
				+ "2: 加入课程\t"
				+ "3: 查看已选课程\t"
				+ "4: 查找本课程学生\t"
				+ "5: 退出课程\t"
				+ "6: 个人信息\t"
				+"7:退出登录\n");
	}
	public void getAccount(Duters u) {
		u.print();
		System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
				+ "2: 加入课程\t"
				+ "3: 查看已选课程\t"
				+ "4: 查找本课程学生\t"
				+ "5: 退出课程\t"
				+ "6: 个人信息\t"
				+"7:退出登录\n");
	}
	public boolean IsLogOut() {
		System.out.println("您是否继续其它操作若是请输入y,退出请按任意键");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y"))
			{
			System.out.print("已返回上级菜单,请输入服务: \n1: 查询课程\t"
					+ "2: 加入课程\t"
					+ "3: 查看已选课程\t"
					+ "4: 查找本课程学生\t"
					+ "5: 退出课程\t"
					+ "6: 个人信息\t"
					+"7:退出登录\n");
			return false;
			}
		else {
			System.out.println("您已成功退出系统");
			return true;
		}
	}
}
