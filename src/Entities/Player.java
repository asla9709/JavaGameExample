package Entities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import GameState.CharacterState;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Level1State;
import GameState.Level2State;
import Objects.Block;
import Physics.Collision;
import main.GamePanel;

public class Player {
	
	// movement booleans
	protected static boolean right = false;
	protected static boolean left = false;
	protected static boolean jumping = false;
	protected static boolean falling = false;
	private boolean topCollision = false;

	// bounds
	private double x;
	private double y;
	private int height = 64, width = 64;
	public double xMin, xMax;

	// move main Background
	private double dx, dy;

	// move speed
	private double moveSpeed = 2;

	// jump speed
	private double jumpSpeed = 2.000005;
	private double currentJumpSpeed = jumpSpeed;

	// fall speed
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;

	// collisions
	private boolean leftCol, rightCol, topCol, bottomCol;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 2, 5 };

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	
	private boolean walk = false;

	private Animation animation;
	private boolean facingLeft = false;

	public Player(String s) {
		x = GamePanel.WIDTH / 2;
		y = 200 - height;

		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Animations/AakashSprites.png"));
			if(CharacterState.playerName == "Player 1"){
				spritesheet = ImageIO.read(getClass().getResourceAsStream("/Animations/MikeSprites.png"));
			}
			
			
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 2; i++) {

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
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
		right = false;
		left = false;
		jumping = false;
		falling = false;
	}
	
	public void setBounds(int xMin, int xMax) {
		this.xMin = xMin;
		this.xMax = xMax;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setBackgroundMove(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void update(ArrayList<Block> b) {

		int iX = (int) x;
		int iY = (int) y;

		for (int i = 0; i < b.size(); i++) {

			rightCol = Collision.playerBlock(
					new Point(iX + width + (int) GameState.xOffset, iY
							+ (int) GameState.yOffset + 2), b.get(i))
					|| Collision.playerBlock(new Point(iX + width
							+ (int) GameState.xOffset, iY + height
							+ (int) GameState.yOffset - 1), b.get(i));
			leftCol = Collision.playerBlock(new Point(iX
					+ (int) GameState.xOffset - 1, iY + (int) GameState.yOffset
					+ 2), b.get(i))
					|| Collision.playerBlock(new Point(iX
							+ (int) GameState.xOffset - 1, iY + height
							+ (int) GameState.yOffset - 1), b.get(i));
			topCol = Collision.playerBlock(
					new Point(iX + (int) GameState.xOffset + 1, iY
							+ (int) GameState.yOffset), b.get(i))
					|| Collision.playerBlock(new Point(iX + width
							+ (int) GameState.xOffset - 1, iY
							+ (int) GameState.yOffset), b.get(i));
			bottomCol = Collision.playerBlock(new Point(iX
					+ (int) GameState.xOffset + 2, iY + height + 1), b.get(i))
					|| Collision.playerBlock(new Point(iX + width
							+ (int) GameState.xOffset - 1, iY + height
							+ (int) GameState.yOffset + 1), b.get(i));

			// left
			if (leftCol) {
				// left = false;
				// jumping = false;
			}

			// top
			if (topCol) {
				jumping = false;
				falling = true;
			}

			// right
			if (rightCol) {
				// right = false;
				// jumping = false;
				// falling = false;
			}

			// bottom
			if (bottomCol) {
				// if(!rightCol && !leftCol)
				y = b.get(i).getY() - height - GameState.yOffset + 1;
				falling = false;
				topCollision = true;
			} else {
				if (!topCollision && !jumping) {
					falling = true;
				}
			}
		}

		topCollision = false;

		if (right) {
			if (GameState.xOffset < xMax) {
				GameState.xOffset += moveSpeed;
				if (GameStateManager.getState() instanceof Level1State) {
					Level1State.back2.x -= dx;
					Level1State.back3.x -= dx * 0.8;
				}
				if (GameStateManager.getState() instanceof Level2State) {
					Level2State.back2.x -= dx;
				}
			}
			else{
				right = false;
			}
			
		}

		if (left) {
			if (GameState.xOffset > xMin) {
				GameState.xOffset -= moveSpeed;
				if (GameStateManager.getState() instanceof Level1State
						&& Level1State.back2.x <= -32) {
					Level1State.back2.x += dx;
					Level1State.back3.x += dx * 0.8;
				}
				if (GameStateManager.getState() instanceof Level2State
						&& Level2State.back2.x <= -32) {
					Level2State.back2.x += dx;
				}
			}
			else{
				left = false;
			}
		}

		if (jumping) {
			y -= currentJumpSpeed;
			currentJumpSpeed -= 0.05;
			if (currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
			Level1State.back2.y += dy;
			
		}

		if (falling) {
			y += currentFallSpeed;
			if (currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += 0.1;
			}
			Level1State.back2.y -= dy;
			if (Level1State.back2.y > 640)
				Level1State.back2.y = 640;

		}

		if (!falling) {
			currentFallSpeed = 0.1;
		}

		
		// walking animations
		if (right || left) {
			if(jumping || falling) {
				animation.setFrame(0);
				animation.setFrames(sprites.get(IDLE));
				walk = false;
			}
			else {
				if(walk != true) {
					animation.setFrames(sprites.get(WALKING));
					animation.setDelay(200);
					walk = true;
					if(right){
						facingLeft = false;
					}
					if(left){
						facingLeft = true;
					}
				}
				if(jumping || falling) {
					animation.setFrames(sprites.get(IDLE));
				}
			}
		} 
		

		else {
			animation.setFrames(sprites.get(IDLE));
			animation.setDelay(40);
			width = 30;
			animation.setFrame(0);
			walk = false;
		}

		animation.update();
	}

	public void draw(Graphics2D g) {
		// g.drawImage(image, (int) x, (int) y, null);
		if(facingLeft) {
			g.drawImage(animation.getImage(), (int) x + animation.getImage().getWidth()/2, (int) y, -animation.getImage().getWidth(), animation.getImage().getHeight(), null);
		}
		else {
			g.drawImage(animation.getImage(), (int)x, (int)y, null);
		}
		 
	}

	public void printData() {
		System.out.println("x: " + GameState.xOffset);
		System.out.println("y: " + y);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_D){
			right = true;
			left = false;
			
			facingLeft = false;
		}
		if (k == KeyEvent.VK_A){
			left = true;
			right = false;
			
			facingLeft = true;
		}
		if (k == KeyEvent.VK_W && !jumping && !falling)
			jumping = true;

		if (k == KeyEvent.VK_ENTER) {
			printData();
		}
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_D){
			right = false;
		}
		if (k == KeyEvent.VK_A) {
			left = false;
		}
		
	}

}
