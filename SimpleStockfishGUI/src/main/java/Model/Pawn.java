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
//next step - double check all directions as intended
/**
 *
 * @author zamil, Phoebe
 */
public class Pawn extends ChessPiece {
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
	public boolean isValidPath(Position targetPos) { // all posbblie moves in paw.n //chess board calls to see illagel
														// ones after
		// throw new UnsupportedOperationException("Not supported yet.");

		boolean isValid = false;
		setIsAttacking(false);
		int[] forward = { 0, 0 }, diag1 = { 0, 0 }, diag2 = { 0, 0 };// forwrd, forward left, forward right. From pieces
																		// pov
		// [x,y]
		int currentX = 0, currentY = 0, targetX = 0, targetY = 0;
		currentX = this.pos.getRow(); // or swithc around
		currentY = this.pos.getCol();
		targetX = targetPos.getRow();
		targetY = targetPos.getCol();

		// define â€œforwardâ€�//up for white. or down for black //calcuatel end positon
		if (this.isWhite()) {

			forward[0] = currentX;
			forward[1] = currentY - targetY; // forward= [currentX-targetX,currentY];

			diag1[0] = currentX - targetX + 1;
			diag1[1] = currentY - targetY;

			diag2[0] = currentX - targetX - 1;
			diag2[1] = currentY - targetY;

			// forward = [this.pos[y coord]-targetPos[y coord], this.pos[xcoord] ];//dist of
			// this.pos to targetPos //y corrd is pos[1]??
			// diag1 = [this.pos[y coord]-targetPos[y coord], this.pos[x coord]-targetPos[x
			// coord] + 1] ;
			// diag2 = [this.pos[y coord]-targetPos[y coord], this.pos[x coord]-targetPos[x
			// coord]-1] ;
		} else if (!this.isWhite()) {

			forward[0] = currentX;
			forward[1] = targetY - currentY;

			diag1[0] = targetX - currentX + 1;
			diag1[1] = targetY - currentY;

			diag2[0] = targetX - currentX - 1;
			diag2[1] = targetY - currentY;

			// forward = [targetPos[y coord]- this.pos[y coord],this.pos[x coord] ];//dist
			// of this.pos to targetPos
			// diag1 = [targetPos[y coord]- this.pos[y coord], targetPos[x coord]-
			// this.pos[x coord] +1];
			// diag2 = [targetPos[y coord]- this.pos[y coord], targetPos[x coord]-
			// this.pos[x coord] -1];

		}

		// =========================================
		// check invalid-- in chess board??
		// dont need add current? or change in bisoph and queen

		if (forward[0] + currentX > BOARD_MAX || diag1[0] + currentX > BOARD_MAX || diag2[0] + currentX > BOARD_MAX
				|| forward[1] + currentY > BOARD_MAX || diag1[1] + currentY > BOARD_MAX
				|| diag2[1] + currentY > BOARD_MAX || forward[0] + currentX < BOARD_MIN
				|| diag1[0] + currentX < BOARD_MIN || diag2[0] + currentX < BOARD_MIN
				|| forward[1] + currentY < BOARD_MIN || diag1[1] + currentY < BOARD_MIN
				|| diag2[1] + currentY < BOARD_MIN)

		{ // if forard.y coord + current position.ycoord > bord len
			return false;
		}

		// ===============================================

		// Can move 1 "forward"

		if (forward[1] == 1) {// (forward == [0,1]){
			isValid = true;
		}

		// If this is its first move (first move = true).//Can move 2.
		if (isFirstMove == true && forward[1] == 2) {
			isValid = true;
		}

		// asume diagonal forward is valid. later prove not valid if no peice i sthere
		if ((diag1[0] == 1 && diag1[1] == 1) || (diag2[0] == 1 && diag2[1] == 1)) {
			isValid = true;
		}

		// Check enpassant //do enpassent in board
		// can only do when at row 5. assume other peice moved in valid area
		if (currentY == 5) {
			isValid = true;
			setCanEnPassant(true);
		}

		// If at end of board( y position = 8 )
		// Turn this piece into queen ( knight or rook etc)- can choose //do the change
		// in chess board

		if (isValid == true && forward[1] + currentY == BOARD_MAX) { // should be just forward? not currenty?
			setCanChange(true);
		}

		return isValid;
	}

	@Override
	public Position[] generatePseudoLegalMoves() { // can move this to chess BOard? since will be same in rest
		// throw new UnsupportedOperationException("Not supported yet.");

		Position[] positions = {};// ArrayList<Position> positions = new ArrayList<Position>();
		int counter = 0;

		for (int i = 0; i < BOARD_MAX; i++) {
			for (int j = 0; j < BOARD_MAX; j++) {

				Position checkPosition = new Position(i, j);
				if (isValidPath(checkPosition)) {

					positions[counter] = checkPosition;// positions.add(checkPosition);
					counter++;
				}
			}
		}

		return positions;

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
