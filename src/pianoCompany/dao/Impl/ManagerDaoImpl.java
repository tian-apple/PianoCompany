package pianoCompany.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pianoCompany.dao.BaseDao;
import pianoCompany.dao.ManagerDao;
import pianoCompany.entity.Manager;

public class ManagerDaoImpl extends BaseDao implements ManagerDao{

	private PreparedStatement pstmt = null;//用于执行sql语句
    private ResultSet rs = null;//用于保存sql查询结果集

    @Override
    public List<Manager> getAllManager() {
        List<Manager> ManagerList = new ArrayList<Manager>();
        try {
            String prepareSql = "select * from Manager";
            //conn = getConn();
            pstmt = conn.prepareStatement(prepareSql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Manager managers = new Manager();
                managers.setId(rs.getInt(1));//用户标识符
                managers.setUser_name(rs.getString(2));//用户姓名
                managers.setUser_identity(rs.getString(3));//用户身份(学生)
                ManagerList.add(managers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return ManagerList;
    }

    @Override
    public List<Manager> getManagers(String sql, String[] param){
    	List<Manager> Managerlist = new ArrayList<Manager>();

        try{
            //conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if(param != null){
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Manager managers=null;
            while (rs.next()){
            	managers = new Manager();
            	managers.setId(rs.getInt(1));//用户标识符
            	managers.setUser_name(rs.getString(2));//用户姓名
            	managers.setUser_identity(rs.getString(3));//用户身份(学生)
                Managerlist.add(managers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return Managerlist;
    }
    
    public Manager getManager(String sql,String [] param){
        Manager managers=null;
        try{
            //conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if(param != null){
                for(int i=0;i<param.length;i++){
                    pstmt.setString(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()){
            	managers = new Manager();
            	managers.setId(rs.getInt(1));//用户标识符
            	managers.setUser_name(rs.getString(2));//用户姓名
            	managers.setUser_identity(rs.getString(3));//用户身份(学生)
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pstmt,rs);
        }
        return managers;
    }

    @Override
    public int updateManager(String sql, Object[] param) {
        int count = executeSQL(sql, param);
        return count;
    }
	
}
