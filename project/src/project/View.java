package project;

import java.util.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JFrame{
	Model model;
	DrawPanel menuPanel, game1panel, game2panel;
	static String currentpanel;
	
	int frameCount;
	int picNum;
	final static int frameWidth = 800;
	final static int frameHeight = 600;
	int imageHeight;
	int imageWidth;
	BufferedImage[][] imageArray;
	Button exit, game1, game2, ans1, ans2, menu1, menu2, replay;
	
	int drawDelay = 30;
	Action drawAction;
	
	Image g2_backimage;
	Image g1_backimage;
	Image cr_image;
	Image t_image;
	
	int rand;
	static boolean randflag = true;
	
	
	public View() {
		//load images
		try {
			g1_backimage = ImageIO.read(new File("images/g1_background.png"));
			t_image = ImageIO.read(new File("images/trout_temp.png"));
			g2_backimage = ImageIO.read(new File("images/g2_background.png"));
			cr_image = ImageIO.read(new File("images/cr_temp.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		menuPanel();
		
	}
	
	public void menuPanel() {
		//add a drawpanel
				menuPanel = new DrawPanel();
				menuPanel.setLayout(null);
				menuPanel.setBackground(Color.darkGray);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(frameWidth, frameHeight);
				
				//add buttons
				game1 = new Button("Game 1: Osprey");
				game1.setBounds(200,50,400,100);
				menuPanel.add(game1);
				
				game2 = new Button("Game 2: Clapper Rail");
				game2.setBounds(200,200,400,100);
				menuPanel.add(game2);
				
				
				add(menuPanel);
				currentpanel = "m";
				menuPanel.setFocusable(true); //allows for button presses
				menuPanel.requestFocus();
				setVisible(true);
				
				drawAction = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						if(getContent() == "g2" || getContent() == "g1") {
							repaint();
							//System.out.println("action");
							model.updateGame();
						}
						
				}};
	}
	
	public void addModelToView(Model m) {
		this.model = m;
	}
	
	public void game1Panel() {
		this.getContentPane().remove(menuPanel); //remove current panel
		DrawPanel game1panel = new DrawPanel();
		game1panel.setLayout(null);
		
		menu1 = new Button("main menu");
		menu1.setBounds(frameWidth-150,10, 100, 30);
		//menu1.addActionListener(this);
		
		
		game1panel.add(menu1);
		
		
		this.getContentPane().add(game1panel);
		currentpanel = "g1";
		setVisible(true);
		
		
	}
	public void game2Panel() {
		
		this.getContentPane().remove(menuPanel); //remove current panel
		DrawPanel game2panel = new DrawPanel();
		game2panel.setLayout(null);
		
		menu2 = new Button("main menu");
		menu2.setBounds(frameWidth-150,10, 100, 30);
		game2panel.add(menu2);
		
		this.getContentPane().add(game2panel);
		currentpanel = "g2";
		setVisible(true);
		
		
	}

	
	public BufferedImage createBufferedImage() {
		return new BufferedImage(1,1,1);
	}
	public static String getContent() {
		return currentpanel;
	}
	private class DrawPanel extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (getContent() == "g2") {
				
				
				g.drawImage(g2_backimage, 0,0,Color.gray,this);
				g.drawImage(cr_image, model.p.getxLoc(),model.p.getyLoc(),model.p.getimageWidth(), model.p.getimageHeight(),this);
				
				
				g.setColor(Color.RED);
				
				for(ScoringObject so : model.scoringObjects){
					g.fillRect(so.xloc, so.yloc, so.imageWidth, so.imageHeight);
				}	
			}
			
			if (getContent() == "g1") {
					
					g.drawImage(g1_backimage,0,0,Color.gray,this);
					
					//draws rectangle for bird
					g.fillRect(model.p.getxLoc(), model.p.getyLoc(), model.p.getimageWidth(), model.p.getimageHeight());
					
					for(ScoringObject so : model.scoringObjects) {
			/*			if (so.getID()== "Fish2") {
							g.drawImage(t_image, so.xloc,so.yloc,so.imageWidth, so.imageHeight,this);
						}
						else {*/
							//g.setColor(Color.YELLOW);
							//g.fillRect(so.xloc, so.yloc, so.imageWidth, so.imageHeight);
							g.drawImage(t_image, so.xloc, so.yloc, so.imageWidth, so.imageHeight, this);
						}
					}
					
				}
		}
	
	
	/**
	 * adds the Controller class as the listener to buttons in View
	 * @param the controller instance 
	 * @return none
	 * @author Brendan Azueta
	 */
	
	public void addControllertoButton(Controller c) {
		game1.addActionListener(c);
		game2.addActionListener(c);
		//ans1.addActionListener(c);
		//ans2.addActionListener(c);
		//menu1.addActionListener(c);
		//menu2.addActionListener(c);
		//replay.addActionListener(c);
	}
	
	
	
}