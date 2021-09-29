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
    private char[] charPieceMap = new char[64];
    
    public ChessPiece[] pieceMap = new ChessPiece[64];
    
    boolean activePiece;
    boolean whiteInCheck = false;
    boolean blackInCheck = true;
    
    // WhiteKingSide, WhiteQueenSide, BlackKingSide, BlackQueenSide
    // need to interpret this from FEN
    boolean[] castlingRights = {true, true, true, true};
    
    /**
     * Constructor to set up the chessboard in it's starting position
     */
    public ChessBoard() {
        this.fen = DEFAULT_FEN;
        buildBoard();
    }
    
    /**
     * Constructor to set up the chess board with a custom FEN
     * @param fen the FEN string to set up the board with
     */
    public ChessBoard(String fen) {
        this.fen = fen;
        buildBoard();
    }
    
    /**
     * Sets a custom FEN for the chessboard, then updates the pieceMap[]
     * @param fen the FEN string to set up the board with
     */
    public void setFen(String fen) {
        this.fen = fen;
        buildBoard();
    }
    
    /**
     * Retreive the FEN of the chessboard
     * @return the FEN string 
     */
    public String getFen() {
        return fen;
    }
    
    @Override
    /**
     * Prints chess board in an 8x8 layout.
     */
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
     * Fills pieceMap[] based on current FEN
     */
    private void buildBoard() {
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
                switch (c) {
                    case 'r':
                        pieceMap[index] = new Rook(false, new Position(index));
                        break;
                    case 'R':
                        pieceMap[index] = new Rook(true, new Position(index));
                        break;
                    case 'n':
                        pieceMap[index] = new Knight(false, new Position(index));
                        break;
                    case 'N':
                        pieceMap[index] = new Knight(true, new Position(index));
                        break;
                    case 'b':
                        pieceMap[index] = new Bishop(false, new Position(index));
                        break;
                    case 'B':
                        pieceMap[index] = new Bishop(true, new Position(index));
                        break;
                    case 'q':
                        pieceMap[index] = new Queen(false, new Position(index));
                        break;
                    case 'Q':
                        pieceMap[index] = new Queen(true, new Position(index));
                        break;
                    case 'k':
                        pieceMap[index] = new King(false, new Position(index));
                        break;
                    case 'K':
                        pieceMap[index] = new King(true, new Position(index));
                        break;
                    case 'p':
                        pieceMap[index] = new Pawn(false, new Position(index));
                        break;
                    case 'P':
                        pieceMap[index] = new Pawn(true, new Position(index));
                        break;
                    default:
                        break; 
                }
                charPieceMap[index] = c;
                col++;
            }
            
            else if (Character.isDigit(c)) {
                for (int j = 0; j < c-'0'; ++j) {
                    charPieceMap[index] = '.';
                    col++;
                    index++;
                }
            }
            else {
                System.out.println("something went wrong");
            }
        }
    }
    
    /**
     * Updates FEN, pieceMap, and activePiece based on the move. Does not have to
     *  be a legal move, nor does it have to be a move from activePiece. Supposed
     *  to be used in Board editor mode
     * @param startSquare The starting square of a piece
     * @param endSquare  The ending square of a piece
     */
    public void move(Position startSquare, Position endSquare) {
        
        charPieceMap[endSquare.getIndex()] = charPieceMap[startSquare.getIndex()];
        charPieceMap[startSquare.getIndex()] = '.';
        updateRow(startSquare.getRow());
        updateRow(endSquare.getRow());

    }
    
    /**
     * 
     * @param row 
     */
    private void updateRow(int row) {
                
        String newRow = "";
        char c;
        int emptySquareCount = 0;
        
        for (int i = 0; i < 8; ++i) {
            c = charPieceMap[row*8 + i];
            
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
    
    
}