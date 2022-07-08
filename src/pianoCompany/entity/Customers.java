package pianoCompany.entity;

import java.util.Scanner;

import pianoCompany.service.*;

public class Customers extends Users {
	private String tel;//�˿͵绰
	private double money;//���ѽ��
	private String address;//�˿��ͻ���ַ
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void print() {
		super.print();
		System.out.print("\t�˿͵绰: "+tel+"\t�ۼ������ܽ��: "+money+"\t�ͻ���ַ: "+address);
	}
	public void service() {
		CustomerService s = new CustomerService();
		System.out.print("1: ��������\t");
		System.out.print("2: �鿴��������\t");
		System.out.print("3: �鿴�ѹ��������\t");
		System.out.print("4: �˻�\t");
		System.out.print("5: ������Ϣ\t");
		System.out.print("6: �˳���¼\n");
		Scanner input = new Scanner(System.in);
		boolean type = true;
		int num;
		while (type) {
			num = input.nextInt();
			switch (num) {
			case 1:
				s.BuyPiano(this);
				break;
			case 2:
				s.GetExistsPiano();
				break;
			case 3:
				s.GetPiano(this);
				break;
			case 4:
				s.QuitPiano(this);
				break;
			case 5:
				Customers temp = s.getAccount(this);
				this.setMoney(temp.getMoney());
				break;
			case 6:
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
