/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author zamil, Phoebe
 */
public class ChessBoard {

	private final static String DEFAULT_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private String fen; // = r3k2r/pp1b1ppp/1qnbpn2/2ppN3/3P1B2/1QPBP3/PP1N1PPP/R4RK1 w kq - 0 1
	public char[] pieceMap = new char[64];
	public ArrayList<ChessPiece> pieces = new ArrayList<>(); // this is all the pices current positions -

	boolean turn; // indicates whose turn it is - white = 0, black = 1

	/**
	 * TODO: interpret castling rights from FEN interpret turn from FEN
	 */

	/**
	 * Constructor to set up the chessboard in it's starting position
	 */
	public ChessBoard() {
		this.fen = DEFAULT_FEN;
		buildBoard();
	}

	/**
	 * Constructor to set up the chess board with a custom FEN
	 * 
	 * @param fen
	 *            the FEN string to set up the board with
	 */
	public ChessBoard(String fen) {
		this.fen = fen;
		buildBoard();
	}

	/**
	 * Sets a custom FEN for the chessboard, then updates the pieceMap[]
	 * 
	 * @param fen
	 *            the FEN string to set up the board with
	 */
	public void setFen(String fen) {
		this.fen = fen;
		buildBoard();
	}

	/**
	 * Retrieve the FEN of the chessboard
	 * 
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
				boardString += pieceMap[row * 8 + col] + " ";
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

		int row = 7;
		int col = 0;
		int fen_len = fenBoard.length();
		int index;
		char c;

		for (int i = 0; i < fen_len; ++i) {
			c = fenBoard.charAt(i);
			index = row * 8 + col;

			if (c == '/') {
				row--;
				col = 0;
			}

			else if (Character.isLetter(c)) {
				switch (c) {
				case 'r':
				case 'R':
					pieces.add(new Rook(Character.isUpperCase(c), new Position(index)));
					break;
				case 'b':
				case 'B':
					pieces.add(new Bishop(Character.isUpperCase(c), new Position(index)));
					break;
				case 'n':
				case 'N':
					pieces.add(new Knight(Character.isUpperCase(c), new Position(index)));
					break;
				case 'q':
				case 'Q':
					pieces.add(new Queen(Character.isUpperCase(c), new Position(index)));
					break;
				case 'k':
				case 'K':
					pieces.add(new King(Character.isUpperCase(c), new Position(index)));
					break;
				case 'p':
				case 'P':
					pieces.add(new Pawn(Character.isUpperCase(c), new Position(index)));
					break;
				default:

					break;
				}
				pieceMap[index] = c;
				col++;
			}

			else if (Character.isDigit(c)) {
				for (int j = 0; j < c - '0'; ++j) {
					pieceMap[index] = '.';
					col++;
					index++;
				}
			} else {
				System.out.println("something went wrong");
			}
		}
	}

	/**
	 * Updates FEN, pieceMap, and activePiece based on the move. Does not have to be
	 * a legal move, nor does it have to be a move from activePiece. Supposed to be
	 * used in Board editor mode
	 * 
	 * @param startSquare
	 *            The starting square of the move
	 * @param targetSquare
	 *            The target square of the move
	 */
	public void move(Position startSquare, Position targetSquare) {
		// TODO: change pieces[]
		Iterator<ChessPiece> iter = pieces.iterator();

		pieceMap[targetSquare.getIndex()] = pieceMap[startSquare.getIndex()];
		pieceMap[startSquare.getIndex()] = '.';
		updateRow(startSquare.getRow());
		updateRow(targetSquare.getRow());

		// added:-unested
		// once a move is done: delete attacked piece from pieces array
		// change pawn if need be.

		ChessPiece currentPiece;
		ChessPiece anotherPiece;// attacekd piece

		Position currentStartSquare, anotherStartSquare;
		String currentPieceType;

		Position[] END_POSITIONS = { new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(3, 0),
				new Position(4, 0), new Position(5, 0), new Position(6, 0), new Position(7, 0), new Position(0, 7),
				new Position(1, 7), new Position(2, 7), new Position(3, 7), new Position(4, 7), new Position(5, 7),
				new Position(6, 7), new Position(7, 7) };
		// pawn end positions-if land on it-will become a queen

		for (int num1 = 0; num1 < pieces.size(); num1++) { // for all peices

			// current peice
			currentPiece = pieces.get(num1);
			currentStartSquare = currentPiece.getPosition();
			currentPieceType = currentPiece.getType().toString();

			if (currentPieceType.equals("PAWN")
					&& isDuplicate(END_POSITIONS, END_POSITIONS.length, currentPiece.getPosition())) {
				// if pawn at end of board
				// the actual change pawn into queen --only after actual move
				currentPiece = new Queen(currentPiece.isWhite(), currentPiece.getPosition());

			} else {

				for (int num2 = 0; num2 < pieces.size(); num2++) { // for all peices
					anotherPiece = pieces.get(num2);

					anotherStartSquare = currentPiece.getPosition();

					if (currentStartSquare.equals(anotherStartSquare) && !currentPiece.equals(anotherPiece)) {
						// if 2 peices have same start position: remove attacked peice

						pieces.remove(anotherPiece);// or use peices.get(i) = NONE peice?

					}
				}

			}
		}

	}

	/**
	 * check which side is currently in check - unimplemented/untested
	 * 
	 * @param anotherPiece
	 *            another chess piece
	 * @return colorInCheck which team is currently in check. 1 = white in check. 2
	 *         = black in check . 0 = none in check
	 */
	public int makeCheck(ChessPiece anotherPiece) { // find which color is in check
		// check if you in check cuz of other color move?->(outside of this func) this
		// team must then move out of check from start postion . (must call this for end
		// positon and make 0 or toher team )

		// maybe shoudl check in move instead? after a move is done can check whena team
		// is in check...--that way can get end postions

		Position endPosition, wKingPosition, bKingPosition;

		int colorInCheck = 0; // 1 = white in check. 2 = balck in check . 0 - none in check

		endPosition = anotherPiece.getPosition(); // this is start ppos should be where end pos..// wherre antoehr
													// peices can end its turn = end positon;

		wKingPosition = new Position(-1, -1); // or use king start position
		bKingPosition = new Position(-1, -1);

		for (int i = 0; i < pieces.size(); i++) { // find kings

			if (pieces.get(i).getType().toString().equals("KING") && pieces.get(i).isWhite()) {
				wKingPosition = pieces.get(i).getPosition(); // found white king
			}
			if (pieces.get(i).getType().toString().equals("KING") && !pieces.get(i).isWhite()) {
				bKingPosition = pieces.get(i).getPosition(); // found black king
			}

		}

		// another(attacking) peice is opp color and end postion = king positon
		// if peice can land on king squre->other color is in check

		if (!anotherPiece.isWhite() && endPosition.equals(wKingPosition)) {
			colorInCheck = 1; // balck can land on white king
		} else if (anotherPiece.isWhite() && endPosition.equals(bKingPosition)) {
			colorInCheck = 2; // white can land on balck king

		}
		return colorInCheck;
	}

	/**
	 * check if moving the current piece would put its own team in check -
	 * unimplemented
	 * 
	 * @param currentpiece
	 *            the current chess piece having its legal moves checked.
	 * @param anotherPiece
	 *            another chess piece on the board
	 * 
	 * 
	 * 
	 * @return true if your move makes yourself in check. false otherwise.
	 */
	public boolean isCheck(ChessPiece currentpiece, ChessPiece anotherPiece) { // dummy funcs
		// check if your move makes yourself in check( your king atackatble) - cant do -

		// where another peice is...opp color? and all possible moves of opp team after
		// you fake move? //call gernate legal mvoes again? where peices array shows
		// the fake move done?--need revert after..

		// if you white and make white in check after moving->true (cant do it)
		// if your coloru = white and your fake move -> make check = 1: cant do
		// it(return true;)
		// if you corlr = balck and make check = 2: cant do it

		if (currentpiece.isWhite() && makeCheck(anotherPiece) == 1
				|| (!currentpiece.isWhite() && makeCheck(anotherPiece) == 2))
			return true;

		return false;
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
	 * @return A list of strings(?) containing the moves
	 */

	public Move[] generateLegalMoves() {

		int BOARD_MAX = 7, BOARD_MIN = 0;

		Move[] moves = new Move[10000];
		ChessPiece currentPiece;
		ChessPiece anotherPiece;// blcoking piece

		Position currentStartSquare;
		Position currentTargetSquare;

		String currentPieceType;

		int counter = 0;// current size-of positions array
		int counterTemp = 0;
		Position positions[] = new Position[100]; // end positions
		Position targetPos = new Position(-1, -1);

		int currentCol = 0, currentRow = 0, targetCol = 0, targetRow = 0;

		int movesCounter = 0; // for each peice it changes

		for (int num = 0; num < pieces.size(); num++) { // use peices array list for all peices

			// current peice
			currentPiece = pieces.get(num);
			currentStartSquare = currentPiece.getPosition();
			currentPieceType = currentPiece.getType().toString();

			if (currentPieceType.equals("BISHOP")) {
				// FIND BISHOP LEGAL MOVES ===========================================

				counter = 0;
				positions = new Position[100];
				targetPos = new Position(-1, -1);

				currentCol = currentStartSquare.getCol();
				currentRow = currentStartSquare.getRow();

				// can only move diagonaly . 4 ways
				// for 7 possible squares...// check if reach end of board and if target

				// up right //cols grow, rows grow ===========================
				for (int i = 0; i <= BOARD_MAX; i++) {
					targetCol = currentCol + i;
					targetRow = currentRow + i;

					if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
						targetPos = new Position(targetCol, targetRow);

						if (isBlocking(targetPos, currentPiece)) {// leave direction

							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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

						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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

				// for all postions- add to moves
				for (int j = 0; j < positionsAns.length; j++) {

					currentTargetSquare = positionsAns[j];
					// add a move of start positon to end psotion
					moves[movesCounter] = new Move(currentStartSquare, currentTargetSquare);
					movesCounter++;

				}

			} else if (currentPieceType.equals("QUEEN")) {
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

						if (isBlocking(targetPos, currentPiece)) {// leave direction

							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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

						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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

						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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

						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							counterTemp = counter;
							counter = checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counterTemp);

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

				// for all postions- add to moves
				for (int j = 0; j < positionsAns.length; j++) {
					currentTargetSquare = positionsAns[j];
					moves[movesCounter] = new Move(currentStartSquare, currentTargetSquare);
					movesCounter++;
				}

			} else if (currentPieceType.equals("PAWN")) {
				// FIND PAWN LEGAL MOVES ===========================================

				counter = 0;// current size

				positions = new Position[100];
				targetPos = new Position(-1, -1);

				currentCol = currentStartSquare.getCol();
				currentRow = currentStartSquare.getRow();

				Position[] START_POSITIONS = { new Position(0, 6), new Position(1, 6), new Position(2, 6),
						new Position(3, 6), new Position(4, 6), new Position(5, 6), new Position(6, 6),
						new Position(7, 6), new Position(0, 1), new Position(1, 1), new Position(2, 1),
						new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(6, 1),
						new Position(7, 1) }; // pawn start postiosn
				// ENPASSENT_POSITIONS = {};

				Position[] END_POSITIONS = { new Position(0, 0), new Position(1, 0), new Position(2, 0),
						new Position(3, 0), new Position(4, 0), new Position(5, 0), new Position(6, 0),
						new Position(7, 0), new Position(0, 7), new Position(1, 7), new Position(2, 7),
						new Position(3, 7), new Position(4, 7), new Position(5, 7), new Position(6, 7),
						new Position(7, 7) }; // pawn end postiosn-if land on - will become a queen

				int numSpaces = 1; // how far it can go

				if (isDuplicate(START_POSITIONS, START_POSITIONS.length, currentPiece.getPosition())) {
					// current pos is a start pos->first move -> can move 2 spaces
					numSpaces = 2;
				}

				if (isDuplicate(END_POSITIONS, END_POSITIONS.length, currentPiece.getPosition())) {
					;
					// currentPiece = new Queen(currentPiece.isWhite(), currentPiece.getPosition());
					// not yet--only after actual move
				}

				// calc end positions

				// define "forward"//up for white. down for black
				// ================================================
				if (currentPiece.isWhite()) {// WHITE: UP //cols same, rows change

					for (int i = 0; i <= numSpaces; i++) {
						targetCol = currentCol;
						targetRow = currentRow + i;

						if (targetRow <= BOARD_MAX) {
							targetPos = new Position(targetCol, targetRow);

							if (isBlocking(targetPos, currentPiece)) {// leave direction
								break;
							}
							if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								positions[counter] = targetPos;
								counter++;
							}
						}

					}

				} else if (!currentPiece.isWhite()) {// BALCK: DOWN
					for (int i = 0; i <= numSpaces; i++) {
						targetCol = currentCol;
						targetRow = currentRow - i;

						if (targetRow >= BOARD_MIN) {
							targetPos = new Position(targetCol, targetRow);
							if (isBlocking(targetPos, currentPiece)) {// leave direction
								break;
							}
							if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								positions[counter] = targetPos;
								counter++;
							}
						}

					}

				}

				// diag 4 ways //depends on color =========================
				// move diag (only if isblocking = true and dif color).

				// for 2 possible squares check
				// if reach end of board and if target alreadly in list

				if (currentPiece.isWhite()) { // WHITE: UP(left/right)

					// up right //cols grow, rows grow
					for (int i = 0; i <= 1; i++) {
						targetCol = currentCol + i;
						targetRow = currentRow + i;

						if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
							targetPos = new Position(targetCol, targetRow);

							// check blocking
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice

							for (int m = 0; m < pieces.size(); m++) {// search for this other peice
								if (pieces.get(m).getPosition().equals(targetPos)) {
									anotherPiece = pieces.get(m);
								}
							}

							if (isBlocking(targetPos, currentPiece) && !anotherPiece.isWhite()
									&& !isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								// and color dif

								positions[counter] = targetPos;
								counter++;
							}

						}

					}

					// up left //cols shrink, rows grow
					for (int i = 0; i <= 1; i++) {
						targetCol = currentCol - i;
						targetRow = currentRow + i;

						if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
							targetPos = new Position(targetCol, targetRow);

							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice

							for (int m = 0; m < pieces.size(); m++) {// search for this other peice
								if (pieces.get(m).getPosition().equals(targetPos)) {
									anotherPiece = pieces.get(m);
								}
							}

							if (isBlocking(targetPos, currentPiece) && !anotherPiece.isWhite()
									&& !isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								// and color dif

								positions[counter] = targetPos;
								counter++;
							}

						}

					}

				} else if (!currentPiece.isWhite()) {// black pawn - down(left or right)

					// down left //cols shrink, rows shrink
					for (int i = 0; i <= 1; i++) {
						targetCol = currentCol - i;
						targetRow = currentRow - i;

						if (targetCol >= BOARD_MIN && targetRow >= BOARD_MIN) {
							targetPos = new Position(targetCol, targetRow);

							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice

							for (int m = 0; m < pieces.size(); m++) {// search for this other peice
								if (pieces.get(m).getPosition().equals(targetPos)) {
									anotherPiece = pieces.get(m);
								}
							}

							if (isBlocking(targetPos, currentPiece) && anotherPiece.isWhite()
									&& !isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								// and color dif

								positions[counter] = targetPos;
								counter++;
							}
						}

					}

					// down right //cols grow, rows shrink
					for (int i = 0; i <= 1; i++) {
						targetCol = currentCol + i;
						targetRow = currentRow - i;

						if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
							targetPos = new Position(targetCol, targetRow);

							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice

							for (int m = 0; m < pieces.size(); m++) {// search for this other peice
								if (pieces.get(m).getPosition().equals(targetPos)) {
									anotherPiece = pieces.get(m);
								}
							}

							if (isBlocking(targetPos, currentPiece) && anotherPiece.isWhite()
									&& !isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								// and color dif
								positions[counter] = targetPos;
								counter++;
							}
						}

					}

				}

				// copy array of correct size
				Position positionsAns[] = new Position[counter];
				for (int i = 0; i < counter; i++) {
					positionsAns[i] = positions[i];
				}

				// for all postions- add to moves
				for (int j = 0; j < positionsAns.length; j++) {
					currentTargetSquare = positionsAns[j];
					moves[movesCounter] = new Move(currentStartSquare, currentTargetSquare);
					movesCounter++;
				}

			} else {
				// CASE king knight rook ===================

				;
			}

		} // end all pices

		// copy array of correct size
		Move movesAns[] = new Move[movesCounter];
		for (int i = 0; i < movesCounter; i++) {
			movesAns[i] = moves[i];
		}

		// System.out.println("Number of moves: " + movesCounter); // debug
		return movesAns;

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
	public boolean isBlocking(Position targetPos, ChessPiece currentPiece) {
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
			Position[] positions, int counter) {

		int newCounter = 0;

		for (int m = 0; m < pieces.size(); m++) {// search for this other peice
			if (pieces.get(m).getPosition().equals(targetPos)) {
				anotherPiece = pieces.get(m);
			}
		}

		// can attack: if currentPiece is blocked by anotherPiece and colours are dif
		if ((!anotherPiece.isWhite() && currentPiece.isWhite())
				|| (anotherPiece.isWhite() && !currentPiece.isWhite())) { // if dif colors
			if (isBlocking(targetPos, currentPiece) && !isDuplicate(positions, counter, targetPos)
					&& !(isCheck() || isCheckMate())) {// if blocked - add to end postions
				positions[counter] = targetPos;
				counter++;
			}
		}

		newCounter = counter;
		return newCounter;
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
			c = pieceMap[row * 8 + i];

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

		String[] fenArray = this.fen.split(" "); // split FEN into [board, turn, castling, en passant, halfmove clock,
													// fullmove number)
		String[] fenArrayBoard = fenArray[0].split("/"); // further split boardFEN into rows

		// System.out.println(newRow);

		// replace the old row with the new row, then join the boardFEN
		fenArrayBoard[7 - row] = newRow;
		List<String> fenBoardList = Arrays.asList(fenArrayBoard);
		String fenBoardString = String.join("/", fenBoardList);

		// replace the old boardFEN with new boardFEN and then join all FEN strings
		fenArray[0] = fenBoardString;
		List<String> fenList = Arrays.asList(fenArray);
		this.fen = String.join(" ", fenList);
	}

}