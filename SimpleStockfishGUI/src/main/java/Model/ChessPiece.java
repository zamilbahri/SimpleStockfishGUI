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
public abstract class ChessPiece {
    	public int BOARD_MAX = 7, BOARD_MIN = 0;// board dimension
    private boolean isWhite; // true or false
    Position pos;
    Type type;

    public ChessPiece(boolean isWhite, Position pos) {
        this.isWhite = isWhite;
        this.pos = pos;
    }
    /**
     * A function that indicates the colour of this piece
     * @return true if white, false is black
     */
    public boolean isWhite() {
        return isWhite;
    }
    
    /**
     * 
     * @returns the type of the Piece (Rook, Knight, Bishop, Queen, King, Pawn) 
     */
    public Type getType() {
        return this.type;
    };
    
    /**
     * 
     * @param targetPos the position where the Piece is considering moving to
     * @return a boolean indicating if the path is valid
     */
    public abstract boolean isValidPath(Position targetPos);
    
    /**
     * Creates an array of all positions that the piece can move if the board
     * was empty.
     * @return the array of possible moves
     */
    public abstract Position[] generatePseudoLegalMoves();

    @Override
    public String toString() {
        return String.format("{%s, (%s, %s), %s}", type, pos.getCol(), pos.getRow(), isWhite);
    }
    
    
    
}