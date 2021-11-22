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
public class Knight extends ChessPiece {

    public Knight(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.type = Type.KNIGHT;
    }
    
    @Override
    public boolean isValidPath(Position targetPos, ChessBoard newBoard) {
    	ChessPiece targetPiece = targetPos.getNewPiece();
    	if(targetPiece != null && targetPiece.isWhite()) {
    		return false;
    	}
        return true;
    }
    
    public ArrayList<Position> KnightMoves(ChessBoard chessBoard){
    	Position[][] newPosition = chessBoard.getSquare();
    	ArrayList<Position> movement = new ArrayList<Position>();
    	Position newSpace = currentSpace;
    	int boardNumWidth = newSpace.getCol();
    	int boardNumHeight = newSpace.getRow();
    	// x axis
    	int newCol = chessBoard.getWidth();
    	// y axis
    	int newRow = chessBoard.getHeight();
    	
    	// Still need to work on movement. Set right values.
    	
    	if ((newCol + 2) < boardNumWidth && (newRow + 1) < boardNumHeight) {
    		movement.add(newPosition[newCol + 2][newRow + 1]);
    	}
    	if ((newCol + 1) < boardNumWidth && (newRow + 2) < boardNumHeight){
    		movement.add(newPosition[newCol + 1][newRow + 2]);
    	}
    	if ((newCol - 1) < boardNumWidth && (newRow - 2) < boardNumHeight) {
    		movement.add(newPosition[newCol - 1][newRow - 2]);
    	}
    	if ((newCol - 2) < boardNumWidth && (newRow - 1) < boardNumHeight) {
    		movement.add(newPosition[newCol - 2][newRow - 1]);
    	}
    	if ((newCol + 2) > boardNumWidth && (newRow + 1) > boardNumHeight) {
    		movement.add(newPosition[newCol + 2][newRow + 1]);
    	}
    	if ((newCol + 2) > boardNumWidth && (newRow + 1) > boardNumHeight) {
    		movement.add(newPosition[newCol + 2][newRow + 1]);
    	}
    	if ((newCol + 2) > boardNumWidth && (newRow + 1) > boardNumHeight) {
    		movement.add(newPosition[newCol + 2][newRow + 1]);
    	}
    	if ((newCol + 2) > boardNumWidth && (newRow + 1) > boardNumHeight) {
    		movement.add(newPosition[newCol + 2][newRow + 1]);
    	}
    	
    	
    	return movement;
    }

    @Override
    public Position[] generatePseudoLegalMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
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
