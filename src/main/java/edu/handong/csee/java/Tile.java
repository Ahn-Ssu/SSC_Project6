package edu.handong.csee.java;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;

public class Tile extends JLabel implements MouseListener, MouseMotionListener{

	private Cursor cursor;
	private Stone hereStone;
	
	public static StopWatch TimeKeeper = new StopWatch();
	
	public Tile(int i, int j){
		this.setVisible(true);
		this.setLocation(20 + i*40, 10 + j*40);
		this.setSize(39, 39);
		addMouseListener(this);
		addMouseMotionListener(this);
		hereStone = new Stone(j,i);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
System.out.println(e.getPoint());
		
		int now = Justice.getInstance().getCount();
		MusicPlayer.putSound();
		if(!Justice.getInstance().isDoStart()) {
			hereStone.setMyJob(Stone.NPC);
			PlayFrame.getInstance().setTurnLabel(hereStone.getRoleIcon());
		}
		else if(now%4==0) {
			hereStone.setMyJob(Stone.BLACK);
			PlayFrame.getInstance().setTurnLabel(Stone.WhiteIcon);
			TimeKeeper.stopCountDown();
			TimeKeeper = new StopWatch();
			TimeKeeper.startCountDown();
		}
		else if(now%4==1) {
			hereStone.setMyJob(Stone.WHITE);
			PlayFrame.getInstance().setTurnLabel(Stone.WhiteIcon);
		}
		else if(now%4==2) {
			hereStone.setMyJob(Stone.WHITE);
			PlayFrame.getInstance().setTurnLabel(Stone.BlackIcon);
			TimeKeeper.stopCountDown();
			TimeKeeper = new StopWatch();
			TimeKeeper.startCountDown();
		}
		else if(now%4==3) {
			hereStone.setMyJob(Stone.BLACK);
			PlayFrame.getInstance().setTurnLabel(Stone.BlackIcon);
		}
			
		infoActivation();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		PlayFrame.getPlayFrame().setCursor(cursor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		cursor = Cursor.getDefaultCursor();
		PlayFrame.getPlayFrame().setCursor(cursor);
	}
	
	
	
	private void infoActivation() {
		this.setIcon(hereStone.getRoleIcon());
		System.out.println(hereStone.getLocationNRole()[0] + " : " +hereStone.getLocationNRole()[1] + " : " +hereStone.getLocationNRole()[2]);
		Justice.getInstance().setGameInfo(hereStone.getLocationNRole());
		
		removeMouseListener(this);
		removeMouseMotionListener(this);
		cursor = Cursor.getDefaultCursor();
		PlayFrame.getPlayFrame().setCursor(cursor);
	}
	public static StopWatch getTimeKeeper() {
		return TimeKeeper;
	}
	
	public static void stopTimer() {
		TimeKeeper.stopCountDown();
		TimeKeeper  = new StopWatch();
	}
}
