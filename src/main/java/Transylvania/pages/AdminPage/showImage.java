package Transylvania.pages.AdminPage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class showImage {
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Transylvania", "developer", "secretdevpass");

            // Create a PreparedStatement
            PreparedStatement ps = conn.prepareStatement("SELECT data FROM images WHERE id = 1");

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // Read the image from the ResultSet
            if (rs.next()) {
                byte[] imageBytes = rs.getBytes(1);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage image = ImageIO.read(bis);

                // Create an ImageIcon from the image
                ImageIcon imageIcon = new ImageIcon(image);

                // Create a JLabel and add the ImageIcon to it
                JLabel label = new JLabel(imageIcon);

                // Create a JPanel and add the JLabel to it
                JPanel panel = new JPanel();
                panel.add(label);

                // Create a JFrame and add the JPanel to it
                JFrame frame = new JFrame();
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
            }

            // Close the resources
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

