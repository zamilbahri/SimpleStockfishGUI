/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ChessBoardGUI;
import Model.ChessBoard;
/**
 *
 * @author zamil
 */
public class ChessBoardController {
    
    static String fen;
    
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //ChessBoard cb = new ChessBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
            ChessBoard cb = new ChessBoard("r4rk1/p4p2/5Rpb/Qp2p2p/3pP2P/6PB/Pq6/5R1K w - - 0 25");
            char pieceMap[] = cb.pieceMap;
            
            ChessBoardGUI gui = new ChessBoardGUI(pieceMap);
            
            //gui.setVisible(true);
        });
    }
    
    
}
