package menuComponents;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: A sound effect that is mainly used for clicking buttons
 *
 */
public class SoundEffect {
	
	public SoundEffect(String soundEffect) {
		try {
			// Load audio input stream
			URL url = this.getClass().getClassLoader().getResource(soundEffect);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Retrieve sound clip
			// Create variable to store audio
			Clip clip = AudioSystem.getClip(); 
			// Open input stream
			clip.open(audioIn); 
			// Play sound
			clip.start(); 
		} catch (UnsupportedAudioFileException e) {
			//Print stack trace if error occurs
			e.printStackTrace();
		} catch (IOException e) {
			//Print stack trace if error occurs
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			//Print stack trace if error occurs
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SoundEffect("click.wav");
	}

}
