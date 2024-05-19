package DAO;

import DTO.SanPhamDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class KhoHangDAO {   
    public ArrayList<SanPhamDTO> getDataFromSQL() {

        ArrayList<SanPhamDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from KhoHang");

            while (rs.next()) {
                String IDSanPham = rs.getString("IDSanPham");
                String TenSanPham = rs.getString("TenSanPham");
                int TonKho = rs.getInt("TonKho");
                Double DonGia = rs.getDouble("DonGia");

                list.add(new SanPhamDTO(IDSanPham, TenSanPham, TonKho, DonGia));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int capNhatSoLuongSanPham(SanPhamDTO sanPham) {
        String sqlQuery = "update KhoHang set TonKho = ? where IDSanPham = ?";
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
