package DAO;

import DTO.ChiTietChuongTrinhGiamGiaDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietChuongTrinhGiamGiaDAO {
    public ArrayList<ChiTietChuongTrinhGiamGiaDTO> getDataFromSQL() {

        ArrayList<ChiTietChuongTrinhGiamGiaDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from ChiTietChuongTrinhGiamGia");

            while (rs.next()) {
                String IDGiamGia = rs.getString("IDGiamGia");
                String IDSanPham = rs.getString("IDSanPham");
                String NDChuongTrinhGiamGia = rs.getString("NoiDungGiamGia");
                double PhanTramGiamGia = rs.getDouble("PhanTramGiamGia");

                list.add(new ChiTietChuongTrinhGiamGiaDTO(IDGiamGia, IDSanPham, NDChuongTrinhGiamGia, PhanTramGiamGia));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int updateChuongTrinhGiamGia(ChiTietChuongTrinhGiamGiaDTO chiTietChuongTrinhGiamGiaDTO) {
        String sqlQuery = "update ChiTietChuongTrinhGiamGia set IDSanPham = ?,NoiDungGiamGia = ?, PhanTramGiamGia = ?  where IDGiamGia = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, chiTietChuongTrinhGiamGiaDTO.getIDSanPham());
            pr.setString(2,chiTietChuongTrinhGiamGiaDTO.getNDChuongTrinhGiamGia());
            pr.setDouble(3,chiTietChuongTrinhGiamGiaDTO.getPhanTramGiamGia());
            pr.setString(4, chiTietChuongTrinhGiamGiaDTO.getIDGiamGia());
            
            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
