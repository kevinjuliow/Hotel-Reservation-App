package Transylvania.database;

import Transylvania.Classes.Hotel;
import Transylvania.Classes.Transaction;
import Transylvania.Classes.User;
import Transylvania.env;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class DBControls {

    public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static Statement st = null;
    public static ResultSet rs = null;

    public static void openConnection() {
        System.out.println("Mencoba membuat koneksi ke database...");
        conn = getConnection();
    }

    public static Connection getConnection() {
        String host = "localhost";
        String port = "3306";
        String db = "transylvania";
        String usr = "developer";
        String pwd = "secretdevpass";

        // Load driver class.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Maaf, driver class tidak ditemukan");
            System.out.println(ex.getMessage());
        }

        // Connecting to data source.
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + usr + "&password=" + pwd;
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
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

    public static void registerUserAccount(User userData) throws SQLException {
        try {
            ps = conn.prepareStatement("INSERT INTO users (fullname, email, password, phone_number, type) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, userData.getFullName());
            ps.setString(2, userData.getEmail());
            ps.setString(3, userData.getPassword());
            ps.setString(4, userData.getPhoneNumber());
            ps.setString(5, "user");
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

    public static User findUserByEmail(String email) {
        User userData = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                userData = new User(
                        rs.getInt("user_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("type")
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

    public static ArrayList findHotel(String hotelName) throws IOException {
        ArrayList<Hotel> hotel = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM hotel WHERE hotel_name LIKE ?");
            String pattern = "%" + hotelName + "%";
            ps.setString(1, pattern);
            rs = ps.executeQuery();

            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("image");
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage image = ImageIO.read(bis);
                ImageIcon imageIcon = new ImageIcon(image);

                hotel.add(new Hotel(
                        rs.getString("hotel_id"),
                        rs.getString("hotel_name"),
                        rs.getString("hotel_description"),
                        rs.getString("hotel_location"),
                        Integer.parseInt(rs.getString("total_standard")),
                        Integer.parseInt(rs.getString("total_deluxe")),
                        Integer.parseInt(rs.getString("total_suite")),
                        Integer.parseInt(rs.getString("star")),
                        imageIcon));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return hotel;
    }

    public static <Hotel> ArrayList findHotelStar(int star) throws IOException {
        ArrayList<Hotel> hotel = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM hotel WHERE star = ?");
            ps.setInt(1, star);
            rs = ps.executeQuery();

            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("image");
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage image = ImageIO.read(bis);
                ImageIcon imageIcon = new ImageIcon(image);

                hotel.add((Hotel) new Transylvania.Classes.Hotel(
                        rs.getString("hotel_id"),
                        rs.getString("hotel_name"),
                        rs.getString("hotel_description"),
                        rs.getString("hotel_location"),
                        Integer.parseInt(rs.getString("total_standard")),
                        Integer.parseInt(rs.getString("total_deluxe")),
                        Integer.parseInt(rs.getString("total_suite")),
                        Integer.parseInt(rs.getString("star")),
                        imageIcon));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return hotel;
    }

    public static List<Hotel> GETHotel() throws IOException {
        List<Hotel> hotelList = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM hotel");

            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("image");
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage image = ImageIO.read(bis);
                ImageIcon imageIcon = new ImageIcon(image);

                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getString(1));
                hotel.setHotelName(rs.getString(2));
                hotel.setDescription(rs.getString(3));
                hotel.setLocation(rs.getString(4));
                hotel.setTotalStandard(rs.getInt(5));
                hotel.setTotalDeluxe(rs.getInt(6));
                hotel.setTotalSuite(rs.getInt(7));
                hotel.setStar(rs.getInt(8));
                hotel.setImage(imageIcon);
                hotelList.add(hotel);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return hotelList;
    }

    public static int findRoomPriceByHotelId(int hotelId) {
        int price = 0;

        try {
            ps = conn.prepareStatement("SELECT price FROM room WHERE hotel_id = ?");
            ps.setInt(1, hotelId);
            rs = ps.executeQuery();

            if (rs.next()) {
                price =  rs.getInt("price");
            }
            // conn.setAutoCommit(false);
            // conn.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return price;
    }

    public static void addHotel(Hotel hotel) throws SQLException {
        try {
            int id = 0;
            ps = conn.prepareStatement("INSERT INTO hotel (hotel_name, hotel_description, hotel_location, total_standard, total_deluxe, total_suite, star, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, hotel.getHotelName());
            ps.setString(2, hotel.getDescription());
            ps.setString(3, hotel.getLocation());
            ps.setInt(4, hotel.getTotalStandard());
            ps.setInt(5, hotel.getTotalDeluxe());
            ps.setInt(6, hotel.getTotalSuite());
            ps.setInt(7, hotel.getStar());
            ps.setBytes(8, hotel.getImageData());
            ps.executeUpdate();
            // conn.setAutoCommit(false);
            // conn.commit();
            System.out.println("Data Hotel sudah ditambahkan!");

            ps = conn.prepareStatement("SELECT hotel_id FROM hotel WHERE hotel_name = ?");
            ps.setString(1, hotel.getHotelName());
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("hotel_id");
            }

            for (int i = 0; i < hotel.getTotalStandard(); i++) {
                ps = conn.prepareStatement("INSERT INTO room (room_id, room_type, price, room_available, hotel_id) VALUES(?, ?, ?, ?, ?)");
                String pattern = String.valueOf(id) + i;

                ps.setInt(1, Integer.parseInt(pattern));
                ps.setString(2, "Standard");
                ps.setInt(3, hotel.getStandardPrice());
                ps.setInt(4, 1);
                ps.setInt(5, id);
                ps.executeUpdate();
            }

            System.out.println("Data Room Standard sudah ditambahkan!");

            for (int i = hotel.getTotalStandard(); i < hotel.getTotalDeluxe() + hotel.getTotalStandard(); i++) {
                ps = conn.prepareStatement("INSERT INTO room (room_id, room_type, price, room_available, hotel_id) VALUES(?, ?, ?, ?, ?)");
                String pattern = String.valueOf(id) + i;

                ps.setInt(1, Integer.parseInt(pattern));
                ps.setString(2, "Deluxe");
                ps.setInt(3, hotel.getDeluxePrice());
                ps.setInt(4, 1);
                ps.setInt(5, id);
                ps.executeUpdate();
            }

            System.out.println("Data Room Deluxe sudah ditambahkan!");

            for (int i = hotel.getTotalStandard() + hotel.getTotalDeluxe(); i < hotel.getTotalDeluxe() + hotel.getTotalStandard() + hotel.getTotalSuite(); i++) {
                ps = conn.prepareStatement("INSERT INTO room (room_id, room_type, price, room_available, hotel_id) VALUES(?, ?, ?, ?, ?)");
                String pattern = String.valueOf(id) + i;

                ps.setInt(1, Integer.parseInt(pattern));
                ps.setString(2, "Suite");
                ps.setInt(3, hotel.getSuitePrice());
                ps.setInt(4, 1);
                ps.setInt(5, id);
                ps.executeUpdate();
            }

            System.out.println("Data Room Suite sudah ditambahkan!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException("SQL ada yang salah!!!");
        }
    }

    public static void updateDataUser(User userData) throws SQLException {
        try {
            ps = conn.prepareStatement("UPDATE users SET fullname = ?, password = ?, phone_number = ? WHERE email = ?");
            ps.setString(1, userData.getFullName());
            ps.setString(2, userData.getPassword());
            ps.setString(3, userData.getPhoneNumber());
            ps.setString(4, userData.getEmail());
            ps.executeUpdate();
            // conn.setAutoCommit(false);
            // conn.commit();
            System.out.println("Data updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException("User data update failed!");
        }
    }

    public static Hotel GetEditHotel(int user_id) throws IOException {
        Hotel hotel = new Hotel();
        try {
            ps = conn.prepareStatement("SELECT hotel_id FROM user WHERE user_id = ?");
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                hotel.setHotelId(rs.getString("hotel_id"));
            }
            
            ps = conn.prepareStatement("SELECT * FROM hotel WHERE hotel_id = ?");
            ps.setString(1, hotel.getHotelId());
            rs = ps.executeQuery();

            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("image");
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage image = ImageIO.read(bis);
                ImageIcon imageIcon = new ImageIcon(image);

//                Hotel hotel = new Hotel();
//                hotel.setHotelId(rs.getString(1));
                hotel.setHotelName(rs.getString("hotel_name"));
                hotel.setDescription(rs.getString("hotel_description"));
                hotel.setLocation(rs.getString("hotel_location"));
                hotel.setTotalStandard(rs.getInt("total_standard"));
                hotel.setTotalDeluxe(rs.getInt("total_deluxe"));
                hotel.setTotalSuite(rs.getInt("total_suite"));
                hotel.setStar(rs.getInt("star"));
                hotel.setImage(imageIcon);
//                hotelList.add(hotel);
            }

            for (int i = 0; i < 3; i++) {
                int count = 0;

                if (i == 1) {
                    count = hotel.getTotalStandard();
                } else if (i == 2) {
                    count = hotel.getTotalStandard() + hotel.getTotalDeluxe();
                }

                ps = conn.prepareStatement("SELECT * FROM room WHERE room_id = ?");
                String pattrent = hotel.getHotelId() + count;
                ps.setInt(1, Integer.parseInt(pattrent));
                rs = ps.executeQuery();

                if (i == 0) {
                    while (rs.next()) {
                        hotel.setStandardPrice(rs.getInt("price"));
//                hotelList.add(hotel);
                    }
                } else if (i == 2) { // SEBELUMNYA 3
                    while (rs.next()) {
                        hotel.setDeluxePrice(rs.getInt("price"));
//                hotelList.add(hotel);
                    }
                } else {
                    while (rs.next()) {
                        hotel.setSuitePrice(rs.getInt("price"));
//                hotelList.add(hotel);
                    }
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return hotel;
    }

//    public static void addTransaction (Transaction trx) throws SQLException {
//        try {
//            ps = conn.prepareStatement("INSERT INTO transaction (transaction_id, payment_method, tax_fee, check_in_date, check_out_date, room_type, user_id, room_id) VALUES(?, ?, ?, ?, ?, ?, ? , ? , ?)");
//            ps.setInt(1, trx.getTransactionId());
//            ps.setString(2, trx.getPaymentMethod());
//            ps.setDouble(3, trx.getTaxFee());
//            ps.setString(4, trx.getCheck_in());
//            ps.setString(5, trx.getCheck_out());
//            ps.setString(6, trx.getType());
//            ps.setInt(7, trx.getTransactionPrice());
//            ps.setInt(8, env.userData.getUser_id());
//            ps.setInt(9, trx.getRoomId());
//            ps.executeUpdate();
//            System.out.println("Data Transaction sudah ditambahkan!");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            throw new SQLException("SQL ada yang salah!!!");
//        }
//    }



    public static void addTransaction (Transaction trx) throws SQLException {
        try {
            ps = conn.prepareStatement("INSERT INTO transaction (transaction_id, payment_method, tax_fee, check_in_date, check_out_date, room_type, transaction_price, user_id, room_id) VALUES(?, ?, ?, ?, ?, ?, ? , ? , ?)");
            ps.setInt(1, trx.getTransactionId());
            ps.setString(2, trx.getPaymentMethod());
            ps.setDouble(3, trx.getTaxFee());
            ps.setString(4, trx.getCheck_in());
            ps.setString(5, trx.getCheck_out());
            ps.setString(6, trx.getType());
            ps.setInt(7, trx.getTransactionPrice());
            ps.setInt(8, env.userData.getUser_id());
            ps.setInt(9, trx.getRoomId());
            ps.executeUpdate();
            System.out.println("Data Transaction sudah ditambahkan!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException("SQL PROBLEMS");
        }
    }

    public static int findRoomIdByHotelId(int hotelId) {
        int id = 0;

        try {
            ps = conn.prepareStatement("SELECT room_id FROM room WHERE hotel_id = ?");
            ps.setInt(1, hotelId);
            rs = ps.executeQuery();

            if (rs.next()) {
                id =  rs.getInt("room_id");
            }
            // conn.setAutoCommit(false);
            // conn.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public static List<Transaction> GETAllTransaction (){
        List<Transaction> transactionList = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM transaction");

            while (rs.next()) {
               Transaction transaction = new Transaction();
               transaction.setUserId(rs.getInt("user_id"));
               transaction.setRoomId(rs.getInt("room_id"));
               transaction.setPaymentMethod(rs.getString("payment_method"));
               transaction.setCheck_in(rs.getString("check_in_date"));
               transaction.setCheck_out(rs.getString("check_out_date"));
               transaction.setType(rs.getString("room_type"));
               transaction.setTransactionPrice(rs.getInt("transaction_price"));
               transaction.setTaxFee(rs.getDouble("tax_fee"));

               transactionList.add(transaction);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return transactionList;
    }

    public static void updateRoomAvailability(String hotelId, String roomType, int bookingCount) throws SQLException {
        int roomCount = 0;

        try {
            ps = conn.prepareStatement("SELECT " + roomType + " FROM hotel WHERE hotel_id = ?");
            ps.setInt(1, Integer.parseInt(hotelId));
            rs = ps.executeQuery();

            if (rs.next()) {
                roomCount =  rs.getInt(roomType);
            }
            // conn.setAutoCommit(false);
            // conn.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }

        int updateAvailabilityRoom = roomCount - bookingCount;

        int parameterIndex = roomType.equals("total_standard") ? 4 : roomType.equals("total_deluxe") ? 5 : roomType.equals("total_suite") ? 6 : 1;

        try {
            ps = conn.prepareStatement("UPDATE hotel SET " + roomType + " = ? WHERE hotel_id = ?");
            ps.setInt(1, updateAvailabilityRoom);
            ps.setInt(2, Integer.parseInt(hotelId));
            ps.executeUpdate();
            // conn.setAutoCommit(false);
            // conn.commit();
            System.out.println("Data updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException("User data update failed!");
        }
    }

    public static int findRoomPriceByHotelId(int hotelId, String roomType, int totalStandard, int totalDeluxe) {
        int price = 0;

        try {
            ps = conn.prepareStatement("SELECT price FROM room WHERE hotel_id = ? AND room_id = ?");
            int i = 0;
            if (roomType.equals("Standard")) {
                i = 0;
            } else if (roomType.equals("Deluxe")) {
                i = totalStandard;
            } else {
                i = totalStandard + totalDeluxe;
            }
            String patt = String.valueOf(hotelId) + String.valueOf(i);

            ps.setInt(1, hotelId);
            ps.setInt(2, Integer.parseInt(patt));
            rs = ps.executeQuery();



            if (rs.next()) {
                price =  rs.getInt("price");
            }
            // conn.setAutoCommit(false);
            // conn.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL PROBLEMS", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return price;
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
