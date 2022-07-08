package pianoCompany.entity;

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Course {

	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	private String courseid;//�γ̺�
	private String subject;//�γ̿�Ŀ
	private String level;//�γ̵ȼ�
	private double price;//�γ̼۸�
	private Date starttime;//�γ̿�ʼʱ��
	private Date endtime;//�γ̽���ʱ��
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public void print() {
		System.out.println("�γ̺�: "+courseid+"\t�γ̿�Ŀ: "+subject+"\t�γ̵ȼ�: "+level+"\t�γ̼۸�: "+price+"\t��ʼʱ��: "+starttime+"\t����ʱ��: "+endtime);
	}
	public void check() {
		Scanner input = new Scanner(System.in);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		while(!(this.getLevel().equals("������")||this.getLevel().equals("�м���")||this.getLevel().equals("�߼���"))) {
			System.out.println("�γ̵ȼ����ִ�������������!");
			this.setLevel(input.next());
		}
		while(this.getPrice()<=0) {
			System.out.println("�γ̼۸���ִ�������������!");
			this.setPrice(input.nextDouble());
		}
		while(this.getStarttime().getTime()>=this.getEndtime().getTime()) {
			System.out.println("�γ�ʱ����ִ�������������!");
			try {
				this.setStarttime(s.parse(input.next()));
				this.setEndtime(s.parse(input.next()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
