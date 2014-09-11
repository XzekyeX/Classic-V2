package net.teamfps.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zekye
 *
 */
public class Console {
	private static List<String> list = new ArrayList<String>();
	private static int MAX = 100;
	public static void Out(String str) {
		if(list.size() >= MAX) {
			list.remove(0);
		}
		list.add(str);
		System.out.println(str);
	}
	
	/**
	 * @return the list
	 */
	public static List<String> getList() {
		return list;
	}
}
