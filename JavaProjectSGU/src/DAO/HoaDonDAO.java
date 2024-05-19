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
                String IDHoaDon = rs.getString("IDHoaDOn");
                String IDKhachHang = rs.getString("IDKhachHang");
                String IDNhanVien = rs.getString("IDNhanVien");
                String IDGiamGia = rs.getString("IDGiamGia");
                Date ngayTaoHoaDon = rs.getDate("NgayTaoHoaDon"); 
                double TongTien = rs.getDouble("TongTien");
                double TienGiamGia = rs.getDouble("TienGiamGia");
                
                list.add(new HoaDonDTO(IDHoaDon, IDKhachHang, IDNhanVien, IDGiamGia, ngayTaoHoaDon, TongTien, TienGiamGia));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
