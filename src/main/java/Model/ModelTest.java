/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Position;
import Model.ChessPiece;
import Model.ChessBoard;
/**
 *
 * @author zamil
 */
public class ModelTest {
    
    public static void main(String args[]) {
        
        
        Position p1 = new Position("a3");
        Position p2 = new Position("f3");
        Position p3 = new Position("a7");
        
        ChessPiece rookW = new Rook(true, p1);
        
        System.out.println(String.format("Path from %s to %s", p1.getAlgebraic(), p3.getAlgebraic()));
        Position path[] = rookW.drawPath(p3);
        int path_len = path.length;
        for (int i = 0; i < path_len; ++i) {
            System.out.print(path[i].getAlgebraic() + " ");
        }
        System.out.println(""); // prints an empty line
        
        ChessBoard cb = new ChessBoard();
        System.out.println(cb.toString());
    }
}
