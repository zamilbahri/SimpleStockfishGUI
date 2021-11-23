/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author zamil, Phoebe
 */
class Queen extends ChessPiece {

	public Queen(boolean isWhite, Position pos) {
		super(isWhite, pos);
		this.type = Type.QUEEN;
	}

	@Override
	public boolean isValidPath(Position targetPos) { // unused
		return true;
	}

	@Override
	public Position[] generatePseudoLegalMoves() { // unused
		Position[] p = { new Position(-99, -99) };
		return p;
	}

	/**
	 * 
	 * @param BOARD_MAX
	 *            edge of chessboard
	 * @param BOARD_MIN
	 *            edge of chessboard
	 * @param moves
	 *            array of currently legal moves of all pieces on a chess board
	 * @param currentPiece
	 *            the current chess piece having its legal moves checked.
	 * @param anotherPiece
	 *            another chess piece on the board
	 * @param currentStartSquare
	 *            a starting position on the chess board
	 * @param currentTargetSquare
	 *            a ending position on the chess board to check for
	 * @param counter
	 *            current size of positions array
	 * @param counterTemp
	 *            temporary current size of positions array
	 * @param positions
	 *            array of legal positions for current piece so far
	 * @param targetPos
	 *            where the current piece is trying to land.
	 * @param currentCol
	 *            a starting column on the chess board
	 * @param currentRow
	 *            a starting row on the chess board
	 * @param targetCol
	 *            a ending column on the chess board to check for
	 * @param targetRow
	 *            a ending row on the chess board to check for
	 * @param movesCounter
	 *            number of legal moves so far
	 * @param pieces
	 *            arraylist of all pieces on the chess board
	 * @return positionsAns array of legal (end) positions for current piece.
	 */

	public Position[] generatePseudoLegalMovesBishop(int BOARD_MAX, int BOARD_MIN, Move[] moves,
			ChessPiece currentPiece, ChessPiece anotherPiece, Position currentStartSquare, Position currentTargetSquare,
			int counter, int counterTemp, Position[] positions, Position targetPos, int currentCol, int currentRow,
			int targetCol, int targetRow, int movesCounter, ArrayList<ChessPiece> pieces) {
		// FIND QUEEN LEGAL MOVES ===========================================

		counter = 0;// current size

		positions = new Position[100];
		targetPos = new Position(-1, -1);

		currentCol = currentStartSquare.getCol();
		currentRow = currentStartSquare.getRow();

		// daigs(same as bishop) ==========================================

		// up right //cols grow, rows grow ===========================
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol + i;
			targetRow = currentRow + i;

			if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);

				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction

					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}

				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
					// add to end positions
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MAX || targetRow == BOARD_MAX)
				break;
		}

		// down left //cols shrink, rows shrink ===========================
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol - i;
			targetRow = currentRow - i;

			if (targetCol >= BOARD_MIN && targetRow >= BOARD_MIN) {
				targetPos = new Position(targetCol, targetRow);

				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction
					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}
				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MIN || targetRow == BOARD_MIN)
				break;
		}

		// up left //cols shrink, rows grow ===========================
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol - i;
			targetRow = currentRow + i;

			if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);
				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction
					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}
				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {

					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MIN || targetRow == BOARD_MAX)
				break;
		}

		// down right //cols grow, rows shrink ===========================
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol + i;
			targetRow = currentRow - i;

			if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);
				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction
					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}
				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MAX || targetRow == BOARD_MIN)
				break;
		}

		// horiz/vertical moves ========================

		// right //cols change, rows same
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol + i;
			targetRow = currentRow;// + i;

			if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);
				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction
					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}
				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MAX || targetRow == BOARD_MAX)
				break;
		}

		// left //cols change, rows same
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol - i;
			targetRow = currentRow; // - i;

			if (targetCol >= BOARD_MIN && targetRow >= BOARD_MIN) {
				targetPos = new Position(targetCol, targetRow);

				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction
					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}
				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MIN || targetRow == BOARD_MIN)
				break;
		}

		// up //cols same, rows change
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol; // - i;
			targetRow = currentRow + i;

			if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);

				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction
					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}
				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MIN || targetRow == BOARD_MAX)
				break;
		}

		// down //cols same, rows change
		for (int i = 0; i <= BOARD_MAX; i++) {
			targetCol = currentCol; // + i;
			targetRow = currentRow - i;

			if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
				targetPos = new Position(targetCol, targetRow);
				if (isBlocking(targetPos, currentPiece, pieces)) {// leave direction
					anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
					counterTemp = counter;
					counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp, pieces);

					break;
				}
				if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
					positions[counter] = targetPos;
					counter++;
				}
			}
			if (targetCol == BOARD_MAX || targetRow == BOARD_MIN)
				break;
		}

		// copy array of correct size
		Position positionsAns[] = new Position[counter];
		for (int i = 0; i < counter; i++) {
			positionsAns[i] = positions[i];

		}

		return positionsAns;

	}

	/**
	 * 
	 * @param positions
	 *            array of legal positions for current piece so far
	 * @param counter
	 *            current size of positions array
	 * @param targetPos
	 *            where the current piece is trying to land
	 * @return true if targetPos is already in positions list. false otherwise.
	 */
	public boolean isDuplicate(Position[] positions, int counter, Position targetPos) {
		if (counter == 0)
			return false;

		for (int i = 0; i < counter; i++) {
			if (positions[i].equals(targetPos))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param targetPos
	 *            where the current piece is trying to land.
	 * @param currentPiece
	 *            the current chess piece having its legal moves checked.
	 * @return true if another chess piece is in the way of of the current direction
	 *         (eg left). false otherwise.
	 */
	public boolean isBlocking(Position targetPos, ChessPiece currentPiece, ArrayList<ChessPiece> pieces) {
		if (pieces.size() == 0)
			return false;

		// block if: target postion is another peices starting postion
		// another piece in way but is not same piece

		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getPosition().equals(targetPos) && !pieces.get(i).equals(currentPiece))
				return true;
		}
		return false;

	}

	/**
	 * 
	 * @param anotherPiece
	 *            another chess piece on the board
	 * @param targetPos
	 *            where the current piece is trying to land.
	 * @param currentPiece
	 *            the current chess piece having its legal moves checked.
	 * @param positions
	 *            array of legal positions for current piece so far
	 * 
	 * @param counter
	 *            current size of positions array
	 * @return newCounter new size of positions array
	 */
	public int checkCanAttack(ChessPiece anotherPiece, Position targetPos, ChessPiece currentPiece,
			Position[] positions, int counter, ArrayList<ChessPiece> pieces) {

		int newCounter = 0;

		for (int m = 0; m < pieces.size(); m++) {// search for this other peice
			if (pieces.get(m).getPosition().equals(targetPos)) {
				anotherPiece = pieces.get(m);
			}
		}

		// can attack: if currentPiece is blocked by anotherPiece and colours are dif
		if ((!anotherPiece.isWhite() && currentPiece.isWhite())
				|| (anotherPiece.isWhite() && !currentPiece.isWhite())) { // if dif colors
			if (isBlocking(targetPos, currentPiece, pieces) && !isDuplicate(positions, counter, targetPos)
					&& !(isCheck() || isCheckMate())) {// if blocked - add to end postions
				positions[counter] = targetPos;
				counter++;
			}
		}

		newCounter = counter;
		return newCounter;
	}

	/**
	 * check if your move puts you in check
	 * 
	 * @return true if its invalid move. false otherwise.
	 */
	public boolean isCheck() {
		return false;
	}

	/**
	 * check if your move puts you in checkMate
	 * 
	 * @return true if its invalid move. false otherwise.
	 */
	public boolean isCheckMate() {
		return false;
	}

	/**
	 * 
	 * @returns the type of the Piece ( Queen)
	 */
	@Override
	public Type getType() {
		return super.getType();
	}

	/**
	 * A function that indicates the colour of this piece
	 * 
	 * @return true if white, false is black
	 */
	@Override
	public boolean isWhite() {
		return super.isWhite();
	}

	/**
	 * 
	 * @returns the start position of the Piece ( Queen)
	 */
	@Override
	public Position getPosition() {
		return super.getPosition();
	}
}
