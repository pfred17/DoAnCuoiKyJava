package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import DAO.TaiKhoanNhanVienDAO;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NhanVienBUS {

    private ArrayList<NhanVienDTO> danhSachNhanVien;
    private NhanVienDAO nhanVienDAO;

    public NhanVienBUS() {
        nhanVienDAO = new NhanVienDAO();
    }
 
    public void loadData() {
        danhSachNhanVien = nhanVienDAO.getDataFromSQL();
    }

    public boolean addNhanVien(String IDNhanVien, String HoNhanVien, String TenNhanVien, String LoaiNhanVien, String SDT, String Gmail, String GioiTinh, int TrangThai) {

        // Kiem tra input co hop le khong ?
        if (checkEmtyValue(HoNhanVien) || checkEmtyValue(SDT) || checkEmtyValue(Gmail) || checkEmtyValue(GioiTinh)) {
            JOptionPane.showMessageDialog(null, "Khong duoc de trong bat ki thong tin nao! Vui long nhap lai!", "Thong bao", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        NhanVienDTO nhanVienDTO = new NhanVienDTO(createAutoIDNhanVien(), HoNhanVien, TenNhanVien, LoaiNhanVien, SDT, Gmail, GioiTinh, TrangThai, 0);

        if (nhanVienDAO.addNhanVien(nhanVienDTO) > 0) {
            JOptionPane.showMessageDialog(null, "THEM NHAN VIEN THANH CONG");
            TaiKhoanNhanVienDAO taikhoan = new TaiKhoanNhanVienDAO();
            taikhoan.createTaiKhoanNhanVien(IDNhanVien);
            return true;
        }
        return false;
    }

    public boolean deleteNhanVien(String IDNhanVien) {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên này không ? ", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (nhanVienDAO.deleteNhanVien(IDNhanVien) > 0) {
                JOptionPane.showMessageDialog(null, "XOA NHAN VIEN THANH CONG");
                return true;
            }
        }
        return false;
    }
    
    public boolean updateNhanVien(String IDNhanVien, String HoNhanVien, String TenNhanVien, String LoaiNhanVien, String SDT, String Gmail, String GioiTinh, int TrangThai) {
        // Kiem tra input co hop le khong ?
        if (checkEmtyValue(HoNhanVien) || checkEmtyValue(SDT) || checkEmtyValue(Gmail) || checkEmtyValue(GioiTinh)) {
            JOptionPane.showMessageDialog(null, "Khong duoc de trong bat ki thong tin nao! Vui long nhap lai!", "Thong bao", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        NhanVienDTO nhanVienDTO = new NhanVienDTO(IDNhanVien, HoNhanVien, TenNhanVien, LoaiNhanVien, SDT, Gmail, GioiTinh, TrangThai, 0);
        
        if(nhanVienDAO.updateNhanVien(nhanVienDTO) > 0) {
            JOptionPane.showMessageDialog(null, "UPDATE NHAN VIEN THANH CONG");
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

    public String createAutoIDNhanVien() {
        loadData();
        int id = danhSachNhanVien.size() + 1;

        if (id >= 100) {
            return "NV" + id;
        } else {
            return "NV0" + String.format("%02d", id);
        }
    }

    public boolean checkExistIDNhanVien(String IDNhanVien) { // Hàm tìm kiem ID nhan vien
        loadData();
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getIDNhanVien().equals(IDNhanVien)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistHoNhanVien(String hoNhanVien) {
        loadData();
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getHoNhanVien().equals(hoNhanVien)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistTenNhanVien(String tenNhanVien) {
        loadData();
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getTenNhanVien().equals(tenNhanVien)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistSDT(String sdt) {
        loadData();
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getSDT().equals(sdt)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistGmail(String gmail) {
        loadData();
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getGmail().equals(gmail)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistGioiTinh(String gioiTinh) {
        loadData();
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getGioiTinh().equals(gioiTinh)) {
                return true;
            }
        }
        return false;
    }
    
    public String getLoaiNhanVien(String IDNhanVien) {
        loadData();
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getIDNhanVien().equals(IDNhanVien)) {
                return nhanVien.getLoaiNhanVien();
            }
        }
        return "";
    }

//    Hàm tìm kiem 1 nhan vien theo ma nhan vien (IDNhanVien)
    public NhanVienDTO searchNhanVienByID(NhanVienDTO nhanVien) {
        loadData();
        for (NhanVienDTO nhanVienDTO : danhSachNhanVien) {
            if (nhanVienDTO.getIDNhanVien().equals(nhanVien.getIDNhanVien())) {
                return nhanVienDTO;
            }
        }
        return null;
    }

//    Hàm render du lieu vao bang cho NhanVienGUI su dung
    public void renderTable(DefaultTableModel model) {
        loadData();
        model.setRowCount(0);
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getTrangThai() == 0) {
                String taiKhoanString = "";
                if (nhanVien.getTaiKhoan() == 0) {
                    taiKhoanString = "Chưa có";
                } else if (nhanVien.getTaiKhoan() == 1){
                    taiKhoanString = "Hiệu lực";
                } else {
                    taiKhoanString = "Bị khóa";
                }
                model.addRow(new Object[]{nhanVien.getIDNhanVien(), nhanVien.getHoNhanVien(), nhanVien.getTenNhanVien(), nhanVien.getSDT(), nhanVien.getGmail(), nhanVien.getGioiTinh(), nhanVien.getLoaiNhanVien(), taiKhoanString
                });
            }
        }
    }
    
//    Hàm render dữ liệu vào bảng khi thực hiện chức năng tìm kiếm nhân viên
    public void renderNhanVienTableSearch(DefaultTableModel model, String keyValue) {
        loadData();
        boolean flag = false;
        model.setRowCount(0);
        if (keyValue.equals("")) {
           JOptionPane.showMessageDialog(null, "Dữ liệu không được bỏ trống!!!", "Thông báo", JOptionPane.ERROR_MESSAGE); 
        } 
        for (NhanVienDTO nhanVien : danhSachNhanVien) {
            if (nhanVien.getIDNhanVien().toLowerCase().equals(keyValue)
                || nhanVien.getHoNhanVien().toLowerCase().equals(keyValue)
                || nhanVien.getTenNhanVien().toLowerCase().equals(keyValue)
                || nhanVien.getSDT().toLowerCase().equals(keyValue)
                || nhanVien.getGmail().toLowerCase().equals(keyValue)
                || nhanVien.getGioiTinh().toLowerCase().equals(keyValue)   ) {
                if (nhanVien.getTrangThai() == 0) {
                    
                    model.addRow(new Object[]{nhanVien.getIDNhanVien(), nhanVien.getHoNhanVien(), nhanVien.getTenNhanVien(), nhanVien.getSDT(), nhanVien.getGmail(), nhanVien.getGioiTinh(), nhanVien.getLoaiNhanVien()});
                    flag = true;
                }
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

}
