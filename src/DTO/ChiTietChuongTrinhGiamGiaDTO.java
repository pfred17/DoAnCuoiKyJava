package DTO;

public class ChiTietChuongTrinhGiamGiaDTO {

    private String IDGiamGia;
    private String IDSanPham;
    private double PhanTramGiamGia;

    public ChiTietChuongTrinhGiamGiaDTO(String IDGiamGia, String IDSanPham, double PhanTramGiamGia) {
        this.IDGiamGia = IDGiamGia;
        this.IDSanPham = IDSanPham;
        this.PhanTramGiamGia = PhanTramGiamGia;
    }

    public String getIDGiamGia() {
        return IDGiamGia;
    }

    public void setIDGiamGia(String IDGiamGia) {
        this.IDGiamGia = IDGiamGia;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public double getPhanTramGiamGia() {
        return PhanTramGiamGia;
    }

    public void setPhanTramGiamGia(double PhanTramGiamGia) {
        this.PhanTramGiamGia = PhanTramGiamGia;
    }

    

}
