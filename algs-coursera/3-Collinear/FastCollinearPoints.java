/******************************************************************************
 *  Compilation:   javac FastCollinearPoints.java
 *  Execution:     java  FastCollinearPointsCollinearPoints
 *  Dependencies:  Point.java and LineSegment
 *  @ Author:      Sriram Rajkumar
 *  Last Modified: 2 July 2017  
 *  An immutable data type for finding collinear points in a plane.This passed 
 *  all the tests in the autograder. 
 *
 ******************************************************************************/

import java.util.*;

public class FastCollinearPoints {
    private LineSegment[] lineseg;
    private int constant;
    private int smallestSeen;
    
    public FastCollinearPoints(Point[] points) {  // finds all line segments containing 4 or more points
        cornerCases(points);
        final List<LineSegment> segs = new ArrayList<LineSegment>();
        Point[] copyofPoints2 = Arrays.copyOf(points, points.length); 
        Arrays.sort(copyofPoints2);
        
        
        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];

            Point[] copyofPoints = Arrays.copyOf(copyofPoints2, copyofPoints2.length); 
            Arrays.sort(copyofPoints, origin.slopeOrder());
          
         
            
            for (int index = 0; index < copyofPoints.length; index = index + constant) {
                    constant = 1;
                     
                    if (index+constant < copyofPoints.length) {                        
                        while (origin.slopeTo(copyofPoints[index]) == origin.slopeTo(copyofPoints[index+constant])) {                           
                            constant = constant+1;
                            if (index+constant >= copyofPoints.length) break;
                        }    
                    }
                    
               
                    if (constant > 2) {
                        for (int j = index; j < index+constant; j++) {
                            smallestSeen = origin.compareTo(copyofPoints[j]);
                            if (smallestSeen > 0) break;
                        }
                    if (smallestSeen <= 0) {
                    LineSegment subseg = new LineSegment(origin, copyofPoints[index + constant-1]);               
                    segs.add(subseg);
                    }
                }    
            }
            
        }
        lineseg = segs.toArray(new LineSegment[segs.size()]);
    }   
    public           int numberOfSegments()  {   // the number of line segments
        return lineseg.length;
    }   
    public LineSegment[] segments() { // the line segments      
        return Arrays.copyOf(lineseg,lineseg.length); 
    } 
    
     private void cornerCases(Point[] points) {
        
        if (points == null || points.length == 0) {
            throw new java.lang.IllegalArgumentException();
        }
        for(int i = 0; i<points.length; i++){
            if (points[i] == null)
                    throw new java.lang.IllegalArgumentException();
        }
        for(int i = 0; i<points.length; i++) 
            for (int j = i+1; j<points.length; j++)
                if ((points[i].compareTo(points[j])) == 0)
                    throw new java.lang.IllegalArgumentException();
    }
    
    public static void main(String[] args) {

//      if(args.length != 1)
//    throw new IllegalArgumentException("No file name.");
//    // read the n points from a file
//    String filename = args[0];
//    In in = new In(filename);
//    int n = in.readInt();
//    Point[] points2 = new Point[n];
//    for (int i = 0; i < n; i++) {
//        int x = in.readInt();
//        int y = in.readInt();
//        points2[i] = new Point(x, y);
//    }
//
//    // draw the points
//    StdDraw.enableDoubleBuffering();
//    StdDraw.setXscale(0, 32768);
//    StdDraw.setYscale(0, 32768);
//    for (Point p : points2) {
//        p.draw();
//    }
//    StdDraw.show();
//
//    // print and draw the line segments
//    FastCollinearPoints collinear = new FastCollinearPoints(points2);
//    StdOut.println(collinear.numberOfSegments());
//    for (LineSegment segment : collinear.segments()) {
//        StdOut.println(segment);
//        segment.draw();
//    }
//    
//    StdDraw.show();
}

}