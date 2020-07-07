package edu.handong.csee.java;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

	private static File BGMFile;
	private static File effectFile ;
	private static Clip BGMClip;
	private static Clip effectClip;
	
	
	public MusicPlayer() {
	}
	
	
	public static void  BGMPlay() {
        try {
        	if(PlayFrame.isSoundOn()) {
        		BGMFile = new File("/Users/suhyun/git/SSC_Project6/Source/BGM.wav");
        		AudioInputStream stream = AudioSystem.getAudioInputStream(BGMFile);
                BGMClip = AudioSystem.getClip();
                BGMClip.open(stream);
                BGMClip.start();
                BGMClip.loop(100);
        	}
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
	}
	
	public static void  putSound() {
        try {
            if(PlayFrame.isSoundOn()) {
            	effectFile = new File("/Users/suhyun/git/SSC_Project6/Source/puteffect.wav");
            	AudioInputStream stream = AudioSystem.getAudioInputStream(effectFile);
                effectClip = AudioSystem.getClip();
                effectClip.open(stream);
                effectClip.start();
            }
        } catch(Exception e) {
            
            e.printStackTrace();
        }
	}
	
	public static void stopBGM() {
		BGMClip.stop();
	}
	
	public static void startBGM() {
		BGMClip.start();
		BGMClip.loop(100);
	}
}
