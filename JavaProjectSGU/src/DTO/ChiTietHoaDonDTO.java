package DTO;

public class ChiTietHoaDonDTO {
    private String IDHoaDon;
    private String IDSanPham;
    private int SoLuong;
    private double DonGia;
    private double ThanhTien;
    private double TienGiamGia;

    public ChiTietHoaDonDTO() {
    }

    public ChiTietHoaDonDTO(String IDHoaDon, String IDSanPham, int SoLuong, double DonGia, double ThanhTien, double TienGiamGia) {
        this.IDHoaDon = IDHoaDon;
        this.IDSanPham = IDSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
        this.TienGiamGia = TienGiamGia;
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public double getTienGiamGia() {
        return TienGiamGia;
    }

    public void setTienGiamGia(double TienGiamGia) {
        this.TienGiamGia = TienGiamGia;
    }
    }
