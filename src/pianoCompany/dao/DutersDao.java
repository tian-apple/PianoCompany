package pianoCompany.dao;

import java.util.List;

import pianoCompany.entity.Duters;

public interface DutersDao {
	/**
	 * ��ѯ������ѧ��
	 */
	public  List<Duters> getAllDuters();
	
	/**
	 * ���ݲ�ѯ������ѯ��ѧ��
	 */
	public  List<Duters> getDuters(String sql, String[] param);

	/**
	 * ���¿γ�ѧ��
	 */
	public  int updateDuters(String sql, Object[] param);

}
