package GUI;

import BUS.SanPhamBUS;
import DAO.SanPhamDAO;

import javax.swing.table.DefaultTableModel;

public class BanHangGUI extends javax.swing.JFrame {

    private String IDNhanVien;
    private String LoaiNhanVien;
    private SanPhamBUS sanPhamBUS;
    private SanPhamDAO sanPhamDAO;
    private DefaultTableModel modelSanPham;
    private DefaultTableModel modelGioHang;

    public BanHangGUI() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);

        sanPhamBUS = new SanPhamBUS();
        sanPhamDAO = new SanPhamDAO();

        tblSanPham.fixTable(jScrollPane1);
        tblGioHang.fixTable(jScrollPane2);
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        modelGioHang = (DefaultTableModel) tblGioHang.getModel();

        renderTableSanPham();
        renderTongTienGioHang();
    }

    public void renderTableSanPham() {
        sanPhamBUS.renderTableSanPhamForBanHangGUI(modelSanPham);
    }

    public void renderTongTienGioHang() {
        sanPhamBUS.renderTongTien(lableTongTien);
    }

    // Hàm nhận dữ liệu user khi login
    public void setUserValueFromLoginGUI(String IDNhanVien, String LoaiNhanVien) {
        this.IDNhanVien = IDNhanVien;
        this.LoaiNhanVien = LoaiNhanVien;
        lableMaNV.setText(IDNhanVien);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        jCheckBox1.setText("jCheckBox1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(54, 48, 98));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BÁN HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(555, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        btnThemGioHang.setEnabled(true);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnThemGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGioHangActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblSanPham.getSelectedRow();

        String IDSanPham = (String) tblSanPham.getValueAt(selectedRow, 0);
        String tenSanPham = (String) tblSanPham.getValueAt(selectedRow, 1);
        int soLuong = (int) spinnerSoLuong.getValue();
        System.out.println(tblSanPham.getValueAt(selectedRow, 3));
        System.out.println("SoLuong = " + soLuong);
        String giaSanPhamStr = (String) tblSanPham.getValueAt(selectedRow, 3);
        giaSanPhamStr = giaSanPhamStr.replace(",", "");
        Double donGia = Double.parseDouble(giaSanPhamStr);
        double thanhTien = donGia * soLuong;
        sanPhamBUS.handleThemGioHang(IDSanPham, tenSanPham, soLuong, donGia, thanhTien);
        sanPhamBUS.renderTableGioHangForBanHangGUI(modelGioHang);
        sanPhamBUS.renderTableSanPhamForBanHangGUI(modelSanPham);
        renderTongTienGioHang();
    }//GEN-LAST:event_btnThemGioHangActionPerformed

    private void btnXuatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatHDActionPerformed

    private void btnXoaSanPhamGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamGioHangActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblGioHang.getSelectedRow();
        String IDSanPham = (String) tblGioHang.getValueAt(selectedRow, 0);
        sanPhamBUS.xoaSanPhamTrongGioHang(IDSanPham);
        sanPhamBUS.renderTableGioHangForBanHangGUI(modelGioHang);
        renderTongTienGioHang();
    }//GEN-LAST:event_btnXoaSanPhamGioHangActionPerformed

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
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
