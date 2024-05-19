package DTO;


public class ChiTietHoaDonDTO {
    private String IDHoaDon;
    private String IDChiTietHoaDon;
    private String IDSanPham;
    private int SoLuong;
    private double DonGia;
    private double ThanhTien;
    private double TienGiamGia;
    private String TenSanPham;
    private int TrangThaiChiTietHD;

    public ChiTietHoaDonDTO(String IDHoaDon, String IDSanPham, int SoLuong, double DonGia, double ThanhTien, double TienGiamGia) {
        this.IDHoaDon = IDHoaDon;
        this.IDSanPham = IDSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
        this.TienGiamGia = TienGiamGia;
    }
    
    public ChiTietHoaDonDTO(String IDHoaDon, int soLuong, String TenSanPham, double DonGia, double TienGiamGia, double ThanhTien) {
        this.IDHoaDon = IDHoaDon;
        this.TenSanPham = TenSanPham;
        this.SoLuong = soLuong;
        this.DonGia = DonGia;
        this.TienGiamGia = TienGiamGia;
        this.ThanhTien = ThanhTien;
    }

    public ChiTietHoaDonDTO(String IDHoaDon, String IDChiTietHoaDon, String IDSanPham, int SoLuong, double DonGia, double ThanhTien, double TienGiamGia) {
        this.IDHoaDon = IDHoaDon;
        this.IDChiTietHoaDon = IDChiTietHoaDon;
        this.IDSanPham = IDSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
        this.TienGiamGia = TienGiamGia;
    }
    
    public ChiTietHoaDonDTO(String IDHoaDon, String IDChiTietHoaDon, String IDSanPham, int SoLuong, double DonGia, double ThanhTien, double TienGiamGia, int TrangThai) {
        this.IDHoaDon = IDHoaDon;
        this.IDChiTietHoaDon = IDChiTietHoaDon;
        this.IDSanPham = IDSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
        this.TienGiamGia = TienGiamGia;
        this.TrangThaiChiTietHD = TrangThai;
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

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getIDChiTietHoaDon() {
        return IDChiTietHoaDon;
    }

    public void setIDChiTietHoaDon(String IDChiTietHoaDon) {
        this.IDChiTietHoaDon = IDChiTietHoaDon;
    }

    public int getTrangThaiChiTietHD() {
        return TrangThaiChiTietHD;
    }

    public void setTrangThaiChiTietHD(int TrangThaiChiTietHD) {
        this.TrangThaiChiTietHD = TrangThaiChiTietHD;
    }
    
        
    
    }
