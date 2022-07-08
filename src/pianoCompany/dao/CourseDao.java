package pianoCompany.dao;
import java.util.List;


import pianoCompany.entity.Course;
public interface CourseDao {
	/**
	 * 查询出所有课程
	 */
	public  List<Course> getAllCourse();
	
	/**
	 * 根据查询条件查询出课程
	 */
	public  List<Course> getCourse(String sql, String[] param);

	/**
	 * 更新课程信息
	 */
	public  int updateCourse(String sql, Object[] param);

}
