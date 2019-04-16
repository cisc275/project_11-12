package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener {
	
	Model model;
	View view;
	Timer t;
	
	boolean upflag = false;
	boolean downflag = true;
	
	Controller(){
		view = new View();
		view.addControllertoButton(this);
		view.addKeyListener(this);
		view.setFocusable(true);
		view.setFocusTraversalKeysEnabled(false);
		model = new Model();
		view.addModelToView(this.model);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.game1) {
			System.out.println("game1 button pressed");
			view.game1Panel();
			model.runGameOne();
		}
		else if (e.getSource() == view.game2) {
			System.out.println("game2 button pressed");
			view.game2Panel();
			model.runGameTwo();
		}
		else if (e.getSource() == view.instruct) {
			System.out.println("instructions button pressed");
			view.instructPanel();
		}
	}
	
	
	public void start(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(view.drawDelay, view.drawAction); //call drawAction every (drawDelay) msecs
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
	        			model.p.setyIncr(-120);
	        		}
	        		upflag = false;
	        		downflag = true;
	        		System.out.println("up");
	        		break;
	        		
	        	case KeyEvent.VK_DOWN:
	        		if (downflag) {
	        			model.p.setyIncr(120);
	        		}
	        		downflag = false;
	        		upflag = true;
	        		System.out.println("down");
	        		break;
	        		
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		if (!(model.p.getxLoc() - 200 < 0)){
	        			model.p.setxIncr(-150);
	        		}
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		if (!(model.p.getxLoc() + 300 > View.frameWidth)) {
	        			model.p.setxIncr(150);
	        		}
	        		break;
	        	case KeyEvent.VK_SPACE:
	        		model.p.setyIncr(50);
	        		System.out.println("space");
	        		break;
	     
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		//System.out.println("key released");
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		//System.out.println("key typed");
		
	}
	
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
		System.out.println("main called.");
	}
}
