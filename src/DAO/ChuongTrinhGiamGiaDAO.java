package DAO;

import DTO.ChuongTrinhGiamGiaDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class ChuongTrinhGiamGiaDAO {

    public ArrayList<ChuongTrinhGiamGiaDTO> getDataFromSQL() {

        ArrayList<ChuongTrinhGiamGiaDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from ChuongTrinhGiamGia");

            while (rs.next()) {
                String IDGiamGia = rs.getString("IDGiamGia");
                String TenCT = rs.getString("TenChuongTrinh");
                String LoaiCT = rs.getString("LoaiChuongTrinh");
                Date ThoiGianKhoidau = rs.getDate("TG_khoiDau");
                Date ThoiGianKetthuc = rs.getDate("TG_KetThuc");
                int TrangThai = rs.getInt("TrangThai");

                list.add(new ChuongTrinhGiamGiaDTO(IDGiamGia, TenCT, LoaiCT, ThoiGianKhoidau, ThoiGianKetthuc, TrangThai));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addChuongTrinhGiamGia(ChuongTrinhGiamGiaDTO chuongTrinhGiamGiaDTO) {
        String sqlQuery = "insert into ChuongTrinhGiamGia(IDGiamGia, TenChuongTrinh, LoaiChuongTrinh, TG_khoiDau, TG_KetThuc, TrangThai) values(?, ?, ?, ?, ?, 0)";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, chuongTrinhGiamGiaDTO.getIDGiamGia());
            pr.setString(2, chuongTrinhGiamGiaDTO.getTenCT());
            pr.setString(3, chuongTrinhGiamGiaDTO.getLoaiCT());
            pr.setDate(4, new java.sql.Date(chuongTrinhGiamGiaDTO.getThoiGianKhoidau().getTime()));
            pr.setDate(5, new java.sql.Date(chuongTrinhGiamGiaDTO.getThoiGianKetthuc().getTime()));

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateChuongTrinhGiamGia(ChuongTrinhGiamGiaDTO chuongTrinhGiamGiaDTO) {
        String sqlQuery = "update ChuongTrinhGiamGia set TenChuongTrinh = ?,LoaiChuongTrinh = ?, TG_khoiDau = ?, TG_KetThuc = ?  where IDGiamGia = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, chuongTrinhGiamGiaDTO.getTenCT());
            pr.setString(2, chuongTrinhGiamGiaDTO.getLoaiCT());
            pr.setDate(3, new java.sql.Date(chuongTrinhGiamGiaDTO.getThoiGianKhoidau().getTime()));
            pr.setDate(4, new java.sql.Date(chuongTrinhGiamGiaDTO.getThoiGianKetthuc().getTime()));
            pr.setString(5, chuongTrinhGiamGiaDTO.getIDGiamGia());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteChuongTrinhGiamGia(String IDGiamGia) {
        String sqlQuery = "update ChuongTrinhGiamGia set TrangThai = 1 where IDGiamGia = ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, IDGiamGia);

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
