package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Objects.Block;

public class Enemy {
		// bounds
		private double x = 400;
		private double y = 320;
		public double xMin = -64, xMax = 4064;

		// move speed
		private double moveSpeed = 1;

		private BufferedImage image;

		public Enemy(String s) {
			// load sprites
			try {
				image = ImageIO.read(getClass().getResourceAsStream(s));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			
			this.x += moveSpeed;
			
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
