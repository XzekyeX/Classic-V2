package net.teamfps.classic.gfx.level.blocks;

import net.teamfps.classic.gfx.Screen;
import net.teamfps.classic.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public class Floor extends Block {

	/**
	 * @param x
	 * @param y
	 */
	public Floor(int x, int y) {
		super(x, y);
	}

	@Override
	public double getHardness() {
		return 999;
	}

	@Override
	public boolean solid() {
		return false;
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(Sprite.floor, x, y, w, h, true);
		// screen.fillRect(x, y, w, h, 0xff00ff, true);
	}

}
