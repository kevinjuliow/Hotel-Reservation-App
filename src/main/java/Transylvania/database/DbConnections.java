package Transylvania.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbConnections {
    public static Connection getConnection (){
        String host = "localhost";
        String port = "3306";
        String db = "test";
        String user = "";
        String pwd = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ex){
            System.out.println("Maaf , driver class tidak ditemukan");
            System.out.println(ex.getMessage());
        }

        Connection conn = null ;
        try{
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pwd;
            conn = DriverManager.getConnection(url);
        }catch (SQLException ex){
            System.out.println("Maaf koneksi tidak berhasil.");
        }

        if (conn != null){
            System.out.println("Koneksi ke database berhasil");
        }else {
            System.out.println("Maaf koneksi ke database gagal");
        }
        return conn;
    }

    public static void showData (){
        Connection conn = null  ;
        Statement st = null ;
        ResultSet rs = null ;

        conn = getConnection();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from mahasiswa");
            System.out.println("Nim\t\t\tNama\t\t\tIPK");
            System.out.println("===========================================================");
            while(rs.next()){
                System.out.println(rs.getString(1)+" " +"\t" +rs.getString(2)
                        +" \t" +rs.getString(3)) ;
            }
            conn.setAutoCommit(false);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException err){
                System.out.println(err.getMessage());
            }
        }
    }

    public static void inputData (){
        Connection conn = null ;
        PreparedStatement ps = null ;

        conn = getConnection();

        Scanner scan = new Scanner(System.in);
        System.out.println("INPUT DATA");
        System.out.print("Masukkan NIM : ");
        int nim = scan.nextInt();
        System.out.print("Masukkan NAMA : ");
        String nama = scan.next();
        System.out.print("Masukkan IPK : ");
        float ipk = scan.nextFloat();

        try {
            ps = conn.prepareStatement("insert into mahasiswa values (? , ? , ?)");
            ps.setInt(1 , nim);
            ps.setString(2, nama);
            ps.setFloat(3 , ipk);
            ps.executeUpdate();
            conn.setAutoCommit(false);
            System.out.println("Data sudah ditambahkan");
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        finally {
            try{
                ps.close();
                conn.close();
            }catch (SQLException err){
                System.out.println(err.getMessage());
            }
        }
    }
    public static void inputDataGui (int nim , String nama , float ipk){
        Connection conn = null ;
        PreparedStatement ps = null ;

        conn = getConnection();
        try {
            ps = conn.prepareStatement("insert into mahasiswa values (? , ? , ?)");
            ps.setInt(1 , nim);
            ps.setString(2, nama);
            ps.setFloat(3 , ipk);
            ps.executeUpdate();
            conn.setAutoCommit(false);
            System.out.println("Data sudah ditambahkan");
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        finally {
            try{
                ps.close();
                conn.close();
            }catch (SQLException err){
                System.out.println(err.getMessage());
            }
        }
    }
    public static void hapusData(){
        Connection conn = null ;
        PreparedStatement ps = null ;

        conn = getConnection() ;

        Scanner scan = new Scanner(System.in);
        System.out.println("DELETE DATA");
        System.out.print("Masukkan NIM yang akan dihapus : ");
        int nim = scan.nextInt();

        try{
            ps = conn.prepareStatement("delete from mahasiswa where nim = ?");
            ps.setInt(1, nim);
            ps.executeUpdate();
            conn.setAutoCommit(false);
            System.out.println("Data sudah dihapus");
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }finally {
            try{
                ps.close();
                conn.close();
            }catch (SQLException err){
                System.out.println(err.getMessage());
            }
        }
    }

    public static void updateData(){
        Connection conn = null  ;
        PreparedStatement ps = null;

        conn = getConnection();
        Scanner scan = new Scanner(System.in);
        System.out.println("UPDATE DATA");
        System.out.print("Masukkan NIM yang akan di update");
        int nim = scan.nextInt();
        System.out.print("Masukkan koreksi NAMA : ");
        String nama = scan.next();
        System.out.print("Msaukkan koreksi IPK : ");
        float ipk = scan.nextFloat();

        try{
            ps = conn.prepareStatement("update mahasiswa set nama = ? , ipk = ? where nim = ?");
            ps.setString(1,  nama);
            ps.setDouble(2 , ipk);
            ps.setInt(3 , nim);
            ps.executeUpdate();
            conn.setAutoCommit(false);
            System.out.println("Data sudah diperbaiki");
        }catch (SQLException err){
            System.out.println(err.getMessage());
        } finally {
            try{
                ps.close();
                conn.close();
            }catch (SQLException err ){
                System.out.println(err.getMessage());
            }
        }

    }
    public static void updateDataGui(int nim , String nama , float ipk){
        Connection conn = null  ;
        PreparedStatement ps = null;

        conn = getConnection();
        try{
            ps = conn.prepareStatement("update mahasiswa set nama = ? , ipk = ? where nim = ?");
            ps.setString(1,  nama);
            ps.setDouble(2 , ipk);
            ps.setInt(3 , nim);
            ps.executeUpdate();
            conn.setAutoCommit(false);
            System.out.println("Data sudah diperbaiki");
        }catch (SQLException err){
            System.out.println(err.getMessage());
        } finally {
            try{
                ps.close();
                conn.close();
            }catch (SQLException err ){
                System.out.println(err.getMessage());
            }
        }

    }

}
