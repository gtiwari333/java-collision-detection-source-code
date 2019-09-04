package collision;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Balls");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MainPanel mainPanel = new MainPanel(800, 600);
            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setVisible(true);

            mainPanel.startDrawing();
        });
    }
}
