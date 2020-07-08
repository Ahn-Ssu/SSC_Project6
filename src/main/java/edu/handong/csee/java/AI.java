package edu.handong.csee.java;

import java.awt.Point;
import java.util.Arrays;


//백색 중심 AI 입니다. 흑색 카운터임 
public class AI {

	
	private int[][] fieldInfo;
	private int[][] SumInfo;
	
	private int[][] xInfo, yInfo, lsInfo, rsInfo;

	public AI() {
		fieldInfo = new int[19][19];
	}

	public void setInfo(int[][] nowPlayInfo) {
		fieldInfo = deepCopy(nowPlayInfo);
		analyze();
	}

	private void analyze() {
		xDectect();
		yDectect();
		rsDectect();
		lsDectect();
		sumUpInfo();
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

	private void sumUpInfo() {
		SumInfo = deepCopy(fieldInfo);
		System.out.println("-------------------------Sum-----------------");
		for(int y = 0 ; y < 19 ; y ++) {
			for(int x = 0 ; x < 19 ; x++) {
				
				if(xInfo[y][x] == fieldInfo[y][x] &&
						yInfo[y][x] == fieldInfo[y][x] &&
						lsInfo[y][x] == fieldInfo[y][x] &&
						rsInfo[y][x] == fieldInfo[y][x] ) 
					continue;
					
				
					if(xInfo[y][x] != 0 && xInfo[y][x] != -1 && xInfo[y][x] != 1)
						SumInfo[y][x] += xInfo[y][x];
					if(yInfo[y][x] != 0 && yInfo[y][x] != -1 && yInfo[y][x] != 1)
						SumInfo[y][x] += yInfo[y][x];
					if (lsInfo[y][x] != 0 && lsInfo[y][x] != -1 && lsInfo[y][x] != 1)
						SumInfo[y][x] += lsInfo[y][x];
					if(rsInfo[y][x] != 0 && rsInfo[y][x] != -1 && rsInfo[y][x] != 1)
						SumInfo[y][x] += rsInfo[y][x];
				
			}
		}
		
		for(int y = 0 ; y < 19 ; y ++) {
			for(int x = 0 ; x < 19 ; x++) {
				System.out.print(String.format("%3d", SumInfo[y][x]));
			}
			System.out.println(" ");
		}
	}
	
	public void checkInfo() {
		System.out.println("----------AI Detection---------");
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.print(" ");
				System.out.print(String.format("%2d", fieldInfo[i][j]));
			}
			System.out.println(" ");
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
				if (fieldInfo[y][x] == Stone.WHITE) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (x0Location == -99 && value == 0) {
						x0Location = x - 1;
						y0Location = y;
					}

					value++;

					if (value != 0 && x == 18) {
						if (x0Location != -99 && x0Location > 0)
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
					if (x0Location != -99 && x0Location > 0)
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
				if (fieldInfo[y][x] == Stone.BLACK) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (x0Location == -99 && value == 0) {
						x0Location = x - 1;
						y0Location = y;
					}

					value--;

					if (value != 0 && x == 18) {
						if (x0Location != -99 && x0Location > 0)
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
					if (x0Location != -99 && x0Location > 0)
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
				if (fieldInfo[y][x] == Stone.WHITE) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (y0Location == -99 && y > 0 && value == 0) {
						y0Location = y - 1;
						x0Location = x;
					}

					value++;
					
					if(value != 0 && y == 18 ) {
						if (y0Location != -99 && y0Location > 0 )
							if (fieldInfo[y0Location][x0Location] == Stone.NONE)
								yInfo[y0Location][x0Location] += value;
					}
				}
				// 기록하다가 하얀돌이 아닌 친구에 도달 했을때.
				else if (value != 0 && fieldInfo[y][x] != Stone.WHITE) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[y][x] == Stone.NONE)
						yInfo[y][x] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (y0Location != -99 && y0Location > 0 )
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
				if (fieldInfo[y][x] == Stone.BLACK) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					if (y0Location == -99 && y > 0 && value == 0) {
						y0Location = y - 1;
						x0Location = x;
					}

					value--;
					
					if(value != 0 && y == 18 ) {
						if (y0Location != -99 && y0Location > 0 )
							if (fieldInfo[y0Location][x0Location] == Stone.NONE)
								yInfo[y0Location][x0Location] += value;
					}
				}
				// 기록하다가 까망돌이 아닌 친구에 도달 했을때.
				else if (value != 0 && fieldInfo[y][x] != Stone.BLACK) {
					// y 검출 값을 돌의 끝 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (fieldInfo[y][x] == Stone.NONE)
						yInfo[y][x] += value;

					// y 검출 값을 기억해놓은 위치에 배치
					// 대신 해당 좌표가 0으로 (오브젝트가없는) 비어있어야 함
					if (y0Location != -99 && y0Location > 0 )
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

					if (x0Location == -99 && xt < 18 && value == 0) {

					}
					if (xt == 18 || yt == 18 || xt == 0 || yt == 0)
						value = 0;
					value++;

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
					if (xt == 18 || yt == 18 || xt == 0 || yt == 0)
						value = 0;

					value--;

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
		// 흰돌 탐지
		for (int x = 0, y = 18; x < 19;) {

			int xt = x;
			int yt = y;

			while (xt < 19 && yt < 19) {
				// 하얀 돌을 검출 했을때
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
					if (xt == 18 || yt == 18 || xt == 0 || yt == 0)
						value = 0;
					value++;
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
		} // 흰돌 탐색의 끝

		// 까망 탐지
		for (int x = 0, y = 18; x < 19;) {

			int xt = x;
			int yt = y;

			while (xt < 19 && yt < 19) {
				// 돌을 검출 했을때
				if (fieldInfo[yt][xt] == Stone.BLACK) {
					// 위치 기억을 아직 하지 않았으면 위치를 기억하게 시킴
					// 0에서 시작하는 경우 이전 값을 기억 할 필요가 없음
					// 저장된 value가 0일때, 첫번째 발견임
					// x좌표가 증가하기 때문에 최소 값을 가질때의 좌측은 기록 할 필요가 없음
					// y좌표도 증가하기 때문에 최소 값을 가질때 상단은 알 필요가 없음
					if (y0Location == -99 && xt > 0 && yt > 0 && value == 0) {
						y0Location = yt - 1;
						x0Location = xt - 1;
						System.out.println("xt : " + xt + " : yt : " + yt);
					}
					if (xt == 18 || yt == 18 || xt == 0 || yt == 0)
						value = 0;
					value--;
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
		} // 까망돌 탐색의 끝

		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {

				System.out.print(String.format("%3d", lsInfo[y][x]));
			}
			System.out.println(" ");
		}
	}
}
