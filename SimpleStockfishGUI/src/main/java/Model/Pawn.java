/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/*
 * TODO:
//pawn idea:
 * 
 create insatnce var startSquare(). = each pawns indiv starting square-when crated--done - not need
 
 if current postion != startsquare: then can move 1. //ist firstmove = false->  
 else can move 2 --done
 
 check enpassent -- look at currnet positon of current peice . if in enpassent square and enemy in right place: can enpassent = true. do enpassent
 //use a conts to hold possible enpassent squraes?  --STILL NEED
 
 if current postion is an end tile -> can change = true;- STILL NEED --fix endPostions array to be bottom and top row.dont change peice until move is done
 
 if can change = true
use pcies array - make pieices.get cuurent = new queen(pawn info) etc


//when land on pice: attack: make peices array remove that piece - make it a none piece -STILLNEED--putin move() ? --dont put in can attack.onnly once peice is mpved, it gone from peices array 
 
  =====
  
  idea for putting stuff back in seperate files--may  need to add dummy funcs to chesse(add duplicate and blocking) peice kight king rook--COULD DO
  
  board.gernate legla moves{
  
  if currentpiece= bishop: 
temppeice = new bihpsop(currnet piece data)
  
  
  curent can moves= temppeice.generatelegalMoves(pass what need to in here)
  
 else if pawn:
  
  else if queen:
  
  else... //other peices 
  
  
  }
 araylist gernalleglmoves(pieices etc){}
 
bishop  gernate psudeo moves-- will still exisits but not do anything
*/

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
/*
 * commit 4 - chess board generate legal mvoes
 * move: setend positon s, set start positon--no
 * modle test: test cases for movments
 * 
 * chesspeice, pawn,bishop,queen: added get postition()
 * ROOK,kings,knight: added get postition()
 * move- getMoveStr() added, remove() added
 * 
 * chess board- break in case statments
 * 
 * 
 * rn: legal moves is just all possible moves
 * stuck: hwo do legal moves? can add all possible to moves. then remove blocked moves. 
 * or direaclyt find all legal in bishop? need to pass stuff to func tho? how find other peicies that could block it...
 */

//next step - double check all directions as intended-it is. practice has test cases working
//note: gentarte psudo legal not used

/*
 * commit 5
 * modle test:made: new tester func for chess board
 * 
 * chessboard.genrate legal moves--works for initial chess board set up (bishops and queen, pawn(no enpassent,no first move check)) .
 * chessboard:  isblocking,isdupiclate added
 * 
 */

//next step://neeed test pawn diag case //add check enpassent .where set first mvoe false?
//if(is block ){ if(color is dif)-- postions.add(postion). set attacking = true //can go on that squre (to attack) but cant go past it} //do thisin every direcion check
// should put in dif func to clean up code in generate legla moves-// can make a func - pass: currentStartSquare . current peice

/*
 * commit 6 
 * chess board:legal moves-accomade for pawns first turn or not. 
 *  chess board:legal moves- accomadate for landing on peice of opposite color - pawn ok(only land on diag of dif color)  
 *  - queen and bishop land on  peices of dif color-- should be ok now.
 *  chess board: added check can attack()
 *  
 *model test: some test cases added for chess board.

 *  

 *
 */

/*
 * commmit 7
//* added to pawn - migh tnot need
 * 
 *
 * chesboard - legal moves- pawn can change to queen accounted for (actual changin not ) //mightnot ned?
 * 
 * 
 * cheess board  -move - actual turn pawn to queen . - and atack peice (remove form peices aray) .added---untested--need gui for more test cases
 * 
 * chess piece - added is equal()
 * 
 * cleaned up chess board legal move with helper funcs--check if this is equiv using test cases... --its not 
 */

//to celan could add LooskleftBIshop() funcs etc lookrightBIhosp(); - not work 
// new func in pawn.java--unused /// * ned to add not check to pawn -done

//next: test can attack/legal mvoes for queen and bihosp is ok 

//next: can help with check/checkmate--commit changes first
/*
 * commmit 8
 * 
 * started chessboard is check --not sure how test/ impment...-. unfinished feature
 *  
 *  model test:checked queen is blocking/can attack. checked bishop - done . checked arbiarty board (pawn,bishop,queen all ok - ignores enpassent, ignores moving puts self in check)
 *  
 *  chessboard: : queen horize/vert movments  wrong in isblocking...made it more similar to bishop diags- start from that postions--fixed
 *  
 *  
 *  
 *  https://lichess.org/editor?fen=1nbpkb1r%2Fp1p1p1pp%2F8%2F1p1q3P%2F8%2F8%2F3P3P%2FRNBQKBN1+w+Qk+-+0+1
 *  
 *  for fen testss cases
 */
/**
 *
 * @author zamil, Phoebe
 */
class Pawn extends ChessPiece {
	private boolean isFirstMove, isAttacking, canChange, canEnPassant;// is atack /can attack
	private Position startPos;
	private ArrayList<Position> canChangePosition;

	public Pawn(boolean isWhite, Position pos) {
		super(isWhite, pos);
		this.type = Type.PAWN;
		isFirstMove = true;
		isAttacking = false;
		canChange = false;
		canEnPassant = false;

		startPos = pos; // to check first move
		canChangePosition = new ArrayList<Position>();// new Position(-1, -1); // nothing to start //make sure clean at
														// some poitn? assuming moving anwywhere
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

	@Override
	public Position getPosition() {
		return super.getPosition();
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

	public boolean getCanChange() {
		return canChange;
	}

	public void setCanEnPassant(boolean set) {
		canEnPassant = set;
	}

	public boolean getCanEnPassant() {
		return canEnPassant;
	}

	public void setStartPos(Position sp) {
		startPos = sp;
	}

	public Position getStartPos() {
		return startPos;
	}

	public void addCanChangePos(Position ccp) {
		// canChangePosition = ccp;
		canChangePosition.add(ccp); // max 3 elems
	}

	public ArrayList<Position> getCanChangePos() {
		return canChangePosition;
	}

	public void cleanCanChangePos() { // reset list
		for (Position p : canChangePosition)
			canChangePosition.remove(p); // max 3 elems
	}

}
