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

		practice.testerBishop(); // bishop tester
		practice.testerQueen();
		practice.testerPawn();

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