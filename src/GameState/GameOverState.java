package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

import TileMap.Background;

public class GameOverState extends GameState {

	private Background bg;
	private double backOpacity = 0;
	private double titleOpacity = 0;
	private String title;
	private Color titleColor;
	private Font titleFont;
	public static double dO = 0.05;
	
	public GameOverState(GameStateManager gsm){
		super(gsm);
		
		try {
			bg = MenuState.bg2;
			bg.setRedraw(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		title = "Game Over";
		titleFont = new Font("Century Gothic", Font.PLAIN, 48);
	}
	
	public void init() {}
	public void update() {}
	public void draw(Graphics2D g) {
		//draw background
		bg.draw(g);
		
		//draw "Game Over"
		titleColor = new Color(0, 0, 0, (int) titleOpacity);
		g.setColor(titleColor);
		g.setFont(titleFont);

		int stringLen = (int) g.getFontMetrics().getStringBounds(title, g).getWidth();
		int startPos = GamePanel.WIDTH / 2 - stringLen / 2;
		g.drawString(title, startPos, GamePanel.HEIGHT/2 + 24);
		
		titleOpacity++;
		if (titleOpacity >= 255) titleOpacity = 255;
		
		
		//fade out
		g.setColor(new Color(0, 0, 0, (int)backOpacity));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		backOpacity += 0.01 + Math.pow(dO, 2);
		dO+=0.002;
		
		if(backOpacity >= 255) {
			gsm.setState(GameStateManager.RESTART_STATE);
			backOpacity = 0;
		}
		
	}
	public void keyPressed(int k) {}
	public void keyReleased(int k) {}
}
