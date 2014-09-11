package net.teamfps.classic.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Zekye
 *
 */
public class Sprite {
	private int width, height;
	private String path;
	private int[] pixels;
	private BufferedImage image;

	public static Sprite map = new Sprite("/map.png");
	public static Sprite mobs = new Sprite("/mobs.png");
	public static Sprite floor = new Sprite("/floor.png");

	/**
	 * 
	 * @param path
	 */
	public Sprite(String path) {
		this.path = path;
		load();
	}

	private void load() {
		try {
			image = ImageIO.read(Sprite.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the pixels
	 */
	public int[] getPixels() {
		return pixels;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}
}
