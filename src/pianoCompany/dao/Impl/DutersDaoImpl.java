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
    private PreparedStatement pstmt = null;//用于执行sql语句
    private ResultSet rs = null;//用于保存sql查询结果集

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
                duters.setId(rs.getInt(1));//用户标识符
                duters.setUser_name(rs.getString(2));//用户姓名
                duters.setUser_identity(rs.getString(3));//用户身份(学生)
                duters.setTel(rs.getString(4));//学生电话
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
                duters.setId(rs.getInt(1));//用户标识符
                duters.setUser_name(rs.getString(2));//用户姓名
                duters.setUser_identity(rs.getString(3));//用户身份(学生)
//                duters.setPassword(rs.getString(4));//学生密码
                duters.setTel(rs.getString(4));//学生电话
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
                duter.setId(rs.getInt(1));//用户标识符
                duter.setUser_name(rs.getString(2));//用户姓名
                duter.setUser_identity(rs.getString(3));//用户身份(学生)
//                duter.setPassword(rs.getString(4));//学生密码
                duter.setTel(rs.getString(4));//学生电话
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
