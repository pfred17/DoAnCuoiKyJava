package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

public class NhaCungCapBUS {
    private NhaCungCapDAO nhaCungCapDAO;
    private ArrayList<NhaCungCapDTO> danhSachNhaCunCap;
    
    public NhaCungCapBUS() {
        nhaCungCapDAO = new NhaCungCapDAO();
    }
    
    public void loadData() {
        danhSachNhaCunCap = nhaCungCapDAO.getDataFromSQL();
    }
    
    public ArrayList<NhaCungCapDTO> getArrayNhaCC() {
        ArrayList<NhaCungCapDTO> danhSach = nhaCungCapDAO.getDataFromSQL();
        return danhSach;
    }
    
    public ArrayList<String> getArrayTenNCC() {
        ArrayList<String> danhSach = new ArrayList<>();
        loadData();
        for (NhaCungCapDTO ncc : danhSachNhaCunCap) {
            danhSach.add(ncc.getTenNhaCungCap());
        }
        return danhSach;
    }
    
    public String getIDNhaCungCapbyName(String name) {
        loadData();
        for (NhaCungCapDTO ncc : danhSachNhaCunCap) {
            if (ncc.getTenNhaCungCap().equals(name)) {
                return ncc.getIDNhacungcap();
            }
        }
        return "";
    }
    
    public String getTenNhaCCbyID(String ID) {
        loadData();
        for (NhaCungCapDTO ncc : danhSachNhaCunCap) {
            if (ncc.getIDNhacungcap().equals(ID)) {
                return ncc.getTenNhaCungCap();
            }
        }
        return "";
    }
}
