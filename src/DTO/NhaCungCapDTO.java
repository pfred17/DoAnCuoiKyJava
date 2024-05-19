package DTO;

public class NhaCungCapDTO {
    
    private String IDNhacungcap;
    private String TenNhaCungCap;
    private String DiaChi;
    private int TrangThai;
    
    public NhaCungCapDTO()
    {
    }
    
    public NhaCungCapDTO(String IDNhacungcap, String TenNhaCungCap, String DiaChi, int TrangThai) {
        this.IDNhacungcap = IDNhacungcap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public String getIDNhacungcap() {
        return IDNhacungcap;
    }

    public void setIDNhacungcap(String IDNhacungcap) {
        this.IDNhacungcap = IDNhacungcap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
}

