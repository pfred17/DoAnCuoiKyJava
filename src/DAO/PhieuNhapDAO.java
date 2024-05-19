package DAO;

import DTO.PhieuNhapDTO;
import java.sql.*;
import connectionDB.SQLServerConnection;
import java.util.ArrayList;

public class PhieuNhapDAO {
    public ArrayList<PhieuNhapDTO> getDataFromSQL() {
        ArrayList<PhieuNhapDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from PhieuNhap");
            
            while (rs.next()) {
                String IDPhieuNhap = rs.getString("IDPhieuNhap");
                String IDNhaCungCap = rs.getString("IDNhaCungCap");
                String IDNhanVien = rs.getString("IDNhanVien");
                Date NgayNhapHang = rs.getDate("NgayNhapHang"); 
                double TongTien = rs.getDouble("TongTien");
                int TrangThai = rs.getInt("TrangThai");
                
                list.add(new PhieuNhapDTO(IDPhieuNhap, IDNhanVien, IDNhaCungCap, NgayNhapHang, TongTien, TrangThai));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int addPhieuNhap(PhieuNhapDTO phieuNhap) {
        String sqlQuery = "insert into PhieuNhap(IDPhieuNhap, IDNhaCungCap, IDNhanVien, NgayNhapHang, TongTien, TrangThai)" + "values(?, ?, ?, ?, ?, 0)";
        try {
            Connection connection=SQLServerConnection.getConnection();
            PreparedStatement pr=connection.prepareStatement(sqlQuery);
            
            pr.setString(1, phieuNhap.getIDPhieuNhap());
            pr.setString(2, phieuNhap.getIDNhaCungCap());
            pr.setString(3, phieuNhap.getIDNhanVien());
            pr.setDate(4, new java.sql.Date(phieuNhap.getNgayNhapHang().getTime()));
            pr.setDouble(5, phieuNhap.getTongTien());
           
            return pr.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int deletePhieuNhap(String IDPhieuNhap) {
        String sqlQuery = "update PhieuNhap set TrangThai = 1 where IDPhieuNhap = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, IDPhieuNhap);

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
     public int updatePhieuNhap(PhieuNhapDTO phieuNhap) {
        String sqlQuery = "update PhieuNhap set IDNhaCungCap = ?, IDNhanVien = ?, NgayLapHoaDon = ?, TongTien = ? where IDPhieuNhap = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, phieuNhap.getIDNhaCungCap());
            pr.setString(2, phieuNhap.getIDNhanVien());
            pr.setDate(3, new java.sql.Date(phieuNhap.getNgayNhapHang().getTime()));
            pr.setDouble(4, phieuNhap.getTongTien());
            pr.setString(5, phieuNhap.getIDPhieuNhap());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
