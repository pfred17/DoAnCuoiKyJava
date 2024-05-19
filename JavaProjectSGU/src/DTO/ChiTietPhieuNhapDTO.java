/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Dell
 */
public class ChiTietPhieuNhapDTO {
    private int IDPhieuNhap;
    private int IDsp;
    private int soluong;
    private Double GiaNhap;

    public ChiTietPhieuNhapDTO() {
    }

    public ChiTietPhieuNhapDTO(int IDPhieuNhap, int IDsp, int soluong, Double GiaNhap) {
        this.IDPhieuNhap = IDPhieuNhap;
        this.IDsp = IDsp;
        this.soluong = soluong;
        this.GiaNhap = GiaNhap;
    }

    public int getIDPhieuNhap() {
        return IDPhieuNhap;
    }

    public void setIDPhieuNhap(int IDPhieuNhap) {
        this.IDPhieuNhap = IDPhieuNhap;
    }

    public int getIDsp() {
        return IDsp;
    }

    public void setIDsp(int IDsp) {
        this.IDsp = IDsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(Double GiaNhap) {
        this.GiaNhap = GiaNhap;
    }
    
    
}
