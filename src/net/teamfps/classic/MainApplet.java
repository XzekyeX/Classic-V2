package net.teamfps.classic;

import javax.swing.JApplet;

/**
 * @author Zekye
 *
 */
public class MainApplet extends JApplet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		Main m = new Main();
		add(m, "Center");
		setSize(m.getSize());
		m.start();
	}
}
