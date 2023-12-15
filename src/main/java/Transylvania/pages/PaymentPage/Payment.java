package Transylvania.pages.PaymentPage;

import Transylvania.Classes.Hotel;
import Transylvania.Classes.Room;
import Transylvania.Classes.Transaction;
import Transylvania.Classes.User;
import Transylvania.Main;
import Transylvania.database.DBControls;
import Transylvania.env;
import Transylvania.pages.Detail.HotelDetail;
import Transylvania.pages.LoginSignUp.LoginSignupPage;
import Transylvania.style.NoScalingIcon;
import Transylvania.style.RoundedBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Payment extends JFrame {
    JPanel centerPanel;

    public Payment(Transaction trx) {
        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Side Bar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 80, env.FRAME_HEIGHT);
        sidebar.setBackground(Color.decode(env.NICE_RED));
        NoScalingIcon imageIconSideBar = new NoScalingIcon(env.LoadImage("assets/chevron-left-solid-240.png", 48, 48));
        JLabel icon = new JLabel(imageIconSideBar);
        icon.setBounds(16, 34, 48, 48);
        sidebar.add(icon);
        env.MouseListener(icon, (MouseEvent e) -> {
            HotelDetail hotelDetail = new HotelDetail(env.hotelData);
            dispose();
            return null;
        });
        icon.addMouseListener(new env.CursorPointerStyle(icon));

        // Top bar
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBounds(80, 0, 1200, 80);
//        topBar.setBackground(Color.decode(env.TOPBAR_COLOR));
        topBar.setBackground(Color.decode(env.MAIN_COLOR));

        // Center Panel (Main Panel)
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.decode(env.MAIN_COLOR));
        centerPanel.setBounds(80, 80, 1200, 640);

        JLabel paymentTitle = new JLabel("Confirmation and Payment");
        paymentTitle.setBounds(48, -45, 1200, 210);
        paymentTitle.setFont(env.pixel28B);
        paymentTitle.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(paymentTitle);

        JLabel bookingDetailsTitle = new JLabel("Booking details");
        bookingDetailsTitle.setBounds(48, 80, 100, 50);
        bookingDetailsTitle.setFont(env.pixel12);
        bookingDetailsTitle.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(bookingDetailsTitle);

        JLabel datesLabel = new JLabel("Dates");
        datesLabel.setBounds(48, 105, 100, 50);
        datesLabel.setFont(env.pixel16B);
        datesLabel.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(datesLabel);

        JLabel checkInLabel = new JLabel("Check-in");
        checkInLabel.setBounds(48, 130, 100, 50);
        checkInLabel.setFont(env.pixel16B);
        checkInLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(checkInLabel);

        JLabel lineBetween = new JLabel();
        lineBetween.setBounds(215, 140, 20, 56);
        Border leftBorder = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.decode(env.DARK_COLOR));
        lineBetween.setBorder(leftBorder);
        centerPanel.add(lineBetween);

        JLabel checkOutLabel = new JLabel("Check-out");
        checkOutLabel.setBounds(250, 130, 100, 50);
        checkOutLabel.setFont(env.pixel16B);
        checkOutLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(checkOutLabel);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM d', 'yyyy");

        JLabel checkInDate = new JLabel(String.valueOf(LocalDate.parse(trx.getCheck_in(), inputFormatter).format(outputFormatter)));
        checkInDate.setBounds(48, 155, 200, 50);
        checkInDate.setFont(env.pixel16);
        checkInDate.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(checkInDate);

        JLabel checkOutDate = new JLabel(String.valueOf(LocalDate.parse(trx.getCheck_out(), inputFormatter).format(outputFormatter)));
        checkOutDate.setBounds(250, 155, 200, 50);
        checkOutDate.setFont(env.pixel16);
        checkOutDate.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(checkOutDate);

        JLabel bookedByLabel = new JLabel("Room booked by");
        bookedByLabel.setBounds(48, 200, 200, 50);
        bookedByLabel.setFont(env.pixel16B);
        bookedByLabel.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(bookedByLabel);

        JLabel names = new JLabel(env.userData.getFullName());
        names.setBounds(48, 228, 200, 50);
        names.setFont(env.pixel16);
        names.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(names);

        JLabel paymentMethodTitle = new JLabel("Payment method");
        paymentMethodTitle.setBounds(48, 270, 100, 50);
        paymentMethodTitle.setFont(env.pixel12);
        paymentMethodTitle.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(paymentMethodTitle);

        JComboBox<String> paymentMethodBox = new JComboBox<>(new String[]{"BCA", "BRI", "BNI", "BTN", "Mandiri", "PayPal", "Dana"});
        paymentMethodBox.setBounds(48, 312, 300, 30);
        paymentMethodBox.setFont(env.pixel12);
//        paymentMethodBox.setBackground(Color.decode(env.NICE_RED));
//        paymentMethodBox.setRenderer(new CustomRenderer());
        centerPanel.add(paymentMethodBox);

        JLabel cancellationPolicy = new JLabel("Cancellation Policy");
        cancellationPolicy.setBounds(48, 494, 200, 50);
        cancellationPolicy.setFont(env.pixel16B);
        cancellationPolicy.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(cancellationPolicy);

        JLabel firstPolicy = new JLabel("Non-refundable: Cancel before check-in and get back only the cleaning fee, if you've paid one.");
        firstPolicy.setBounds(48, 524, 500, 50);
        firstPolicy.setFont(env.pixel12);
        firstPolicy.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(firstPolicy);

        JLabel secondPolicy = new JLabel("Our Extenuating Circumstances policy does not cover travel disruptions caused by COVID-19.");
        secondPolicy.setBounds(48, 544, 500, 50);
        secondPolicy.setFont(env.pixel12);
        secondPolicy.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(secondPolicy);

        JButton confirmButton = new JButton("CONFIRM AND PAY!");
        confirmButton.setBounds(946, 538, 200, 40);
        confirmButton.setFont(env.pixel14B);
        confirmButton.setFocusPainted(false);
        confirmButton.setBackground(Color.decode(env.NICE_RED));
        confirmButton.setForeground(Color.decode(env.MAIN_COLOR));
        centerPanel.add(confirmButton);

        JLabel transactionDetailsTitle = new JLabel("Transaction details");
        transactionDetailsTitle.setBounds(670, 80, 100, 50);
        transactionDetailsTitle.setFont(env.pixel12);
        transactionDetailsTitle.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(transactionDetailsTitle);

        // Transaction panel side
        JPanel transactionPanel = new JPanel(null);
        transactionPanel.setBackground(Color.decode(env.MAIN_COLOR));
        transactionPanel.setBounds(670, 130, 480, 190);
        transactionPanel.setBorder(new RoundedBorder(40));
        centerPanel.add(transactionPanel);

        JLabel hotelTransactionLabel = new JLabel("Hotel");
        hotelTransactionLabel.setBounds(20, 14, transactionPanel.getWidth() - 10, 20);
        hotelTransactionLabel.setFont(env.pixel12);
        hotelTransactionLabel.setForeground(Color.decode(env.LIGHT_GRAY));

//        String hotelName = "";
//        for (int i = 0; i < env.hotelListGlobal.size(); i++) {
//            if (env.hotelListGlobal.get(i).getHotelId().equals(trx.getHotelId())) {
//                hotelName = env.hotelListGlobal.get(i).getHotelName();
//                System.out.println(i + " " + hotelName);
//            }
//        }
        System.out.println("INI HOTEL ID YA " + trx.getHotelId());
        JLabel hotelNameLabel = new JLabel(env.hotelName);
        hotelNameLabel.setBounds(21, 34, transactionPanel.getWidth() - 10, 20);
        hotelNameLabel.setFont(env.pixel16);

        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(env.DARK_COLOR));
        JLabel lineBottomOne = new JLabel();
        lineBottomOne.setBounds(21, 60, 440, 5);
        lineBottomOne.setBorder(bottomBorder);

        JLabel priceDetailLabel = new JLabel("Price Details");
        priceDetailLabel.setBounds(21, 70, transactionPanel.getWidth() - 10, 20);
        priceDetailLabel.setFont(env.pixel16B);

        //Date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Days differences
        int daysDifference = (int) LocalDate.parse(trx.getCheck_in(), formatter).until(LocalDate.parse(trx.getCheck_out(), formatter)).getDays();

        JLabel stayingPrice = new JLabel("Rp" + trx.getTransactionPrice() + " x " + daysDifference + " night(s)");
        stayingPrice.setBounds(21, 94, 150, 20);
        stayingPrice.setFont(env.pixel12);

        int paymentTotal = (trx.getTransactionPrice() * daysDifference);

        JLabel stayingPriceVal = new JLabel("Rp" + NumberFormat.getNumberInstance(Locale.GERMAN).format(paymentTotal));
        stayingPriceVal.setBounds(280, 94, 150, 20);
        stayingPriceVal.setFont(env.pixel12);

        JLabel taxLabel = new JLabel("Application Tax");
        taxLabel.setBounds(21, 112, 150, 20);
        taxLabel.setFont(env.pixel12);

        JLabel lineBottomTwo = new JLabel();
        lineBottomTwo.setBounds(21, 137, 440, 5);
        lineBottomTwo.setBorder(bottomBorder);

        JLabel taxVal = new JLabel("Rp" + NumberFormat.getNumberInstance(Locale.GERMAN).format(paymentTotal * 0.05));
        taxVal.setBounds(280, 112, 150, 20);
        taxVal.setFont(env.pixel12);

        JLabel totalPriceLabel = new JLabel("Total price");
        totalPriceLabel.setBounds(21, 150, 120, 20);
        totalPriceLabel.setFont(env.pixel16B);

        JLabel totalPriceVal = new JLabel("Rp" + NumberFormat.getNumberInstance(Locale.GERMAN).format(paymentTotal + (paymentTotal * 0.05)));
        totalPriceVal.setBounds(280, 150, 150, 20);
        totalPriceVal.setFont(env.pixel16B);

        // Adding Transaction Panel
        transactionPanel.add(hotelTransactionLabel);
        transactionPanel.add(hotelNameLabel);
        transactionPanel.add(priceDetailLabel);
        transactionPanel.add(stayingPrice);
        transactionPanel.add(stayingPriceVal);
        transactionPanel.add(taxLabel);
        transactionPanel.add(taxVal);
        transactionPanel.add(totalPriceVal);
        transactionPanel.add(totalPriceLabel);
        transactionPanel.add(lineBottomOne);
        transactionPanel.add(lineBottomTwo);

        // Adding
        panel.add(sidebar);
        panel.add(topBar);
        panel.add(centerPanel);

        // Settings
        setSize(env.FRAME_WIDTH, env.FRAME_HEIGHT);
        setContentPane(panel);
        setResizable(false);
        setLocation(env.WINDOW_POST_X, env.WINDOW_POST_Y);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Action Listeners
        //ConfirmButton

        env.ActionListener(confirmButton, (ActionEvent e) -> {
            panel.remove(centerPanel);
            centerPanel = PaymentSuccess.PaymentSuccess();
            panel.add(centerPanel);

            Timer timer = new Timer(1000, (ActionEvent evt) -> {
                panel.remove(centerPanel);
                dispose();
                try {
                    trx.setPaymentMethod(paymentMethodBox.getSelectedItem().toString());
                    DBControls.addTransaction(trx);
                    String roomType = trx.getType().equals("Standard") ? "total_standard" : trx.getType().equals("Deluxe") ? "total_deluxe" : trx.getType().equals("Suite") ? "total_suite" : "";
                    DBControls.updateRoomAvailability(String.valueOf(trx.getHotelId()), roomType, trx.getCountRoom());
                    Main main = new Main(env.userData);
                } catch (IOException ex) {
                    Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            timer.setRepeats(false);
            timer.start();
            repaint();
            revalidate();
            return null;
        });
    }

//    public static void main(String[] args) {
//        env.userData = new User("Stevan Ryan", "stve88@gmail.com", "stevansss", "081392598030");
//        Payment payment = new Payment(new Room("Suite", 1, "1", "14-12-2023", "16-12-2023", 1250000));
//        payment.setVisible(true);
//    }
}
