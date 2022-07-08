package pianoCompany.dao.Impl;

import pianoCompany.dao.CourseDao;
import pianoCompany.dao.BaseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pianoCompany.entity.Course;

public class CourseDaoImpl extends BaseDao implements CourseDao {
	    private PreparedStatement pstmt = null;//����ִ��sql���
	    private ResultSet rs = null;//���ڱ���sql��ѯ�����

	    @Override
	    public List<Course> getAllCourse() {
	        List<Course> courseList = new ArrayList<Course>();
	        try {
	            String prepareSql = "select * from course";
	            //conn = getConn();
	            pstmt = conn.prepareStatement(prepareSql);
	            rs = pstmt.executeQuery();
	            while (rs.next()){
	                Course course = new Course();
	                course.setCourseid(rs.getString(1));//�γ̺�
	                course.setSubject(rs.getString(2));//�γ̿�Ŀ
	                course.setLevel(rs.getString(3));//�γ̵ȼ�
	                course.setPrice(rs.getDouble(4));//�γ̼۸�
	                course.setStarttime(rs.getDate(5));//�γ̿�ʼʱ��
	                course.setEndtime(rs.getDate(6));//�γ̽���ʱ��
	                courseList.add(course);
	            }
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }finally {
	            close(pstmt,rs);
	        }
	        return courseList;
	    }

	    @Override
	    public  List<Course> getCourse(String sql, String[] param){
	    	List<Course> CourseList = new ArrayList<Course>();
	        try{
	            //conn = getConn();
	            pstmt = conn.prepareStatement(sql);
	            if(param != null){
	                for(int i=0;i<param.length;i++){
	                    pstmt.setObject(i+1,param[i]);
	                }
	            }
	            rs = pstmt.executeQuery();
	            Course course=null;
	            while (rs.next()){
	                course = new Course();
	                course.setCourseid(rs.getString(1));
	                course.setSubject(rs.getString(2));
	                course.setLevel(rs.getString(3));
	                course.setPrice(rs.getDouble(4));
	                course.setStarttime(rs.getDate(5));
	                course.setEndtime(rs.getDate(6));
	                CourseList.add(course);
	            }
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }finally {
	            close(pstmt,rs);
	        }
	        return CourseList;
	    }

	    @Override
	    public int updateCourse(String sql, Object[] param) {
	        int count = executeSQL(sql,param);
	        return count;
	    }

}
