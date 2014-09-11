package net.teamfps.classic.gfx.level.entities;

import java.util.List;

import net.teamfps.classic.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class Mob extends Entity {

	@Override
	public void update() {

	}

	@Override
	public void render(Screen screen) {

	}

	public boolean FollowAI(Type t, double radius, double speed) {
		boolean following = false;
		int xa = 0;
		int ya = 0;
		if (level != null) {
			switch (t) {
			case ENTITY:
				List<Entity> entities = level.getClosetEntities(this, radius);
				if (entities.size() > 0) {
					Entity e = entities.get(0);
					if (getX() <= e.getX()) {
						xa += speed;
						following = true;
					}
					if (getX() >= e.getX()) {
						xa -= speed;
						following = true;
					}
					if (getY() <= e.getY()) {
						ya += speed;
						following = true;
					}
					if (getY() >= e.getY()) {
						ya -= speed;
						following = true;
					}
				}
				break;
			case PLAYER:
				List<Player> players = level.getClosetPlayers(this, radius);
				if (players.size() > 0) {
					Player p = players.get(0);
					if (getX() <= p.getX()) {
						xa += speed;
						following = true;
					}
					if (getX() >= p.getX()) {
						xa -= speed;
						following = true;
					}
					if (getY() <= p.getY()) {
						ya += speed;
						following = true;
					}
					if (getY() >= p.getY()) {
						ya -= speed;
						following = true;
					}
				}
				break;
			default:
				break;
			}
		}
		move(xa, ya);
		return following;
	}

	private double shootTimer = 0;

	public boolean ShootAI(Type t, double radius, double bulletRange,double bulletSpeed,double bulletDamage, double fireRate) {
		boolean shooting = false;
		if (level != null) {
			switch (t) {
			case ENTITY:
				List<Entity> entities = level.getClosetEntities(this, radius);
				if (entities.size() > 0) {
					Entity e = entities.get(0);
					double dx = e.getX() - getX();
					double dy = e.getY() - getY();
					double dir = Math.atan2(dy, dx);
					shootTimer += 0.1D;
					if (shootTimer >= fireRate) {
						shootTimer = 0;
						shooting = true;
						Shoot(new Bullet(getX() + (getW() / 2), getY() + (getH() / 2), dir,bulletRange,bulletSpeed,bulletDamage));
					}
				}
				break;
			case PLAYER:
				List<Player> players = level.getClosetPlayers(this, radius);
				if (players.size() > 0) {
					Player p = players.get(0);
					double dx = p.getX() - getX();
					double dy = p.getY() - getY();
					double dir = Math.atan2(dy, dx);
					shootTimer += 0.1D;
					if (shootTimer >= fireRate) {
						shootTimer = 0;
						shooting = true;
						Shoot(new Bullet(getX() + (getW() / 2), getY() + (getH() / 2), dir,bulletRange,bulletSpeed,bulletDamage));
					}
				}
				break;
			default:
				break;
			}
		}
		return shooting;
	}

	public enum Type {
		ENTITY, PLAYER;
	}

}
