package net.teamfps.classic.gfx.level.entities;

import net.teamfps.classic.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class Projectile extends Entity {
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected double x,y;
	protected double nx,ny;
	protected double speed,range,damage; 
	public Projectile(int x,int y,double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
	
	protected void move() {
		
	}

	@Override
	public void render(Screen screen) {
		
	}
}
