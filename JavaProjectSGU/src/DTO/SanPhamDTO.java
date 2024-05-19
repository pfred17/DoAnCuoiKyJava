package DTO;

public class SanPhamDTO {

    private String IDSanPham;
    private String IDLoaiSanPham;
    private String TenSanPham;
    private int Soluong;
    private int SoLuongGioHang;
    private Double DonGia;
    private int TrangThai;
    private Double ThanhTien;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String IDSanPham, String TenSanPham, int SoLuongGioHang, Double DonGia, Double ThanhTien) {
        this.IDSanPham = IDSanPham;
        this.TenSanPham = TenSanPham;
        this.SoLuongGioHang = SoLuongGioHang;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }

    public SanPhamDTO(String IDSanPham, String IDLoaiSanPham, String TenSanPham, int Soluong, Double DonGia, int TrangThai) {
        this.IDSanPham = IDSanPham;
        this.IDLoaiSanPham = IDLoaiSanPham;
        this.TenSanPham = TenSanPham;
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.TrangThai = TrangThai;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDsp) {
        this.IDSanPham = IDsp;
    }

    public String getIDLoaiSanPham() {
        return IDLoaiSanPham;
    }

    public void setIDLoaiSanPham(String IDLoaisp) {
        this.IDLoaiSanPham = IDLoaisp;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham (String Tensp) {
        this.TenSanPham = Tensp;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public Double getDongia() {
        return DonGia;
    }

    public void setDongia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public Double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public int getSoLuongGioHang() {
        return SoLuongGioHang;
    }

    public void setSoLuongGioHang(int SoLuongGioHang) {
        this.SoLuongGioHang = SoLuongGioHang;
    }
    
    

}
