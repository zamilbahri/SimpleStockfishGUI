/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author zamil
 */
public class Position {
    
    private int col; // x axis
    private int row; // y axis
    
    // Chess algebraic notation is expressed as "[file][rank]", from the
    // perspective of white. Left-most file (column) is "a", and the bottom 
    // rank (row) is "1". 
    // Example: "d5", where "d" is the 4th file/column (index = 3), and "5" is the 5th
    // rank/row (index = 4).
    private String algebraic;
    
    /**
     * Converts Cartesian coordinates to algebraic notation
     * @param col the column or "file"
     * @param row the row or "rank"
     * @return the algebraic notation for the associated Cartesian coordinates
     */
    private String cartesianToAlgebraic(int col, int row) {
        // convert 'col' to ASCII character, then convert to string.
        String file = String.valueOf((char)(col + 'a'));
        
        // (8-'row') because bottom row considered the first row in chess.
        String rank = String.valueOf(row + 1);
        
        return file+rank;
    }
    
    /**
     * Converts algebraic notation to Cartesian coordinates
     * @param algebraic
     * @return Cartesian coordinates. Example: (3,4) will be returned as "34"
     */
    private String algebraicToCartesian(String algebraic) {
        String col = String.valueOf(algebraic.charAt(0) - 'a');
        String row = String.valueOf(algebraic.charAt(1) - '1');
        
        return col+row;
    }
    
    /**
     * Creates a Position object based on Cartesian input
     * @param col
     * @param row 
     */
    public Position(int col, int row) {
        this.col = col;
        this.row = row;
        
        // calculate algebraic notation
        this.algebraic = cartesianToAlgebraic(col, row);
    }
    
    /**
     * Creates a Position object based on algebraic input
     * @param algebraic 
     */
    public Position(String algebraic) {
        this.algebraic = algebraic;
        
        // extract Cartesian coords
        String coords = algebraicToCartesian(algebraic);
        this.col = coords.charAt(0) - '0';
        this.row = coords.charAt(1) - '0';
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setAlgebraic(String algebraic) {
        this.algebraic = algebraic;
    }

    @Override
    public String toString() {
        return String.format("Algebraic: %s, Cartesian: (%d, %d)", algebraic, col, row);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getAlgebraic() {
        return algebraic;
    }
    
}
