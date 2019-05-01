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
	
	final int drawDelay = 30;
	Action drawAction;
	
	boolean upflag = false;
	boolean downflag = true;
	
	int CR_Y = View.frameHeight/9 * 2;
	int CR_X = View.frameWidth/5;
	int O_Y = 50;
	
	Controller(){
		
		this.initializeView();
		this.initializeModel();
		view.addModelToView(this.model);
	
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
					view.repaint();
					model.updateGame();
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
	
	public void initializeModel() {
		model = new Model();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.game1) {
			System.out.println("game1 button pressed");
			view.cl.show(view.panelContainer, "1");
			view.currentpanel = "g1";
			model.runGameOne();
		}
		else if (e.getSource() == view.game2) {
			System.out.println("game2 button pressed");
			view.cl.show(view.panelContainer, "2");
			view.currentpanel = "g2";
			model.runGameTwo();
		}
		else if (e.getSource() == view.menu2 || e.getSource() == view.menu1) {
			System.out.println("menu button pressed");
			view.cl.show(view.panelContainer, "0");
			view.currentpanel = "m";
			
		}
	}
	
	public String getContent() {
		return view.currentpanel;
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
	        			model.p.setyIncr(-CR_Y);
	        		}
	        		upflag = false;
	        		downflag = true;
	        		System.out.println("up");
	        		break;
	        		
	        	case KeyEvent.VK_DOWN:
	        		if (downflag) {
	        			model.p.setyIncr(CR_Y);
	        		}
	        		downflag = false;
	        		upflag = true;
	        		System.out.println("down");
	        		System.out.println(model.p.getyIncr());
	        		System.out.println(CR_Y);
	        		break;
	        		
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		if (!(model.p.getXloc() - CR_X < 0)){
	        			model.p.setxIncr(-CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		if (!(model.p.getXloc() + model.p.getImageWidth() + CR_X > View.frameWidth)) {
	        			model.p.setxIncr(CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_SPACE:
	        		model.p.setyIncr(O_Y);
	        		System.out.println("space");
	        		break;
	     
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		//System.out.println("key released");
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			model.p.setyIncr(-O_Y);
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		//System.out.println("key typed");
		
	}
	
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
		//System.out.println("main called.");
	}
}