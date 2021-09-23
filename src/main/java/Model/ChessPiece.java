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
public abstract class ChessPiece {
// Not sure whether to use Interface or Abstract class. Don't think it matters?
    
    private boolean white; // true or false
    private boolean taken;
    Position pos;
    Type type;

    public ChessPiece(boolean isWhite, Position pos) {
        this.white = isWhite;
        this.taken = false;
        this.pos = pos;
    }

    public boolean isWhite() {
        return white;
    }

    public void take(boolean taken) {
        this.taken = taken;
    }

    public boolean isTaken() {
        return taken;
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
     * Creates an array of all Positions between current Position and targetPosition
     * @param targetPos the position where the piece is considering moving to
     * @return an array of Position objects of the given path
     */
    public abstract Position[] drawPath(Position targetPos);
    
}