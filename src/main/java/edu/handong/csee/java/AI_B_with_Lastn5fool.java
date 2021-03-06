package edu.handong.csee.java;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.event.InputEvent;

//흑 중심 AI 입니다. 흑색 카운터임 
public class AI_B {

	private Robot AIrobot;

	private int[][] fieldInfo;
	private int[][] SumInfo;

	private int[][] xInfo, yInfo, lsInfo, rsInfo;

	private ArrayList<int[]> tempLocation = new ArrayList<int[]>();
	private ArrayList<int[]> opLocation = new ArrayList<int[]>();
	private ArrayList<int[]> subLocation = new ArrayList<int[]>();
	private ArrayList<int[]> effectiveLocation = new ArrayList<int[]>();

	private ArrayList<int[]> foolLocation = new ArrayList<int[]>();
	private ArrayList<int[]> twoRangeLocation = new ArrayList<int[]>();

	private HashMap<String, Integer> foolData = new HashMap<>();
	private HashMap<String, Integer> twoRangeData = new HashMap<>();

	private int xFool_X = 0, yFool_X = 0;
	private int xFool_Y = 0, yFool_Y = 0;
	private int xFool_RS = 0, yFool_RS = 0;
	private int xFool_LS = 0, yFool_LS = 0;
	private int xTwoRangeData_X = 0, yTwoRangeData_X = 0;
	private int xTwoRangeData_Y = 0, yTwoRangeData_Y = 0;
	private int xTwoRangeData_RS = 0, yTwoRangeData_RS = 0;
	private int xTwoRangeData_LS = 0, yTwoRangeData_LS = 0;

	public void setting() {
		foolData.put("11, 0, 22, 22, 22, 22, 11", 1);
		foolData.put("11, 22, 0, 22, 22, 22, 11", 2);
		foolData.put("11, 22, 22, 0, 22, 22, 11", 3);
		foolData.put("11, 22, 22, 22, 0, 22, 11", 4);
		foolData.put("11, 22, 22, 22, 22, 0, 11", 5);
		// 바보수 case 1 : (흰색으로) 양쪽이 막힌 곳, 4개

		foolData.put("11, 0, 0, 22, 22, 22, 11", 2);
		foolData.put("11, 0, 22, 0, 22, 22, 11", 3);
		foolData.put("11, 0, 22, 22, 0, 22, 11", 4);
		foolData.put("11, 0, 22, 22, 22, 0, 11", 5);
		foolData.put("11, 22, 0, 22, 22, 0, 11", 2);
		foolData.put("11, 22, 22, 0, 22, 0, 11", 3);
		foolData.put("11, 22, 22, 22, 0, 0, 11", 4);
		foolData.put("11, 0, 22, 22, 22, 11", 1);
		foolData.put("11, 22, 0, 22, 22, 11", 2);
		foolData.put("11, 22, 22, 0, 22, 11", 3);
		foolData.put("11, 22, 22, 22, 0, 11", 4);
		// 바보수 case 2 : 흰색으로) 양쪽이 막힌 곳, 3개

		foolData.put("99, 0, 22, 22, 22, 22, 11", 1);
		foolData.put("99, 22, 0, 22, 22, 22, 11", 2);
		foolData.put("99, 22, 22, 0, 22, 22, 11", 3);
		foolData.put("99, 22, 22, 22, 0, 22, 11", 4);
		foolData.put("99, 22, 22, 22, 22, 0, 11", 5);
		// 바보수 case 3 : (적색, 흰색으로) 양쪽이 막힌 곳, 4개

		foolData.put("99, 0, 0, 22, 22, 22, 11", 2);
		foolData.put("99, 0, 22, 0, 22, 22, 11", 3);
		foolData.put("99, 0, 22, 22, 0, 22, 11", 4);
		foolData.put("99, 0, 22, 22, 22, 0, 11", 5);
		foolData.put("99, 22, 0, 22, 22, 0, 11", 2);
		foolData.put("99, 22, 22, 0, 22, 0, 11", 3);
		foolData.put("99, 22, 22, 22, 0, 0, 11", 4);
		foolData.put("99, 0, 22, 22, 22, 11", 1);
		foolData.put("99, 22, 0, 22, 22, 11", 2);
		foolData.put("99, 22, 22, 0, 22, 11", 3);
		foolData.put("99, 22, 22, 22, 0, 11", 4);
		// 바보수 case 4 : (적색, 흰색으로) 양쪽이 막힌 곳, 3개

		foolData.put("11, 0, 22, 22, 22, 22, 99", 1);
		foolData.put("11, 22, 0, 22, 22, 22, 99", 2);
		foolData.put("11, 22, 22, 0, 22, 22, 99", 3);
		foolData.put("11, 22, 22, 22, 0, 22, 99", 4);
		foolData.put("11, 22, 22, 22, 22, 0, 99", 5);
		// 바보수 case 5 : (흰색, 적색으로) 양쪽이 막힌 곳, 4개

		foolData.put("11, 0, 0, 22, 22, 22, 99", 2);
		foolData.put("11, 0, 22, 0, 22, 22, 99", 3);
		foolData.put("11, 0, 22, 22, 0, 22, 99", 4);
		foolData.put("11, 0, 22, 22, 22, 0, 99", 5);
		foolData.put("11, 22, 0, 22, 22, 0, 99", 2);
		foolData.put("11, 22, 22, 0, 22, 0, 99", 3);
		foolData.put("11, 22, 22, 22, 0, 0, 99", 4);
		foolData.put("11, 0, 22, 22, 22, 99", 1);
		foolData.put("11, 22, 0, 22, 22, 99", 2);
		foolData.put("11, 22, 22, 0, 22, 99", 3);
		foolData.put("11, 22, 22, 22, 0, 99", 4);
		// 바보수 case 6 : (흰색, 적색으로) 양쪽이 막힌 곳, 3개

		foolData.put("99, 0, 22, 22, 22, 22, 99", 1);
		foolData.put("99, 22, 0, 22, 22, 22, 99", 2);
		foolData.put("99, 22, 22, 0, 22, 22, 99", 3);
		foolData.put("99, 22, 22, 22, 0, 22, 99", 4);
		foolData.put("99, 22, 22, 22, 22, 0, 99", 5);
		// 바보수 case 7 : (적색, 적색으로) 양쪽이 막힌 곳, 4개

		foolData.put("99, 0, 0, 22, 22, 22, 99", 2);
		foolData.put("99, 0, 22, 0, 22, 22, 99", 3);
		foolData.put("99, 0, 22, 22, 0, 22, 99", 4);
		foolData.put("99, 0, 22, 22, 22, 0, 99", 5);
		foolData.put("99, 22, 0, 22, 22, 0, 99", 2);
		foolData.put("99, 22, 22, 0, 22, 0, 99", 3);
		foolData.put("99, 22, 22, 22, 0, 0, 99", 4);
		foolData.put("99, 0, 22, 22, 22, 99", 1);
		foolData.put("99, 22, 0, 22, 22, 99", 2);
		foolData.put("99, 22, 22, 0, 22, 99", 3);
		foolData.put("99, 22, 22, 22, 0, 99", 4);
		// 바보수 case 8 : (적색, 적색으로) 양쪽이 막힌 곳, 3개

		twoRangeData.put("22, 22, 0, 0, 22, 22", 2);
		twoRangeData.put("11, 11, 0, 0, 11, 11", 2);
		twoRangeData.put("22, 22, 22, 0, 0, 22", 3);
		twoRangeData.put("11, 11, 11, 0, 0, 11", 3);
		twoRangeData.put("22, 0, 0, 22, 22, 22", 1);
		twoRangeData.put("11, 0, 0, 11, 11, 11", 1);
		twoRangeData.put("22, 0, 22, 22, 0, 22", 1);
		twoRangeData.put("11, 0, 11, 11, 0, 11", 1);
		twoRangeData.put("22, 11, 11, 11, 11, 11, 0, 22", 6);
		twoRangeData.put("11, 22, 22, 22, 22, 22, 0, 11", 6);
		twoRangeData.put("22, 0, 11, 11, 11, 11, 11, 22", 1);
		twoRangeData.put("11, 0, 22, 22, 22, 22, 22, 11", 1);
		
		// 돌 2개를 두면 6개가 만들어질 수 있는 케이스

		//
		// twoRangeData.put("0, 11, 11, 11, 11, 11", 0);
		// twoRangeData.put("11, 0, 11, 11, 11, 11", 1);
		// twoRangeData.put("11, 11, 0, 11, 11, 11", 2);
		// twoRangeData.put("11, 11, 11, 0, 11, 11", 3);
		// twoRangeData.put("11, 11, 11, 11, 0, 11", 4);
		// twoRangeData.put("11, 11, 11, 11, 11, 0", 5);
		//
		// twoRangeData.put("0, 22, 22, 22, 22, 22", 0);
		// twoRangeData.put("22, 0, 22, 22, 22, 22", 1);
		// twoRangeData.put("22, 22, 0, 22, 22, 22", 2);
		// twoRangeData.put("22, 22, 22, 0, 22, 22", 3);
		// twoRangeData.put("22, 22, 22, 22, 0, 22", 4);
		// twoRangeData.put("22, 22, 22, 22, 22, 0", 5);

	}

	public AI_B() {
		try {
			fieldInfo = new int[19][19];
			AIrobot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void setInfo(int[][] nowPlayInfo) {
		fieldInfo = deepCopy(nowPlayInfo);
		analyze();
	}

	private void analyze() {
		setting();

		xFourDetect();
		xDectect();

		yFourDetect();
		yDectect();

		rsFourDetect();
		rsDectect();

		lsFourDetect();
		lsDectect();

		findPoint();
		AIClick();
		clearAll();
	}

	private void AIClick() {
		// (20 + i*40, 10 + j*40)
		int theLocationX = 0;
		int theLocationY = 0;
		if (subLocation.size() == 1) {
			theLocationX = subLocation.get(0)[1];
			theLocationY = subLocation.get(0)[0];
			System.out.println("inClick == Sub.size() : " + subLocation.size());
		} else if (subLocation.size() > 1) {
			int randomIndex = (int) (Math.random() * subLocation.size() - 1);
			theLocationX = subLocation.get(randomIndex)[1];
			theLocationY = subLocation.get(randomIndex)[0];
			System.out.println("inClick == Sub.size() : " + subLocation.size());
		}

		AIrobot.mouseMove(55 + theLocationX * 40, 125 + theLocationY * 40);
		AIrobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		AIrobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		// robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//좌클릭 다운
		// robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);//좌클릭 업
		// robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//좌클릭 다운
		// robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);//좌클릭 업
	}

	private void clearAll() {
		tempLocation.clear();
		opLocation.clear();
		subLocation.clear();
		effectiveLocation.clear();

		foolLocation.clear();
		twoRangeLocation.clear();

		xInfo = new int[19][19];
		yInfo = new int[19][19];
		lsInfo = new int[19][19];
		rsInfo = new int[19][19];
	}

	// AI가 흑돌인 경우 적돌의 위치를 계산해서 평균 좌표로 낙하
	private void firstPoint() {
		int NPC_count = 0;
		int NPC_centerCount = 0;
		int myMax = 0;
		int x, y;

		ArrayList<int[]> NPCLocation = new ArrayList<int[]>();

		// 센터에 5x5로 몰려 있는지 검사
		for (y = 7; y < 12; y++) {
			for (x = 7; x < 12; x++) {
				if (fieldInfo[y][x] == 99) {
					NPC_centerCount++;
				}
			}
		}
		// 중앙에 적돌착수가 3개 이하로 되었을 때 평균 좌표 계
		if (NPC_centerCount < 4) {
			for (y = 0; y < 19; y++) {
				for (x = 0; x < 19; x++) {
					if (fieldInfo[y][x] == 99) {
						NPC_count++;
						NPCLocation.add(new int[] { y, x });
						System.out.println("NPC =" + NPC_count);
					}
				}
			}
			if (NPC_count == 0) { // 착수된 적돌이 없을 때
				for (y = 8; y < 11; y++) {
					for (x = 8; x < 11; x++) {
						// 탐색 위치가 착수된 돌이면 생략
						if (fieldInfo[y][x] == 11 || 22 == fieldInfo[y][x] || 99 == fieldInfo[y][x]) {
							continue;
						}

						// 최대값을 찾았을 때 갱신 필터
						// myMax 는 0부터 시작하여 양수(내 기준 돌)만 고려함
						if (xInfo[y][x] > myMax) {
							System.out.println("my x clear");
							tempLocation.clear();
							myMax = xInfo[y][x];
						}
						if (yInfo[y][x] > myMax) {
							System.out.println("my y clear");
							tempLocation.clear();
							myMax = yInfo[y][x];
						}
						if (lsInfo[y][x] > myMax) {
							System.out.println("my ls clear");
							tempLocation.clear();
							myMax = lsInfo[y][x];
						}
						if (rsInfo[y][x] > myMax) {
							System.out.println("my rs clear");
							tempLocation.clear();
							myMax = rsInfo[y][x];
						}
						// 최대값과 동일 했을때, 저장
						if (xInfo[y][x] == myMax) {
							tempLocation.add(new int[] { y, x, Stone.DirectionX });
						}
						if (yInfo[y][x] == myMax) {
							tempLocation.add(new int[] { y, x, Stone.DirectionY });
						}
						if (lsInfo[y][x] == myMax) {
							tempLocation.add(new int[] { y, x, Stone.DirectionLS });
						}
						if (rsInfo[y][x] == myMax) {
							tempLocation.add(new int[] { y, x, Stone.DirectionRS });
						}
					}
				}
			} // if (NPC_count == 0) { // 착수된 적돌이 없을 떄
				// 착수된 적돌이 있을때
			else {
				int averageX = 0;
				int averageY = 0;
				double targetX = 0;
				double targetY = 0;
				double sumX = 0;
				double sumY = 0;

				for (int[] pointInfo : NPCLocation) {
					targetX = pointInfo[1];
					targetY = pointInfo[0];
					if (targetX > 9) {
						sumX += (targetX / 2);
					} else if (targetX < 9) {
						sumX += ((18 - targetX) / 2 + targetX);
					} else {
						sumX += targetX;
					}
					if (targetY > 9) {
						sumY += (targetY / 2);
					} else if (targetY < 9) {
						sumY += ((18 - targetY) / 2 + targetY);
					} else {
						sumY += targetY;
					}

				}
				averageX = (int) (sumX / NPC_count);
				averageY = (int) (sumY / NPC_count);
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (99 != fieldInfo[averageY + i][averageX + j])
							tempLocation.add(new int[] { averageY + i, averageX + j });
					}
				}

			}

		} // 센터에 3개 이하일때의 if문의 끝
		else {
			// 4 14 int randomIndex = (int) (Math.random() * subLocation.size() - 1);
			int randomNumber = (int) (Math.random() * 2);
			int randomX, randomY;

			if (randomNumber == 0)
				randomX = 5;
			else
				randomX = 13;

			randomNumber = (int) (Math.random() * 2);
			if (randomNumber == 0)
				randomY = 5;
			else
				randomY = 13;

			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (99 != fieldInfo[randomY + i][randomX + j])
						tempLocation.add(new int[] { randomY + i, randomX + j });
				}
			}

		}

		System.out.println("ih");
		subLocation = (ArrayList<int[]>) tempLocation.clone();
		return;
	}

	private void findPoint() {
		int x, y;
		int myMax = 0;
		int opMax = 0;

		tempLocation = new ArrayList<>();
		opLocation = new ArrayList<>();
		subLocation = new ArrayList<>();
		effectiveLocation = new ArrayList<>();
		// 내 정보중 가장 큰 value를 가진 포인트를 탐색

		if (Justice.getInstance().nowCount() == 0) {
			firstPoint();
			return;
		}
		for (y = 0; y < 19; y++) {
			for (x = 0; x < 19; x++) {
				// 탐색 위치가 착수된 돌이면 생략
				if (fieldInfo[y][x] == 11 || 22 == fieldInfo[y][x] || 99 == fieldInfo[y][x]) {
					continue;
				}

				// 최대값을 찾았을 때 갱신 필터
				// myMax 는 0부터 시작하여 양수(내 기준 돌)만 고려함
				if (xInfo[y][x] > myMax) {
					System.out.println("my x clear");
					tempLocation.clear();
					myMax = xInfo[y][x];
				}
				if (yInfo[y][x] > myMax) {
					System.out.println("my y clear");
					tempLocation.clear();
					myMax = yInfo[y][x];
				}
				if (lsInfo[y][x] > myMax) {
					System.out.println("my ls clear");
					tempLocation.clear();
					myMax = lsInfo[y][x];
				}
				if (rsInfo[y][x] > myMax) {
					System.out.println("my rs clear");
					tempLocation.clear();
					myMax = rsInfo[y][x];
				}
				// 최대값과 동일 했을때, 저장
				if (xInfo[y][x] == myMax) {
					tempLocation.add(new int[] { y, x, Stone.DirectionX });
				}
				if (yInfo[y][x] == myMax) {
					tempLocation.add(new int[] { y, x, Stone.DirectionY });
				}
				if (lsInfo[y][x] == myMax) {
					tempLocation.add(new int[] { y, x, Stone.DirectionLS });
				}
				if (rsInfo[y][x] == myMax) {
					tempLocation.add(new int[] { y, x, Stone.DirectionRS });
				}
			}
		}

		for (int opValue = -6; opValue < 0; opValue++) {
			// value 값이 바뀌고 수행 -> 상위 value에서 탐색실패로 반복문 탈출을 못했기 때문에 재 수행
			System.out.println("opValue : " + opValue);
			for (y = 0; y < 19; y++) {
				for (x = 0; x < 19; x++) {
					if (fieldInfo[y][x] == 11 || 22 == fieldInfo[y][x] || 99 == fieldInfo[y][x]) {
						continue;
					}

					// 최대값을 찾았을 때 갱신 필터
					if (xInfo[y][x] < opMax) {
						opMax = xInfo[y][x];
					}
					if (yInfo[y][x] < opMax) {
						opMax = yInfo[y][x];
					}
					if (lsInfo[y][x] < opMax) {
						opMax = lsInfo[y][x];
					}
					if (rsInfo[y][x] < opMax) {
						opMax = rsInfo[y][x];
					}

					if (xInfo[y][x] == opValue) {
						opLocation.add(new int[] { y, x, Stone.DirectionX });
					}
					if (yInfo[y][x] == opValue) {
						opLocation.add(new int[] { y, x, Stone.DirectionY });
					}
					if (lsInfo[y][x] == opValue) {
						opLocation.add(new int[] { y, x, Stone.DirectionLS });
					}
					if (rsInfo[y][x] == opValue) {
						opLocation.add(new int[] { y, x, Stone.DirectionRS });
					}
				}
			}
			// opValue를 -6 부터 -1 까지 검색하되, 내 최대값과 겹치는 위치가 있는지 탐색
			if (opLocation.size() > 0) {
				for (int[] my : tempLocation) {
					for (int[] op : opLocation) {
						if (my[0] == op[0] && my[1] == op[1]) {
							effectiveLocation.add(new int[] { my[0], my[1] });
						}

					}
				}
				// 겹치는 위치가 하나라도 있다면 해당 opValue 검색에서 더 이상 작업을 수행하지 않고 탐색 종료
				if (effectiveLocation.size() > 0)
					break;
			}
		}

		// 내가 4이상의 자리가 있으면 무조건 공격 (방어가 하나도 안된 자리인 경우 둘다 언능 막아야함)
		// ! 이거 2자리 탐지를 안해서 뻘 공격 할 수도 있음
		if (myMax >= 5) {
			System.out.println("내가 5이상 가지고 있대요");
			subLocation = (ArrayList<int[]>) tempLocation.clone();
			return;
		}
		if (myMax >= 4 && (Justice.getInstance().nowCount() % 4 == 0)) {
			// spaceCheck(tempLocation, Stone.BLACK);
			if (subLocation.size() > 0)
				return;
		}
		// 상대방에게도 4이상의 자리가 있으면 무조건 방어
		if (opMax <= -4) {
			System.out.println("상대가 4이상 가지고 있대요");
			opLocation.clear();
			for (y = 0; y < 19; y++) {
				for (x = 0; x < 19; x++) {
					if (fieldInfo[y][x] == 11 || 22 == fieldInfo[y][x] || 99 == fieldInfo[y][x]) {
						continue;
					}

					if (xInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionX });
					}
					if (yInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionY });
					}
					if (lsInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionLS });
					}
					if (rsInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionRS });
					}
				}
			}
			
			if(opMax == -5 ) {
				subLocation = (ArrayList<int[]>) opLocation.clone();
				return;
			}
			
			System.out.println("방어 2자리 필터 검색 시작");
			spaceCheck(opLocation, Stone.WHITE);
			System.out.println("방어 2자리 필터 검색 끝");
			if (subLocation.size() > 0) {
				System.out.println("상대방에게 유효공격자리가 있습니다");
				return;
			}
		}
		System.out.println("search == before myMax  : " + myMax);
		// 내가 4연속의 자리가 있는데, 그 자리에서 2개이상의 수를 둘 수 있는 경우.
		if (myMax >= 3) {
			// 공격 2자리 필터
			System.out.println("공격 2자리 필터 검색 시작 ");
			spaceCheck(tempLocation, Stone.BLACK);
			System.out.println("공격 2자리 필터 검색 끝");
			if (subLocation.size() > 0) {
				System.out.println("우리에게 유효공격자리가 있습니다");
				return;
			}
			// 우리의 최대값이 유효공격자리가 아닌 경우
			else {
				tempLocation.clear();

				// 3 -> 2 -> 1순으로 whlie문 파괴
				while (tempLocation.size() == 0 && myMax > 0) {
					tempLocation.clear();
					myMax--;

					for (y = 0; y < 19; y++) {
						for (x = 0; x < 19; x++) {
							// 탐색 위치가 착수된 돌이면 생략
							if (fieldInfo[y][x] == 11 || 22 == fieldInfo[y][x] || 99 == fieldInfo[y][x]) {
								continue;
							}
							// 최대값과 동일 했을때, 저장
							if (xInfo[y][x] == myMax) {
								tempLocation.add(new int[] { y, x, Stone.DirectionX });
							}
							if (yInfo[y][x] == myMax) {
								tempLocation.add(new int[] { y, x, Stone.DirectionY });
							}
							if (lsInfo[y][x] == myMax) {
								tempLocation.add(new int[] { y, x, Stone.DirectionLS });
							}
							if (rsInfo[y][x] == myMax) {
								tempLocation.add(new int[] { y, x, Stone.DirectionRS });
							}
						}
					}
				}
			}

		} // if 문 끝

		if (opMax <= -3) {
			opLocation.clear();
			for (y = 0; y < 19; y++) {
				for (x = 0; x < 19; x++) {
					if (fieldInfo[y][x] == 11 || 22 == fieldInfo[y][x] || 99 == fieldInfo[y][x]) {
						continue;
					}

					if (xInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionX });
					}
					if (yInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionY });
					}
					if (lsInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionLS });
					}
					if (rsInfo[y][x] == opMax) {
						opLocation.add(new int[] { y, x, Stone.DirectionRS });
					}
				}
			}

			spaceCheck(opLocation, Stone.WHITE);
		}

		if (subLocation.size() == 0) {

			if (effectiveLocation.size() > 0) {
				subLocation = (ArrayList<int[]>) effectiveLocation.clone();
				return;
			}

			subLocation = (ArrayList<int[]>) tempLocation.clone();
		}

		System.out.println("search == myMax  : " + myMax);
		System.out.println("search == opMax : " + opMax);
		System.out.println("search == temp.size() : " + tempLocation.size());
		System.out.println("search == op.size() : " + opLocation.size());
		System.out.println("search == Sub.size() : " + subLocation.size());
		System.out.println("search == Sub.info : ");

	}

	private void spaceCheck(ArrayList<int[]> targetLocation, int StoneRole) {

		for (int[] attackCheck : targetLocation) {
			int checkYPoint = attackCheck[0];
			int checkXPoint = attackCheck[1];
			int checkDirection = attackCheck[2];

			System.out.println("attackC [y] :" + attackCheck[0]);
			System.out.println("attackC [x] :" + attackCheck[1]);

			if (checkDirection == Stone.DirectionX) {
				// 1) 체크하는 좌표의 위치가 맨 끝자리가 아니어야함
				// 돌의 4개있을 때의 케이스, 그러나 4개인 경우 끝자리를 탐색할 필요가 없음
				if (checkXPoint > 0 && checkXPoint < 18) {
					// 2-1) 4연속 체크, checkPoint의 위치가 1,2,3 이면 좌측으로 연속이 아님
					// 왼편의 0,1,2,3 4칸에 X 좌표가 있는 경우
					if (checkXPoint < 4) {
						// 시작) 'v' 11 11 11 11
						if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 4] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 'V' 11 11 11 11
							if (checkXPoint > 0 && fieldInfo[checkYPoint][checkXPoint - 1] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 왼쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 ");
								subLocation.add(attackCheck);
							}
							// 'V' 11 11 11 11 0
							else if (fieldInfo[checkYPoint][checkXPoint + 5] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른 반대편 1칸 여유탐지 : 'V' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 'v' 11 11 11 11

						// 시작) 11 'v' 11 11 11
						if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'V' 11 11 11
							if (checkXPoint > 2 && fieldInfo[checkYPoint][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 왼쪽편 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'V' 11 11 11 0
							else if (fieldInfo[checkYPoint][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 오른편 1칸 여유탐지 : 11 'V' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 'v' 11 11 11

						// 시작) 11 11 'v' 11 11
						if (checkXPoint > 1 && fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'V' 11 11
							if (checkXPoint > 3 && fieldInfo[checkYPoint][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 왼쪽편 1칸 여유탐지 : 0 11 11 'V' 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 'V' 11 11 0
							else if (fieldInfo[checkYPoint][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 오른편 1칸 여유탐지 :  11 11 'V' 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 'v' 11 11

						// 시작) 11 11 11 'v' 11
						if (checkXPoint > 2 && fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 'v' 11
							if (checkXPoint > 4 && fieldInfo[checkYPoint][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 왼쪽 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if (fieldInfo[checkYPoint][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 왼쪽 1칸 여유탐지 : 11 11 11 'v' 11 0");
								subLocation.add(attackCheck);
							}

						} // 끝) 11 11 11 'v' 11
					}

					// 2-2) 4연속 체크, 위치가 16,17,18 이면 우측으로 연속이 아님
					// 15, 16, 17, 18 칸 안에 타겟이 있으면 오른방향으로 4연속은 안나타남
					else if (checkXPoint > 14) {
						if (fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 4] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 11 11 11 11 'v' 0
							if (checkXPoint < 18 && fieldInfo[checkYPoint][checkXPoint + 1] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 11 11 11 11 'v' 0");
								subLocation.add(attackCheck);
							}
							// 0 11 11 11 11 'v'
							else if (fieldInfo[checkYPoint][checkXPoint - 5] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 왼쪽 건너편 1칸 여유탐지 : 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						}

						// 11 'v' 11 11 11
						if (checkXPoint < 16 && fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'V' 11 11 11
							if (fieldInfo[checkYPoint][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 왼쪽 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'V' 11 11 11 0
							else if (checkXPoint < 15 && fieldInfo[checkYPoint][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 오른편 1칸 여유탐지 : 11 'V' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 'v' 11 11 11

						// 11 11 'v' 11 11
						if (checkXPoint < 17 && fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'V' 11 11
							if (fieldInfo[checkYPoint][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 왼쪽 1칸 여유탐지 : 0 11 11 'V' 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 'V' 11 11 0
							else if (checkXPoint < 16 && fieldInfo[checkYPoint][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 오른쪽 1칸 여유탐지 :  11 11 'V' 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 'v' 11 11

						// 11 11 11 'v' 11
						if (fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'V' 11 11 11
							if (checkXPoint < 17 && fieldInfo[checkYPoint][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 왼쪽 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'V' 11 11 11 0
							else if (fieldInfo[checkYPoint][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 기준, 오른쪽 1칸 여유탐지 : 11 'V' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 11'v' 11

					}
					// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
					else if (checkXPoint > 3 && checkXPoint < 15) {
						// 왼쪽으로 연속인가?
						if (fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 4] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 11 11 11 11 'v' 0
							if (fieldInfo[checkYPoint][checkXPoint + 1] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 왼쪽 2칸 여유탐지 : 11 11 11 11 'v' 0");
								subLocation.add(attackCheck);
							}
							// 0 11 11 11 11 'v'
							else if (checkXPoint > 5 && fieldInfo[checkYPoint][checkXPoint - 5] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 왼쪽 2칸 여유탐지 : 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						}
						// 오른쪽으로 연속인가?
						else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 4] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 'V' 11 11 11 11
							if (fieldInfo[checkYPoint][checkXPoint - 1] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 : 0 'V' 11 11 11 11 ");
								subLocation.add(attackCheck);
							}
							// 'V' 11 11 11 11 0
							else if (checkXPoint < 14 && fieldInfo[checkYPoint][checkXPoint + 5] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 : 'V' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						}
						// 11 'v' 11 11 11 케이스
						else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'v' 11 11 11
							if (fieldInfo[checkYPoint][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 :  0 11 'v' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'v' 11 11 11 0
							else if (fieldInfo[checkYPoint][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 :   11 'v' 11 11 11 0 ");
								subLocation.add(attackCheck);
							}
						}
						// 11 11 'v' 11 11 케이스
						else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'v' 11 11 케이스
							if (fieldInfo[checkYPoint][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 :   0 11 11 'v' 11 11  ");
								subLocation.add(attackCheck);
							}
							// 11 11 'v' 11 11 0
							else if (fieldInfo[checkYPoint][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 :  11 11 'v' 11 11 0");
								subLocation.add(attackCheck);
							}
						}
						// 11 11 11 'v' 11
						else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 'v' 11 케이스
							if (fieldInfo[checkYPoint][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 :  0 11 11 11 'v' 11");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if (fieldInfo[checkYPoint][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === x 축 오른쪽 2칸 여유탐지 :  11 11 11 'v' 11  0");
								subLocation.add(attackCheck);
							}
						}
					}
				}
//				// 5개 연속일 때
//				if (checkXPoint < 5) {
//					if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 4] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 5] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 0 'V' 11 11 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
//				// 2-2) 4연속 체크, 위치가 16,17,18 이면 우측으로 연속이 아님
//				else if (checkXPoint > 13) {
//					if (fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 4] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 5] == StoneRole) {
//						// 3) 공간 여유 2칸 체크
//						// 11 11 11 11 'v' 0
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 왼쪽 1칸 여유탐지 : 11 11 11 11 11 'v' 0");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
//				// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
//				else if (checkXPoint > 4 && checkXPoint < 14) {
//					// 왼쪽으로 연속인가?
//					if (fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 4] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 5] == StoneRole) {
//						// 3) 공간 여유 2칸 체크
//						// 11 11 11 11 11 'v' 0
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 왼쪽 1칸 여유탐지 : 11 11 11 11 11 'v' ");
//							subLocation.add(attackCheck);
//						}
//					}
//					// 오른쪽으로 연속인가?
//					else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 4] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 5] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 0 'V' 11 11 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 'V' 11 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 4] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 'v' 11 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 11 'v' 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 'v' 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 11 'v' 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 4] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//							&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 11 11 'v' 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
			} // x축 탐지 끝
			else if (checkDirection == Stone.DirectionY) {
				// 1) 체크하는 좌표의 위치가 맨 끝자리가 아니어야함 (
				if (checkYPoint > 0 && checkYPoint < 18) {
					// 2-1) 4연속 체크, checkPoint의 위치가 1,2,3 이면 위쪽으로 연속이 아님
					if (checkYPoint < 4) {
						// 'v' 11 11 11 11
						if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 4][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 'v' 11 11 11 11 0
							if (fieldInfo[checkYPoint + 5][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 : 'v' 11 11 11 11 0 ");
								subLocation.add(attackCheck);
							}
							// 0 'v' 11 11 11 11
							else if (fieldInfo[checkYPoint - 1][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  0 'v' 11 11 11 11 ");
								subLocation.add(attackCheck);
							}
						}

						// 11 'v' 11 11 11
						if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'V' 11 11 11
							if (checkYPoint > 2 && fieldInfo[checkYPoint - 2][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 왼쪽 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'V' 11 11 11 0
							else if (fieldInfo[checkYPoint + 4][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 오른쪽 1칸 여유탐지 : 11 'V' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 'v' 11 11 11

						// 11 11 'v' 11 11
						if (checkYPoint > 1 && fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'V' 11 11
							if (checkYPoint > 3 && fieldInfo[checkYPoint - 3][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 왼쪽 1칸 여유탐지 : 0 11 11 'V' 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 'V' 11 11 0
							else if (fieldInfo[checkYPoint + 3][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 오른쪽 1칸 여유탐지 :  11 11 'V' 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 'v' 11 11

						// 11 11 11 'v' 11
						if (checkYPoint > 2 && fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'V' 11 11 11
							if (fieldInfo[checkYPoint + 2][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 왼쪽 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'V' 11 11 11 0
							else if (checkYPoint > 3 && fieldInfo[checkYPoint - 4][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 오른쪽 1칸 여유탐지 : 11 'V' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 11'v' 11
					}

					// 2-2) 4연속 체크, 위치가 16,17,18 이면 아래로 연속이 아님
					else if (checkYPoint > 14) {
						// 11 11 11 11 'v'
						if (fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 4][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 0 11 11 11 11 'v'
							if (fieldInfo[checkYPoint - 5][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ==== y  위 축 2칸 여유탐지 : 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);

							}
							// 11 11 11 11 'v' 0
							else if (fieldInfo[checkYPoint + 1][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ==== y  위 축 2칸 여유탐지 : 11 11 11 11 'v' 0");
								subLocation.add(attackCheck);
							}
						}

						// 11 'v' 11 11 11
						if (checkYPoint < 16 && fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'V' 11 11 11
							if (fieldInfo[checkYPoint - 2][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 왼쪽 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'V' 11 11 11 0
							else if (checkYPoint < 15 && fieldInfo[checkYPoint + 4][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 오른쪽 1칸 여유탐지 : 11 'V' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 'v' 11 11 11

						// 11 11 'v' 11 11
						if (checkYPoint < 17 && fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'V' 11 11
							if (fieldInfo[checkYPoint - 3][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 왼쪽 1칸 여유탐지 : 0 11 11 'V' 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 'V' 11 11 0
							else if (checkYPoint < 16 && fieldInfo[checkYPoint + 3][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 오른쪽 1칸 여유탐지 :  11 11 'V' 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 'v' 11 11

						// 11 11 11 'v' 11
						if (fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'V' 11 11 11
							if (checkYPoint < 17 && fieldInfo[checkYPoint + 2][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 왼쪽 1칸 여유탐지 : 0 11 'V' 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'V' 11 11 11 0
							else if (fieldInfo[checkYPoint - 4][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " === Y 축 기준, 오른쪽 1칸 여유탐지 : 11 'V' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 11'v' 11
					}
					// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
					else if (checkYPoint > 3 && checkYPoint < 15) {
						// 왼쪽으로 연속인가?
						if (fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 4][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 0 11 11 11 11 'v'
							// 익셉션
							if (checkYPoint > 4 && fieldInfo[checkYPoint - 5][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ==== y  위 축 2칸 여유탐지 : 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 11 11 'v' 0
							else if (fieldInfo[checkYPoint + 1][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ==== y  위 축 2칸 여유탐지 : 11 11 11 11 'v' 0");
								subLocation.add(attackCheck);
							}
						} else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 4][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 'v' 11 11 11 11 0
							// 익셉션
							if (checkYPoint < 14 && fieldInfo[checkYPoint + 5][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 : 'v' 11 11 11 11 0 ");
								subLocation.add(attackCheck);
							}
							// 0 'v' 11 11 11 11
							else if (fieldInfo[checkYPoint - 1][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  0 'v' 11 11 11 11 ");
								subLocation.add(attackCheck);
							}
						}
						// 11 'v' 11 11 11
						else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 11 'v' 11 11 11 0
							if (fieldInfo[checkYPoint + 4][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  11 'v' 11 11 11 0 ");
								subLocation.add(attackCheck);
							}
							// 0 11 'v' 11 11 11
							else if (fieldInfo[checkYPoint - 2][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  0 11 'v' 11 11 11  ");
								subLocation.add(attackCheck);
							}
						}
						// 11 11 'v' 11 11
						else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 11 11 'v' 11 11 0
							if (fieldInfo[checkYPoint + 3][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  11 11 'v' 11 11 0 ");
								subLocation.add(attackCheck);
							}
							// 0 11 11 'v' 11 11
							else if (fieldInfo[checkYPoint - 3][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  0 11 11 'v' 11 11  ");
								subLocation.add(attackCheck);
							}
						}
						// 11 11 11 'v' 11
						else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 11 11 11 'v' 11 0
							if (fieldInfo[checkYPoint + 2][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  11 11 11 'v' 11 0 ");
								subLocation.add(attackCheck);
							}
							// 0 11 11 11 'v' 11
							else if (fieldInfo[checkYPoint - 4][checkXPoint] == Stone.NONE) {
								System.out.println(StoneRole + " ===y 축 아래 2칸 여유탐지 :  0 11 11 11 'v' 11  ");
								subLocation.add(attackCheck);
							}
						}
					}
				}
//				// 5연속일때
//				if (checkYPoint < 5) {
//					// 'v' 11 11 11 11 11
//					if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 4][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 5][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 'v' 11 11 11 11 11 0
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ===y 축 아래 1칸 여유탐지 : 'v' 11 11 11 11 11");
//							subLocation.add(attackCheck);
//						}
//						// 0 'v' 11 11 11 11 11
//					}
//				}
//				// 2-2) 4연속 체크, 위치가 16,17,18 이면 아래로 연속이 아님
//				else if (checkYPoint > 13) {
//					// 11 11 11 11 'v'
//					if (fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 4][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 5][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2칸 체크
//						// 11 11 11 11 'v' 0
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ==== y  위 축 1칸 여유탐지 : 11 11 11 11 11 'v'");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
//				// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
//				else if (checkYPoint > 4 && checkYPoint < 14) {
//					// 왼쪽으로 연속인가?
//					if (fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 4][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 5][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2칸 체크
//						// 0 11 11 11 11 11 'v'
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ==== y  위 축 1칸 여유탐지 : 11 11 11 11 11 'v'");
//							subLocation.add(attackCheck);
//						}
//					}
//
//					else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 4][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 5][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 'v' 11 11 11 11 11 0
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ===y 축 아래 1칸 여유탐지 : 'v' 11 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 4][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 'v' 11 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ===y 축 아래 1칸 여유탐지 : 11 'v' 11 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 'v' 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ===y 축 아래 1칸 여유탐지 : 11 11 'v' 11 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint + 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 11 'v' 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ===y 축 아래 1칸 여유탐지 : 11 11 11 'v' 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					} else if (fieldInfo[checkYPoint + 1][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 4][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 3][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint] == StoneRole
//							&& fieldInfo[checkYPoint - 1][checkXPoint] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 11 11 'v' 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " ===y 축 아래 1칸 여유탐지 : 11 11 11 'v' 11 11 ");
//							subLocation.add(attackCheck);
//						}
//					}
//
//				}

			} // Y
			else if (checkDirection == Stone.DirectionLS) {
				
				int weightCompair1 = 0;
				int weightCompair2 = 0;

				if (checkXPoint == 13 && checkYPoint == 0 && 
						fieldInfo[1][14] == StoneRole && 
						fieldInfo[2][15] == StoneRole && 
						fieldInfo[3][16] == StoneRole &&
						fieldInfo[4][17] == StoneRole) {
					if (fieldInfo[5][18] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[0][13] + yInfo[0][13] + rsInfo[0][13]);
						weightCompair2 = Math.abs(xInfo[5][18] + yInfo[5][18] + rsInfo[5][18]);
						if (weightCompair1 < weightCompair2) {
							subLocation.add(new int[] { 5, 18, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						
					} else if (fieldInfo[5][18] == StoneRole) {
						subLocation.add(attackCheck);
						
					}
					return;
				}
				else if (checkXPoint == 18 && checkYPoint == 5 && 
						fieldInfo[1][14] == StoneRole && 
						fieldInfo[2][15] == StoneRole && 
						fieldInfo[3][16] == StoneRole &&
						fieldInfo[4][17] == StoneRole) {
					if (fieldInfo[0][13] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[0][13] + yInfo[0][13] + rsInfo[0][13]);
						weightCompair2 = Math.abs(xInfo[5][18] + yInfo[5][18] + rsInfo[5][18]);
						if (weightCompair1 > weightCompair2) {
							subLocation.add(new int[] { 0, 13, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						
					} else if (fieldInfo[0][13] == StoneRole) {
						subLocation.add(attackCheck);
						
					}
					return;
				}
				else if (checkXPoint == 5 && checkYPoint == 18 && 
						fieldInfo[14][1] == StoneRole && 
						fieldInfo[15][2] == StoneRole && 
						fieldInfo[16][3] == StoneRole &&
						fieldInfo[17][4] == StoneRole) {
					if (fieldInfo[13][0] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[13][0] + yInfo[13][0] + rsInfo[13][0]);
						weightCompair2 = Math.abs(xInfo[18][5] + yInfo[18][5] + rsInfo[18][5]);
						if (weightCompair1 > weightCompair2) {
							subLocation.add(new int[] { 13, 0, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						
					} else if (fieldInfo[13][0] == StoneRole) {
						subLocation.add(attackCheck);
						
					}
					return;
				}
				else if (checkXPoint == 0 && checkYPoint == 13 && 
						fieldInfo[14][1] == StoneRole && 
						fieldInfo[15][2] == StoneRole && 
						fieldInfo[16][3] == StoneRole &&
						fieldInfo[17][4] == StoneRole) {
					if (fieldInfo[18][5] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[13][0] + yInfo[13][0] + rsInfo[13][0]);
						weightCompair2 = Math.abs(xInfo[18][5] + yInfo[18][5] + rsInfo[18][5]);
						if (weightCompair1 < weightCompair2) {
							subLocation.add(new int[] { 18, 5, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						
					} else if (fieldInfo[18][5] == StoneRole) {
						subLocation.add(attackCheck);
						
					}
					return;
				}
					
				
				
				// 1) 체크하는 좌표의 위치가 맨 끝자리가 아니어야함 (

				// 예외 케이스! 4짜리를 대각선 6칸에 알맞게 끼우면 이 조건에 벗어남으로 (0,13)+(5,18),(13,0)+(18,5)처리를 해줘야함
				if (checkXPoint > 0 && checkXPoint < 18 && checkYPoint > 0 && checkYPoint < 18) {
					// 2-1) 4연속 체크, checkPoint의 왼쪽 영역 혹은 상단 이면 좌표기준 왼쪽 위방향으로는 연속이 안나타남
					if ((checkXPoint < 4 && checkYPoint < 15) || (checkXPoint < 15 && checkYPoint < 4)) {
						// 'v' 11 11 11 11
						if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint + 4][checkXPoint + 4] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 'v' 11 11 11 11
							if (checkXPoint > 0 && checkYPoint > 0
									&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 왼쪽 위 방향 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 'v' 11 11 11 11 0
							else if (checkYPoint < 14 && fieldInfo[checkYPoint + 5][checkXPoint + 5] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래 오른방향 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 'v' 11 11 11 11

						// 11 'v' 11 11 11
						if (checkXPoint > 0 && checkYPoint > 0
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'v' 11 11 11
							if (checkXPoint > 2 && checkYPoint > 2
									&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 왼쪽 위 방향 2칸 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'v' 11 11 11 0
							else if (fieldInfo[checkYPoint + 4][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 11 'v' 11 11 11

						// 11 11 'v' 11 11
						if (checkXPoint > 1 && checkYPoint > 1
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'v' 11 11
							if (checkXPoint > 2 && checkYPoint > 2
									&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 왼쪽 위 방향 2칸 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 'v' 11 11 0
							else if (fieldInfo[checkYPoint + 3][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 11 11 'v' 11 11

						// 11 11 11 'v' 11
						if (checkXPoint > 2 && checkYPoint > 2
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							System.out.println("(checkXPoint < 4 && checkYPoint < 15), 3+1");
							// 3) 공간 여유 2 체크
							// 0 11 11 11 'v' 11
							if (checkXPoint > 3 && checkYPoint > 3
									&& fieldInfo[checkYPoint - 4][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 왼쪽 위 방향 2칸 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if (fieldInfo[checkYPoint + 2][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 11 11 11 'v' 11
					}
					// 2-2) 4연속 체크, 위치가 16,17,18 이면 아래로 연속이 아님
					// (checkXPoint < 4 && checkYPoint < 15) || (checkXPoint < 15 && checkYPoint <
					// 4)
					else if ((checkXPoint > 3 && checkYPoint > 14) || (checkXPoint > 14 && checkYPoint > 3)) {
						System.out.println("checkXPoint > 3 && checkYPoint > 14");
						System.out.println("checkX : " + checkXPoint);
						System.out.println("checkY : " + checkYPoint);
						// 11 11 11 11 'v'
						if (fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint - 4][checkXPoint - 4] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 11 'v'
							if (checkXPoint > 4 && checkYPoint > 4
									&& fieldInfo[checkYPoint - 5][checkXPoint - 5] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 0  11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 11 11 'v' 0
							else if (checkXPoint < 18 && checkYPoint < 18
									&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 11 11 11 11 'v' 0");
								subLocation.add(attackCheck);
							}
						}

						// 11 'v' 11 11 11
						if (checkXPoint < 16 && checkYPoint < 16
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'v' 11 11 11
							if (fieldInfo[checkYPoint - 2][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 왼쪽 위 방향 2칸 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 'v' 11 11 11 0
							else if (checkXPoint < 15 && checkYPoint < 15
									&& fieldInfo[checkYPoint + 4][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 11 'v' 11 11 11

						// 11 11 'v' 11 11
						if (checkXPoint < 17 && checkYPoint < 17
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'v' 11 11
							if (fieldInfo[checkYPoint - 3][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 왼쪽 위 방향 2칸 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 'v' 11 11 0
							else if (checkXPoint < 16 && checkYPoint < 16
									&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 11 11 'v' 11 11

						// 11 11 11 'v' 11
						if (checkXPoint < 18 && checkYPoint < 18
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 'v' 11
							if (fieldInfo[checkYPoint - 4][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 왼쪽 위 방향 2칸 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if (checkXPoint < 17 && checkYPoint < 17
									&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						} // 11 11 11 'v' 11
					}
					// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
					else {
						System.out.println("4-14, 4-14");
						// 왼쪽으로 연속인가?
						if (checkXPoint > 3 && checkYPoint > 3
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint - 4][checkXPoint - 4] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 11 'v'
							// 익셉션
							if (checkXPoint > 4 && checkYPoint > 4
									&& fieldInfo[checkYPoint - 5][checkXPoint - 5] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 0  11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 11 11 'v' 0
							else if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 11 11 11 11 'v' 0");
								subLocation.add(attackCheck);
							}
						}
						// 오른쪽으로 연속인가?

						if (checkYPoint < 15 && checkXPoint < 15
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint + 4][checkXPoint + 4] == StoneRole) {

							// 'v' 11 11 11 11 0
							// 익셉션
							if (checkXPoint < 14 && checkYPoint < 14
									&& fieldInfo[checkYPoint + 5][checkXPoint + 5] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
							// 3) 공간 여유 2 체크
							// 0 'v' 11 11 11 11
							else if (fieldInfo[checkYPoint - 1][checkXPoint - 1] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}

						}
						// 11 'v' 11 11 11
						if (checkYPoint > 0 && checkXPoint > 0 && checkYPoint < 16 && checkXPoint < 16
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'v' 11 11 11
							if (checkXPoint > 1 && checkYPoint > 1
									&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 :  0 11 'v'  11 11 11 ");
								subLocation.add(attackCheck);
							}
							// 11 'v' 11 11 11 0
							else if (checkYPoint < 15 && checkXPoint < 15
									&& fieldInfo[checkYPoint + 4][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 11 'v' 11 11 11 0");
								subLocation.add(attackCheck);
							}
						}
						// 11 11 'v' 11 11
						if (checkYPoint > 1 && checkXPoint > 1 && checkYPoint < 17 && checkXPoint < 17
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'v' 11 11
							if (checkXPoint > 2 && checkYPoint > 2
									&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 :  0 11 11 'v'  11 11 ");
								subLocation.add(attackCheck);
							}
							// 11 11 'v' 11 11 0
							else if (checkXPoint < 16 && checkXPoint < 16
									&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 11 11 'v' 11 11 0");
								subLocation.add(attackCheck);
							}
						}
						// 11 11 11 'v' 11
						if (checkYPoint > 2 && checkXPoint > 2 && checkYPoint < 18 && checkXPoint < 18
								&& fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 'v' 11
							if (checkXPoint > 4 && checkYPoint > 4
									&& fieldInfo[checkYPoint - 4][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 0 11 11 11 'v'  11");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if (checkXPoint < 17 && checkYPoint < 17
									&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === ls 축 아래오른방향 2칸 여유탐지 : 11 11 'v' 11 11 0");
								subLocation.add(attackCheck);
							}
						}
					}
				}
//				// 5연속
//				if ((checkXPoint < 5 && checkYPoint < 14)) {
//					// 'v' 11 11 11 11
//					if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
//							&& fieldInfo[checkYPoint + 4][checkXPoint + 4] == StoneRole
//							&& fieldInfo[checkYPoint + 5][checkXPoint + 5] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 0 'v' 11 11 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 : 'v' 11 11 11 11 11");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
//				// 2-2) 4연속 체크, 위치가 16,17,18 이면 아래로 연속이 아님
//				else if ((checkXPoint > 4 && checkYPoint > 13)) {
//					// 11 11 11 11 'v'
//					if (fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
//							&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
//							&& fieldInfo[checkYPoint - 4][checkXPoint - 4] == StoneRole
//							&& fieldInfo[checkYPoint - 5][checkXPoint - 5] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 11 11 11 'v'
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 : 11 11 11 11 'v' 0");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
//				// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
//				else {
//					// 왼쪽으로 연속인가?
//					if (checkYPoint > 4 && checkXPoint > 4)
//						if (fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole
//								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
//								&& fieldInfo[checkYPoint - 4][checkXPoint - 4] == StoneRole
//								&& fieldInfo[checkYPoint - 5][checkXPoint - 5] == StoneRole) {
//							// 3) 공간 여유 2 체크
//							// 0 11 11 11 11 11 'v'
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 :11 11 11 11 11 'v'");
//								subLocation.add(attackCheck);
//							}
//						}
//					// 오른쪽으로 연속인가?
//					if (checkYPoint < 14 && checkXPoint < 14)
//						if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
//								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
//								&& fieldInfo[checkYPoint + 4][checkXPoint + 4] == StoneRole
//								&& fieldInfo[checkYPoint + 5][checkXPoint + 5] == StoneRole) {
//							// 3) 공간 여유 2 체크
//							// 'v' 11 11 11 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 : 'v' 11 11 11 11 11");
//								subLocation.add(attackCheck);
//							}
//						}
//					if (checkYPoint > 0 && checkXPoint > 0 && checkYPoint < 15 && checkXPoint < 15)
//						if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
//								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
//								&& fieldInfo[checkYPoint + 4][checkXPoint + 4] == StoneRole
//								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
//							// 3) 공간 여유 2 체크
//							// 11 'v' 11 11 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 : 11 'v' 11 11 11 11");
//								subLocation.add(attackCheck);
//							}
//						}
//					if (checkYPoint > 1 && checkXPoint > 1 && checkYPoint < 16 && checkXPoint < 16)
//						if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
//								&& fieldInfo[checkYPoint + 3][checkXPoint + 3] == StoneRole
//								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
//							// 3) 공간 여유 2 체크
//							// 11 11 'v' 11 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 : 11 11 'v' 11 11 11");
//								subLocation.add(attackCheck);
//							}
//						}
//					if (checkYPoint > 2 && checkXPoint > 2 && checkYPoint < 17 && checkXPoint < 17)
//						if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint + 2] == StoneRole
//								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
//								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
//							// 3) 공간 여유 2 체크
//							// 11 11 11 'v' 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 : 11 11 11 'v' 11 11");
//								subLocation.add(attackCheck);
//							}
//						}
//					if (checkYPoint > 3 && checkXPoint > 3 && checkYPoint < 18 && checkXPoint < 18)
//						if (fieldInfo[checkYPoint + 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint - 4][checkXPoint - 4] == StoneRole
//								&& fieldInfo[checkYPoint - 3][checkXPoint - 3] == StoneRole
//								&& fieldInfo[checkYPoint - 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint - 1][checkXPoint - 1] == StoneRole) {
//							// 3) 공간 여유 2 체크
//							// 11 11 11 'v' 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === ls 축 아래오른방향 1칸 여유탐지 : 11 11 11 11 'v' 11");
//								subLocation.add(attackCheck);
//							}
//						}
//				}

			} // LS 조건문 끝
			else if (checkDirection == Stone.DirectionRS) {
				// 예외 조건
				int weightCompair1 = 0;
				int weightCompair2 = 0;

				if (checkXPoint == 5 && checkYPoint == 0 && fieldInfo[1][4] == StoneRole && fieldInfo[2][3] == StoneRole
						&& fieldInfo[3][2] == StoneRole && fieldInfo[4][1] == StoneRole) {
					if (fieldInfo[5][0] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[5][0] + yInfo[5][0] + lsInfo[5][0]);
						weightCompair2 = Math.abs(xInfo[0][5] + yInfo[0][5] + lsInfo[0][5]);
						if (weightCompair1 > weightCompair2) {
							subLocation.add(new int[] { 5, 0, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						return;
					} else if (fieldInfo[5][0] == StoneRole) {
						subLocation.add(attackCheck);
						return;
					}
				} else if (checkXPoint == 0 && checkYPoint == 5 && fieldInfo[1][4] == StoneRole
						&& fieldInfo[2][3] == StoneRole && fieldInfo[3][2] == StoneRole
						&& fieldInfo[4][1] == StoneRole) {
					if (fieldInfo[0][5] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[5][0] + yInfo[5][0] + lsInfo[5][0]);
						weightCompair2 = Math.abs(xInfo[0][5] + yInfo[0][5] + lsInfo[0][5]);
						if (weightCompair1 < weightCompair2) {
							subLocation.add(new int[] { 0, 5, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						return;
					} else if (fieldInfo[0][5] == StoneRole) {
						subLocation.add(attackCheck);
						return;
					}
				}
				else if (checkXPoint == 18 && checkYPoint == 13 
						&& fieldInfo[14][17] == StoneRole
						&& fieldInfo[15][16] == StoneRole
						&& fieldInfo[16][15] == StoneRole
						&& fieldInfo[17][14] == StoneRole) {
					if (fieldInfo[18][13] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[18][13] + yInfo[18][13] + lsInfo[18][13]);
						weightCompair2 = Math.abs(xInfo[13][18] + yInfo[13][18] + lsInfo[13][18]);
						if (weightCompair1 > weightCompair2) {
							subLocation.add(new int[] { 18, 13, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						return;
					} else if (fieldInfo[18][13] == StoneRole) {
						subLocation.add(attackCheck);
						return;
					}
				}
				else if (checkXPoint == 13 && checkYPoint == 18 
						&& fieldInfo[14][17] == StoneRole
						&& fieldInfo[15][16] == StoneRole
						&& fieldInfo[16][15] == StoneRole
						&& fieldInfo[17][14] == StoneRole) {
					if (fieldInfo[13][18] == Stone.NONE) {
						weightCompair1 = Math.abs(xInfo[18][13] + yInfo[18][13] + lsInfo[18][13]);
						weightCompair2 = Math.abs(xInfo[13][18] + yInfo[13][18] + lsInfo[13][18]);
						if (weightCompair1 < weightCompair2) {
							subLocation.add(new int[] { 13, 18, attackCheck[2] });
						} else {
							subLocation.add(attackCheck);
						}
						return;
					} else if (fieldInfo[13][18] == StoneRole) {
						subLocation.add(attackCheck);
						return;
					}
				}

				// 1) 체크하는 좌표의 위치가 맨 끝자리가 아니어야함 (
				if (checkXPoint > 0 && checkXPoint < 18 && checkYPoint > 0 && checkYPoint < 18) {
					// 2-1) 4연속 체크, checkPoint의 위치가 1,2,3 이면 오른위쪽으로 연속이 아님
					if ((checkXPoint > 3 && checkYPoint < 4) || (checkXPoint > 14 && checkYPoint < 15)) {
						// 11 11 11 11 'v'
						if (fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 11 'v'
							if (checkYPoint < 14 && checkXPoint > 5
									&& fieldInfo[checkYPoint + 5][checkXPoint - 5] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 11 11 'v' 0
							else if (checkXPoint < 18 && checkYPoint > 0
									&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 11 11 'v'

						// 11 11 11 'v' 11
						if (checkXPoint < 18 && checkYPoint > 0
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 'v' 11
							if (checkYPoint < 15 && checkXPoint > 4
									&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if (checkXPoint < 17 && checkYPoint > 1
									&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 11 'v' 11

						// 11 11 'v' 11 11
						if (checkXPoint < 17 && checkYPoint > 1
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'v' 11 11
							if (checkYPoint < 16 && checkXPoint > 2
									&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 'v' 11 11 0
							else if (checkXPoint < 16 && checkYPoint > 2
									&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 'v' 11 11

						// 11 'v' 11 11 11
						if (checkXPoint < 16 && checkYPoint > 2
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'v' 11 11 11
							if (checkYPoint < 17 && checkXPoint > 1
									&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 'v' 11 11 11 0
							else if (checkXPoint < 15 && checkYPoint > 3
									&& fieldInfo[checkYPoint - 4][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 'v' 11 11 11
					}
					// 2-2) 4연속 체크, 위치가 16,17,18 이면 아래로 연속이 아님
					else if ((checkXPoint < 15 && checkYPoint > 14) || (checkXPoint < 4 && checkYPoint > 3)) {
						// 'v' 11 11 11 11
						if (fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint - 4][checkXPoint + 4] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 0 'v' 11 11 11 11
							if (fieldInfo[checkYPoint + 1][checkXPoint - 1] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
							// 'v' 11 11 11 11 0
							else if (fieldInfo[checkYPoint - 5][checkXPoint + 5] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
							}
						}

						// 11 11 11 'v' 11
						if (checkXPoint > 2 && checkYPoint < 16
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 11 'v' 11
							if (checkXPoint > 3 && checkYPoint < 15
									&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if (fieldInfo[checkYPoint - 2][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 11 'v' 11

						// 11 11 'v' 11 11
						if (checkXPoint > 1 && checkYPoint < 17
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 11 'v' 11 11
							if (checkXPoint > 2 && checkYPoint < 16
									&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 11 'v' 11 11 0
							else if (fieldInfo[checkYPoint - 3][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 11 'v' 11 11

						// 11 'v' 11 11 11
						if (checkXPoint > 0 && checkYPoint < 18
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole) {
							// 3) 공간 여유 2 체크
							// 0 11 'v' 11 11 11
							if (checkXPoint > 1 && checkYPoint < 17
									&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
							// 11 'v' 11 11 11 0
							else if (fieldInfo[checkYPoint - 4][checkXPoint + 4] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
								subLocation.add(attackCheck);
							}
						} // 끝) 11 'v' 11 11 11
					}
					// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
					else {

						// 왼쪽으로 연속인가?
						if (checkXPoint > 3 && checkYPoint < 15)
							if (fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
									&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
									&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
									&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == StoneRole) {
								// 3) 공간 여유 2 체크
								// 0 11 11 11 11 'v'
								if (checkXPoint > 4 && checkYPoint < 14
										&& fieldInfo[checkYPoint + 5][checkXPoint - 5] == Stone.NONE) {
									System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
									subLocation.add(attackCheck);
								}
								// 11 11 11 11 'v' 0
								else if (checkXPoint < 18 && checkYPoint > 0
										&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == Stone.NONE) {
									System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 11 11 11 11 'v'");
									subLocation.add(attackCheck);
								}
							}
						// 오른쪽으로 연속인가?
						if (checkXPoint < 15 && checkYPoint > 3
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
								&& fieldInfo[checkYPoint - 4][checkXPoint + 4] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 0 'v' 11 11 11 11
							if (checkXPoint < 14 && checkYPoint > 4
									&& fieldInfo[checkYPoint - 5][checkXPoint + 5] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 'v' 11 11 11 11 0");
								subLocation.add(attackCheck);
								// 'v' 11 11 11 11 0
							} else if (checkXPoint > 0 && checkYPoint < 18
									&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 0 'v' 11 11 11 11");
								subLocation.add(attackCheck);
							}
						}
						// 11 'v' 11 11 11
						if ((checkXPoint > 0 && checkYPoint < 18) && (checkXPoint < 16 && checkYPoint > 2))
							if (fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
									&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
									&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
									&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole) {
								// 3) 공간 여유 2칸 체크
								// 0 11 'v' 11 11 11
								if ((checkXPoint > 1 && checkYPoint < 17)
										&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == Stone.NONE) {
									System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 : 0 11 'v' 11 11 11");
									subLocation.add(attackCheck);
								}
								// 11 'v' 11 11 11 0
								else if ((checkXPoint < 15 && checkYPoint > 3)
										&& fieldInfo[checkYPoint - 4][checkXPoint + 4] == Stone.NONE) {
									System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 : 11 'v' 11 11 11 0");
									subLocation.add(attackCheck);
								}
							}
						// 11 11 'v' 11 11
						if ((checkXPoint > 1 && checkYPoint < 17) && (checkXPoint < 17 && checkYPoint > 1)
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 0 11 11 'v' 11 11
							if ((checkXPoint > 2 && checkYPoint < 16)
									&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 : 0 11 11 'v' 11 11 여기야~");
								subLocation.add(attackCheck);
							}
							// 11 11 'v' 11 11 0
							else if ((checkXPoint < 16 && checkYPoint > 2)
									&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 : 11 11 'v' 11 11 0");
								subLocation.add(attackCheck);
							}
						}
						// 11 11 11 'v' 11
						if ((checkXPoint > 2 && checkYPoint < 16) && (checkXPoint < 18 && checkYPoint > 0)
								&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
								&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole) {
							// 3) 공간 여유 2칸 체크
							// 0 11 11 11 'v' 11
							if ((checkXPoint > 3 && checkYPoint < 15)
									&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 :  0  11 11 11 'v' 11");
								subLocation.add(attackCheck);
							}
							// 11 11 11 'v' 11 0
							else if ((checkXPoint < 17 && checkYPoint > 1)
									&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == Stone.NONE) {
								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 2칸 여유탐지 : 11 11 11 'v' 11 0");
								subLocation.add(attackCheck);
							}
						}

					}
				}
//				// 5연속
//				if ((checkXPoint > 4 && checkYPoint < 14)) {
//					// 11 11 11 11 11 'v'
//					if (fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
//							&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
//							&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
//							&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == StoneRole
//							&& fieldInfo[checkYPoint + 5][checkXPoint - 5] == StoneRole) {
//						// 3) 공간 여유 2 체크
//						// 11 11 11 11 'v' 0
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지 : 11 11 11 11 11 'v'");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
//				// 2-2) 4연속 체크, 위치가 16,17,18 이면 아래로 연속이 아님
//				else if (checkXPoint < 14 && checkYPoint > 4) {
//					// 'v' 11 11 11 11
//					if (fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
//							&& fieldInfo[checkYPoint - 4][checkXPoint + 4] == StoneRole
//							&& fieldInfo[checkYPoint - 5][checkXPoint + 5] == StoneRole) {
//						// 3) 공간 여유 2칸 체크
//						// 'v' 11 11 11 11 0
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지 'v' 11 11 11 11 0");
//							subLocation.add(attackCheck);
//						}
//					}
//				}
//				// 2-3) 4연속 체크, 4와 14부터는 양방향으로 연속일 수 있음
//				else {
//					// 왼쪽으로 연속인가
//					if (checkYPoint < 14 && checkXPoint > 4)
//						if (fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
//								&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == StoneRole
//								&& fieldInfo[checkYPoint + 5][checkXPoint - 5] == StoneRole) {
//							// 3) 공간 여유 2 체크
//							// 0 11 11 11 11 'v'
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지 11 11 11 11 11 'v'");
//								subLocation.add(attackCheck);
//							}
//						}
//					// 오른쪽으로 연속인가?
//					if (checkYPoint > 4 && checkXPoint < 14)
//						if (fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
//								&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
//								&& fieldInfo[checkYPoint - 4][checkXPoint + 4] == StoneRole
//								&& fieldInfo[checkYPoint - 5][checkXPoint + 5] == StoneRole) {
//							// 3) 공간 여유 2칸 체크
//							// 0 'v' 11 11 11 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지 'v' 11 11 11 11 11");
//								subLocation.add(attackCheck);
//							}
//						}
//					if ((checkXPoint > 0 && checkYPoint < 18) && (checkXPoint < 15 && checkYPoint > 3)
//							&& fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
//							&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
//							&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
//							&& fieldInfo[checkYPoint - 4][checkXPoint + 4] == StoneRole
//							&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole) {
//						// 3) 공간 여유 2칸 체크
//						// 11 'v' 11 11 11 11
//						if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//							System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지 11 'v' 11 11 11 11");
//							subLocation.add(attackCheck);
//						}
//					}
//					if (checkXPoint > 1 && checkYPoint < 17 && checkXPoint < 16 && checkYPoint < 17)
//						if (fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
//								&& fieldInfo[checkYPoint - 3][checkXPoint + 3] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole) {
//							// 3) 공간 여유 2칸 체크
//							// 11 11 'v' 11 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지  11 11 'v' 11 11 11");
//								subLocation.add(attackCheck);
//							}
//						}
//					if (checkXPoint > 2 && checkYPoint < 16 && checkXPoint < 17 && checkYPoint > 1)
//						if (fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint - 2][checkXPoint + 2] == StoneRole
//								&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole) {
//							// 3) 공간 여유 2칸 체크
//							// 11 11 11 'v' 11 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지 11 11 11 'v' 11 11");
//								subLocation.add(attackCheck);
//							}
//						}
//					if (checkXPoint > 3 && checkYPoint < 15 && checkXPoint < 18 && checkYPoint > 0)
//						if (fieldInfo[checkYPoint - 1][checkXPoint + 1] == StoneRole
//								&& fieldInfo[checkYPoint + 4][checkXPoint - 4] == StoneRole
//								&& fieldInfo[checkYPoint + 3][checkXPoint - 3] == StoneRole
//								&& fieldInfo[checkYPoint + 2][checkXPoint - 2] == StoneRole
//								&& fieldInfo[checkYPoint + 1][checkXPoint - 1] == StoneRole) {
//							// 3) 공간 여유 2칸 체크
//							// 11 11 11 11 'v' 11
//							if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//								System.out.println(StoneRole + " === rs 축 왼쪽아래방향 1칸 여유탐지 11 11 11 11 'v' 11 ");
//								subLocation.add(attackCheck);
//							}
//						}
//				}
			} // rs 조건문 끝

		} // for 문 끝
	}

	private void sumUpInfo() {
		SumInfo = deepCopy(fieldInfo);
		System.out.println("-------------------------Sum-----------------");
		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {

				if (xInfo[y][x] == fieldInfo[y][x] && yInfo[y][x] == fieldInfo[y][x] && lsInfo[y][x] == fieldInfo[y][x]
						&& rsInfo[y][x] == fieldInfo[y][x])
					continue;

				if (xInfo[y][x] != 0 && xInfo[y][x] != -1 && xInfo[y][x] != 1)
					SumInfo[y][x] += xInfo[y][x];
				if (yInfo[y][x] != 0 && yInfo[y][x] != -1 && yInfo[y][x] != 1)
					SumInfo[y][x] += yInfo[y][x];
				if (lsInfo[y][x] != 0 && lsInfo[y][x] != -1 && lsInfo[y][x] != 1)
					SumInfo[y][x] += lsInfo[y][x];
				if (rsInfo[y][x] != 0 && rsInfo[y][x] != -1 && rsInfo[y][x] != 1)
					SumInfo[y][x] += rsInfo[y][x];
			}
		}

		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {
				System.out.print(String.format("%3d", SumInfo[y][x]));
			}
			System.out.println(" ");
		}
	}

	private void xFourDetect() {

		String Col = new String();
		int ColTemp[] = new int[19];

		String Col2 = new String();
		int ColTemp2[] = new int[19];

		int startPoint = 0;
		int zeroPoint = 0;

		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {
				ColTemp[x] = fieldInfo[y][x];
				Col = Arrays.toString(ColTemp);

				ColTemp2[x] = fieldInfo[y][x];
				Col2 = Arrays.toString(ColTemp2);
			}

			for (String pattern : foolData.keySet()) {

				if (Col.contains(pattern)) {
					System.out.println("---------있다--------");
					startPoint = (Col.indexOf(pattern) - 1) / 3;
					xFool_X = startPoint + foolData.get(pattern);
					yFool_X = y;
					foolLocation.add(new int[] { xFool_X, yFool_X });
					System.out.println("바보수 위치 x : " + xFool_X);
					System.out.println("바보수 위치 y ; " + yFool_X);
				}
			}

			for (String pattern2 : twoRangeData.keySet()) {
				if (Col2.contains(pattern2)) {
					System.out.println("----------있다-----------");
					startPoint = (Col2.indexOf(pattern2) - 1) / 3;
					xTwoRangeData_X = startPoint + twoRangeData.get(pattern2);
					yTwoRangeData_X = y;
					twoRangeLocation.add(new int[] { xTwoRangeData_X, yTwoRangeData_X });
					System.out.println("두개 위치 x : " + xTwoRangeData_X);
					System.out.println("두개 위치 y ; " + yTwoRangeData_X);
				}
			}

		}
	}

	private void yFourDetect() {

		String Col = new String();
		int ColTemp[] = new int[19];

		String Col2 = new String();
		int ColTemp2[] = new int[19];

		int startPoint = 0;

		for (int x = 0; x < 19; x++) {
			for (int y = 0; y < 19; y++) {
				ColTemp[y] = fieldInfo[y][x];
				Col = Arrays.toString(ColTemp);
				ColTemp2[y] = fieldInfo[y][x];
				Col2 = Arrays.toString(ColTemp2);
			}

			for (String pattern : foolData.keySet()) {
				if (Col.contains(pattern)) {
					// System.out.println("있다");
					startPoint = (Col.indexOf(pattern) - 1) / 3;
					yFool_Y = startPoint + foolData.get(pattern);
					xFool_Y = x;
					foolLocation.add(new int[] { xFool_Y, yFool_Y });
					// System.out.println("바보수 위치 x : " + xFool_Y);
					// System.out.println("바보수 위치 y ; " + yFool_Y);
				}
			}

			for (String pattern : twoRangeData.keySet()) {
				if (Col2.contains(pattern)) {
					// System.out.println("있다");
					startPoint = (Col2.indexOf(pattern) - 1) / 3;
					yTwoRangeData_Y = startPoint + twoRangeData.get(pattern);
					xTwoRangeData_Y = x;
					twoRangeLocation.add(new int[] { xTwoRangeData_Y, yTwoRangeData_Y });
					// System.out.println("바보수 위치 x : " + xFool_Y);
					// System.out.println("바보수 위치 y ; " + yFool_Y);
				}
			}

		}
	}

	private void rsFourDetect() {

		String Col = new String();
		int ColTemp[] = new int[38];

		String Col2 = new String();
		int ColTemp2[] = new int[38];

		int startPoint = 0;

		for (int x = 0, y = 0; x < 19 && y < 19;) {

			int xt = x;
			int yt = y;

			while (xt > -1 && yt < 19) {

				if (y < 1) {
					ColTemp[yt] = fieldInfo[yt][xt];
					Col = Arrays.toString(ColTemp);

					ColTemp2[yt] = fieldInfo[yt][xt];
					Col2 = Arrays.toString(ColTemp2);
				}

				if (y > 0 && y < 19) {
					ColTemp[18 - xt] = fieldInfo[yt][xt];
					Col = Arrays.toString(ColTemp);

					ColTemp2[18 - xt] = fieldInfo[yt][xt];
					Col2 = Arrays.toString(ColTemp2);
				}

				xt--;
				yt++;
			}

			if (x == 18) {
				y++;

			}

			else
				x++;

			if (y < 1) {
				for (String pattern : foolData.keySet()) {
					if (Col.contains(pattern)) {
						// System.out.println("있다");
						// System.out.println((Col.indexOf(pattern)-1)/3);
						startPoint = (Col.indexOf(pattern) - 1) / 3;
						xFool_RS = x - foolData.get(pattern) - startPoint - 1;
						yFool_RS = startPoint + foolData.get(pattern);
						foolLocation.add(new int[] { xFool_RS, yFool_RS });
						// System.out.println("바보수 위치 x : " + xFool_RS);
						// System.out.println("바보수 위치 y ; " + yFool_RS);
					}
				}

				for (String pattern : twoRangeData.keySet()) {
					if (Col.contains(pattern)) {
						// System.out.println("있다");
						// System.out.println((Col.indexOf(pattern)-1)/3);
						startPoint = (Col.indexOf(pattern) - 1) / 3;
						xTwoRangeData_RS = x - twoRangeData.get(pattern) - startPoint - 1;
						yTwoRangeData_RS = startPoint + twoRangeData.get(pattern);
						twoRangeLocation.add(new int[] { xTwoRangeData_RS, yTwoRangeData_RS });
						// System.out.println("바보수 위치 x : " + xFool_RS);
						// System.out.println("바보수 위치 y ; " + yFool_RS);
					}
				}
			}

			if (y > 0 && y < 19) {
				for (String pattern : foolData.keySet()) {
					if (Col.contains(pattern)) {
						startPoint = (Col.indexOf(pattern) - 1) / 3;
						// System.out.println((Col.indexOf(pattern)));
						// System.out.println("StartPoint : " + startPoint);
						xFool_RS = 18 - (startPoint + foolData.get(pattern));
						yFool_RS = y + foolData.get(pattern) - 1 + startPoint;
						foolLocation.add(new int[] { xFool_RS, yFool_RS });
						// System.out.println("바보수 위치 x : " + xFool_RS);
						// System.out.println("바보수 위치 y ; " + yFool_RS);
					}
				}

				for (String pattern : twoRangeData.keySet()) {
					if (Col.contains(pattern)) {
						startPoint = (Col.indexOf(pattern) - 1) / 3;
						// System.out.println((Col.indexOf(pattern)));
						// System.out.println("StartPoint : " + startPoint);
						xTwoRangeData_RS = 18 - (startPoint + twoRangeData.get(pattern));
						yTwoRangeData_RS = y + twoRangeData.get(pattern) - 1 + startPoint;
						twoRangeLocation.add(new int[] { xTwoRangeData_RS, yTwoRangeData_RS });
						// System.out.println("바보수 위치 x : " + xFool_RS);
						// System.out.println("바보수 위치 y ; " + yFool_RS);
					}
				}
			}
		}

	}

	private void lsFourDetect() {

		String Col = new String();
		int ColTemp[] = new int[38];

		String Col2 = new String();
		int ColTemp2[] = new int[38];
		int startPoint = 0;

		for (int x = 0, y = 18; x < 19;) {

			int xt = x;
			int yt = y;

			while (xt < 19 && yt < 19) {

				if (x < 1) {
					ColTemp[xt] = fieldInfo[yt][xt];
					Col = Arrays.toString(ColTemp);

					ColTemp2[xt] = fieldInfo[yt][xt];
					Col2 = Arrays.toString(ColTemp2);
				}

				if (x > 0 && x < 19) {
					ColTemp[yt] = fieldInfo[yt][xt];
					Col = Arrays.toString(ColTemp);

					ColTemp2[yt] = fieldInfo[yt][xt];
					Col2 = Arrays.toString(ColTemp2);
				}

				xt++;
				yt++;
			}
			if (y == 0)
				x++;
			else
				y--;

			if (x < 1) {
				for (String pattern : foolData.keySet()) {
					if (Col.contains(pattern)) {
						// System.out.println("있다");
						startPoint = (Col.indexOf(pattern) - 1) / 3;
						// System.out.println(startPoint);
						xFool_LS = startPoint + foolData.get(pattern);
						yFool_LS = y + 1 + startPoint + foolData.get(pattern);
						// System.out.println("바보수 위치 x : " + xFool_LS);
						// System.out.println("바보수 위치 y ; " + yFool_LS);
					}
				}

				for (String pattern : twoRangeData.keySet()) {
					if (Col2.contains(pattern)) {
						// System.out.println("있다");
						startPoint = (Col2.indexOf(pattern) - 1) / 3;
						// System.out.println(startPoint);
						xTwoRangeData_LS = startPoint + twoRangeData.get(pattern);
						yTwoRangeData_LS = y + 1 + startPoint + twoRangeData.get(pattern);
						twoRangeLocation.add(new int[] { xTwoRangeData_LS, yTwoRangeData_LS });
						// System.out.println("바보수 위치 x : " + xFool_LS);
						// System.out.println("바보수 위치 y ; " + yFool_LS);
					}
				}
			}

			if (x > 0 && y < 19) {
				for (String pattern : foolData.keySet()) {
					if (Col.contains(pattern)) {
						startPoint = (Col.indexOf(pattern) - 1) / 3;
						// System.out.println((Col.indexOf(pattern)));
						// System.out.println("StartPoint : " + startPoint);
						xFool_LS = x - 1 + startPoint + foolData.get(pattern);
						yFool_LS = startPoint + foolData.get(pattern);
						// System.out.println("바보수 위치 x : " + xFool_RS);
						// System.out.println("바보수 위치 y ; " + yFool_RS);
					}
				}

				for (String pattern : twoRangeData.keySet()) {
					if (Col2.contains(pattern)) {
						startPoint = (Col2.indexOf(pattern) - 1) / 3;
						// System.out.println((Col.indexOf(pattern)));
						// System.out.println("StartPoint : " + startPoint);
						xTwoRangeData_LS = x - 1 + startPoint + twoRangeData.get(pattern);
						yTwoRangeData_LS = startPoint + twoRangeData.get(pattern);
						twoRangeLocation.add(new int[] { xTwoRangeData_LS, yTwoRangeData_LS });
						// System.out.println("바보수 위치 x : " + xFool_RS);
						// System.out.println("바보수 위치 y ; " + yFool_RS);
					}
				}
			}
		}
	}

	private void xDectect() {
		xInfo = deepCopy(fieldInfo);
		int x0Location = -99;
		int y0Location = -99;
		int value = 0;
		System.out.println("--------- AI xDetection--------");
		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {
				// 하얀 돌을 검출 했을때
				if (fieldInfo[y][x] == Stone.BLACK) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (x0Location == -99 && value == 0) {
						x0Location = x - 1;
						y0Location = y;
					}

					value++;

					if (value != 0 && x == 18) {
						if (x0Location != -99 && x0Location > -1)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE)
								xInfo[y0Location][x0Location] += value;
					}
				}

				// 기록하다가 하얀돌이 아닌 친구에 도달 했을때.
				else if (value != 0 && fieldInfo[y][x] != Stone.BLACK) {

					// x 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[y][x] == Stone.NONE)
						xInfo[y][x] += value;

					// x 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (x0Location != -99 && x0Location > -1)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							xInfo[y0Location][x0Location] += value;

					// 하얀돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}

			}
			x0Location = -99;
			y0Location = -99;
			value = 0;
		}
		// 까망돌 검출
		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {
				// 돌을 검출 했을때
				if (fieldInfo[y][x] == Stone.WHITE) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (x0Location == -99 && value == 0) {
						x0Location = x - 1;
						y0Location = y;
					}

					value--;

					if (value != 0 && x == 18) {
						if (x0Location != -99 && x0Location > -1)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE)
								xInfo[y0Location][x0Location] += value;
					}
				}

				// 기록하다가 하얀돌이 아닌 친구에 도달 했을때.
				else if (value != 0 && fieldInfo[y][x] != Stone.WHITE) {

					// x 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[y][x] == Stone.NONE)
						xInfo[y][x] += value;

					// x 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (x0Location != -99 && x0Location > -1)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							xInfo[y0Location][x0Location] += value;

					// 돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}

			}
			x0Location = -99;
			y0Location = -99;
			value = 0;
		} // 까망돌 검출 종료

		for (int[] foolPoint : foolLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == foolPoint[0] && y == foolPoint[1]) {
						xInfo[y][x] = 0;
					}
				}
			}
		}
		foolLocation.clear();
		for (int[] doublePoint : twoRangeLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == doublePoint[0] && y == doublePoint[1]) {
						xInfo[y][x] = 5;
					}
				}
			}
		}
		twoRangeLocation.clear();

		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {

				System.out.print(String.format("%3d", xInfo[y][x]));
			}
			System.out.println(" ");
		}
	}

	private void yDectect() {
		// 정보를 검출 할 때마다 받아와야함
		yInfo = deepCopy(fieldInfo);
		int y0Location = -99;
		int x0Location = -99;
		int value = 0;

		System.out.println("--------- AI yDetection--------");
		for (int x = 0; x < 19; x++) {
			for (int y = 0; y < 19; y++) {
				// 하얀 돌을 검출 했을때
				if (fieldInfo[y][x] == Stone.BLACK) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (y0Location == -99 && y > 0 && value == 0) {
						y0Location = y - 1;
						x0Location = x;
					}

					value++;

					if (value != 0 && y == 18) {
						if (y0Location != -99 && y0Location > -1)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE)
								yInfo[y0Location][x0Location] += value;
					}
				}
				// 기록하다가 하얀돌이 아닌 친구에 도달 했을때.
				else if (value != 0 && fieldInfo[y][x] != Stone.BLACK) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[y][x] == Stone.NONE)
						yInfo[y][x] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (y0Location != -99 && y0Location > -1)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							yInfo[y0Location][x0Location] += value;

					// 하얀돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}
			}
			x0Location = -99;
			y0Location = -99;
			value = 0;
		}
		for (int x = 0; x < 19; x++) {
			for (int y = 0; y < 19; y++) {
				// 까망 돌을 검출 했을때
				if (fieldInfo[y][x] == Stone.WHITE) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (y0Location == -99 && y > 0 && value == 0) {
						y0Location = y - 1;
						x0Location = x;
					}

					value--;

					if (value != 0 && y == 18) {
						if (y0Location != -99 && y0Location > -1)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE)
								yInfo[y0Location][x0Location] += value;
					}
				}
				// 기록하다가 까망돌이 아닌 친구에 도달 했을때.
				else if (value != 0 && fieldInfo[y][x] != Stone.WHITE) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[y][x] == Stone.NONE)
						yInfo[y][x] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (y0Location != -99 && y0Location > -1)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							yInfo[y0Location][x0Location] += value;

					// 돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}
			}
			x0Location = -99;
			y0Location = -99;
			value = 0;
		}

		for (int[] foolPoint : foolLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == foolPoint[0] && y == foolPoint[1]) {
						yInfo[y][x] = 0;
					}
				}
			}
		}
		foolLocation.clear();

		for (int[] doublePoint : twoRangeLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == doublePoint[0] && y == doublePoint[1]) {
						yInfo[y][x] = 5;
					}
				}
			}
		}
		twoRangeLocation.clear();

		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {

				System.out.print(String.format("%3d", yInfo[y][x]));
			}
			System.out.println(" ");
		}
	}

	private void rsDectect() {
		// 정보를 검출 할 때마다 받아와야함
		rsInfo = deepCopy(fieldInfo);
		int x0Location = -99;
		int y0Location = -99;
		int value = 0;
		System.out.println("--------- AI rsDetection--------");
		// 우상 대각선 방향 검출 (특수식)
		// 흰돌 탐지
		for (int x = 0, y = 0; x < 19 && y < 19;) {

			int xt = x;
			int yt = y;

			while (xt > -1 && yt < 19) {
				// 하얀 돌을 검출 했을때
				if (fieldInfo[yt][xt] == Stone.BLACK) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임

					// x좌표가 증가하기 때문에 최대값을 가질때의 우측은 기록 할 필요가 없음
					// y좌표는 감소하기 때문에 최소 값을 가질때 상단은 알 필요가 없음
					if (y0Location == -99 && yt > 0 && xt < 18 && value == 0) {
						y0Location = yt - 1;
						x0Location = xt + 1;
					}

					value++;
					if (xt == 18 || yt == 0) {
						if (y0Location != -99 && x0Location != -99)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE) {
								lsInfo[y0Location][x0Location] += value;
								x0Location = -99;
								y0Location = -99;
								value = 0;
							}

					}
				} // 검사 끝
				else if (value != 0 && fieldInfo[yt][xt] != Stone.BLACK) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[yt][xt] == Stone.NONE && yt != 0 && xt != 18)
						rsInfo[yt][xt] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					// 둘중에 하나라도 쓰레기 값을 가지면 필터링
					if (y0Location != -99 && x0Location != -99)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							rsInfo[y0Location][x0Location] += value;

					// 하얀돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}
				xt--;
				yt++;
			}
			if (x == 18)
				y++;
			else
				x++;
		} // 흰돌 탐색의 끝

		// 까망돌 탐지
		for (int x = 0, y = 0; x < 19 && y < 19;) {

			int xt = x;
			int yt = y;

			while (xt > -1 && yt < 19) {
				// 하얀 돌을 검출 했을때
				if (fieldInfo[yt][xt] == Stone.WHITE) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임

					// x좌표가 증가하기 때문에 최대값을 가질때의 우측은 기록 할 필요가 없음
					// y좌표는 감소하기 때문에 최소 값을 가질때 상단은 알 필요가 없음
					if (y0Location == -99 && yt > 0 && xt < 18 && value == 0) {
						y0Location = yt - 1;
						x0Location = xt + 1;
					}

					value--;
					if (xt == 18 || yt == 0) {
						if (y0Location != -99 && x0Location != -99)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE) {
								lsInfo[y0Location][x0Location] += value;
								x0Location = -99;
								y0Location = -99;
								value = 0;
							}

					}

				} // 검사 끝
				else if (value != 0 && fieldInfo[yt][xt] != Stone.WHITE) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[yt][xt] == Stone.NONE && yt != 0 && xt != 18)
						rsInfo[yt][xt] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					// 둘중에 하나라도 쓰레기 값을 가지면 필터링
					if (y0Location != -99 && x0Location != -99)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							rsInfo[y0Location][x0Location] += value;

					// 돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}
				xt--;
				yt++;
			}
			if (x == 18)
				y++;
			else
				x++;
		} // 까망돌 탐색의 끝

		for (int[] foolPoint : foolLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == foolPoint[0] && y == foolPoint[1]) {
						rsInfo[y][x] = 0;
					}
				}
			}
		}
		foolLocation.clear();

		for (int[] doublePoint : twoRangeLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == doublePoint[0] && y == doublePoint[1]) {
						rsInfo[y][x] = 5;
					}
				}
			}
		}
		twoRangeLocation.clear();
		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {

				System.out.print(String.format("%3d", rsInfo[y][x]));
			}
			System.out.println(" ");
		}
	}

	private void lsDectect() {
		// 정보를 검출 할 때마다 받아와야함
		lsInfo = deepCopy(fieldInfo);
		int x0Location = -99;
		int y0Location = -99;
		int value = 0;
		System.out.println("--------- AI lsDetection--------");
		// 좌상 대각선 방향 검출 (특수식)
		// 까망 탐지
		for (int x = 0, y = 18; x < 19;) {

			int xt = x;
			int yt = y;

			while (xt < 19 && yt < 19) {
				// 하얀 돌을 검출 했을때
				if (fieldInfo[yt][xt] == Stone.BLACK) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					// x좌표가 증가하기 때문에 최소 값을 가질때의 좌측은 기록 할 필요가 없음
					// y좌표도 증가하기 때문에 최소 값을 가질때 상단은 알 필요가 없음
					if (y0Location == -99 && xt > 0 && yt > 0 && value == 0) {
						y0Location = yt - 1;
						x0Location = xt - 1;
					}

					value++;

					if (xt == 18 || yt == 18) {
						if (y0Location != -99 && x0Location != -99)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE) {
								lsInfo[y0Location][x0Location] += value;
								x0Location = -99;
								y0Location = -99;
								value = 0;
							}

					}
				} // 검사 끝
				else if (value != 0 && fieldInfo[yt][xt] != Stone.BLACK) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[yt][xt] == Stone.NONE && xt != 0 && yt != 0)
						lsInfo[yt][xt] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					// 둘중에 하나라도 쓰레기 값을 가지면 필터링
					if (y0Location != -99 && x0Location != -99)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							lsInfo[y0Location][x0Location] += value;

					// 돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}

				xt++;
				yt++;
			}
			if (y == 0)
				x++;
			else
				y--;
		} // 까망 탐색의 끝

		// 흰돌 탐지
		for (int x = 0, y = 18; x < 19;) {

			int xt = x;
			int yt = y;

			while (xt < 19 && yt < 19) {
				// 돌을 검출 했을때
				if (fieldInfo[yt][xt] == Stone.WHITE) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					// x좌표가 증가하기 때문에 최소 값을 가질때의 좌측은 기록 할 필요가 없음
					// y좌표도 증가하기 때문에 최소 값을 가질때 상단은 알 필요가 없음
					if (y0Location == -99 && xt > 0 && yt > 0 && value == 0) {
						y0Location = yt - 1;
						x0Location = xt - 1;
					}

					value--;
					if (xt == 18 || yt == 18) {
						if (y0Location != -99 && x0Location != -99)
							if (fieldInfo[y0Location][x0Location] == Stone.NONE) {
								lsInfo[y0Location][x0Location] += value;
								x0Location = -99;
								y0Location = -99;
								value = 0;
							}

					}

				} // 검사 끝
				else if (value != 0 && fieldInfo[yt][xt] != Stone.WHITE) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[yt][xt] == Stone.NONE && xt != 0 && yt != 0)
						lsInfo[yt][xt] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					// 둘중에 하나라도 쓰레기 값을 가지면 필터링
					if (y0Location != -99 && x0Location != -99)
						if (fieldInfo[y0Location][x0Location] == Stone.NONE)
							lsInfo[y0Location][x0Location] += value;

					// 돌의 연속이 끝났음으로 인자 초기화
					x0Location = -99;
					y0Location = -99;
					value = 0;
				}

				xt++;
				yt++;
			}
			if (y == 0)
				x++;
			else
				y--;
		} // 까망돌 탐색의 끝

		for (int[] foolPoint : foolLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == foolPoint[0] && y == foolPoint[1]) {
						lsInfo[y][x] = 0;
					}
				}
			}
		}
		foolLocation.clear();

		for (int[] doublePoint : twoRangeLocation) {
			for (int y = 0; y < 19; y++) {
				for (int x = 0; x < 19; x++) {
					if (x == doublePoint[0] && y == doublePoint[1]) {
						lsInfo[y][x] = 5;
					}
				}
			}
		}
		twoRangeLocation.clear();
		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {

				System.out.print(String.format("%3d", lsInfo[y][x]));
			}
			System.out.println(" ");
		}
	}

	public static int[][] deepCopy(int[][] original) {
		if (original == null) {
			return null;
		}

		final int[][] result = new int[original.length][];
		for (int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
			// For Java versions prior to Java 6 use the next:
			// System.arraycopy(original[i], 0, result[i], 0, original[i].length);
		}
		return result;
	}
}