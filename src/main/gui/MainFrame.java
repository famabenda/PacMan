package gui;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MainFrame extends JFrame {

    private CardLayoutPanel cardLayoutPanel;

    public MainFrame() {
        cardLayoutPanel = new CardLayoutPanel();
        add(cardLayoutPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void showGameView() {
        CardLayout layout = (CardLayout) getCardLayoutPanel().getLayout();
        layout.show(cardLayoutPanel, "mainPanel");
        cardLayoutPanel.getMainPanel().requestFocus();
        revalidate();
    }

    public void showMenuView() {
        CardLayout layout = (CardLayout) getCardLayoutPanel().getLayout();
        layout.show(cardLayoutPanel, "menuPanel");
        revalidate();
    }


}
