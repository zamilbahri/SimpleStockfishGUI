/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author zamil,Phoebe
 */
public class ModelTest {

	public static void main(String args[]) {

		Position p1 = new Position(27);
		Position p2 = new Position(63);
		Position p3 = new Position(0);
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());
		/*
		 * ChessPiece rookW = new Rook(true, p1);
		 * 
		 * System.out.println(String.format("Path from %s to %s", p1.getAlgebraic(),
		 * p3.getAlgebraic())); Position path[] = rookW.drawPath(p3); int path_len =
		 * path.length; for (int i = 0; i < path_len; ++i) {
		 * System.out.print(path[i].getAlgebraic() + " "); } System.out.println(""); //
		 * prints an empty line
		 * 
		 * ChessBoard cb = new ChessBoard(); System.out.println(cb.toString());
		 */

		// ChessBoard cb = new
		// ChessBoard("r3k2r/pp1b1ppp/1qnbpn2/2ppN3/3P1B2/1QPBP3/PP1N1PPP/R4RK1 w kq - 0
		// 1");
		/*
		 * ChessBoard cb = new ChessBoard();
		 * 
		 * cb.move(p1, p2); System.out.println(cb.toString()); cb.move(new
		 * Position("d7"), new Position("d5")); System.out.println(cb.toString());
		 * cb.move(new Position("e4"), new Position("d5"));
		 * System.out.println(cb.toString());
		 * 
		 * System.out.println(cb.getFen());
		 */

		// practice -test cases:pawn,bisop,queen
		// position(col,row) //on board looks like (y,x)

		// enable for test cases:
		// practice.testerBishop(); // bishop tester
		// practice.testerQueen();
		// practice.testerPawn();

		moveTester();
		testerBoard();
	}

	public static void testerBoard() {

		System.out.println(new Position(0, 0));// a1
		System.out.println(new Position(7, 7)); // h8
		System.out.println(new Position(2, 0));// c1
		System.out.println(new Position(3, 1));// d2
		System.out.println(new Position(1, 1));
		System.out.println(new Position(0, 2));

		Move[] moves;
		ChessBoard cb;

		// chess board case 1-ok
		/*
		 * bishops expect: c8c8 f8f8 c1c1 f1f1
		 * 
		 * queens expect: d8 to d8 d1 to d1 --ok
		 * 
		 * 
		 * pawns expect:-ok(asuming first move)
		 * 
		 * a7a7 a7a6 a7a5
		 * 
		 * b7b7 b7b6 b7b5 ... h7h7 h7h6 h7h5
		 * 
		 * a2a2 a2a3 a2a4 b2b2 b2b3 b2b4 ... h2h2 h2h3 h2h4
		 */
		cb = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"); // new
																			// ChessBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R
																			// b KQkq - 1 2");
		System.out.println(cb);
		moves = cb.generateLegalMoves();

		for (Move move : moves) { // normal peices arrya
			System.out.println(move.getMoveStr());
		}

		// chess board case 2-ok
		/*
		 * bishops expect: c8c8 f8f8 c1c1 f1f1 f1e2 f1d3 f1c4 f1b5 f1a6 queens expect:
		 * --ok d8d8 d8c7 d8b6 d8a5 d1d1 d1e2
		 * 
		 * pawns expect:-ok(asuming first move) -- where set first mvoe false?--works
		 * now
		 * 
		 * a7a7 a7a6 a7a5
		 * 
		 * b7b7 b7b6 b7b5 ...
		 * 
		 * c5c5 c5c4 c5c3--should be no if first move false ... h7h7 h7h6 h7h5
		 * 
		 * a2a2 a2a3 a2a4 b2b2 b2b3 b2b4 ...
		 * 
		 * e4e4 e4e5 e4e6--should be no when firt move false ... h2h2 h2h3 h2h4
		 * 
		 * 
		 */
		cb = new ChessBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
		System.out.println(cb);
		moves = cb.generateLegalMoves();

		for (Move move : moves) { // normal peices arrya
			System.out.println(move.getMoveStr());
		}

		// case 3 -check pawn diagnoal attack
		// expect: c3 to b2, c3 to d2 -- test ok
		cb = new ChessBoard("rnbqkbnr/pp1ppppp/8/8/4P3/2p2N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
		System.out.println(cb);
		moves = cb.generateLegalMoves();

		for (Move move : moves) { // normal peices arrya
			System.out.println(move.getMoveStr());
		}

		// case 4 -check pawn diagnoal attack of same color
		// expect: c3 to b2, d3d4--not d3e4-- test ok.
		cb = new ChessBoard("rnbqkbnr/pp1ppppp/8/8/4P3/2pP1N2/PPP2PPP/RNBQKB1R b KQkq - 1 2");
		System.out.println(cb);
		moves = cb.generateLegalMoves();

		for (Move move : moves) { // normal peices arrya
			System.out.println(move.getMoveStr());
		}

		// test generate legal mvoes:
		// change pcies array - 2 peices
		// need use peixes for all peices
		/*
		 * Move[] moves = new Move[10000]; ChessPiece currentPiece; ChessPiece
		 * anotherPiece;// blcoking piece String currentPieceType;
		 * 
		 * // try board with 2 peices eg //CASE 1 OK:-- pieces = new ArrayList<>();
		 * //clean it for now
		 * 
		 * for(int i= 0 ; i < 11; i++) //add fake peices pieces.add(new Bishop(true,new
		 * Position(-99, -99)));
		 * 
		 * //case1 ok currentPiece = new Bishop(true, new Position(2, 0));// peices in
		 * peices array anotherPiece = new Bishop(true, new Position(3, 1));
		 * 
		 * //CASE 2: ok currentPiece = new Bishop(true, new Position(1, 1));// peices in
		 * peices array anotherPiece = new Bishop(true, new Position(2, 0));
		 * 
		 * //CASE 3:ok currentPiece = new Bishop(true, new Position(3, 5));//d6// peices
		 * in peices array anotherPiece = new Bishop(true, new Position(1, 3));//b4
		 * 
		 * pieces.add(currentPiece); //debug pieces.add(anotherPiece);
		 * 
		 * 
		 * for (int i = 0; i < pieces.size(); i++) { //debug
		 * System.out.println(pieces.get(i)); }
		 * 
		 * 
		 * System.out.println("EXPECT c1c1 c1b2 c1a3");//case ok
		 * System.out.println("EXPECT b2b2	b2c3 b2d4 	b2e5 b2f6 b2g7 b2h8 b2a1 b2a3");
		 * //case ok
		 */
		/*
		 * expect d6d6 d6e7 d6f8 d6c5 d6c7 d6b8 d6e5 d6f4 d6g3 d6h2
		 */
		System.out.println("past 3 cases ok \n\n");
	}

	public static void moveTester() {
		;
		/*
		 * for (int i = 0; i < pieces.size(); i++) { // araray list
		 * System.out.println(pieces.get(i)); }
		 */ // get peicies()

		Bishop b;
		Position[] fakeMoves = null;
		Position[] expectedMoves = null;
		boolean bool;

		Move[] moves = null;

		b = new Bishop(true, new Position(2, 0));// intilziation (col,row)
		fakeMoves = b.generatePseudoLegalMoves();
		expectedMoves = new Position[] { new Position(2, 0), new Position(3, 1), new Position(4, 2), new Position(5, 3),
				new Position(6, 4), new Position(7, 5), new Position(1, 1), new Position(0, 2) };

		bool = false;// bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		ChessBoard cb = new ChessBoard();

		moves = cb.generateLegalMoves(); // b.genrate

		for (Move move : moves) {
			System.out.println(move.getMoveStr());
		} // why is mutli stuff starting at same spot?

		// System.out.println(ChessBoard.pieces.get(0).getPosition());
	}
}

class practice {

	public static boolean testerHelper(Position[] fakeMoves, Position[] expectedMoves) {
		// order matters: put expected moves in expected order // or sort?// or
		// if(!fakeMoves in expectedMoves) { return false;};

		if (fakeMoves == null)
			return false;

		System.out.println("fakeMoves: col,row\n");// debug
		for (Position move : fakeMoves) { // debug
			System.out.println(move.getCol() + " " + move.getRow());
		}

		if (fakeMoves.length != expectedMoves.length) {
			return false;
		}

		boolean bool = true;
		for (int i = 0; i < fakeMoves.length; i++) { // not .lenght()

			if (!fakeMoves[i].equals(expectedMoves[i])) {
				bool = false;
				break;
			}

		}
		return bool;
	}

	public static void testerHelperQueen(Queen q, Position[] expectedMoves) {
		Position[] fakeMoves = null;
		boolean bool;

		fakeMoves = q.generatePseudoLegalMoves();

		// System.out.println(fakeMoves[0].toString(),expectedMoves[0].toString());
		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}
	}

	public static void testerBishop() { // unit testing // test ideal bishop.vs actaul bishops

		Bishop b;
		Position[] fakeMoves = null;
		Position[] expectedMoves = null;
		boolean bool;

		/*
		 * //test diagonals in gernal --all works fine b = new Bishop(true, new
		 * Position(0, 0)); // test 1 -ok fakeMoves = b.generatePseudoLegalMoves();
		 * expectedMoves = new Position[] { new Position(0, 0), new Position(1, 1), new
		 * Position(2, 2), new Position(3, 3), new Position(4, 4), new Position(5, 5),
		 * new Position(6, 6), new Position(7, 7) };
		 * 
		 * bool = testerHelper(fakeMoves, expectedMoves); if (bool) {
		 * System.out.println("\nTEST OK\n"); } else {
		 * System.out.println("\nTEST BAD\n"); }
		 * 
		 * // TEST 2-ok// need be able to look bawards too b = new Bishop(true, new
		 * Position(1, 1)); fakeMoves = b.generatePseudoLegalMoves(); expectedMoves =
		 * new Position[] { new Position(0, 0), new Position(2, 0), new Position(0, 2),
		 * new Position(1, 1), new Position(2, 2), new Position(3, 3), new Position(4,
		 * 4), new Position(5, 5), new Position(6, 6), new Position(7, 7) };
		 * 
		 * bool = testerHelper(fakeMoves, expectedMoves); if (bool) {
		 * System.out.println("\nTEST OK\n"); } else {
		 * System.out.println("\nTEST BAD\n"); }
		 * 
		 * b = new Bishop(true, new Position(2, 2)); // T3 - ok fakeMoves =
		 * b.generatePseudoLegalMoves(); expectedMoves = new Position[] { new
		 * Position(0, 0), new Position(1, 3), new Position(3, 1), new Position(0, 4),
		 * new Position(4, 0), new Position(1, 1), new Position(2, 2), new Position(3,
		 * 3), new Position(4, 4), new Position(5, 5), new Position(6, 6), new
		 * Position(7, 7) };
		 * 
		 * bool = testerHelper(fakeMoves, expectedMoves); if (bool) {
		 * System.out.println("\nTEST OK\n"); } else {
		 * System.out.println("\nTEST BAD\n"); }
		 * 
		 * b = new Bishop(true, new Position(6, 6)); // t4- ok fakeMoves =
		 * b.generatePseudoLegalMoves(); expectedMoves = new Position[] { new
		 * Position(0, 0), new Position(5, 7), new Position(7, 5), new Position(1, 1),
		 * new Position(2, 2), new Position(3, 3), new Position(4, 4), new Position(5,
		 * 5), new Position(6, 6), new Position(7, 7) };
		 * 
		 * bool = testerHelper(fakeMoves, expectedMoves); if (bool) {
		 * System.out.println("\nTEST OK\n"); } else {
		 * System.out.println("\nTEST BAD\n"); }
		 * 
		 * b = new Bishop(true, new Position(7, 7)); // t5-ok fakeMoves =
		 * b.generatePseudoLegalMoves(); expectedMoves = new Position[] { new
		 * Position(0, 0), new Position(1, 1), new Position(2, 2), new Position(3, 3),
		 * new Position(4, 4), new Position(5, 5), new Position(6, 6), new Position(7,
		 * 7) };
		 * 
		 * bool = testerHelper(fakeMoves, expectedMoves); if (bool) {
		 * System.out.println("\nTEST OK\n"); } else {
		 * System.out.println("\nTEST BAD\n"); }
		 * System.out.println("done first tests\n");
		 */
		// actaul bishop start spot
		// CASE 1 //ok
		b = new Bishop(true, new Position(2, 0));// intilziation (col,row)
		fakeMoves = b.generatePseudoLegalMoves();
		expectedMoves = new Position[] { new Position(2, 0), new Position(3, 1), new Position(4, 2), new Position(5, 3),
				new Position(6, 4), new Position(7, 5), new Position(1, 1), new Position(0, 2) };

		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		b = new Bishop(true, new Position(5, 0)); // case2 -ok
		fakeMoves = b.generatePseudoLegalMoves();
		expectedMoves = new Position[] { new Position(5, 0), new Position(6, 1), new Position(7, 2), new Position(4, 1),
				new Position(3, 2), new Position(2, 3), new Position(1, 4), new Position(0, 5) };

		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		b = new Bishop(true, new Position(2, 7));// case3 -ok
		fakeMoves = b.generatePseudoLegalMoves();
		expectedMoves = new Position[] { new Position(2, 7), new Position(1, 6), new Position(0, 5), new Position(3, 6),
				new Position(4, 5), new Position(5, 4), new Position(6, 3), new Position(7, 2) };

		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		b = new Bishop(true, new Position(5, 7)); // case 4 -ok // 5,7 //4,6 3,5 2,4 1,3 0,2 // 6,6 7,5
		fakeMoves = b.generatePseudoLegalMoves();
		expectedMoves = new Position[] { new Position(5, 7), new Position(4, 6), new Position(3, 5), new Position(2, 4),
				new Position(1, 3), new Position(0, 2), new Position(6, 6), new Position(7, 5) };

		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		b = new Bishop(true, new Position(3, 5));// case 5 -ok // 35 - arbairatry legal spot //all 4 directions
		// expectd 2,6 and 1,7 aldo 7 1 0 2 6 2 1 3 5 3 2 4 4 4 3 5 4 6 5 7
		fakeMoves = b.generatePseudoLegalMoves();
		expectedMoves = new Position[] { new Position(3, 5), new Position(4, 6), new Position(5, 7), new Position(2, 4),
				new Position(1, 3), new Position(0, 2), new Position(2, 6), new Position(1, 7), new Position(4, 4),
				new Position(5, 3), new Position(6, 2), new Position(7, 1) };

		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		b = new Bishop(true, new Position(5, 4)); // case5 - arb case --seems ok - just dif order
		// 36 27 10 21 32 72 43 63 54 45 65 76

		fakeMoves = b.generatePseudoLegalMoves();
		/*
		 * expectedMoves = new Position[] { new Position(3, 6), new Position(2, 7), new
		 * Position(1, 0), new Position(2, 1), new Position(3, 2), new Position(7, 2),
		 * new Position(4, 3), new Position(6, 3), new Position(5, 4), new Position(4,
		 * 5), new Position(6, 5), new Position(7, 6) };
		 */
		expectedMoves = new Position[] { new Position(5, 4), new Position(6, 5), new Position(7, 6), new Position(4, 3),
				new Position(3, 2), new Position(2, 1), new Position(1, 0), new Position(4, 5), new Position(3, 6),
				new Position(2, 7), new Position(6, 3), new Position(7, 2), };

		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		System.out.println("done all bishop tests\n");

	}

	public static void testerQueen() { // unit testing //order matters...

		Queen q;
		// Position[] fakeMoves = null;
		Position[] expectedMoves = null;
		// boolean bool;

		// case1
		q = new Queen(true, new Position(3, 7)); // case1 -ok
		/*
		 * expectedMoves = new Position[] { new Position(0, 7),new Position(1, 7), new
		 * Position(2, 7),new Position(3, 7), new Position(4, 7),new Position(5, 7), new
		 * Position(6, 7),new Position(7, 7), new Position(3, 0), new Position(3, 1),
		 * new Position(3, 2), new Position(3, 3), new Position(3, 4), new Position(3,
		 * 5), new Position(3, 6), new Position(3, 7), new Position(2, 6), new
		 * Position(1, 5), new Position(0, 4), new Position(4, 6), new Position(5, 5),
		 * new Position(6, 4), new Position(7, 3)};
		 */

		expectedMoves = new Position[] { new Position(3, 7), new Position(2, 6), new Position(1, 5), new Position(0, 4),
				new Position(4, 6), new Position(5, 5), new Position(6, 4), new Position(7, 3), new Position(0, 7),
				new Position(1, 7), new Position(2, 7), new Position(4, 7), new Position(5, 7), new Position(6, 7),
				new Position(7, 7), new Position(3, 0), new Position(3, 1), new Position(3, 2), new Position(3, 3),
				new Position(3, 4), new Position(3, 5), new Position(3, 6) };
		testerHelperQueen(q, expectedMoves);

		/*
		 * Position[] fakeMoves = null; boolean bool;
		 * 
		 * fakeMoves = q.generatePseudoLegalMoves();
		 * 
		 * // System.out.println(fakeMoves[0].toString(),expectedMoves[0].toString());
		 * bool = testerHelper(fakeMoves, expectedMoves); if (bool) {
		 * System.out.println("\nTEST OK\n"); } else {
		 * System.out.println("\nTEST BAD\n"); }
		 */
		// case 2

		q = new Queen(true, new Position(3, 0)); // case2 --ok
		/*
		 * expectedMoves = new Position[] { new Position(0, 0),new Position(1, 0), new
		 * Position(2, 0),new Position(3, 0), new Position(4, 0),new Position(5, 0), new
		 * Position(6, 0),new Position(7, 0), new Position(3, 0), new Position(3, 1),
		 * new Position(3, 2), new Position(3, 3), new Position(3, 4), new Position(3,
		 * 5), new Position(3, 6), new Position(3, 7), new Position(2, 1), new
		 * Position(1, 2), new Position(0, 3), new Position(4, 1), new Position(5, 2),
		 * new Position(6, 3), new Position(7, 4)};
		 */
		expectedMoves = new Position[] { new Position(3, 0), new Position(4, 1), new Position(5, 2), new Position(6, 3),
				new Position(7, 4), new Position(2, 1), new Position(1, 2), new Position(0, 3), new Position(0, 0),
				new Position(1, 0), new Position(2, 0), new Position(4, 0), new Position(5, 0), new Position(6, 0),
				new Position(7, 0), new Position(3, 1), new Position(3, 2), new Position(3, 3), new Position(3, 4),
				new Position(3, 5), new Position(3, 6), new Position(3, 7) };

		testerHelperQueen(q, expectedMoves);

		// case 3

		q = new Queen(true, new Position(5, 4)); // case3 - arb case //36 27 10 21 32 72 43 63 54 45 65 76 ...
		expectedMoves = new Position[] { new Position(5, 4), new Position(6, 5), new Position(7, 6), new Position(4, 3),
				new Position(3, 2), new Position(2, 1), new Position(1, 0), new Position(4, 5), new Position(3, 6),
				new Position(2, 7), new Position(6, 3), new Position(7, 2),

				new Position(0, 4), new Position(1, 4), new Position(2, 4), new Position(3, 4), new Position(4, 4),
				new Position(6, 4), new Position(7, 4), new Position(5, 0), new Position(5, 1), new Position(5, 2),
				new Position(5, 3), new Position(5, 5), new Position(5, 6), new Position(5, 7),

		};
		testerHelperQueen(q, expectedMoves);
		System.out.println("done all queen tests\n");
	}

	public static void testerPawn() {
		Pawn p;
		Position[] fakeMoves = null;
		Position[] expectedMoves = null;
		boolean bool;

		p = new Pawn(true, new Position(1, 1)); // case1 //white pawn-ok
		expectedMoves = new Position[] { new Position(1, 1), new Position(1, 2), new Position(1, 3), new Position(2, 2),
				new Position(0, 2) }; // + enpassent

		fakeMoves = p.generatePseudoLegalMoves();
		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		p = new Pawn(false, new Position(1, 1)); // case2 //black pawn-ok
		expectedMoves = new Position[] { new Position(1, 1), new Position(1, 0), new Position(0, 0),
				new Position(2, 0) }; // + enpassent

		fakeMoves = p.generatePseudoLegalMoves();
		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		p = new Pawn(true, new Position(5, 6)); // case3 //white pawn-ok
		// expectedMoves = new Position[] { new Position(5, 6), new Position(5, 7), new
		// Position(4, 7), new Position(6, 7) }; //+ enpassent
		expectedMoves = new Position[] { new Position(5, 6), new Position(5, 7), new Position(6, 7),
				new Position(4, 7) }; // + enpassent

		fakeMoves = p.generatePseudoLegalMoves();
		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		p = new Pawn(false, new Position(5, 6)); // case4 //black pawn- ok
		expectedMoves = new Position[] { new Position(5, 6), new Position(5, 5), new Position(5, 4), new Position(4, 5),
				new Position(6, 5) }; // + enpassent

		fakeMoves = p.generatePseudoLegalMoves();
		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		p = new Pawn(true, new Position(0, 1)); // case5
		expectedMoves = new Position[] { new Position(0, 1), new Position(0, 2), new Position(0, 3),
				new Position(1, 2) }; // + enpassent

		fakeMoves = p.generatePseudoLegalMoves();
		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		p = new Pawn(false, new Position(0, 1)); // case6
		expectedMoves = new Position[] { new Position(0, 1), new Position(0, 0), new Position(1, 0) }; // + enpassent

		fakeMoves = p.generatePseudoLegalMoves();
		bool = testerHelper(fakeMoves, expectedMoves);
		if (bool) {
			System.out.println("\nTEST OK\n");
		} else {
			System.out.println("\nTEST BAD\n");
		}

		// do an enpassent case
		System.out.println("done all pawn tests\n");
	}
}