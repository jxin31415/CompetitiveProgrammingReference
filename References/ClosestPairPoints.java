package References;

import java.util.*;

public class ClosestPairPoints {
    
	public static class ClosestPair {
		
		  // Each row in points represents a point (x, y)
		  private double[][] points;
		  Point p1, p2;

		  ClosestPair() {
		  }   
		  
		  public ClosestPair(double[][] points) {
		    setPoints(points);
		  } 
		  
		  public void setPoints(double[][] points) {
		    this.points = points;
		  }
		  
		  public double getMinimumDistance() {    
		    Point[] pointsOrderedOnX = new Point[points.length];
		    
		    for (int i = 0; i < pointsOrderedOnX.length; i++)
		      pointsOrderedOnX[i] = new Point(points[i][0], points[i][1]);
		    
		    Arrays.sort(pointsOrderedOnX);

		    // Locate the identical points if exists
		    if (checkIdentical(pointsOrderedOnX))
		      return 0; // The distance between the identical points is 0
		    
		    Point[] pointsOrderedOnY = pointsOrderedOnX.clone();
		    Arrays.sort(pointsOrderedOnY, new CompareY());
		    
		    return distance(pointsOrderedOnX, 0, 
		        pointsOrderedOnX.length - 1, pointsOrderedOnY);
		  }
		  
		  // Locate the identical points if exist
		  public boolean checkIdentical(Point[] pointsOrderedOnX) {
		    for (int i = 0; i < pointsOrderedOnX.length - 1; i++) {
		      if (pointsOrderedOnX[i].compareTo(pointsOrderedOnX[i + 1]) == 0) {
		        p1 = pointsOrderedOnX[i];
		        p2 = pointsOrderedOnX[i + 1];
		        return true;        
		      }
		    }
		    
		    return false;
		  }

		  /** Return the distance of the closest pair of points in 
		   *  pointsOrderedOnY. 
		   *  pointsOrderedOnX[low..high] and pointsOrderedOnY contain the 
		   *  same points. */
		  public double distance(
		      Point[] pointsOrderedOnX, int low, int high,
		      Point[] pointsOrderedOnY) {
		    if (low >= high) // Zero or one point in the set
		      return Double.MAX_VALUE;
		    else if (low + 1 == high) {
		      // Two points in the set
		      p1 = pointsOrderedOnX[low];
		      p2 = pointsOrderedOnX[high];
		      return distance(pointsOrderedOnX[low], pointsOrderedOnX[high]);
		    }

		    // Divide the points into two sets in pointsOrderedOnX.
		    // Store the points p into pointsOrderedOnYL if 
		    //   p.x <= pointsOrderedOnX[mid]. Otherwise, store it into
		    // pointsOrderedOnYR. The points in pointsOrderedOnYL and 
		    // pointsOrderedOnYL are ordered on their y-coordinates    
		    int mid = (low + high) / 2;
		    Point[] pointsOrderedOnYL = new Point[mid - low + 1];
		    Point[] pointsOrderedOnYR = new Point[high - mid];
		    int j1 = 0; int j2 = 0;
		    for (int i = 0; i < pointsOrderedOnY.length; i++) {
		      if (pointsOrderedOnY[i].compareTo(pointsOrderedOnX[mid]) <= 0)
		        pointsOrderedOnYL[j1++] = pointsOrderedOnY[i];
		      else
		        pointsOrderedOnYR[j2++] = pointsOrderedOnY[i];
		    }

		    // Recursively find the distance of the closest pair in the left
		    // half and the right half
		    double d1 = distance(
		      pointsOrderedOnX, low, mid, pointsOrderedOnYL);
		    double d2 = distance(
		      pointsOrderedOnX, mid + 1, high, pointsOrderedOnYR);
		    double d = Math.min(d1, d2);

		    // stripL: the points in pointsOrderedOnYL within the strip d
		    int count = 0;
		    for (int i = 0; i < pointsOrderedOnYL.length; i++)
		      if (pointsOrderedOnYL[i].x >= pointsOrderedOnX[mid].x - d)
		        count++;
		    Point[] stripL = new Point[count];
		    count = 0;
		    for (int i = 0; i < pointsOrderedOnYL.length; i++)
		      if (pointsOrderedOnYL[i].x >= pointsOrderedOnX[mid].x - d)
		        stripL[count++] = pointsOrderedOnYL[i];

		    // stripR: the points in pointsOrderedOnYR within the strip d
		    count = 0;
		    for (int i = 0; i < pointsOrderedOnYR.length; i++)
		      if (pointsOrderedOnYR[i].x <= pointsOrderedOnX[mid].x + d)
		        count++;
		    Point[] stripR = new Point[count];
		    count = 0;
		    for (int i = 0; i < pointsOrderedOnYR.length; i++)
		      if (pointsOrderedOnYR[i].x <= pointsOrderedOnX[mid].x + d)
		        stripR[count++] = pointsOrderedOnYR[i];

		    // Find the closest pair for a point in stripL and 
		    // a point in stripR
		    double d3 = d;
		    int j = 0;
		    for (int i = 0; i < stripL.length; i++) {
		      while (j < stripR.length && stripL[i].y > stripR[j].y + d)
		        j++;

		      // Compare a point in stripL with six points in stripR
		      int k = j; // Start from r1 up in stripR
		      while (k < stripR.length && stripR[k].y <= stripL[i].y + d) {
		        if (d3 > distance(stripL[i], stripR[k])) {
		          d3 = distance(stripL[i], stripR[k]);
		          p1 = stripL[i];
		          p2 = stripR[k];
		        }
		        k++;
		      }
		    }

		    return Math.min(d, d3);
		  }

		  // Compute the distance between two points p1 and p2
		  public static double distance(Point p1, Point p2) {
		    return distance(p1.x, p1.y, p2.x, p2.y);
		  }

		  //Compute the distance between two points (x1, y1) and (x2, y2)
		  public static double distance(double x1, double y1, double x2, double y2) {
		    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		  }

		  
		  // Sort by y-coordinate
		  static class CompareY implements Comparator<Point> {
		    public int compare(Point p1, Point p2) {
		    	return p1.y == p2.y ? Double.compare(p1.x, p2.x) : Double.compare(p1.y, p2.y);
		    }
		  }
		  
		}

	static class Point implements Comparable<Point> {
	    double x;
	    double y;

	    Point(double x, double y) {
	      this.x = x;
	      this.y = y;
	    }

	    public int compareTo(Point p2) {
	    	return (this.x == p2.x) ? Double.compare(y, p2.y) : Double.compare(x, p2.x);
	    }
	}
}
