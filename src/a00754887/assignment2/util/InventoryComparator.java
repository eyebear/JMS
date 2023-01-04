package a00754887.assignment2.util;

import java.util.Comparator;

public class InventoryComparator {
	/**
	 * this class implements class comparing by either first name or last name,
	 * alphabetically sorted with the first letter
	 * 
	 * @author Ao
	 * 
	 */
	public static class CompareByString implements Comparator<String> {
		public int compare(String s1, String s2) {
			return s1.charAt(0) - s2.charAt(0);
		}
	}

}
