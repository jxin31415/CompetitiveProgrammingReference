package References;

import java.util.*;

public class Tuple {
	static class tup implements Comparable<tup>, Comparator<tup>{
		int a, b;
		tup(int a,int b){
			this.a=a;
			this.b=b;
		}
		public tup() {
		}
		@Override
		public int compareTo(tup o){
			return Integer.compare(b,o.b);
		}
		@Override
		public int compare(tup o1, tup o2) {
			return Integer.compare(o1.b, o2.b);
		}
		
		@Override
	    public int hashCode() {
			return Objects.hash(a, b);
	    }
 
	    @Override
	    public boolean equals(Object obj) {
	    	if (this == obj)
                return true;
	    	if (obj == null)
                return false;
	    	if (getClass() != obj.getClass())
                return false;
	    	tup other = (tup) obj;
	    	return a==other.a && b==other.b;
	    }
	    
	    @Override
	    public String toString() {
	    	return a + " " + b;
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
		
		@Override
		public String toString() {
			return left + " " + right;
		}
	}
}
