  /**************
  * PercolationStats. 
  * Author: Sriram Rajkumar. Last Modified : 4th june 2017.
  * Dependecies : This code requires some standard libraries provided along 
  * "Algorithms Part 1" Coursera. This also Percolation.java.
  * Takes n-gridSize as first input and T-Trials as the second input.
  * Creates an n by n grid then determines the percolation thershold by 
  * Monte Carlo simulation. 
  */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] outcomeArr;
    private int  trialsNo;
    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("The Value Should be above zero"); 
        }
        outcomeArr = new double[trials];
        trialsNo = trials;
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1)); 
            }
            double gridSize = n*n;
            outcomeArr[i] = (perc.numberOfOpenSites())/(gridSize);
   
        }
    }
    
    
    public double mean() {
        double avg = StdStats.mean(outcomeArr); 
        return avg;
    }                          
   
    public double stddev() {
        double dev = StdStats.stddev(outcomeArr); 
        return dev;
    }                        
    
    public double confidenceLo() {
        double tempTr = trialsNo;
        double temp  = (StdStats.stddev(outcomeArr)*1.96)/ (Math.sqrt(tempTr));
        double lo = StdStats.mean(outcomeArr)-temp;
        return lo;
    }                  
    
    public double confidenceHi() {
        double tempTr = trialsNo; 
        double temp = (StdStats.stddev(outcomeArr)*1.96)/(Math.sqrt(tempTr));
        double hi = StdStats.mean(outcomeArr)+temp;
        return hi;
    }                  
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats pstats = new PercolationStats(n, trials);
        StdOut.println("Mean                     = " + pstats.mean());
        StdOut.println("stddev                   = " + pstats.stddev());
        StdOut.println("95% Confidence Interval  = " + "[" + pstats.confidenceLo() + "," + pstats.confidenceHi()+ "]");
        
    }        // test client (described below)
}