package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Student {
		// bounds
		private double x = 200;
		private double y = 320;
		private int height = 64, width = 64;
		public double xMin = 0, xMax = 4064;
		
		// animations
		private ArrayList<BufferedImage[]> sprites;
		private final int[] numFrames = { 5 };
		private Animation animation;

		// move speed
		private double moveSpeed = 0.2;

		public Student(String s) {
			// load sprites
			try {
				
				BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(s));				
				
				sprites = new ArrayList<BufferedImage[]>();
				for (int i = 0; i < 1; i++) {

					BufferedImage[] bi = new BufferedImage[numFrames[i]];

					for (int j = 0; j < numFrames[i]; j++) {

						bi[j] = spritesheet.getSubimage(j * width, i * height,
								width, height);

					}

					sprites.add(bi);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			animation = new Animation();
			animation.setFrames(sprites.get(0));
			animation.setDelay(250);
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

		public void update() {
			if(!Player.right && !Player.left){
				this.x += moveSpeed;
			}
			if(Player.right){
				this.x -= moveSpeed * 0.8;
			}
			if(Player.left){
				this.x += moveSpeed * 6.8;
			}
			
			if(this.x >= xMax){
				this.x = xMin;
			}
			
			animation.update();
		}

		public void draw(Graphics2D g) {
			// g.drawImage(image, (int) x, (int) y, null);
			/*if(facingLeft) {
				g.drawImage(image, (int) x + image.getWidth()/2, (int) y, -image.getWidth(), image.getHeight(), null);
			}
			else {*/
				g.drawImage(animation.getImage(), (int)this.x, (int)y, null);
			//}
			 
		}
		
		public double getX(){
			return x;
		}

}
