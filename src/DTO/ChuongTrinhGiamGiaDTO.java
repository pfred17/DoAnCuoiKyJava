package DTO;

import java.util.Date;

public class ChuongTrinhGiamGiaDTO {
    private String IDGiamGia;
    private String TenCT;
    private String LoaiCT;
    private Date ThoiGianKhoidau;
    private Date ThoiGianKetthuc;
    private int TrangThai;

    public ChuongTrinhGiamGiaDTO(String IDGiamGia, String TenCT, String LoaiCT, Date ThoiGianKhoidau, Date ThoiGianKetthuc, int TrangThai) {
        this.IDGiamGia = IDGiamGia;
        this.TenCT = TenCT;
        this.LoaiCT = LoaiCT;
        this.ThoiGianKhoidau = ThoiGianKhoidau;
        this.ThoiGianKetthuc = ThoiGianKetthuc;
        this.TrangThai = TrangThai;
    }

    public String getIDGiamGia() {
        return IDGiamGia;
    }

    public void setIDGiamGia(String IDGiamGia) {
        this.IDGiamGia = IDGiamGia;
    }

    public String getTenCT() {
        return TenCT;
    }

    public void setTenCT(String TenCT) {
        this.TenCT = TenCT;
    }

    public String getLoaiCT() {
        return LoaiCT;
    }

    public void setLoaiCT(String LoaiCT) {
        this.LoaiCT = LoaiCT;
    }

    public Date getThoiGianKhoidau() {
        return ThoiGianKhoidau;
    }

    public void setThoiGianKhoidau(Date ThoiGianKhoidau) {
        this.ThoiGianKhoidau = ThoiGianKhoidau;
    }

    public Date getThoiGianKetthuc() {
        return ThoiGianKetthuc;
    }

    public void setThoiGianKetthuc(Date ThoiGianKetthuc) {
        this.ThoiGianKetthuc = ThoiGianKetthuc;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
}
