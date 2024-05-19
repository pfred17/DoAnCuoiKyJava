package utils;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import DTO.ChiTietPhieuNhapDTO;
import DTO.KhachHangDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class XuatPhieuNhap {

    private PriceFormat priceFormat;
    private DateFormat dateFormat;
    private String path;
    private String maKH;
    private String maNV;
    private String maHD;
    private String[][] danhSachChiTietHoaDon;

    private SanPhamBUS sanPhamBUS;
    private PhieuNhapBUS phieuNhapBUS;
    private ChiTietPhieuNhapBUS chiTietPhieuNhapBUS;

    public XuatPhieuNhap() {
        priceFormat = new PriceFormat();
        dateFormat = new DateFormat();

        chiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
        phieuNhapBUS = new PhieuNhapBUS();
        sanPhamBUS = new SanPhamBUS();
    }

    public XuatPhieuNhap(String path, String maKH, String maNV, String maHD, String[][] danhSachChiTietHoaDon) {
        this.path = path;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maHD = maHD;
        this.danhSachChiTietHoaDon = danhSachChiTietHoaDon;
        priceFormat = new PriceFormat();
    }

    public void xuatPhieuNhap(String path, String IDPhieuNhap, String IDNhanVien, String IDNhaCungCap, String TenNhaCungCap, ArrayList<SanPhamDTO> danhSachSanPham, String tongTien, ArrayList<ChiTietPhieuNhapDTO> mangChiTiet) {
        String url = "D:\\IT-SGU\\HK2_Nam2\\doAn\\javaProjectSGU\\src\\asset\\phieunhap";
        try {
            // Lấy thời gian
            Date currentDate = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            String ngayLapPhieuNhap = dateFormat.dateToString(currentDate);
            String thoiGian = timeFormat.format(currentDate);

            // Biến tạm
            double tongTienNhap = 0;
            boolean flag = false;

            // Tạo một đối tượng Document
            Document document = new Document(PageSize.A4);

            // Tạo một đối tượng PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream(url + "\\" + path + ".pdf"));

            // Mở Document để viết
            document.open();

            // Thêm nội dung vào Document
            document.add(new Paragraph("PHIEU NHAP"));
            document.add(new Paragraph("-----------------------------------------------------------------------------------"));
            document.add(new Paragraph("Ngay tao: " + ngayLapPhieuNhap + " " + thoiGian));
            document.add(new Paragraph("Ma phieu nhap: " + IDPhieuNhap));
            document.add(new Paragraph("Nha cung cap: " + IDNhaCungCap + " - " + TenNhaCungCap));
            document.add(new Paragraph("Ma nhan vien: " + IDNhanVien));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("-----------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));
            // Tạo bảng
            PdfPTable table = new PdfPTable(3); // 3 cột cho mỗi đối tượng ChiTietPhieuNhapDTO

            // Thêm tiêu đề cho bảng
            table.addCell("San pham");
            table.addCell("So luong");
            table.addCell("Don gia");

            // Thêm dữ liệu từ danhSachChiTietHoaDon vào bảng
            for (SanPhamDTO sanPham : danhSachSanPham) {
                table.addCell(sanPham.getTenSanPham());
                table.addCell(String.valueOf(sanPham.getSoluong()));
                table.addCell(String.valueOf(priceFormat.formatDonGia(sanPham.getDonGia())));
            }

            // Thêm bảng vào Document
            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("-----------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Tong tien nhap hang: " + tongTien));

            tongTien = tongTien.replace(",", "");
            Double tongTienDouble = Double.parseDouble(tongTien);

            // Tạo phiếu nhập
            PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO(IDPhieuNhap, IDNhanVien, IDNhaCungCap, currentDate, tongTienDouble, 0);

            if (phieuNhapBUS.addPhieuNhap(phieuNhapDTO)) {
                for (ChiTietPhieuNhapDTO chiTiet : mangChiTiet) {
                    ChiTietPhieuNhapDTO chiTietPhieuNhapDTO = new ChiTietPhieuNhapDTO(chiTietPhieuNhapBUS.createAutoIDChiTietPhieuNhap(), IDPhieuNhap, chiTiet.getIDSanPham(),
                            chiTiet.getSoLuong(), chiTiet.getDonGia()
                    );
                    if (chiTietPhieuNhapBUS.addChiTietPhieuNhap(chiTietPhieuNhapDTO)) {
                        flag = true;
                        SanPhamDTO sanPham = sanPhamBUS.getSanPhamByID(chiTietPhieuNhapDTO.getIDSanPham());
                        sanPham.setSoluong(sanPham.getSoluong() + chiTietPhieuNhapDTO.getSoLuong());
                        sanPhamBUS.capNhatSoluong(sanPham);
                    }
                }
            }

            // Đóng Document
            document.close();

            if (flag) {
                JOptionPane.showMessageDialog(null, "Phiếu nhập đã được tạo và lưu vào file: " + url + "\\" + path + ".pdf");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }
}
