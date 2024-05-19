package DTO;

import java.util.Date;

public class ChiTietSanPhamDTO {

    private String IDChiTietSanPham;
    private String IDSanPham;
    private String ManHinh;
    private String CameraSau;
    private String CameraTruoc;
    private String Ram;
    private String BoNhoTrong;
    private String Cpu;
    private Date NgayRaMat;
    private String pin;

    public ChiTietSanPhamDTO(String IDChiTietSanPham, String IDSanPham, String ManHinh, String CameraSau, String CameraTruoc, String Ram, String BoNhoTrong, String Cpu, Date NgayRaMat, String pin) {
        this.IDChiTietSanPham = IDChiTietSanPham;
        this.IDSanPham = IDSanPham;
        this.ManHinh = ManHinh;
        this.CameraSau = CameraSau;
        this.CameraTruoc = CameraTruoc;
        this.Ram = Ram;
        this.BoNhoTrong = BoNhoTrong;
        this.Cpu = Cpu;
        this.NgayRaMat = NgayRaMat;
        this.pin = pin;
    }

    public String getIDChiTietSanPham() {
        return IDChiTietSanPham;
    }

    public void setIDChiTietSanPham(String IDChiTietSanPham) {
        this.IDChiTietSanPham = IDChiTietSanPham;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getManHinh() {
        return ManHinh;
    }

    public void setManHinh(String ManHinh) {
        this.ManHinh = ManHinh;
    }

    public String getCameraSau() {
        return CameraSau;
    }

    public void setCameraSau(String CameraSau) {
        this.CameraSau = CameraSau;
    }

    public String getCameraTruoc() {
        return CameraTruoc;
    }

    public void setCameraTruoc(String CameraTruoc) {
        this.CameraTruoc = CameraTruoc;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String Ram) {
        this.Ram = Ram;
    }

    public String getBoNhoTrong() {
        return BoNhoTrong;
    }

    public void setBoNhoTrong(String BoNhoTrong) {
        this.BoNhoTrong = BoNhoTrong;
    }

    public String getCpu() {
        return Cpu;
    }

    public void setCpu(String Cpu) {
        this.Cpu = Cpu;
    }

    public Date getNgayRaMat() {
        return NgayRaMat;
    }

    public void setNgayRaMat(Date NgayRaMat) {
        this.NgayRaMat = NgayRaMat;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    
    
}
