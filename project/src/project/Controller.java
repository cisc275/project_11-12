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
	
	int cr_y = View.frameHeight/9 * 2;
	int cr_x = View.frameWidth/5;
	int o_y = 50;
	
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
		else if (e.getSource() == view.menu2 || e.getSource() == view.menu1) {
			System.out.println("menu button pressed");
			view.menuPanel();
			
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
	        			model.p.setyIncr(-cr_y);
	        		}
	        		upflag = false;
	        		downflag = true;
	        		System.out.println("up");
	        		break;
	        		
	        	case KeyEvent.VK_DOWN:
	        		if (downflag) {
	        			model.p.setyIncr(cr_y);
	        		}
	        		downflag = false;
	        		upflag = true;
	        		System.out.println("down");
	        		System.out.println(model.p.getyIncr());
	        		System.out.println(cr_y);
	        		break;
	        		
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		if (!(model.p.getxLoc() - cr_x < 0)){
	        			model.p.setxIncr(-cr_x);
	        		}
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		if (!(model.p.getxLoc() + model.p.getimageWidth() + cr_x > View.frameWidth)) {
	        			model.p.setxIncr(cr_x);
	        		}
	        		break;
	        	case KeyEvent.VK_SPACE:
	        		model.p.setyIncr(o_y);
	        		System.out.println("space");
	        		break;
	     
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		//System.out.println("key released");
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			model.p.setyIncr(-o_y);
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