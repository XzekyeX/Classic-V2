package net.teamfps.classic.gfx.level.entities;

import java.awt.event.KeyEvent;

import net.teamfps.classic.Input;
import net.teamfps.classic.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class Player extends Mob {
	private double max_hp;

	public Player(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.w = 30;
		this.h = 30;
		this.hp = 100;
		this.max_hp = hp;
	}

	public Player(String name) {
		this.name = name;
		this.w = 30;
		this.h = 30;
		this.hp = 100;
		this.max_hp = hp;
	}

	public Player setPos(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	private double speed = 2D;

	@Override
	public void update() {
		int xa = 0;
		int ya = 0;
		if (Input.isKeyDown(KeyEvent.VK_A)) {
			xa -= speed;
		}
		if (Input.isKeyDown(KeyEvent.VK_D)) {
			xa += speed;
		}
		if (Input.isKeyDown(KeyEvent.VK_S)) {
			ya += speed;
		}
		if (Input.isKeyDown(KeyEvent.VK_W)) {
			ya -= speed;
		}
		move(xa, ya);
	}

	@Override
	public void render(Screen screen) {
		screen.fillRect(x, y, w, h, 0xffff00, true);
		renderHP(screen);
	}

	private void renderHP(Screen screen) {
		double max = (hp / max_hp);
		int cw = 200;
		int hx = (screen.width / 2) - (cw / 2);
		int hy = 20;
		int hh = 12;
		screen.fillRect(hx - 3, hy - 4, (int) (cw + 3), hh + 5, 3, 0xff515151, 0xff000000, false);
		screen.fillRect(hx, hy, (int) (cw * max), hh, 0xff0000, false);
		String shp = String.format("%.0f", hp);
		screen.drawString("" + shp + "/" + (int) max_hp, 12, hx + (cw / 2) - 20, hy + hh - 2, 0xffffff, false);
	}

}
