package net.teamfps.classic.gfx.level;

import java.util.ArrayList;
import java.util.List;

import net.teamfps.classic.Input;
import net.teamfps.classic.gfx.Screen;
import net.teamfps.classic.gfx.Sprite;
import net.teamfps.classic.gfx.level.blocks.Block;
import net.teamfps.classic.gfx.level.blocks.Floor;
import net.teamfps.classic.gfx.level.blocks.Stone;
import net.teamfps.classic.gfx.level.entities.Entity;
import net.teamfps.classic.gfx.level.entities.Hostile;
import net.teamfps.classic.gfx.level.entities.Player;
import net.teamfps.classic.gfx.level.entities.Projectile;

/**
 * @author Zekye
 *
 */
public class Level {
	private Screen screen;
	protected List<Entity> entities = new ArrayList<Entity>();
	protected List<Player> players = new ArrayList<Player>();
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	private Block[][] blocks;
	private int width;
	private int height;

	/**
	 * @param screen
	 */
	public Level(Screen screen) {
		this.screen = screen;
		Load(Sprite.map);
		LoadMobs(Sprite.mobs);
	}

	protected void Load(Sprite s) {
		System.out.println("Loading: " + s.getPath());
		width = s.getWidth();
		height = s.getHeight();
		blocks = new Block[width][height];
		Generate(s.getPixels());
	}

	protected void LoadMobs(Sprite s) {
		System.out.println("Loading: " + s.getPath());
		GenerateMobs(s.getPixels());
	}

	protected void Generate(int[] pixels) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int src = pixels[x + y * width];
				if (src == 0xffff00ff) {
					blocks[x][y] = new Floor(x * 32, y * 32);
				}
				if (src == 0xff555555) {
					blocks[x][y] = new Stone(x * 32, y * 32);
				}
			}
		}
		System.out.println("Level has Generated!");
	}

	protected void GenerateMobs(int[] pixels) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int src = pixels[x + y * width];
				if (src == 0xffffff00) {
					add(screen.getPlayer().setPos(x * 32, y * 32));
				}
				if (src == 0xffff0000) {
					add(new Hostile(x * 32, y * 32));
				}
			}
		}
		System.out.println("Mobs has Generated!");
	}

	public void update() {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).isRemoved()) {
				projectiles.remove(i);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
			if (entities.get(i).MouseOver(screen) && Input.ML) {
				entities.get(i).remove();
			}
			if (entities.get(i).isRemoved()) {
				entities.remove(i);
			}
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
			// if (players.get(i).MouseOver(screen) && Input.ML) {
			// players.get(i).remove();
			// }
			if (players.get(i).isRemoved()) {
				players.remove(i);
			}
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (blocks[x][y] != null) {
					blocks[x][y].render(screen);
					if (blocks[x][y].getHardness() <= blocks[x][y].breakness) {
						blocks[x][y] = new Floor(x * 32, y * 32);
					}
				}
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Player) {
			players.add((Player) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}

	public List<Entity> getClosetEntities(Entity e, double radius) {
		List<Entity> result = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			Entity ee = entities.get(i);
			double dx = e.getX() - ee.getX();
			double dy = e.getY() - ee.getY();
			double dist = Math.sqrt((dx * dx) + (dy * dy));
			if (dist <= radius) {
				result.add(ee);
			}
		}
		return result;
	}

	public List<Player> getClosetPlayers(Entity e, double radius) {
		List<Player> result = new ArrayList<Player>();
		for (int i = 0; i < players.size(); i++) {
			Player ee = players.get(i);
			double dx = e.getX() - ee.getX();
			double dy = e.getY() - ee.getY();
			double dist = Math.sqrt((dx * dx) + (dy * dy));
			if (dist <= radius) {
				result.add(ee);
			}
		}
		return result;
	}

	/**
	 * @return the blocks
	 */
	public Block getBlock(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return null;
		return blocks[x][y];
	}

}
