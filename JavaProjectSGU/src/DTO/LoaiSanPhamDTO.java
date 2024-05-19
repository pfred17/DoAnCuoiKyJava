package DTO;

public class LoaiSanPhamDTO {
    private int IDLoaisp;
    private String Tensp;

    public LoaiSanPhamDTO() {
    }

    public LoaiSanPhamDTO(int IDLoaisp, String Tensp) {
        this.IDLoaisp = IDLoaisp;
        this.Tensp = Tensp;
    }

    public int getIDLoaisp() {
        return IDLoaisp;
    }

    public void setIDLoaisp(int IDLoaisp) {
        this.IDLoaisp = IDLoaisp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String Tensp) {
        this.Tensp = Tensp;
    }
    
    
}
