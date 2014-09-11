package net.teamfps.classic.gfx.level.blocks;

import net.teamfps.classic.gfx.Screen;
import net.teamfps.classic.gfx.level.entities.Entity;

/**
 * @author Zekye
 *
 */
public abstract class Block extends Entity {
	public double breakness = 0.0D;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 32;
		this.h = 32;
	}

	public abstract double getHardness();

	public abstract boolean solid();

	@Override
	public abstract void update();

	@Override
	public abstract void render(Screen screen);

}
