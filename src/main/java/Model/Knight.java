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
public class Knight extends ChessPiece {

    public Knight(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.type = Type.KNIGHT;
    }
    
    @Override
    public boolean isValidPath(Position targetPos) {
        return true;
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
