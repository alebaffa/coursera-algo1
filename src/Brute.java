import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

/**
 * Brute force.
 * 
 * Write a program Brute.java that examines 4 points at a time and checks
 * whether they all lie on the same line segment, printing out any such line
 * segments to standard output and drawing them using standard drawing. To check
 * whether the 4 points p, q, r, and s are collinear, check whether the slopes
 * between p and q, between p and r, and between p and s are all equal.
 * */
public class Brute {

	public static void main(String[] args) throws FileNotFoundException {
		String filename = args[0];
		Scanner sc = new Scanner(new File(filename));
		int num = sc.nextInt();

		List<Point> points = new ArrayList<Point>();

		for (int i = 0; i < num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			Point point = new Point(x, y);
			points.add(point);
		}
		sc.close();

		Collections.sort(points);

		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		for (int i = 0; i < points.size(); i++)
			points.get(i).draw();

		for (int i = 0; i < points.size(); i++) {
			for (int x = i + 1; x < points.size(); x++) {
				for (int y = x + 1; y < points.size(); y++) {
					for (int z = y + 1; z < points.size(); z++) {
						double slopeIX = points.get(i).slopeTo(points.get(x));
						double slopeXY = points.get(x).slopeTo(points.get(y));
						double slopeYZ = points.get(y).slopeTo(points.get(z));
						double slopeZI = points.get(z).slopeTo(points.get(i));
						if (slopeIX == slopeXY && slopeXY == slopeYZ
								&& slopeYZ == slopeZI && slopeIX == slopeZI) {
							points.get(i).drawTo(points.get(z));
						}
					}
				}
			}
		}
	}
}
