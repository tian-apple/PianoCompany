package pianoCompany.dao;

import java.util.List;

import pianoCompany.entity.Teachers;

public interface TeachersDao {
	/**
	 * 查询所有的在职教师信息
	 */
	public  List<Teachers> getAllTeachers();

	/**
	 * 根据已知乐器的信息查询乐器信息
	 */
	public  List<Teachers> getTeachers(String sql, String[] param);
	
	/**
	 * 更新乐器信息
	 */
	public  int updateTeachers(String sql, Object[] param);

}
