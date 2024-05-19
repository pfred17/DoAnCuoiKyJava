package DAO;

import DTO.NhanVienDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class NhanVienDAO {

    public ArrayList<NhanVienDTO> getDataFromSQL() {

        ArrayList<NhanVienDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from NhanVien inner join TaiKhoanNhanVien on NhanVien.IDNhanVien = TaiKhoanNhanVien.IDNhanVien");

            while (rs.next()) {
                String IDNhanVien = rs.getString("IDNhanVien");
                String HoNhanVien = rs.getString("HoNhanVien");
                String TenNhanVien = rs.getString("TenNhanVien");
                String LoaiNhanVien = rs.getString("LoaiNhanVien");
                String SDT = rs.getString("SDT");
                String Gmail = rs.getString("Gmail");
                String GioiTinh = rs.getString("GioiTinh");
                int TrangThai = rs.getInt("TrangThai"); // Trạng thái nhân viên đã bị xóa hay chưa
                int taiKhoan = rs.getInt("TrangThaiTaiKhoan"); // Trạng thái Tài Khoản Nhân Viên

                list.add(new NhanVienDTO(IDNhanVien, HoNhanVien, TenNhanVien, LoaiNhanVien, SDT, Gmail, GioiTinh, TrangThai, taiKhoan));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addNhanVien(NhanVienDTO nhanVien) {
        String sqlQuery = "insert into NhanVien(IDNhanVien, HoNhanVien, TenNhanVien, LoaiNhanVien, SDT, Gmail, GioiTinh, TrangThai)"
                + "values(?, ?, ?, ?, ?, ?, ?, 0)";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, nhanVien.getIDNhanVien());
            pr.setString(2, nhanVien.getHoNhanVien());
            pr.setString(3, nhanVien.getTenNhanVien());
            pr.setString(4, nhanVien.getLoaiNhanVien());
            pr.setString(5, nhanVien.getSDT());
            pr.setString(6, nhanVien.getGmail());
            pr.setString(7, nhanVien.getGioiTinh());
//            TaiKhoanNhanVienDAO taikhoan = new TaiKhoanNhanVienDAO();
//            taikhoan.createTaiKhoanNhanVien(nhanVien.getIDNhanVien());
            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateNhanVien(NhanVienDTO nhanVien) {
        String sqlQuery = "update NhanVien set HoNhanVien = ?, TenNhanVien = ?, LoaiNhanVien = ?,SDT = ?, Gmail = ?, GioiTinh = ? where IDNhanVien = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, nhanVien.getHoNhanVien());
            pr.setString(2, nhanVien.getTenNhanVien());
            pr.setString(3, nhanVien.getLoaiNhanVien());
            pr.setString(4, nhanVien.getSDT());
            pr.setString(5, nhanVien.getGmail());
            pr.setString(6, nhanVien.getGioiTinh());
            pr.setString(7, nhanVien.getIDNhanVien());

            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteNhanVien(String IDNhanVien) {
        String sqlQuery = "update NhanVien set TrangThai = 1 where IDNhanVien = ?";
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
