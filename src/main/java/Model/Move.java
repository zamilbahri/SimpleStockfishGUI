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
public class Move {
    Position startSquare;
    Position targetSquare;
    String moveStr;

    public Move(Position startSquare, Position targetSquare) {
        this.startSquare = startSquare;
        this.targetSquare = targetSquare;
        this.moveStr = startSquare.getAlgebraic() + targetSquare.getAlgebraic();
    }
    
    public Move(String startSquare, String targetSquare) {
        this.startSquare = new Position(startSquare);
        this.targetSquare = new Position(targetSquare);
        this.moveStr = startSquare + targetSquare;
    }

    public Position getStartSquare() {
        return startSquare;
    }

    public Position getTargetSquare() {
        return targetSquare;
    }
    
    
    
    
}
