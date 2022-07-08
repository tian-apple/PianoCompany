package pianoCompany.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pianoCompany.dao.CustomersDao;
import pianoCompany.entity.Customers;
import pianoCompany.dao.BaseDao;
public class CustomersDaoImpl extends BaseDao implements CustomersDao{
    private PreparedStatement pstmt = null;//用于执行sql语句
    private ResultSet rs = null;//用于保存sql查询结果集

    public List<Date> getBuyingDates()
    {
    	List<Date> dateList = new ArrayList<Date>();
    	try {
    		String prepareSql = "select buy_time from buying order by cust_id,instrument_id";
    		pstmt = conn.prepareStatement(prepareSql);
    		rs = pstmt.executeQuery();
    		while(rs.next())
    		{
    			dateList.add(rs.getDate(1));
    		}
    	}
    	catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pstmt,rs);
        }
    	return dateList;
    }
    @Override
    public List<Customers> getAllCustomers() {
        List<Customers> customersList = new ArrayList<Customers>();
        try {
            String prepareSql = "select * from customers";
            //conn = getConn();
            pstmt = conn.prepareStatement(prepareSql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Customers customers = new Customers();
                customers.setId(rs.getInt(1));//用户标识符
                customers.setUser_name(rs.getString(2));//用户姓名
                customers.setUser_identity(rs.getString(3));//用户身份(顾客)
                customers.setTel(rs.getString(4));//顾客电话
                customers.setMoney(rs.getInt(5));//消费金额
                customers.setAddress(rs.getString(6));//顾客送货地址
                customersList.add(customers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pstmt,rs);
        }
        return customersList;
    }

    @Override
    public List<Customers> getCustomers(String sql, String[] param) {
        List<Customers> Customerslist = new ArrayList<Customers>();
        try{
            //conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if(param != null){
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Customers customers=null;
            while (rs.next()){
                customers = new Customers();
                customers.setId(rs.getInt(1));//用户标识符
                customers.setUser_name(rs.getString(2));//用户姓名
                customers.setUser_identity(rs.getString(3));//用户身份(顾客)
                customers.setTel(rs.getString(4));//顾客电话
                customers.setMoney(rs.getDouble(5));//消费金额
                customers.setAddress(rs.getString(6));//顾客送货地址
                Customerslist.add(customers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return Customerslist;
    }
    
    public Customers getCustomer(String sql, String[] param) {  
        Customers customer=null;
        try{
            //conn = getConn();
    		pstmt = conn.prepareStatement(sql);
            if(param != null){
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()){
            	customer = new Customers();
                customer.setId(rs.getInt(1));//用户标识符
                customer.setUser_name(rs.getString(2));//用户姓名
                customer.setUser_identity(rs.getString(3));//用户身份(顾客)
                customer.setTel(rs.getString(4));//顾客电话
                customer.setMoney(rs.getDouble(5));//消费金额
                customer.setAddress(rs.getString(6));//顾客送货地址
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return customer;
    }

    @Override
    public int updateCustomers(String sql, Object[] param) {
        int count = executeSQL(sql,param);
        return count;
    }
}
