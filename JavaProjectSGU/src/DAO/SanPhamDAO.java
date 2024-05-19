package DAO;

import DTO.SanPhamDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class SanPhamDAO {
    public ArrayList<SanPhamDTO> getDataFromSQL() {

        ArrayList<SanPhamDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from SanPham");

            while (rs.next()) {
                String IDSanPham = rs.getString("IDSanPham");
                String IDLoaiSanPham = rs.getString("IDLoaiSanPham");
                String TenSanPham = rs.getString("TenSanPham");
                int SoLuong = rs.getInt("SoLuong");
                Double DonGia = rs.getDouble("DonGia");
                int TrangThai = rs.getInt("TrangThai");

                list.add(new SanPhamDTO(IDSanPham, IDLoaiSanPham, TenSanPham, SoLuong, DonGia, TrangThai));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int addSanPham(SanPhamDTO sanPham) {
        String sqlQuery = "insert into SanPham(IDSanPham, IDLoaiSanPham, TenSanPham, SoLuong, DonGia, TrangThai) values(?, ?, ?, ?, ?, 0)";
        
         try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, sanPham.getIDSanPham());
            pr.setString(2, sanPham.getIDLoaiSanPham()  );
            pr.setString(3, sanPham.getTenSanPham());
            pr.setInt(4, sanPham.getSoluong());
            pr.setDouble(5, sanPham.getDongia());
            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateSanPham(SanPhamDTO sanPham) {
        String sqlQuery = "update SanPham set TenSanPham = ?,SoLuong = ?, DonGia = ? where IDSanPham = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, sanPham.getTenSanPham());
            pr.setInt(2, sanPham.getSoluong());
            pr.setDouble(3, sanPham.getDongia());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int deleteSanPham(String IDSanPham) {
        String sqlQuery = "update SanPham set TrangThai = 1 where IDSanPham = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, IDSanPham);

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
   
}
