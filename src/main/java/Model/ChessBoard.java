/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.ChessPiece;
import Model.Type;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author zamil
 */
public class ChessBoard {
    
    private final static String DEFAULT_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private String fen; // = r3k2r/pp1b1ppp/1qnbpn2/2ppN3/3P1B2/1QPBP3/PP1N1PPP/R4RK1 w kq - 0 1
    public char[] pieceMap = new char[64];
    
    boolean activePiece;
    boolean whiteInCheck = false;
    boolean blackInCheck = true;
    
    // WhiteKingSide, WhiteQueenSide, BlackKingSide, BlackQueenSide
    // need to interpret this from FEN
    boolean[] castlingRights = {true, true, true, true};
    
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
        this.activePiece = ("w".equals(fenSplit[1])) ? true : false;
        
        int row = 7;
        int col = 0;
        int fen_len = fenBoard.length();
        int index;
        char c;
        
        
        for (int i = 0; i < fen_len; ++i) {
            c = fenBoard.charAt(i);
            index = row*8 + col;
            
            if (c == '/') {
                row--;
                col = 0;
            }
            
            else if (Character.isLetter(c)) {
                pieceMap[index] = c;
                col++;
                //System.out.println(String.format("c: %d, col: %d", c, col));
            }
            
            else if (Character.isDigit(c)) {
                for (int j = 0; j < c-'0'; ++j) {
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
    /*
    @Override
    public String toString() {
    // Need to make a better version of this
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
    } */

    @Override
    public String toString() {
        String boardString = "";
        for (int row = 7; row >= 0; --row) {
            for (int col = 0; col < 8; ++col) {
                boardString += pieceMap[row*8 + col] + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }
    
    
    
    /**
     * Updates FEN, pieceMap, and activePiece based on the move. Does not have to
     *  be a legal move, nor does it have to be a move from activePiece. Supposed
     *  to be used in Board editor mode
     * @param startSquare The starting square of a piece
     * @param endSquare  The ending square of a piece
     */
    public void move(Position startSquare, Position endSquare) {
        
        pieceMap[endSquare.getIndex()] = pieceMap[startSquare.getIndex()];
        pieceMap[startSquare.getIndex()] = '.';
        System.out.println(String.format("startRow: %s, endRow: %s", startSquare.getRow(), endSquare.getRow()));
        updateRow(startSquare.getRow());
        updateRow(endSquare.getRow());

    }
    
    /**
     * 
     * @param row 
     */
    public void updateRow(int row) {
                
        String newRow = "";
        char c;
        int emptySquareCount = 0;
        
        for (int i = 0; i < 8; ++i) {
            c = pieceMap[row*8 + i];
            
            // If square is empty
            if (c == '.') {
                emptySquareCount++;
            }
            
            // If square has a piece
            else {
                if (emptySquareCount > 0) {
                    newRow += emptySquareCount;
                }
                emptySquareCount = 0;
                newRow += c;
            }
        }
        if (emptySquareCount > 0) {
            newRow += emptySquareCount;
        }
        
        // update FEN - probably a better way to do this. This seems inefficient
        
        String[] fenArray = this.fen.split(" "); // split FEN into [board, turn, castling, en passant, halfmove clock, fullmove number)
        String[] fenArrayBoard = fenArray[0].split("/"); // further split boardFEN into rows
        
        //System.out.println(newRow);
        
        // replace the old row with the new row, then join the boardFEN
        fenArrayBoard[7-row] = newRow;
        List<String> fenBoardList = Arrays.asList(fenArrayBoard);
        String fenBoardString = String.join("/", fenBoardList);
        
        // replace the old boardFEN with new boardFEN and then join all FEN strings
        fenArray[0] = fenBoardString;
        List<String> fenList = Arrays.asList(fenArray);
        this.fen = String.join(" ", fenList);
    }

    public String getFen() {
        return fen;
    }
    
    
}