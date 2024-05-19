package utils;

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.SanPhamBUS;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class XuatHoaDon {

    private PriceFormat priceFormat;
    private DateFormat dateFormat;
    private String path;
    private String maKH;
    private String maNV;
    private String maHD;
    private String[][] danhSachChiTietHoaDon;

    private HoaDonBUS hoaDonBUS;
    private KhachHangBUS khachHangBUS;
    private SanPhamBUS sanPhamBUS;
    private ChiTietHoaDonBUS chiTietHDBUS;

    public XuatHoaDon() {
        priceFormat = new PriceFormat();
        dateFormat = new DateFormat();

        hoaDonBUS = new HoaDonBUS();
        khachHangBUS = new KhachHangBUS();
        chiTietHDBUS = new ChiTietHoaDonBUS();
        sanPhamBUS = new SanPhamBUS();
    }

    public XuatHoaDon(String path, String maKH, String maNV, String maHD, String[][] danhSachChiTietHoaDon) {
        this.path = path;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maHD = maHD;
        this.danhSachChiTietHoaDon = danhSachChiTietHoaDon;
        priceFormat = new PriceFormat();
    }

    public void xuatHoaDon(String path, String maKH, String maNV, String maHD, String TenKhachHang, String SDT, ArrayList<ChiTietHoaDonDTO> danhSachChiTietHoaDon, String tongTien, ArrayList<ChiTietHoaDonDTO> mangChiTiet) {
        String url = "D:\\IT-SGU\\HK2_Nam2\\doAn\\javaProjectSGU\\src\\asset\\hoadon";
        try {
            // Lấy thời gian
            Date currentDate = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            String ngayLapHoaDon = dateFormat.dateToString(currentDate);
            String thoiGian = timeFormat.format(currentDate);

            // Biến tạm
            double tienGiamGia = 0;
            boolean flag = false;

            // Tạo một đối tượng Document
            Document document = new Document(PageSize.A4);

            // Tạo một đối tượng PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream(url + "\\" + path + ".pdf"));

            // Mở Document để viết
            document.open();

            // Thêm nội dung vào Document
            document.add(new Paragraph("HOA DON"));
            document.add(new Paragraph("-----------------------------------------------------------------------------------"));
            document.add(new Paragraph("Ngay tao: " + ngayLapHoaDon + " " + thoiGian));
            document.add(new Paragraph("Khach hang: " + TenKhachHang));
            document.add(new Paragraph("SDT: " + SDT));
            document.add(new Paragraph("Ma hoa don: " + maHD));
            document.add(new Paragraph("Ma khach hang: " + maKH));
            document.add(new Paragraph("Ma nhan vien: " + maNV));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("-----------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));
            // Tạo bảng
            PdfPTable table = new PdfPTable(5); // 5 cột cho mỗi đối tượng ChiTietHoaDonDTO

            // Thêm tiêu đề cho bảng
            table.addCell("San pham");
            table.addCell("Don gia");
            table.addCell("So luong");
            table.addCell("Tien giam gia");
            table.addCell("Thanh tien");

            // Thêm dữ liệu từ danhSachChiTietHoaDon vào bảng
            for (ChiTietHoaDonDTO chiTietHoaDonDTO : danhSachChiTietHoaDon) {
                tienGiamGia += chiTietHoaDonDTO.getTienGiamGia();
                table.addCell(chiTietHoaDonDTO.getTenSanPham());
                table.addCell(String.valueOf(chiTietHoaDonDTO.getSoLuong()));
                table.addCell(String.valueOf(priceFormat.formatDonGia(chiTietHoaDonDTO.getDonGia())));
                table.addCell(String.valueOf(priceFormat.formatDonGia(chiTietHoaDonDTO.getTienGiamGia())));
                table.addCell(String.valueOf(priceFormat.formatDonGia(chiTietHoaDonDTO.getThanhTien())));
            }

            // Thêm bảng vào Document
            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("-----------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Tong tien: " + tongTien));

            tongTien = tongTien.replace(",", "");
            Double tongTienDouble = Double.valueOf(tongTien);
            // Tạo hóa đơn
            HoaDonDTO hoaDonDTO = new HoaDonDTO(maHD, maKH, maNV, "CGG001", currentDate, tongTienDouble, tienGiamGia, 0);
            KhachHangDTO khachHangDTO = new KhachHangDTO(maKH, TenKhachHang, SDT, tongTienDouble);

            if (!khachHangBUS.addKhachHang(khachHangDTO) || !hoaDonBUS.addHoaDon(hoaDonDTO)) {
                return;
            } else {
                for (ChiTietHoaDonDTO chiTietHoaDonDTO : mangChiTiet) {
                    ChiTietHoaDonDTO chiTiet = new ChiTietHoaDonDTO(chiTietHoaDonDTO.getIDHoaDon(), chiTietHDBUS.createAutoIDChiTietHoaDon(), chiTietHoaDonDTO.getIDSanPham(),
                            chiTietHoaDonDTO.getSoLuong(), chiTietHoaDonDTO.getDonGia(), chiTietHoaDonDTO.getThanhTien(), chiTietHoaDonDTO.getTienGiamGia()
                    );
                    if (chiTietHDBUS.addChiTietHoaDon(chiTiet)) {
                        flag = true;
                        SanPhamDTO sanPham = sanPhamBUS.getSanPhamByID(chiTietHoaDonDTO.getIDSanPham());
                        sanPham.setSoluong(sanPham.getSoluong() - chiTietHoaDonDTO.getSoLuong());
                        sanPhamBUS.capNhatSoluong(sanPham);
                    }
                }
            }

            // Đóng Document
            document.close();

            if (flag) {
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo và lưu vào file: " + url + "\\" + path + ".pdf");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }
}
