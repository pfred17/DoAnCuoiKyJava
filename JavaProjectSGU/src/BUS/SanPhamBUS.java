package BUS;

import BUS.ChiTietChuongTrinhGiamGiaBUS;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class SanPhamBUS {

    private ArrayList<SanPhamDTO> danhSachSanPham;
    private ArrayList<SanPhamDTO> danhSachGioHang;
    private ArrayList<SanPhamDTO> gioHang;

    private SanPhamDAO sanPhamDAO;
    private ChiTietChuongTrinhGiamGiaBUS chiTietGGBUS;

    public SanPhamBUS() {
        sanPhamDAO = new SanPhamDAO();
        chiTietGGBUS = new ChiTietChuongTrinhGiamGiaBUS();
        danhSachGioHang = new ArrayList<>();
    }

    public void loadData() {
        danhSachSanPham = sanPhamDAO.getDataFromSQL();
    }

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

    public void renderTableSanPhamForBanHangGUI(DefaultTableModel model) {
        loadData();
        model.setRowCount(0);
        for (SanPhamDTO sanPham : danhSachSanPham) {
            model.addRow(new Object[]{sanPham.getIDSanPham(), sanPham.getTenSanPham(), sanPham.getSoluong(), formatDonGia(sanPham.getDongia())});
        }
    }

    public void handleThemGioHang(String IDSanPham, String TenSanPham, int soLuong, Double donGia, double ThanhTien) {
        for (SanPhamDTO sanPham : danhSachGioHang) {
            if (sanPham.getIDSanPham().equals(IDSanPham)) {
                sanPham.setSoLuongGioHang(sanPham.getSoLuongGioHang() + soLuong);
                sanPham.setThanhTien(sanPham.getSoLuongGioHang() * donGia - (getPhanTramGiamGiaByID(IDSanPham) * sanPham.getSoLuongGioHang() * donGia));
                return;
            }
        }
        SanPhamDTO sanPham = new SanPhamDTO(IDSanPham, TenSanPham, soLuong, donGia, ThanhTien - ( donGia * getPhanTramGiamGiaByID(IDSanPham) * soLuong));
        danhSachGioHang.add(sanPham);
    }

    public void xoaSanPhamTrongGioHang(String IDSanPham) {
        for (int i = 0; i < danhSachGioHang.size(); i++) {
            SanPhamDTO sanPham = danhSachGioHang.get(i);
            if (sanPham.getIDSanPham().equals(IDSanPham)) {
                danhSachGioHang.remove(i);
                return;
            }
        }
    }

    public void renderTableGioHangForBanHangGUI(DefaultTableModel model) {
        model.setRowCount(0);
        if (danhSachGioHang != null) {
            for (SanPhamDTO sanPham : danhSachGioHang) {
                model.addRow(new Object[]{sanPham.getIDSanPham(), sanPham.getTenSanPham(), sanPham.getSoLuongGioHang(), formatDonGia(sanPham.getDongia()), getPhanTramGiamGiaByID(sanPham.getIDSanPham()), formatDonGia(sanPham.getThanhTien())});
            }
        }
    }

    public void renderTongTien(JLabel lableTongTien) {
        double tien = 0;

        for (SanPhamDTO sanPham : danhSachGioHang) {
            tien += sanPham.getThanhTien();
        }

        System.out.println("Tong Tien = " + tien);

        lableTongTien.setText(formatDonGia(tien));
    }
}
