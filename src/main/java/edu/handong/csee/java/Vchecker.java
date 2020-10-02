//package edu.handong.csee.java;
//
//public class Vchecker {
//
//	public Vchecker() {
//		// TODO Auto-generated constructor stub
//	}
//
//}
// AI 클래스에 있던 SpaceCheck 메소드에 있던 5연속 체크 코드 
// 일단 버렸음 

//			 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//				// 5개 연속일 때 
//				// 왼쪽으로 연속인가?
//				if (checkXPoint > 4 
//						&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 4] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 5] == StoneRole) {
//					// 3) 공간 여유 2칸 체크
//					// 11 11 11 11 11 'v' 0
//					if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//						System.out.println(StoneRole + " === x 축 왼쪽 1칸 여유탐지 : 11 11 11 11 11 'v' ");
//						subLocation.add(attackCheck);
//					}
//				}
//				// 오른쪽으로 연속인가?
//				else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 4] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 5] == StoneRole) {
//					// 3) 공간 여유 2 체크
//					// 0 'V' 11 11 11 11 11
//					if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//						System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 'V' 11 11 11 11 11 ");
//						subLocation.add(attackCheck);
//					}
//				} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 4] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//					// 3) 공간 여유 2 체크
//					// 11 'v' 11 11 11 11
//					if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//						System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 11 'v' 11 11 11 11 ");
//						subLocation.add(attackCheck);
//					}
//				} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 3] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//					// 3) 공간 여유 2 체크
//					// 11 11 'v' 11 11 11
//					if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//						System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 11 ");
//						subLocation.add(attackCheck);
//					}
//				} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint + 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//					// 3) 공간 여유 2 체크
//					// 11 11 11 'v' 11 11
//					if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//						System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 11 ");
//						subLocation.add(attackCheck);
//					}
//				} else if (fieldInfo[checkYPoint][checkXPoint + 1] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 4] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 3] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 2] == StoneRole
//						&& fieldInfo[checkYPoint][checkXPoint - 1] == StoneRole) {
//					// 3) 공간 여유 2 체크
//					// 11 11 11 11 'v' 11
//					if (fieldInfo[checkYPoint][checkXPoint] == Stone.NONE) {
//						System.out.println(StoneRole + " === x 축 오른쪽 1칸 여유탐지 : 0 'V' 11 11 11 11 11 ");
//						subLocation.add(attackCheck);
//					}
//				}
// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


//YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
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
//YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY

// LLLLLLLLLLLLLLLLLLLSSSSSSSSSSSSSSSSSS
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
// LLLLLLLLLLLLLLLLLLLSSSSSSSSSSSSSSSSSS


// RRRRRRRRRRRRRRRRRRRSSSSSSSSSSSSSSSSSS
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
// RRRRRRRRRRRRRRRRRRRSSSSSSSSSSSSSSSSSS


