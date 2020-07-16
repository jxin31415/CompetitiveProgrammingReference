package References;
import java.util.*;
import java.io.*;

public class CustomComparator { 
	static class Element implements Comparator<Element> {
		int value1, value2;
		public Element(int n1, int n2) {
			value1 = n1;
			value2 = n2;
		}
		
		public String toString() {
			return "Element: " + value1 + " " + value2;
		}
		@Override
		public int compare(Element o1, Element o2) {
			if(o1.value1 * o1.value2 > o2.value1 * o2.value2) {
				return 1;
			}
			if(o1.value1 * o1.value2 < o2.value1 * o2.value2) {
				return -1;
			}
			return 0;
		}
		
	}
	public static void main (String [] args) throws IOException {
		List<Element> lis = new ArrayList<Element>();
		lis.add(new Element(2, 4));
		lis.add(new Element(1, 7));
		lis.add(new Element(3, 3));
		lis.add(new Element(1, 8));
		
		Element sorter = new Element(0, 0);
		
		System.out.println("Presorted: " + lis);
		
		Collections.sort(lis, sorter);
		
		System.out.println("Sorted(based on value 1 x value 2, ascending): " + lis);
	}
	
}
