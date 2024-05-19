package DTO;

import java.util.Date;

public class PhieuNhapDTO {
    private String IDPhieuNhap;
    private String IDNhanVien;
    private String IDNhaCungCap;
    private Date NgayNhapHang;
    private double TongTien;
    private int TrangThai;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(String IDPhieuNhap, String IDNhanVien, String IDNhaCungCap, Date NgayNhapHang, double TongTien, int TrangThai) {
        this.IDPhieuNhap = IDPhieuNhap;
        this.IDNhanVien = IDNhanVien;
        this.IDNhaCungCap = IDNhaCungCap;
        this.NgayNhapHang = NgayNhapHang;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public String getIDPhieuNhap() {
        return IDPhieuNhap;
    }

    public void setIDPhieuNhap(String IDPhieuNhap) {
        this.IDPhieuNhap = IDPhieuNhap;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public Date getNgayNhapHang() {
        return NgayNhapHang;
    }

    public void setNgayNhapHang(Date NgayNhapHang) {
        this.NgayNhapHang = NgayNhapHang;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Object getNgayLapHoaDon() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
