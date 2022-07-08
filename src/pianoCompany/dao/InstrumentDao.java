package pianoCompany.dao;
import java.util.List;

import pianoCompany.entity.Instrument;



public interface InstrumentDao {
	/**
	 * 查询所有的在库乐器信息
	 */
	public  List<Instrument> getAllInstrument();

	/**
	 * 根据已知乐器的信息查询乐器信息
	 */
	public  List<Instrument> getInstrument(String sql, Object[] param);
	
	/**
	 * 更新乐器信息
	 */
	public  int updateInstrument(String sql, Object[] param);

}
