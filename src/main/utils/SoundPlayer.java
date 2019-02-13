package utils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {

    private static Clip clip;

    public SoundPlayer() {

    }

    public static void playStartMusic() {
        InputStream startMusik = SoundPlayer.class.getClassLoader().getResourceAsStream("sounds/pacman_beginning.wav");
        try {
            AudioStream audioStream = new AudioStream(startMusik);
            AudioPlayer.player.start(audioStream);
        } catch (IOException ioe) {
            Logger.error("Can't find soundfile pacman_beginning_wav");
        }
    }


    public static void playChompSound() {
        try {
            playSound(AudioSystem.getAudioInputStream(SoundPlayer.class.getClassLoader().getResourceAsStream("sounds/pacman_chomp.wav")));

        } catch (UnsupportedAudioFileException e) {
            Logger.error("UnsupportedAudioFileException in SoundPlayer while playing chomp sound");
        } catch (IOException ioe) {
            Logger.error("Can't find soundfile pacman_beginning_wav");


        }
    }


    public static void playPlayerDeathSound() {
        try {
            playSound(AudioSystem.getAudioInputStream(SoundPlayer.class.getClassLoader().getResourceAsStream("sounds/pacman_death.wav")));

        } catch (UnsupportedAudioFileException e) {
            Logger.error("UnsupportedAudioFileException in SoundPlayer while playing player-death sound");
        } catch (IOException ioe) {
            Logger.error("Can't find soundfile pacman_death");


        }
    }

    private static void playSound(AudioInputStream sound) {
        try {
            clip = AudioSystem.getClip();

            clip.open(sound);
            clip.start();

        } catch (LineUnavailableException lue) {
            Logger.error("LineUnavailableException in SoundPlayer while playing sound: " + sound);

        } catch (IOException ioe) {
            Logger.error("IO-Exception in SoundPlayer while playing sound: " + sound);
        }
    }


}
