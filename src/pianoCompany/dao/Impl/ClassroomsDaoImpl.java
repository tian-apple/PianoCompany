package pianoCompany.dao.Impl;
import java.util.*;
import java.util.List;
import pianoCompany.dao.BaseDao;
import pianoCompany.dao.ClassroomsDao;
import pianoCompany.entity.Classrooms;

import java.sql.*;
public class ClassroomsDaoImpl extends BaseDao implements ClassroomsDao{
    private PreparedStatement pstmt = null;//����ִ��sql���
    private ResultSet rs = null;//���ڱ���sql��ѯ�����

    @Override
    public List<Classrooms> getAllClassrooms() {
        List<Classrooms> classroomsList = new ArrayList<Classrooms>();
        try {
            String prepareSql = "select * from classroom ";
            //conn = getConn();
            pstmt = conn.prepareStatement(prepareSql);
            rs = pstmt.executeQuery();
            while (rs.next()){

            	Classrooms classrooms = new Classrooms();
                classrooms.setCourse_id(rs.getString(1));
                classrooms.setDut_id(rs.getString(2));
                classrooms.setTea_id(rs.getString(3));
                classroomsList.add(classrooms);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return classroomsList;
    }

    @Override
    public List<Classrooms> getClassrooms(String sql, String[] param) {
        List<Classrooms> ClassroomsList = new ArrayList<Classrooms>();
        try {
            //conn = getConn(); // �õ����ݿ�����
            pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
                }
            }
            rs = pstmt.executeQuery(); // ִ��SQL���
            Classrooms classrooms=null;
            while(rs.next()){
            	classrooms=new Classrooms();
            	classrooms.setCourse_id(rs.getString(1));
            	classrooms.setDut_id(rs.getString(2));
            	classrooms.setTea_id(rs.getString(3));
            	ClassroomsList.add(classrooms);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return ClassroomsList;
    }
    
    public List<String> getCourseid(String sql, String[] param) {
        List<String> CourseidList = new ArrayList<String>();
        try {
            //conn = getConn(); // �õ����ݿ�����
            pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
                }
            }
            rs = pstmt.executeQuery(); // ִ��SQL���
            String Courseid=null;
            while(rs.next()){
            	Courseid=rs.getString(1);
            	CourseidList.add(Courseid);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return CourseidList;
    }
    
    public List<Integer> getTeaid(String sql, String[] param) {
        List<Integer> TeaidList = new ArrayList<Integer>();
        try {
            //conn = getConn(); // �õ����ݿ�����
            pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
                }
            }
            rs = pstmt.executeQuery(); // ִ��SQL���
            int Teaid;
            while(rs.next()){
            	Teaid=rs.getInt(1);
            	TeaidList.add(Teaid);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return TeaidList;
    }

    @Override
    public int updateClassrooms(String sql, Object[] param) {
        int count = executeSQL(sql,param);
        return count;
    }
}

