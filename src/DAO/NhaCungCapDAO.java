package DAO;

import DTO.NhaCungCapDTO;
import java.sql.*;
import connectionDB.SQLServerConnection;
import java.util.ArrayList;

public class NhaCungCapDAO {
    public ArrayList<NhaCungCapDTO> getDataFromSQL() {
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();
        try {
            Connection connection = SQLServerConnection.getConnection();
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from NhaCungCap");
            
            while (rs.next()) {
                String IDNhaCungCap = rs.getString("IDNhaCungCap");
                String TenNhaCungCap = rs.getString("TenNhaCungCap");
                String DiaChi = rs.getString("DiaChi");
                int TrangThai = rs.getInt("TrangThai");
                
                list.add(new NhaCungCapDTO(IDNhaCungCap, TenNhaCungCap, DiaChi, TrangThai));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
