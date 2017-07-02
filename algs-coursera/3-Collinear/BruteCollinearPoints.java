/******************************************************************************
 *  Compilation:   javac BruteCollinearPoints.java
 *  Execution:     java BruteCollinearPoints
 *  Dependencies:  Point.java and LineSegment
 *  @ Author:      Sriram Rajkumar
 *  Last Modified: 2 July 2017  
 *  An immutable data type for finding collinear points in a plane.This passed 
 *  all the tests in the autograder. Examines 4 points at a time and checks 
 *  whether they all lie on the same line segment, returning all 
 *  such line segments. 
 *
 ******************************************************************************/
import java.util.*;

public class BruteCollinearPoints {
    private LineSegment[] lineseg;

    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        cornerCases(points);
        Point[] copyofPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyofPoints);
        final List<LineSegment> segs = new ArrayList<LineSegment>();
        for (int p = 0; p < points.length; p++)
            for (int q = p+1; q < points.length; q++)
                for (int r = q+1; r < points.length; r++)
            if (copyofPoints[p].slopeTo(copyofPoints[q]) == copyofPoints[p].slopeTo(copyofPoints[r])) {
                    for (int s = r+1; s < points.length; s++) {                      
                        if (copyofPoints[p].slopeTo(copyofPoints[q]) == copyofPoints[p].slopeTo(copyofPoints[s])) {
                            LineSegment subseg = new LineSegment(copyofPoints[p], copyofPoints[s]);               
                            segs.add(subseg);                                                       
                        }    
                    }
        }
       lineseg = segs.toArray(new LineSegment[segs.size()]);
    }
    
    public int numberOfSegments() {        // the number of line segments
        return lineseg.length;
    } 
    
    public LineSegment[] segments() {                // the line segments
         return Arrays.copyOf(lineseg, lineseg.length);

    }
    
    private void cornerCases(Point[] points) {
        
        if (points == null || points.length == 0) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                    throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) 
            for (int j = i+1; j < points.length; j++)
                if ((points[i].compareTo(points[j])) == 0)
                    throw new java.lang.IllegalArgumentException();
    }
    
    
    public static void main(String[] args) {

}
}