package project;

import java.util.*;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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

//import project.View.DrawPanel;

@SuppressWarnings("serial")
public class View extends JFrame{
	Model model;
	GameObjectStorage GobjS;
	DrawPanel panelContainer, menupanel, game1panel, game2panel;
	static String currentpanel;
	
	int frameCount;
	int picNum;
	final static int frameWidth = 800;
	final static int frameHeight = 600;
	int imageHeight;
	int imageWidth;
	BufferedImage[][] imageArray;
	Button exit, game1, game2, ans1, ans2, menu1, menu2, replay;
	
	//int DRAW_DELAY = 30;
	//Action drawAction;
	
	Image g2_backimage;
	Image g1_backimage;
	Image cr_image;
	Image t_image;
	Image o_image;
	Image sw_image;
	
	int rand;
	static boolean randflag = true;
	
	CardLayout cl = new CardLayout();
	
	public View() {
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		//load images
		try {
			g1_backimage = ImageIO.read(new File("images/g1_background.png"));
			t_image = ImageIO.read(new File("images/trout_temp.png"));
			g2_backimage = ImageIO.read(new File("images/g2_background.png"));
			cr_image = ImageIO.read(new File("images/cr_temp.png"));
			o_image = ImageIO.read(new File("images/o_temp.png"));
			sw_image = ImageIO.read(new File("images/seaweed.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//set up card layout & panels
		panelContainer = new DrawPanel();
		panelContainer.setLayout(cl);
		
		createlayouts();
		
		panelContainer.add(menupanel, "0");
		panelContainer.add(game1panel, "1");
		panelContainer.add(game2panel, "2");
		
		cl.show(panelContainer, "0");
		
		this.add(panelContainer);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		/*
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if(getContent() == "g2" || getContent() == "g1") {
					repaint();
					//System.out.println("action");
					model.updateGame();
				}
		
			}
		};
		*/
	}
	
	public void createlayouts() {
		menupanel = new DrawPanel();
		menupanel.setLayout(null);
		menupanel.setBackground(Color.darkGray);
		
		game1 = new Button("Game 1: Osprey");
		game1.setBounds(200,50,400,100);
		game2 = new Button("Game 2: Clapper Rail");
		game2.setBounds(200,200,400,100);
		menupanel.add(game1);
		menupanel.add(game2);
		
		game1panel = new DrawPanel();
		game1panel.setLayout(null);
		menu1 = new Button("main menu");
		menu1.setBounds(frameWidth-150,10, 100, 30);
		game1panel.add(menu1);
		
		game2panel = new DrawPanel();
		game2panel.setLayout(null);
		menu2 = new Button("main menu");
		menu2.setBounds(frameWidth-150,10, 100, 30);
		game2panel.add(menu2);
	}
	/*
	public void addModelToView(Model m) {
		this.model = m;
	}
	*/
	public void addGameObjectStorageToView(GameObjectStorage GobjS) {
		this.GobjS = GobjS;
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

			if (getContent() == "g1") {
					g.drawImage(g1_backimage,0,0,Color.gray,this);
				
					//very temporary picture for osprey
					g.drawImage(o_image, GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(), this);
					
					for(ScoringObject so : GobjS.getScoringObjects()) {
						//System.out.println(so);
						if(so.ID.equals("Fish1") || so.ID.equals("Fish2") || so.ID.equals("Fish3"))
						{
							g.drawImage(t_image, so.xloc, so.yloc, so.imageWidth, so.imageHeight, this);
						}
						if(so.ID.equals("Seaweed1") || so.ID.equals("Seaweed2") || so.ID.equals("Seaweed3"))
						{
							g.drawImage(sw_image, so.xloc, so.yloc, so.imageWidth, so.imageHeight, this);
						}
					}
			}
			
			if (currentpanel == "g2") {
				g.drawImage(g2_backimage, 0,0,Color.gray,this);
				g.drawImage(cr_image, GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(),this);
				
				for(ScoringObject so : GobjS.getScoringObjects()){
					if (so.pointValue == 1) {
						g.setColor(Color.GREEN);
						g.fillRect(so.xloc, so.yloc, so.imageWidth, so.imageHeight);
					} else {
						g.setColor(Color.RED);
						g.fillRect(so.xloc, so.yloc, so.imageWidth, so.imageHeight);
					}
					
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