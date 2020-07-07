package edu.handong.csee.java;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Stone {

	private int indexX, indexY;
	private int role;
	private ImageIcon roleIcon;
	private int[] locationNRole;
	
	// 흑 -1 , 백 +1 , 없음 0, 중립 99;
	public final static int WHITE = 11;
	public final static int BLACK = 22;
	public final static int NPC = 99;
	public final static int NONE = 0;
	
	public static final ImageIcon BlackIcon = new ImageIcon("/Users/suhyun/git/SSC_Project5/Source/blackCatStone.png");
	public static final ImageIcon WhiteIcon = new ImageIcon("/Users/suhyun/git/SSC_Project5/Source/WhiteCatStone.png");
	public static final ImageIcon NPCIcon = new ImageIcon("/Users/suhyun/git/SSC_Project5/Source/NPCCatStone - red.png");
	
	public Stone(int indexX, int indexY) {
		locationNRole = new int[3];
		locationNRole[0] = indexX;
		locationNRole[1] = indexY;
	}

	public void setMyJob(int role) {
		locationNRole[2] = role;
		
		if(role == NPC) {
			roleIcon = NPCIcon;
		}
		else if(role == BLACK){
			roleIcon = BlackIcon;
		}
		else if (role == WHITE) {
			roleIcon = WhiteIcon;
		}
		else if(role == NONE) {
			
		}
	}
	
	public int[] getLocationNRole() {
		return locationNRole;
	}

	public ImageIcon getRoleIcon() {
		return roleIcon;
	}
	
	
}
