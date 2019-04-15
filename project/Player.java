package project;

public interface Player {
	public void move();	
	public int getxLoc();
	public int getyLoc();
	public int getxIncr();
	public int getyIncr();
	public int getimageWidth();
	public int getimageHeight();
	public void setxLoc(int x);
	public void setyLoc(int y);
	public void setxIncr(int xI);
	public void setyIncr(int yI);
}