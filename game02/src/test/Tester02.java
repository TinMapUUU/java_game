package test;

import javax.swing.JOptionPane;

import mmcardds.MMCards;

public class Tester02 {

	static String PLAYER;

	public static void main(String[] args) {
		MMCards game = new MMCards();
		
		 PLAYER = JOptionPane.showInputDialog("Please enter the name of player");
	}

}
