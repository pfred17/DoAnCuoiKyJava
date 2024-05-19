package DTO;

public class NhanVienDTO {

    private String IDNhanVien;
    private String HoNhanVien;
    private String TenNhanVien;
    private String LoaiNhanVien;
    private String SDT;
    private String Gmail;
    private String GioiTinh;
    private int TrangThai;
    private int taiKhoan;

    public NhanVienDTO() {

    }

    public NhanVienDTO(String IDNhanVien, String HoNhanVien, String TenNhanVien, String LoaiNhanVien, String SDT, String Gmail, String GioiTinh, int TrangThai, int taiKhoan) {
        this.IDNhanVien = IDNhanVien;
        this.HoNhanVien = HoNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.LoaiNhanVien = LoaiNhanVien;
        this.SDT = SDT;
        this.Gmail = Gmail;
        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
        this.taiKhoan = taiKhoan;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getHoNhanVien() {
        return HoNhanVien;
    }

    public void setHoNhanVien(String HoNhanVien) {
        this.HoNhanVien = HoNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getLoaiNhanVien() {
        return LoaiNhanVien;
    }

    public void setLoaiNhanVien(String LoaiNhanVien) {
        this.LoaiNhanVien = LoaiNhanVien;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String Gmail) {
        this.Gmail = Gmail;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int daKhoa) {
        this.TrangThai = daKhoa;
    }

    public int getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(int taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

}
