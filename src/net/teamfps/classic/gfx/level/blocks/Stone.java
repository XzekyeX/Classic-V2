package net.teamfps.classic.gfx.level.blocks;

import java.awt.Color;

import net.teamfps.classic.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class Stone extends Block {

	/**
	 * @param x
	 * @param y
	 */
	public Stone(int x, int y) {
		super(x, y);
	}

	@Override
	public double getHardness() {
		return 1.5;
	}

	@Override
	public boolean solid() {
		return true;
	}

	@Override
	public void update() {
	}

	private int alpha = 255;
	private Color color = new Color(85, 85, 85, alpha);

	@Override
	public void render(Screen screen) {
		double max = breakness / getHardness();
		alpha = (int) (255 - (255 * max));
		color = new Color(85, 85, 85, alpha);
		screen.fillRect(x, y, w, h, color, true);
	}

}
