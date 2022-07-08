package pianoCompany.dao;

import java.util.List;

import pianoCompany.entity.Duters;

public interface DutersDao {
	/**
	 * 查询出所有学生
	 */
	public  List<Duters> getAllDuters();
	
	/**
	 * 根据查询条件查询出学生
	 */
	public  List<Duters> getDuters(String sql, String[] param);

	/**
	 * 更新课程学生
	 */
	public  int updateDuters(String sql, Object[] param);

}
