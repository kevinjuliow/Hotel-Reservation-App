package org.example;

import org.example.pages.HomePage.HomeContent;
import org.example.pages.LoginSignUp.LoginSignupPage;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args) {
//        Main main = new Main("Username");
        LoginSignupPage start = new LoginSignupPage();
    }

    public Main(String username){
        JPanel mainPanel = new JPanel();
        JPanel sideBarPanel = sideBarPanel();
        JPanel navBarPanel = navBarPanel(username);
        JPanel content = HomeContent.HomeContent();


        mainPanel.setBackground(Color.decode(env.MAIN_COLOR));

        env.animate(sideBarPanel , new Point(0 , 0 ) , 20 , 1);
        env.animate(navBarPanel , new Point(80 , 0 ) , 20 , 1);
        env.animate(content , new Point(80 , 80 ) , 20 , 1);

        mainPanel.add(sideBarPanel);
        mainPanel.add(navBarPanel);
        mainPanel.add(content);


        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        setBounds(env.WINDOW_POST_X , env.WINDOW_POST_Y, env.FRAME_WIDTH , env.FRAME_HEIGHT);
        setUndecorated(true);
        setVisible(true);
    }

    public static JPanel sideBarPanel(){
        JPanel sideBarPanel = new JPanel();


        sideBarPanel.setBackground(Color.GRAY);
        sideBarPanel.setBounds(-200 , 0 , 80 , env.FRAME_HEIGHT);

        sideBarPanel.setLayout(null);
        return sideBarPanel;
    }

    public static JPanel navBarPanel(String username){
        //Images
        ImageIcon userImage = new ImageIcon(HomeContent.class.getClassLoader().getResource("assets/user.png"));
        userImage = new ImageIcon(userImage.getImage().getScaledInstance(35 , 35 , Image.SCALE_SMOOTH));

        //Components
        JPanel navBarPanel = new JPanel();
        JLabel userImageLabel = new JLabel(userImage);
        JLabel usernameLabel = new JLabel(username);

        //Backgrounds
        navBarPanel.setBackground(Color.decode(env.MAIN_COLOR));

        //Bounds
        navBarPanel.setBounds(200 , 0 , env.FRAME_WIDTH-80 , 80);
        usernameLabel.setBounds(navBarPanel.getWidth() - 180 , (navBarPanel.getHeight()/2)-10 , 100 , 30);
        userImageLabel.setBounds(usernameLabel.getX() + 85 , (navBarPanel.getHeight()/2)-10 , 35 , 35);


        //add
        navBarPanel.add(userImageLabel);
        navBarPanel.add(usernameLabel);

        navBarPanel.setLayout(null);
        return navBarPanel;
    }
}