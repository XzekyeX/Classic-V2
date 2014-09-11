package net.teamfps.classic.gfx.level.entities;

import java.util.Random;

import net.teamfps.classic.Input;
import net.teamfps.classic.gfx.Screen;
import net.teamfps.classic.gfx.level.Level;
import net.teamfps.classic.gfx.level.blocks.Block;

/**
 * @author Zekye
 *
 */
public abstract class Entity {
	public int x, y, w, h;
	public double hp;
	public String name = "";
	private boolean removed = false;
	protected Level level;
	protected final Random rand = new Random();

	public abstract void update();

	public abstract void render(Screen screen);

	public void remove() {
		removed = true;
	}

	/**
	 * @return the removed
	 */
	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public boolean MouseOver(Screen screen) {
		boolean over = false;
		if(Input.MX + screen.getOffset().getX() >= x && Input.MX + screen.getOffset().getX() <= x + w && Input.MY + screen.getOffset().getY() >= y && Input.MY + screen.getOffset().getY() <= y + h) {
			over = true;
		}
		return over;
	}

	public boolean collision(int xa, int ya) {
		boolean solid = false;
		int bSize = 32;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + bSize) / bSize;
			int yt = ((y + ya + (bSize / 2)) + c / 2 * h + (bSize / 2)) / bSize;
			if (level != null) {
				Block b = level.getBlock(xt - 1, yt - 1);
				if (b != null) {
					if (b.solid()) {
						solid = true;
					}
				}
			}
		}
		return solid;
	}
	
	public boolean Collision(Entity e) {
		boolean solid = false;
		
		return solid;
	}
	
	public void Shoot(Projectile p) {
		if(level != null) {
			level.add(p);
		}
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

}
