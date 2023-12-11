package mmcardds;

import javax.swing.JFrame;

public class MMCards extends JFrame {
	static int width = 1300;
	static int height = 870;
	static String PLAYER="";
	
	public MMCards() {
		setTitle("My Game");
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		add(new Settings());
		
		setVisible(true);
	}
}
