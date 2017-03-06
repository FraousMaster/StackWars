import java.io.File;
import javax.swing.*;
import javax.sound.sampled.*;

public class Main {
	public static void main(final String[] args){
		new Menu();
		//File file =	new File("Sound/CC Red Alert 3 Theme - Soviet March.wav");
		File file =	new File("Sound/Denzel Curry-  Ultimate (Lyrics).wav");
		try {
            Clip clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            AudioInputStream ais = AudioSystem.
                    getAudioInputStream(file);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch (Exception e){
		    e.printStackTrace();
        }
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}
}
