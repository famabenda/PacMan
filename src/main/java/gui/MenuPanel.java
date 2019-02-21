package gui;

import game.GameOrchestrator;
import lombok.Data;
import score.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Data
public class MenuPanel extends JPanel implements ActionListener {

    private JButton jbtnStartGame;
    private JTable highscoreTable;

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

        highscoreTable = new JTable();
        ArrayList<Score> highscores = GameOrchestrator.highscoreTable.getHighscores();
        String colums[] = {"Platz", "Name", "Score"};
        DefaultTableModel tableModel = new DefaultTableModel(colums, 0);
        highscores.sort((score1, score2) -> {
            if (score1.getScore() < score2.getScore()) return 1;
            if (score1.getScore() > score2.getScore()) return -1;
            return 0;
        });
        for (int i = 0; i < highscores.size(); i++) {
            Object[] row = new Object[]{i + 1, highscores.get(i).getName(), String.valueOf(highscores.get(i).getScore())};
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        table.setVisible(true);
        table.setSize(400, 400);

        this.add(jbtnStartGame);
        this.add(new JScrollPane(table));
        setVisible(true);
    }


    public void updateHighscoreTable(){


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("START")) {
            GameOrchestrator.startGame();
        }
    }
}
