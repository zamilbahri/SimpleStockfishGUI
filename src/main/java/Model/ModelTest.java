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
        
        
        Position p1 = new Position("e2");
        Position p2 = new Position("e4");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        
        
        /*
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
        */
        
        //ChessBoard cb = new ChessBoard("r3k2r/pp1b1ppp/1qnbpn2/2ppN3/3P1B2/1QPBP3/PP1N1PPP/R4RK1 w kq - 0 1");
        ChessBoard cb = new ChessBoard();
        cb.buildBoard();
        
        cb.move(p1, p2);
        System.out.println(cb.toString());
        cb.move(new Position("d7"), new Position("d5"));
        System.out.println(cb.toString());
        cb.move(new Position("e4"), new Position("d5"));
        System.out.println(cb.toString());
        
        System.out.println(cb.getFen());
    }
}
