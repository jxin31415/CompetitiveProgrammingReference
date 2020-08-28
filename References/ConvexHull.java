package References;
import java.util.*;

public class ConvexHull {
	
	static class Line {
		long m;
		long b;
		public Line(long M, long B) {
			m = M; b = B;
		}
		
		public long valueAt(long x) {
			return m * x + b;
		}
		
		public double intersectionAt(Line o) { // returns x value of intersection
			return ((double)o.b - b) / (m - o.m);
		}
	}
	
	/*
	 * Convex hull trick: a DP optimization
	 * 
	 * Given some set of lines, we want to find the maximum y-value from those lines at some x-value.
	 * In other words, we are trying to find
	 * 
	 * max { m_i * x + b_i } for some x value.
	 * 
	 * Supported operations:
	 * 	1. Query. Query for the maximum at some x value.
	 * 	2. Insert. Insert a new line / function.
	 * 
	 * Note that the actual operations can be very different depending on whether the operations are sorted by x or y, if they're ascending or descending,
	 * if lines can be parallel, etc.
	 * 
	 * In this case, we are assuming are no parallel lines and the operations are sorted in increasing x value and decreasing y value.
	 * 
	 */
	public static void convexHullTrick(Queue<Integer> operations, Queue<Integer> queries, Queue<Line> insertions) {
		ArrayDeque<Line> deq = new ArrayDeque<>(); // store the lines in convex hull

		while(operations.size() > 0) {
			int o = operations.poll();
			if(o == 0) {
				// Query
				int q = queries.poll();
				
				// change according to problem specifications
				Line right = deq.pollLast();
				while(deq.size() > 0 && right.valueAt(q) <= deq.peekLast().valueAt(q)) {
					right = deq.pollLast();
				}
				deq.addLast(right);
				
				
				// print answer
				System.out.println(right.valueAt(q));
			} else {
				// Insertion
				Line insert = insertions.poll();
				
				// change according to problem specifications
				Line left = deq.pollFirst();
				while(deq.size() > 0 && insert.intersectionAt(deq.peekFirst()) >= left.intersectionAt(deq.peekFirst())) {
					left = deq.pollFirst();
				}
				deq.addFirst(left);
				deq.addFirst(insert);
				
			}
		}
	}
	
	
	
	boolean cw(Point a, Point b, Point c) {
        return a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y) < 0;
    }
    
    boolean ccw(Point a, Point b, Point c) {
        return a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y) > 0;
    }
    
    static Stack<Point> hull = new Stack<Point>();
    ArrayList<Point> convex_hull(Point[] a, int n) { // MAKE SURE TO SORT BY X; int n = points.length;
        hull.push(a[0]);       // a[0] is first extreme point
        int k1;
        for (k1 = 1; k1 < n; k1++)
            if (a[0].x != a[k1].x || a[0].y != a[k1].y) break;
        if (k1 == n) return new ArrayList<Point>();        // all points equal
        int k2;
        for(k2 = k1 + 1; k2 < n; k2++)
            if(ccw(a[0], a[k1], a[k2]) || cw(a[0], a[k1], a[k2])) break;
        hull.push(a[k2 - 1]);
        
        for (int i = k2; i < n; i++) {
            Point top = hull.pop();
            while (!hull.isEmpty() && !ccw(hull.peek(), top, a[i])) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(a[i]);
        }
        ArrayList<Point> chull = new ArrayList<>();
        for(Point x : hull) chull.add(x);
        return chull;
    }
    
    static class Point implements Comparable<Point>, Comparator<Point>{
		int x, y;
		Point(int a,int b){
			this.x=a;
			this.y=b;
		}
		public Point() {
		}
		@Override
		public int compareTo(Point o){
			return Integer.compare(o.x,x);
		}
		@Override
		public int compare(Point o1, Point o2) {
			return Integer.compare(o1.x, o2.x);
		}
		
		@Override
	    public int hashCode() {
			return Objects.hash(x, y);
	    }
 
	    @Override
	    public boolean equals(Object obj) {
	    	if (this == obj)
                return true;
	    	if (obj == null)
                return false;
	    	if (getClass() != obj.getClass())
                return false;
	    	Point other = (Point) obj;
	    	return x==other.x && y==other.y;
	    }
	    
	    @Override
	    public String toString() {
	    	return x + " " + y;
	    }
	}
}
