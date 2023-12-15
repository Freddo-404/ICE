import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

    public class BackgroundMusic {



        public static void playBackgroundMusic(String filePath) {
            try {
                File soundFile = new File(filePath);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY);

                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


