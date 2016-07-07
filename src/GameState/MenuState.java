package GameState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;

import TileMap.Background;

public class MenuState extends GameState {

	public static Background bg, bg2;

	private int currentChoice = 0;
	private String[] options = { "Start", "Help", "Quit" };

	private Color titleColor;
	private Font titleFont;
	private Font font;
	private String title;

	public MenuState(GameStateManager gsm) {
		super(gsm);

		try {
			bg = NameAnimationState.bg;
			//bg.setVector(-0.48, 0);
			//bg.setRedraw(true);
			
			bg2 = NameAnimationState.bg2;
			//bg.setVector(-0.12, 0);
			//bg.setRedraw(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		titleColor = new Color(255, 40, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 48);
		font = new Font("Arial", Font.PLAIN, 24);
		title = "Park Runner Game";
	}

	public void init() {
	}

	public void update() {
		bg.update();
		bg2.update();
	}

	public void draw(Graphics2D g) {
		// draw Background
		
		bg2.draw(g);
		bg.draw(g);
		
		
		g.setFont(titleFont);

		// centers text
		int stringLen = (int) g.getFontMetrics().getStringBounds(title, g).getWidth();
		int startPos = GamePanel.WIDTH / 2 - stringLen / 2;
		
		//g.setColor(new Color((int)(titleColor.getRed() * 0.4) % 255, (int)(titleColor.getGreen() * 0.4) % 255, (int)(titleColor.getBlue() * 0.4) % 255));
		g.setColor(Color.black);
		g.drawString(title, startPos + 2, 112);
		
		g.setColor(titleColor);
		g.drawString(title, startPos, 110);
		

		g.setColor(new Color(255, 255, 255, 150));
		// g.fillRect(xLocation, yLocation, width, height);
		g.fillRect(GamePanel.WIDTH / 2 - 44, 168, 90, 130);
		g.setColor(new Color(0, 0, 0, 150));
		g.setStroke(new BasicStroke(3));
		g.drawRect(GamePanel.WIDTH / 2 - 44, 168, 90, 130);

		// drawMenuOptions
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.black);
			}

			// centers text
			int len = (int) g.getFontMetrics().getStringBounds(options[i], g).getWidth();
			int start = GamePanel.WIDTH / 2 - len / 2;
			g.drawString(options[i], start, 200 + (i * 40));

		}
	}

	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.CHARACTER_STATE);
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.HELP_STATE);
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_W) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_S) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	public void keyReleased(int k) {
	}

}
