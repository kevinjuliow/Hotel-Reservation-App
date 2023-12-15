package Transylvania.pages.Detail;


import Transylvania.Classes.Hotel;
import Transylvania.Classes.Room;
import Transylvania.Classes.Transaction;
import Transylvania.Main;
import Transylvania.database.DBControls;
import Transylvania.env;
import Transylvania.pages.PaymentPage.Payment;
import Transylvania.style.NoScalingIcon;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class HotelDetail extends JFrame {

    JRadioButton standardRadio, deluxeRadio, suiteRadio;
    int bookingRoomCount = 0;
    int currentAvailableRoom = 0;

    boolean isAlreadySelectedRoom = false;

    public HotelDetail(Hotel hotelData) {
        env.hotelData = hotelData;
        int star = hotelData.getStar();
        env.hotelName = hotelData.getHotelName();

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Side Bar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 80, env.FRAME_HEIGHT);
        sidebar.setBackground(Color.decode(env.NICE_RED));
        NoScalingIcon imageIconSideBar = new NoScalingIcon(env.LoadImage("assets/chevron-left-solid-240.png", 48, 48));
        JLabel backIcon = new JLabel(imageIconSideBar);
        backIcon.setBounds(16, 34, 48, 48);
        sidebar.add(backIcon);
        env.MouseListener(backIcon, (MouseEvent e) -> {
            try {
                Main main = new Main(env.userData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
            return null;
        });

        backIcon.addMouseListener(new env.CursorPointerStyle(backIcon));

        // Top bar
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBounds(80, 0, 1200, 80);
//        topBar.setBackground(Color.decode(env.TOPBAR_COLOR));
        topBar.setBackground(Color.decode(env.MAIN_COLOR));
        // Center Panel (Main Panel)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.decode(env.MAIN_COLOR));
        centerPanel.setBounds(80, 80, 1200, 640);
        
        ImageIcon image = hotelData.getImage();
        
        env.LoadImageIcon(image, 30, 30);
        NoScalingIcon hotelImage = new NoScalingIcon(image);
//        NoScalingIcon hotelImage = new NoScalingIcon(env.LoadImage("assets/example.png", 1100, 210));
        JLabel imageHotel = new JLabel(hotelImage);
        imageHotel.setBounds(0, 40, 1200, 210);
        centerPanel.add(imageHotel);

        JLabel hotelTitle = new JLabel(hotelData.getHotelName());
        hotelTitle.setBounds(48, 265, 600, 50);
        hotelTitle.setFont(env.pixel28B);
        hotelTitle.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(hotelTitle);

        JLabel locationLabel = new JLabel(hotelData.getLocation(), SwingConstants.LEFT);
        locationLabel.setBounds(48, 310, 200, 36);
        locationLabel.setFont(env.pixel16);
        locationLabel.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(locationLabel);

//        ImageIcon imageStarIcon = null;
        NoScalingIcon imageStarIcon = null;
        int starWidth = 0;
        if (star == 5) {
            imageStarIcon = new NoScalingIcon(env.LoadImage("assets/five-star.png", 98, 18));
        } else if (star == 4) {
            imageStarIcon = new NoScalingIcon(env.LoadImage("assets/four-star.png", 78, 18));
        } else if (star == 3) {
            imageStarIcon = new NoScalingIcon(env.LoadImage("assets/three-star.png", 57, 18));
        }

        JLabel imageStar = new JLabel(imageStarIcon, SwingConstants.LEFT);
        imageStar.setBounds(42, 340, 200, 36);
        centerPanel.add(imageStar);

        JLabel descriptionTitle = new JLabel("Description");
        descriptionTitle.setBounds(48, 370, 100, 50);
        descriptionTitle.setFont(env.pixel12);
        descriptionTitle.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(descriptionTitle);

        JTextArea description = new JTextArea(hotelData.getDescription());
        description.setFont(env.pixel16);
        description.setForeground(Color.decode(env.DARK_COLOR));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setBorder(null);
        description.setBackground(Color.WHITE);
        JScrollPane descriptionScroll = new JScrollPane(description);
        descriptionScroll.setBorder(null);
        descriptionScroll.setBounds(48, 410, 1100, 65);

        JScrollBar verticalScrollBar = descriptionScroll.getVerticalScrollBar();
        verticalScrollBar.setBackground(Color.gray);
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.decode(env.NICE_RED);
                this.trackColor = Color.decode(env.MAIN_COLOR);
            }
        });
        centerPanel.add(descriptionScroll);

        JLabel jumlahKamarDipesan = new JLabel("Booking room count");
        jumlahKamarDipesan.setBounds(48, 490, 150, 50);
        jumlahKamarDipesan.setFont(env.pixel12);
        jumlahKamarDipesan.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(jumlahKamarDipesan);

        JLabel jumlahKamar = new JLabel(String.valueOf(bookingRoomCount));
        jumlahKamar.setBounds(48, 510, 50, 80);
        jumlahKamar.setFont(env.pixel32B);
        jumlahKamar.setForeground(Color.decode(env.DARK_COLOR));
        centerPanel.add(jumlahKamar);

        JButton minus = new JButton("-");
        minus.setBounds(100, 538, 50, 30);
        minus.setFont(env.pixel20B);
        minus.setFocusPainted(false);
        minus.setBackground(Color.decode(env.NICE_RED));
        minus.setForeground(Color.decode(env.MAIN_COLOR));
        centerPanel.add(minus);

        JButton plus = new JButton("+");
        plus.setBounds(160, 538, 50, 30);
        plus.setFont(env.pixel20B);
        // plus.setBorderPainted(false); // Button border
        plus.setFocusPainted(false);
        plus.setBackground(Color.decode(env.NICE_RED));
        plus.setForeground(Color.decode(env.MAIN_COLOR));
        centerPanel.add(plus);

        JLabel tipeKamar = new JLabel("Room Type");
        tipeKamar.setBounds(300, 490, 150, 50);
        tipeKamar.setFont(env.pixel12);
        tipeKamar.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(tipeKamar);

        JLabel standardLabel = new JLabel("Standard");
        standardLabel.setFont(env.pixel14B);
        JLabel deluxeLabel = new JLabel("Deluxe");
        deluxeLabel.setFont(env.pixel14B);
        JLabel suiteLabel = new JLabel("Suite");
        suiteLabel.setFont(env.pixel14B);

        standardLabel.setBounds(300, 514, 100, 50);
        deluxeLabel.setBounds(380, 514, 100, 50);
        suiteLabel.setBounds(460, 514, 100, 50);

        centerPanel.add(standardLabel);
        centerPanel.add(deluxeLabel);
        centerPanel.add(suiteLabel);

        standardRadio = new JRadioButton();
        standardRadio.setBackground(Color.decode(env.MAIN_COLOR));

        deluxeRadio = new JRadioButton();
        deluxeRadio.setBackground(Color.decode(env.MAIN_COLOR));

        suiteRadio = new JRadioButton();
        suiteRadio.setBackground(Color.decode(env.MAIN_COLOR));

        standardRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                standardLabel.setForeground(standardRadio.isSelected() ? Color.decode(env.NICE_RED) : Color.decode(env.DARK_COLOR));
            }
        });

        deluxeRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                deluxeLabel.setForeground(deluxeRadio.isSelected() ? Color.decode(env.NICE_RED) : Color.decode(env.DARK_COLOR));
            }
        });

        suiteRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                suiteLabel.setForeground(suiteRadio.isSelected() ? Color.decode(env.NICE_RED) : Color.decode(env.DARK_COLOR));
            }
        });

        ButtonGroup tipeKamarGroup = new ButtonGroup();
        tipeKamarGroup.add(standardRadio);
        tipeKamarGroup.add(deluxeRadio);
        tipeKamarGroup.add(suiteRadio);

        standardRadio.setBounds(320, 552, 20, 20);
        deluxeRadio.setBounds(396, 552, 20, 20);
        suiteRadio.setBounds(466, 552, 20, 20);

        centerPanel.add(standardRadio);
        centerPanel.add(deluxeRadio);
        centerPanel.add(suiteRadio);

//        JLabel pricePerNight = new JLabel()

        JLabel checkInLabel = new JLabel("Check-in");
        checkInLabel.setBounds(600, 490, 150, 50);
        checkInLabel.setFont(env.pixel12);
        checkInLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(checkInLabel);
        JLabel checkOutLabel = new JLabel("Check-out");
        checkOutLabel.setBounds(748, 490, 150, 50);
        checkOutLabel.setFont(env.pixel12);
        checkOutLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        centerPanel.add(checkOutLabel);

        JXDatePicker datePickerCheckIn = new JXDatePicker();
        datePickerCheckIn.setBounds(600, 538, 120, 28);
        datePickerCheckIn.setDate(Calendar.getInstance().getTime());
        datePickerCheckIn.setFormats(new SimpleDateFormat("dd - MM - yyyy"));
        centerPanel.add(datePickerCheckIn);

        JXDatePicker datePickerCheckOut = new JXDatePicker();
        datePickerCheckOut.setBounds(750, 538, 120, 28);
        datePickerCheckOut.setDate(Calendar.getInstance().getTime());
        datePickerCheckOut.setFormats(new SimpleDateFormat("dd - MM - yyyy"));
        centerPanel.add(datePickerCheckOut);

        JButton pesanButton = new JButton("BOOK NOW");
        pesanButton.setBounds(946, 538, 200, 40);
        pesanButton.setFont(env.pixel14B);
        pesanButton.setFocusPainted(false);
        pesanButton.setBackground(Color.decode(env.NICE_RED));
        pesanButton.setForeground(Color.decode(env.MAIN_COLOR));
        pesanButton.setEnabled(false);
        centerPanel.add(pesanButton);

        JLabel errorInformationLabel = new JLabel("", SwingConstants.CENTER);
        errorInformationLabel.setBounds(925, 580, 240, 28);
        errorInformationLabel.setFont(env.pixel9B);
        errorInformationLabel.setForeground(Color.decode(env.NICE_RED));
        centerPanel.add(errorInformationLabel);

        //Radio Buttons
        JLabel availableRoom = new JLabel();
        availableRoom.setBounds(300, 585, 150, 20);
        availableRoom.setForeground(Color.decode(env.LIGHT_GRAY));
        availableRoom.setFont(env.pixel12);
        centerPanel.add(availableRoom);
        //Radio Button Action Listeners
        env.RadioButtonListener(standardRadio, (ActionEvent e) -> {
            pesanButton.setEnabled(true);
            isAlreadySelectedRoom = true;
            jumlahKamar.setText("0");
            bookingRoomCount = 0;
            plus.setEnabled(true);
            availableRoom.setText("Available Room : " + String.valueOf(hotelData.getTotalStandard()));
            currentAvailableRoom = hotelData.getTotalStandard();
            return null;
        });

        env.RadioButtonListener(deluxeRadio, (ActionEvent e) -> {
            pesanButton.setEnabled(true);
            isAlreadySelectedRoom = true;
            jumlahKamar.setText("0");
            bookingRoomCount = 0;
            plus.setEnabled(true);
            availableRoom.setText("Available Room : " + String.valueOf(hotelData.getTotalDeluxe()));
            currentAvailableRoom = hotelData.getTotalDeluxe();
            return null;
        });

        env.RadioButtonListener(suiteRadio, (ActionEvent e) -> {
            pesanButton.setEnabled(true);
            isAlreadySelectedRoom = true;
            jumlahKamar.setText("0");
            bookingRoomCount = 0;
            plus.setEnabled(true);
            availableRoom.setText("Available Room : " + String.valueOf(hotelData.getTotalSuite()));
            currentAvailableRoom = hotelData.getTotalSuite();
            return null;
        });

        if (!isAlreadySelectedRoom) {
            plus.setEnabled(false);
        }
        if (!isAlreadySelectedRoom) {
            minus.setEnabled(false);
        }

        //Plus Minus Action Listeners
        env.ActionListener(minus, (ActionEvent e) -> {
            if (bookingRoomCount != currentAvailableRoom + 1) {
                plus.setEnabled(true);
            }
            minus.setEnabled(true);
            if (bookingRoomCount != 0) {
                bookingRoomCount--;
                jumlahKamar.setText(String.valueOf(bookingRoomCount));
            }
            return null;
        });

        env.ActionListener(plus, (ActionEvent e) -> {
            if (bookingRoomCount == currentAvailableRoom - 1) {
                plus.setEnabled(false);
            }
            minus.setEnabled(true);
            bookingRoomCount++;
            jumlahKamar.setText(String.valueOf(bookingRoomCount));
            return null;
        });

        pesanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookingRoomCount == 0) {
                    errorInformationLabel.setText("ONE IS MINIMUM REQUIREMENT OF BOOKING ROOM");
                    return;
                }
                //check if checkout date less than checkin date
                if (datePickerCheckOut.getDate().before(datePickerCheckIn.getDate())) {
                    errorInformationLabel.setText("CHECKOUT DATE INVALID");
                } else if (datePickerCheckIn.getDate().equals(datePickerCheckOut.getDate())) {
                    errorInformationLabel.setText("CHECKOUT DATE INVALID");
                } else {
                    //format the date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedCheckInDate = dateFormat.format(datePickerCheckIn.getDate());
                    String formattedCheckOutDate = dateFormat.format(datePickerCheckOut.getDate());

                    System.out.println("Check-In Date: " + formattedCheckInDate);
                    System.out.println("Check-Out Date: " + formattedCheckOutDate);
                    errorInformationLabel.setText("SUCCESS");

                    String roomType = standardRadio.isSelected() ? "Standard" : deluxeRadio.isSelected() ? "Deluxe" : suiteRadio.isSelected() ? "Suite" : "";

//                    dispose();
                    // PRICE 1250000 CUMA DATA DUMMY, NANTI HARUSNYA AMBIL DARI DATABASE
//                    Room roomData = new Room(roomType, bookingRoomCount, Integer.parseInt(hotelData.getHotelId()), formattedCheckInDate, formattedCheckOutDate, DBControls.findRoomPriceByHotelId(Integer.parseInt(hotelData.getHotelId())));
                    Room roomData = new Room(roomType, bookingRoomCount, Integer.parseInt(hotelData.getHotelId()), formattedCheckInDate, formattedCheckOutDate, DBControls.findRoomPriceByHotelId(Integer.parseInt(hotelData.getHotelId()), roomType, hotelData.getTotalStandard(), hotelData.getTotalDeluxe()));
                    Transaction trx = new Transaction();
                    trx.setCheck_in(roomData.getCheckIn());
                    trx.setCheck_out(roomData.getCheckOut());
                    trx.setType(roomData.getRoomType());
                    trx.setTransactionPrice(roomData.getPrice());
                    trx.setCountRoom(roomData.getRoomCount());
                    trx.setTaxFee((roomData.getRoomCount() * roomData.getPrice()) * 0.05);
                    trx.setHotelId(roomData.getHotelId());
                    trx.setRoomId(DBControls.findRoomIdByHotelId(roomData.getHotelId()));

                    Payment payment = new Payment(trx);
                    payment.setVisible(true);
                    dispose();
                }
            }
        });

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
        setVisible(true);
    }

//    public static void main(String[] args) {
//        HotelDetail hotelDetail = new HotelDetail(new Hotel("100", "gatau", "hehe", "indonesia aja", 15, 22, 33, 5));
//    }
}
