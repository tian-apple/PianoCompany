package pianoCompany.entity;
import java.util.Scanner;

import pianoCompany.service.ManagerService;

public class Manager extends Users {

	public void service() {
		ManagerService s = new ManagerService();
		System.out.print("1: Ƹ�ý�ʦ\t");
		System.out.print("2: ���˽�ʦ\t");
		System.out.print("3: ������Ʒ\t");
		System.out.print("4: �¼���Ʒ\t");
		System.out.print("5: �޸Ĺ���\t");
		System.out.print("6: ��ѯ��Ŀ\t");
		System.out.print("7: �˳���¼\n");
		Scanner input = new Scanner(System.in);
		boolean type = true;
		int num;
		while (type)
		{
			num = input.nextInt();
			switch(num)
			{
			case 1:
				s.enrollteacher();
				break;
			case 2:
				s.quitTeacher();
				break;
			case 3:
				s.importPiano();
				break;
			case 4:
				s.deletePiano();
				break;
			case 5:
				s.updateSalary();
				break;
			case 6:
				s.showAccount();
				break;
			case 7:
				if(s.IsLogOut())
					type = false;
				break;
			default:
				System.out.println("������������������:");
				break;
			}
		}
	}
}
