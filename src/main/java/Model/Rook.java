/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author zamil
 */
public class Rook extends ChessPiece {

    public Rook(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.type = Type.ROOK;
    }
	
    // Still need to work on
    public ArrayList<Position> RookMoves(ChessBoard chessBoard){
    	Position[][] newPosition = chessBoard.getSquare();
    	ArrayList<Position> movement = new ArrayList<Position>();
    	Position newSpace = currentSpace;
    	int boardNumWidth = newSpace.getCol();
    	int boardNumHeight = newSpace.getRow();
    	// x axis 
    	int newCol = chessBoard.getWidth();
    	// y axis 
    	int newRow = chessBoard.getHeight();
    	
    	for(int i = 0; i < boardNumWidth; i++) {
    		if(i != newCol) {
    			movement.add(newPosition[i][newRow]);
    		}
    	}
    	
    	for(int j = 0; j < boardNumHeight; j++) {
    		if(j != newRow) {
    			movement.add(newPosition[newCol][j]);
    		}
    	}
    	
		return movement;
    }
    
    @Override
    public boolean isValidPath(Position targetPos, ChessBoard chessBoard) {
    	Position[][] newPosition = chessBoard.getSquare();
    	Position newSpace = currentSpace;
    	int boardNumWidth = newSpace.getCol();
    	int boardNumHeight = newSpace.getRow();
    	// x axis
    	int newCol = targetPos.getCol();
    	// y axis 
    	int newRow = targetPos.getRow();
    	boolean canMove = true;
    	
    	if (newCol == boardNumWidth && newRow < boardNumHeight) {
    		for(int i = (newRow + 1); i < boardNumWidth; i++) {
    			Position Pos = newPosition[newRow][i];
    			canMove = (Pos.getNewPiece() == null);
    		}
    	}
    	if (newCol == boardNumWidth && newRow < boardNumHeight) {
    		for (int i = (newRow + 1); i < boardNumHeight; i++) {
    			Position Pos = newPosition[newRow][i];
    			canMove = (Pos.getNewPiece() == null);
    		}
    	}
    	if (newCol == boardNumHeight && newRow > boardNumWidth) {
    		for (int i = (newRow + 1); i < boardNumHeight; i++) {
    			Position Pos = newPosition[newRow][i];
    			canMove = (Pos.getNewPiece() == null);
    		}
    	}
    	if (newCol == boardNumWidth && newRow > boardNumHeight) {
    		for (int i = (newRow + 1); i < boardNumHeight; i++) {
    			Position Pos = newPosition[newRow][i];
    			canMove = (Pos.getNewPiece() == null);
    		}
    	}
    	
        // returns true if current and target positions have same row or column
        if ((this.pos.getRow() == targetPos.getRow() || this.pos.getCol() == targetPos.getCol())){
        	return true;
        }
		return canMove;
    }
    
    @Override
    public Position[] generatePseudoLegalMoves() {
        // Needs to be properly implemented
        
        return new Position[] {new Position(8), new Position(16)};
    }
    
    @Override
    public Type getType() {
        return super.getType();
    }

    @Override
    public boolean isWhite() {
        return super.isWhite();
    }   
}
