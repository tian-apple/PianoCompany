package pianoCompany.entity;

import java.util.Scanner;

import pianoCompany.service.*;

public class Duters extends Users {
	private String tel;//学生电话
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void print() {
		super.print();
		System.out.println("\t学生电话: "+tel);
	}
	public void service() {
		DuterService s = new DuterService();
		System.out.print("1: 查询课程\t");
		System.out.print("2: 加入课程\t");
		System.out.print("3: 查看已选课程\t");
		System.out.print("4: 查找本课程学生\t");
		System.out.print("5: 退出课程\t");
		System.out.print("6: 个人信息\t");
		System.out.print("7: 退出登录\n");
		Scanner input = new Scanner(System.in);
		boolean type = true;
		int num;
		while (type) {
			num = input.nextInt();
			switch (num) {
			case 1:
				s.GetAllCourse();
				break;
			case 2:
				s.JoinCourse(this);
				break;
			case 3:
				s.GetCourse(this);
				break;
			case 4:
				s.GetDuter(this);
				break;
			case 5:
				s.QuitCourse(this);
				break;
			case 6:
				s.getAccount(this);
				break;
			case 7:
				if(s.IsLogOut()) 
					type = false;
				break;
			default:
				System.out.println("输入有误,请重新输入");
				break;
			}
		}
	}
	
}
