package Transylvania.pages.LoginSignUp;

import Transylvania.database.DBControls;
import Transylvania.env;
import Transylvania.style.NoScalingIcon;
import Transylvania.style.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginSignupPanels {

    public static JPanel loginPanel() {
        JPanel loginPanel = new JPanel(null);
        loginPanel.setBounds((env.FRAME_WIDTH / 2), 0, env.FRAME_WIDTH / 2, env.FRAME_HEIGHT);

        //Images
        NoScalingIcon closeImg = new NoScalingIcon(env.LoadImage("assets/x-regular-240.png", 36, 36));
        NoScalingIcon minimizeImg = new NoScalingIcon(env.LoadImage("assets/minimize-button.png", 24, 6));

        //Components
        JLabel emailLabel = new JLabel("Email");
        JTextField emailTf = new JTextField();
        emailTf.setName("emailTf");

        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordTf = new JPasswordField();
        passwordTf.setName("passwordTf");

        JLabel errorInformationLabel = new JLabel("", SwingConstants.CENTER);
        errorInformationLabel.setName("errorInformationLabelLogin");

        JLabel signUpLabel = new JLabel("don't have an account?");
        signUpLabel.setName("signUpLabel");
        JButton loginBtn = new JButton("Login");
        loginBtn.setName("loginBtn");

        JLabel closeBtn = new JLabel(closeImg);
        JLabel minimizeBtn = new JLabel(minimizeImg);
        minimizeBtn.setName("minimizeBtn");

        //Bounds
        emailLabel.setBounds(170, 254, 100, 20);
        emailTf.setBounds(170, 286, 320, 54);

        passwordLabel.setBounds(170, 350, 100, 20);
        passwordTf.setBounds(170, 382, 320, 54);

        errorInformationLabel.setBounds(72, 440, 500, 28);
        errorInformationLabel.setForeground(Color.decode(env.NICE_RED));

        loginBtn.setBounds(224, 500, 200, 40);
        signUpLabel.setBounds(272, 550, 200, 20);

        closeBtn.setBounds(loginPanel.getWidth() - 70, 20, 48, 48);
        minimizeBtn.setBounds(loginPanel.getWidth() - 106, 24, 48, 48);


        //Backgrounds
        loginBtn.setBackground(Color.decode(env.NICE_RED));
        loginPanel.setBackground(Color.decode(env.MAIN_COLOR));

        //Foregrounds
        loginBtn.setForeground(Color.decode(env.MAIN_COLOR));

        //Borders
        emailTf.setBorder(new RoundedBorder(20));
        passwordTf.setBorder(new RoundedBorder(20));

        //loginPanel add
        loginPanel.add(emailTf);
        loginPanel.add(passwordTf);
        loginPanel.add(emailLabel);
        loginPanel.add(passwordLabel);
        loginPanel.add(errorInformationLabel);
        loginPanel.add(loginBtn);
        loginPanel.add(signUpLabel);
        loginPanel.add(closeBtn);
        loginPanel.add(minimizeBtn);

        //Fonts
        emailLabel.setFont(env.pixel14);
        passwordLabel.setFont(env.pixel14);
        loginBtn.setFont(env.pixel12B);
        signUpLabel.setFont(env.pixel12);


        emailTf.setEditable(true);
        loginPanel.setLayout(null);


        //On Click Login Button When Pressing Enter
        enterClick(loginBtn, loginBtn);
        enterClick(passwordTf, loginBtn);
        enterClick(emailTf, loginBtn);

        //Action Listeners
        env.MouseListener(closeBtn, (MouseEvent e) -> {
            DBControls.closeConnection();
            System.exit(0);
            return null;
        });

        return loginPanel;
    }


    public static JPanel imagePanel() {
        JPanel imgPanel = new JPanel(null);
        imgPanel.setBounds(0, 0, env.FRAME_WIDTH / 2, env.FRAME_HEIGHT);
        //Images
        NoScalingIcon imageIcon = new NoScalingIcon(env.LoadImage("assets/img.png", env.FRAME_WIDTH / 2, env.FRAME_HEIGHT));
        //Components
        JLabel imageLabel = new JLabel(imageIcon);
        //Bounds
        imageLabel.setBounds(0, 0, env.FRAME_WIDTH / 2, env.FRAME_HEIGHT);
        //imgPanel add
        imgPanel.add(imageLabel);
        return imgPanel;
    }

    public static JPanel signUpPanel() {
        JPanel signUpPanel = new JPanel(null);
        signUpPanel.setBounds(env.FRAME_WIDTH / 2, 0, env.FRAME_WIDTH / 2, env.FRAME_HEIGHT);

        //Images
        NoScalingIcon closeImg = new NoScalingIcon(env.LoadImage("assets/x-regular-240.png", 36, 36));

        //Components
        JTextField emailTf = new JTextField();
        emailTf.setName("emailTf");
        JPasswordField passwordTf = new JPasswordField();
        passwordTf.setName("passwordTf");
        JTextField noTelpTf = new JTextField();
        noTelpTf.setName("noTelpTf");
        JTextField fullNameTf = new JTextField();
        fullNameTf.setName("fullNameTf");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        JLabel noTelpLabel = new JLabel("Phone Number");
        JLabel fullNameLabel = new JLabel("Full Name");

        JLabel errorInformationLabel = new JLabel("", SwingConstants.CENTER);
        errorInformationLabel.setName("errorInformationLabelSignUp");

        JLabel loginLabel = new JLabel("already have an account?");
        loginLabel.setName("loginLabel");
        JButton signupBtn = new JButton("Sign Up");
        signupBtn.setName("signUpBtn");
        JLabel closeBtn = new JLabel(closeImg);
//        JLabel minimizeBtn = new JLabel(minimizeImg);
//        minimizeBtn.setName("minimizeBtn");

        //Bounds
        fullNameLabel.setBounds(170, 120, 100, 20);
        fullNameTf.setBounds(170, 151, 320, 54);

        noTelpLabel.setBounds(170, 218, 100, 20);
        noTelpTf.setBounds(170, 249, 320, 54);

        emailLabel.setBounds(170, 316, 100, 20);
        emailTf.setBounds(170, 346, 320, 54);

        passwordLabel.setBounds(170, 416, 100, 20);
        passwordTf.setBounds(170, 446, 320, 54);

        errorInformationLabel.setBounds(72, 505, 500, 28);
        errorInformationLabel.setForeground(Color.decode(env.NICE_RED));

        signupBtn.setBounds(224, 560, 200, 40);
        loginLabel.setBounds(260, 616, 200, 20);

        closeBtn.setBounds(signUpPanel.getWidth() - 70, 20, 48, 48);
//        minimizeBtn.setBounds(signUpPanel.getWidth() - 106 , 24 , 48 , 48);

        //Background
        signupBtn.setBackground(Color.decode(env.NICE_RED));
        signUpPanel.setBackground(Color.decode(env.MAIN_COLOR));

        //ForeGround
        signupBtn.setForeground(Color.decode(env.MAIN_COLOR));

        //Fonts
        emailLabel.setFont(env.pixel14);
        passwordLabel.setFont(env.pixel14);
        fullNameLabel.setFont(env.pixel14);
        noTelpLabel.setFont(env.pixel14);
        signupBtn.setFont(env.pixel12B);
        loginLabel.setFont(env.pixel12);

        //Borders
        emailTf.setBorder(new RoundedBorder(20));
        passwordTf.setBorder(new RoundedBorder(20));
        fullNameTf.setBorder(new RoundedBorder(20));
        noTelpTf.setBorder(new RoundedBorder(20));

        //signUpPanel add
        signUpPanel.add(fullNameTf);
        signUpPanel.add(fullNameLabel);
        signUpPanel.add(noTelpLabel);
        signUpPanel.add(noTelpTf);
        signUpPanel.add(emailTf);
        signUpPanel.add(passwordTf);
        signUpPanel.add(emailLabel);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(errorInformationLabel);
        signUpPanel.add(signupBtn);
        signUpPanel.add(loginLabel);
        signUpPanel.add(closeBtn);
//        signUpPanel.add(minimizeBtn);


        //KeyListeners
        noTelpTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isDigit(e.getKeyChar())) {
                } else e.consume();
            }
        });

        fullNameTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isDigit(e.getKeyChar())) e.consume();
            }
        });

        //Action Listeners
        env.MouseListener(closeBtn, (MouseEvent e) -> {
            DBControls.closeConnection();
            System.exit(0);
            return null;
        });

        enterClick(signupBtn, signupBtn);
        enterClick(fullNameTf, signupBtn);
        enterClick(noTelpTf, signupBtn);
        enterClick(emailTf, signupBtn);
        enterClick(passwordTf, signupBtn);
        return signUpPanel;
    }

    public static void enterClick(JComponent a, JButton btn) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn.doClick();
                }
            }
        });
    }
}
