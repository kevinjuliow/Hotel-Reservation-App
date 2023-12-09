package Transylvania.pages.ReceiptPage;

import Transylvania.style.RoundedBorder;
import Transylvania.env;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class historyContent {
    public static JPanel historyPanel() {
        JPanel panel3 = new JPanel(null);
        JPanel panel31 = new JPanel();
        panel31.setLayout(new BoxLayout(panel31, BoxLayout.Y_AXIS));

        panel3.setBounds(80, 80, env.FRAME_WIDTH-80, env.FRAME_HEIGHT-80);

        JScrollPane scrollPane = new JScrollPane(panel31);
        scrollPane.setBounds(55, 80, 1080, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel3.setBackground(Color.decode(env.MAIN_COLOR));
        panel31.setBackground(Color.decode("#FFFFFF"));

        for (int i = 0; i < 5; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setPreferredSize(new Dimension(900, 150));
            panel.setMaximumSize(new Dimension(900, 150));
            panel.setBackground(Color.decode(env.MAIN_COLOR));
            panel.setBorder(BorderFactory.createLineBorder(Color.decode("#D9D9D9"), 1));
            panel.setBorder(new RoundedBorder(20));


            JPanel panel311 = new JPanel();
            panel311.setPreferredSize(new Dimension(800, 50));
            panel311.setMaximumSize(new Dimension(800, 50));
            panel311.setBackground(Color.decode("#D9D9D9"));

            panel.add(Box.createVerticalGlue());
            panel.add(panel311);
            panel.add(Box.createVerticalGlue());

            panel31.add(panel);
            panel31.add(Box.createVerticalStrut(10)); // Add a vertical gap
        }

        //Scrollbar
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();

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
        //borders
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setViewportBorder(null);

        panel3.add(scrollPane);

        return panel3;
    }
}
