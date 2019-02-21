package score;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HighscoreTable {

    private ArrayList<Score> highscores = new ArrayList<>();


    public void add(Score score){
        highscores.add(score);
    }

}
