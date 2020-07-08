package edu.handong.csee.java;

import java.awt.AWTException;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PlayFrame extends JComponent implements ActionListener  {

	private static PlayFrame instance;
	
	private static JFrame playFrame = new JFrame("Connect6 + AI");
	private JButton startButton, soundOnOff, restartButton;
	private JLabel boardLabel, nowTurnLabelBody, countDownLabelBody ,playTimeLabelBody  ;
	private static JPanel panel;
	
	private BoardActivator myActivator = new BoardActivator();
	private static StopWatch playTimer;
	private Tile[][] setTile;
	private Robot firstHelper;
	
	private static boolean soundOn = true;
	
	

	//인터페이스 구축 
	public PlayFrame() {
	
		MusicPlayer.BGMPlay();
		try {
			firstHelper = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		playFrame.getContentPane().setBackground(Color.WHITE);
		playFrame.getContentPane().setLayout(null);
		playFrame.setSize(1000, 900);
		
		JLabel playTimeLabelHead = new JLabel("대국 시간");
		playTimeLabelHead.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", playTimeLabelHead.getFont().getStyle(), 15));
		playTimeLabelHead.setHorizontalAlignment(SwingConstants.CENTER);
		playTimeLabelHead.setBounds(859, 30, 91, 29);
		playFrame.getContentPane().add(playTimeLabelHead);
		
		playTimeLabelBody = new JLabel("05 : 36");
		playTimeLabelBody.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", playTimeLabelBody.getFont().getStyle(), 17));
		playTimeLabelBody.setHorizontalAlignment(SwingConstants.CENTER);
		playTimeLabelBody.setBounds(859, 60, 91, 29);
		playFrame.getContentPane().add(playTimeLabelBody);
		
		JLabel countDownLabelHead = new JLabel("제한 시간");
		countDownLabelHead.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", countDownLabelHead.getFont().getStyle(), 15));
		countDownLabelHead.setHorizontalAlignment(SwingConstants.CENTER);
		countDownLabelHead.setBounds(859, 100, 90, 29);
		playFrame.getContentPane().add(countDownLabelHead);
		 
		countDownLabelBody = new JLabel("00 : 00");
		countDownLabelBody.setForeground(Color.RED);
		countDownLabelBody.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", countDownLabelBody.getFont().getStyle(), 17));
		countDownLabelBody.setHorizontalAlignment(SwingConstants.CENTER);
		countDownLabelBody.setBounds(859, 130, 91, 36);
		playFrame.getContentPane().add(countDownLabelBody);
		
		JLabel nowTurnLabelHead = new JLabel("놓을 차례");
		nowTurnLabelHead.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", nowTurnLabelHead.getFont().getStyle(), 15));
		nowTurnLabelHead.setHorizontalAlignment(SwingConstants.CENTER);
		nowTurnLabelHead.setBounds(859, 182, 91, 29);
		playFrame.getContentPane().add(nowTurnLabelHead);
		
		nowTurnLabelBody = new JLabel(" ");
		nowTurnLabelBody.setHorizontalAlignment(SwingConstants.CENTER);
		nowTurnLabelBody.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/NPCCatStone - red.png"));
		nowTurnLabelBody.setBounds(877, 210, 60, 60);
		playFrame.getContentPane().add(nowTurnLabelBody);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(18, 49, 800, 790);
		panel.setLayout(null);
		playFrame.getContentPane().add(panel);
	
		
		setTile = myActivator.getSetTile();
		
		for(Tile[] temp : setTile) {
			for(Tile t : temp) {
				panel.add(t);
			}
		}
		
		boardLabel = new JLabel("");
		boardLabel.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/Connect6Borad.png"));
		boardLabel.setBounds(18, 18, 760, 760);
		panel.add(boardLabel);
		
		startButton = new JButton("게임 시작!");
		startButton.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", startButton.getFont().getStyle(), startButton.getFont().getSize()));
		startButton.setBounds(701, 13, 117, 29);
		playFrame.getContentPane().add(startButton);
		
		soundOnOff = new JButton(" : On");
		soundOnOff.setHorizontalAlignment(SwingConstants.LEADING);
		soundOnOff.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", soundOnOff.getFont().getStyle(), soundOnOff.getFont().getSize()));
		soundOnOff.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/speaker.png"));
		soundOnOff.setBounds(859, 733, 117, 106);
		soundOnOff.setBorderPainted(false);
		playFrame.getContentPane().add(soundOnOff);
		
		 restartButton = new JButton("다시 하기");
		restartButton.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", restartButton.getFont().getStyle(), restartButton.getFont().getSize()));
		restartButton.setBounds(18, 12, 117, 29);
		playFrame.getContentPane().add(restartButton);
		
		playFrame.setVisible(true);
		
		startButton.addActionListener(this);
		soundOnOff.addActionListener(this);
		restartButton.addActionListener(this);
		
	}

	public static JFrame getPlayFrame() {
		return playFrame;
	}
	
	public void setTurnLabel(ImageIcon nowIcon) {
		nowTurnLabelBody.setIcon(nowIcon);
	}
	
	public void setPlayTimeLabelBody(String text) {
		playTimeLabelBody.setText(text);
	}
	
	public void setCountDownLabelBody(String text) {
		countDownLabelBody.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource().equals(startButton)) {
			setTurnLabel(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/blackCatStone.png"));
			startButton.setText("게임 중...");
			playTimer = new StopWatch();
			playTimer.startPlayTimeCount();
			startButton.setEnabled(false);
			Justice.getInstance().setDoStart(true);
			
//			firstHelper.mouseMove(55 + 10 * 40, 125 + 9 * 40);
//
//			firstHelper.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//
//			firstHelper.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
		}
		else if(e.getSource().equals(soundOnOff)) {
			if(soundOn) {
				soundOnOff.setText(" : Off");
				MusicPlayer.stopBGM();
			}
			else {
				soundOnOff.setText(" : On");
				MusicPlayer.startBGM();
			}
			soundOn = !soundOn;
				
		}
		else if(e.getSource().equals(restartButton)) {
			restart();
		}
	}

	public static boolean isSoundOn() {
		return soundOn;
	}
	
	public static PlayFrame getInstance() {
		if(instance == null)
			instance = new PlayFrame();
		return instance;
	}
	
	public static void stopPlayTimer() {
		playTimer.stopPlayTimeCount();
	}
	
	public static void clearActivate() {
		panel.removeAll();
	}
	
	private void restart() {
		System.out.println("re");
		 
		panel.removeAll();
		Justice.getInstance().setDoStart(false);
		myActivator = new BoardActivator();
		setTile = myActivator.getSetTile();

		for (Tile[] temp : setTile) {
			for (Tile t : temp) {
				panel.add(t);
			}
		}
		Justice.getInstance().clear();
		panel.add(boardLabel);
		playFrame.setVisible(false);
		playFrame.setVisible(true);
		playTimer.stopPlayTimeCount();
		Tile.stopTimer();
		nowTurnLabelBody.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/NPCCatStone - red.png"));
		startButton.setText("게임 시작!");
		startButton.setEnabled(true);
	}
}
