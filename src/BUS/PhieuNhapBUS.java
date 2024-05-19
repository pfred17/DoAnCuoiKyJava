package BUS;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import utils.DateFormat;

public class PhieuNhapBUS {

    private DateFormat dateFormat;
    private PhieuNhapDAO phieuNhapDAO;
    private ArrayList<PhieuNhapDTO> danhSachPhieuNhap;

    public PhieuNhapBUS() {
        dateFormat = new DateFormat();
        phieuNhapDAO = new PhieuNhapDAO();
    }

    public void loadData() {
        danhSachPhieuNhap = phieuNhapDAO.getDataFromSQL();
    }

    public ArrayList<PhieuNhapDTO> getDanhSachPhieuNhap() {
        ArrayList<PhieuNhapDTO> danhSach = phieuNhapDAO.getDataFromSQL();

        return danhSach;
    }

    public boolean addPhieuNhap(PhieuNhapDTO phieuNhap) {
        if (phieuNhapDAO.addPhieuNhap(phieuNhap) > 0) {
            return true;
        }
        return false;
    }

    public String createAutoIDPhieuNhap() {
        loadData();
        int id = danhSachPhieuNhap.size() + 1;

        if (id >= 100) {
            return "PNH" + id;
        } else {
            return "PNH0" + String.format("%02d", id);
        }
    }

    public PhieuNhapDTO getPhieuNhapByID(String IDPhieuNhap) {
        loadData();
        for (PhieuNhapDTO phieuNhap : danhSachPhieuNhap) {
            if (phieuNhap.getIDPhieuNhap().equals(IDPhieuNhap)) {
                return phieuNhap;
            }
        }
        return null;
    }

    public ArrayList<PhieuNhapDTO> timKiemTheoGia(double giaBD, double giaKT) {
        loadData();
        ArrayList<PhieuNhapDTO> danhSach = new ArrayList<>();
        for (PhieuNhapDTO phieuNhap : danhSachPhieuNhap) {
            if (phieuNhap.getTongTien() >= giaBD && phieuNhap.getTongTien() <= giaKT) {
                danhSach.add(phieuNhap);
            }
        }
        return danhSach;
    }

    public ArrayList<PhieuNhapDTO> timKiemTheoNgay(Date ngayBD, Date ngayKT) throws ParseException {
        loadData();
        ArrayList<PhieuNhapDTO> danhSach = new ArrayList<>();
        for (PhieuNhapDTO phieuNhap : danhSachPhieuNhap) {
            String ngayNhapHang = dateFormat.dateToString(phieuNhap.getNgayNhapHang());
            if (dateFormat.StringToDate(ngayNhapHang).after(ngayBD) && dateFormat.StringToDate(ngayNhapHang).before(ngayKT)) {
                danhSach.add(phieuNhap);
            }
        }
        System.out.println("Tim kiem theo ngay: " + danhSach.size());
        return danhSach;
    }

    public ArrayList<PhieuNhapDTO> timKiem(double giaBD, double giaKT, Date ngayBD, Date ngayKT) throws ParseException {
        ArrayList<PhieuNhapDTO> danhSachTimKiemTheoGia = timKiemTheoGia(giaBD, giaKT);
        ArrayList<PhieuNhapDTO> danhSach = new ArrayList<>();
        for (PhieuNhapDTO phieuNhap : danhSachTimKiemTheoGia) {
            String ngayNhapHang = dateFormat.dateToString(phieuNhap.getNgayNhapHang());
            if (dateFormat.StringToDate(ngayNhapHang).after(ngayBD) && dateFormat.StringToDate(ngayNhapHang).before(ngayKT)) {
                danhSach.add(phieuNhap);
            }
        }
        return danhSach;
    }

}
