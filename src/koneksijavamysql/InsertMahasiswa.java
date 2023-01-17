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
public class InsertMahasiswa {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String username = "faisal";
            final String password = "@Lingz_fy2205";

            final Connection koneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/siakad", username, password);
            System.out.println("Koneksi telah berhasil");
            
            final Statement statement = koneksi.createStatement();
            statement.executeUpdate("insert into mahasiswa "
                    + "(nim, nama, alamat, kota, telepon, kodeprodi) "
                    + "values "
                    + "('0140015', 'Mahendra', 'Jl. Pemuda No 123', 'Surabaya', '0813457383783', '03'),"
                    + "('0140016', 'Dihyan Gantari Nirbana', 'Jl. Pandanwangi I No.1', 'Kab. Bandung', '087823462532', '01'),"
                    + "('0140017', 'Dadang Koswari', 'Jl. Raya Bandung Subang', 'Kab. Subang', '087235447266', '04'),"
                    + "('0140018', 'Neli Retnawati', 'Jl. Raya Binong', 'Kab. Subang', '08128342387', '01'),"
                    + "('0140019', 'Nurman Purwana', 'Jl. Cileungsi', 'Kab. Bekasi', '081365746574', '04')"
                    + ";");
        } catch (final SQLException | ClassNotFoundException ex) {
            System.out.println("Koneksi tidak berhasil");
        }
    }
}
