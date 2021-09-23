/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author zamil
 */
public class Rook extends ChessPiece {

    public Rook(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.type = Type.ROOK;
    }
    
    @Override
    public boolean isValidPath(Position targetPos) {
        // returns true if current and target positions have same row or column
        return (this.pos.getRow() == targetPos.getRow() || this.pos.getCol() == targetPos.getCol());
    }

    @Override
    public Position[] drawPath(Position targetPos) {
        
        int numPoints;
        int xDir; // right = 1, left = -1
        int yDir; // up = 1, down = -1
        
        int curCol = pos.getCol();
        int curRow = pos.getRow();
        int targetCol = targetPos.getCol();
        int targetRow = targetPos.getRow();
        
        int newRow, newCol;
        
        
        // Determine direction and number of points
        if (curCol == targetCol) { // same column (movement is vertical)
            numPoints = Math.abs(targetRow - curRow);
            xDir = 0;
            yDir = (targetRow - curRow) > 0 ? 1 : -1;
            
        } else { // same row (movement is horizontal)
            numPoints = Math.abs(targetCol - curCol);
            xDir = (targetCol - curCol) > 0 ? 1 : -1;
            yDir = 0;
        }
        
        // Add positions to an array
        Position[] posList = new Position[numPoints];
        
        for (int i = 1; i <= numPoints; ++i) {
            newCol = curCol + xDir*i;
            newRow = curRow + yDir*i;
            posList[i-1] = new Position(newCol, newRow);
        }
        
        return posList;
        
    }
    
    
    
    
}
