package pianoCompany.entity;

import java.util.Scanner;

import pianoCompany.service.*;

public class Teachers extends Users {
	private String tel;//教师电话
	private double salary;//教师工资
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void print() {
		super.print();
		System.out.println("\t教师电话: "+tel+"\t教师工资: "+salary);
	}
	public void service() {
		TeacherService s = new TeacherService();
		System.out.print("1: 查看教授课程\t");
		System.out.print("2: 新建课程\t");
		System.out.print("3: 个人信息\t");
		System.out.print("4: 退出登录\n");
		Scanner input = new Scanner(System.in);
		boolean type = true;
		int num;
		while (type) {
			num = input.nextInt();
			switch (num) {
			case 1:
				s.GetCourse(this);
				break;
			case 2:
				s.CreateCourse(this);
				break;
			case 3:
				s.getAccount(this);
				break;
			case 4:
				if(s.IsLogOut()) 
					type = false;
				break;
			default:
				System.out.println("输入有误,请重新输入");
				break;
			}
		}
		//input.close();
	};
}
