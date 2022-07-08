package pianoCompany.service;

import java.util.List;
import java.util.Scanner;

import pianoCompany.dao.Impl.*;
import pianoCompany.entity.*;

public class DuterService {
	public void GetAllCourse() {
		CourseDaoImpl m =new CourseDaoImpl();
		List<Course> courseList = m.getAllCourse();
		System.out.println("ȫ���γ�����: ");
		for(int i=0;i<courseList.size();i++)
			courseList.get(i).print();
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
				+ "2: ����γ�\t"
				+ "3: �鿴��ѡ�γ�\t"
				+ "4: ���ұ��γ�ѧ��\t"
				+ "5: �˳��γ�\t"
				+ "6: ������Ϣ\t"
				+"7:�˳���¼\n");
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
		System.out.println("��ѡ���������Ŀγ̺�:������-1�����ϼ��˵��� ");
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
					System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
							+ "2: ����γ�\t"
							+ "3: �鿴��ѡ�γ�\t"
							+ "4: ���ұ��γ�ѧ��\t"
							+ "5: �˳��γ�\t"
							+ "6: ������Ϣ\t"
							+"7:�˳���¼\n");
					return;
				}
			if(flag)
				break;
			else
				System.out.println("�˿γ̲����ڻ��Ѿ����룬����������: ");
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
			System.out.println("�ѳɹ�����γ�!");
			System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
					+ "2: ����γ�\t"
					+ "3: �鿴��ѡ�γ�\t"
					+ "4: ���ұ��γ�ѧ��\t"
					+ "5: �˳��γ�\t"
					+ "6: ������Ϣ\t"
					+"7:�˳���¼\n");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("�˿γ��޽�ʦ����!");
		}
	}
	public void GetCourse(Duters u) {
		CourseDaoImpl m =new CourseDaoImpl();
		String sql = "select * from course where course_id in (select course_id from takes where dut_id=?)";
		String[] param = new String[1];
		param[0] = Integer.toString(u.getId());
		List<Course> courseList = m.getCourse(sql, param);
		System.out.println("�Ѽ���Ŀγ�����: ");
		for(int i=0;i<courseList.size();i++)
			courseList.get(i).print();
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
				+ "2: ����γ�\t"
				+ "3: �鿴��ѡ�γ�\t"
				+ "4: ���ұ��γ�ѧ��\t"
				+ "5: �˳��γ�\t"
				+ "6: ������Ϣ\t"
				+"7:�˳���¼\n");
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
		System.out.println("��ѡ�������ѯ�Ŀγ̺�:������-1�����ϼ��˵��� ");
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
				System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
						+ "2: ����γ�\t"
						+ "3: �鿴��ѡ�γ�\t"
						+ "4: ���ұ��γ�ѧ��\t"
						+ "5: �˳��γ�\t"
						+ "6: ������Ϣ\t"
						+"7:�˳���¼\n");
				return;
			}
			if(flag)
				break;
			else
				System.out.println("�˿γ̲����ڻ�δ���룬����������: ");
		}while(true);
		sql = "select id, dut_name, dut_identity, dut_tel from takes join duters on takes.dut_id=duters.id where course_id =?";
		param[0] = course_id;
		List<Duters> DutersList = d.getDuters(sql, param);
		for(int i=0;i<DutersList.size();i++)
			DutersList.get(i).print();
		System.out.print("��ѯ�������,�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
				+ "2: ����γ�\t"
				+ "3: �鿴��ѡ�γ�\t"
				+ "4: ���ұ��γ�ѧ��\t"
				+ "5: �˳��γ�\t"
				+ "6: ������Ϣ\t"
				+"7:�˳���¼\n");
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
		System.out.println("��ѡ�������˳��Ŀγ̺�:������-1�����ϼ��˵��� ");
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
				System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
						+ "2: ����γ�\t"
						+ "3: �鿴��ѡ�γ�\t"
						+ "4: ���ұ��γ�ѧ��\t"
						+ "5: �˳��γ�\t"
						+ "6: ������Ϣ\t"
						+"7:�˳���¼\n");
				return;
			}
			if(flag)
				break;
			else
				System.out.println("�˿γ̲����ڻ��Ѿ��˳�������������: ");
		}while(true);
		sql = "delete from takes where course_id=? and dut_id=?";
		param = new String[2];
		param[0] = course_id;
		param[1] = String.valueOf(u.getId());
		c.updateClassrooms(sql,param);
		System.out.println("�ѳɹ�ɾ���γ�!");
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
				+ "2: ����γ�\t"
				+ "3: �鿴��ѡ�γ�\t"
				+ "4: ���ұ��γ�ѧ��\t"
				+ "5: �˳��γ�\t"
				+ "6: ������Ϣ\t"
				+"7:�˳���¼\n");
	}
	public void getAccount(Duters u) {
		u.print();
		System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
				+ "2: ����γ�\t"
				+ "3: �鿴��ѡ�γ�\t"
				+ "4: ���ұ��γ�ѧ��\t"
				+ "5: �˳��γ�\t"
				+ "6: ������Ϣ\t"
				+"7:�˳���¼\n");
	}
	public boolean IsLogOut() {
		System.out.println("���Ƿ����������������������y,�˳��밴�����");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y"))
			{
			System.out.print("�ѷ����ϼ��˵�,���������: \n1: ��ѯ�γ�\t"
					+ "2: ����γ�\t"
					+ "3: �鿴��ѡ�γ�\t"
					+ "4: ���ұ��γ�ѧ��\t"
					+ "5: �˳��γ�\t"
					+ "6: ������Ϣ\t"
					+"7:�˳���¼\n");
			return false;
			}
		else {
			System.out.println("���ѳɹ��˳�ϵͳ");
			return true;
		}
	}
}
