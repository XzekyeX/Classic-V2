package net.teamfps.classic;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import net.teamfps.classic.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static JFrame f = new JFrame("Classic V2");
	private static int width = 640;
	private static int height = 480;
	private Thread thread;
	private boolean running;
	private Screen screen;
	private Input input;

	private void init() {
		screen = new Screen(width, height);
		input = new Input();
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
	}

	public void start() {
		if (running) return;
		running = true;
		thread = new Thread(this, "Main Thread");
		thread.start();
	}

	public void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int ups = 0;
		int fps = 0;
		init();
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				delta--;
				update();
				ups++;
			}
			render();
			fps++;
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				Console.Out("fps[" + fps + "], ups[" + ups + "]");
				fps = 0;
				ups = 0;
			}
		}
	}

	private void update() {
		screen.update();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		screen.render(g);
		g.dispose();
		bs.show();
	}

	private static boolean resizable = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		f.add(m, "Center");
		f.pack();
		f.setSize(new Dimension(width, height));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(resizable);
		f.setVisible(true);
		m.start();
	}

	public Dimension getSize() {
		return new Dimension(width, height);
	}

}
