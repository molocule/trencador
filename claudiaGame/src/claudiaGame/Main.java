package claudiaGame;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		theGame theGame = new theGame();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Claudia Breakout");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(theGame);
		obj.setVisible(true);
	}

}
