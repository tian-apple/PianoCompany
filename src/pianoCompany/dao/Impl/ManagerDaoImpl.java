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

	private PreparedStatement pstmt = null;//����ִ��sql���
    private ResultSet rs = null;//���ڱ���sql��ѯ�����

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
                managers.setId(rs.getInt(1));//�û���ʶ��
                managers.setUser_name(rs.getString(2));//�û�����
                managers.setUser_identity(rs.getString(3));//�û����(ѧ��)
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
            	managers.setId(rs.getInt(1));//�û���ʶ��
            	managers.setUser_name(rs.getString(2));//�û�����
            	managers.setUser_identity(rs.getString(3));//�û����(ѧ��)
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
            	managers.setId(rs.getInt(1));//�û���ʶ��
            	managers.setUser_name(rs.getString(2));//�û�����
            	managers.setUser_identity(rs.getString(3));//�û����(ѧ��)
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
