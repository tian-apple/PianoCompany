package pianoCompany.entity;

import java.util.Scanner;

import pianoCompany.service.*;

public class Teachers extends Users {
	private String tel;//��ʦ�绰
	private double salary;//��ʦ����
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
		System.out.println("\t��ʦ�绰: "+tel+"\t��ʦ����: "+salary);
	}
	public void service() {
		TeacherService s = new TeacherService();
		System.out.print("1: �鿴���ڿγ�\t");
		System.out.print("2: �½��γ�\t");
		System.out.print("3: ������Ϣ\t");
		System.out.print("4: �˳���¼\n");
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
				System.out.println("��������,����������");
				break;
			}
		}
		//input.close();
	};
}
