
package BUS;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class KhachHangBUS {
    private ArrayList<KhachHangDTO> danhSachKhachHang;
    private KhachHangDAO khachHangDAO;
    public KhachHangBUS(){
        khachHangDAO=new KhachHangDAO();
        
    }
    public void loadData(){
        danhSachKhachHang=khachHangDAO.getDataFromSQL();
    }
    public String createAutoKhachHang() {
        loadData();
        int id = danhSachKhachHang.size() + 1;

        if (id >= 100) {
            return "KH" + id;
        } else {
            return "KH0" + String.format("%02d", id);
        }
    }
    public boolean updateKhachHang(String IDKhachHang, String HoKH, String TenKH, String SDT, String Gmail, String GioiTinh, Double TongChiTieu, int TrangThai) {
        // Kiem tra input co hop le khong ?
        if (checkEmtyValue(HoKH) || checkEmtyValue(SDT) || checkEmtyValue(Gmail) || checkEmtyValue(GioiTinh)) {
            JOptionPane.showMessageDialog(null, "Khong duoc de trong bat ki thong tin nao! Vui long nhap lai!", "Thong bao", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        KhachHangDTO khachHangDTO = new KhachHangDTO(IDKhachHang, HoKH, TenKH, SDT, Gmail, GioiTinh,TongChiTieu, TrangThai);
        
        if(khachHangDAO.updateKhachHang(khachHangDTO) > 0) {
            JOptionPane.showMessageDialog(null, "UPDATE NHAN VIEN THANH CONG");
            return true;
        }
        
        return false;
    }
    public boolean checkEmtyValue(String value) {
        if (value.equals("")) {
            return true;
        }
        return false;
    }
    public boolean addKhachHang(String IDKhachHang,String HoKH,String TenKh,String SDT,String Gmail,String GioiTinh,double TongChiTieu,int TrangThai){
        KhachHangDTO khachHangDTO=new KhachHangDTO(IDKhachHang,HoKH,TenKh, SDT, Gmail, GioiTinh, TongChiTieu,TrangThai);
        if(khachHangDAO.addkhachHang(khachHangDTO)>0){
            JOptionPane.showMessageDialog(null, "THEM THANH CONG");
            return true;
        }
        return false;
    }
    public boolean deleteKhachHang(String IDKhachHang) {
    if (khachHangDAO.deleteKhachHang(IDKhachHang) > 0) {
        JOptionPane.showMessageDialog(null, "Xóa thành công");
        return true;
    }
    JOptionPane.showMessageDialog(null, "Xóa không thành công");
    return false;
}

    public String searchIDKhachHang(String IDKhachHang){
        loadData();
        for(KhachHangDTO khachHang:danhSachKhachHang){
            if(khachHang.getIDKhachHang().equals(IDKhachHang)){
                return khachHang.getIDKhachHang();
            }
        }
        return "";
    }
    public String searchHoKhachHang(String hoKhachHang){
        loadData();
        for(KhachHangDTO khachHang: danhSachKhachHang){
            if(khachHang.getHoKH().equals(hoKhachHang)){
                return khachHang.getHoKH();
            }
        }
        return "";
    }
    public String searchTenKhachHang(String tenKhachHang){
        loadData();
        for(KhachHangDTO khachHang: danhSachKhachHang){
            if(khachHang.getTenKh().equals(tenKhachHang)){
                return khachHang.getTenKh();
            }
        }
        return "";
    }
    public String searchSDT(String SDT){
        loadData();
        for(KhachHangDTO khachHang:danhSachKhachHang){
            if(khachHang.getSDT().equals(SDT)){
                return khachHang.getSDT();
            }
        }
        return "";
    }
    public String searchGmail(String Gmail){
        loadData();
        for(KhachHangDTO khachHang:danhSachKhachHang){
            if(khachHang.getGmail().equals(Gmail)){
                return khachHang.getGmail();
            }
        }
        return "";
    }
    public String searchGioiTinh(String gioiTinh){
        loadData();
        for(KhachHangDTO khachHang:danhSachKhachHang){
            if(khachHang.getGioiTinh().equals(gioiTinh)){
                return khachHang.getGioiTinh();
            }
        }
        return "";
    }
    //tim kiem khach Hang theo ma khach hang
    public KhachHangDTO searchIDKhachHang(KhachHangDTO khachHang){
        loadData();
        for(KhachHangDTO khachHangDTO:danhSachKhachHang){
            if(khachHangDTO.getIDKhachHang().equals(khachHang)){
                return khachHangDTO;
            }
        }
        return null;
        
    }
    public void rendeerTable(DefaultTableModel model){
        loadData();
        model.setRowCount(0);
        for(KhachHangDTO khachHangDTO:danhSachKhachHang){
            if(khachHangDTO.getTrangThai()== 0){
                model.addRow(new Object[]{khachHangDTO.getIDKhachHang(),khachHangDTO.getHoKH(),khachHangDTO.getTenKh(),khachHangDTO.getSDT(),khachHangDTO.getGmail(),khachHangDTO.getGioiTinh(),khachHangDTO.getTongchitieu()});
            }
        }
    }
    public void renderNhanVienTableSearch(DefaultTableModel model, String keyValue) {
        loadData();
        boolean flag = false;
        model.setRowCount(0);
        if (keyValue.equals("")) {
           JOptionPane.showMessageDialog(null, "Dữ liệu không được bỏ trống!!!", "Thông báo", JOptionPane.ERROR_MESSAGE); 
        } 
        for (KhachHangDTO khachHang : danhSachKhachHang) {
            if (khachHang.getIDKhachHang().toLowerCase().equals(keyValue)
                || khachHang.getHoKH().toLowerCase().equals(keyValue)
                || khachHang.getTenKh().toLowerCase().equals(keyValue)
                || khachHang.getSDT().toLowerCase().equals(keyValue)
                || khachHang.getGmail().toLowerCase().equals(keyValue)
                || khachHang.getGioiTinh().toLowerCase().equals(keyValue)
                || String.valueOf(khachHang.getTongchitieu()).equals(keyValue.toLowerCase())    ) {
                if (khachHang.getTrangThai() == 0) {
                    model.addRow(new Object[]{khachHang.getIDKhachHang(), khachHang.getHoKH(), khachHang.getTenKh(), khachHang.getSDT(), khachHang.getGmail(), khachHang.getGioiTinh(), khachHang.getTongchitieu()});
                    flag = true;
                }
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
