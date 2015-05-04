import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * Sound class retrieves sound files from sound folder.
 * 
 * @Author Henrique
 */
public class Sound{
   public void playSound(String soundFile) throws LineUnavailableException {
    
    try {
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
                "./sounds/"+soundFile+".wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }

    catch (UnsupportedAudioFileException uae) {
        System.out.println(uae);
    } catch (IOException ioe) {
        System.out.println(ioe);
    } catch (LineUnavailableException lua) {
        System.out.println(lua);
    }

}
 
}