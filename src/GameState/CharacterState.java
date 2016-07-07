package GameState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

import TileMap.Background;

public class CharacterState extends GameState {

	private Background bg;
	private BufferedImage img, img1, img2;
	
	private int currentChoice = 0;
	private String[] options = { "Player 1", "Player 2" };
	private Color optionColor;
	
	public static String playerName;

	private Font font;

	public CharacterState(GameStateManager gsm) {
		super(gsm);
		try {
			bg = MenuState.bg2;
			bg.setVector(-0.12, 0);
			
			img1 = ImageIO.read(getClass().getResourceAsStream("/Animations/MikeV2.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/Animations/AakashV2.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		font = new Font("Arial", Font.PLAIN, 24);
	}

	public void init() {
	}

	public void update() {
		bg.update();

	}

	public void draw(Graphics2D g) {
		// draw Background
		bg.draw(g);

		// drawMenuOptions
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				optionColor = Color.red;
			} else {
				optionColor = Color.black;
			}

			int start = 0;
			// centers text
			int len = (int) g.getFontMetrics().getStringBounds(options[i], g).getWidth();
			int height = (int) g.getFontMetrics().getStringBounds(options[i], g).getHeight();
			if (i == 0) {
				start = GamePanel.WIDTH / 2 -  64 - len;
				img = img1;
			} else {
				start = GamePanel.WIDTH / 2 + 64;
				img = img2;
			}
			
			//draw shaded rectangle
			g.setColor(new Color(255, 255, 255, 150));
			// g.fillRect(xLocation, yLocation, width, height);
			g.fillRect(start - 16, 324 - 26, len + 32, height + 8);
			
			//draw rectangle border
			g.setColor(new Color(0, 0, 0, 150));
			g.setStroke(new BasicStroke(3));
			g.drawRect(start - 16, 324 - 26, len + 32, height + 8);
			
			g.setColor(optionColor);
			g.drawString(options[i], start, 324);
			g.drawImage(img, start + (int)((len - (img.getWidth() * 2.5))/2), 100, (int)(img.getWidth() * 2.5), 
					(int)(img1.getHeight()* 2.5), null);
			

		}

	}
	
	private void select() {
		playerName = options[currentChoice];
		gsm.setState(GameStateManager.TRANSITION_STATE);
		GameState.stopMusic();
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_A) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_D) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k) {}
}
