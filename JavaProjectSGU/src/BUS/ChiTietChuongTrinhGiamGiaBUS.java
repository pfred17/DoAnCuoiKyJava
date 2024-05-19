package BUS;

import DAO.ChiTietChuongTrinhGiamGiaDAO;
import DTO.ChiTietChuongTrinhGiamGiaDTO;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ChiTietChuongTrinhGiamGiaBUS {
    
    private ChiTietChuongTrinhGiamGiaDAO chiTietChuongTrinhGiamGiaDAO;
    private ArrayList<ChiTietChuongTrinhGiamGiaDTO> danhSachChiTietCTGG;
    
    public ChiTietChuongTrinhGiamGiaBUS() {
        chiTietChuongTrinhGiamGiaDAO = new ChiTietChuongTrinhGiamGiaDAO();
    }
    
    public void loadData() {
        danhSachChiTietCTGG = chiTietChuongTrinhGiamGiaDAO.getDataFromSQL();
    }
    
    public double phanTramGiamGia(String IDSanPham) {   
        loadData();
        for(ChiTietChuongTrinhGiamGiaDTO chiTietGG : danhSachChiTietCTGG) {
            if (chiTietGG.getIDSanPham().equals(IDSanPham)) {
                return chiTietGG.getPhanTramGiamGia();
            }
        }
        return 0;
    }
    
}
