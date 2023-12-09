package Transylvania;

import Transylvania.database.DBControls;
import Transylvania.database.DbConnections;
import Transylvania.pages.LoginSignUp.LoginSignupPage;
import Transylvania.pages.HomePage.HomeContent;
import Transylvania.pages.ReceiptPage.historyContent;
import Transylvania.pages.SearchPage.SearchContent;
import Transylvania.style.NoScalingIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Main extends JFrame{
     static JPanel  content;
     public static JLabel homeIcon , searchIcon , receiptIcon ;


    public static void main(String[] args) {
        Main main = new Main("Username");
//        LoginSignupPage start = new LoginSignupPage();
        DBControls.openConnection();
    }

    public Main(String username){
        //Images
        NoScalingIcon homeIconImg = new NoScalingIcon(env.LoadImage("assets/home-regular-240 (1).png" , 36 , 36));
        NoScalingIcon homeIconClickedImg = new NoScalingIcon( env.LoadImage("assets/home-solid-240.png" , 36 , 36) );
        NoScalingIcon searchIconImg = new NoScalingIcon(env.LoadImage("assets/search-regular-240 (1).png" , 36 ,36));
        NoScalingIcon searchIconClickedImg = new NoScalingIcon(env.LoadImage("assets/search-solid-240.png" , 36 ,36 ));
        NoScalingIcon receiptIconImg = new NoScalingIcon(env.LoadImage("assets/receipt-regular-240.png" , 36 , 36 ));
        NoScalingIcon receiptIconClicked = new NoScalingIcon(env.LoadImage("assets/receipt-solid-240.png" , 36 , 36 ));



        JPanel mainPanel = new JPanel();
        JPanel sideBarPanel = sideBarPanel();
        JPanel navBarPanel = navBarPanel(username);
        JPanel homeContent = HomeContent.HomeContent();
        JPanel searchContent = SearchContent.findPanel();
        JPanel receiptContent = historyContent.historyPanel();
        content = homeContent;
        JPanel profilePanel = profilePanel();


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

        //sideBarIcons
         homeIcon = (JLabel) env.FindComponents(sideBarPanel , "homeIcon");
         searchIcon = (JLabel)env.FindComponents(sideBarPanel , "searchIcon");
         receiptIcon = (JLabel)env.FindComponents(sideBarPanel , "receiptIcon");



        //navBar Components
        JComponent profileIcon = env.FindComponents(navBarPanel , "userIcon");

        //MouseListeners

        //Profile Icon
        env.MouseListener(profileIcon , (MouseEvent e) ->{
           setBounds(env.WINDOW_POST_X-150 , env.WINDOW_POST_Y, env.FRAME_WIDTH+300 , env.FRAME_HEIGHT);
           mainPanel.add(profilePanel);
           JComponent exitProfile = env.FindComponents(profilePanel , "cancelProfileBtn");
           env.MouseListener(exitProfile , (MouseEvent b)->{
               setBounds(env.WINDOW_POST_X , env.WINDOW_POST_Y, env.FRAME_WIDTH , env.FRAME_HEIGHT);
               mainPanel.remove(profilePanel);
               return null ;
           });
           return null;
       });

        //SideBar Icons
        env.MouseListener(searchIcon , (MouseEvent e)->{
            //Icons
            homeIcon.setIcon(homeIconImg);
            searchIcon.setIcon(searchIconClickedImg);
            receiptIcon.setIcon(receiptIconImg);

            mainPanel.remove(content);
            content = searchContent;
            mainPanel.add(content);
            mainPanel.revalidate();
            mainPanel.repaint();
            content.setBounds(80 , 80 , env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);
            return null;
        });

        env.MouseListener(receiptIcon , (MouseEvent e)->{
            homeIcon.setIcon(homeIconImg);
            searchIcon.setIcon(searchIconImg);
            receiptIcon.setIcon(receiptIconClicked);

            mainPanel.remove(content);
            content = receiptContent;
            mainPanel.add(content);
            mainPanel.revalidate();
            mainPanel.repaint();
            content.setBounds(80 , 80 , env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);
            return null;
        });
        env.MouseListener(homeIcon , (MouseEvent e)->{
            homeIcon.setIcon(homeIconClickedImg);
            searchIcon.setIcon(searchIconImg);
            receiptIcon.setIcon(receiptIconImg);

            mainPanel.remove(content);
            content = homeContent;
            mainPanel.add(content);
            mainPanel.revalidate();
            mainPanel.repaint();
            content.setBounds(80 , 80 , env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);
            return null;
        });

    }







    public static JPanel sideBarPanel(){
        //Images
        NoScalingIcon homeIconClicked = new NoScalingIcon(env.LoadImage("assets/home-solid-240.png" , 36 , 36));
        NoScalingIcon searchIcon = new NoScalingIcon(env.LoadImage("assets/search-regular-240 (1).png" , 36 ,36));
        NoScalingIcon receiptIcon = new NoScalingIcon(env.LoadImage("assets/receipt-regular-240.png" , 36 , 36 ));


        //Components
        JPanel sideBarPanel = new JPanel();
        JLabel home = new JLabel(homeIconClicked);
        home.setName("homeIcon");
        JLabel search = new JLabel(searchIcon);
        search.setName("searchIcon");
        JLabel receipt = new JLabel(receiptIcon);
        receipt.setName("receiptIcon");

        //Bounds
        sideBarPanel.setBounds(-200 , 0 , 80 , env.FRAME_HEIGHT);
        home.setBounds(22 , 40  , 36 ,36);
        search.setBounds(22 , 112 , 36 ,36);
        receipt.setBounds(22 , 184 , 36 , 36);

        //adds
        sideBarPanel.add(home);
        sideBarPanel.add(search);
        sideBarPanel.add(receipt);

        //Backgrounds
        sideBarPanel.setBackground(Color.decode(env.NICE_RED));


        sideBarPanel.setLayout(null);
        return sideBarPanel;
    }

    public static JPanel navBarPanel(String username){
        //Images
        NoScalingIcon userImage = new NoScalingIcon(env.LoadImage("assets/user.png" , 35 , 35));

        //Components
        JPanel navBarPanel = new JPanel();
        JLabel userImageLabel = new JLabel(userImage);
        userImageLabel.setName("userIcon");
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

    //ProfilePanel
    public static JPanel profilePanel (){
        //Panels
        JPanel profilePanel =  new JPanel(null);
        profilePanel.setBounds(env.FRAME_WIDTH ,0, 300 , env.FRAME_HEIGHT);
        profilePanel.setBackground(Color.decode("#d6d6d6"));

        JPanel decorationPanel = new JPanel(null);
        decorationPanel.setBounds(profilePanel.getWidth() - 20 , 0 , 50 , env.FRAME_HEIGHT);
        decorationPanel.setBackground(Color.decode(env.NICE_RED));

        //Images
        NoScalingIcon editIcon = new NoScalingIcon(env.LoadImage("assets/edit-regular-240.png" , 20 , 20));

        //Profile Labels
        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setBounds(40 , 100 , 100 , 50);
        profileLabel.setFont(env.pixel24B);

        //Name Profile
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(50 , 151 , 100 , 25);
        nameLabel.setFont(env.pixel12);
        JLabel nameEdit = new JLabel(editIcon);
        nameEdit.setBounds(50 , 176 , 20 , 25 );
        JTextField nameValue = new JTextField("FELOS DOE");
        nameValue.setBounds(75 , 176 , 200 , 25);
        nameValue.setBackground(Color.decode("#d6d6d6"));
        nameValue.setEditable(false);
        nameValue.setBorder(null);
        nameValue.setFont(env.pixel16B);

        //Email Profile
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50 , 201 , 100 , 25);
        emailLabel.setFont(env.pixel12);
        JLabel emailEdit = new JLabel(editIcon);
        emailEdit.setBounds(50 , 226 , 20 , 25 );
        JTextField emailValue = new JTextField("felDoe@gmail.com");
        emailValue.setBounds(75 , 226 , 200 , 25);
        emailValue.setBackground(Color.decode("#d6d6d6"));
        emailValue.setEditable(false);
        emailValue.setBorder(null);
        emailValue.setFont(env.pixel16B);

        //Phone Profile
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(50 , 251 , 100 , 25);
        phoneLabel.setFont(env.pixel12);
        JLabel phoneEdit = new JLabel(editIcon);
        phoneEdit .setBounds(50 , 276 , 20 , 25 );
        JTextField phoneValue = new JTextField("081320901");
        phoneValue.setBounds(75 , 276 , 200 , 25);
        phoneValue.setBackground(Color.decode("#d6d6d6"));
        phoneValue.setEditable(false);
        phoneValue.setBorder(null);
        phoneValue.setFont(env.pixel16B);

        //Buttons
        JButton saveBtn = new JButton("SAVE CHANGES");
        saveBtn.setBounds(65 , 400 , 150 , 50);
        saveBtn.setBackground(Color.decode(env.NICE_RED));
        saveBtn.setForeground(Color.decode(env.MAIN_COLOR));
        saveBtn.setFont(env.pixel12B);
        saveBtn.setBorder(null);

        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setName("cancelProfileBtn");
        cancelBtn.setBounds(65 , 460 , 150 , 50);
        cancelBtn.setBackground(Color.decode(env.MAIN_COLOR));
        cancelBtn.setForeground(Color.decode(env.NICE_RED));
        cancelBtn.setFont(env.pixel12B);


        //Edit Listeners
        env.MouseListener(nameEdit , (MouseEvent e)->{
            nameValue.setEditable(true);
            nameValue.setBackground(Color.decode(env.MAIN_COLOR));
            return null;
        });
        env.MouseListener(emailEdit , (MouseEvent e)->{
            emailValue.setEditable(true);
            emailValue.setBackground(Color.decode(env.MAIN_COLOR));
            return null;
        });
        env.MouseListener(phoneEdit , (MouseEvent e)->{
            phoneValue.setEditable(true);
            phoneValue.setBackground(Color.decode(env.MAIN_COLOR));
            return null;
        });


        //Button Listeners
        //Save Button
        env.ActionListener(saveBtn , (ActionEvent e)->{
            return null ;
        });

        //Cancel Button
        env.ActionListener(cancelBtn , (ActionEvent e)->{
            nameValue.setText("FELOS DOE");
            emailValue.setText("felDoe@gmail.com");
            phoneValue.setText("081320901");
            nameValue.setEditable(false);
            nameValue.setBackground(Color.decode("#d6d6d6"));
            emailValue.setEditable(false);
            emailValue.setBackground(Color.decode("#d6d6d6"));
            phoneValue.setEditable(false);
            phoneValue.setBackground(Color.decode("#d6d6d6"));
            return null;
        });



        profilePanel.add(decorationPanel);
        profilePanel.add(profileLabel);
        profilePanel.add(nameLabel);
        profilePanel.add(nameEdit);
        profilePanel.add(nameValue);
        profilePanel.add(emailLabel);
        profilePanel.add(emailEdit);
        profilePanel.add(emailValue);
        profilePanel.add(phoneLabel);
        profilePanel.add(phoneEdit);
        profilePanel.add(phoneValue);
        profilePanel.add(saveBtn);
        profilePanel.add(cancelBtn);

        return profilePanel;
    }
}