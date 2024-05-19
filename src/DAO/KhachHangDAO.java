
package DAO;
import java.sql.*;

import DTO.KhachHangDTO;
import connectionDB.SQLServerConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang Phu
 */
public class KhachHangDAO {
    public ArrayList<KhachHangDTO> getDataFromSQL(){
       ArrayList<KhachHangDTO> list =new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();

            Statement state = connection.createStatement();
            ResultSet rs=state.executeQuery("select* from KhachHang");
            while(rs.next()){
                String IDKhachHang=rs.getString("IDKhachHang");
                String hoKhachHang=rs.getString("HoKhachHang");
                String tenKhachHang=rs.getString("TenKhachHang");
                String SDT=rs.getString("SDT");
                String Gmail=rs.getString("Gmail");
                String gioiTinh=rs.getString("GioiTinh");
                double tongChiTeu=rs.getDouble("TongChiTieu");
                int TrangThai=rs.getInt("TrangThai");
                
                list.add(new KhachHangDTO(IDKhachHang,hoKhachHang,tenKhachHang,SDT,Gmail,gioiTinh,tongChiTeu,TrangThai));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public int addkhachHang(KhachHangDTO khachHang){
        String sqlQuery="insert into KhachHang(IDKhachHang,HoKhachHang,TenKhachHang,SDT,Gmail,GioiTinh,TongChiTieu,TrangThai)"+"values(?,?,?,?,?,?,?,0)";
        try {
            Connection connection=SQLServerConnection.getConnection();
            PreparedStatement pr=connection.prepareStatement(sqlQuery);
            
            pr.setString(1, khachHang.getIDKhachHang());
            pr.setString(2, khachHang.getHoKH());
            pr.setString(3, khachHang.getTenKh());
            pr.setString(4, khachHang.getSDT());
            pr.setString(5, khachHang.getGmail());
            pr.setString(6, khachHang.getGioiTinh());
            pr.setDouble(7, khachHang.getTongchitieu());
           
            return pr.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int updateKhachHang(KhachHangDTO khachHang){
        String sqlQuery="update KhachHang set HoKhachHang=?,TenKhachHang=?,SDT=?,Gmail=?,GioiTinh=?,TongChiTieu=? where IDKhachHang=?";
        try {
            Connection connection=SQLServerConnection.getConnection();
            PreparedStatement pr=connection.prepareStatement(sqlQuery);
            pr.setString(1, khachHang.getHoKH());
            pr.setString(2, khachHang.getTenKh());
            pr.setString(3, khachHang.getSDT());
            pr.setString(4, khachHang.getGmail()); 
            pr.setString(5, khachHang.getGioiTinh());
            pr.setDouble(6, khachHang.getTongchitieu());
            pr.setString(7, khachHang.getIDKhachHang());

            
            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int deleteKhachHang(String IDKhachHang){
        String sqlQuery="update KhachHang set TrangThai = 1 where IDKhachHang = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, IDKhachHang);
            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
    }
    
}
