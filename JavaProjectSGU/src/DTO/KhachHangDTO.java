package DTO;

public class KhachHangDTO {
    private String IDKhachHang;
    private String HoKH;
    private String TenKh;
    private String SDT;
    private String Gmail;
    private String GioiTinh;
    private Double Tongchitieu;
    private int TrangThai;

    public KhachHangDTO() {
    }
 
    public KhachHangDTO(String IDKhachHang, String HoKH, String TenKh, String SDT, String Gmail, String GioiTinh, Double Tongchitieu,int TrangThai) {
        this.IDKhachHang = IDKhachHang;
        this.HoKH = HoKH;
        this.TenKh = TenKh;
        this.SDT = SDT;
        this.Gmail = Gmail;
        this.GioiTinh = GioiTinh;
        this.Tongchitieu = Tongchitieu;
        this.TrangThai=TrangThai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    

    public String getHoKH() {
        return HoKH;
    }

    public void setHoKH(String HoKH) {
        this.HoKH = HoKH;
    }

    public String getTenKh() {
        return TenKh;
    }

    public void setTenKh(String TenKh) {
        this.TenKh = TenKh;
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

    public Double getTongchitieu() {
        return Tongchitieu;
    }

    public void setTongchitieu(Double Tongchitieu) {
        this.Tongchitieu = Tongchitieu;
    }
    
    
    
}
