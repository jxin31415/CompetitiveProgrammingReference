package References;
import java.util.*;

public class ConvexHull {
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
