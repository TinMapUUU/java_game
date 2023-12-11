package mmcardds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Settings extends JPanel implements ActionListener, MouseListener {
	
	Image background; 
	Timer timer;
	boolean flip = false;
	boolean check = false;
	int [] selected = new int[2];
	int count = 0;
	int index;
//
	
	Card [] card;

	int cardSize = 26;
	int cardBoomSize = 2;
	String [] lol= {"ahri", "akali", "darius", "ezeal", "jinx", "kaisa", "lux",
				    "missFortune", "rakan", "seraphine", "xayah", "zyra", 
				    "boom1"};  //boom2 ?
	
	public Settings() {
		card = new Card[cardSize];
		
		List<String> shuffled_list = Arrays.asList(lol);
		Collections.shuffle(shuffled_list);

		for (int i=0; i<cardSize; i++) {			
			card[i] = new Card(lol[i%13], i);			
			card[i].setX(52 + i *120);
			if (i>=10 && i<20) {
				card[i].setX(52+(i-10)*120);
				card[i].setY(225);
			}
			if (i>=20) {
				card[i].setX(52+(i-20)*120);
				card[i].setY(2*225);
			}
		}
		
		addMouseListener(this);
		timer = new Timer(10, this);
        timer.start();
        
        	  // LẬT THẺ(biến)
        boolean flip = false;
        
		 		// SET BACKGROUND 
		try {
			background = ImageIO.read(new File("./src/images/background02.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
//		g.setColor(Color.RED);
		g.drawImage(background, 0, 0, MMCards.width, MMCards.height, null);
		

		for (int i=0; i<cardSize; i++) {
			g.drawImage(card[i].getPicture(), card[i].getX(), card[i].getY(), 
						card[i].getWidth(), card[i].getHeight(), null);

		}
		
		Font kiten = new Font("Forte", Font.BOLD, 40);
		g.setFont(kiten);
		g.setColor(Color.MAGENTA);
		g.drawString("DMinh_MDuy_Hung", 100, 750);
		g.drawString(MMCards.PLAYER, 900, 700);
		
		
						
	}
	
	  // PHƯƠNG HƯỚNG ( SAU KHI ĐC LẬT)
	int direction = 1;
	@Override
	public void actionPerformed(ActionEvent e) {
//		for (Card the : card) {
		if(flip) {
			flip = card[index].flip();
			if(card[index].getWidth()<=0) {
				card[index].setPicture(card[index].getLol());
			}	
			
		}	
		
		if (check) 
		{
			if(card[selected[0]].getLol() != card[selected[1]].getLol())
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				
				card[selected[0]].setPicture("BG_cards");
				card[selected[1]].setPicture("BG_cards");
			}
			check = false;
			
		}
		repaint();
		
		if(count==2 && flip == false)
		{
			check = true;
			count = 0;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		flip = true;
		direction = 1;
		
		for (Card the : card) {
			if(the.collision(e.getX(), e.getY()))
			{
				selected[count] = the.getIndex();
				
				if(count==0 || (count==1 && selected[0] 
											!= selected[1]))
				{
				index = the.getIndex();
				count++;
				}
			}
		}
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
