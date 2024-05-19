package BUS;

import DAO.ChiTietChuongTrinhGiamGiaDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietChuongTrinhGiamGiaDTO;
import DTO.ChuongTrinhGiamGiaDTO;
import DTO.SanPhamDTO;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class ChiTietChuongTrinhGiamGiaBUS {

    private ChuongTrinhGiamGiaBUS chuongTrinhGiamGiaBUS;
    private SanPhamBUS sanPhamBUS;
    private SanPhamDAO sanPhamDAO;
    private ChiTietChuongTrinhGiamGiaDAO chiTietChuongTrinhGiamGiaDAO;
    private ArrayList<ChiTietChuongTrinhGiamGiaDTO> danhSachChiTietCTGG;
    private ArrayList<SanPhamDTO> danhSachSanPham;

    public ChiTietChuongTrinhGiamGiaBUS() {
        chiTietChuongTrinhGiamGiaDAO = new ChiTietChuongTrinhGiamGiaDAO();
//        sanPhamBUS = new SanPhamBUS(this);
        sanPhamDAO = new SanPhamDAO();
    }

    public ChiTietChuongTrinhGiamGiaBUS(ChuongTrinhGiamGiaBUS chuongTrinhGiamGiaBUS) {
        this.chuongTrinhGiamGiaBUS = chuongTrinhGiamGiaBUS;
        chiTietChuongTrinhGiamGiaDAO = new ChiTietChuongTrinhGiamGiaDAO();
    }

    public void loadData() {
        danhSachChiTietCTGG = chiTietChuongTrinhGiamGiaDAO.getDataFromSQL();
    }

    public void loadDataSanPham() {
        danhSachSanPham = sanPhamDAO.getDataFromSQL();
    }
    
    public ArrayList<ChiTietChuongTrinhGiamGiaDTO> getDanhSachChiTietCTGG() {
        ArrayList<ChiTietChuongTrinhGiamGiaDTO> danhSachChiTietCTGG = chiTietChuongTrinhGiamGiaDAO.getDataFromSQL();
        return danhSachChiTietCTGG;
    }
    
    public ArrayList<SanPhamDTO> getDanhSachSanPham() {
        ArrayList<SanPhamDTO> danhSachSanPham = sanPhamDAO.getDataFromSQL();
        return danhSachSanPham;
    }

    public ArrayList<String> getArrayIDSanPhamFromSanPhamBUS() {
        loadDataSanPham();
        ArrayList<String> dataList = new ArrayList<>();
        for (SanPhamDTO sanPhamDTO : danhSachSanPham) {
            dataList.add(sanPhamDTO.getIDSanPham());
        }

        return dataList;
    }

    public boolean addChiTietGG(ChiTietChuongTrinhGiamGiaDTO chiTietChuongTrinhGiamGiaDTO) {
        if (chiTietChuongTrinhGiamGiaDAO.addChiTietCTGG(chiTietChuongTrinhGiamGiaDTO) > 0) {
            return true;
        }
        return false;
    }
    
    public boolean deleteChiTietGG(String IDGiamGia, String IDSanPham ) {
        if (chiTietChuongTrinhGiamGiaDAO.deleteChiTietChuongTrinhGiamGia(IDSanPham, IDGiamGia) > 0) {
            return true;
        }
        return false;
    }
    
    public boolean updateChiTietGG(ChiTietChuongTrinhGiamGiaDTO chiTietChuongTrinhGiamGiaDTO) {
        if (chiTietChuongTrinhGiamGiaDAO.updateChiTietChuongTrinhGiamGia(chiTietChuongTrinhGiamGiaDTO) > 0) {
            return true;
        }
        return false;
    }

    public double phanTramGiamGia(String IDSanPham) {
        loadData();
        for (ChiTietChuongTrinhGiamGiaDTO chiTietGG : danhSachChiTietCTGG) {
            if (chiTietGG.getIDSanPham().equals(IDSanPham)) {
                return chiTietGG.getPhanTramGiamGia();
            }
        }
        return 0;
    }

    public ChiTietChuongTrinhGiamGiaDTO getChiTietByID(String IDGiamGia) {
        loadData();
        for (ChiTietChuongTrinhGiamGiaDTO ctctgg : danhSachChiTietCTGG) {
            if (ctctgg.getIDGiamGia().equals(IDGiamGia)) {
                return ctctgg;
            }
        }
        return null;
    }
}
