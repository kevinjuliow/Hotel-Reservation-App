package Transylvania;

import Transylvania.pages.HomePage.HomeContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.InputMismatchException;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class env {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final String DARK_COLOR = "#1f1f1f";
    public static final String MAIN_COLOR = "#ffffff";
    public static final String SECONDARY_COLOR = "#00c7a3";
    public static final String SIDEBAR_COLOR = "#21BF73";
    public static final String TOPBAR_COLOR = "#1f1f1f";
    public static final String NICE_RED = "#FF385C";
    //    public static final String BRIGHT_GREEN = "#21BF73";
//    public static final String DARK_GREEN = "#40513B";
//    public static final String SMOOTH_DARK_GREEN = "#609966";
//    public static final String LIGHT_GREEN = "#9DC08B";
//    public static final String SUPER_LIGHT_GREEN = "#B0EACD";
    public static final String LIGHT_GRAY = "#808080";
    //    public static final String OFF_WHITE = "#EDF1D6";
    public static final int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720;
    public static final int WINDOW_POST_X = (int) (screenSize.getWidth() - FRAME_WIDTH) / 2;
    public static final int WINDOW_POST_Y = (int) (screenSize.getHeight() - FRAME_HEIGHT) / 2;
    public static final Font pixel36 = new Font("Segoe UI", Font.PLAIN, 36);
    public static final Font pixel36B = new Font("Segoe UI", Font.BOLD, 36);
    public static final Font pixel28 = new Font("Segoe UI", Font.PLAIN, 28);
    public static final Font pixel28B = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font pixel24 = new Font("Segoe UI", Font.PLAIN, 24);
    public static final Font pixel24B = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font pixel20 = new Font("Segoe UI", Font.PLAIN, 20);
    public static final Font pixel20B = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font pixel18 = new Font("Segoe UI", Font.PLAIN, 18);
    public static final Font pixel18B = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font pixel16 = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font pixel16B = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font pixel14 = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font pixel14B = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font pixel12 = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font pixel12B = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font pixel9 = new Font("Segoe UI", Font.PLAIN, 9);
    public static final Font pixel9B = new Font("Segoe UI", Font.BOLD, 9);
    public static final Font pixel8 = new Font("Segoe UI", Font.PLAIN, 8);
    public static final Font pixel8B = new Font("Segoe UI", Font.BOLD, 8);


    public static void animate(JComponent component, Point newPoint, int frames, int interval) {
        Rectangle compBounds = component.getBounds();
        Point oldPoint = new Point(compBounds.x, compBounds.y),
                animFrame = new Point((newPoint.x - oldPoint.x) / frames,
                        (newPoint.y - oldPoint.y) / frames);
        new Timer(interval, new ActionListener() {
            int currentFrame = 0;

            public void actionPerformed(ActionEvent e) {
                component.setBounds(oldPoint.x + (animFrame.x * currentFrame),
                        oldPoint.y + (animFrame.y * currentFrame),
                        compBounds.width,
                        compBounds.height);
                if (currentFrame != frames)
                    currentFrame++;
                else
                    ((Timer) e.getSource()).stop();
            }
        }).start();
    }

    public static void animate2(JComponent component, Point newPoint, int frames, int interval, Runnable onAnimationComplete) {
        Rectangle compBounds = component.getBounds();
        Point oldPoint = new Point(compBounds.x, compBounds.y);
        Point animFrame = new Point((newPoint.x - oldPoint.x) / frames, (newPoint.y - oldPoint.y) / frames);

        Timer timer = new Timer(interval, null);
        timer.addActionListener(new ActionListener() {
            int currentFrame = 0;

            public void actionPerformed(ActionEvent e) {
                component.setBounds(oldPoint.x + (animFrame.x * currentFrame),
                        oldPoint.y + (animFrame.y * currentFrame),
                        compBounds.width,
                        compBounds.height);

                if (currentFrame != frames)
                    currentFrame++;
                else {
                    ((Timer) e.getSource()).stop();
                    if (onAnimationComplete != null) {
                        onAnimationComplete.run();
                    }
                }
            }
        });
        timer.start();
    }

    public static void MouseListener(JComponent components, Function<MouseEvent, Void> function) {
        components.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                function.apply(e);
            }
        });
    }

    public static JComponent FindComponents(JComponent panel, String s) {
        JComponent label = null;
        Component[] panelComponents = panel.getComponents();
        for (Component c : panelComponents) {
            if (c.getName() != null && c.getName().equals(s)) {
                label = (JComponent) c;
            }
        }
        return label;
    }

    public static ImageIcon LoadImage(String path, int w, int h) {
        ImageIcon image = new ImageIcon(HomeContent.class.getClassLoader().getResource(path));
        image = new ImageIcon(image.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        return image;
    }

    public static void ActionListener(JButton component, Function<ActionEvent, Void> function) {
        component.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                function.apply(e);
            }
        });
    }
}


