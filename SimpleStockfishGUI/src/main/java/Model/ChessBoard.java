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
 * @author zamil
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

		/// added:
		// once a move is done: delte attacked peice for piecies
		// change pawn if need be.

		/*
		 * if curent piece = PAWN and duplicate (getcurrentpos , endpostion)_> //or get
		 * end can change change
		 * 
		 * if current peice.can attack() = true; //and another peice.isattacked = true?
		 * for peices if another peice.pos == current pice .pos and is not another peice
		 * peices.remove another peice;
		 */

		// int BOARD_MAX = 7, BOARD_MIN = 0;

		ChessPiece currentPiece;
		ChessPiece anotherPiece;// attacekd piece

		Position currentStartSquare, anotherStartSquare;
		// Position currentTargetSquare;

		String currentPieceType;

		Position[] END_POSITIONS = { new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(3, 0),
				new Position(4, 0), new Position(5, 0), new Position(6, 0), new Position(7, 0), new Position(0, 7),
				new Position(1, 7), new Position(2, 7), new Position(3, 7), new Position(4, 7), new Position(5, 7),
				new Position(6, 7), new Position(7, 7) };// pawn end postiosn-if land on - will become a queen

		for (int num1 = 0; num1 < pieces.size(); num1++) { // for all peices
			// current peice
			currentPiece = pieces.get(num1);
			currentStartSquare = currentPiece.getPosition();
			currentPieceType = currentPiece.getType().toString();

			// dont need can change bool or array..
			if (currentPieceType.equals("PAWN")
					&& isDuplicate(END_POSITIONS, END_POSITIONS.length, currentPiece.getPosition())) {// change pawn to
																										// queen

				currentPiece = new Queen(currentPiece.isWhite(), currentPiece.getPosition());
				// the actual change into queen //--only after actual move

			} else {

				for (int num2 = 0; num2 < pieces.size(); num2++) { // for all peices
					anotherPiece = pieces.get(num2);

					anotherStartSquare = currentPiece.getPosition();

					if (currentStartSquare.equals(anotherStartSquare) && !currentPiece.equals(anotherPiece)) {
						// if same start psiitsno //check attacking

						pieces.remove(anotherPiece);
						// or peices.get i = none peice?

					}
				}

			}
		}

	}

	public boolean isCheck() { // dummy funcs
		return false;
	}

	public boolean isCheckMate() {
		return false;
	}

	/**
	 * 
	 * @return A list of strings(?) containing the moves
	 */

	public Move[] generateLegalMoves() { // works - but meesy

		// need use peixes array list for all peices

		/*
		 * for (int i = 0; i < pieces.size(); i++) { // debug
		 * System.out.println(pieces.get(i)); }
		 */

		int BOARD_MAX = 7, BOARD_MIN = 0;

		Move[] moves = new Move[10000];
		ChessPiece currentPiece;
		ChessPiece anotherPiece;// blcoking piece

		Position currentStartSquare;
		Position currentTargetSquare;

		String currentPieceType;

		int counter = 0;// current size-of positions
		Position positions[] = new Position[100]; // end positions
		Position targetPos = new Position(-1, -1);

		int currentCol = 0, currentRow = 0, targetCol = 0, targetRow = 0;

		int movesCounter = 0; // for each peice it changes

		for (int num = 0; num < pieces.size(); num++) { // for all peices

			// current peice
			currentPiece = pieces.get(num);
			currentStartSquare = currentPiece.getPosition();
			currentPieceType = currentPiece.getType().toString();

			// =================== //using genrate psudo and legal in 1
			if (currentPieceType.equals("BISHOP")) {

				counter = 0;
				positions = new Position[100];
				targetPos = new Position(-1, -1);

				currentCol = currentStartSquare.getCol();
				currentRow = currentStartSquare.getRow();

				// can only move diagonaly . 4 ways
				// for 7 possible squares...// check if reach end of board and if target

				for (int i = 0; i <= BOARD_MAX; i++) {// up right //cols grow, rows grow
					targetCol = currentCol + i;
					targetRow = currentRow + i;

					if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
						targetPos = new Position(targetCol, targetRow);

						if (isBlocking(targetPos, currentPiece)) {// leave direction

							// if target in psudeo legal: ignore it-and any coming after it in that
							// direction
							/*
							 * for (pieces.size() ){//serach to find other using pieces
							 * 
							 * if( (currentPiece.isWhite() && !other.isWhite() )|| (!currentPiece.isWhite()
							 * && other.isWhite()) ) { positions[counter] = targetPos; counter++;
							 * currentPiece.setAttacking(true); }
							 */
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);

							/*
							 * // can attack anotherPiece = new Pawn(true, new Position(-1, -1)); // default
							 * peice // class for (int m = 0; m < pieces.size(); m++) {// search for this
							 * other peice if (pieces.get(m).getPosition().equals(targetPos)) { anotherPiece
							 * = pieces.get(m); } }
							 * 
							 * if (isBlocking(targetPos, currentPiece) && !anotherPiece.isWhite() &&
							 * !isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate()))
							 * { positions[counter] = targetPos; counter++; }
							 */
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

				for (int i = 0; i <= BOARD_MAX; i++) { // down left //cols shrink, rows shrink
					targetCol = currentCol - i;
					targetRow = currentRow - i;

					if (targetCol >= BOARD_MIN && targetRow >= BOARD_MIN) {
						targetPos = new Position(targetCol, targetRow);

						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
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

				for (int i = 0; i <= BOARD_MAX; i++) {// up left //cols shrink, rows grow
					targetCol = currentCol - i;
					targetRow = currentRow + i;

					if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
						targetPos = new Position(targetCol, targetRow);
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
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

				for (int i = 0; i <= BOARD_MAX; i++) {// down right //cols grow, rows shrink
					targetCol = currentCol + i;
					targetRow = currentRow - i;

					if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
						targetPos = new Position(targetCol, targetRow);
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
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

				// return positionsAns;

				// for all postions- add to moves
				// movesCounter = 0;
				for (int j = 0; j < positionsAns.length; j++) {

					currentTargetSquare = positionsAns[j];
					moves[movesCounter] = new Move(currentStartSquare, currentTargetSquare);// add a move of start
																							// positon to end psotion
					movesCounter++;

				}

			} else if (currentPieceType.equals("QUEEN")) {
				// System.out.println("quuen");//debug

				counter = 0;// current size

				positions = new Position[100];
				targetPos = new Position(-1, -1);

				currentCol = currentStartSquare.getCol();
				currentRow = currentStartSquare.getRow();

				// daigs

				for (int i = 0; i <= BOARD_MAX; i++) {// up right //cols grow, rows grow
					targetCol = currentCol + i;
					targetRow = currentRow + i;

					if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
						targetPos = new Position(targetCol, targetRow);
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
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

				for (int i = 0; i <= BOARD_MAX; i++) { // down left //cols shrink, rows shrink
					targetCol = currentCol - i;
					targetRow = currentRow - i;

					if (targetCol >= BOARD_MIN && targetRow >= BOARD_MIN) {
						targetPos = new Position(targetCol, targetRow);

						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
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

				for (int i = 0; i <= BOARD_MAX; i++) {// up left //cols shrink, rows grow
					targetCol = currentCol - i;
					targetRow = currentRow + i;

					if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
						targetPos = new Position(targetCol, targetRow);

						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
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

				for (int i = 0; i <= BOARD_MAX; i++) {// down right //cols grow, rows shrink
					targetCol = currentCol + i;
					targetRow = currentRow - i;

					if (targetCol >= BOARD_MIN && targetRow <= BOARD_MAX) {
						targetPos = new Position(targetCol, targetRow);
						if (isBlocking(targetPos, currentPiece)) {// leave direction
							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
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

				// horiz/vertical moves
				for (int i = 0; i <= BOARD_MAX; i++) {// right/left //cols change, rows same
					targetCol = i;
					targetRow = currentRow;

					targetPos = new Position(targetCol, targetRow);
					if (isBlocking(targetPos, currentPiece)) {// leave direction
						anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
						checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
						break;
					}
					if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
						positions[counter] = targetPos;
						counter++;
					}

				}

				for (int i = 0; i <= BOARD_MAX; i++) {// up/down //cols same, rows change
					targetCol = currentCol;
					targetRow = i;

					targetPos = new Position(targetCol, targetRow);
					if (isBlocking(targetPos, currentPiece)) {// leave direction
						anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
						checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter);
						break;
					}
					if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
						positions[counter] = targetPos;
						counter++;
					}

				}

				// copy array of correct size
				Position positionsAns[] = new Position[counter];
				for (int i = 0; i < counter; i++) {
					positionsAns[i] = positions[i];

				}

				// return positionsAns;

				// for all postions- add to moves
				for (int j = 0; j < positionsAns.length; j++) {
					currentTargetSquare = positionsAns[j];
					moves[movesCounter] = new Move(currentStartSquare, currentTargetSquare);
					movesCounter++;

				}

			} else if (currentPieceType.equals("PAWN")) { // CASE PAWN
				// System.out.println("checking pawn");

				counter = 0;// current size

				positions = new Position[100];
				targetPos = new Position(-1, -1);

				currentCol = currentStartSquare.getCol();
				currentRow = currentStartSquare.getRow();

				Position[] START_POSITIONS = { new Position(0, 6), new Position(1, 6), new Position(2, 6),
						new Position(3, 6), new Position(4, 6), new Position(5, 6), new Position(6, 6),
						new Position(7, 6), new Position(0, 1), new Position(1, 1), new Position(2, 1),
						new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(6, 1),
						new Position(7, 1) };// pawn start postiosn
				// ENPASSENT_POSITIONS = {};

				// end postions... i,0 and i,7 ? //put end postiosn as another case?-no
				Position[] END_POSITIONS = { new Position(0, 0), new Position(1, 0), new Position(2, 0),
						new Position(3, 0), new Position(4, 0), new Position(5, 0), new Position(6, 0),
						new Position(7, 0), new Position(0, 7), new Position(1, 7), new Position(2, 7),
						new Position(3, 7), new Position(4, 7), new Position(5, 7), new Position(6, 7),
						new Position(7, 7) };// pawn end postiosn-if land on - will become a queen

				int numSpaces = 1; // how far it can go

				if (isDuplicate(START_POSITIONS, START_POSITIONS.length, currentPiece.getPosition())) { // current pos
																										// is a start
																										// pos->first
					// move
					numSpaces = 2;
				}

				if (isDuplicate(END_POSITIONS, END_POSITIONS.length, currentPiece.getPosition())) {
					// currentPiece = new Pawn(currentPiece.isWhite(), currentPiece.getPosition());
					// currentPiece.canChange() = true;
					;

					// currentPiece = new Queen(currentPiece.isWhite(), currentPiece.getPosition());
					// //not yet--only after actual move
					// exit this case... since its no longer a queen -- make it if pawn{else if pawn
					// and end postion}...
					Pawn tempPawn = (Pawn) currentPiece;
					tempPawn.setCanChange(true); // how relate this to actual peice?

					tempPawn.addCanChangePos(currentPiece.getPosition());
					// tempPawn.setCanChangePos = currentPiece.getPosition();
					currentPiece = tempPawn; // shoudl be same but can change true now

				} else { // just one end postions(out of posbiel 3 (forawrd diag 1 diag 2)) need to match
							// -> that end postion can
							// change...
					// set false?
					;
				}

				/*
				 * Pawn tempPawn = (Pawn) currentPiece; // type cast//check is first move
				 * 
				 * if (tempPawn.getStartPos().equals(currentPiece.getPosition())) {// not
				 * working?===================== // if
				 * (tempPawn.getStartPos().equals(currentStartSquare)) { // if current sqaure //
				 * = start squre: can move 2
				 * 
				 * // if (tempPawn.getIsFirstMove() == true) { numSpaces = 2; // pawn at c5= pos
				 * 24.c5 is not a start pos...way in define temp peices pos is // made...
				 * System.out.println( "\n" + tempPawn.getStartPos() + " " +
				 * currentPiece.getPosition() + " " + currentPiece); }
				 */
				// define forward//up for white. or down for black //calcuatel end positon

				if (currentPiece.isWhite()) {// WHITE: UP //cols same, rows change

					for (int i = 0; i <= numSpaces; i++) {
						targetCol = currentCol;
						targetRow = currentRow + i;

						if (targetRow <= BOARD_MAX) {// if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
							targetPos = new Position(targetCol, targetRow);

							if (isBlocking(targetPos, currentPiece)) {// leave direction
								break;
							}
							if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								positions[counter] = targetPos;
								counter++;
							}
						}
						// if (targetCol == BOARD_MAX || targetRow == BOARD_MAX) break;
					}

				} else if (!currentPiece.isWhite()) {// BALCK: DOWN
					for (int i = 0; i <= numSpaces; i++) {
						targetCol = currentCol;
						targetRow = currentRow - i;

						if (targetRow >= BOARD_MIN) {// if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
							targetPos = new Position(targetCol, targetRow);
							if (isBlocking(targetPos, currentPiece)) {// leave direction
								break;
							}
							if (!isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {
								positions[counter] = targetPos;
								counter++;
							}
						}
						// if (targetCol == BOARD_MAX || targetRow == BOARD_MAX) break;
					}

				}

				// move diag only if isblocking = true and dif color

				// can move diagonaly . 4 ways //depends on color
				// for 2 possible squares...// check if reach end of board and if target
				// alreadly in list

				if (currentPiece.isWhite()) {

					for (int i = 0; i <= 1; i++) {// up right //cols grow, rows grow
						targetCol = currentCol + i;
						targetRow = currentRow + i;

						if (targetCol <= BOARD_MAX && targetRow <= BOARD_MAX) {
							targetPos = new Position(targetCol, targetRow);

							/*
							 * anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice
							 * checkCanAttack(anotherPiece, targetPos, currentPiece, positions, counter); if
							 * (isBlocking(targetPos, currentPiece) && !isDuplicate(positions, counter,
							 * targetPos) && !(isCheck() || isCheckMate())) { --might add it twice if use...
							 */

							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice //cant use chess peice
																					// class
							for (int m = 0; m < pieces.size(); m++) {// search for this other peice
								if (pieces.get(m).getPosition().equals(targetPos)) {
									anotherPiece = pieces.get(m);
								}
							}

							if (isBlocking(targetPos, currentPiece) && !anotherPiece.isWhite()
									&& !isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) { // and
																														// color
																														// dif

								positions[counter] = targetPos;// positions.append(targetCol,targetRow);
								counter++;
							}

							/*
							 * if (isBlocking(targetPos, currentPiece)) {// leave direction break; } if
							 * (!isDuplicate(positions, counter, targetPos)) { positions[counter] =
							 * targetPos;// positions.append(targetCol,targetRow); counter++; }
							 */
						}
						// if (targetCol == BOARD_MAX || targetRow == BOARD_MAX)break;
					}

					for (int i = 0; i <= 1; i++) {// up left //cols shrink, rows grow
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

								// if (isBlocking(targetPos, currentPiece) && !isDuplicate(positions, counter,
								// targetPos)) {
								// and color dif
								positions[counter] = targetPos;// positions.append(targetCol,targetRow);
								counter++;
							}
							/*
							 * if (isBlocking(targetPos, currentPiece)) {// leave direction break; } if
							 * (!isDuplicate(positions, counter, targetPos)) { positions[counter] =
							 * targetPos; counter++; }
							 */
						}
						// if (targetCol == BOARD_MIN || targetRow == BOARD_MAX) break;
					}

				} else if (!currentPiece.isWhite()) {// black pawn

					for (int i = 0; i <= 1; i++) { // down left //cols shrink, rows shrink
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
								// if (isBlocking(targetPos, currentPiece) && !isDuplicate(positions, counter,
								// targetPos)) {
								// and color dif
								positions[counter] = targetPos;// positions.append(targetCol,targetRow);
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

							anotherPiece = new Pawn(true, new Position(-1, -1)); // default peice

							for (int m = 0; m < pieces.size(); m++) {// search for this other peice
								if (pieces.get(m).getPosition().equals(targetPos)) {
									anotherPiece = pieces.get(m);
								}
							}

							if (isBlocking(targetPos, currentPiece) && anotherPiece.isWhite()
									&& !isDuplicate(positions, counter, targetPos) && !(isCheck() || isCheckMate())) {

								// and color dif
								positions[counter] = targetPos;// positions.append(targetCol,targetRow);
								counter++;
							}
						}
						// if (targetCol == BOARD_MAX || targetRow == BOARD_MIN)break;
					}

				}

				// clear can change array?
				// currentPiece.cleanCanChangePos();

				// copy array of correct size
				Position positionsAns[] = new Position[counter]; // current array size used up
				for (int i = 0; i < counter; i++) {
					positionsAns[i] = positions[i];
				}

				// return positionsAns;

				// for all postions- add to moves
				for (int j = 0; j < positionsAns.length; j++) {

					currentTargetSquare = positionsAns[j];
					moves[movesCounter] = new Move(currentStartSquare, currentTargetSquare);
					movesCounter++;

				}

			} else {// CASE king kinght rook ===================
				// System.out.println("checking piece");// debug

				;
			}

		} // end all pices

		// copy array of correct size
		Move movesAns[] = new Move[movesCounter];
		for (int i = 0; i < movesCounter; i++) {
			movesAns[i] = moves[i];
		}

		System.out.println("availble moves " + movesCounter); // debug
		return movesAns;

	}

	public boolean isDuplicate(Position[] positions, int counter, Position targetPos) { // added

		if (counter == 0) // if (positions.length==0)
			return false;

		for (int i = 0; i < counter; i++) {
			if (positions[i].equals(targetPos))
				return true;
		}
		return false;
	}

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

	public void checkCanAttack(ChessPiece anotherPiece, Position targetPos, ChessPiece currentPiece,
			Position[] positions, int counter) {
		// can attack when colours dif. and is blocked by taht

		// can attack

		for (int m = 0; m < pieces.size(); m++) {// search for this other peice
			if (pieces.get(m).getPosition().equals(targetPos)) {
				anotherPiece = pieces.get(m);
			}
		}

		if ((!anotherPiece.isWhite() && currentPiece.isWhite())
				|| (anotherPiece.isWhite() && !currentPiece.isWhite())) { // if dif colors
			if (isBlocking(targetPos, currentPiece) && !isDuplicate(positions, counter, targetPos)
					&& !(isCheck() || isCheckMate())) {// if blocked - add to end postions
				positions[counter] = targetPos;
				counter++;
			}
		}
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