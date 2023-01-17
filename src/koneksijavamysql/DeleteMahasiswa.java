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
public class DeleteMahasiswa {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String username = "faisal";
            final String password = "@Lingz_fy2205";

            final Connection koneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/siakad", username, password);
            System.out.println("Koneksi telah berhasil");
            
            final Statement statement = koneksi.createStatement();
            statement.executeUpdate("delete from mahasiswa"
                    + " where nim = '0140011';");
        } catch (final SQLException | ClassNotFoundException ex) {
            System.out.println("Koneksi tidak berhasil");
        }
    }
}
