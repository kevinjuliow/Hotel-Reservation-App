package Transylvania.pages.LoginSignUp;

import Transylvania.Classes.User;
import Transylvania.Main;
import Transylvania.Validator.DataValidator;
import Transylvania.database.DBControls;
import Transylvania.env;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class LoginSignupPage extends JFrame {
    JButton loginBtn;

    public LoginSignupPage() {
        //Panels
        JPanel mainPanel = new JPanel();
        setBounds(env.WINDOW_POST_X, env.WINDOW_POST_Y, env.FRAME_WIDTH, env.FRAME_HEIGHT);
        JPanel imgPanel = LoginSignupPanels.imagePanel();
        JPanel loginPanel = LoginSignupPanels.loginPanel();
        JPanel signUpPanel = LoginSignupPanels.signUpPanel();

        //Background
        mainPanel.setBackground(Color.decode(env.MAIN_COLOR));

        //mainPanel add
        mainPanel.add(loginPanel);
        mainPanel.add(imgPanel);

        //loginPanel Components
        JLabel signUpLabel = (JLabel) env.FindComponents(loginPanel, "signUpLabel");
        JButton loginBtn = (JButton) env.FindComponents(loginPanel, "loginBtn");

        //signupPanel Components
        JLabel loginLabel = (JLabel) env.FindComponents(signUpPanel, "loginLabel");
        JButton signUpBtn = (JButton) env.FindComponents(signUpPanel, "signUpBtn");

        JTextField emailInput = (JTextField) env.FindComponents(loginPanel, "emailTf");
        JTextField passwordInput = (JTextField) env.FindComponents(loginPanel, "passwordTf");

        //ActionListeners
        env.ActionListener(loginBtn, (ActionEvent e) -> {
            try {
                //data validation
                DataValidator.checkIsEmpty(emailInput.getText());
                DataValidator.checkIsEmpty(passwordInput.getText());
                DataValidator.checkValidEmailFormat(emailInput.getText());

                User userData = DBControls.findUserByEmail(emailInput.getText());
                if (userData != null && userData.getPassword().equals(passwordInput.getText())) {
                    env.animate(imgPanel, new Point(-2000, 0), 20, 2);
                    env.animate2(loginPanel, new Point(2000, 0), 20, 2, () -> {
                        Main main = new Main("John Doe");
                        dispose();
                    });
                    return null;
                }

                JLabel errorInformationLabel = (JLabel) env.FindComponents(loginPanel, "errorInformationLabelLogin");
                errorInformationLabel.setText("User with email " + emailInput.getText() + " isn't found, or wrong password?");
                return null;
            } catch (IllegalArgumentException | InputMismatchException err) {
                JLabel errorInformationLabel = (JLabel) env.FindComponents(loginPanel, "errorInformationLabelLogin");
                errorInformationLabel.setText(err.getMessage());
                return null;
            }
        });

        JTextField newFullname = (JTextField) env.FindComponents(signUpPanel, "fullNameTf");
        JTextField newEmail = (JTextField) env.FindComponents(signUpPanel, "emailTf");
        JTextField newPassword = (JTextField) env.FindComponents(signUpPanel, "passwordTf");
        JTextField newPhoneNumber = (JTextField) env.FindComponents(signUpPanel, "noTelpTf");

        env.ActionListener(signUpBtn, (ActionEvent e) -> {
            try {
                User userData = new User(newFullname.getText(), newEmail.getText(), newPassword.getText(), newPhoneNumber.getText());

                //data validation
                DataValidator.checkIsEmpty(userData.getFullName());
                DataValidator.checkIsEmpty(userData.getEmail());
                DataValidator.checkIsEmpty(userData.getPassword());
                DataValidator.checkIsEmpty(userData.getPhoneNumber());
                DataValidator.checkFullNameLength(userData.getFullName());
                DataValidator.checkNameIsCharacter(userData.getFullName());
                DataValidator.checkValidEmailFormat(userData.getEmail());
                DataValidator.checkPhoneNumber(userData.getPhoneNumber());

                //check email is exist or not
                User verifyExistingEmail = DBControls.findUserByEmail(userData.getEmail());

                if (verifyExistingEmail != null) {
                    JLabel errorInformationLabel = (JLabel) env.FindComponents(signUpPanel, "errorInformationLabelSignUp");
                    errorInformationLabel.setText("Email " + emailInput.getText() + " already exist!");
                    return null;
                }
                DBControls.registerUserAccount(userData);

                //clear the fields
                newFullname.setText("");
                newEmail.setText("");
                newPassword.setText("");
                newPhoneNumber.setText("");

                //swap to login, so the user can login with their new account
                mainPanel.remove(signUpPanel);
                env.animate(imgPanel, new Point(0, 0), 20, 1);
                loginPanel.setBounds((env.FRAME_WIDTH / 2) / 5, 0, env.FRAME_WIDTH, env.FRAME_HEIGHT);
                mainPanel.add(loginPanel);
                env.animate(loginPanel, new Point(env.FRAME_WIDTH / 2, 0), 20, 1);
                return null;
            } catch (IllegalArgumentException | InputMismatchException | SQLException err) {
                JLabel errorInformationLabel = (JLabel) env.FindComponents(signUpPanel, "errorInformationLabelSignUp");
                errorInformationLabel.setText(err.getMessage());
                return null;
            }
        });

        signUpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.remove(loginPanel);
                loginPanel.setBounds((env.FRAME_WIDTH / 2) / 5, 0, env.FRAME_WIDTH / 2, env.FRAME_HEIGHT);
                env.animate(imgPanel, new Point(env.FRAME_WIDTH / 2, 0), 20, 1);
                signUpPanel.setBounds(env.FRAME_WIDTH / 2, 0, env.FRAME_WIDTH, env.FRAME_HEIGHT);
                mainPanel.add(signUpPanel);
                env.animate(signUpPanel, new Point(0, 0), 20, 1);

                //clear error text
                JLabel errorInformationLabel = (JLabel) env.FindComponents(signUpPanel, "errorInformationLabelSignUp");
                errorInformationLabel.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                signUpLabel.setForeground(Color.decode(env.NICE_RED));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                signUpLabel.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                signUpLabel.setForeground(Color.decode(env.NICE_RED));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpLabel.setForeground(Color.BLACK);
            }
        });

        loginLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.remove(signUpPanel);
                env.animate(imgPanel, new Point(0, 0), 20, 1);
                loginPanel.setBounds((env.FRAME_WIDTH / 2) / 5, 0, env.FRAME_WIDTH, env.FRAME_HEIGHT);
                mainPanel.add(loginPanel);
                env.animate(loginPanel, new Point(env.FRAME_WIDTH / 2, 0), 20, 1);

                //clear error text
                JLabel errorInformationLabel = (JLabel) env.FindComponents(loginPanel, "errorInformationLabel");
                errorInformationLabel.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) { loginLabel.setForeground(Color.decode(env.NICE_RED)); }
            @Override
            public void mouseReleased(MouseEvent e) { loginLabel.setForeground(Color.BLACK); }
            @Override
            public void mouseEntered(MouseEvent e) { loginLabel.setForeground(Color.decode(env.NICE_RED)); }
            @Override
            public void mouseExited(MouseEvent e) { loginLabel.setForeground(Color.BLACK); }
        });

        JLabel minimizer = (JLabel) env.FindComponents(loginPanel, "minimizeBtn");

        env.MouseListener(minimizer, (MouseEvent e) -> {
            setState(JFrame.ICONIFIED);
            return null;
        });

        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        setUndecorated(true);
        setVisible(true);
    }
}
