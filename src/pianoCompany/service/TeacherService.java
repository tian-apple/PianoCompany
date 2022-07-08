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
		System.out.println("���ڿγ����£�");
		for(int i=0;i<courseList.size();i++)
			courseList.get(i).print();
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ���ڿγ�\t"
				+ "2: �½��γ�\t"
				+ "3: ������Ϣ\t"
				+ "4: �˳���¼\n");
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
		System.out.println("��������Ҫ�½��Ŀγ̺š��γ����͡��γ̼��𡢿γ̼۸񡢿γ̿�ʼʱ�������ʱ��:������-1�����ϼ��˵��� ");
		do {
			c.setCourseid(input.next());
			boolean flag = false;
			for(int i=0;i<courseList.size();i++)
				if(c.getCourseid().equals(courseList.get(i).getCourseid())) {
					flag = true;
					break;
				}
			if(c.getCourseid().equals("-1")){
				System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ���ڿγ�\t"
						+ "2: �½��γ�\t"
						+ "3: ������Ϣ\t"
						+ "4: �˳���¼\n");
				return;
			}
			if(!flag)
				break;
			else
				System.out.println("�˿γ��Ѵ��ڣ�����������: ");
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
		System.out.println("�ѳɹ���ӿγ�!");
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ���ڿγ�\t"
				+ "2: �½��γ�\t"
				+ "3: ������Ϣ\t"
				+ "4: �˳���¼\n");
	}
	public void getAccount(Teachers t) {
		t.print();
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ���ڿγ�\t"
				+ "2: �½��γ�\t"
				+ "3: ������Ϣ\t"
				+ "4: �˳���¼\n");
	}
	public boolean IsLogOut() {
		System.out.println("���Ƿ����������������������y,�˳��밴�����");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y"))
			{
			System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ���ڿγ�\t"
					+ "2: �½��γ�\t"
					+ "3: ������Ϣ\t"
					+ "4: �˳���¼\n");
			return false;
			}
		else {
			System.out.println("���ѳɹ��˳�ϵͳ");
			return true;
		}
	}
}