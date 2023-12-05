package org.example.pages.LoginSignUp;

import org.example.Main;
import org.example.env;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginSignupPage extends JFrame{
    JLabel signUpLabel , loginLabel;
    JButton loginBtn ;
    public LoginSignupPage() {
        //Panels
        JPanel mainPanel = new JPanel();
        setBounds(env.WINDOW_POST_X , env.WINDOW_POST_Y  , env.FRAME_WIDTH , env.FRAME_HEIGHT);
        JPanel imgPanel = LoginSignupPanels.imagePanel();
        JPanel loginPanel = LoginSignupPanels.loginPanel();
        JPanel signUpPanel = LoginSignupPanels.signUpPanel();

        //Background
        mainPanel.setBackground(Color.decode(env.MAIN_COLOR));

        //mainPanel add
        mainPanel.add(loginPanel);
        mainPanel.add(imgPanel);

        //loginPanel Components
        Component[] loginComponents = loginPanel.getComponents();
        for (Component c :
                loginComponents) {
            if ( c.getName() != null && c.getName().equals("signUpLabel") ){
                signUpLabel = (JLabel) c ;
            }
            else if (c.getName() != null && c.getName().equals("loginBtn")){
                loginBtn = (JButton) c ;
            }
        }

        //signupPanel Components
        Component[] signupComponents = signUpPanel.getComponents();
        for (Component c :
                signupComponents) {
            if ( c.getName() != null && c.getName().equals("loginLabel") ){
                loginLabel = (JLabel) c ;
            }
        }


        //ActionListeners
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                env.animate(imgPanel, new Point(-2000, 0), 20, 2);
                env.animate2(loginPanel, new Point(2000, 0), 20, 2, () -> {
                        Main main = new Main("John Doe");
                        dispose();
                    });
            }
        });


        signUpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.remove(loginPanel);
                loginPanel.setBounds((env.FRAME_WIDTH/2)/5 , 0 , env.FRAME_WIDTH/2 , env.FRAME_HEIGHT);
                env.animate(imgPanel , new Point(env.FRAME_WIDTH/2 , 0) , 20 , 1);
                signUpPanel.setBounds(env.FRAME_WIDTH/2 , 0 , env.FRAME_WIDTH , env.FRAME_HEIGHT);
                mainPanel.add(signUpPanel);
                env.animate(signUpPanel , new Point(0 , 0) , 20 , 1);
            }
            @Override
            public void mousePressed(MouseEvent e) {signUpLabel.setForeground(Color.decode(env.SECONDARY_COLOR));}
            @Override
            public void mouseReleased(MouseEvent e) {signUpLabel.setForeground(Color.BLACK);}
            @Override
            public void mouseEntered(MouseEvent e) {signUpLabel.setForeground(Color.decode(env.SECONDARY_COLOR));}
            @Override
            public void mouseExited(MouseEvent e) {signUpLabel.setForeground(Color.BLACK);}
        });
        loginLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.remove(signUpPanel);
                env.animate(imgPanel , new Point(0, 0) , 20 , 1);
                loginPanel.setBounds((env.FRAME_WIDTH/2)/5 , 0 , env.FRAME_WIDTH , env.FRAME_HEIGHT);
                mainPanel.add(loginPanel);
                env.animate(loginPanel , new Point(env.FRAME_WIDTH/2 , 0) , 20 , 1);
            }
            @Override
            public void mousePressed(MouseEvent e) {loginLabel.setForeground(Color.decode(env.SECONDARY_COLOR));}
            @Override
            public void mouseReleased(MouseEvent e) {loginLabel.setForeground(Color.BLACK);}
            @Override
            public void mouseEntered(MouseEvent e) {loginLabel.setForeground(Color.decode(env.SECONDARY_COLOR));}
            @Override
            public void mouseExited(MouseEvent e) {loginLabel.setForeground(Color.BLACK);}
        });


        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        setUndecorated(true);
        setVisible(true);

    }





}
