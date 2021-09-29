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
public class Rook extends ChessPiece {

    public Rook(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.type = Type.ROOK;
    }
    
    @Override
    public Position[] generatePseudoLegalMoves() {
        // Needs to be properly implemented
        
        return new Position[] {new Position(8), new Position(16)};
    }
    
    @Override
    public boolean isValidPath(Position targetPos) {
        // returns true if current and target positions have same row or column
        return (this.pos.getRow() == targetPos.getRow() || this.pos.getCol() == targetPos.getCol());
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
