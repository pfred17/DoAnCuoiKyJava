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
    
    public int addChiTietSanPham(ChiTietSanPhamDTO chiTietSanPhamDTO) {
        String sqlQuery = "insert into ChiTietSanPham(IDChiTietSanPham, IDSanPham, ManHinh, CameraSau, CameraTruoc, Ram, BoNhoTrong, Cpu, NgayRaMat, pin) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, chiTietSanPhamDTO.getIDChiTietSanPham());
            pr.setString(2, chiTietSanPhamDTO.getIDSanPham());
            pr.setString(3, chiTietSanPhamDTO.getManHinh());
            pr.setString(4, chiTietSanPhamDTO.getCameraSau());
            pr.setString(5, chiTietSanPhamDTO.getCameraTruoc());
            pr.setString(6, chiTietSanPhamDTO.getRam());
            pr.setString(7, chiTietSanPhamDTO.getBoNhoTrong());
            pr.setString(8, chiTietSanPhamDTO.getCpu());
            pr.setString(9, chiTietSanPhamDTO.getRam());
            pr.setDate(10, new java.sql.Date(chiTietSanPhamDTO.getNgayRaMat().getTime()));
            
            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateChiTietSanPham(ChiTietSanPhamDTO chiTietSanPhamDTO) {
         String sqlQuery = "update ChiTietSanPham set ManHinh = ?, CameraSau = ?, CameraTruoc = ?, Ram = ?, BoNhoTrong = ?, Cpu = ?, NgayRaMat = ?, pin = ?  where IDChiTietSanPham = ? and IDSanPham = ?";

        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlQuery);

            pr.setString(1, chiTietSanPhamDTO.getManHinh());
            pr.setString(2, chiTietSanPhamDTO.getCameraSau());
            pr.setString(3, chiTietSanPhamDTO.getCameraTruoc());
            pr.setString(4, chiTietSanPhamDTO.getRam());
            pr.setString(5, chiTietSanPhamDTO.getBoNhoTrong());
            pr.setString(6, chiTietSanPhamDTO.getCpu());
            pr.setDate(7, new java.sql.Date(chiTietSanPhamDTO.getNgayRaMat().getTime()));
            pr.setString(8, chiTietSanPhamDTO.getPin());
            pr.setString(9, chiTietSanPhamDTO.getIDChiTietSanPham());
            pr.setString(10, chiTietSanPhamDTO.getIDSanPham());
            
            return pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
