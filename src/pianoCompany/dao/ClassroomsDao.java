package pianoCompany.dao;

import java.util.List;

import pianoCompany.entity.Classrooms;
//import java.io.IOException;
public interface ClassroomsDao {
	/**
	 * ��ѯ�����а༶
	 */
	public  List<Classrooms> getAllClassrooms();
	
	/**
	 * ���ݲ�ѯ������ѯ���༶
	 */
	public  List<Classrooms> getClassrooms(String sql, String[] param);

	/**
	 * ���°༶��Ϣ
	 */
	public  int updateClassrooms(String sql, Object[] param);

}
