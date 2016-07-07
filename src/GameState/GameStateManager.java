package GameState;

import java.util.ArrayList;

public class GameStateManager {

	private static ArrayList<GameState> gameStates;
	private static int currentState, previousState;

	public static final int NAME_ANIMATION_STATE = 0;
	public static final int MENU_STATE = 1;
	public static final int HELP_STATE = 2;
	public static final int CHARACTER_STATE = 3;
	public static final int TRANSITION_STATE = 4;
	public static final int LEVEL_1_STATE = 5;
	public static final int LEVEL_2_STATE = 6;
	public static final int GAME_OVER_STATE = 7;
	public static final int RESTART_STATE = 8;

	public GameStateManager() {

		gameStates = new ArrayList<GameState>();

		currentState = 0;
		gameStates.add(new NameAnimationState(this));
		gameStates.add(new MenuState(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new CharacterState(this));
		gameStates.add(new TransitionState(this));
		gameStates.add(new Level1State(this));
		gameStates.add(new Level2State(this));
		gameStates.add(new GameOverState(this));
		gameStates.add(new RestartState(this));
	}

	public void setState(int state) {
		previousState = currentState;
		currentState = state;
		gameStates.get(currentState).init();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}

	public void keyPressed(int k) {	gameStates.get(currentState).keyPressed(k);	}

	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
	public static GameState getState(){
		return gameStates.get(currentState);
	}
	
	public static int getPreviousState(){
		return previousState;
	}
}
