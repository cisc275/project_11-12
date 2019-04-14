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
	
	Controller(){
		view = new View();
		view.addControllertoButton(this);
		view.addControllerToKeys(this);
		model = new Model();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.game1) {
			System.out.println("game1 button pressed");
			view.game1Panel();
		}
		else if (e.getSource() == view.game2) {
			System.out.println("game2 button pressed");
			view.game2Panel();
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
	        		System.out.println("up");
	        		break;
	        	case KeyEvent.VK_DOWN:
	        		Model.cr.yIncr = -100;
	        		System.out.println("down");
	        		break;
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		break;
	     
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("key released");
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("key typed");
		
	}
	
	
	public static void main(String[] args) {
		Controller c = new Controller();
		
		System.out.println("main called.");
	}
}
