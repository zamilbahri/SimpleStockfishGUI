package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
/**
 *
 * @author zamil, Phoebe
 */
class Bishop extends ChessPiece {
	private boolean isAttacking;

	public Bishop(boolean isWhite, Position pos) {
		super(isWhite, pos);
		this.type = Type.BISHOP;
		isAttacking = false;
	}

	public boolean isValidPath(Position targetPos) { // works:up left.//not work: up right
		return true;
	}

	@Override
	public Position[] generatePseudoLegalMoves() { // wihtout using isvalid //asumes peicies not in way

		int counter = 0;// current size
		Position positions[] = new Position[100];
		Position targetPos = new Position(0, 0);

		int currentCol = 0, currentRow = 0, targetCol = 0, targetRow = 0;
		currentCol = this.pos.getCol();
		currentRow = this.pos.getRow();
		// targetCol = targetPos.getCol();
		// targetRow = targetPos.getRow();

		// can only move diagonaly . 4 ways
		// for 7 possible squares...// check if reach end of board and if target
		// alreadly in list
		for (int i = 0; i <= BOARD_MAX; i++) {// up right //cols grow, rows grow
			targetCol = currentCol + i;
			targetRow = currentRow + i;

			if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);

				if (!isDuplicate(positions, counter, targetPos)) {
					positions[counter] = targetPos;// positions.append(targetCol,targetRow);
					counter++;
				}
			}
			if (targetCol == BOARD_MAX || targetRow == BOARD_MAX)
				break;
		}

		for (int i = 0; i <= BOARD_MAX; i++) { // down left //cols shrink, rows shrink
			targetCol = currentCol - i;
			targetRow = currentRow - i;

			if (targetCol >= BOARD_MIN && targetRow >= BOARD_MIN) {
				targetPos = new Position(targetCol, targetRow);

				if (!isDuplicate(positions, counter, targetPos)) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MIN || targetRow == BOARD_MIN)
				break;
		}

		for (int i = 0; i <= BOARD_MAX; i++) {// up left //cols shrink, rows grow
			targetCol = currentCol - i;
			targetRow = currentRow + i;

			if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);

				if (!isDuplicate(positions, counter, targetPos)) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MIN || targetRow == BOARD_MAX)
				break;
		}

		for (int i = 0; i <= BOARD_MAX; i++) {// down right //cols grow, rows shrink
			targetCol = currentCol + i;
			targetRow = currentRow - i;

			if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);

				if (!isDuplicate(positions, counter, targetPos)) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MAX || targetRow == BOARD_MIN)
				break;
		}

		// copy array of correct size
		Position positionsAns[] = new Position[counter]; // current array size used up
		for (int i = 0; i < counter; i++) {
			positionsAns[i] = positions[i];
		}

		return positionsAns;

	}

	public boolean isDuplicate(Position[] positions, int counter, Position targetPos) { // added

		if (counter == 0) // if (positions.length==0) //length is tot len availble
			return false;

		for (int i = 0; i < counter; i++) {// for (Position pos: positions) {
			if (positions[i].equals(targetPos))// if (pos.equals(targetPos))
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

	public void setIsAttacking(boolean set) { /// can check start postion is not current postion ?
		isAttacking = set;
	}

	public boolean getIsAttacking() {
		return isAttacking;
	}

	private boolean checkValid(int[] list) { // helper--unused
		// if crreunt postion[0] + list[0] out of range...
		if (BOARD_MIN < list[0] && list[0] < BOARD_MAX && BOARD_MIN < list[1] && list[1] < BOARD_MAX) {
			return true;

		}
		return false;

	}

}
