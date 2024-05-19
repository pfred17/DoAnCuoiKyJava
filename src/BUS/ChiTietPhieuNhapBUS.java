package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;

public class ChiTietPhieuNhapBUS {

    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;
    private ArrayList<ChiTietPhieuNhapDTO> danhSachChiTietPhieuNhap;
    private SanPhamBUS sanPhamBUS;
    
    public ChiTietPhieuNhapBUS() {
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    }
     
    public void loadData() {
        danhSachChiTietPhieuNhap = chiTietPhieuNhapDAO.getDataFromSQL();
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getDanhSachChiTietPN() {
        ArrayList<ChiTietPhieuNhapDTO> danhSach = chiTietPhieuNhapDAO.getDataFromSQL();
        return danhSach;
    }
    
    public boolean addChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTiet) {
        if (chiTietPhieuNhapDAO.addChiTietHoaDon(chiTiet) > 0) {
            return true;
        }
        return false;
    }
    
    public String createAutoIDChiTietPhieuNhap() {
        loadData();
        int id = danhSachChiTietPhieuNhap.size() + 1;

        if (id >= 100) {
            return "CTPH" + id;
        } else {
            return "CTPN0" + String.format("%02d", id);
        }
    }
    
}
