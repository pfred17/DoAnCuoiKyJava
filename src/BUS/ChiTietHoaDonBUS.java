package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;

public class ChiTietHoaDonBUS {

    private ChiTietHoaDonDAO chiTietHoaDonDAO;
    private ArrayList<ChiTietHoaDonDTO> danhSachChiTietHoaDon;
    private SanPhamBUS sanPhamBUS;

    public ChiTietHoaDonBUS() {
        chiTietHoaDonDAO = new ChiTietHoaDonDAO();
        sanPhamBUS = new SanPhamBUS();
    }

    public void loadData() {
        danhSachChiTietHoaDon = chiTietHoaDonDAO.getDataFromSQL();
    }

//    Hàm trả lấy danh danh sách hóa đơn
    public ArrayList<ChiTietHoaDonDTO> getDanhSachHD() {

        ArrayList<ChiTietHoaDonDTO> danhSachHD = chiTietHoaDonDAO.getDataFromSQL();

        return danhSachHD;
    }
//    Thêm mới 1 hóa đơn

    public boolean addChiTietHoaDon(ChiTietHoaDonDTO chiTietHoaDonDTO) {
        if (chiTietHoaDonDAO.addChiTietHoaDon(chiTietHoaDonDTO) > 0) {
            return true;
        }
        return false;
    }

//    Xóa hóa đơn
//    public boolean deleteHoaDon(String IDHoaDon) {
//        if (chiTietHoaDonDAO.dele(IDHoaDon) > 0) {
//            return true;
//        }
//        return false;
//    }
//    Sửa hóa đơn
//    public boolean updateHoaDon(HoaDonDTO hoaDonDTO) {
//        if (hoaDonDAO.updateHoaDon(hoaDonDTO) > 0) {
//            return true;
//        }
//
//        return false;
//    }
//    Tạo ID Hóa đơn tự động dựa trên độ dài của mảng hóa đơn trong database
    public String createAutoIDChiTietHoaDon() {
        loadData();
        int id = danhSachChiTietHoaDon.size() + 1;

        if (id >= 100) {
            return "CTHD" + id;
        } else {
            return "CTHD0" + String.format("%02d", id);
        }
    }
    
    public ArrayList<ChiTietHoaDonDTO> getDanhSachTimKiem(String keyValue) {
        loadData();
        ArrayList<ChiTietHoaDonDTO> danhSachTimKiem = new ArrayList<>();
        for (ChiTietHoaDonDTO chiTiet : danhSachChiTietHoaDon) {
            String TenSanPham = sanPhamBUS.getTenSanPhamByID(chiTiet.getIDSanPham());
            if (chiTiet.getIDChiTietHoaDon().toLowerCase().contains(keyValue.toLowerCase()) || 
                TenSanPham.toLowerCase().contains(keyValue.toLowerCase())
                ) {
                danhSachTimKiem.add(chiTiet);
            }
        }
        return danhSachTimKiem;
    }
}
