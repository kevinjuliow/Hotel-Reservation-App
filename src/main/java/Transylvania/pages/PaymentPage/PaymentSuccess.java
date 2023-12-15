package Transylvania.pages.PaymentPage;

import Transylvania.env;
import Transylvania.style.NoScalingIcon;

import javax.swing.*;
import java.awt.*;

public class PaymentSuccess {

    public static JPanel PaymentSuccess() {
        //Panel
        JPanel paymentSuccessPanel = new JPanel(null);
        paymentSuccessPanel.setBounds(80, 80, 1200, 640);
        paymentSuccessPanel.setBackground(Color.WHITE);

        //Image
        NoScalingIcon successIcon = new NoScalingIcon(env.LoadImage("assets/check-circle-regular-240.png", 300, 300));
        JLabel imgLabel = new JLabel(successIcon);
        imgLabel.setBounds(443, 110, 300, 300);

        JLabel successLabel = new JLabel("Payment Successful");
        successLabel.setBounds(480, 350, 300, 100);
        successLabel.setFont(env.pixel24B);

        JLabel waitLabel = new JLabel("Please wait until this page closed automatically");
        waitLabel.setBounds(435, 390, 400, 100);
        waitLabel.setFont(env.pixel16);

        paymentSuccessPanel.add(imgLabel);
        paymentSuccessPanel.add(successLabel);
        paymentSuccessPanel.add(waitLabel);

        return paymentSuccessPanel;
    }
}
