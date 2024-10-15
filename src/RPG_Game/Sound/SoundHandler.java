package RPG_Game.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler
{
    Clip clip;
    AudioInputStream inputStream;
    long clipTimePos;
    public SoundHandler()
    {

    }

    public void runMusic(String path)
    {
        try {
            inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f); // Reduce volume by decibels.
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void pauseMusic(boolean OnOff)
    {
        if (OnOff) {
            clipTimePos = clip.getMicrosecondPosition();
            clip.stop();
        } else
        {
            clip.setMicrosecondPosition(clipTimePos);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopMusic()
    {
        clip.stop();
        clip.close();
    }


}
