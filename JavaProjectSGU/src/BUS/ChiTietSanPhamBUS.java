package BUS;

import DAO.ChiTietSanPhamDAO;
import DTO.ChiTietSanPhamDTO;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ChiTietSanPhamBUS {
    private ChiTietSanPhamDAO chiTietSanPhamDAO;
    private ArrayList<ChiTietSanPhamDTO> danhSachChiTietSanPham;
    
    public ChiTietSanPhamBUS() {
        chiTietSanPhamDAO = new ChiTietSanPhamDAO();
    }
    
    public void loadData() {
        chiTietSanPhamDAO.getDataFromSQL();
    }
    
    public ChiTietSanPhamDTO getChiTietSPByID(String IDSanPham) {
        loadData();
        for (ChiTietSanPhamDTO chiTietSanPham : danhSachChiTietSanPham) {
            if (chiTietSanPham.getIDSanPham().equals(IDSanPham)) {
                return chiTietSanPham;
            }
        }
        return null;
    }
    
    public void showChiTietSanPham(String IDSanPham, JLabel manHinh, JLabel cameraSau, JLabel cameraTruoc, JLabel ram, JLabel boNhoTrong, JLabel cpu, JLabel pin ) {
        ChiTietSanPhamDTO chiTiet = getChiTietSPByID(IDSanPham);
        if (chiTiet != null) {
            manHinh.setText(chiTiet.getManHinh());
            cameraSau.setText(chiTiet.getCameraSau());
            cameraTruoc.setText(chiTiet.getCameraTruoc());
            ram.setText(chiTiet.getRam());
            boNhoTrong.setText(chiTiet.getBoNhoTrong());
            cpu.setText(chiTiet.getCpu());
            pin.setText(chiTiet.getPin());
        }
    }
    
}
