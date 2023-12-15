package Transylvania;

import Transylvania.Classes.User;
import Transylvania.Validator.DataValidator;
import Transylvania.database.DBControls;
import Transylvania.database.DbConnections;
import Transylvania.pages.LoginSignUp.LoginSignupPage;
import Transylvania.pages.HomePage.HomeContent;
import Transylvania.pages.LoginSignUp.LoginSignupPanels;
import Transylvania.pages.ReceiptPage.historyContent;
import Transylvania.pages.SearchPage.SearchContent;
import Transylvania.style.NoScalingIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Main extends JFrame {
    static JPanel content;
    public static JLabel homeIcon, searchIcon, receiptIcon, logoutIcon , usernameLabel;


    public static void main(String[] args) {
//        Main main = new Main("Username");
        LoginSignupPage start = new LoginSignupPage();
        DBControls.openConnection();
    }

    public Main(User userdata) throws IOException {
        //Images
        NoScalingIcon homeIconImg = new NoScalingIcon(env.LoadImage("assets/home-regular-240 (1).png", 36, 36));
        NoScalingIcon homeIconClickedImg = new NoScalingIcon(env.LoadImage("assets/home-solid-240.png", 36, 36));
        NoScalingIcon searchIconImg = new NoScalingIcon(env.LoadImage("assets/search-regular-240 (1).png", 36, 36));
        NoScalingIcon searchIconClickedImg = new NoScalingIcon(env.LoadImage("assets/search-solid-240.png", 36, 36));
        NoScalingIcon receiptIconImg = new NoScalingIcon(env.LoadImage("assets/receipt-regular-240.png", 36, 36));
        NoScalingIcon receiptIconClicked = new NoScalingIcon(env.LoadImage("assets/receipt-solid-240.png", 36, 36));

        JPanel mainPanel = new JPanel();
        JPanel sideBarPanel = sideBarPanel();
        JPanel navBarPanel = navBarPanel(userdata.getFullName());
        JPanel homeContent = HomeContent.HomeContent();
        JPanel searchContent = SearchContent.findPanel();
        JPanel receiptContent = historyContent.historyPanel();
        content = homeContent;
        JPanel profilePanel = profilePanel(userdata);

        mainPanel.setBackground(Color.decode(env.MAIN_COLOR));

        env.animate(sideBarPanel, new Point(0, 0), 20, 1);
        env.animate(navBarPanel, new Point(80, 0), 20, 1);
        env.animate(content, new Point(80, 80), 20, 1);

        mainPanel.add(sideBarPanel);
        mainPanel.add(navBarPanel);
        mainPanel.add(content);

        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        setBounds(env.WINDOW_POST_X, env.WINDOW_POST_Y, env.FRAME_WIDTH, env.FRAME_HEIGHT);
        setUndecorated(true);
        setVisible(true);

        //sideBarIcons
        homeIcon = (JLabel) env.FindComponents(sideBarPanel, "homeIcon");
        searchIcon = (JLabel) env.FindComponents(sideBarPanel, "searchIcon");
        receiptIcon = (JLabel) env.FindComponents(sideBarPanel, "receiptIcon");
        logoutIcon = (JLabel) env.FindComponents(sideBarPanel, "logoutIcon");

        //navBar Components
        JComponent profileIcon = env.FindComponents(navBarPanel, "userIcon");

        //Profile Icon
        env.MouseListener(profileIcon, (MouseEvent e) -> {
//            setBounds(env.WINDOW_POST_X - 150, env.WINDOW_POST_Y, env.FRAME_WIDTH + 300, env.FRAME_HEIGHT);
            setBounds(env.WINDOW_POST_X - 110, env.WINDOW_POST_Y, env.FRAME_WIDTH + 220, env.FRAME_HEIGHT);
            mainPanel.add(profilePanel);
            JComponent exitProfile = env.FindComponents(profilePanel, "cancelProfileBtn");
            env.MouseListener(exitProfile, (MouseEvent b) -> {
                setBounds(env.WINDOW_POST_X, env.WINDOW_POST_Y, env.FRAME_WIDTH, env.FRAME_HEIGHT);
                mainPanel.remove(profilePanel);
                return null;
            });
            return null;
        });

        //SideBar Icons
        env.MouseListener(searchIcon, (MouseEvent e) -> {
            //Icons
            homeIcon.setIcon(homeIconImg);
            searchIcon.setIcon(searchIconClickedImg);
            receiptIcon.setIcon(receiptIconImg);

            mainPanel.remove(content);
            content = searchContent;
            mainPanel.add(content);
            mainPanel.revalidate();
            mainPanel.repaint();
            content.setBounds(80, 80, env.FRAME_WIDTH - 80, env.FRAME_HEIGHT - 80);
            return null;
        });

        env.MouseListener(receiptIcon, (MouseEvent e) -> {
            homeIcon.setIcon(homeIconImg);
            searchIcon.setIcon(searchIconImg);
            receiptIcon.setIcon(receiptIconClicked);

            mainPanel.remove(content);
            content = receiptContent;
            mainPanel.add(content);
            mainPanel.revalidate();
            mainPanel.repaint();
            content.setBounds(80, 80, env.FRAME_WIDTH - 80, env.FRAME_HEIGHT - 80);
            return null;
        });

        env.MouseListener(homeIcon, (MouseEvent e) -> {
            homeIcon.setIcon(homeIconClickedImg);
            searchIcon.setIcon(searchIconImg);
            receiptIcon.setIcon(receiptIconImg);

            mainPanel.remove(content);
            content = homeContent;
            mainPanel.add(content);
            mainPanel.revalidate();
            mainPanel.repaint();
            content.setBounds(80, 80, env.FRAME_WIDTH - 80, env.FRAME_HEIGHT - 80);
            return null;
        });

        env.MouseListener(logoutIcon, (MouseEvent e) -> {
            env.animate(sideBarPanel, new Point(-200, 0), 20, 1);
            env.animate(navBarPanel, new Point(80, 0), 20, 1);
            env.animate2(content, new Point(4000, 0), 20, 5, () -> {
                LoginSignupPage start = new LoginSignupPage();
                dispose();
            });
            return null;
        });


    }

    public static JPanel sideBarPanel() {
        //Images
        NoScalingIcon homeIconClicked = new NoScalingIcon(env.LoadImage("assets/home-solid-240.png", 36, 36));
        NoScalingIcon searchIcon = new NoScalingIcon(env.LoadImage("assets/search-regular-240 (1).png", 36, 36));
        NoScalingIcon receiptIcon = new NoScalingIcon(env.LoadImage("assets/receipt-regular-240.png", 36, 36));
        NoScalingIcon logoutIcon = new NoScalingIcon(env.LoadImage("assets/log-out-regular-240 (1).png", 36, 36));

        //Components
        JPanel sideBarPanel = new JPanel();
        JLabel home = new JLabel(homeIconClicked);
        home.setName("homeIcon");
        JLabel search = new JLabel(searchIcon);
        search.setName("searchIcon");
        JLabel receipt = new JLabel(receiptIcon);
        receipt.setName("receiptIcon");
        JLabel logout = new JLabel(logoutIcon);
        logout.setName("logoutIcon");

        home.addMouseListener(new env.CursorPointerStyle(home));
        search.addMouseListener(new env.CursorPointerStyle(search));
        receipt.addMouseListener(new env.CursorPointerStyle(receipt));
        logout.addMouseListener(new env.CursorPointerStyle(logout));

        //Bounds
        sideBarPanel.setBounds(-200, 0, 80, env.FRAME_HEIGHT);
        home.setBounds(22, 40, 36, 36);
        search.setBounds(22, 112, 36, 36);
        receipt.setBounds(22, 184, 36, 36);
        logout.setBounds(22, 640, 36, 36);

        //adds
        sideBarPanel.add(home);
        sideBarPanel.add(search);
        sideBarPanel.add(receipt);
        sideBarPanel.add(logout);

        //Backgrounds
        sideBarPanel.setBackground(Color.decode(env.NICE_RED));

        sideBarPanel.setLayout(null);
        return sideBarPanel;
    }

    public static JPanel navBarPanel(String username) {
        //Images
        NoScalingIcon userImage = new NoScalingIcon(env.LoadImage("assets/user.png", 35, 35));

        //Components
        JPanel navBarPanel = new JPanel();
        JLabel userImageLabel = new JLabel(userImage);
        userImageLabel.setName("userIcon");
        usernameLabel = new JLabel(username, SwingConstants.RIGHT);

        userImageLabel.addMouseListener(new env.CursorPointerStyle(userImageLabel));

        //Backgrounds
        navBarPanel.setBackground(Color.decode(env.MAIN_COLOR));

        //Bounds
        navBarPanel.setBounds(200, 0, env.FRAME_WIDTH - 80, 80);
        usernameLabel.setBounds(navBarPanel.getWidth() - 180, (navBarPanel.getHeight() / 2) - 10, 100, 30);
        userImageLabel.setBounds(usernameLabel.getX() + 105, (navBarPanel.getHeight() / 2) - 10, 35, 35);

        //add
        navBarPanel.add(userImageLabel);
        navBarPanel.add(usernameLabel);

        navBarPanel.setLayout(null);
        return navBarPanel;
    }

    //ProfilePanel
    public static JPanel profilePanel(User userData) {
        //Panels
        JPanel profilePanel = new JPanel(null);
        profilePanel.setBounds(env.FRAME_WIDTH, 0, 300, env.FRAME_HEIGHT);
        profilePanel.setBackground(Color.decode(env.MAIN_COLOR));

        Border leftBorder = BorderFactory.createMatteBorder(0, 5, 0, 0, Color.decode(env.NICE_RED));
        profilePanel.setBorder(leftBorder);

        JPanel decorationPanel = new JPanel(null);
        decorationPanel.setBounds(profilePanel.getWidth() - 20, 0, 50, env.FRAME_HEIGHT);
        decorationPanel.setBackground(Color.decode(env.NICE_RED));

        //Images
        NoScalingIcon editIcon = new NoScalingIcon(env.LoadImage("assets/edit-regular-240.png", 20, 20));

        //Profile Labels
        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setBounds(75, 100, 100, 50);
        profileLabel.setFont(env.pixel24B);

        //Name Profile
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(20, 151, 100, 25);
        nameLabel.setFont(env.pixel12);
        JLabel nameEdit = new JLabel(editIcon);
        nameEdit.setBounds(20, 176, 20, 25);
        nameEdit.addMouseListener(new env.CursorPointerStyle(nameEdit));

        JTextField nameValue = new JTextField(userData.getFullName());
        nameValue.setBounds(50, 176, 156, 25);
        nameValue.setBackground(Color.decode("#d6d6d6"));
        nameValue.setEditable(false);
        nameValue.setBorder(null);
        nameValue.setFont(env.pixel12);

        //Email Profile
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 201, 100, 25);
        passwordLabel.setFont(env.pixel12);
        JLabel passwordEdit = new JLabel(editIcon);
        passwordEdit.setBounds(20, 226, 20, 25);
        passwordEdit.addMouseListener(new env.CursorPointerStyle(passwordEdit));

        JTextField passwordValue = new JTextField(userData.getPassword());
        passwordValue.setBounds(50, 226, 156, 25);
        passwordValue.setBackground(Color.decode("#d6d6d6"));
        passwordValue.setEditable(false);
        passwordValue.setBorder(null);
        passwordValue.setFont(env.pixel12);

        //Phone Profile
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(20, 251, 100, 25);
        phoneLabel.setFont(env.pixel12);
        JLabel phoneEdit = new JLabel(editIcon);
        phoneEdit.setBounds(20, 276, 20, 25);
        phoneEdit.addMouseListener(new env.CursorPointerStyle(phoneEdit));

        JTextField phoneValue = new JTextField(userData.getPhoneNumber());
        phoneValue.setBounds(50, 276, 156, 25);
        phoneValue.setBackground(Color.decode("#d6d6d6"));
        phoneValue.setEditable(false);
        phoneValue.setBorder(null);
        phoneValue.setFont(env.pixel12);

        JLabel errorInformationLabel = new JLabel("", SwingConstants.CENTER);
        errorInformationLabel.setBounds(12, 370,  200, 25);
        errorInformationLabel.setForeground(Color.decode(env.NICE_RED));
        errorInformationLabel.setFont(env.pixel9B);

        //Buttons
        JButton saveBtn = new JButton("SAVE CHANGES");
        saveBtn.setBounds(40, 400, 150, 32);
        saveBtn.setBackground(Color.decode(env.NICE_RED));
        saveBtn.setForeground(Color.decode(env.MAIN_COLOR));
        saveBtn.setFont(env.pixel12B);
        saveBtn.setBorder(null);

        JButton cancelBtn = new JButton("BACK");
        cancelBtn.setName("cancelProfileBtn");
        cancelBtn.setBounds(40, 440, 150, 32);
        cancelBtn.setBackground(Color.decode(env.MAIN_COLOR));
        cancelBtn.setForeground(Color.decode(env.NICE_RED));
        cancelBtn.setFont(env.pixel12B);

        //Edit Listeners
        env.MouseListener(nameEdit, (MouseEvent e) -> {
            nameValue.setEditable(true);
            nameValue.setBackground(Color.decode("#ff8282"));
            return null;
        });

        env.MouseListener(passwordEdit, (MouseEvent e) -> {
            passwordValue.setEditable(true);
            passwordValue.setBackground(Color.decode("#ff8282"));
            return null;
        });

        env.MouseListener(phoneEdit, (MouseEvent e) -> {
            phoneValue.setEditable(true);
            phoneValue.setBackground(Color.decode("#ff8282"));
            return null;
        });

        //Button Listeners
        //Save Button
        env.ActionListener(saveBtn, (ActionEvent e) -> {
            try {
                User requestUpdateUser = new User(userData.getFullName(), userData.getEmail(), userData.getPassword(), userData.getPhoneNumber() , "user");

                //data validation
                DataValidator.checkIsEmpty(nameValue.getText());
                DataValidator.checkIsEmpty(passwordValue.getText());
                DataValidator.checkIsEmpty(phoneValue.getText());
                DataValidator.checkFullNameLength(nameValue.getText());
                DataValidator.checkNameIsCharacter(nameValue.getText());
                DataValidator.checkPhoneNumber(phoneValue.getText());

                if (!nameValue.getText().equals(userData.getFullName())) {
                    requestUpdateUser.setFullName(nameValue.getText());
                }
                if (!passwordValue.getText().equals(userData.getPassword())) {
                    requestUpdateUser.setPassword(passwordValue.getText());
                }
                if (!phoneValue.getText().equals(userData.getPhoneNumber())) {
                    requestUpdateUser.setPhoneNumber(phoneValue.getText());
                }

                errorInformationLabel.setText("DATA UPDATED SUCCESSFULLY!");
                errorInformationLabel.setForeground(Color.green);
                Timer timer = new Timer(1000, (ActionEvent evt) -> {
                    errorInformationLabel.setText("");
                    errorInformationLabel.setForeground(Color.decode(env.NICE_RED));
                });
                timer.setRepeats(false);
                timer.start();

                DBControls.updateDataUser(requestUpdateUser);
                LoginSignupPanels.loginPanel().revalidate();
                LoginSignupPanels.loginPanel().repaint();
                env.userData = requestUpdateUser;
                usernameLabel.setText(env.userData.getFullName());
                nameValue.setText(env.userData.getFullName());
                passwordValue.setText(env.userData.getPassword());
                phoneValue.setText(env.userData.getPhoneNumber());

            } catch (IllegalArgumentException | InputMismatchException | SQLException err) {
                errorInformationLabel.setText(err.getMessage());
                return null;
            }
            return null;
        });

        //Cancel Button
        env.ActionListener(cancelBtn, (ActionEvent e) -> {
            nameValue.setText(env.userData.getFullName());
            passwordValue.setText(env.userData.getPassword());
            phoneValue.setText(env.userData.getPhoneNumber());
            nameValue.setEditable(false);
            nameValue.setBackground(Color.decode("#d6d6d6"));
            passwordValue.setEditable(false);
            passwordValue.setBackground(Color.decode("#d6d6d6"));
            phoneValue.setEditable(false);
            phoneValue.setBackground(Color.decode("#d6d6d6"));
            return null;
        });

        profilePanel.add(decorationPanel);
        profilePanel.add(profileLabel);
        profilePanel.add(nameLabel);
        profilePanel.add(nameEdit);
        profilePanel.add(nameValue);
        profilePanel.add(passwordLabel);
        profilePanel.add(passwordEdit);
        profilePanel.add(passwordValue);
        profilePanel.add(phoneLabel);
        profilePanel.add(phoneEdit);
        profilePanel.add(phoneValue);
        profilePanel.add(errorInformationLabel);
        profilePanel.add(saveBtn);
        profilePanel.add(cancelBtn);

        return profilePanel;
    }
}