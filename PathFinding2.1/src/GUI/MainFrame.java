package GUI;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){

        int width = (int) getToolkit().getScreenSize().getWidth();
        int height = (int) getToolkit().getScreenSize().getHeight();

        this.setSize(width, height);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);

        this.add(new MainPanel(width, height));

        this.setVisible(true);
    }

}
