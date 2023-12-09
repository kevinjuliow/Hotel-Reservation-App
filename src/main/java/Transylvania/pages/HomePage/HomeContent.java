package Transylvania.pages.HomePage;

import Transylvania.env;
import Transylvania.style.NoScalingIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class HomeContent extends JFrame {
    public static JPanel HomeContent(){
        //Images
        NoScalingIcon promoImage = new NoScalingIcon( env.LoadImage("assets/img_1.png", 1120 , 200));

        //Components
        JPanel homeContentPanel = new JPanel();
        JLabel imagePromo = new JLabel(promoImage);
        JLabel welcomeLabel = new JLabel("Welcome");
        JLabel topRatedLabel = new JLabel("Top Rated");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));


        //Bounds
        homeContentPanel.setBounds(400 , 80 , env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);
        welcomeLabel.setBounds(29 , 33 , 265 , 53);
        imagePromo.setBounds(29 , 100, 1145 , 200);
        scrollPane.setBounds(31 , 370 , 1139 , 250);
        topRatedLabel.setBounds(31 , 325 , 200 , 35);

        //Fonts
        welcomeLabel.setFont(env.pixel36);
        topRatedLabel.setFont(env.pixel24);


        //Add
        homeContentPanel.add(imagePromo);
        homeContentPanel.add(welcomeLabel);
        homeContentPanel.add(scrollPane);
        homeContentPanel.add(topRatedLabel);


        // Add some panels to the content panel
        for (int i = 0; i < 6; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(250, scrollPane.getHeight()));
            panel.setBackground(Color.decode("#D9D9D9"));
            contentPanel.add(panel);

            // Add a 10-pixel gap
            if (i < 5) {
                contentPanel.add(Box.createHorizontalStrut(10));
            }
        }

        // Add the content panel to the JScrollPane
        scrollPane.setViewportView(contentPanel);

        //Scrollbar
        JScrollBar scrollBar = scrollPane.getHorizontalScrollBar();

        //ScrollBar Customization
        scrollBar.setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
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
