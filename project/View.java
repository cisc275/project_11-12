package project;

import java.util.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
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
	static DrawPanel menuPanel, game1panel, game2panel;
	static String currentpanel;
	
	int frameCount;
	int picNum;
	final static int frameWidth = 800;
	final static int frameHeight = 600;
	int imageHeight;
	int imageWidth;
	BufferedImage[][] imageArray;
	Button exit, game1, game2, ans1, ans2, menu, replay, instruct;
	
	int drawDelay = 30;
	Action drawAction;
	
	Image g2_backimage;
	Image g1_backimage;
	
	static int rand;
	static boolean randflag = true;
	
	
	public View() {
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
		
		instruct = new Button("Instructions");
		instruct.setBounds(200,350,400,100);
		menuPanel.add(instruct);
		
		add(menuPanel);
		currentpanel = "m";
		menuPanel.setFocusable(true); //allows for button presses
		menuPanel.requestFocus();
		setVisible(true);
		
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if(View.getContent() == "g2" || View.getContent() == "g1") {
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
		game1panel.setBackground(Color.blue);
		game1panel.setFocusable(true); //allows for button presses
		game1panel.requestFocus();
		this.getContentPane().add(game1panel);
		currentpanel = "g1";
		setVisible(true);
		
		
	}
	public void game2Panel() {
		
		this.getContentPane().remove(menuPanel); //remove current panel
		DrawPanel game2panel = new DrawPanel();
		game2panel.setLayout(null);
		game2panel.setBackground(Color.green);
		this.getContentPane().add(game2panel);
		currentpanel = "g2";
		setVisible(true);
		
	}
	public void instructPanel() {
		
		this.getContentPane().remove(menuPanel); //remove current panel
		DrawPanel infopanel = new DrawPanel();
		infopanel.setLayout(null);
		infopanel.setBackground(Color.gray);
		this.getContentPane().add(infopanel);
		currentpanel = "info";
		JLabel label1 = new JLabel("Osprey Game: For this game you have to eat fish to build up your energy to migrate.");
		JLabel label2 = new JLabel("Clapper Rail Game: For this game you have to feed on insects and avoid garbage and the fox");
		JFrame window = new JFrame("Instructions");
		window.setVisible(true);
		window.setSize(800,600);
		label1.setBounds(0, 0, 500, 50);
		window.add(label1);
		window.add(label2);
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
			if (View.getContent() == "g2") {
				try {
					g2_backimage = ImageIO.read(new File("images/g2_background.png"));
				} catch(IOException e) {
					e.printStackTrace();
				}
				//add background
				g.drawImage(g2_backimage, 0,0,Color.gray,this);
				//black square for clapper rail
				g.setColor(Color.BLACK);
				g.fillRect(model.cr.xloc,model.cr.yloc, 50,50);
				
				Random r = new Random();
				g.setColor(Color.RED);
				if (randflag) {
					rand = r.nextInt(8);
					randflag = false;
				}
				if (Model.count <33) {
					g.fillRect(Model.g2locations[rand].x, Model.g2locations[rand].y, 30, 30);
				}	
			}
			
			if (View.getContent() == "g1") {
					try {
						g1_backimage = ImageIO.read(new File("images/g1_background.png"));
					} catch(IOException e) {
						e.printStackTrace();
					}
					g.drawImage(g1_backimage,0,0,Color.gray,this);
					
					//draws rectangle for bird
					g.fillRect(model.p.getxLoc(), model.p.getyLoc(), model.p.getimageWidth(), model.p.getimageHeight());
					
					for(ScoringObject so : model.scoringObjects) {
						g.setColor(Color.YELLOW);
						g.fillRect(so.xloc, so.yloc, so.imageWidth, so.imageHeight);
					}
					
				}
				
			
			//update the view of the game here
		}
	}
	
	/**
	 * adds the Controller class as the listener to buttons in View
	 * @param the controller instance 
	 * @return none
	 * @author Brendan Azueta
	 */
	
	public void addControllertoButton(Controller c) {
		instruct.addActionListener(c);
		game1.addActionListener(c);
		game2.addActionListener(c);
		//ans1.addActionListener(c);
		//ans2.addActionListener(c);
		//menu.addActionListener(c);
		//replay.addActionListener(c);
	}
	
	
	
}
	