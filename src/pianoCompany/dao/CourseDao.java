package pianoCompany.dao;
import java.util.List;


import pianoCompany.entity.Course;
public interface CourseDao {
	/**
	 * ��ѯ�����пγ�
	 */
	public  List<Course> getAllCourse();
	
	/**
	 * ���ݲ�ѯ������ѯ���γ�
	 */
	public  List<Course> getCourse(String sql, String[] param);

	/**
	 * ���¿γ���Ϣ
	 */
	public  int updateCourse(String sql, Object[] param);

}
