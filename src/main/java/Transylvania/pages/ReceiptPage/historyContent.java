package Transylvania.pages.ReceiptPage;

import Transylvania.Classes.Hotel;
import Transylvania.Classes.Transaction;
import Transylvania.database.DBControls;
import Transylvania.style.RoundedBorder;
import Transylvania.env;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class historyContent {
    public static JPanel historyPanel() {
        List<Transaction> transactionList = DBControls.GETAllTransaction() ;
        int y = 60;

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBounds(80 , 80 , env.FRAME_WIDTH-80 , env.FRAME_HEIGHT-80);
        mainPanel.setBackground(Color.decode(env.MAIN_COLOR));
        for (int i = 0; i < transactionList.size(); i++) {
            JPanel panel = new JPanel(null);
            panel.setBounds(100 , y , 1000 , 180);
            mainPanel.add(panel);

            JLabel checkinDate = new JLabel("Check in : "+transactionList.get(i).getCheck_in());
            checkinDate.setBounds(10 , 50 , 240 , 20);
            checkinDate.setFont(env.pixel20);
            panel.add(checkinDate);
//
            JLabel checkoutDate = new JLabel("Check out : "+transactionList.get(i).getCheck_out());
            checkoutDate.setBounds(10 , 80 , 240 , 20);
            checkoutDate.setFont(env.pixel20);
            panel.add(checkoutDate);

            JLabel paymentMethod = new JLabel("Payment Method : "+transactionList.get(i).getPaymentMethod());
            paymentMethod.setBounds(10 , 150 , 200 , 20);
            paymentMethod.setForeground(Color.decode(env.LIGHT_GRAY));
            paymentMethod.setFont(env.pixel12);
            panel.add(paymentMethod);

            JLabel transactionPrice = new JLabel("IDR " +transactionList.get(i).getTransactionPrice());
            transactionPrice.setBounds(760 , 50 , 220 , 20);
            transactionPrice.setFont(env.pixel24);
            panel.add(transactionPrice);
//
            JLabel taxFee = new JLabel("Tax Fee : " + transactionList.get(i).getTaxFee());
            taxFee.setBounds(760 , 80 , 220 , 20);
            taxFee.setFont(env.pixel12);
            taxFee.setForeground(Color.decode(env.LIGHT_GRAY));
            panel.add(taxFee);


            y+= 220;

        }

        return mainPanel;
    }
}
