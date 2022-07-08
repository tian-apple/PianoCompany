package pianoCompany.dao;

import java.util.Date;
import java.util.List;

import pianoCompany.entity.Customers;

public interface CustomersDao {
	/**
	 * 查询出所有顾客
	 */
	public  List<Customers> getAllCustomers();
	
	/**
	 * 根据查询条件查询出顾客
	 */
	public  List<Customers> getCustomers(String sql, String[] param);

	/**
	 * 更新顾客
	 */
	public  int updateCustomers(String sql, Object[] param);
	
	public List<Date> getBuyingDates();

}
