package BUS;

import BUS.ChiTietChuongTrinhGiamGiaBUS;
import DAO.KhoHangDAO;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SanPhamBUS {

    private ArrayList<SanPhamDTO> danhSachSanPham;
    private ArrayList<SanPhamDTO> gioHang;

    private SanPhamDAO sanPhamDAO;
    private KhoHangDAO khoHangDAO;
    private ChiTietChuongTrinhGiamGiaBUS chiTietGGBUS;

    public SanPhamBUS() {
        sanPhamDAO = new SanPhamDAO();
        khoHangDAO = new KhoHangDAO();
        chiTietGGBUS = new ChiTietChuongTrinhGiamGiaBUS();
    }
    
    public SanPhamBUS(ChiTietChuongTrinhGiamGiaBUS chiTietBUS) {
        this.chiTietGGBUS = chiTietBUS;
    }

    public void loadData() {
        danhSachSanPham = sanPhamDAO.getDataFromSQL();
    }
    
    public ArrayList<SanPhamDTO> getArraySanPham() {
        ArrayList<SanPhamDTO> danhSachSanPham = sanPhamDAO.getDataFromSQL();
        return danhSachSanPham;
    }
    
    public ArrayList<SanPhamDTO> getArraySanPhamKhoHang() {
        ArrayList<SanPhamDTO> danhSachSanPham = khoHangDAO.getDataFromSQL();
        return danhSachSanPham;
    }
    
    public boolean addSanPham(SanPhamDTO sanPham) {
        if(sanPhamDAO.addSanPham(sanPham) > 0) {
            return true;
        } 
        
        return false;
    }
    
    public boolean addSanPhamNhap(SanPhamDTO sanPham) {
        if (sanPhamDAO.addSanPhamNhap(sanPham) > 0 ) {
            return true;
        }
        return false;
    }
    
    public boolean deleteSanPham(String IDSanPham) {
        if (sanPhamDAO.deleteSanPham(IDSanPham) > 0) {
            return true;
        }
        return false;
    }
    
    public boolean updateSanPham(SanPhamDTO sanPham) {
        if (sanPhamDAO.updateSanPham(sanPham) > 0) {
            return true;
        }
        return false;
    }
    // Hàm cập nhật số lượng sản phẩm khi sản phẩm đó được bán ra
    public boolean capNhatSoluong(SanPhamDTO sanPham) {
         if (sanPhamDAO.capNhatSoLuongSanPham(sanPham) > 0) {
            return true;
        }
        return false;
    }
    
    // Hàm cập nhật số lượng sản phẩm trong kho khi nhập hàng
    public boolean capNhatSoluongKho(SanPhamDTO sanPham) {
         if (khoHangDAO.capNhatSoLuongSanPham(sanPham) > 0) {
            return true;
        }
        return false;
    }
    
    
    public String createAutoIDSanPham() {
        loadData();
        int id = danhSachSanPham.size() + 1;

        if (id >= 100) {
            return "SP" + id;
        } else {
            return "SP0" + String.format("%02d", id);
        }
    }
    
//    ArrayList<SanPhamDTO> getArraySanPhamForNhapHangGUI() {
//        ArrayList<SanPhamDTO> danhSachSanPham = new ArrayList<>();
//        SanPham
//        return danhSachSanPham;
//    } 

    public String formatDonGia(double donGia) {
        Locale locale = new Locale("en", "EN");
        String pattern = "###,###.##";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat
                .getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(donGia);
    }

    public double getPhanTramGiamGiaByID(String IDSanPham) {
        return chiTietGGBUS.phanTramGiamGia(IDSanPham) / 100;
    }
    
    public SanPhamDTO getSanPhamByID(String IDSanPham) {
        loadData();
        for (SanPhamDTO sanPham : danhSachSanPham) {
            if (sanPham.getIDSanPham().equals((IDSanPham))) {
                return sanPham;
            }
        }
        return null;
    }
    
    public double getDonGiaByIDSanPham(String IDSanPham) {
        loadData();
        for (SanPhamDTO sanPham : danhSachSanPham) {
            if (sanPham.getIDSanPham().equals((IDSanPham))) {
                return sanPham.getDonGia();
            }
        }
        return 0;
    }
    
    public String getTenSanPhamByID(String IDSanPham) {
        loadData();
        for (SanPhamDTO sanPham : danhSachSanPham) {
            if (sanPham.getIDSanPham().equals((IDSanPham))) {
                return sanPham.getTenSanPham();
            }
        }
        return "Lỗi tìm tên sản phẩm.";
    }
    
    public ArrayList<SanPhamDTO> timKiemCoBan(String tuKhoa) {
        loadData();
        if (tuKhoa.isEmpty()) {
            return this.danhSachSanPham;
        }
        ArrayList<SanPhamDTO> danhSachSanPhamTimKiem = new ArrayList<>();
        for (SanPhamDTO sanPham : danhSachSanPham) {
            if (sanPham.getIDSanPham().toLowerCase().contains(tuKhoa) || sanPham.getIDLoaiSanPham().toLowerCase().contains(tuKhoa) || sanPham.getTenSanPham().toLowerCase().contains(tuKhoa) || sanPham.getThuongHieu().toLowerCase().contains(tuKhoa)) {
                danhSachSanPhamTimKiem.add(sanPham);
            }
        }
        
        return danhSachSanPhamTimKiem;
    }
    
    public ArrayList<SanPhamDTO> timKiemNangCao(String thuongHieu, double giaBD, double giaKT, String tuKhoa) {
        boolean flag = true;
        if (giaBD == 0 && giaKT == 0) {
            giaBD = 0;
            giaKT = 100000000;
        }
        
        ArrayList<SanPhamDTO> danhSachSanPhamTimKiem = timKiemCoBan(tuKhoa);
        ArrayList<SanPhamDTO> danhSachSanPhamTiemKiemNangCao = new ArrayList<>();
        
        for (SanPhamDTO sanPham : danhSachSanPhamTimKiem) {
                if (sanPham.getDonGia() >= giaBD && sanPham.getDonGia() <= giaKT && sanPham.getThuongHieu().equals(thuongHieu) ||
                    sanPham.getDonGia() >= giaBD && sanPham.getDonGia() <= giaKT && thuongHieu.equals("Chọn") )  {
                    danhSachSanPhamTiemKiemNangCao.add(sanPham);
                    flag = false;
                }
        }
        if (flag) {
            return null;
        }
        return danhSachSanPhamTiemKiemNangCao;
    }
}
