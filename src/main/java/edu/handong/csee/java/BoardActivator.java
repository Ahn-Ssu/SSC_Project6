package edu.handong.csee.java;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BoardActivator {

	private Tile[][] setTile;

	public BoardActivator() {
		setTile = new Tile[19][19];
		makeTiles();
	}

	private void makeTiles() {

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				setTile[i][j] = new Tile(i, j);
			}
		}
	}

	public Tile[][] getSetTile() {
		return setTile;
	}

}
