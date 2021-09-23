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
    public boolean isValidPath(Position targetPos) {
        return true;
    }

    @Override
    public Position[] drawPath(Position targetPos) {
        
        int numPoints = 0;
        Position[] posList = new Position[numPoints];
        
        return posList;
    }
    
}
