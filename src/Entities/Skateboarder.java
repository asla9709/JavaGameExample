package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Objects.Block;

public class Skateboarder {
		// bounds
		private double x;
		private double y;
		public double xMin, xMax;

		// move speed
		private double moveSpeed = 1.0;

		private BufferedImage image;

		public Skateboarder(String s) {
			// load sprites
			try {
				image = ImageIO.read(getClass().getResourceAsStream(s));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void setMoveSpeed(double dx){
			moveSpeed = dx;
		}
		
		public void setBounds(int x1, int x2) {
			xMin = x1;
			xMax = x2;
		}

		public void setLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void update(ArrayList<Block> b) {
			
			if(!Player.right && !Player.left){
				this.x += moveSpeed;
			}
			if(Player.right){
				this.x += moveSpeed * 0.85;
			}
			if(Player.left){
				this.x += moveSpeed * 1.18;
			}
			
			
			if(this.x >= xMax){
				this.x = xMin;
			}
		}

		public void draw(Graphics2D g) {
			// g.drawImage(image, (int) x, (int) y, null);
			/*if(facingLeft) {
				g.drawImage(image, (int) x + image.getWidth()/2, (int) y, -image.getWidth(), image.getHeight(), null);
			}
			else {*/
				g.drawImage(image, (int)this.x, (int)y, null);
			//}
			 
		}

}
