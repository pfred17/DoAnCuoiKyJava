package DAO;

import DTO.ChiTietPhieuNhapDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhapDAO {
    public ArrayList<ChiTietPhieuNhapDTO> getDataFromSQL() {

        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from ChiTietPhieuNhap");

            while (rs.next()) {
                String IDChiTietPhieuNhap = rs.getString("IDChiTietPhieuNhap");
                String IDPhieuNhap = rs.getString("IDPhieuNhap");
                String IDSanPham = rs.getString("IDSanPham");
                int SoLuong = rs.getInt("SoLuong");
                Double DonGia = rs.getDouble("DonGia");

                list.add(new ChiTietPhieuNhapDTO(IDChiTietPhieuNhap, IDPhieuNhap, IDSanPham, SoLuong, DonGia));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int addChiTietHoaDon(ChiTietPhieuNhapDTO chiTiet) {
        String sqlQuery = "insert into ChiTietPhieuNhap(IDChiTietPhieuNhap, IDPhieuNhap, IDSanPham, SoLuong, DonGia) values(?, ?, ?, ?, ?)";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, chiTiet.getIDChiTietPhieuNhap());
            pr.setString(2, chiTiet.getIDPhieuNhap());
            pr.setString(3, chiTiet.getIDSanPham());
            pr.setInt(4, chiTiet.getSoLuong());
            pr.setDouble(5, chiTiet.getDonGia());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
