package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;


public class Controller implements ActionListener, KeyListener {
	
	Model model;
	View view;
	Timer t;
	final int drawDelay = 30; // change this to 25
	Action drawAction;
	private int clockcount = 0;
	
	boolean upflag = false;
	boolean downflag = true;
	
	int CR_Y = 150;
	int CR_X = 160;
	int O_Y = 50;
	
	Controller(){
		this.initializeView();
		this.initializeModel();
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
					view.repaint();
					view.addGameObjectStorageToView(model.getGobjS());
					model.updateGame();
					clockcount++;
					if (clockcount > 3000) { //3000*drawDelay[30] = 90000 = 1.5min
						endGame();	
					}
			}
		};
		
	}
	
	public void initializeView() {
		view = new View();
		view.addControllertoButton(this);
		view.addKeyListener(this);
		view.setFocusable(true);
		view.setFocusTraversalKeysEnabled(false);
	}
	
	public void endGame() {
		if (view.getContent() == "g1") {
			view.cl.show(view.panelContainer, "3");
			view.currentpanel = "e1";
		}
		else {
			view.cl.show(view.panelContainer, "4");
			view.currentpanel = "e2";
		}
	}
	
	public void initializeModel() {
		model = new Model();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.game1) {
			System.out.println("game1 button pressed");
			view.cl.show(view.panelContainer, "1");
			view.currentpanel = "g1";
			model.initializeGameOne();
		}
		else if (e.getSource() == view.game2) {
			System.out.println("game2 button pressed");
			view.cl.show(view.panelContainer, "2");
			view.currentpanel = "g2";
			model.initializeGameTwo();
		}
		else if (e.getSource() == view.menu2 || e.getSource() == view.menu1) {
			System.out.println("menu button pressed");
			model.getGobjS().getScoringObjects().removeAll(model.getGobjS().getScoringObjects()); //clear scoring objects
			view.cl.show(view.panelContainer, "0");
			view.currentpanel = "m";
		}
		view.initializeGameImages();
	}

	
	
	public void start(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(drawDelay, drawAction); //call drawAction every (drawDelay) msecs
				t.start();
				} 
		});	
		
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
			int k = e.getKeyCode();
			switch( k ) { 
	        	case KeyEvent.VK_UP:
	        		if (upflag) {
	        			model.getGobjS().getPlayer().setyIncr(-CR_Y);
	        		}
	        		upflag = false;
	        		downflag = true;
	        		System.out.println("up");
	        		break;
	        		
	        	case KeyEvent.VK_DOWN:
	        		if (downflag) {
	        			model.getGobjS().getPlayer().setyIncr(CR_Y);
	        		}
	        		downflag = false;
	        		upflag = true;
	        		System.out.println("down");
	        		break;
	        		
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		if (!(model.getGobjS().getPlayer().getXloc() - CR_X < 0)){
	        			model.getGobjS().getPlayer().setxIncr(-CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		if (!(model.getGobjS().getPlayer().getXloc() + model.getGobjS().getPlayer().getImageWidth() + CR_X > View.frameWidth)) {
	        			model.getGobjS().getPlayer().setxIncr(CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_SPACE:
	        		System.out.println("space");
	        		if (view.getContent() == "g2") {
	        			model.eatFoodOrTrash();
	        		}
	        		else {
	        			model.getGobjS().getPlayer().setyIncr(O_Y);
	        		}
	        		break;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_SPACE && view.getContent() == "g1") {
			model.getGobjS().getPlayer().setyIncr(-O_Y);
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
		//System.out.println("main called.");
	}
}