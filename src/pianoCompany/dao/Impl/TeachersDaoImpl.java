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
    private PreparedStatement pstmt = null;//����ִ��sql���
    private ResultSet rs = null;//���ڱ���sql��ѯ�����

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
                teachers.setId(rs.getInt(1));//�û���ʶ��
                teachers.setUser_name(rs.getString(2));//�û�����
                teachers.setUser_identity(rs.getString(3));//�û����(��ʦ)
                teachers.setTel(rs.getString(4));//��ʦ�绰
                teachers.setSalary(rs.getDouble(5));//��ʦн��
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
                teachers.setId(rs.getInt(1));//�û���ʶ��
                teachers.setUser_name(rs.getString(2));//�û�����
                teachers.setUser_identity(rs.getString(3));//�û����(��ʦ)
//                teachers.setPassword(rs.getString(4));//��ʦ����
                teachers.setTel(rs.getString(4));//��ʦ�绰
                teachers.setSalary(rs.getDouble(5));//��ʦн��
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
                teacher.setId(rs.getInt(1));//�û���ʶ��
                teacher.setUser_name(rs.getString(2));//�û�����
                teacher.setUser_identity(rs.getString(3));//�û����(��ʦ)
//                teachers.setPassword(rs.getString(4));//��ʦ����
                teacher.setTel(rs.getString(4));//��ʦ�绰
                teacher.setSalary(rs.getDouble(5));//��ʦн��
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
