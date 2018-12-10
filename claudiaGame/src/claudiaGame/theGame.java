package claudiaGame;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;

public class theGame extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballPX = 120;
	private int ballPY = 350;
	private int ballDX = -1;
	private int ballDY = -2;
	
	private Bricks map;
	
	
	public theGame() {
		map = new Bricks(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		// let's draw things
		// first let's add background
		g.setColor(Color.GRAY);
		g.fillRect(1, 1, 692, 592);
		// this is the background
		
		//let's make the borders
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// now let's draw the bricks!
		map.draw((Graphics2D) g);
		
		// score board
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 580, 30);
		
		// make the bottom slider
		g.setColor(Color.PINK);
		g.fillRect(playerX, 550, 100, 8);
		
		// make the ball
		g.setColor(Color.BLACK); 
		g.fillOval(ballPX, ballPY, 20, 20);
		
		if(totalBricks <= 0) {
			play = false;
			ballDX = 0;
			ballDY = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("YOU WON!!, Score: " + score , 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Play Again", 230, 350);
					
		}
		
		
		if(ballPY > 570) {
			play = false;
			ballDX = 0;
			ballDY = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score , 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Play Again", 230, 350);
					
		}
		
		g.dispose();
		
	}
	
	public void moveRight() {
		play = true;
		playerX += 20;
	}

	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {
			
			if (new Rectangle (ballPX, ballPY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballDY = -ballDY;
			}
			
			// ball bounces if it hits a brick
			A: for (int i = 0; i<map.map.length; i++) {
				for (int j = 0; j<map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j* map.brickWidth + 80;
						int brickY = i*map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPX, ballPY, 20, 20);
						Rectangle brickRect = rect;
						
						if (ballRect.intersects(brickRect)) {
							map.setBrickVal(0, i, j);
							totalBricks --;
							score += 5;
							
							if (ballPX + 19 <= brickRect.x || ballPX + 1 >= brickRect.x + brickRect.width) {
								ballDX = - ballDX;
								
							}
							else {
								ballDY = -ballDY;
							}
							break A;
						}
					}
			}
			}
			
			// ball movement
			ballPX += ballDX;
			ballPY += ballDY;
			if (ballPX < 0) {
				ballDX = -ballDX;
				// when ball hits the left wall it bounces
			}
			
			if (ballPY < 0) {
				ballDY = -ballDY;
				// when ball hits the ceiling it bounces
			}
			if (ballPX > 670) {
				ballDX = -ballDX;
				// when ball hits the right wall it bounces
			}
		}
		
		repaint();
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// we don't need this, doesn't matter
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 590) {
				playerX = 590; 
				// make sure player does not
				// go out of bounds
			}
			else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX <= 10) {
				playerX = 10; 
				// make sure player does not
				// go out of bounds
			}
			else {
				moveLeft();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//restart game
			if (!play) {
				// use initial values 
				play = true;
				ballPX = 120;
				ballPY = 350;
				ballDX = -1;
				ballDY = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new Bricks(3, 7);
				
				repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// we don't need this, doesn't matter
		
	}
	
	
	

}
