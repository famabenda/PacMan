package score;

import lombok.Data;

@Data
public class Score {
    private String name;
    private int score;


    public Score(String name, int score){
        setName(name);
        setScore(score);
    }

}
