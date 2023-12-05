package org.example.pages.LoginSignUp;

import org.example.Main;
import org.example.env;
import org.example.style.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginSignupPanels {

    public static JPanel loginPanel(){
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds((env.FRAME_WIDTH/2) , 0 , env.FRAME_WIDTH/2 , env.FRAME_HEIGHT);

        //Components
        JTextField emailTf = new JTextField();
        JPasswordField passwordTf = new JPasswordField();
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        JLabel signUpLabel = new JLabel("don't have an account?");
        signUpLabel.setName("signUpLabel");
        JButton loginBtn = new JButton("Login");
        loginBtn.setName("loginBtn");

        //Bounds
        emailTf.setBounds(170 , 315 , 320 , 54);
        passwordTf.setBounds(170 , 394 , 320 , 54);
        emailLabel.setBounds(170 , 290 , 100 , 20);
        passwordLabel.setBounds(170 , 369 , 100 , 20);
        loginBtn.setBounds(235 , 464 , 200 , 40);
        signUpLabel.setBounds(272 , 520 , 200 , 20);


        //Backgrounds
        loginBtn.setBackground(Color.decode(env.SECONDARY_COLOR));
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
        loginPanel.add(loginBtn);
        loginPanel.add(signUpLabel);

        //Fonts
        emailLabel.setFont(env.pixel9);
        passwordLabel.setFont(env.pixel9);
        loginBtn.setFont(env.pixel12B);
        signUpLabel.setFont(env.pixel12);


        emailTf.setEditable(true);
        loginPanel.setLayout(null);


        //On Click Login Button When Pressing Enter
        enterClick(loginBtn , loginBtn);
        enterClick(passwordTf , loginBtn);
        enterClick(emailTf , loginBtn);

        return loginPanel;
    }



    public static JPanel imagePanel(){
        JPanel imgPanel = new JPanel();
        imgPanel.setBounds(0 , 0 , env.FRAME_WIDTH/2 , env.FRAME_HEIGHT);

        //Images
        ImageIcon imageIcon = new ImageIcon(LoginSignupPanels.class.getClassLoader().getResource("assets/img.png"));
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(env.FRAME_WIDTH/2 , env.FRAME_HEIGHT , Image.SCALE_SMOOTH));

        //Components
        JLabel imageLabel = new JLabel(imageIcon);


        //Bounds
        imageLabel.setBounds(0 , 0 , env.FRAME_WIDTH/2 , env.FRAME_HEIGHT);


        //Backgrounds
//        imgPanel.setBackground(Color.decode(env.SECONDARY_COLOR));

        //imgPanel add
        imgPanel.add(imageLabel);

        return imgPanel;
    }




    public static JPanel signUpPanel(){
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBounds(env.FRAME_WIDTH/2 , 0 , env.FRAME_WIDTH/2 , env.FRAME_HEIGHT);

        //Components
        JTextField emailTf = new JTextField();
        JPasswordField passwordTf = new JPasswordField();
        JTextField noTelpTf = new JTextField();
        JTextField fullNameTf = new JTextField();
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        JLabel noTelpLabel = new JLabel("Phone Number");
        JLabel fullNameLabel = new JLabel("Full Name");
        JLabel loginLabel = new JLabel("already have an account?");
        loginLabel.setName("loginLabel");
        JButton signupBtn = new JButton("Sign Up");

        //Bounds
        noTelpLabel.setBounds(170 , 157 , 100 , 20);
        noTelpTf.setBounds(170 , 182 , 320 , 54);

        fullNameLabel.setBounds(170 , 239 , 100 , 20);
        fullNameTf.setBounds(170 , 264 , 320 , 54);

        emailLabel.setBounds(170 , 318 , 100 , 20);
        emailTf.setBounds(170 , 343 , 320 , 54);

        passwordLabel.setBounds(170 , 397 , 100 , 20);
        passwordTf.setBounds(170 , 422 , 320 , 54);


        signupBtn.setBounds(235 , 492 , 200 , 40);
        loginLabel.setBounds(272 , 548 , 200 , 20);

        //Background
        signupBtn.setBackground(Color.decode(env.SECONDARY_COLOR));
        signUpPanel.setBackground(Color.decode(env.MAIN_COLOR));


        //ForeGround
        signupBtn.setForeground(Color.decode(env.MAIN_COLOR));


        //Fonts
        emailLabel.setFont(env.pixel9);
        passwordLabel.setFont(env.pixel9);
        fullNameLabel.setFont(env.pixel9);
        noTelpLabel.setFont(env.pixel9);
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
        signUpPanel.add(signupBtn);
        signUpPanel.add(loginLabel);


        //KeyListeners
        noTelpTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isDigit(e.getKeyChar())) {}
                else e.consume();
            }
        });
        fullNameTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isDigit(e.getKeyChar())) e.consume();
            }
        });


        enterClick(signupBtn , signupBtn);
        enterClick(fullNameTf , signupBtn);
        enterClick(noTelpTf , signupBtn);
        enterClick(emailTf , signupBtn);
        enterClick(passwordTf , signupBtn);
        return signUpPanel;

    }

    public static void enterClick (JComponent a , JButton btn ){
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    btn.doClick();
                }
            }
        });
    }
}
