package net.teamfps.classic.gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import net.teamfps.classic.Input;
import net.teamfps.classic.gfx.level.Level;
import net.teamfps.classic.gfx.level.entities.Player;

/**
 * @author Zekye
 *
 */
public class Screen extends Bitmap {
	private Level level;
	private Player player;
	private boolean pause = false;
	private boolean flash = false;
	private int delay = 20;

	/**
	 * @param width
	 * @param height
	 */
	public Screen(int width, int height) {
		super(width, height);
		player = new Player("Player");
		level = new Level(this);
	}

	private int timer = 0;

	public void update() {
		if (delay > 0) delay--;
		if (Input.isKeyDown(KeyEvent.VK_ESCAPE) && delay == 0) {
			delay = 20;
			pause = !pause;
		}
		if (!pause) {
			level.update();
		}
		timer++;
		if (timer >= 32) {
			timer = 0;
			flash = !flash;
		}
	}

	public void render(Graphics g) {
		initGFX((Graphics2D) g);
//		double scale = 1D;
//		Zoom(scale, scale);
		int cw = player.getX() - (width / 2);
		int ch = player.getY() - (height / 2);
		setOffset(cw, ch);
		level.render();
		if (pause && flash) {
			drawString("Paused!", 32, width / 2 - 32, height / 2 - 64, 0xffffff, false);
		}
	}

	/**
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

}
