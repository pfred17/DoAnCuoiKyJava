package DAO;

import DTO.ChiTietSanPhamDTO;
import connectionDB.SQLServerConnection;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietSanPhamDAO {

    public ArrayList<ChiTietSanPhamDTO> getDataFromSQL() {

        ArrayList<ChiTietSanPhamDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from ChiTietSanPham");

            while (rs.next()) {
                String IDChiTietSanPham = rs.getString("IDChiTietSanPham");
                String IDSanPham = rs.getString("IDSanPham");
                String ManHinh = rs.getString("ManHinh");
                String CameraSau = rs.getString("CameraSau");
                String CameraTruoc = rs.getString("CameraTruoc");
                String Ram = rs.getString("Ram");
                String BoNhoTrong = rs.getString("BoNhoTrong");
                String Cpu = rs.getString("Cpu");
                Date NgayRaMat = rs.getDate("NgayRaMat");
                String pin = rs.getString("pin");

                list.add(new ChiTietSanPhamDTO(IDChiTietSanPham, IDSanPham, ManHinh, CameraSau, CameraTruoc, Ram, BoNhoTrong, Cpu, NgayRaMat, pin));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
