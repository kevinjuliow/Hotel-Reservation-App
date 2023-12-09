package Transylvania.pages.SearchPage;

import Transylvania.pages.LoginSignUp.LoginSignupPanels;
import Transylvania.env;
import Transylvania.style.NoScalingIcon;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author ASUS
 */
public class SearchContent extends JFrame{
    public static void main(String[] args) {
        SearchContent main = new SearchContent();
        main.setVisible(true);
        
    }
    
    public SearchContent() {
        setSize(env.FRAME_WIDTH, env.FRAME_HEIGHT);
        setTitle("Test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setUndecorated(true);
        
        
        Container contentPane = getContentPane();
        JPanel top = new JPanel();
        top.setBackground(Color.pink);
        
        JPanel side = new JPanel();
        side.setBackground(Color.red);
        
        JPanel findPanel = findPanel();

        
        contentPane.setLayout(null);
        contentPane.add(findPanel);
//        contentPane.setLayout(new BorderLayout());
//        contentPane.add(top, BorderLayout.NORTH);
//        contentPane.add(side, BorderLayout.WEST);
//        contentPane.add(findPanel, BorderLayout.CENTER);
        contentPane.setBackground(Color.red);
    }
    
    public static JPanel findPanel() {
        JPanel besar = new JPanel(null);
        besar.setBackground(Color.decode(env.MAIN_COLOR));
        besar.setBounds(80, 80, env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);

        //Images
        NoScalingIcon filterImg = new NoScalingIcon(env.LoadImage("assets/filter.png" , 20 ,20));

        NoScalingIcon star3 = new NoScalingIcon(env.LoadImage("assets/three-star.png" , 50 , 15));
        NoScalingIcon star4 =  new NoScalingIcon(env.LoadImage("assets/four-star.png" , 70 , 15));
        NoScalingIcon star5 =  new NoScalingIcon(env.LoadImage("assets/five-star.png" , 90 , 15));

        //Bagian searchBar
        JPanel bar = new JPanel();
        JLabel teks = new JLabel("Cari berdasarkan nama hotel");
        teks.setFont(env.pixel16);
        teks.setBounds(20, 20, 770, 20);
        
        JTextField searchBar = new JTextField();
        searchBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        searchBar.setBounds(20, 50, 770, 40);
        
        JButton bFind = new JButton("Find");
        bFind.setBackground(Color.decode(env.DARK_COLOR));
        bFind.setFont(env.pixel16);
        bFind.setForeground(Color.white);
        bFind.setBounds(790, 50, 80, 40);
        
        bar.setLayout(null);
        bar.add(teks);
        bar.add(searchBar);
        bar.add(bFind);
        bar.setBackground(Color.decode(env.MAIN_COLOR));

        bar.setBounds(10, 10, 900, 100);
        besar.add(bar);
        
        //Bagian filter
        JPanel filter = new JPanel();
        
        JLabel pFilter = new JLabel(filterImg);
        JLabel jFilter = new JLabel("Filter :");
        JCheckBox jcbBinLima = new JCheckBox();
        JCheckBox jcbBinEmpat = new JCheckBox();
        JCheckBox jcbBinTiga = new JCheckBox();
        JLabel star3Label = new JLabel(star3);
        JLabel star4label = new JLabel(star4);
        JLabel star5label = new JLabel(star5);

        jcbBinLima.setEnabled(true);
        jcbBinEmpat.setEnabled(true);
        jcbBinTiga.setEnabled(true);

        
        pFilter.setBounds(20,20,100,30);
        jFilter.setBounds(80,20,100,30);
        jcbBinLima.setBounds(20,60,20,30);
        jcbBinEmpat.setBounds(20,100,20,30);
        jcbBinTiga.setBounds(20,140,20,30);
        star3Label.setBounds(46 ,145 , 50 , 20 );
        star4label.setBounds(46,105,70,20);
        star5label.setBounds(42, 65, 100,20);
        
        filter.setLayout(null);
        filter.add(pFilter);
        filter.add(jFilter);
        filter.add(jcbBinLima);
        filter.add(jcbBinEmpat);
        filter.add(jcbBinTiga);
        filter.add(star3Label);
        filter.add(star4label);
        filter.add(star5label);


        filter.setBounds(910, 10, 300, 600);
        besar.add(filter);
        
        //Panel Hotel-Hotel
        JPanel hotel = new JPanel(new GridLayout(0, 3, 10, 10));

        JScrollPane scrollPane = new JScrollPane(hotel);
        scrollPane.setBounds(10, 120, 880, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(250, 200));
            panel.setBackground(Color.decode("#D9D9D9"));
            hotel.add(panel);

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

        besar.add(scrollPane);
        
        besar.setBounds(80, 80, env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);
        return besar;
    }
}
