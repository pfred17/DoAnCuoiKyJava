package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import utils.DateFormat;

public class HoaDonBUS {

    private HoaDonDAO hoaDonDAO;
    private ArrayList<HoaDonDTO> danhSachHoaDon;
    private DateFormat dateFormat;

    public HoaDonBUS() {
        hoaDonDAO = new HoaDonDAO();
        dateFormat = new DateFormat();
    }

    public void loadData() {
        danhSachHoaDon = hoaDonDAO.getDataFromSQL();
    }

//    Hàm trả lấy danh danh sách hóa đơn
    public ArrayList<HoaDonDTO> getDanhSachHD() {

        ArrayList<HoaDonDTO> danhSachHD = hoaDonDAO.getDataFromSQL();

        return danhSachHD;
    }
//    Thêm mới 1 hóa đơn

    public boolean addHoaDon(HoaDonDTO hoaDonDTO) {
        if (hoaDonDAO.addHoaDon(hoaDonDTO) > 0) {
            return true;
        }
        return false;
    }

//    Xóa hóa đơn
    public boolean deleteHoaDon(String IDHoaDon) {
        if (hoaDonDAO.deleteHoaDon(IDHoaDon) > 0) {
            return true;
        }
        return false;
    }
//    Sửa hóa đơn

    public boolean updateHoaDon(HoaDonDTO hoaDonDTO) {
        if (hoaDonDAO.updateHoaDon(hoaDonDTO) > 0) {
            return true;
        }

        return false;
    }
//    Tạo ID Hóa đơn tự động dựa trên độ dài của mảng hóa đơn trong database

    public String createAutoIDHoaDon() {
        loadData();
        int id = danhSachHoaDon.size() + 1;

        if (id >= 100) {
            return "HD" + id;
        } else {
            return "HD0" + String.format("%02d", id);
        }
    }
//    Hàm tìm kiếm hóa đơn theo từ khóa
    public ArrayList<HoaDonDTO> getDanhSachTiemKiemByKeyWord(String value) {
        loadData();
        
        ArrayList<HoaDonDTO> danhSach = new ArrayList<>();
        if (value.isEmpty()) {
            danhSach = this.danhSachHoaDon;
            return danhSach;
        }
        for (HoaDonDTO hoaDon : danhSachHoaDon) {
            if (hoaDon.getIDGiamGia().toLowerCase().contains(value.toLowerCase()) ||
                hoaDon.getIDHoaDon().toLowerCase().contains(value.toLowerCase()) ||
                hoaDon.getIDNhanVien().toLowerCase().contains(value.toLowerCase())    ||
                hoaDon.getIDKhachHang().toLowerCase().contains(value.toLowerCase())) {
                danhSach.add(hoaDon);
            }
        }
        return danhSach;
    }

//    Hàm tìm kiếm hóa đơn theo ngày
    public ArrayList<HoaDonDTO> getDanhSachTiemKiem(Date ngayBD, Date NgayKT, String value) throws ParseException {
        loadData();
        ArrayList<HoaDonDTO> danhSach = getDanhSachTiemKiemByKeyWord(value);
        ArrayList<HoaDonDTO> danhSachTimKiem = new ArrayList<>();
        for (HoaDonDTO hoaDon : danhSach) {
            String ngayLapHoaDon = dateFormat.dateToString(hoaDon.getNgayLapHoaDon());
            if (dateFormat.StringToDate(ngayLapHoaDon).after(ngayBD) && dateFormat.StringToDate(ngayLapHoaDon).before(NgayKT) && hoaDon.getTrangThai() == 0) {
                danhSachTimKiem.add(hoaDon);
            }
        }
        return danhSachTimKiem;
    }

}
