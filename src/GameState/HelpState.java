package GameState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

import TileMap.Background;

public class HelpState extends GameState {

    private Background bg, bg2;

    private Color titleColor;
    private Font titleFont;
    private Font font;
    private String title;
    private String[] helpText = {"Use 'A' and 'D' to move left and right.",
            "Use 'W' to jump.", "The goal is to reach the end of the level.",
            "GOOD LUCK!"};

    public HelpState(GameStateManager gsm) {
        super(gsm);

        try {
            bg = MenuState.bg;
            bg2 = MenuState.bg2;
            bg.setRedraw(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        titleColor = Color.BLACK;
        titleFont = new Font("Century Gothic", Font.PLAIN, 48);
        font = new Font("Arial", Font.PLAIN, 24);
        title = "Help";

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

        g.setColor(new Color(255, 255, 255, 150));
        g.fillRect(GamePanel.WIDTH / 2 - 240, 100, GamePanel.WIDTH - 150, GamePanel.HEIGHT - 200);
        g.setColor(new Color(0, 0, 0, 150));
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.WIDTH / 2 - 240, 100, GamePanel.WIDTH - 150, GamePanel.HEIGHT - 200);

        // draw Title
        g.setColor(titleColor);
        g.setFont(titleFont);

        // centers text
        int stringLen = (int) g.getFontMetrics().getStringBounds(title, g)
                .getWidth();
        int startPos = GamePanel.WIDTH / 2 - stringLen / 2;
        g.drawString(title, startPos, 70);

        // drawMenuOptions
        g.setFont(font);
        for (int i = 0; i < helpText.length; i++) {
            g.setColor(Color.BLACK);
            // centers text
            int len = (int) g.getFontMetrics().getStringBounds(helpText[i], g)
                    .getWidth();
            int start = GamePanel.WIDTH / 2 - len / 2;
            g.drawString(helpText[i], start, 150 + (i * 50));

        }

        g.setColor(Color.red);
        g.drawString("Continue", 520, 430);
    }

    public void keyPressed(int k) {
        gsm.setState(GameStateManager.MENU_STATE);
    }

    public void keyReleased(int k) {
    }
}
