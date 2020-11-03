package edu.handong.csee.java;

import java.time.Clock;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatch {

	private int secCount = 30;
	private int startTime;

	private Timer secTimer = new Timer();
	private TimerTask task15 = new TimerTask() {

		@Override
		public void run() {
			if (secCount > -1) {
				PlayFrame.getInstance().setCountDownLabelBody(secToMMSS(secCount));
				secCount--;
			} else {
				new ClearPopup(99);
				PlayFrame.stopPlayTimer();
				secTimer.cancel();
			}
		}
	};

	public void startCountDown() {
		secTimer.schedule(task15, 0, 1000);
	}

	public void stopCountDown() {
		secTimer.cancel();
	}

	private Timer playTimer = new Timer();
	private TimerTask taskAll = new TimerTask() {
		@Override
		public void run() {
			PlayFrame.getInstance()
					.setPlayTimeLabelBody(secToMMSS((int) System.currentTimeMillis() / 1000 - startTime));
		}
	};

	public void startPlayTimeCount() {
		startTime = (int) System.currentTimeMillis() / 1000;
		playTimer.schedule(taskAll, 0, 1000);
	}
	
	public void stopPlayTimeCount() {
		playTimer.cancel();
	}

	private String secToMMSS(int secs) {
		int min, sec;

		sec = secs % 60;
		min = secs / 60 % 60;

		return String.format("%02d : %02d", min, sec);
	}
}
