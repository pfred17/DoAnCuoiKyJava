package DTO;

public class PhieuNhapHangDTO {
    private String ID_PhieuNhap;
    private String IDNhanVien;
    private String IDNhaCungCap;

    public PhieuNhapHangDTO() {
    }

    public PhieuNhapHangDTO(String ID_PhieuNhap, String IDNhanVien, String IDNhaCungCap) {
        this.ID_PhieuNhap = ID_PhieuNhap;
        this.IDNhanVien = IDNhanVien;
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public String getID_PhieuNhap() {
        return ID_PhieuNhap;
    }

    public void setID_PhieuNhap(String ID_PhieuNhap) {
        this.ID_PhieuNhap = ID_PhieuNhap;
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
    
    
}
