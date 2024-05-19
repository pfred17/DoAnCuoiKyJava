package DAO;

import DTO.ChiTietHoaDonDTO;
import java.sql.*;
import connectionDB.SQLServerConnection;
import java.util.ArrayList;
public class ChiTietHoaDonDAO {
    public ArrayList<ChiTietHoaDonDTO> getDataFromSQL() {
        ArrayList<ChiTietHoaDonDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from ChiTietHoaDon");
            
            while (rs.next()) {
                String IDHoaDon = rs.getString("IDHoaDOn");
                String IDSanPham = rs.getString("IDSanPham");
                int SoLuong = rs.getInt("SoLuong");
                double DonGia = rs.getDouble("DonGia");
                double ThanhTien = rs.getDouble("ThanhTien");
                double TienGiamGia = rs.getDouble("TienGiamGia");
                
                list.add(new ChiTietHoaDonDTO(IDHoaDon, IDSanPham, SoLuong, DonGia, ThanhTien, TienGiamGia));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
