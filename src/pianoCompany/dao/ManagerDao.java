package pianoCompany.dao;

import java.util.List;

import pianoCompany.entity.Manager;

public interface ManagerDao {

	public List<Manager> getAllManager();
	
	public Manager getManager(String sql,String [] param);
	
	public int updateManager(String sql, Object[] param);
	
    public List<Manager> getManagers(String sql, String[] param);
}
