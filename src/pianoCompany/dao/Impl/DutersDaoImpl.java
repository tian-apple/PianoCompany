package pianoCompany.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pianoCompany.dao.DutersDao;
//import pianoCompany.entity.Customers;
import pianoCompany.entity.Duters;
import pianoCompany.dao.BaseDao;

public class DutersDaoImpl extends BaseDao implements DutersDao{
    private PreparedStatement pstmt = null;//����ִ��sql���
    private ResultSet rs = null;//���ڱ���sql��ѯ�����

    @Override
    public List<Duters> getAllDuters() {
        List<Duters> dutersList = new ArrayList<Duters>();
        try {
            String prepareSql = "select * from duters";
            //conn = getConn();
            pstmt = conn.prepareStatement(prepareSql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Duters duters = new Duters();
                duters.setId(rs.getInt(1));//�û���ʶ��
                duters.setUser_name(rs.getString(2));//�û�����
                duters.setUser_identity(rs.getString(3));//�û����(ѧ��)
                duters.setTel(rs.getString(4));//ѧ���绰
                dutersList.add(duters);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return dutersList;
    }

    @Override
    public List<Duters> getDuters(String sql, String[] param){
    	List<Duters> Duterslist = new ArrayList<Duters>();

        try{
            //conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if(param != null){
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Duters duters=null;
            while (rs.next()){
                duters = new Duters();
                duters.setId(rs.getInt(1));//�û���ʶ��
                duters.setUser_name(rs.getString(2));//�û�����
                duters.setUser_identity(rs.getString(3));//�û����(ѧ��)
//                duters.setPassword(rs.getString(4));//ѧ������
                duters.setTel(rs.getString(4));//ѧ���绰
                Duterslist.add(duters);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return Duterslist;
    }
    
    public Duters getDuter(String sql, String[] param){
        Duters duter=null;
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
                duter = new Duters();
                duter.setId(rs.getInt(1));//�û���ʶ��
                duter.setUser_name(rs.getString(2));//�û�����
                duter.setUser_identity(rs.getString(3));//�û����(ѧ��)
//                duter.setPassword(rs.getString(4));//ѧ������
                duter.setTel(rs.getString(4));//ѧ���绰
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pstmt,rs);
        }
        return duter;
    }

    @Override
    public int updateDuters(String sql, Object[] param) {
        int count = executeSQL(sql, param);
        return count;
    }
}
