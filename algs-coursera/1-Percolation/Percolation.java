  /****************************
  * Percolation Java. 
  * Author : Sriram Rajkumar. Last Modified : 4th June 2017.
  * Dependencied : This code rquires some standard libraries provided for 
  * "Algorithms Part1" Coursera.
  * This code takes in n-grid size and creates n by b grid with all sites blocked.
  * Backwashing has not been fixed yet.
  ********************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private WeightedQuickUnionUF uf;
    private int currSize;
    private int top = 0;
    private int bottom;
    private int id; 
    private byte[][]  op;
    private int opened = 0;
    
    public Percolation(int n) {
     // Creates n-by-n grid with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("n Should be above zero"); 
        }
        currSize = n+1;
        uf = new WeightedQuickUnionUF(currSize*currSize);
        bottom = currSize*currSize-1;
        for (int i = 1; i <= n; i++) {
            uf.union(top, i+n);
        }
        for (int i = 1; i <= n; i++) { 
            uf.union(bottom, i+((n*n)));
        } 
        op = new byte[currSize+1][currSize+1]; // check;
        currSize = n;
    }
        

    
    public void open(int row, int col) {
    // open site (row, col) if it is not open already
        boundary(row, col);
       
        if (!isOpen(row, col)) {
            op[row][col] = 1; // if the site is not open it     
            opened++;
        }        
        id = index(row, col);
        if ((op[row-1][col] == 1) && row-1 >= 1 && row-1 <= currSize) {
            uf.union(id, index(row-1, col)); // top
        }
        if ((op[row+1][col] == 1) && row+1 >= 1 && row+1 <= currSize) {
            uf.union(id, index(row+1, col)); // bottom
        }   
        if ((op[row][col-1] == 1) && col-1 >= 1 && col-1 <= currSize) {
            uf.union(id, index(row, col-1)); // left
        }
        if ((op[row][col+1] == 1) && col+1 >= 1 && col+1 <= currSize) {
            uf.union(id, index(row, col+1)); // right
        }             
    }
    
    public boolean isOpen(int row, int col) {
        boundary(row, col);
        return op[row][col] == 1;        
    }
    
    public boolean isFull(int row, int col) {
        boundary(row, col);
        id = index(row, col);
        return (uf.connected(top, id) && op[row][col] == 1);        
    }
    
    public int numberOfOpenSites() {
        // number of open sites
        return opened;    
    }
    
    public boolean percolates() {
        // does the system percolate?        
        return uf.connected(top, bottom);        
    }
    private void boundary(int row, int col) {
        if (row <= 0 || row > currSize) {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }
        if (col <= 0 || col > currSize) {
            throw new IndexOutOfBoundsException("Col index out of bounds");
        }
    }    
    private int index(int row, int col) {
        id = currSize*row+col;
        return id;    
    }  
    public static void main(String[] args) {
    // Test Client
    }

}