package TileMap;

import java.awt.image.*;
import javax.imageio.ImageIO;

import java.awt.*;

public class Background {

	private BufferedImage image;
	
	public double x;
	public double y;
	private double dx;
	private double dy;
	
	private boolean redraw = false;

	public Background(String s) {
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	public void setPosition(double x, double y) {
		this.x = x % GamePanel.WIDTH;
		this.y = y % GamePanel.HEIGHT;
	}
	*/
	
	//scroll background
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setRedraw(boolean re) {
		redraw = re;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		//if all the way to left redraw background to avoid weird stuff
		if(redraw == true) {
			if(x < 0) {
				g.drawImage(image, (int)x + image.getWidth(), (int)y, null);
				//System.out.println((int)x);
				if(x < -image.getWidth()) 
					x = 0; // If x becomes too small, then adding GamePanel.WIDTH is no longer enough to bring it back to 
						   // the other side. Therefore, this line restricts the x-position from becoming lower than 
					       // GamePanel.WIDTH, so it will continuously redraw on the other side of the screen.
			}
			
		}
		
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
}
