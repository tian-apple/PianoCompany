package pianoCompany.dao.Impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pianoCompany.dao.InstrumentDao;
import pianoCompany.entity.Instrument;
import pianoCompany.dao.BaseDao;
public class InstrumentDaoImpl extends BaseDao implements InstrumentDao{
    private PreparedStatement pstmt = null;//����ִ��sql���
    private ResultSet rs = null;//���ڱ���sql��ѯ�����

    @Override
    public List<Instrument> getAllInstrument() {
        List<Instrument> instrumentList = new ArrayList<Instrument>();
        try {
            String prepareSql = "select * from instrument";
            //conn = getConn();
            pstmt = conn.prepareStatement(prepareSql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Instrument instrument = new Instrument();
                instrument.setId(rs.getInt(1));//������ʶ��
                instrument.setBrand(rs.getString(2));//����Ʒ��
                instrument.setType(rs.getString(3));//��������
                instrument.setPrice(rs.getDouble(4));//�����۸�
                instrument.setModel(rs.getString(5));//�����ͺ�
                instrumentList.add(instrument);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pstmt,rs);
        }
        return instrumentList;
    }

    @Override
    public List<Instrument> getInstrument(String sql, Object[] param) {
        List<Instrument> Instrumentlist = new ArrayList<Instrument>();
        try{
            //conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if(param != null){
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Instrument instrument=null;
            while (rs.next()){
                instrument = new Instrument();
                instrument.setId(rs.getInt(1));//������ʶ��
                instrument.setBrand(rs.getString(2));//����Ʒ��
                instrument.setType(rs.getString(3));//��������
                instrument.setPrice(rs.getDouble(4));//�����۸�
                instrument.setModel(rs.getString(5));//�����ͺ�
                Instrumentlist.add(instrument);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(pstmt,rs);
        }
        return Instrumentlist;
    }

    @Override
    public int updateInstrument(String sql, Object[] param) {
        int count = executeSQL(sql, param);
        return count;
    }
}
