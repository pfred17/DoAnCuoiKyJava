package DTO;

public class ChiTietPhieuNhapDTO {
   
    private String IDChiTietPhieuNhap;
    private String IDPhieuNhap;
    private String IDSanPham;
    private int SoLuong;
    private double DonGia;

    public ChiTietPhieuNhapDTO(String IDPhieuNhap, String IDSanPham, int SoLuong, double DonGia) {
        this.IDPhieuNhap = IDPhieuNhap;
        this.IDSanPham = IDSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }
    
    public ChiTietPhieuNhapDTO(String IDChiTietPhieuNhap, String IDPhieuNhap, String IDSanPham, int SoLuong, double DonGia) {
        this.IDChiTietPhieuNhap = IDChiTietPhieuNhap;
        this.IDPhieuNhap = IDPhieuNhap;
        this.IDSanPham = IDSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public String getIDChiTietPhieuNhap() {
        return IDChiTietPhieuNhap;
    }

    public void setIDChiTietPhieuNhap(String IDChiTietPhieuNhap) {
        this.IDChiTietPhieuNhap = IDChiTietPhieuNhap;
    }

    public String getIDPhieuNhap() {
        return IDPhieuNhap;
    }

    public void setIDPhieuNhap(String IDPhieuNhap) {
        this.IDPhieuNhap = IDPhieuNhap;
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
    
    
    
}
