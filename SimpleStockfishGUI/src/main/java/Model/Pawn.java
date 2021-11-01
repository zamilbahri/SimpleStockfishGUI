/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/*
 * trye commit only changed files? 
 * commit message:  
 * chess peice- added constants boardmax/board min - dimension of board

pawn - updated. in progress.
bishop - updating... 
chessboard - added psduo code

note:public Position[] generatePseudoLegalMoves() -will prob be same in each indiv piece class?
-------------------------
commit 2: 

bishop- more
queen - started
pawn- small change
chessboard- small change to psduo code

 */

/*
 * commit 3:
 * changes:
 * pawn queen bishop- all movements fixed--assuming no peicies in way. not include enpassent- all generatepsudo moves work - wont use isvalid() 
 *  tester funcs added--all test cases work -(not added to chessboard controller)
 * 
 * public boolean equals(Object obj) { // ADDED  in Position
 * public boolean isDuplicate(Position[] positions, int counter, Position targetPos) { () added to bishop,queen,pawn
 * 

 */

//next step - double check all directions as intended-it is. practice has test cases working

/**
 *
 * @author zamil, Phoebe
 */
class Pawn extends ChessPiece {
	private boolean isFirstMove, isAttacking, canChange, canEnPassant;// is atack /can attack

	public Pawn(boolean isWhite, Position pos) {
		super(isWhite, pos);
		this.type = Type.PAWN;
		isFirstMove = true;
		isAttacking = false;
		canChange = false;
		canEnPassant = false;
	}

	@Override
	public boolean isValidPath(Position targetPos) {
		return true;
	}
	/*
	 * 
	 * 
	 * 
	 * // Check enpassant //do enpassent in board // can only do when at row 5.
	 * assume other peice moved in valid area if (currentY == 5) { isValid = true;
	 * setCanEnPassant(true); }
	 * 
	 * // If at end of board( y position = 8 ) // Turn this piece into queen (
	 * knight or rook etc)- can choose //do the change // in chess board
	 * 
	 * }
	 */

	@Override
	public Position[] generatePseudoLegalMoves() {

		int counter = 0;// current size
		Position positions[] = new Position[100];
		Position targetPos = new Position(0, 0);

		int currentCol = 0, currentRow = 0, targetCol = 0, targetRow = 0;
		currentCol = this.pos.getCol();
		currentRow = this.pos.getRow();

		// define forward//up for white. or down for black //calcuatel end positon
		// //asume Can move 2 forards
		if (this.isWhite()) {// WHITE: UP //cols same, rows change

			for (int i = 0; i <= 2; i++) {
				targetCol = currentCol;
				targetRow = currentRow + i;

				if (targetRow <= BOARD_MAX) {// if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
					targetPos = new Position(targetCol, targetRow);

					if (!isDuplicate(positions, counter, targetPos)) {
						positions[counter] = targetPos;
						counter++;
					}
				}
				// if (targetCol == BOARD_MAX || targetRow == BOARD_MAX) break;
			}

		} else if (!this.isWhite()) {// BALCK: DOWN
			for (int i = 0; i <= 2; i++) {
				targetCol = currentCol;
				targetRow = currentRow - i;

				if (targetRow >= BOARD_MIN) {// if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
					targetPos = new Position(targetCol, targetRow);

					if (!isDuplicate(positions, counter, targetPos)) {
						positions[counter] = targetPos;
						counter++;
					}
				}
				// if (targetCol == BOARD_MAX || targetRow == BOARD_MAX) break;
			}

		}

		// asume diagonal forward is valid. later prove not valid if no peice i sthere

		// can move diagonaly . 4 ways //depends on color
		// for 2 possible squares...// check if reach end of board and if target
		// alreadly in list

		if (this.isWhite()) {

			for (int i = 0; i <= 1; i++) {// up right //cols grow, rows grow
				targetCol = currentCol + i;
				targetRow = currentRow + i;

				if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
					targetPos = new Position(targetCol, targetRow);

					if (!isDuplicate(positions, counter, targetPos)) {
						positions[counter] = targetPos;// positions.append(targetCol,targetRow);
						counter++;
					}
				}
				// if (targetCol == BOARD_MAX || targetRow == BOARD_MAX)break;
			}

			for (int i = 0; i <= 1; i++) {// up left //cols shrink, rows grow
				targetCol = currentCol - i;
				targetRow = currentRow + i;

				if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
					targetPos = new Position(targetCol, targetRow);

					if (!isDuplicate(positions, counter, targetPos)) {
						positions[counter] = targetPos;
						counter++;
					}
				}
				// if (targetCol == BOARD_MIN || targetRow == BOARD_MAX) break;
			}

		} else if (!this.isWhite()) {

			for (int i = 0; i <= 1; i++) { // down left //cols shrink, rows shrink
				targetCol = currentCol - i;
				targetRow = currentRow - i;

				if (targetCol >= BOARD_MIN && targetRow >= BOARD_MIN) {
					targetPos = new Position(targetCol, targetRow);

					if (!isDuplicate(positions, counter, targetPos)) {
						positions[counter] = targetPos;
						counter++;
					}
				}
				// if (targetCol == BOARD_MIN || targetRow == BOARD_MIN) break;
			}

			for (int i = 0; i <= 1; i++) {// down right //cols grow, rows shrink
				targetCol = currentCol + i;
				targetRow = currentRow - i;

				if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
					targetPos = new Position(targetCol, targetRow);

					if (!isDuplicate(positions, counter, targetPos)) {
						positions[counter] = targetPos;
						counter++;
					}
				}
				// if (targetCol == BOARD_MAX || targetRow == BOARD_MIN)break;
			}

		}

		// copy array of correct size
		Position positionsAns[] = new Position[counter]; // current array size used up
		for (int i = 0; i < counter; i++) {
			positionsAns[i] = positions[i];
		}

		return positionsAns;

	}

	public boolean isDuplicate(Position[] positions, int counter, Position targetPos) { // added

		if (counter == 0)
			return false;

		for (int i = 0; i < counter; i++) {
			if (positions[i].equals(targetPos))
				return true;
		}
		return false;
	}

	@Override
	public Type getType() {
		return super.getType();
	}

	@Override
	public boolean isWhite() {
		return super.isWhite();
	}

	/*
	 * //do this in chess board
	 * 
	 * 
	 * public void changePawn() { if (canChange == true) // public
	 * ArrayList<ChessPiece> pieces = new ArrayList<>(); ;
	 * 
	 * }
	 */

	public void setIsFirstMove(boolean set) { // after first move. its false //or can check start postion is not current
												// postion
		isFirstMove = set;
	}

	public boolean getIsFirstMove() {
		return isFirstMove;
	}

	public void setIsAttacking(boolean set) { // after first move. its false //or can check start postion is not current
												// postion
		isAttacking = set;
	}

	public boolean getIsAttacking() {
		return isAttacking;
	}

	public void setCanChange(boolean set) {
		canChange = set;
	}

	public boolean getCanChange(boolean set) {
		return canChange;
	}

	public void setCanEnPassant(boolean set) {
		canEnPassant = set;
	}

	public boolean getCanEnPassant(boolean set) {
		return canEnPassant;
	}
}
