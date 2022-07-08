package pianoCompany.dao;

import java.util.List;

import pianoCompany.entity.Teachers;

public interface TeachersDao {
	/**
	 * ��ѯ���е���ְ��ʦ��Ϣ
	 */
	public  List<Teachers> getAllTeachers();

	/**
	 * ������֪��������Ϣ��ѯ������Ϣ
	 */
	public  List<Teachers> getTeachers(String sql, String[] param);
	
	/**
	 * ����������Ϣ
	 */
	public  int updateTeachers(String sql, Object[] param);

}
