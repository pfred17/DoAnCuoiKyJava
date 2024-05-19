package BUS;

import BUS.ChiTietChuongTrinhGiamGiaBUS;
import DAO.ChuongTrinhGiamGiaDAO;
import DAO.ChiTietChuongTrinhGiamGiaDAO;
import DTO.ChuongTrinhGiamGiaDTO;
import DTO.ChiTietChuongTrinhGiamGiaDTO;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ChuongTrinhGiamGiaBUS {

    private ChuongTrinhGiamGiaDAO chuongTrinhGiamGiaDAO;
    private ChiTietChuongTrinhGiamGiaBUS chiTietChuongTrinhGiamGiaBUS;
    private ChiTietChuongTrinhGiamGiaDAO chiTietChuongTrinhGiamGiaDAO;
    private ArrayList<ChuongTrinhGiamGiaDTO> danhSachCTGG;

    public ChuongTrinhGiamGiaBUS() {
        chuongTrinhGiamGiaDAO = new ChuongTrinhGiamGiaDAO();
        chiTietChuongTrinhGiamGiaDAO = new ChiTietChuongTrinhGiamGiaDAO();
        chiTietChuongTrinhGiamGiaBUS = new ChiTietChuongTrinhGiamGiaBUS(this);
    }

    public ChuongTrinhGiamGiaBUS(ChiTietChuongTrinhGiamGiaBUS chiTietBUS) {
        this.chiTietChuongTrinhGiamGiaBUS = chiTietBUS;
    }

    public void loadData() {
        danhSachCTGG = chuongTrinhGiamGiaDAO.getDataFromSQL();
    }
    
    public ArrayList<ChuongTrinhGiamGiaDTO> getDanhSachCTGG() {
        ArrayList<ChuongTrinhGiamGiaDTO> danhSachCTGG = chuongTrinhGiamGiaDAO.getDataFromSQL();
        return danhSachCTGG;
    }

    public boolean addChuongTrinhGG(ChuongTrinhGiamGiaDTO chuongTrinhGiamGiaDTO) throws ParseException {

        if (chuongTrinhGiamGiaDAO.addChuongTrinhGiamGia(chuongTrinhGiamGiaDTO) > 0) {
            JOptionPane.showMessageDialog(null, "THÊM THÀNH CÔNG");
            return true;
        }

        return false;
    }

    public boolean deleteChuongTrinhGG(String IDGiamGia) {
        if (chuongTrinhGiamGiaDAO.deleteChuongTrinhGiamGia(IDGiamGia) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateChuongTrinhGG(ChuongTrinhGiamGiaDTO chuongTrinhGiamGiaDTO) throws ParseException {
        if (chuongTrinhGiamGiaDAO.updateChuongTrinhGiamGia(chuongTrinhGiamGiaDTO) > 0) {
            return true;
        }
        return false;
    }

    public String createAutoIDGiamGia() {
        loadData();
        int id = danhSachCTGG.size() + 1;

        if (id >= 100) {
            return "CGG" + id;
        } else {
            return "CGG0" + String.format("%02d", id);
        }
    }

    public ChuongTrinhGiamGiaDTO getCTGGbyID(String IDGG) {
        loadData();
        for (ChuongTrinhGiamGiaDTO chuongTrinhGiamGiaDTO : danhSachCTGG) {

            if (chuongTrinhGiamGiaDTO.getIDGiamGia().equals(IDGG)) {
                return chuongTrinhGiamGiaDTO;
            }
        }

        return null;
    }
}
