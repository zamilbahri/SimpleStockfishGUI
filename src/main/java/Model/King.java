/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
/**
 * The King is most important piece in chess. It can only move one space at a time. 
 * Once the king is captured the game is over. If the king is ever about to be captured the game is in a "Check" state.
 * The king is unable to move if it would put itself in danger and other pieces also cannot move if 
 * it would result in the king being in danger
 */
public class King extends ChessPiece {

    public King(boolean isWhite, Position pos) {
        super(isWhite, pos);
        this.type = Type.KING;
    }

    
    public ArrayList<Position> KingMoves(ChessBoard chessBoard){
    	Position[][] newPostion = chessBoard.getSquare();
    	ArrayList<Position> movement = new ArrayList<Position>();
    	Position newSpace = currentSpace;
    	int boardNumWidth = newSpace.getCol();
    	int boardNumHeight = newSpace.getRow();
    	// x axis
    	int newCol = chessBoard.getWidth();
    	// y axis
    	int newRow = chessBoard.getHeight();
    	
    	if((newCol + 1) < boardNumHeight) {
    		movement.add(newPostion[newCol + 1][newRow]);
    	}
		
		if ((newRow + 1) < boardNumHeight) {
			movement.add(newPostion[newCol][newRow + 1]);
			if(newCol - 1 >= 0) {
				movement.add(newPostion[newCol - 1][newRow + 1]);
			}
			if(newCol + 1 < boardNumWidth) {
				movement.add(newPostion[newCol + 1][newRow + 1]);
			}
		}
		
		if ((newRow - 1) >= 0) {
			movement.add(newPostion[newCol][newRow - 1]);
			if(newCol - 1 >= 0) {
				movement.add(newPostion[newCol - 1][newRow - 1]);
			}
			if(newCol + 1 < boardNumWidth) {
				movement.add(newPostion[newCol + 1][newRow - 1]);
			}
		}
			
		if ((newCol - 1) >= 0) {
			movement.add(newPostion[newCol - 1][newRow]);
		}
		return movement;
    }
    
    private static boolean outOfBounds(int a, int b) {
		return a >= 0 && a < 8 && b >= 0 && b < 8;
    }
      
    // Function not final
    // Will change to fit project needs
    private static int isKingSafe(char[][] newBoard) {
    	
    	int i, j;
    	for (i = 0; i < 8; i++) {
    		for (j = 0; j < 8; j++) {
    			if(newBoard[i][j] == 'k') {
    				if(KingPiece(newBoard, 'K', i, j)) {
    					return 1;
    				}
    				if(BishopPiece(newBoard, 'B', i, j)) {
    					return 1;
    				}
    				if(QueenPiece(newBoard, 'Q', i, j)) {
    					return 1;
    				}
    				if(KnightPiece(newBoard, 'N', i, j)) {
    					return 1;
    				}
    				if(PawnPiece(newBoard, 'P', i, j)) {
    					return 1;
    				}
    				if(RookPiece(newBoard, 'R', i, j)) {
    					return 1;
    				}
    			}
    			if(newBoard[i][j] == 'K') {
    				if(KingPiece(newBoard, 'k', i, j)) {
    					return 1;
    				}
    				if(BishopPiece(newBoard, 'b', i, j)) {
    					return 1;
    				}
    				if(QueenPiece(newBoard, 'q', i, j)) {
    					return 1;
    				}
    				if(KnightPiece(newBoard, 'n', i, j)) {
    					return 1;
    				}
    				if(PawnPiece(newBoard, 'p', i, j)) {
    					return 1;
    				}
    				if(RookPiece(newBoard, 'r', i, j)) {
    					return 1;
    				}
    			}
    			
    		}
    	}
    	
		return 0;
    }
    
    private static boolean KingPiece(char[][] setupBoard, char z, int i, int j) {
    	int a, b, c;
    	int[] column = {-1, -1, -1, 0, 0, 1, 1, 1};
    	int[] row = {-1, 0, 1, -1, 1, -1, 0, 1};
    	for (a = 0; a < 8; a++) {
    		b = i + column[a];
    		c = j + row[a];
    		
    		if(outOfBounds(b, c) && setupBoard[b][c] == z) {
    			return true;
    		}
    	}
		return false;
    	
    }
    
    // Still needs work.
    // Set correct values for while loop
    private static boolean BishopPiece(char[][] setupBoard, char a, int i, int j) {
    	int z = 0;
    	while(outOfBounds(i + ++z, j + z)) {
    		if (setupBoard[i + z][j + z] == a) {
    			return true;
    		}
    	}
    	while(outOfBounds(i + ++z, j + z)) {
    		if (setupBoard[i + z][j + z] == a) {
    			return true;
    		}
    	}	
    	while(outOfBounds(i - --z, j -z)) {
    		if (setupBoard[i - z][j - z] == a) {
    			return true;
    		}
    	}
    	while(outOfBounds(i - --z, j - z)) {
    		if (setupBoard[i - z][j - z] == a) {
    			return true;
    		}
    	}
    	
		return false;
    	
    }
    private static boolean QueenPiece(char[][] setupBoard, char a, int i, int j) {
    	if (BishopPiece(setupBoard, a, i, j) || RookPiece(setupBoard, a, i, j)) {
    		return true;
    	}
		return false;
    	
    }
    private static boolean KnightPiece(char[][] setupBoard, char z, int i, int j) {
    	int a, b, c;
    	int column[] = {2, 2, -2, -2, 1, 1, -1, 1};
    	int row[] = {1, -1, 1, -1, 2, -2, 2, -2};
    	
    	for (a = 0; a < 8; a++) {
    		b = i + column[a];
    		c = j + row[a];
    		
    		if(outOfBounds(b, c)) {
    			return true;
    		}
    	}
    	
		return false;
    }
	private static boolean PawnPiece(char[][] setupBoard, char z, int i, int j) {
    	
		if (outOfBounds(i - 1, j - 1)
                && setupBoard[i - 1][j - 1] == z)
                return true;
            if (outOfBounds(i - 1, j + 1)
                && setupBoard[i - 1][j + 1] == z)
                return true;
    	return false;
    }
    private static boolean RookPiece(char[][] setupBoard, char z, int i, int j) {
    	
    	int a = 0;
    	
    	while(outOfBounds(i + ++a, j)) {
    		if(setupBoard[i + a][j] == z) {
    			return true;
    		}
    	}
    	
    	a = 0;
    	while(outOfBounds(i + --a, j)) {
    		if(setupBoard[i + a][j] == z) {
    			return true;
    		}
      	}
    	
    	a = 0;
    	while(outOfBounds(i, j + ++a)) {
    		if(setupBoard[i][j + a] == z) {
    			return true;
    		}
    	}
    	
    	a = 0;
    	while(outOfBounds(i, j + --a)) {
    		if(setupBoard[i][j + a] == z) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    @Override
    // Still need to work on
    public boolean isValidPath(Position targetPos, ChessBoard newBoard) {
    	if(targetPos == currentSpace) {
    		return false;
    	}
    	
    	boolean new_move = true;
    	ChessPiece target = targetPos.getNewPiece();
    	if (target != null)
    		return false;
    	
		return false;
    }

    @Override
    public Position[] generatePseudoLegalMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Type getType() {
        return super.getType();
    }

    @Override
    public boolean isWhite() {
        return super.isWhite();
    }
    
    
    
}
