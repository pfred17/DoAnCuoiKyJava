package BUS;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhachHangBUS {

    private ArrayList<KhachHangDTO> danhSachKhachHang;
    private KhachHangDAO khachHangDAO;

    public KhachHangBUS() {
        khachHangDAO = new KhachHangDAO();

    }

    public void loadData() {
        danhSachKhachHang = khachHangDAO.getDataFromSQL();
    }

    public ArrayList<KhachHangDTO> getDanhSachKhachHang() {
        ArrayList<KhachHangDTO> danhSachKhachHang = khachHangDAO.getDataFromSQL();
        return danhSachKhachHang;
    }

    public String createAutoKhachHang() {
        loadData();
        int id = danhSachKhachHang.size() + 1;

        if (id >= 100) {
            return "KH" + id;
        } else {
            return "KH0" + String.format("%02d", id);
        }
    }

    public boolean addKhachHang(KhachHangDTO khachHang) {
        if (khachHangDAO.addkhachHang(khachHang) > 0) {
            return true;
        }
        return false;
    }

    public boolean deleteKhachHang(String IDKhachHang) {
        if (khachHangDAO.deleteKhachHang(IDKhachHang) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateKhachHang(KhachHangDTO khachHang) {
        // Kiem tra input co hop le khong ?
        if (checkEmtyValue(khachHang.getHoKH()) || checkEmtyValue(khachHang.getTenKh()) || checkEmtyValue(khachHang.getGmail()) || checkEmtyValue(khachHang.getGioiTinh())) {
            JOptionPane.showMessageDialog(null, "Khong duoc de trong bat ki thong tin nao! Vui long nhap lai!", "Thong bao", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (khachHangDAO.updateKhachHang(khachHang) > 0) {
            return true;
        }

        return false;
    }

    public boolean checkEmtyValue(String value) {
        if (value.equals("")) {
            return true;
        }
        return false;
    }

    public String searchIDKhachHang(String IDKhachHang) {
        loadData();
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getIDKhachHang().equals(IDKhachHang)) {
                return khachHang.getIDKhachHang();
            }
        }
        return "";
    }

    public String searchHoKhachHang(String hoKhachHang) {
        loadData();
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getHoKH().equals(hoKhachHang)) {
                return khachHang.getHoKH();
            }
        }
        return "";
    }

    public String searchTenKhachHang(String tenKhachHang) {
        loadData();
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getTenKh().equals(tenKhachHang)) {
                return khachHang.getTenKh();
            }
        }
        return "";
    }

    public String searchSDT(String SDT) {
        loadData();
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getSDT().equals(SDT)) {
                return khachHang.getSDT();
            }
        }
        return "";
    }

    public String searchGmail(String Gmail) {
        loadData();
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getGmail().equals(Gmail)) {
                return khachHang.getGmail();
            }
        }
        return "";
    }

    public String searchGioiTinh(String gioiTinh) {
        loadData();
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getGioiTinh().equals(gioiTinh)) {
                return khachHang.getGioiTinh();
            }
        }
        return "";
    }

    //tim kiem khach Hang theo ma khach hang
    public KhachHangDTO searchIDKhachHang(KhachHangDTO khachHang) {
        loadData();
        for (KhachHangDTO khachHangDTO : danhSachKhachHang) {
            if (khachHangDTO.getIDKhachHang().equals(khachHang)) {
                return khachHangDTO;
            }
        }
        return null;

    }

    public ArrayList<KhachHangDTO> getArrayKhachHangByKeyValue( String keyValue) {
        loadData();
        ArrayList<KhachHangDTO> danhSachKhachHangTiemKiem = new ArrayList<>();

        if (keyValue.equals("")) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không được bỏ trống!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getIDKhachHang().toLowerCase().equals(keyValue)
                    || khachHang.getHoKH().toLowerCase().equals(keyValue)
                    || khachHang.getTenKh().toLowerCase().equals(keyValue)
                    || khachHang.getSDT().toLowerCase().equals(keyValue)
                    || khachHang.getGmail().toLowerCase().equals(keyValue)
                    || khachHang.getGioiTinh().toLowerCase().equals(keyValue)
                    || String.valueOf(khachHang.getTongchitieu()).equals(keyValue.toLowerCase())) {
                if (khachHang.getTrangThai() == 0) {
                    danhSachKhachHangTiemKiem.add(khachHang);
                }
            }
        }
        return danhSachKhachHangTiemKiem;
    }
}
