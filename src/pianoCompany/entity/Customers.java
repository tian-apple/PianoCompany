package pianoCompany.entity;

import java.util.Scanner;

import pianoCompany.service.*;

public class Customers extends Users {
	private String tel;//顾客电话
	private double money;//消费金额
	private String address;//顾客送货地址
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
		System.out.print("\t顾客电话: "+tel+"\t累计消费总金额: "+money+"\t送货地址: "+address);
	}
	public void service() {
		CustomerService s = new CustomerService();
		System.out.print("1: 购买乐器\t");
		System.out.print("2: 查看在售乐器\t");
		System.out.print("3: 查看已购买的乐器\t");
		System.out.print("4: 退货\t");
		System.out.print("5: 个人信息\t");
		System.out.print("6: 退出登录\n");
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
				System.out.println("输入有误,请重新输入");
				break;
			}
		}
		//input.close();
	};
}
