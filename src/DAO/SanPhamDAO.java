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
                String ThuongHieu = rs.getString("ThuongHieu");
                Double GiaNhap = rs.getDouble("GiaNhap");

                list.add(new SanPhamDTO(IDSanPham, IDLoaiSanPham, TenSanPham, SoLuong, DonGia, TrangThai, ThuongHieu, GiaNhap));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int addSanPham(SanPhamDTO sanPham) {
        String sqlQuery = "insert into SanPham(IDSanPham, IDLoaiSanPham, TenSanPham, ThuongHieu,SoLuong, DonGia, TrangThai) values(?, ?, ?, ?, ?, ?, 0)";
        
         try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, sanPham.getIDSanPham());
            pr.setString(2, sanPham.getIDLoaiSanPham());
            pr.setString(3, sanPham.getTenSanPham());
            pr.setString(4, sanPham.getThuongHieu());
            pr.setInt(5, sanPham.getSoluong());
            pr.setDouble(6, sanPham.getDonGia());
            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int addSanPhamNhap(SanPhamDTO sanPham) {
        String sqlQuery = "insert into SanPham(IDSanPham, IDLoaiSanPham, TenSanPham, ThuongHieu,SoLuong, DonGia, TrangThai, GiaNhap) values(?, ?, ?, ?, ?, ?, 0, ?)";
        
         try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, sanPham.getIDSanPham());
            pr.setString(2, sanPham.getIDLoaiSanPham());
            pr.setString(3, sanPham.getTenSanPham());
            pr.setString(4, sanPham.getThuongHieu());
            pr.setInt(5, sanPham.getSoluong());
            pr.setDouble(6, sanPham.getDonGia());
            pr.setDouble(7, sanPham.getGiaNhap());
            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateSanPham(SanPhamDTO sanPham) {
        String sqlQuery = "update SanPham set TenSanPham = ?,SoLuong = ?, DonGia = ?, ThuongHieu = ? where IDSanPham = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, sanPham.getTenSanPham());
            pr.setInt(2, sanPham.getSoluong());
            pr.setDouble(3, sanPham.getDonGia());
            pr.setString(4, sanPham.getThuongHieu());
            pr.setString(5, sanPham.getIDSanPham());

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
    
    public int capNhatSoLuongSanPham(SanPhamDTO sanPham) {
        String sqlQuery = "update SanPham set SoLuong = ? where IDSanPham = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setInt(1, sanPham.getSoluong());
            pr.setString(2, sanPham.getIDSanPham());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
   
}
