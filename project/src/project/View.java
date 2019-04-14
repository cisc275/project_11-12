package project;

import java.util.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame{
	DrawPanel menuPanel;
	int frameCount;
	int picNum;
	final static int frameWidth = 800;
	final static int frameHeight = 600;
	int imageHeight;
	int imageWidth;
	BufferedImage[][] imageArray;
	Button exit, game1, game2, ans1, ans2, menu, replay, instruct;
	
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
		menuPanel.setFocusable(true); //allows for button presses
		menuPanel.requestFocus();
		setVisible(true);
		
	}
	
	
	
	public BufferedImage createBufferedImage() {
		return new BufferedImage(1,1,1);
	}
	
	private class DrawPanel extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
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
		exit.addActionListener(c);
		game1.addActionListener(c);
		game2.addActionListener(c);
		ans1.addActionListener(c);
		ans2.addActionListener(c);
		menu.addActionListener(c);
		replay.addActionListener(c);
	}
	
}