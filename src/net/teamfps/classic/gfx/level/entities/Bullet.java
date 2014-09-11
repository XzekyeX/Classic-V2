package net.teamfps.classic.gfx.level.entities;

import net.teamfps.classic.gfx.Screen;
import net.teamfps.classic.gfx.level.blocks.Block;

/**
 * @author Zekye
 * 
 */
public class Bullet extends Projectile {
	private int color = 0xff00ff;
	private int remove = 0;

	public Bullet(int x, int y, double dir) {
		super(x, y, dir);
		this.w = 8;
		this.h = 8;
		range = 300;
		damage = 10;
		speed = 4;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	/**
	 * @param x
	 * @param y
	 * @param dir
	 * @param bulletRange
	 * @param bulletSpeed
	 * @param bulletDamage
	 */
	public Bullet(int x, int y, double dir, double range, double speed, double damage) {
		super(x, y, dir);
		this.w = 8;
		this.h = 8;
		this.range = range;
		this.speed = speed;
		this.damage = damage;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	@Override
	public void update() {
		move();
		Custom c = CustomCollision((int) (x + nx), (int) (y + ny));
		if (c != null) {
			remove++;
			if (remove >= 10) {
				remove();
				if (c.getBlock() != null) {
					c.getBlock().breakness += 0.1D;
					System.out.println("breakness: " + c.getBlock().breakness);
				}
			}
		}
	}

	public boolean Collisions(Entity e) {
		boolean solid = false;
		if (x + w >= e.getX() && x <= e.getX() + e.getW() && y + h >= e.getY() && y <= e.getY() + e.getH()) {
			solid = true;
		}
		return solid;
	}

	public Custom CustomCollision(int xa, int ya) {
		boolean solid = false;
		int bSize = 32;
		for (int c = 0; c < 4; c++) {
			int xt = ((getX() + xa) + c % 2 * w + bSize) / bSize;
			int yt = ((getY() + ya + (bSize / 2)) + c / 2 * h + (bSize / 2)) / bSize;
			if (level != null) {
				Block b = level.getBlock(xt - 1, yt - 1);
				if (b != null) {
					if (b.solid()) {
						solid = true;
						return new Custom(b, solid);
					}
				}
			}
		}
		return null;
	}

	class Custom {
		public Block block;
		public boolean solid = false;

		public Custom(Block block, boolean solid) {
			this.block = block;
			this.solid = solid;
		}

		/**
		 * @return the block
		 */
		public Block getBlock() {
			return block;
		}

		/**
		 * @return the solid
		 */
		public boolean isSolid() {
			return solid;
		}
	}

	@Override
	public void render(Screen screen) {
		screen.fillRect((int) x, (int) y, w, h, color, true);
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() >= range) {
			remove();
		}
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
}
