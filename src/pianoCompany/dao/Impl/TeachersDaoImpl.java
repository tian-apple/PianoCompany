package pianoCompany.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pianoCompany.dao.TeachersDao;
import pianoCompany.entity.Teachers;
import pianoCompany.dao.BaseDao;
public class TeachersDaoImpl extends BaseDao implements TeachersDao{
    private PreparedStatement pstmt = null;//用于执行sql语句
    private ResultSet rs = null;//用于保存sql查询结果集

    @Override
    public List<Teachers> getAllTeachers() {
        List<Teachers> teachersList = new ArrayList<Teachers>();
        try {
            String prepareSql = "select * from Teachers";
            //conn = getConn();
            pstmt = conn.prepareStatement(prepareSql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Teachers teachers = new Teachers();
                teachers.setId(rs.getInt(1));//用户标识符
                teachers.setUser_name(rs.getString(2));//用户姓名
                teachers.setUser_identity(rs.getString(3));//用户身份(教师)
                teachers.setTel(rs.getString(4));//教师电话
                teachers.setSalary(rs.getDouble(5));//教师薪资
                teachersList.add(teachers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return teachersList;
    }

    @Override
    public List<Teachers> getTeachers(String sql, String[] param) {
        List<Teachers> Teacherslist = new ArrayList<Teachers>();
        try{
            //conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if(param != null){
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Teachers teachers=null;
            while (rs.next()){
                teachers = new Teachers();
                teachers.setId(rs.getInt(1));//用户标识符
                teachers.setUser_name(rs.getString(2));//用户姓名
                teachers.setUser_identity(rs.getString(3));//用户身份(教师)
//                teachers.setPassword(rs.getString(4));//教师密码
                teachers.setTel(rs.getString(4));//教师电话
                teachers.setSalary(rs.getDouble(5));//教师薪资
                Teacherslist.add(teachers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pstmt,rs);
        }
        return Teacherslist;
    }
    
    public Teachers getTeacher(String sql, String[] param) {
        Teachers teacher=null;
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
                teacher = new Teachers();
                teacher.setId(rs.getInt(1));//用户标识符
                teacher.setUser_name(rs.getString(2));//用户姓名
                teacher.setUser_identity(rs.getString(3));//用户身份(教师)
//                teachers.setPassword(rs.getString(4));//教师密码
                teacher.setTel(rs.getString(4));//教师电话
                teacher.setSalary(rs.getDouble(5));//教师薪资
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pstmt,rs);
        }
        return teacher;
    }

    @Override
    public int updateTeachers(String sql, Object[] param) {
        int count = executeSQL(sql, param);
        return count;
    }
}
