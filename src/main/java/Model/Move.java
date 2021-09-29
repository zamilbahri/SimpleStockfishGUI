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

    public Move(Position startSquare, Position targetSquare) {
        this.startSquare = startSquare;
        this.targetSquare = targetSquare;
    }
    
    
}
