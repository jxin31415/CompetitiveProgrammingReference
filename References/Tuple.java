package References;

import java.util.*;

public class Tuple {
	public static class Tup implements Comparator<Tup>, Comparable<Tup>{

		public final int left;
		public final int right;

		public Tup(int left, int right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int hashCode() { return Integer.hashCode(left) ^ Integer.hashCode(right); }

		@Override
		public boolean equals(Object o) {
			@SuppressWarnings("unchecked")
			Pair<Integer, Integer> pairo = (Pair<Integer,Integer>) o;
			return this.left == pairo.left &&
		           this.right == pairo.right;
		  }

		@Override
		public int compareTo(Tup o) {
			if(Integer.compare(this.left, o.left) == 0) {
				return Integer.compare(this.right, o.right);
			}
			return Integer.compare(this.left, o.left);
		}

		@Override
		public int compare(Tup o1, Tup o2) {
			if(Integer.compare(o1.left, o2.left) == 0) {
				return Integer.compare(o1.right, o2.right);
			}
			return Integer.compare(o1.left, o2.left);
		}
	}
	
	public static class Pair<L,R> implements Comparator<Pair<L,R>>, Comparable<Pair<L,R>>{

		public final L left;
		public final R right;

		public Pair(L left, R right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int hashCode() { return left.hashCode() ^ right.hashCode(); }

		@Override
		public boolean equals(Object o) {
			@SuppressWarnings("unchecked")
			Pair<L,R> pairo = (Pair<L,R>) o;
			return this.left.equals(pairo.left) &&
		           this.right.equals(pairo.right);
		  }

		@Override
		public int compareTo(Pair<L, R> o) {
			return 0;
		}

		@Override
		public int compare(Pair<L, R> o1, Pair<L, R> o2) {
			return 0;
		}
	}
}
