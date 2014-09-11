package net.teamfps.classic.gfx.level.entities;

import java.awt.Color;

import net.teamfps.classic.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class Hostile extends Mob {
	private double radius = 7 * 32;
	private boolean following = false;
	private boolean shooting = false;
	private Color lightColor = new Color(255, 255, 255, 32);

	public Hostile(int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 30;
		this.h = 30;
		this.name = "Hostile";
	}

	@Override
	public void update() {
		double ra = radius - 62;
		following = FollowAI(Type.PLAYER, ra, 1.0D);
		shooting = ShootAI(Type.PLAYER, ra, ra + 32, 2.0D, 2.5D, 3.8D);
	}

	@Override
	public void render(Screen screen) {
		screen.fillRect(x, y, w, h, 0xff0000, true);
		if (following) {
			screen.fillOval(x - (int) (radius / 2) + (w / 2), y - (int) (radius / 2) + (h / 2), (int) radius, (int) radius, lightColor, true);
		} else {
			lightColor = new Color(255, 255, 255, 32);
		}
		if (shooting) {
			lightColor = new Color(255, 0, 0, 32);
		}
	}

}
