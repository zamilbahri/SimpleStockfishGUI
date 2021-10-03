/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ChessBoardGUI;
import Model.ChessBoard;
import Model.Move;
import Model.Position;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import java.lang.Thread;
/**
 *
 * @author zamil
 */
public class ChessBoardController {
    
    //static String fen;
    ChessBoardGUI gui; // view
    ChessBoard cb; // model
    
    public ChessBoardController(ChessBoardGUI gui, ChessBoard cb) {
        this.gui = gui; // GUI: The View
        this.cb = cb; // ChessBoard: The Model
        
        // Add the FENListener class as an ActionListener for the
        //  fenInput text field
        this.gui.addFENListener(new FENListener());
    }
    
    // example of a computationally intensive function. This could be
    //  calling a function in the chessboard class that checks to see if the
    //  move made is a legal one.
    public boolean isLegalMove(Move move) throws InterruptedException {
        Thread.sleep(4200);
        return true;
    }
    
    /**
     * Action listener for the fenInput JTexrField. Builds the pieceMap[] for
     * ChessBoard when invoked.
     */
    class FENListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String fenInput = gui.getFEN();
            
            // Create a SwingWorker thread to build the pieceMap in the backghround
            //  and not pause the main program
            class InterpretFEN extends SwingWorker<Void, Object> {
                @Override
                protected Void doInBackground() throws Exception {
                    cb.setFen(fenInput);
                    return null;
                }
            }
            
            // Run the thread
            (new InterpretFEN()).execute();
            
            gui.updatePieceMap(cb.pieceMap);
            gui.update();
        } 
    }
    
    // incomplete
    class MoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Move move = gui.getMove();

        }
        
    }
    
    // Main function needs to be moved to a top level class
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //ChessBoard cb = new ChessBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
            // ChessBoard cb = new ChessBoard("r4rk1/p4p2/5Rpb/Qp2p2p/3pP2P/6PB/Pq6/5R1K w - - 0 25");
            ChessBoard cb = new ChessBoard();
            char pieceMap[] = cb.pieceMap;
          
            //ChessBoardGUI gui = new ChessBoardGUI(pieceMap);
            
            
        });
    }
 
    
}
