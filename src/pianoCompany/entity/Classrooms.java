package pianoCompany.entity;

public class Classrooms {
	private String dut_id;//学生
	private String tea_id;//老师 
	private String course_id;//课程 
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}
	public String getDut_id() {
		return dut_id;
	}
	public void setDut_id(String dut_id) {
		this.dut_id = dut_id;
	}

}