package gui;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class CardLayoutPanel extends JPanel {


    private MainPanel mainPanel;
    private MenuPanel menuPanel;
    private CardLayout cardLayout;

    public CardLayoutPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setBackground(Color.GRAY);
        mainPanel = new MainPanel();
        menuPanel = new MenuPanel();
        add(menuPanel);
        add(mainPanel);
        cardLayout.addLayoutComponent(menuPanel, "menuPanel");
        cardLayout.addLayoutComponent(mainPanel, "mainPanel");


        setVisible(true);
    }



}
