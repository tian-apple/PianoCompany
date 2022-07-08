package pianoCompany.dao;

import java.util.List;

import pianoCompany.entity.Classrooms;
//import java.io.IOException;
public interface ClassroomsDao {
	/**
	 * 查询出所有班级
	 */
	public  List<Classrooms> getAllClassrooms();
	
	/**
	 * 根据查询条件查询出班级
	 */
	public  List<Classrooms> getClassrooms(String sql, String[] param);

	/**
	 * 更新班级信息
	 */
	public  int updateClassrooms(String sql, Object[] param);

}
