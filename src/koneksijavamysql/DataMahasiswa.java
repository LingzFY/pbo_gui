/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package koneksijavamysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author faisal
 */
public class DataMahasiswa extends javax.swing.JFrame {

    Connection Conn;
    ResultSet resultSet;
    Statement statement;

    public Boolean ada = false;
    public Boolean edit = false;

    private DefaultTableModel dftTbMahasiswa;

    /**
     * Creates new form DataMahasiswa
     */
    public DataMahasiswa() {
        initComponents();
        open_db();
        baca_data();
        aktif(false);
        setTombol(true);
        isiCombo();
    }

    private void open_db() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String username = "faisal";
            final String password = "@Lingz_fy2205";

            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/siakad", username, password);
            System.out.println("Koneksi telah berhasil");
        } catch (final SQLException | ClassNotFoundException ex) {
            System.out.println("Koneksi tidak berhasil");
        }
    }

    private void baca_data() {

        dftTbMahasiswa = new DefaultTableModel();
        dftTbMahasiswa.addColumn("Nama");
        dftTbMahasiswa.addColumn("NIM");
        dftTbMahasiswa.addColumn("Alamat");
        dftTbMahasiswa.addColumn("Kota");
        dftTbMahasiswa.addColumn("Telepon");
        dftTbMahasiswa.addColumn("Kode Prodi");

        tbMahasiswa.setModel(dftTbMahasiswa);

        try {
            statement = Conn.createStatement();
            String sql = "select * from mahasiswa";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                dftTbMahasiswa.addRow(new Object[]{
                    resultSet.getString("nama"),
                    resultSet.getString("nim"),
                    resultSet.getString("alamat"),
                    resultSet.getString("kota"),
                    resultSet.getString("telepon"),
                    resultSet.getString("kodeprodi")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

    private void isiCombo() {
        cbKodeProdi.removeAllItems();
        try {
            statement = Conn.createStatement();
            resultSet = statement.executeQuery("select distinct(kodeprodi) from mahasiswa order by kodeprodi");
            while (resultSet.next()) {
                String kodeProdi = resultSet.getString("kodeprodi");
                cbKodeProdi.addItem(kodeProdi);
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void kosong() {
        txtNama.setText("");
        txtNIM.setText("");
        txtAlamat.setText("");
        txtKota.setText("");
        txtTelepon.setText("");
        txtNamaProdi.setText("");
    }

    private void aktif(boolean x) {
        txtNama.setEditable(x);
        txtNIM.setEditable(x);
        txtAlamat.setEditable(x);
        txtKota.setEditable(x);
        txtTelepon.setEditable(x);
        cbKodeProdi.setEditable(x);
        txtNamaProdi.setEditable(x);
    }

    private void setTombol(boolean t) {
        btnTambah.setEnabled(t);
        btnSimpan.setEnabled(!t);
        btnBatal.setEnabled(!t);
        btnKeluar.setEnabled(t);
        btnUbah.setEnabled(t);
        btnHapus.setEnabled(t);
    }

    private void cariProdi() {
        Statement pStatement;
        ResultSet resultSetProdi;

        String kodeProdi;
        kodeProdi = (String) cbKodeProdi.getSelectedItem();
        try {
            String cari = "select * from prodi where kodeprodi = '" + kodeProdi + "'";
            pStatement = Conn.prepareStatement(cari);
            resultSetProdi = pStatement.executeQuery(cari);

            while (resultSetProdi.next()) {
                txtNamaProdi.setText(resultSetProdi.getString("namaprodi"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNIM = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtKota = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        cbKodeProdi = new javax.swing.JComboBox<>();
        txtNamaProdi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMahasiswa = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Master Data Mahasiswa");

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Data Mahasiswa");

        jLabel2.setText("Nama");

        txtNama.setName("txtNama"); // NOI18N

        txtAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatActionPerformed(evt);
            }
        });

        cbKodeProdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKodeProdiActionPerformed(evt);
            }
        });

        jLabel3.setText("NIM");

        jLabel4.setText("Alamat");

        jLabel5.setText("Kota");

        jLabel6.setText("Telepon");

        jLabel7.setText("Kode Prodi");

        tbMahasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "NIM", "Alamat", "Kota", "Telepon", "Kode Prodi"
            }
        ));
        tbMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMahasiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMahasiswa);

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNama)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbKodeProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNamaProdi))
                                    .addComponent(txtNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAlamat)
                                    .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTambah)
                                .addGap(18, 18, 18)
                                .addComponent(btnSimpan)
                                .addGap(18, 18, 18)
                                .addComponent(btnUbah)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapus)
                                .addGap(18, 18, 18)
                                .addComponent(btnBatal)
                                .addGap(18, 18, 18)
                                .addComponent(btnKeluar)))
                        .addGap(0, 254, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKodeProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatActionPerformed

    private void tbMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMahasiswaMouseClicked
        int row = tbMahasiswa.getSelectedRow();
        txtNama.setText(tbMahasiswa.getValueAt(row, 0).toString());
        txtNIM.setText(tbMahasiswa.getValueAt(row, 1).toString());
        txtAlamat.setText(tbMahasiswa.getValueAt(row, 2).toString());
        txtKota.setText(tbMahasiswa.getValueAt(row, 3).toString());
        txtTelepon.setText(tbMahasiswa.getValueAt(row, 4).toString());
        cbKodeProdi.setSelectedItem(tbMahasiswa.getValueAt(row, 5).toString());
    }//GEN-LAST:event_tbMahasiswaMouseClicked

    private void cbKodeProdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKodeProdiActionPerformed
        cariProdi();
    }//GEN-LAST:event_cbKodeProdiActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        setTombol(false);
        kosong();
        aktif(true);
        txtNama.requestFocus();
        edit = false;
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String sql;
        String nama = txtNama.getText();
        String nim = txtNIM.getText();
        String alamat = txtAlamat.getText();
        String kota = txtKota.getText();
        String telepon = txtTelepon.getText();
        String kodeprodi = cbKodeProdi.getSelectedItem().toString();

        if (edit == true) {
            try {
                sql = "update mahasiswa set nama = ?, alamat = ?, kota = ?, telepon = ?, kodeprodi = ? where nim = ?";
                PreparedStatement p = Conn.prepareStatement(sql);
                p.setString(1, nama);
                p.setString(2, alamat);
                p.setString(3, kota);
                p.setString(4, telepon);
                p.setString(5, kodeprodi);
                p.setString(6, nim);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(this, "Data Berhasil Diubah !");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Keterangan " + e);
            } finally {
                baca_data();
                isiCombo();
            }
        }

        if (edit == false) {
            try {
                sql = "insert into mahasiswa (nim, nama, alamat, kota, telepon, kodeprodi) values (?,?,?,?,?,?)";
                PreparedStatement p = Conn.prepareStatement(sql);

                p.setString(1, nim);
                p.setString(2, nama);
                p.setString(3, alamat);
                p.setString(4, kota);
                p.setString(5, telepon);
                p.setString(6, kodeprodi);

                p.executeUpdate();
                p.close();

                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan !");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Keterangan " + e);
            } finally {
                baca_data();
                isiCombo();
            }
        }

        setTombol(true);
        kosong();
        aktif(false);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        int i = tbMahasiswa.getSelectedRow();

        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Pilih Data Mahasiswa yang akan diubah");
        } else {
            edit = true;
            setTombol(false);
            aktif(true);
            txtNama.requestFocus();
            txtNIM.setEditable(false);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int i = tbMahasiswa.getSelectedRow();

        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Pilih Data Mahasiswa yang akan diubah");
        } else {
            int jawab = JOptionPane.showConfirmDialog(this, "Anda yakin akan hapus data ini?", "Hapus Data",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (jawab == JOptionPane.YES_OPTION) {
                try {
                    String nim = txtNIM.getText();
                    String sql = "delete from mahasiswa where nim = ?";
                    PreparedStatement p = Conn.prepareStatement(sql);

                    p.setString(1, nim);
                    p.executeUpdate();
                    p.close();
                    
                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus !");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Keterangan " + e);
                } finally {
                    baca_data();
                    isiCombo();
                    setTombol(true);
                    kosong();
                    aktif(false);
                }
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        setTombol(true);
        kosong();
        aktif(false);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnKeluarActionPerformed

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
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataMahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbKodeProdi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMahasiswa;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtKota;
    private javax.swing.JTextField txtNIM;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaProdi;
    private javax.swing.JTextField txtTelepon;
    // End of variables declaration//GEN-END:variables
}
