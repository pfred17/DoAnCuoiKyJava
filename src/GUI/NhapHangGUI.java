package GUI;

import BUS.NhaCungCapBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.DateFormat;
import utils.PriceFormat;
import utils.XuatPhieuNhap;

public class NhapHangGUI extends javax.swing.JFrame {

    private String IDNhanVien;
    private PriceFormat priceFormat;
    private DateFormat dateFormat;
    private SanPhamBUS sanPhamBUS;
    private PhieuNhapBUS phieuNhapBUS;
    private NhaCungCapBUS nhaCungCapBUS;
    private DefaultTableModel modelKho;
    private DefaultTableModel modelHangChoNhap;
    private DefaultTableModel modelPhieuNhap;
    private ArrayList<SanPhamDTO> danhSachHangChoNhap;
    private XuatPhieuNhap xuatPhieuNhap;

    public NhapHangGUI() {

    }

    public NhapHangGUI(String IDNhanVien) {
        initComponents();
        setLocationRelativeTo(null);
        this.IDNhanVien = IDNhanVien;

        sanPhamBUS = new SanPhamBUS();
        phieuNhapBUS = new PhieuNhapBUS();
        nhaCungCapBUS = new NhaCungCapBUS();

        modelKho = (DefaultTableModel) tblKhoHang.getModel();
        modelHangChoNhap = (DefaultTableModel) tblHangChoNhap.getModel();
        modelPhieuNhap = (DefaultTableModel) tblPhieuNhap.getModel();
        danhSachHangChoNhap = new ArrayList<>();

        dateFormat = new DateFormat();
        priceFormat = new PriceFormat();

        tblHangChoNhap.fixTable(jScrollPane2);
        tblKhoHang.fixTable(jScrollPane1);
        tblPhieuNhap.fixTable(jScrollPane3);

        txtMaPhieuNhap.setText(phieuNhapBUS.createAutoIDPhieuNhap());
        txtMaNhanVien.setText(IDNhanVien);

        xuatPhieuNhap = new XuatPhieuNhap();

        renderTablePhieuNhap();
        renderDataTenNCC();
        renderSanPhamKhoHang();
    }

    public void renderSanPhamKhoHang() {
        ArrayList<SanPhamDTO> danhSachSanPhamKho = sanPhamBUS.getArraySanPhamKhoHang();
        modelKho.setRowCount(0);
        for (SanPhamDTO sanPham : danhSachSanPhamKho) {
            modelKho.addRow(new Object[]{sanPham.getIDSanPham(), sanPham.getTenSanPham(), sanPham.getSoluong(), priceFormat.formatDonGia(sanPham.getDonGia())});
        }
    }

    public void renderTablehangChoNhap(DefaultTableModel model) {
        model.setRowCount(0);
        if (danhSachHangChoNhap != null) {
            for (SanPhamDTO sanPham : danhSachHangChoNhap) {
                model.addRow(new Object[]{sanPham.getIDSanPham(), sanPham.getTenSanPham(), sanPham.getSoluong(), priceFormat.formatDonGia(sanPham.getDonGia())});
            }
        }
    }

    public void renderDataTenNCC() {
        ArrayList<String> danhSachTenNCC = nhaCungCapBUS.getArrayTenNCC();
        comboBoxNhaCC.setSelectedIndex(0);
        for (String tenNCC : danhSachTenNCC) {
            comboBoxNhaCC.addItem(tenNCC);
        }
    }

    public void xoaHangChoNhap(String IDSanPham) {
        for (int i = 0; i < danhSachHangChoNhap.size(); i++) {
            SanPhamDTO sanPham = danhSachHangChoNhap.get(i);
            if (sanPham.getIDSanPham().equals(IDSanPham)) {
                danhSachHangChoNhap.remove(i);
                return;
            }
        }
    }

    public void handleThemHangChoNhap(String IDSanPham, String TenSanPham, int soLuong, Double donGia) {
        for (SanPhamDTO sanPham : danhSachHangChoNhap) {
            if (sanPham.getIDSanPham().equals(IDSanPham)) {
                sanPham.setSoluong(sanPham.getSoluong() + soLuong);
                return;
            }
        }
        SanPhamDTO sanPham = new SanPhamDTO(IDSanPham, TenSanPham, soLuong, donGia);
        danhSachHangChoNhap.add(sanPham);
    }

    public void renderTongTienGioHang() {
        double tien = 0;

        for (SanPhamDTO sanPham : danhSachHangChoNhap) {
            tien += sanPham.getDonGia() * sanPham.getSoluong();
        }

        lableTongTien.setText(priceFormat.formatDonGia(tien));
    }

    public void renderTablePhieuNhap() {
        ArrayList<PhieuNhapDTO> danhSachPhieuNhap = phieuNhapBUS.getDanhSachPhieuNhap();
        modelPhieuNhap.setRowCount(0);
        for (PhieuNhapDTO phieuNhap : danhSachPhieuNhap) {
            modelPhieuNhap.addRow(new Object[]{phieuNhap.getIDPhieuNhap(), phieuNhap.getIDNhaCungCap(), phieuNhap.getIDNhanVien(), dateFormat.dateToString(phieuNhap.getNgayNhapHang()), priceFormat.formatDonGia(phieuNhap.getTongTien())});
        }
    }

    public void renderTablePhieuNhapTimKiem(ArrayList<PhieuNhapDTO> danhSachTimKiem) {
        modelPhieuNhap.setRowCount(0);
        System.out.println("Size trong hàm render: " + danhSachTimKiem.size());
        for (PhieuNhapDTO phieuNhap : danhSachTimKiem) {
            modelPhieuNhap.addRow(new Object[]{
                phieuNhap.getIDPhieuNhap(),
                phieuNhap.getIDNhaCungCap(),
                phieuNhap.getIDNhanVien(),
                dateFormat.dateToString(phieuNhap.getNgayNhapHang()),
                priceFormat.formatDonGia(phieuNhap.getTongTien())
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        dateChooser3 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        materialTabbed1 = new StorageGUI.MaterialTabbed();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhoHang = new StorageGUI.TableDarkGUI();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHangChoNhap = new StorageGUI.TableDarkGUI();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiem = new StorageGUI.TextField();
        spinnerSoLuong = new javax.swing.JSpinner();
        btnThem = new StorageGUI.Button();
        jLabel5 = new javax.swing.JLabel();
        txtMaNhanVien = new StorageGUI.TextField();
        jLabel6 = new javax.swing.JLabel();
        comboBoxNhaCC = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtMaPhieuNhap = new StorageGUI.TextField();
        btnXoa = new StorageGUI.Button();
        btnXuatExcel = new StorageGUI.Button();
        jLabel8 = new javax.swing.JLabel();
        lableTongTien = new javax.swing.JLabel();
        btnNhapHang = new StorageGUI.Button();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPhieuNhap = new StorageGUI.TableDarkGUI();
        panelRound1 = new StorageGUI.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        txtIDNCCtab = new StorageGUI.TextField();
        jLabel10 = new javax.swing.JLabel();
        txtIDPhieuNhaptab = new StorageGUI.TextField();
        jLabel11 = new javax.swing.JLabel();
        txtIDNhanVientab = new StorageGUI.TextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTongTientab = new StorageGUI.TextField();
        txtNgayNhapHangtab = new StorageGUI.TextField();
        button1 = new StorageGUI.Button();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTimKiemGiaBD = new StorageGUI.TextField();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiemGiaKT = new StorageGUI.TextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTimKiemNgayBD = new StorageGUI.TextField();
        txtTimKiemNgayKT = new StorageGUI.TextField();
        btnTimKiemTabPN = new StorageGUI.Button();
        button2 = new StorageGUI.Button();
        typeTimKiem = new javax.swing.JComboBox<>();

        dateChooser1.setTextRefernce(txtNgayNhapHangtab);

        dateChooser2.setTextRefernce(txtTimKiemNgayBD);

        dateChooser3.setTextRefernce(txtTimKiemNgayKT);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(54, 48, 98));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NHẬP HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        tblKhoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Tồn kho", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(tblKhoHang);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Kho hàng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Hàng chờ nhập");

        tblHangChoNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng nhập", "Giá nhập"
            }
        ));
        jScrollPane2.setViewportView(tblHangChoNhap);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm:");

        txtTimKiem.setRound(2);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/image/add.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setRound(2);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã phiếu nhập:");

        txtMaNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        txtMaNhanVien.setFocusable(false);
        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtMaNhanVien.setRound(0);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Nhà cung cấp:");

        comboBoxNhaCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Chọn nhà cung cấp ---" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mã nhân viên:");

        txtMaPhieuNhap.setBackground(new java.awt.Color(204, 204, 204));
        txtMaPhieuNhap.setEnabled(false);
        txtMaPhieuNhap.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtMaPhieuNhap.setRound(0);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/image/delelte.png"))); // NOI18N
        btnXoa.setText("XÓA SẢN PHẨM");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/image/export.png"))); // NOI18N
        btnXuatExcel.setText("XUẤT EXCEL");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("TỔNG TIỀN:");

        lableTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lableTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lableTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lableTongTien.setText("0");

        btnNhapHang.setBackground(new java.awt.Color(0, 204, 102));
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/image/nhaphang.png"))); // NOI18N
        btnNhapHang.setText("NHẬP HÀNG");
        btnNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhapHang.setRound(2);
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxNhaCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(lableTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXuatExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboBoxNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Nhập hàng", jPanel2);

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên", "Ngày nhập", "Tổng tiền"
            }
        ));
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPhieuNhap);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(4);
        panelRound1.setRoundBottomRight(4);
        panelRound1.setRoundTopLeft(4);
        panelRound1.setRoundTopRight(4);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mã phiếu nhập:");

        txtIDNCCtab.setBackground(new java.awt.Color(204, 204, 204));
        txtIDNCCtab.setEnabled(false);
        txtIDNCCtab.setRound(5);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Mã NCC:");

        txtIDPhieuNhaptab.setBackground(new java.awt.Color(204, 204, 204));
        txtIDPhieuNhaptab.setEnabled(false);
        txtIDPhieuNhaptab.setRound(5);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mã nhân viên:");

        txtIDNhanVientab.setBackground(new java.awt.Color(204, 204, 204));
        txtIDNhanVientab.setEnabled(false);
        txtIDNhanVientab.setRound(5);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Ngày lập:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tổng tiền:");

        txtTongTientab.setBackground(new java.awt.Color(204, 204, 204));
        txtTongTientab.setEnabled(false);
        txtTongTientab.setRound(5);

        txtNgayNhapHangtab.setBackground(new java.awt.Color(204, 204, 204));
        txtNgayNhapHangtab.setRound(5);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/image/eye.png"))); // NOI18N
        button1.setText("XEM CHI TIẾT");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDPhieuNhaptab, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDNCCtab, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDNhanVientab, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayNhapHangtab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtTongTientab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDPhieuNhaptab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayNhapHangtab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDNCCtab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTientab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDNhanVientab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setText("Tìm kiếm");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Giá từ:");

        txtTimKiemGiaBD.setBackground(new java.awt.Color(204, 204, 204));
        txtTimKiemGiaBD.setRound(5);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("đến");

        txtTimKiemGiaKT.setBackground(new java.awt.Color(204, 204, 204));
        txtTimKiemGiaKT.setRound(5);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Từ ngày:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("đến");

        txtTimKiemNgayBD.setBackground(new java.awt.Color(204, 204, 204));
        txtTimKiemNgayBD.setRound(5);

        txtTimKiemNgayKT.setBackground(new java.awt.Color(204, 204, 204));
        txtTimKiemNgayKT.setRound(5);

        btnTimKiemTabPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/image/search.png"))); // NOI18N
        btnTimKiemTabPN.setText("TÌM");
        btnTimKiemTabPN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemTabPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTabPNActionPerformed(evt);
            }
        });

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/image/reload.png"))); // NOI18N
        button2.setText("LÀM MỚI");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setRound(2);
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        typeTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Tìm kiếm theo ---", "Tìm theo giá", "Tìm theo ngày", "Ngày và Giá" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(typeTimKiem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemGiaBD, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                .addComponent(txtTimKiemNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTimKiemGiaKT, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTimKiemTabPN, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemGiaBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiemGiaKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemTabPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed1.addTab("Phiếu nhập", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int i = tblKhoHang.getSelectedRow();

        String IDSanPham = modelKho.getValueAt(i, 0).toString();
        String TenSanPham = modelKho.getValueAt(i, 1).toString();
        int soLuong = (int) spinnerSoLuong.getValue();

        String donGiaString = (String) modelKho.getValueAt(i, 3);
        donGiaString = donGiaString.replace(",", "");
        double DonGia = Double.parseDouble(donGiaString);

        handleThemHangChoNhap(IDSanPham, TenSanPham, soLuong, DonGia);
        renderTablehangChoNhap(modelHangChoNhap);
        renderTongTienGioHang();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tblHangChoNhap.getSelectedRow();
        String IDSanPham = (String) tblHangChoNhap.getValueAt(selectedRow, 0);
        xoaHangChoNhap(IDSanPham);
        renderTablehangChoNhap(modelHangChoNhap);
        renderTongTienGioHang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        int rowCount = modelHangChoNhap.getRowCount();
        
        if (comboBoxNhaCC.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm nào được nhập!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String IDPhieuNhap = txtMaPhieuNhap.getText();
        String IDNhaCC = nhaCungCapBUS.getIDNhaCungCapbyName((String) comboBoxNhaCC.getSelectedItem());
        String IDNhanVien = txtMaNhanVien.getText();
        String TenNhaCungCap = (String) comboBoxNhaCC.getSelectedItem();

        String TongTienString = lableTongTien.getText();
        TongTienString = TongTienString.replace(",", "");
        double TongTien = Double.parseDouble(TongTienString);

        ArrayList<SanPhamDTO> mangDoiTuong = new ArrayList<>();
        ArrayList<ChiTietPhieuNhapDTO> themPhieuNhap = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            String IDSanPham = (String) tblHangChoNhap.getValueAt(i, 0);
            String TenSanPham = (String) tblHangChoNhap.getValueAt(i, 1);
            int soLuong = (int) tblHangChoNhap.getValueAt(i, 2);

            String DonGiaString = (String) tblHangChoNhap.getValueAt(i, 3);
            DonGiaString = DonGiaString.replace(",", "");
            Double DonGia = Double.valueOf(DonGiaString);

            SanPhamDTO sanPham = new SanPhamDTO(IDSanPham, TenSanPham, soLuong, DonGia);
            mangDoiTuong.add(sanPham);

            ChiTietPhieuNhapDTO chiTietPhieuNhapDTO = new ChiTietPhieuNhapDTO(IDPhieuNhap, IDSanPham, soLuong, DonGia);
            themPhieuNhap.add(chiTietPhieuNhapDTO);
        }

        xuatPhieuNhap.xuatPhieuNhap(IDPhieuNhap, IDPhieuNhap, IDNhanVien, IDNhaCC, TenNhaCungCap, mangDoiTuong, TongTienString, themPhieuNhap);
        txtMaPhieuNhap.setText(phieuNhapBUS.createAutoIDPhieuNhap());
        modelHangChoNhap.setRowCount(0);
        renderTablePhieuNhap();
//        renderTablehangChoNhap(mode);
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked
        int i = tblPhieuNhap.getSelectedRow();

        txtIDPhieuNhaptab.setText(modelPhieuNhap.getValueAt(i, 0).toString());
        txtIDNCCtab.setText(modelPhieuNhap.getValueAt(i, 1).toString());
        txtIDNhanVientab.setText(modelPhieuNhap.getValueAt(i, 2).toString());
        txtNgayNhapHangtab.setText(modelPhieuNhap.getValueAt(i, 3).toString());
        txtTongTientab.setText(modelPhieuNhap.getValueAt(i, 4).toString());

//        txtIDPhieuNhaptab
    }//GEN-LAST:event_tblPhieuNhapMouseClicked

    private void btnTimKiemTabPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTabPNActionPerformed
        int type = typeTimKiem.getSelectedIndex();

        switch (type) {
            case 0:
                JOptionPane.showMessageDialog(null, "Vui lòng chọn kiểu tìm kiếm", "Thong bao", JOptionPane.ERROR_MESSAGE);
                break;
            case 1:
                if (txtTimKiemGiaBD.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá bắt đầu", "Thong bao", JOptionPane.ERROR_MESSAGE);
                } else if (txtTimKiemGiaKT.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá kết thúc", "Thong bao", JOptionPane.ERROR_MESSAGE);
                } else {
                    String giaBDString = (String) txtTimKiemGiaBD.getText();
                    giaBDString = giaBDString.replace(",", "");
                    double giaBD = Double.parseDouble(giaBDString);

                    String giaKTString = (String) txtTimKiemGiaKT.getText();
                    giaKTString = giaKTString.replace(",", "");
                    double giaKT = Double.parseDouble(giaKTString);
                    renderTablePhieuNhapTimKiem(phieuNhapBUS.timKiemTheoGia(giaBD, giaKT));
                }
                break;
            case 2:
                try {
                    Date ngayBD = dateFormat.StringToDate(txtTimKiemNgayBD.getText());
                    Date ngayKT = dateFormat.StringToDate(txtTimKiemNgayKT.getText());
                    renderTablePhieuNhapTimKiem(phieuNhapBUS.timKiemTheoNgay(ngayBD, ngayKT));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(NhapHangGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            case 3:
                if (txtTimKiemGiaBD.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá bắt đầu", "Thong bao", JOptionPane.ERROR_MESSAGE);
                } else if (txtTimKiemGiaKT.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá kết thúc", "Thong bao", JOptionPane.ERROR_MESSAGE);
                } else {
                    String giaBDString = (String) txtTimKiemGiaBD.getText();
                    giaBDString = giaBDString.replace(",", "");
                    double giaBD = Double.parseDouble(giaBDString);

                    String giaKTString = (String) txtTimKiemGiaKT.getText();
                    giaKTString = giaKTString.replace(",", "");
                    double giaKT = Double.parseDouble(giaKTString);
                    try {
                        Date ngayBD = dateFormat.StringToDate(txtTimKiemNgayBD.getText());
                        Date ngayKT = dateFormat.StringToDate(txtTimKiemNgayKT.getText());
                        renderTablePhieuNhapTimKiem(phieuNhapBUS.timKiem(giaBD, giaKT, ngayBD, ngayKT));
                    } catch (ParseException ex) {
                        Logger.getLogger(NhapHangGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tìm kiếm không hợp lệ", "Thong bao", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnTimKiemTabPNActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        txtIDPhieuNhaptab.setText("");
        txtIDNCCtab.setText("");
        txtIDNhanVientab.setText("");
        txtNgayNhapHangtab.setText("");
        txtTongTientab.setText("");
        txtTimKiemGiaBD.setText("");
        txtTimKiemGiaKT.setText("");
        txtTimKiemNgayBD.setText("");
        txtTimKiemNgayKT.setText("");
        renderTablePhieuNhap();
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        String IDPhieuNhap = txtIDPhieuNhaptab.getText();
        String IDNhaCungCap = txtIDNCCtab.getText();
        String TongTien = txtTongTientab.getText();
        this.setVisible(false);
        ChiTietPhieuNhapGUI chiTietPhieuNhapGUI = new ChiTietPhieuNhapGUI(IDPhieuNhap, IDNhanVien, IDNhaCungCap, TongTien);
        chiTietPhieuNhapGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chiTietPhieuNhapGUI.setVisible(true);

        chiTietPhieuNhapGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chiTietPhieuNhapGUI.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                // Hiển thị lại SanPhamGUI khi ChiTietSanPhamGUI đóng đi
                NhapHangGUI.this.setVisible(true);
            }
        });
    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhapHangGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private StorageGUI.Button btnNhapHang;
    private StorageGUI.Button btnThem;
    private StorageGUI.Button btnTimKiemTabPN;
    private StorageGUI.Button btnXoa;
    private StorageGUI.Button btnXuatExcel;
    private StorageGUI.Button button1;
    private StorageGUI.Button button2;
    private javax.swing.JComboBox<String> comboBoxNhaCC;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private com.raven.datechooser.DateChooser dateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lableTongTien;
    private StorageGUI.MaterialTabbed materialTabbed1;
    private StorageGUI.PanelRound panelRound1;
    private javax.swing.JSpinner spinnerSoLuong;
    private StorageGUI.TableDarkGUI tblHangChoNhap;
    private StorageGUI.TableDarkGUI tblKhoHang;
    private StorageGUI.TableDarkGUI tblPhieuNhap;
    private StorageGUI.TextField txtIDNCCtab;
    private StorageGUI.TextField txtIDNhanVientab;
    private StorageGUI.TextField txtIDPhieuNhaptab;
    private StorageGUI.TextField txtMaNhanVien;
    private StorageGUI.TextField txtMaPhieuNhap;
    private StorageGUI.TextField txtNgayNhapHangtab;
    private StorageGUI.TextField txtTimKiem;
    private StorageGUI.TextField txtTimKiemGiaBD;
    private StorageGUI.TextField txtTimKiemGiaKT;
    private StorageGUI.TextField txtTimKiemNgayBD;
    private StorageGUI.TextField txtTimKiemNgayKT;
    private StorageGUI.TextField txtTongTientab;
    private javax.swing.JComboBox<String> typeTimKiem;
    // End of variables declaration//GEN-END:variables
}
