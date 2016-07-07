package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import TileMap.Background;

import main.GamePanel;

public class NameAnimationState extends GameState {

	private String str1 = "Michael Dame";
	private String str2 = "Aakash Patel";


	private Color str1Color = new Color(0, 255, 80);
	private Color str2Color = new Color(0, 200, 255);
	private Font nameFont = new Font("Century Gothic", Font.BOLD, 28);;
	private double opacity= 255;
	
	public static Background bg, bg2;
	
	
	private double x1 = -144, y1 = GamePanel.HEIGHT/2 + 40;
	private double x2 = GamePanel.WIDTH, y2 = GamePanel.HEIGHT/2 - 40;
	private double dx = 0.9;

	public NameAnimationState(GameStateManager gsm) {
		super(gsm);

		
		try{
			bg = new Background("/Backgrounds/pathFront.png");
			bg.setVector(-0.20, 0);
			bg.setRedraw(true);
			
			bg2 = new Background("/Backgrounds/CloudBackgroundFinalV2.png");
			bg2.setVector(-0.36, 0);
			bg2.setRedraw(true);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init() {
		GameState.startMusic("/Resources/Music/Menu.wav");
	}

	public void update() {
		x1 += dx;
		x2 -= dx;
		
		bg.update();
		bg2.update();
	}

	public void draw(Graphics2D g) {
		//draw background
		bg2.draw(g);
		bg.draw(g);

		g.setColor(new Color(0, 0, 0, (int) opacity));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT); 
		opacity-=.35;
		if(opacity < 0) opacity = 0;
		
		//draw names
		g.setFont(nameFont);
		g.setColor(str1Color);
		g.drawString(str1, (int)x1, (int)y1);
		
		g.setColor(str2Color);
		g.drawString(str2, (int)x2, (int)y2);
		
		if(x1 > GamePanel.WIDTH + 80) {
			gsm.setState(GameStateManager.MENU_STATE);
		}

	}

	public void keyPressed(int k) {
	}

	public void keyReleased(int k) {
	}

}
