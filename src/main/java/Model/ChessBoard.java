/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.ChessPiece;
import java.util.HashMap;
import Model.Type;
/**
 *
 * @author zamil
 */
public class ChessBoard {
    
    private final static String DEFAULT_FEN = "r3k2r/pp1b1ppp/1qnbpn2/2ppN3/3P1B2/1QPBP3/PP1N1PPP/R4RK1 w kq - 0 1";
    private String fen;
    
    
    public ChessBoard() {
        this.fen = DEFAULT_FEN;
    }
    
    public ChessBoard(String fen) {
        this.fen = fen;
    }

    @Override
    public String toString() {
        
        String fenBoard = fen.split(" ")[0];
        int rank = 8;
        int fen_len = fenBoard.length();
        char c;
        
        String HORIZONTAL_LINE = "---------------------------------";
        
        String cols = "";
        for (char col = 'a'; col <= 'h'; ++col) {
            cols += "  " + col + " ";
        }
        
        String boardString = cols + "\n" + HORIZONTAL_LINE + "\n| ";
        
        for (int i = 0; i < fen_len; ++i) {
            c = fenBoard.charAt(i);
            
            if (c == '/') {
                boardString += Integer.toString(rank) + "\n";
                boardString += HORIZONTAL_LINE + "\n";
                boardString += "| ";
                rank--;
                
            } else {
                if (Character.isLetter(c)) {
                    boardString += c + " | ";
                    
                } else if (Character.isDigit(c)) {
                    
                    for (int j = 0; j < c - '0'; ++j) {
                        boardString += " " + " | ";
                    }
                }
            }
        }
        
        boardString += "1\n" + HORIZONTAL_LINE;

        
        return boardString;
    }
    
    
    
    
    
    

}