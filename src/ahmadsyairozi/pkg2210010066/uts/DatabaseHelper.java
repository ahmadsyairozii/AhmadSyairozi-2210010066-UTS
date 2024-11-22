/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahmadsyairozi.pkg2210010066.uts;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DatabaseHelper {
    // Metode untuk koneksi ke database SQLite
    public static Connection koneksidb() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\Documents\\NetBeansProjects\\AhmadSyairozi-2210010066-UTS\\agenda.db"); // Perbaiki URL koneksi
            //JOptionPane.showMessageDialog(null, "Terhubung");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    // Menambahkan kontak baru ke database
    public static void tambahAgenda(String hari, String tanggal, String jam, String agenda_pribadi) throws SQLException {
        String sql = "INSERT INTO agenda(hari, tanggal, jam, agenda_pribadi) VALUES(?, ?, ?, ?)";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hari);
            pstmt.setString(2, tanggal);
            pstmt.setString(3, jam);
            pstmt.setString(4, agenda_pribadi);
            pstmt.executeUpdate();
        }
    }

    // Mendapatkan semua kontak dari database
    public static List<Agenda> getAgenda() throws SQLException {
        List<Agenda> agenda = new ArrayList<>();
        String sql = "SELECT * FROM agenda";
        try (Connection conn = koneksidb(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Agenda agendaa = new Agenda(
                    rs.getInt("id"),
                    rs.getString("hari"),
                    rs.getString("tanggal"),
                    rs.getString("jam"),
                    rs.getString("agenda_pribadi")
                );
                agenda.add(agendaa);
            }
        }
        return agenda;
    }

    // Mengupdate data kontak
    public static void updateAgenda(int id, String hari, String tanggal, String jam, String agenda_pribadi) throws SQLException {
        String sql = "UPDATE agenda SET hari = ?, tanggal = ?, jam = ?, agenda_pribadi = ? WHERE id = ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hari);
            pstmt.setString(2, tanggal);
            pstmt.setString(3, jam);
            pstmt.setString(4, agenda_pribadi);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        }
    }

    // Menghapus kontak dari database
    public static void hapusAgenda(int id) throws SQLException {
        String sql = "DELETE FROM agenda WHERE id = ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Mencari kontak berdasarkan nama atau nomor telepon
    public static List<Agenda> cariAgenda(String keyword) throws SQLException {
        List<Agenda> agenda = new ArrayList<>();
        String sql = "SELECT * FROM agenda WHERE hari LIKE ? OR tanggal LIKE ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Agenda agendaa = new Agenda(
                    rs.getInt("id"),
                    rs.getString("hari"),
                    rs.getString("tanggal"),
                    rs.getString("jam"),
                    rs.getString("agenda_pribadi")
                );
                agenda.add(agendaa);
            }
        }
        return agenda;
    }
}
