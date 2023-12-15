package Transylvania.pages.SearchPage;

import Transylvania.Classes.Hotel;
import Transylvania.Classes.User;
import Transylvania.Main;
import Transylvania.database.DBControls;
import Transylvania.env;
import Transylvania.pages.Detail.HotelDetail;
import Transylvania.style.NoScalingIcon;
import Transylvania.style.RoundedBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import static Transylvania.database.DBControls.findHotelStar;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchContent extends JFrame{
    public static NoScalingIcon star3 = new NoScalingIcon(env.LoadImage("assets/three-star.png" , 50 , 15));
    public static NoScalingIcon star4 =  new NoScalingIcon(env.LoadImage("assets/four-star.png" , 70 , 15));
    public static NoScalingIcon star5 =  new NoScalingIcon(env.LoadImage("assets/five-star.png" , 90 , 15));

    public static List<Hotel> hotelList;

    public static void main(String[] args) throws IOException {
//        Main main = new Main(new User("" , "" , "" , "", "" ));
    }

    public SearchContent() throws IOException {
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
        contentPane.setBackground(Color.red);
    }

    public static JPanel findPanel() throws IOException {
        hotelList = DBControls.GETHotel();
        JPanel besar = new JPanel(null);
        besar.setBackground(Color.decode(env.MAIN_COLOR));
        besar.setBounds(80, 80, env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);

        //Images
        NoScalingIcon filterImg = new NoScalingIcon(env.LoadImage("assets/filter.png" , 20 ,20));


        //Bagian searchBar
        JPanel bar = new JPanel();
        JLabel teks = new JLabel("Cari berdasarkan nama hotel");
        teks.setFont(env.pixel16);
        teks.setBounds(20, 20, 770, 20);

        JTextField searchBar = new JTextField();
        searchBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        searchBar.setBounds(50, 50, 680, 40);
//        searchBar.setBorder(new RoundedBorder(20));


        JButton bFind = new JButton("Find");
        bFind.setBackground(Color.decode(env.DARK_COLOR));
        bFind.setFont(env.pixel16);
        bFind.setForeground(Color.white);
        bFind.setBounds(730, 50, 80, 40);

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
        JRadioButton jcbBinLima = new JRadioButton();
        JRadioButton  jcbBinEmpat =  new JRadioButton();
        JRadioButton  jcbBinTiga = new  JRadioButton();
        JLabel clearLabel = new JLabel("Clear Selection");
        clearLabel.setFont(env.pixel12);
        clearLabel.setForeground(Color.decode(env.LIGHT_GRAY));
        JLabel star3Label = new JLabel(star3);
        JLabel star4label = new JLabel(star4);
        JLabel star5label = new JLabel(star5);

        jcbBinLima.setEnabled(true);
        jcbBinEmpat.setEnabled(true);
        jcbBinTiga.setEnabled(true);

        ButtonGroup filterGroup = new ButtonGroup();
        filterGroup.add(jcbBinLima);
        filterGroup.add(jcbBinEmpat);
        filterGroup.add(jcbBinTiga);


        pFilter.setBounds(20,20,100,30);
        jFilter.setBounds(80,20,100,30);
        jcbBinLima.setBounds(20,60,20,30);
        jcbBinEmpat.setBounds(20,100,20,30);
        jcbBinTiga.setBounds(20,140,20,30);
        clearLabel.setBounds(20 , 185 , 120 , 20);
        star3Label.setBounds(46 ,145 , 50 , 20 );
        star4label.setBounds(46,105,70,20);
        star5label.setBounds(42, 65, 100,20);

        filter.setLayout(null);
        filter.add(pFilter);
        filter.add(jFilter);
        filter.add(jcbBinLima);
        filter.add(jcbBinEmpat);
        filter.add(jcbBinTiga);
        filter.add(clearLabel);
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

        //Fitler Action Listeners
        env.RadioButtonListener(jcbBinLima , (ActionEvent e)->{
            if (jcbBinLima.isSelected()){
                searchBar.setText("");
                try {
                    hotelList =  DBControls.findHotelStar(5);
                } catch (IOException ex) {
                    Logger.getLogger(SearchContent.class.getName()).log(Level.SEVERE, null, ex);
                }
                hotel.removeAll();
                hotelUi( hotelList , hotel);
                hotel.revalidate();
                hotel.repaint();
            }
            return null ;
        });
        env.RadioButtonListener(jcbBinEmpat , (ActionEvent e)->{
            if (jcbBinEmpat.isSelected()){
                searchBar.setText("");
                try {
                    hotelList =  DBControls.findHotelStar(4);
                } catch (IOException ex) {
                    Logger.getLogger(SearchContent.class.getName()).log(Level.SEVERE, null, ex);
                }
                hotel.removeAll();
                hotelUi( hotelList , hotel);
                hotel.revalidate();
                hotel.repaint();
            }
            return null ;
        });
        env.RadioButtonListener(jcbBinTiga , (ActionEvent e)->{
            if (jcbBinTiga.isSelected()){
                searchBar.setText("");
                try {
                    hotelList =  DBControls.findHotelStar(3);
                } catch (IOException ex) {
                    Logger.getLogger(SearchContent.class.getName()).log(Level.SEVERE, null, ex);
                }
                hotel.removeAll();
                hotelUi( hotelList , hotel);
                hotel.revalidate();
                hotel.repaint();
            }
            return null ;
        });
        clearLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchBar.setText("");
                try {
                    hotelList =  DBControls.GETHotel();
                } catch (IOException ex) {
                    Logger.getLogger(SearchContent.class.getName()).log(Level.SEVERE, null, ex);
                }
                hotel.removeAll();
                hotelUi( hotelList , hotel);
                hotel.revalidate();
                hotel.repaint();
                filterGroup.clearSelection();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                clearLabel.setForeground(Color.decode(env.NICE_RED));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                clearLabel.setForeground(Color.decode(env.LIGHT_GRAY));
            }
        });
        clearLabel.addMouseListener(new env.CursorPointerStyle(clearLabel));


        //searchButton
        env.ActionListener(bFind , (ActionEvent e)->{
            try {
                hotelList = DBControls.findHotel(searchBar.getText());
                hotel.removeAll();
                hotelUi( hotelList , hotel);
                hotel.revalidate();
                hotel.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return null ;
        });

        //Enter doClick
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    bFind.doClick();
                }
            }
        });


        hotelUi( hotelList, hotel);

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

    public static void hotelUi(List<Hotel> hotelList, JPanel hotel){
        for (int i = 0; i < hotelList.size(); i++) {
            final int index = i ;
            JPanel panel = new JPanel(null);
            panel.setPreferredSize(new Dimension(270, 330));
            panel.setMaximumSize(new Dimension(270, 330));
            panel.setBackground(Color.decode(env.MAIN_COLOR));
            hotel.add(panel);

            //Text Side
            JPanel textPanel = new JPanel(null);
            textPanel.setBounds(14, 175, 241, 70);
            textPanel.setBackground(Color.decode(env.MAIN_COLOR));
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
            
            env.LoadImageIcon(image , 30 , 30);
            NoScalingIcon hotelImage = new NoScalingIcon(image);
//            NoScalingIcon hotelImage = new NoScalingIcon(env.LoadImage("assets/example.png", 271, 190));
            JLabel hotelImageLabel = new JLabel(hotelImage);
            hotelImageLabel.setBounds(0, -5, 271, 190);
            panel.add(hotelImageLabel);

            env.MouseListener(panel, (MouseEvent e) -> {
                HotelDetail hotelDetail = new HotelDetail(hotelList.get(index));
                return null;
            });
            panel.addMouseListener(new env.CursorPointerStyle(panel));
        }
    }
}
