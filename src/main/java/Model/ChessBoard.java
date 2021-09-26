/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.ChessPiece;
import Model.Type;
/**
 *
 * @author zamil
 */
public class ChessBoard {
    
    private final static String DEFAULT_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private String fen; // = r3k2r/pp1b1ppp/1qnbpn2/2ppN3/3P1B2/1QPBP3/PP1N1PPP/R4RK1 w kq - 0 1
    public char[] pieceMap = new char[64];
    boolean activePiece;
    
    public ChessBoard() {
        this.fen = DEFAULT_FEN;
        this.activePiece = true; // true = white, false = black
    }
    
    public ChessBoard(String fen) {
        this.fen = fen;
    }
    
    public void buildBoard() {
        String fenSplit[] = fen.split(" ");
        String fenBoard = fenSplit[0];
        this.activePiece = (fenSplit[1] == "w") ? true : false;
        
        int row = 0;
        int col = 0;
        int fen_len = fenBoard.length();
        int index;
        char c;
        
        
        for (int i = 0; i < fen_len; ++i) {
            c = fenBoard.charAt(i);
            index = row*8 + col;
            
            if (c == '/') {
                row++;
                col = 0;
            }
            
            else if (Character.isLetter(c)) {
                pieceMap[index] = c;
                col++;
                //System.out.println(String.format("c: %d, col: %d", c, col));
            }
            
            else if (Character.isDigit(c)) {
                for (int j = 0; j < c-'0'; ++j) {
                    //pieceMap[index] = (char)(col + '0');
                    pieceMap[index] = '.';
                    col++;
                    index++;
                }
            }
            else {
                System.out.println("something went wrong");
            }
        }
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