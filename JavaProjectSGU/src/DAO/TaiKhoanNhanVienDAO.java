package DAO;

import DTO.TaiKhoanNhanVienDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class TaiKhoanNhanVienDAO {
    
    public ArrayList<TaiKhoanNhanVienDTO> getDataFromSQL() {
        
        ArrayList<TaiKhoanNhanVienDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from TaiKhoanNhanVien");
            
            while(rs.next()) {
                String IDNhanVien = rs.getString("IDNhanVien");
                String matKhau = rs.getString("MatKhau");
                String TrangThai = rs.getString("TrangThaiTaiKhoan");
                
                list.add(new TaiKhoanNhanVienDTO(IDNhanVien, matKhau, TrangThai));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Hàm khởi tạo tài khoản nhân viên (chưa được cấp tài khoản)
    public int createTaiKhoanNhanVien(String IDNhanVien) {
        String sqlQuery = "insert into TaiKhoanNhanVien(IDNhanVien, MatKhau, TrangThaiTaiKhoan)"
                        + "values(?, 123456, 0)";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);
                
            pr.setString(1, IDNhanVien);
            
            return pr.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    // Hàm thêm\cấp tài khoản nhân viên
    public int addTaiKhoanNhanVien(String IDNhanVien) {
        String sqlQuery = "update TaiKhoanNhanVien set TrangThaiTaiKhoan = 1 where IDNhanVien = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);
                
            pr.setString(1, IDNhanVien);
            
            return pr.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    // Hàm khóa tài khoản nhân viên
    public int blockTaiKhoanNhanVien(String IDNhanVien) {
        String sqlQuery = "update TaiKhoanNhanVien set TrangThaiTaiKhoan = 2 where IDNhanVien = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);
                
            pr.setString(1, IDNhanVien);
            
            return pr.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}
