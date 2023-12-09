package Transylvania.database;

import Transylvania.Classes.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DBControls {

    public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static Statement st = null;
    public static ResultSet rs = null;

    public static void openConnection() {
        System.out.println("Mencoba membuat koneksi ke database...");
        conn = getConnection();
    }

    public static Connection getConnection () {
        String host = "localhost";
        String port = "3306";
        String db = "transylvania";
        String usr = "developer";
        String pwd = "secretdevpass";

        // Load driver class.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            System.out.println("Maaf, driver class tidak ditemukan");
            System.out.println(ex.getMessage());
        }

        // Connecting to data source.
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + usr + "&password=" + pwd;
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex){
            System.out.println("Maaf, koneksi tidak berhasil.");
            System.out.println(ex.getMessage());
        }

        if (conn != null) {
            System.out.println("Koneksi ke database berhasil terbentuk");
            System.out.println("CONNECTION STARTED!");
        } else {
            System.out.println("Maaf, koneksi ke database gagal terbentuk");
        }
        return conn;
    }

    public static DefaultTableModel showData() {
        DefaultTableModel tableModel = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM mahasiswa ORDER BY nim ASC");

            String[] columnNames = {"NIM", "Nama", "IPK"};

            tableModel = new DefaultTableModel(columnNames, 0);

            while (rs.next()) {
                Object[] rowData = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                };
                tableModel.addRow(rowData);
            }
            // conn.setAutoCommit(false);
            // conn.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tableModel;
    }


    public static void registerUserAccount(User userData) throws SQLException {
        try {
            ps = conn.prepareStatement("INSERT INTO users (fullname, email, password, phone_number) VALUES(?, ?, ?, ?)");
            ps.setString(1, userData.getFullName());
            ps.setString(2, userData.getEmail());
            ps.setString(3, userData.getPassword());
            ps.setString(4, userData.getPhoneNumber());
            ps.executeUpdate();
            // conn.setAutoCommit(false);
            // conn.commit();
            System.out.println("Data sudah ditambahkan!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException("NIM sudah digunakan!");
        }
    }

    public static boolean hapusData(int nim) {
        try {
            ps = conn.prepareStatement("DELETE FROM mahasiswa WHERE nim = ?");
            ps.setInt(1, nim);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // conn.setAutoCommit(false);
                // conn.commit();
                System.out.println("Data sudah dihapus!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void updateData(User userData) {
        try {
            ps = conn.prepareStatement("UPDATE mahasiswa SET nama = ?, ipk = ? WHERE nim = ?");
            ps.setString(1, userData.getFullName());
            ps.setString(2, userData.getEmail());
            ps.setString(3, userData.getPassword());
            ps.setString(4, userData.getPhoneNumber());
            ps.executeUpdate();
            // conn.setAutoCommit(false);
            // conn.commit();
            System.out.println("Data sudah diperbaiki!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static User findUserByEmail(String email) {
        User userData = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                userData = new User(
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number")
                );
            }
            // conn.setAutoCommit(false);
            // conn.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return userData;
    }

    public static void closeConnection() {
        try {
            rs.close();
            st.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException err) {
            System.out.println("CONNECTION CLOSED! THANK YOU!");
            System.exit(0);
        }
    }
}

