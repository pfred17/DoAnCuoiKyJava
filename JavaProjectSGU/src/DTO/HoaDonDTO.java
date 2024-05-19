package DTO;

import java.util.Date;

public class HoaDonDTO {
    private String IDHoaDon;
    private String IDKhachHang;
    private String IDNhanVien;
    private String IDGiamGia;
    private Date NgayLapHoaDon;
    private double TongTien;
    private double TienGiamGia;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String IDHoaDon, String IDKhachHang, String IDNhanVien, String IDGiamGia, Date NgayLapHoaDon, double TongTien, double TienGiamGia) {
        this.IDHoaDon = IDHoaDon;
        this.IDKhachHang = IDKhachHang;
        this.IDNhanVien = IDNhanVien;
        this.IDGiamGia = IDGiamGia;
        this.NgayLapHoaDon = NgayLapHoaDon;
        this.TongTien = TongTien;
        this.TienGiamGia = TienGiamGia;
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDGiamGia() {
        return IDGiamGia;
    }

    public void setIDGiamGia(String IDGiamGia) {
        this.IDGiamGia = IDGiamGia;
    }

    public Date getNgayLapHoaDon() {
        return NgayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date NgayLapHoaDon) {
        this.NgayLapHoaDon = NgayLapHoaDon;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public double getTienGiamGia() {
        return TienGiamGia;
    }

    public void setTienGiamGia(double TienGiamGia) {
        this.TienGiamGia = TienGiamGia;
    } 
}
