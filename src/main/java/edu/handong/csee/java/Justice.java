package edu.handong.csee.java;

public class Justice {

	private static Justice instance;

	private int count;
	private boolean doStart;

	private int[][] playInfo;

	private static int leftSlopeCount;
	private static int rightSlopeCount;
	private static int upDownCount;
	private static int leftRightCount;

	
	public Justice() {
		count = 0;
		playInfo = new int[19][19];
	}

	public static Justice getInstance() {
		if (instance == null)
			instance = new Justice();

		return instance;
	}
	
	public void clear() {
		count = 0;
		playInfo = null;
		playInfo = new int[19][19];
	}

	public int getCount() {
		if (doStart)
			return count++;
		else
			return count;
	}

	public void setDoStart(boolean doStart) {
		this.doStart = doStart;
		if(doStart) {
			Tile.getTimeKeeper().startCountDown();
		}
	}

	public boolean isDoStart() {
		return doStart;
	}

	public void setGameInfo(int[] activatedInfo) {
		playInfo[activatedInfo[0]][activatedInfo[1]] = activatedInfo[2];
		System.out.println("Activation :" + playInfo[activatedInfo[0]][activatedInfo[1]]);

		checkPlayInfo();

//		if (count > 10)
			checkWin(activatedInfo[0], activatedInfo[1], activatedInfo[2]);
	}

	private void checkWin(int x, int y, int role) {
		// 어느방향으로던 6스택 쌓으면 승리~
		leftSlopeCount = rightSlopeCount = upDownCount = leftRightCount = 1;
		System.out.println("현재 좌표! " + x + " : " + y);
		try {
			upCheck(x, y, role);
			downCheck(x, y, role);
			
			leftCheck(x, y, role);
			rightCheck(x, y, role);
			
			leftSlopeTopCheck(x, y, role);
			leftSlopeBottomCheck(x, y, role);
			
			rightSlopeTopCheck(x, y, role);
			rightSlopeBottomCheck(x, y, role);
		} catch (Exception e) {
			System.out.println("승리" + role);
			new ClearPopup(role);
			Tile.stopTimer();
			PlayFrame.stopPlayTimer();
		}

		System.out.println("UD : " + upDownCount + ", LR : " + leftRightCount + ", RS : " + rightSlopeCount + ", LS : "
				+ leftSlopeCount);
	}

	public void checkPlayInfo() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.print(" ");
				System.out.print(playInfo[i][j]);
			}
			System.out.println(" ");
		}
	}

	private void upCheck(int x, int y, int role) throws Exception {
		if(x == 0 )
			return;
		
		if (playInfo[x - 1][y] == role) {
			System.out.println("위에 있대용 : " + x + " , " + y);
			upDownCount++;
			upCheck(x - 1, y, role);
		} else {
			System.out.println("위에 끝났대용 : " + x + " , " + y);
			return;
		}

		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

	private void downCheck(int x, int y, int role) throws Exception {
		if( x == 18 )
				return;
		
		if (playInfo[x + 1][y] == role) {
			System.out.println("아래 있대용 : " + x + " , " + y);
			upDownCount++;
			downCheck(x + 1, y, role);
		} else {
			System.out.println("아래 끝났대용 : " + x + " , " + y);
			return;
		}

		// 정규식 밑에 위치한 이유는 6 이상으로 길게 하는 경우 승리 판정을 안하기 위함
		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

	private void leftCheck(int x, int y, int role) throws Exception {
		if (y == 0)
			return;
		
		if (playInfo[x][y - 1] == role) {
			System.out.println("왼쪽에 있대용 : " + x + " , " + y);
			leftRightCount++;
			leftCheck(x, y - 1, role);
		} else {
			System.out.println("왼쪽 끝났대용 : " + x + " , " + y);
			return;
		}

		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

	private void rightCheck(int x, int y, int role) throws Exception {
		if( y == 18)
				return;
		
		if (playInfo[x][y + 1] == role) {
			System.out.println("오른쪽에 있대용 : " + x + " , " + y);
			leftRightCount++;
			rightCheck(x, y + 1, role);
		} else {
			System.out.println("오른쪽 끝났대용" + x + " , " + y);
			return;
		}
		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

	private void leftSlopeTopCheck(int x, int y, int role) throws Exception {
		if (x == 0 || y == 0)
			return;
		
		if (playInfo[x - 1][y - 1] == role) {
			System.out.println("왼쪽 위에 있대용 : " + x + " , " + y);
			leftSlopeCount++;
			leftSlopeTopCheck(x - 1, y - 1, role);
		} else {
			System.out.println("왼쪽 위에 끝났어용 : " + x + " , " + y);
			return;
		}

		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

	private void leftSlopeBottomCheck(int x, int y, int role) throws Exception {
		if( x == 18 || y == 18)
				return;
		
		if (playInfo[x + 1][y + 1] == role) {
			System.out.println("오른 아래 있대용 : " + x + " , " + y);
			leftSlopeCount++;
			leftSlopeBottomCheck(x + 1, y + 1, role);
		} else {
			System.out.println("오른 아래 끝났대용 : " + x + " , " + y);
			return;
		}

		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

	private void rightSlopeTopCheck(int x, int y, int role) throws Exception {
		if(x == 0 || y == 18)
				return;
		if (playInfo[x - 1][y + 1] == role) {
			System.out.println("오른 위에 있대용 : " + x + " , " + y);
			rightSlopeCount++;
			rightSlopeTopCheck(x - 1, y + 1, role);
		} else {
			System.out.println("오른 위에 끝났대용 : " + x + " , " + y);
			return;
		}

		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

	private void rightSlopeBottomCheck(int x, int y, int role) throws Exception {
		if(x == 18 || y == 0)
			return;
		
		if (playInfo[x + 1][y - 1] == role) {
			System.out.println("왼쪽 아래 있대용 : " + x + " , " + y);
			rightSlopeCount++;
			rightSlopeBottomCheck(x + 1, y - 1, role);
		} else {
			System.out.println("왼쪽 아래 끝났대용 : " + x + " , " + y);
			return;
		}

		if (leftSlopeCount == 6 || rightSlopeCount == 6 || upDownCount == 6 || leftRightCount == 6) {
			throw new Exception();
		}
	}

}
