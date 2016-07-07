package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import GameState.GameState;

public class Block extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	public static final int blockSize = 64;
	
	public Block(int x, int y){
		setBounds(x, y, blockSize, blockSize);
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fillRect(x - (int)GameState.xOffset, y, width, height);
	}
}
