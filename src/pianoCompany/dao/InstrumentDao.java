package pianoCompany.dao;
import java.util.List;

import pianoCompany.entity.Instrument;



public interface InstrumentDao {
	/**
	 * ��ѯ���е��ڿ�������Ϣ
	 */
	public  List<Instrument> getAllInstrument();

	/**
	 * ������֪��������Ϣ��ѯ������Ϣ
	 */
	public  List<Instrument> getInstrument(String sql, Object[] param);
	
	/**
	 * ����������Ϣ
	 */
	public  int updateInstrument(String sql, Object[] param);

}
