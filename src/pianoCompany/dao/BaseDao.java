package pianoCompany.dao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
/**
 * ���ݿ��������
 */
public class BaseDao {
	public static String DRIVER; // ���ݿ�����

	public static String URL ; // url

	public static String DBNAME; // ���ݿ��û���

	public static String DBPASS; // ���ݿ�����
	
	public static Connection conn = null;// �������Ӷ���
	
	static{//��̬�����,������ص�ʱ��ִ��
		init();
		try {
			conn=getConn();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ʼ�����Ӳ���,�������ļ�����
	 * @throws IOException 
	 */
		public static void init(){
			Properties params=new Properties();
			String configFile = "database.properties";//�����ļ�·��
			//���������ļ�����������
			InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
			
			try {
				//���������ж�ȡ�����б�
				params.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//����ָ���Ļ�ȡ��Ӧ��ֵ
			DRIVER=params.getProperty("driver");
			URL=params.getProperty("url");
			DBNAME=params.getProperty("user");
			DBPASS=params.getProperty("password");
			
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}   

	/**
	 * �õ����ݿ�����
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return ���ݿ�����
	 */
	public static Connection getConn() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // ע������
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // ������ݿ�����
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn; // ��������
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param pstmt
	 *            PreparedStatement����
	 * @param rs
	 *            �����
	 */
	public void close(PreparedStatement pstmt, ResultSet rs) {

		/* ���rs���գ��ر�rs */
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/* ���pstmt���գ��ر�pstmt */
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConn() {
		/* ���conn���գ��ر�conn */
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ִ��SQL��䣬���Խ�������ɾ���ĵĲ���������ִ�в�ѯ
	 * 
	 * @param sql
	 *            Ԥ����� SQL ���
	 * @param param
	 *            Ԥ����� SQL ����еġ������������ַ�������
	 * @return Ӱ�������
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		//Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* ����SQL,ִ��SQL */
		try {
			//conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
		
			num = pstmt.executeUpdate(); // ִ��SQL���
		} catch (SQLException e) {
			e.printStackTrace(); // ����SQLException�쳣
		} finally {
			this.close(pstmt, null);
		}
		return num;
	}
}

