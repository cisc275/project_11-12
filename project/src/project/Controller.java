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
	GameObjectStorage GobjS = new GameObjectStorage();
	Action drawAction;
	final int drawDelay = 30;
	private int clockcount = 0;
	
	boolean upflag = false;
	boolean downflag = true;
	
	int CR_Y = (View.frameHeight/9 * 2) + 17;
	int CR_X = View.frameWidth/5;
	int CR_Y_SPACE = 30;
	int CR_BOUND_TOP = 250;
	int CR_BOUND_BOTTOM = 250 + CR_Y;
	int O_Y = 50;
	
	Controller(){
		
		this.initializeView();
		this.initializeModel();
		view.addGameObjectStorageToView(this.GobjS);
		model.addGameObjectStorageToModel(this.GobjS);
		
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
					view.repaint();
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
			view.cl.show(view.panelContainer, "0");
			view.currentpanel = "m";
			
		}
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
		if(GobjS.p.imageHeight == model.CR_IMH) {
		
			int k = e.getKeyCode();
			switch( k ) { 
	        	case KeyEvent.VK_UP:
	        		if (upflag) {
	        			GobjS.getPlayer().setyIncr(-CR_Y);
	        		}
	        		upflag = false;
	        		downflag = true;
	        		System.out.println("up");
	        		break;
	        		
	        	case KeyEvent.VK_DOWN:
	        		if (downflag) {
	        			GobjS.getPlayer().setyIncr(CR_Y);
	        		}
	        		downflag = false;
	        		upflag = true;
	        		System.out.println("down");
	        		break;
	        		
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		if (!(GobjS.getPlayer().getXloc() - CR_X < 0)){
	        			GobjS.getPlayer().setxIncr(-CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		if (!(GobjS.getPlayer().getXloc() + GobjS.getPlayer().getImageWidth() + CR_X > View.frameWidth)) {
	        			GobjS.getPlayer().setxIncr(CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_SPACE:
	        		if((GobjS.getPlayer().getYloc() == CR_BOUND_BOTTOM) || (GobjS.getPlayer().getYloc() == CR_BOUND_TOP) )
	        		{
	        			GobjS.getPlayer().setyIncr(CR_Y_SPACE);
	        		}
	        		System.out.println("space");
	        		break;
	     
			}
		
		}
		if(GobjS.p.imageHeight == model.O_IMH) {
			
			int k = e.getKeyCode();
			switch( k ) { 
	      
	        	case KeyEvent.VK_SPACE:
	        		GobjS.getPlayer().setyIncr(O_Y);
	        		System.out.println("space");
	        		break;
			}
		
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		//System.out.println("key released");
		int key = arg0.getKeyCode();
		if(GobjS.p.imageHeight == model.O_IMH)
		{
			if(key == KeyEvent.VK_SPACE) {
				GobjS.getPlayer().setyIncr(-O_Y);
			}
		}
		
		if(GobjS.p.imageHeight == model.CR_IMH)
		{
			if(key == KeyEvent.VK_SPACE) {
				GobjS.getPlayer().setyIncr(-CR_Y_SPACE);
			}
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