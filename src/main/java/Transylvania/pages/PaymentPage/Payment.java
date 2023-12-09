package Transylvania.pages.PaymentPage;

import Transylvania.style.RoundedBorder;
import Transylvania.env;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class Payment extends JFrame {

    public Payment() {
        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Side Bar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 80, env.FRAME_HEIGHT);
        sidebar.setBackground(Color.decode(env.NICE_RED));
        ImageIcon imageIconSideBar = new ImageIcon(Objects.requireNonNull(Payment.class.getClassLoader().getResource("assets/log-out-regular-240 (1).png")));
        imageIconSideBar = new ImageIcon(imageIconSideBar.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        JLabel icon = new JLabel(imageIconSideBar);
        icon.setBounds(16, 34, 48, 48);
        sidebar.add(icon);
        icon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        // Top bar
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBounds(80, 0, 1200, 80);
        topBar.setBackground(Color.decode(env.TOPBAR_COLOR));

        // Center Panel (Main Panel)
        JPanel centerPanel = new JPanel();
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

        JLabel checkOutLabel = new JLabel("Check-in");
        checkOutLabel.setBounds(300, 130, 100, 50);
        checkOutLabel.setFont(env.pixel16B);
        checkOutLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(checkOutLabel);

        JLabel checkInDate = new JLabel("December 25th 2023");
        checkInDate.setBounds(48, 155, 200, 50);
        checkInDate.setFont(env.pixel16);
        checkInDate.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(checkInDate);

        JLabel checkOutDate = new JLabel("January 1st 2023");
        checkOutDate.setBounds(300, 155, 200, 50);
        checkOutDate.setFont(env.pixel16);
        checkOutDate.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(checkOutDate);

        JLabel guestsLabel = new JLabel("Guests");
        guestsLabel.setBounds(48, 200, 100, 50);
        guestsLabel.setFont(env.pixel16B);
        guestsLabel.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(guestsLabel);

        JLabel guestCount = new JLabel("2 Guest(s)");
        guestCount.setBounds(48, 228, 200, 50);
        guestCount.setFont(env.pixel16);
        guestCount.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(guestCount);

        JLabel paymentMethodTitle = new JLabel("Payment method");
        paymentMethodTitle.setBounds(48, 270, 100, 50);
        paymentMethodTitle.setFont(env.pixel12);
        paymentMethodTitle.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(paymentMethodTitle);

        JComboBox<String> paymentMethodBox = new JComboBox<>(new String[]{ "", "BCA", "BRI", "BNI", "BTN", "Mandiri", "PayPal", "Dana" });
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
        transactionDetailsTitle.setBounds(700, 80, 100, 50);
        transactionDetailsTitle.setFont(env.pixel12);
        transactionDetailsTitle.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(transactionDetailsTitle);


        //Transaction Panel side
        JPanel transactionPanel = new JPanel(null);
        transactionPanel.setBackground(Color.decode(env.MAIN_COLOR));
        transactionPanel.setBounds(670 , 130 , 480 , 200);
        transactionPanel.setBorder(new RoundedBorder(40));
        centerPanel.add(transactionPanel);

        JLabel hotelTransactionLabel = new JLabel("Hotel");
        hotelTransactionLabel.setBounds(20 , 8 , transactionPanel.getWidth()-10 , 20);
        hotelTransactionLabel.setFont(env.pixel12);
        hotelTransactionLabel.setForeground(Color.decode(env.LIGHT_GRAY));

        JLabel hotelNameLabel = new JLabel("The Beverly Hills Hotel");
        hotelNameLabel.setBounds(21 , 24 , transactionPanel.getWidth()-10 , 20);
        hotelNameLabel.setFont(env.pixel12);
        hotelNameLabel.setForeground(Color.BLACK);

        JLabel priceDetailLabel = new JLabel("Price Details");
        priceDetailLabel.setBounds(21 , 55 , transactionPanel.getWidth()-10 , 20);
        priceDetailLabel.setFont(env.pixel12B);

        JLabel stayingPrice = new JLabel("Rp 1.250.000 x 2 night(s)");
        stayingPrice.setBounds(21 , 75 , 150 , 20);
        stayingPrice.setFont(env.pixel12);

        JLabel stayingPriceVal = new JLabel("Rp 2.500.000");
        stayingPriceVal.setBounds(370 , 75 , 120 , 20);
        stayingPriceVal.setFont(env.pixel12);

        JLabel taxLabel = new JLabel("Application Tax");
        taxLabel.setBounds(21 , 92 , 150 , 20);
        taxLabel.setFont(env.pixel12);

        JLabel taxVal = new JLabel("Rp 10.000");
        taxVal.setBounds(370 , 92 , 120 , 20);
        taxVal.setFont(env.pixel12);

        JLabel totalPriceLabel = new JLabel("Total price");
        totalPriceLabel.setBounds(21 , 141 , 120 , 20);
        totalPriceLabel.setFont(env.pixel12B);

        JLabel totalPriceVal = new JLabel("Rp. 2.510.000");
        totalPriceVal.setBounds(370 , 141 , 120 ,20);
        totalPriceVal.setFont(env.pixel12B);


        //Adding Transaction Panel
        transactionPanel.add(hotelTransactionLabel);
        transactionPanel.add(hotelNameLabel);
        transactionPanel.add(priceDetailLabel);
        transactionPanel.add(stayingPrice);
        transactionPanel.add(stayingPriceVal);
        transactionPanel.add(taxLabel);
        transactionPanel.add(taxVal);
        transactionPanel.add(totalPriceVal);
        transactionPanel.add(totalPriceLabel);

        // Adding
        panel.add(sidebar);
        panel.add(topBar);
        panel.add(centerPanel);

        // Settings
        setSize(env.FRAME_WIDTH,env.FRAME_HEIGHT);
        setContentPane(panel);
        setResizable(false);
        setLocation(env.WINDOW_POST_X, env.WINDOW_POST_Y);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class CustomRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
            return c;
        }
    }

    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.setVisible(true);
    }
}
