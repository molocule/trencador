package claudiaGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	
	public Bricks(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					// if value is correct
					// create particular brick inside particular position
					g.setColor(Color.BLACK);
					g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
				
					// make borders between the bricks
					g.setStroke(new BasicStroke(5));
					g.setColor(Color.GRAY);
					g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
					
				}
					
				
			}
		}
	}
	
	public void setBrickVal (int val, int row, int col) {
		map[row][col] = val;
		
	}
}
