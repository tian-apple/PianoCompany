package pianoCompany.entity;

import java.util.Scanner;

import pianoCompany.service.*;

public class Duters extends Users {
	private String tel;//ѧ���绰
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void print() {
		super.print();
		System.out.println("\tѧ���绰: "+tel);
	}
	public void service() {
		DuterService s = new DuterService();
		System.out.print("1: ��ѯ�γ�\t");
		System.out.print("2: ����γ�\t");
		System.out.print("3: �鿴��ѡ�γ�\t");
		System.out.print("4: ���ұ��γ�ѧ��\t");
		System.out.print("5: �˳��γ�\t");
		System.out.print("6: ������Ϣ\t");
		System.out.print("7: �˳���¼\n");
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
				System.out.println("��������,����������");
				break;
			}
		}
	}
	
}
