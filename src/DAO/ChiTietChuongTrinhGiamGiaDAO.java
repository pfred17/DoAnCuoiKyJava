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
                double PhanTramGiamGia = rs.getDouble("PhanTramGiamGia");

                list.add(new ChiTietChuongTrinhGiamGiaDTO(IDGiamGia, IDSanPham, PhanTramGiamGia));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addChiTietCTGG(ChiTietChuongTrinhGiamGiaDTO chiTietChuongTrinhGiamGiaDTO) {
        String sqlQuery = "insert into ChiTietChuongTrinhGiamGia(IDGiamGia, IDSanPham, PhanTramGiamGia) values(?, ?, ?)";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, chiTietChuongTrinhGiamGiaDTO.getIDGiamGia());
            pr.setString(2, chiTietChuongTrinhGiamGiaDTO.getIDSanPham());
            pr.setDouble(3, chiTietChuongTrinhGiamGiaDTO.getPhanTramGiamGia());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateChiTietChuongTrinhGiamGia(ChiTietChuongTrinhGiamGiaDTO chiTietChuongTrinhGiamGiaDTO) {
        String sqlQuery = "update ChiTietChuongTrinhGiamGia set PhanTramGiamGia = ?  where IDGiamGia = ? and IDSanPham = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setDouble(1, chiTietChuongTrinhGiamGiaDTO.getPhanTramGiamGia());
            pr.setString(2, chiTietChuongTrinhGiamGiaDTO.getIDGiamGia());
            pr.setString(3, chiTietChuongTrinhGiamGiaDTO.getIDSanPham());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteChiTietChuongTrinhGiamGia(String IDSanPham, String IDGiamGia) {
        String sqlQuery = "delete from ChiTietChuongTrinhGiamGia where IDGiamGia = ? and IDSanPham = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, IDGiamGia);
            pr.setString(2, IDSanPham);

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
