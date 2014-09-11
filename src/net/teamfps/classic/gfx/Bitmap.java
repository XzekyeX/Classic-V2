package net.teamfps.classic.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import net.teamfps.classic.gfx.util.Vector2i;

/**
 * @author Zekye
 *
 */
public class Bitmap {
	public int width;
	public int height;
	public Graphics2D g;
	private int defaultColor = 0xffffff;
	private int xOffs = 0;
	private int yOffs = 0;

	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void initGFX(Graphics2D g) {
		this.g = g;
	}

	public void drawString(String str, int fsize, int x, int y, int color, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		g.setFont(new Font("Arial", 1, fsize));
		g.setColor(new Color(color));
		g.drawString(str, x, y);
		g.setColor(new Color(defaultColor));
	}

	public void renderSprite(Sprite s, int x, int y, int w, int h, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		if (s.getImage() != null) {
			g.drawImage(s.getImage(), x, y, w, h, null);
		} else {
			fillRect(x, y, w, h, 0xff00ff, offset);
		}
	}

	public void fillRect(int x, int y, int w, int h, int color, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		g.setColor(new Color(color));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(defaultColor));
	}

	public void fillRect(int x, int y, int w, int h, Color color, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(new Color(defaultColor));
	}

	public void fillOval(int x, int y, int w, int h, Color color, boolean offset) {
		if (offset) {
			x -= xOffs;
			y -= yOffs;
		}
		g.setColor(color);
		g.fillOval(x, y, w, h);
		g.setColor(new Color(defaultColor));
	}

	public void Rect(int x, int y, int w, int h, int b, int color, boolean offset) {
		fillRect(x, y, w, b, color, offset);
		fillRect(x, y, b, h, color, offset);
		fillRect(x + w, y, b, h, color, offset);
		fillRect(x, y + h, w + b, b, color, offset);
	}

	public void fillRect(int x, int y, int w, int h, int b, int bcolor, int rcolor, boolean offset) {
		fillRect(x, y, w, h, bcolor, offset);
		Rect(x, y, w, h, b, rcolor, offset);
	}

	public void Zoom(double sx, double sy) {
		AffineTransform at = new AffineTransform();
//		at.translate(xOffs - ((width / 2) / sx), yOffs - ((width / 2) / sy));
		at.scale(sx, sy);
		g.setTransform(at);
	}

	public void setOffset(int xOffs, int yOffs) {
		this.xOffs = xOffs;
		this.yOffs = yOffs;
	}

	public Vector2i getOffset() {
		return new Vector2i(xOffs, yOffs);
	}
}
