package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GamePanel;
import Entities.Player;
import Objects.Block;
import TileMap.Background;
//import TileMap.FileInput;

public class Level1State extends GameState {

	public static Background back, back2, back3;
	private Player player;
	
	//private ArrayList<String> blockMap;

	private ArrayList<Block> b;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
		try {
			/* back = clouds
			 * back2 = school
			 * back3 = tree
			 */
			back = new Background("/Backgrounds/CloudBackgroundFinal.png");
			back.setVector(-0.24, 0);
			back2 = new Background("/Backgrounds/SalemSchoolLevel1V3.png");
			back3 = new Background("/Backgrounds/treeBackground.png");
			
			back.setRedraw(true);
			back2.setRedraw(false);
			back3.setRedraw(false);

			init();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		back.update();
		back2.update();
		back3.update();

		
		for(int i = 0; i < b.size(); i++){
			b.get(i).update();
		}
		player.update(b);
		
		//ends game if fall too far
		if(player.getY() > 1500.) {
			gsm.setState(GameStateManager.GAME_OVER_STATE);
			player.setLocation(128, 324);
		}	
		if(GameState.xOffset > 3690.0) {
			gsm.setState(GameStateManager.TRANSITION_STATE);
			GameState.stopMusic();
			player = null;
			GameState.xOffset = 0;
			GameState.yOffset = 0;
		}		
		
	}

	public void init() {
		if(CharacterState.playerName == "Player 1"){
			player = new Player("/Animations/Michael.png");
		}
		else {
			player = new Player("/Animations/Aakash.png");
		}
		
		player.setBounds(0,8000);
		player.setLocation(128, 192);
		player.setBackgroundMove(1.8, 0);
		
		//blockMap = FileInput.tileMap("Resources/Tilesets/blockMap4.txt");
		b = new ArrayList<Block>();
		
		for(int t = 0; t < 120; t++){
			b.add(new Block(t * 64, 384));
		}

	}

	public void draw(Graphics2D g) {
		// clear screen
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		/*
		back.draw(g);
		back2.draw(g);
		
		*/
		for(int i = 0; i < b.size(); i++){
			b.get(i).draw(g);
		}
		
		back.draw(g);
		back3.draw(g);
		back2.draw(g);
		
		player.draw(g);
	}

	public void keyPressed(int k) {
		player.keyPressed(k);
		if(k == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}

}
