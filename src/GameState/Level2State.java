package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GamePanel;
import Entities.Skateboarder;
import Entities.Student;
import Entities.Player;
import Objects.Block;
import TileMap.Background;
import TileMap.FileInput;

public class Level2State extends GameState {

	public static Background back;
	public static Background back2;
	private Player player;
	private ArrayList<Block> b;
	public Skateboarder skateboarder1, skateboarder2;
	public Student student1, student2, student3, student4;

	public Level2State(GameStateManager gsm) {
		super(gsm);
		try {
			back = new Background("/Backgrounds/ScrollingCloudsv1.png");
			back.setVector(-0.12, 0);
			back2 = new Background("/Backgrounds/Level2PathV2.png");

			back.setRedraw(true);
			back2.setRedraw(false);
			
			init();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		if (CharacterState.playerName == "Player 1") {
			player = new Player("/Animations/Michael.png");
		} else {
			player = new Player("/Animations/Aakash.png");
		}
		player.setBounds(0, 5100);
		player.setLocation(128, 320);
		player.setBackgroundMove(0.8, 0);

		FileInput.tileMap("Resources/Tilesets/blockMap3.txt");
		b = new ArrayList<Block>();

		for (int t = 0; t < 120; t++) {
			b.add(new Block(t * 64, 384));
		}
		

		skateboarder1 = new Skateboarder("/Animations/skateboarderRed.png");
		skateboarder2 = new Skateboarder("/Animations/skateboarderGreen.png");
		student1 = new Student("/Animations/studentSpritesBlue.png");
		student2 = new Student("/Animations/studentSpritesBlue.png");
		student3 = new Student("/Animations/studentSpritesPurple.png");
		student4 = new Student("/Animations/studentSpritesFemale.png");
		
		skateboarder1.setBounds(-20, 1000);
		skateboarder1.setLocation(400, 320);
		skateboarder1.setMoveSpeed(1.0);
		
		skateboarder2.setBounds(-20, 1000);
		skateboarder2.setLocation(700, 320);
		skateboarder2.setMoveSpeed(1.4);
		
		student1.setBounds(-20, 700);
		student1.setLocation(200, 320);
		
		student2.setBounds(-20, 1200);
		student2.setLocation(900, 320);
		
		student3.setBounds(-20, 800);
		student3.setLocation(45, 320);
		
		student4.setBounds(-20, 900);
		student4.setLocation(300, 320);
		
		
		//GameState.music("F:\\Level1.wav");

	}

	public void update() {
		back.update();
		back2.update();

		for (int i = 0; i < b.size(); i++) {
			b.get(i).update();
		}
		player.update(b);
		skateboarder1.update(b);
		skateboarder2.update(b);
		student1.update();
		student2.update();
		student3.update();
		student4.update();

		// ends game if fall too far
		if (GameState.yOffset > 1500) {
			gsm.setState(GameStateManager.GAME_OVER_STATE);
		}
		
		if(GameState.xOffset >= 5064){
			gsm.setState(GameStateManager.TRANSITION_STATE);
			GameState.stopMusic();
			player = null;
			GameState.xOffset = 0;
			GameState.yOffset = 0;
		}

	}

	public void draw(Graphics2D g) {
		// clear screen
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		for (int i = 0; i < b.size(); i++) {
			b.get(i).draw(g);
		}

		back.draw(g);
		back2.draw(g);
		skateboarder1.draw(g);
		skateboarder2.draw(g);
		student1.draw(g);
		student2.draw(g);
		student3.draw(g);
		student4.draw(g);
		player.draw(g);

	}

	public void keyPressed(int k) {
		player.keyPressed(k);
		if (k == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (k == KeyEvent.VK_ENTER) {
			System.out.println(student1.getX());
		}
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}

}
