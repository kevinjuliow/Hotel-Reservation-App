package Transylvania.pages.AdminPage;

import Transylvania.Classes.Hotel;
import Transylvania.Classes.User;
import Transylvania.database.DBControls;
import Transylvania.env;

import Transylvania.style.NoScalingIcon;
import Transylvania.style.RoundedBorder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditPage extends JFrame {

    public static int standardRoomValue = 0;
    public static int deluxeRoomValue = 0;
    public static int suiteRoomValue = 0;
    public static int standardPrice = 0;
    public static int deluxePrice = 0;
    public static int suitePrice = 0;
    public File selectedFile;
    public byte[] imageData;

    public static void main(String[] args) throws IOException {
        EditPage main = new EditPage(new User( "", "", "", "", ""));
        DBControls.openConnection();
    }

    public EditPage(User userData) throws IOException {
        Hotel dataHotel = DBControls.GetEditHotel(userData.getUser_id());

        //Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        setBounds(env.WINDOW_POST_X, env.WINDOW_POST_Y, env.FRAME_WIDTH, env.FRAME_HEIGHT);

        // Inner Panel
        JPanel innerPanel = new JPanel(null);
        innerPanel.setBounds(80, 80, env.FRAME_WIDTH - 80, env.FRAME_HEIGHT - 80);
        mainPanel.add(innerPanel);
        innerPanel.setBackground(Color.decode(env.MAIN_COLOR));

        JLabel addHotelLabel = new JLabel("Add Hotel");
        addHotelLabel.setBounds(63, 42, 250, 37);
        addHotelLabel.setForeground(Color.decode(env.DARK_COLOR));
        addHotelLabel.setFont(env.pixel28B);
        innerPanel.add(addHotelLabel);

        //Names
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(63, 96, 100, 20);
        nameLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        nameLabel.setFont(env.pixel12);
        innerPanel.add(nameLabel);

        JTextField nameTf = new JTextField();
        nameTf.setBounds(63, 119, 280, 32);
        nameTf.setText(dataHotel.getHotelName());
        innerPanel.add(nameTf);

        //Location
        JLabel locationLabel = new JLabel("Location");
        locationLabel.setBounds(383, 97, 46, 16);
        locationLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        locationLabel.setFont(env.pixel12);
        innerPanel.add(locationLabel);

        JTextField locationTf = new JTextField();
        locationTf.setBounds(383, 119, 280, 32);
        locationTf.setText(dataHotel.getLocation());
        innerPanel.add(locationTf);

        //Star
        JLabel starLabel = new JLabel("Star");
        starLabel.setBounds(703, 97, 21, 16);
        starLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        starLabel.setFont(env.pixel12);
        innerPanel.add(starLabel);

        Integer star[] = {3, 4, 5};
        JComboBox starJcb = new JComboBox(star);
        starJcb.setBounds(703, 119, 100, 32);
        starJcb.setSelectedItem(dataHotel.getStar());
        innerPanel.add(starJcb);

        //Room Details
        JLabel roomDetailLabel = new JLabel("Room Details");
        roomDetailLabel.setBounds(65, 211, 100, 20);
        innerPanel.add(roomDetailLabel);

        //standardPanel
        JPanel standardPanel = RoomType("Standard", "");
        standardPanel.setBounds(65, 236, 373, 107);
        innerPanel.add(standardPanel);

        //deluxePanel
        JPanel deluxePanel = RoomType("Deluxe", "");
        deluxePanel.setBounds(65, 360, 373, 107);
        innerPanel.add(deluxePanel);

        //suitePanel
        JPanel suitePanel = RoomType("Suite", "");
        suitePanel.setBounds(65, 485, 373, 107);
        innerPanel.add(suitePanel);

        //Description
        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(478, 211, 61, 16);
        descriptionLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        descriptionLabel.setFont(env.pixel12);
        innerPanel.add(descriptionLabel);

        JTextArea desctiptionTf = new JTextArea(10, 0);
        JScrollPane scrollPane = new JScrollPane(desctiptionTf);
        scrollPane.setBounds(478, 233, 300, 200);
        desctiptionTf.setLineWrap(true);
        desctiptionTf.setWrapStyleWord(true);
        desctiptionTf.setBorder(null);
        desctiptionTf.setText(dataHotel.getDescription());
        scrollPane.setBorder(new RoundedBorder(20));
        scrollPane.setBackground(Color.decode(env.MAIN_COLOR));

        innerPanel.add(scrollPane);

        //Hotel Image
        JLabel hotelImageLabel = new JLabel("Hotel Image");
        hotelImageLabel.setBounds(817, 211, 80, 16);
        hotelImageLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        hotelImageLabel.setFont(env.pixel12);
        innerPanel.add(hotelImageLabel);

        JButton addImageBtn = new JButton(new NoScalingIcon(env.LoadImage("assets/camera2.png", 60, 60)));
        addImageBtn.setBounds(820, 233, 300, 200);
        addImageBtn.setBackground(Color.decode(env.MAIN_COLOR));
        addImageBtn.setFont(env.pixel28B);
        addImageBtn.setBorder(new RoundedBorder(20));
        innerPanel.add(addImageBtn);

        addImageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "svg");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                int result = fileChooser.showOpenDialog(EditPage.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(addImageBtn.getWidth(), addImageBtn.getHeight(), Image.SCALE_SMOOTH));
                    NoScalingIcon addedImage = new NoScalingIcon(imageIcon);
                    addImageBtn.setIcon(addedImage);
                    try {
                        InputStream inputStream = new FileInputStream(selectedFile);
                        imageData = new byte[(int) selectedFile.length()];
                        inputStream.read(imageData);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //add Button
        JButton addBtn = new JButton("ADD");
        addBtn.setBounds(1000, 550, 130, 35);
        addBtn.setBackground(Color.decode(env.NICE_RED));
        addBtn.setForeground(Color.decode(env.MAIN_COLOR));
        innerPanel.add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel hotel = new Hotel();

                hotel.setHotelName(nameTf.getText());
                hotel.setDescription(desctiptionTf.getText());
                hotel.setLocation(locationTf.getText());
                hotel.setTotalStandard(standardRoomValue);
                hotel.setTotalDeluxe(deluxeRoomValue);
                hotel.setTotalSuite(suiteRoomValue);
                hotel.setStar(Integer.parseInt(starJcb.getSelectedItem().toString()));
                hotel.setImageData(imageData);

                hotel.setStandardPrice(standardPrice);
                hotel.setDeluxePrice(deluxePrice);
                hotel.setSuitePrice(suitePrice);

                try {
                    DBControls.addHotel(hotel);
                } catch (SQLException ex) {
                    Logger.getLogger(EditPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        setContentPane(mainPanel);
        setUndecorated(true);
        setVisible(true);
        mainPanel.setBackground(Color.decode(env.NICE_RED));
    }

    public JPanel RoomType(String roomType, String tfName) {
        JPanel panel = new JPanel(null);

        JLabel typeLabel = new JLabel(roomType);
        typeLabel.setBounds(12, 10, 56, 15);
        typeLabel.setForeground(Color.decode(env.DARK_COLOR));
        typeLabel.setFont(env.pixel14);
        panel.add(typeLabel);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(15, 30, 56, 12);
        amountLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        amountLabel.setFont(env.pixel9);
        panel.add(amountLabel);

        JLabel amountValue = null;
        if (roomType.equals("Deluxe")) {
            amountValue = new JLabel(String.valueOf(deluxeRoomValue));
        } else if (roomType.equals("Standard")) {
            amountValue = new JLabel(String.valueOf(standardRoomValue));
        } else {
            amountValue = new JLabel(String.valueOf(suiteRoomValue));
        }
        amountValue.setBounds(15, 45, 30, 37);
        amountValue.setFont(env.pixel28);
        panel.add(amountValue);

        JButton minus = new JButton("-");
        minus.setBounds(50, 55, 45, 20);
        minus.setFont(env.pixel14B);
        minus.setFocusPainted(false);
        minus.setBackground(Color.decode(env.NICE_RED));
        minus.setForeground(Color.decode(env.MAIN_COLOR));
        panel.add(minus);

        JButton plus = new JButton("+");
        plus.setBounds(105, 55, 45, 20);
        plus.setFont(env.pixel14B);
        plus.setFocusPainted(false);
        plus.setBackground(Color.decode(env.NICE_RED));
        plus.setForeground(Color.decode(env.MAIN_COLOR));
        panel.add(plus);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(175, 30, 56, 12);
        priceLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        priceLabel.setFont(env.pixel9);
        panel.add(priceLabel);

        JTextField priceTf = new JTextField();
        priceTf.setBounds(170, 50, 180, 35);
        panel.add(priceTf);
        priceTf.setName(roomType.toLowerCase() + "Price");

        if (!(priceTf.getText().equals(""))) {
            if (roomType.equals("Deluxe")) {
                deluxePrice = Integer.parseInt(priceTf.getText());
            } else if (roomType.equals("Standard")) {
                standardPrice = Integer.parseInt(priceTf.getText());
            } else {
                suitePrice = Integer.parseInt(priceTf.getText());
            }
        }

        JLabel finalAmountValue = amountValue;
        env.ActionListener(plus, (ActionEvent e) -> {
            if (roomType.equals("Deluxe")) {
                deluxeRoomValue++;
                finalAmountValue.setText(String.valueOf(deluxeRoomValue));
            } else if (roomType.equals("Standard")) {
                standardRoomValue++;
                finalAmountValue.setText(String.valueOf(standardRoomValue));
            } else {
                suiteRoomValue++;
                finalAmountValue.setText(String.valueOf(suiteRoomValue));
            }
            return null;
        });
        env.ActionListener(minus, (ActionEvent e) -> {
            if (deluxeRoomValue != 0 || standardRoomValue != 0 || suiteRoomValue != 0) {
                if (roomType.equals("Deluxe")) {
                    deluxeRoomValue--;
                    finalAmountValue.setText(String.valueOf(deluxeRoomValue));
                } else if (roomType.equals("Standard")) {
                    standardRoomValue--;
                    finalAmountValue.setText(String.valueOf(standardRoomValue));
                } else {
                    suiteRoomValue--;
                    finalAmountValue.setText(String.valueOf(suiteRoomValue));
                }
            }
            return null;
        });

        priceTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isDigit(e.getKeyChar())) {
                } else {
                    e.consume();
                }
            }
        });
        return panel;
    }

}
