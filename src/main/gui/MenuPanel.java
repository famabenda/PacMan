package gui;

import game.GameOrchestrator;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class MenuPanel extends JPanel implements ActionListener {

    private JButton jbtnStartGame;

    public MenuPanel() {
        init();
        setDoubleBuffered(true);

    }

    private void init() {
        jbtnStartGame = new JButton("Start");
        jbtnStartGame.setSize(50, 300);
        jbtnStartGame.setFont(new Font("impact", Font.BOLD, Gui.RESOLUTION));
        jbtnStartGame.addActionListener(this);
        jbtnStartGame.setActionCommand("START");
        jbtnStartGame.setVisible(true);

        this.add(jbtnStartGame);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("START")) {
            GameOrchestrator.startGame();
        }
    }
}
