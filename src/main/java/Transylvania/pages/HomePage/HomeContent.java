package Transylvania.pages.HomePage;

import Transylvania.Classes.Hotel;
import Transylvania.database.DBControls;
import Transylvania.env;
import Transylvania.pages.Detail.HotelDetail;
import Transylvania.pages.LoginSignUp.LoginSignupPage;
import Transylvania.style.NoScalingIcon;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class HomeContent extends JFrame {

    public static JPanel HomeContent() throws IOException {
        //Fetching Hotels
        List<Hotel> hotelList = DBControls.GETHotel();
        env.hotelListGlobal = hotelList;

        //Images
        NoScalingIcon promoImage = new NoScalingIcon(env.LoadImage("assets/img_1.png", 1120, 200));
        NoScalingIcon star3 = new NoScalingIcon(env.LoadImage("assets/three-star.png", 70, 20));
        NoScalingIcon star4 = new NoScalingIcon(env.LoadImage("assets/four-star.png", 90, 20));
        NoScalingIcon star5 = new NoScalingIcon(env.LoadImage("assets/five-star.png", 110, 20));

        //Components
        JPanel homeContentPanel = new JPanel();
        JLabel imagePromo = new JLabel(promoImage);
        JLabel welcomeLabel = new JLabel("Welcome");
        JLabel topRatedLabel = new JLabel("Top Rated");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JPanel contentPanel = new JPanel(null);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(Color.decode(env.MAIN_COLOR));

        //Bounds
        homeContentPanel.setBounds(400, 80, env.FRAME_WIDTH - 80, env.FRAME_HEIGHT - 80);
        welcomeLabel.setBounds(29, 33, 265, 53);
        imagePromo.setBounds(29, 100, 1145, 200);
        scrollPane.setBounds(31, 370, 1139, 250);
        topRatedLabel.setBounds(31, 325, 200, 35);

        //Fonts
        welcomeLabel.setFont(env.pixel36B);
        topRatedLabel.setFont(env.pixel24B);

        //Add
        homeContentPanel.add(imagePromo);
        homeContentPanel.add(welcomeLabel);
        homeContentPanel.add(scrollPane);
        homeContentPanel.add(topRatedLabel);

        for (int i = 0; i < hotelList.size(); i++) {
            if (hotelList.get(i).getStar() == 5) {
                final int index = i;
                JPanel panel = new JPanel(null);
                panel.setPreferredSize(new Dimension(270, 470));
                panel.setMaximumSize(new Dimension(270, 470));
                panel.setBackground(Color.decode(env.MAIN_COLOR));

                Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode(env.NICE_RED));
                panel.setBorder(border);

                //Text Side
                JPanel textPanel = new JPanel(null);
                textPanel.setBounds(14, 175, 241, 70);
                textPanel.setBackground(Color.decode(env.MAIN_COLOR));
//                textPanel.setBackground(new Color(0, 0, 0, 80));
                panel.add(textPanel);

                int hotelStar = hotelList.get(i).getStar();

                JLabel hotelStarsLabel = new JLabel(new NoScalingIcon(hotelStar == 3 ? star3 : hotelStar == 4 ? star4 : star5));

                hotelStarsLabel.setBounds(0, 28, 241, 20);
                hotelStarsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                textPanel.add(hotelStarsLabel);

                JLabel hotelName = new JLabel(hotelList.get(i).getHotelName(), SwingConstants.CENTER);
                hotelName.setBounds(0, 0, 241, 20);
                hotelName.setBackground(Color.decode(env.MAIN_COLOR));
                hotelName.setFont(env.pixel14B);
                textPanel.add(hotelName);

                //Images
                ImageIcon image = hotelList.get(i).getImage();
                
                env.LoadImageIcon(image, 30, 30);
                NoScalingIcon hotelImage = new NoScalingIcon(image);
//                NoScalingIcon hotelImage = new NoScalingIcon(env.LoadImage("assets/example.png", 271, 190));
                JLabel hotelImageLabel = new JLabel(hotelImage);
                hotelImageLabel.setBounds(0, -5, 271, 190);
                panel.add(hotelImageLabel);

                contentPanel.add(panel);

                // Add a 10-pixel gap
                if (i < hotelList.size() - 1) {
                    contentPanel.add(Box.createHorizontalStrut(5));
                }

                panel.addMouseListener(new env.CursorPointerStyle(panel));

                env.MouseListener(panel, (MouseEvent e) -> {
                    HotelDetail hotelDetail = new HotelDetail(hotelList.get(index));
                    LoginSignupPage.mainFrame.dispose();
                    return null;
                });
            }
        }

        // Add the content panel to the JScrollPane
        scrollPane.setViewportView(contentPanel);

        //Scrollbar
        JScrollBar scrollBar = scrollPane.getHorizontalScrollBar();

        //ScrollBar Customization
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.DARK_GRAY;
                this.trackColor = Color.decode(env.MAIN_COLOR);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        //backgrounds
        homeContentPanel.setBackground(Color.decode(env.MAIN_COLOR));
        scrollPane.getViewport().setBackground(Color.decode(env.MAIN_COLOR));


        //borders
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setViewportBorder(null);

        homeContentPanel.setLayout(null);

        return homeContentPanel;
    }
}
