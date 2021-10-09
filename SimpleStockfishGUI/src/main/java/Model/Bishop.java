/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author zamil, Phoebe
 */
public class Bishop extends ChessPiece {
	private boolean isAttacking;

	public Bishop(boolean isWhite, Position pos) {
		super(isWhite, pos);
		this.type = Type.BISHOP;
		isAttacking = false;
	}

	@Override
	public boolean isValidPath(Position targetPos) { // ignoreing other peices on boards
		// throw new UnsupportedOperationException("Not supported yet.");

		boolean isValid = false;
		setIsAttacking(false);
		int[] diag1 = { 0, 0 }, diag2 = { 0, 0 }, diag3 = { 0, 0 }, diag4 = { 0, 0 };// x,y

		int currentX = 0, currentY = 0, targetX = 0, targetY = 0;
		currentX = this.pos.getRow();
		currentY = this.pos.getCol();
		targetX = targetPos.getRow();
		targetY = targetPos.getCol();

		// can only move diagonaly . 4 ways
		diag1[0] = currentX - targetX + 1;
		diag1[1] = currentY - targetY;

		diag2[0] = currentX - targetX - 1;
		diag2[1] = currentY - targetY;

		diag3[0] = targetX - currentX + 1;
		diag3[1] = targetY - currentY;

		diag4[0] = targetX - currentX - 1;
		diag4[1] = targetY - currentY;

		/*
		 * if (BOARD_MIN < diag1[0] && diag1[0] < BOARD_MAX && BOARD_MIN < diag1[1] &&
		 * diag1[1] < BOARD_MAX && BOARD_MIN < diag2[0] && diag2[0] < BOARD_MAX &&
		 * BOARD_MIN < diag2[1] && diag2[1] < BOARD_MAX && BOARD_MIN < diag3[0] &&
		 * diag3[0] < BOARD_MAX && BOARD_MIN < diag3[1] && diag3[1] < BOARD_MAX &&
		 * BOARD_MIN < diag4[0] && diag4[0] < BOARD_MAX && BOARD_MIN < diag4[1] &&
		 * diag4[1] < BOARD_MAX) { // of // each // diagonal
		 * 
		 * isValid = true;
		 * 
		 * }
		 */
		if (checkValid(diag1) && checkValid(diag2) && checkValid(diag3) && checkValid(diag4)) {
			isValid = true;
		}
		return isValid;
	}

	@Override
	public Position[] generatePseudoLegalMoves() {
		// throw new UnsupportedOperationException("Not supported yet.");

		Position[] positions = {};
		int counter = 0;

		for (int i = 0; i < BOARD_MAX; i++) {
			for (int j = 0; j < BOARD_MAX; j++) {

				Position checkPosition = new Position(i, j);
				if (isValidPath(checkPosition)) {

					positions[counter] = checkPosition;
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

	public void setIsAttacking(boolean set) { // after first move. its false //or can check start postion is not current
												// postion
		isAttacking = set;
	}

	public boolean getIsAttacking() {
		return isAttacking;
	}

	private boolean checkValid(int[] list) { // helper

		if (BOARD_MIN < list[0] && list[0] < BOARD_MAX && BOARD_MIN < list[1] && list[1] < BOARD_MAX) {
			return true;

		}
		return false;

	}
}

// ===============
/*
 * if( BOARD_MIN <diag1[0] &&diag1[0]<BOARD_MAX &&BOARD_MIN <diag1[1]
 * &&diag1[1]<BOARD_MAX &&BOARD_MIN <diag2[0] &&diag2[0]<BOARD_MAX &&BOARD_MIN
 * <diag2[1] &&diag2[1]<BOARD_MAX)
 * 
 * { //valid range
 * 
 * 
 * isValid = true; }
 * 
 * if(BOARD_MIN<diag1[0] +BOARD_MAX && diag1[0]-BOARD_MAX<BOARD_MAX &&
 * BOARD_MIN<diag1[0] +BOARD_MAX && diag1[0]-BOARD_MAX<BOARD_MAX) //valid range-
 * opposite direction?? //need do // 2??
 */