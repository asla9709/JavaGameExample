package GameState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import TileMap.Background;
import sun.audio.*;

public abstract class GameState {

	protected GameStateManager gsm;
	public static double xOffset, yOffset;
	public Background back2;
	private static boolean playing = false;
	
	private static InputStream in = null;
	private static AudioStream as = null;
	
	public static Clip clip = null;

    ContinuousAudioDataStream loop = null;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		
		xOffset = 0;
		yOffset = 0;
		
		init();
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
	public static void startMusic(String s){

		File f1 = new File(".\\" + s);
		if(playing == false){	
			
			try {
				clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(f1);
				clip.open(ais);
			    clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			playing = true;
		}
	}
	
	public static void stopMusic(){
		if(playing == true){
			clip.stop();
			playing = false;
		}
	}

	
}
