/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

import edu.princeton.cs.introcs.StdDraw;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new SlopeOrder(); // YOUR
																	// DEFINITION
																	// HERE

	private final int x; // x coordinate
	private final int y; // y coordinate

	private class SlopeOrder implements Comparator<Point> {

		/*
		 * The SLOPE_ORDER comparator should compare points by the slopes they
		 * make with the invoking point (x0, y0). Formally, the point (x1, y1)
		 * is less than the point (x2, y2) if and only if the slope (y1 − y0) /
		 * (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). Treat
		 * horizontal, vertical, and degenerate line segments as in the
		 * slopeTo() method.
		 */
		@Override
		public int compare(Point point1, Point point2) {
			double slope10 = slopeTo(point1);
			double slope20 = slopeTo(point2);
			if (slope10 < slope20)
				return -1;
			else if (slope10 > slope20)
				return 1;
			else
				return 0;
		}
	}

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	/*
	 * The slopeTo() method should return the slope between the invoking point
	 * (x0, y0) and the argument point (x1, y1), which is given by the formula
	 * (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as
	 * positive zero; treat the slope of a vertical line segment as positive
	 * infinity; treat the slope of a degenerate line segment (between a point
	 * and itself) as negative infinity.
	 */
	public double slopeTo(Point that) {
		// horizontal line
		if (that.y == y)
			return 0.0;
		// vertical line
		else if (that.x == x)
			return Double.POSITIVE_INFINITY;
		else if (that.x == x && that.y == y)
			return Double.NEGATIVE_INFINITY;
		else
			return (that.y - y) / (that.x - x);
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		if (this.y < that.y || (this.y == that.y && this.x < that.x))
			return -1;
		else if (this.y == that.y && this.x == that.x)
			return 0;
		return 1;
	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}
}
