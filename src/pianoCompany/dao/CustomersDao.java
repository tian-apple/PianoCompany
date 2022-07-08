package pianoCompany.dao;

import java.util.Date;
import java.util.List;

import pianoCompany.entity.Customers;

public interface CustomersDao {
	/**
	 * ��ѯ�����й˿�
	 */
	public  List<Customers> getAllCustomers();
	
	/**
	 * ���ݲ�ѯ������ѯ���˿�
	 */
	public  List<Customers> getCustomers(String sql, String[] param);

	/**
	 * ���¹˿�
	 */
	public  int updateCustomers(String sql, Object[] param);
	
	public List<Date> getBuyingDates();

}
