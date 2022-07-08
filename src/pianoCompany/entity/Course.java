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
	private String courseid;//课程号
	private String subject;//课程科目
	private String level;//课程等级
	private double price;//课程价格
	private Date starttime;//课程开始时间
	private Date endtime;//课程结束时间
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
		System.out.println("课程号: "+courseid+"\t课程科目: "+subject+"\t课程等级: "+level+"\t课程价格: "+price+"\t开始时间: "+starttime+"\t结束时间: "+endtime);
	}
	public void check() {
		Scanner input = new Scanner(System.in);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		while(!(this.getLevel().equals("初级班")||this.getLevel().equals("中级班")||this.getLevel().equals("高级班"))) {
			System.out.println("课程等级出现错误，请重新输入!");
			this.setLevel(input.next());
		}
		while(this.getPrice()<=0) {
			System.out.println("课程价格出现错误，请重新输入!");
			this.setPrice(input.nextDouble());
		}
		while(this.getStarttime().getTime()>=this.getEndtime().getTime()) {
			System.out.println("课程时间出现错误，请重新输入!");
			try {
				this.setStarttime(s.parse(input.next()));
				this.setEndtime(s.parse(input.next()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
