package BUS;

import DAO.TaiKhoanNhanVienDAO;
import DTO.TaiKhoanNhanVienDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TaiKhoanNhanVienBUS {

    private ArrayList<TaiKhoanNhanVienDTO> listTaiKhoanNhanVien;
    private TaiKhoanNhanVienDAO taiKhoanNhanVienDAO;

    public TaiKhoanNhanVienBUS() {
        taiKhoanNhanVienDAO = new TaiKhoanNhanVienDAO();
    }

    public void loadData() {
        listTaiKhoanNhanVien = taiKhoanNhanVienDAO.getDataFromSQL();
    }

    public boolean checkExistIDNhanVien(String IdNhanVien) {
        loadData();
        for (TaiKhoanNhanVienDTO taiKhoanNhanVienDTO : listTaiKhoanNhanVien) {
            if (taiKhoanNhanVienDTO.getIDNhanVien().equals(IdNhanVien) || IdNhanVien.equals("admin")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistMatKhau(String matKhau) {
        loadData();
        for (TaiKhoanNhanVienDTO taiKhoanNhanVienDTO : listTaiKhoanNhanVien) {
            if (taiKhoanNhanVienDTO.getMatKhau().equals(matKhau)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTaiKhoanChuaDuocTao(String IDNhanVien) {
        TaiKhoanNhanVienDTO taiKhoan = getTaiKhoanNhanVienByID(IDNhanVien);
        if (taiKhoan != null) {
            if (Integer.parseInt(taiKhoan.getTrangThai()) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTaiKhoanBiKhoa(String IDNhanVien) {
        TaiKhoanNhanVienDTO taiKhoan = getTaiKhoanNhanVienByID(IDNhanVien);
        if (taiKhoan != null) {
            if (Integer.parseInt(taiKhoan.getTrangThai()) == 2) {
                return true;
            }
        }
        return false;
    }

    public TaiKhoanNhanVienDTO getTaiKhoanNhanVienByID(String IDNhanVien) {
        loadData();
        for (TaiKhoanNhanVienDTO taiKhoanNhanVienDTO : listTaiKhoanNhanVien) {
            if (taiKhoanNhanVienDTO.getIDNhanVien().equals(IDNhanVien)) {
                return taiKhoanNhanVienDTO;
            }
        }
        return null;
    }

    public boolean login(String IdNhanVien, String matKhau) {

        if (IdNhanVien.equals("") || matKhau.equals("")) {
            JOptionPane.showMessageDialog(null, "UserID hoac password khong duoc de trong !!!", "Thong bao", JOptionPane.ERROR_MESSAGE);
            return false;
        };

        if (IdNhanVien.equals("admin") && matKhau.equals("admin")) {
            JOptionPane.showMessageDialog(null, "LOGIN THANH CONG " + "userID: admin");
            return true;
        }

        if (!checkExistIDNhanVien(IdNhanVien)) {
            JOptionPane.showMessageDialog(null, "Ten dang nhap khong ton tai!!!", "Thong bao", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!checkExistMatKhau(matKhau)) {
            JOptionPane.showMessageDialog(null, "Mat khau khong chinh xac!!!", "Thong bao", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (checkTaiKhoanBiKhoa(IdNhanVien)) {
            JOptionPane.showMessageDialog(null, "Tài khoản bị khóa!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (checkTaiKhoanChuaDuocTao(IdNhanVien)) {
            JOptionPane.showMessageDialog(null, "Tài khoản của nhân viên chưa được tạo!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (checkExistIDNhanVien(IdNhanVien) && checkExistMatKhau(matKhau)) {
            JOptionPane.showMessageDialog(null, "LOGIN THANH CONG " + "userID: " + IdNhanVien);
            return true;
        }

        JOptionPane.showMessageDialog(null, "LOGIN THAT BAI");
        return false;
    }

    public boolean addTaiKhoanNhanVien(String IDNhanVien) {
        if (taiKhoanNhanVienDAO.addTaiKhoanNhanVien(IDNhanVien) > 0) {
            JOptionPane.showMessageDialog(null, "Cấp tài khoản cho nhân viên " + IDNhanVien + " thành công!!!");
            return true;
        }
        return false;
    }

    public boolean blockTaiKhoanNhanVien(String IDNhanVien) {
        if (taiKhoanNhanVienDAO.blockTaiKhoanNhanVien(IDNhanVien) > 0) {
            JOptionPane.showMessageDialog(null, "Khóa tài khoản cho nhân viên " + IDNhanVien + " thành công!!!");
            return true;
        }
        return false;
    }

}
