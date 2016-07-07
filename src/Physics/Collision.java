package Physics;

import java.awt.Point;

import Objects.Block;

public class Collision {

	public static boolean playerBlock(Point p, Block b){
		return b.contains(p);
	}
}
