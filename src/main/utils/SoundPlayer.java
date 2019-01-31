package utils;

import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {


    public SoundPlayer() {

    }

    public static void playStartMusic() {
        InputStream startMusik = SoundPlayer.class.getResourceAsStream("sounds/pacman_beginning.wav");
        try {
            AudioStream audioStream = new AudioStream(startMusik);
        } catch (IOException ioe) {
            Logger.error("Can't find soundfile pacman_beginning_wav");
        }
    }
}
