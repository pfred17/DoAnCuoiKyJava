package DTO;

public class NhaCungCapDTO {
    
    private int IDNhacungcap;
    private String TenNhaCungCap;
    private String Diachi;
    private String Gmail;
    public NhaCungCapDTO()
    {
    }
    
    public NhaCungCapDTO(int IDNhacungcap, String TenNhaCungCap, String Diachi, String Gmail) {
        this.IDNhacungcap = IDNhacungcap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.Diachi = Diachi;
        this.Gmail = Gmail;
    }

    public int getIDNhacungcap() {
        return IDNhacungcap;
    }

    public void setIDNhacungcap(int IDNhacungcap) {
        this.IDNhacungcap = IDNhacungcap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String Gmail) {
        this.Gmail = Gmail;
    }
    
    
    
}

