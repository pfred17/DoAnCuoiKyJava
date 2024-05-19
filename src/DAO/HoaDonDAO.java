package DAO;

import DTO.HoaDonDTO;
import java.sql.*;
import connectionDB.SQLServerConnection;
import java.util.ArrayList;

public class HoaDonDAO {
    public ArrayList<HoaDonDTO> getDataFromSQL() {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from HoaDon");
            
            while (rs.next()) {
                String IDHoaDon = rs.getString("IDHoaDon");
                String IDKhachHang = rs.getString("IDKhachHang");
                String IDNhanVien = rs.getString("IDNhanVien");
                String IDGiamGia = rs.getString("IDGiamGia");
                Date ngayTaoHoaDon = rs.getDate("NgayLapHoaDon"); 
                double TongTien = rs.getDouble("TongTien");
                double TienGiamGia = rs.getDouble("TienGiamGia");
                int TrangThai = rs.getInt("TrangThai");
                
                list.add(new HoaDonDTO(IDHoaDon, IDKhachHang, IDNhanVien, IDGiamGia, ngayTaoHoaDon, TongTien, TienGiamGia,TrangThai));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int addHoaDon(HoaDonDTO hoaDon) {
        String sqlQuery = "insert into HoaDon(IDHoaDon, IDKhachHang, IDNhanVien, IDGiamGia, NgayLapHoaDon, TongTien, TienGiamGia, TrangThai)" + "values(?, ?, ?, ?, ?, ?, ?, 0)";
        try {
            Connection connection=SQLServerConnection.getConnection();
            PreparedStatement pr=connection.prepareStatement(sqlQuery);
            
            pr.setString(1, hoaDon.getIDHoaDon());
            pr.setString(2, hoaDon.getIDKhachHang());
            pr.setString(3, hoaDon.getIDNhanVien());
            pr.setString(4, hoaDon.getIDGiamGia());
            pr.setDate(5, new java.sql.Date(hoaDon.getNgayLapHoaDon().getTime()));
            pr.setDouble(6, hoaDon.getTongTien());
            pr.setDouble(7, hoaDon.getTienGiamGia());
           
            return pr.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int deleteHoaDon(String IDHoaDon) {
        String sqlQuery = "update HoaDon set TrangThai = 1 where IDHoaDon = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, IDHoaDon);

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateHoaDon(HoaDonDTO hoaDon) {
        String sqlQuery = "update HoaDon set IDKhachHang = ?, IDNhanVien = ?, IDGiamGia = ?, NgayLapHoaDon = ?, TongTien = ?, TienGiamGia = ? where IDHoaDon = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, hoaDon.getIDKhachHang());
            pr.setString(2, hoaDon.getIDNhanVien());
            pr.setString(3, hoaDon.getIDGiamGia());
            pr.setDate(4, new java.sql.Date(hoaDon.getNgayLapHoaDon().getTime()));
            pr.setDouble(5, hoaDon.getTongTien());
            pr.setDouble(6, hoaDon.getTongTien());
            pr.setString(7, hoaDon.getIDHoaDon());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
