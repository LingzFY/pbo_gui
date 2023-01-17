/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksijavamysql;

import java.sql.*;

/**
 *
 * @author faisal
 */
public class DisplayViewMHS {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String username = "faisal";
            final String password = "@Lingz_fy2205";

            final Connection koneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/siakad", username, password);
            System.out.println("Koneksi telah berhasil");
            
            final Statement statement = koneksi.createStatement();
            final ResultSet result = statement.executeQuery("select * from v_data_mhs;");
            while (result.next()) {                
                final String nim = result.getString("nim");
                final String nama = result.getString("nama");
                final String kodeprodi = result.getString("kodeprodi");
                final String namaprodi = result.getString("namaprodi");
                
                System.out.println("NIM : " + nim);
                System.out.println("NAMA : " + nama);
                System.out.println("KODE PRODI : " + kodeprodi);
                System.out.println("NAMA PRODI : " + namaprodi);
                System.out.println("============================================");
            }
        } catch (final SQLException | ClassNotFoundException ex) {
            System.out.println("Koneksi tidak berhasil");
        }
    }
}
