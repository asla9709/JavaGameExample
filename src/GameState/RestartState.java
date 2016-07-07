package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;
import TileMap.Background;

public class RestartState extends GameState{

	public static Background bg;

	private int currentChoice = 0;
	private String[] options = { "Restart", "Quit" };

	private Font font;

	public RestartState(GameStateManager gsm) {
		super(gsm);

		try {
			bg = new Background("/Backgrounds/CloudbackgroundFinalV2.png");
			bg.setVector(-0.12, 0);
			bg.setRedraw(true);

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
				g.setColor(Color.red);
			} else {
				g.setColor(Color.black);
			}
			
			int start = 0;
			// centers text
			int len = (int) g.getFontMetrics().getStringBounds(options[i], g).getWidth();
			if(i == 0) {
				start = GamePanel.WIDTH / 2 - len;
			}
			else {
				start = GamePanel.WIDTH / 2 + len;
			}
			g.drawString(options[i], start, 200);

		}
	}

	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL_1_STATE);
			GameState.xOffset = 0;
			GameState.yOffset = 0;
			
			Level1State.back2.x = 0;
			GameOverState.dO = 0.05;
		}
		if (currentChoice == 1) {
			System.exit(0);
		}
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
