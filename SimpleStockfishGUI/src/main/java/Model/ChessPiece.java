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
public abstract class ChessPiece {
	private boolean isWhite; // true or false
	Position pos;
	Type type;

	public ChessPiece(boolean isWhite, Position pos) {
		this.isWhite = isWhite;
		this.pos = pos;
	}

	/**
	 * A function that indicates the colour of this piece
	 * 
	 * @return true if white, false is black
	 */
	public boolean isWhite() {
		return isWhite;
	}

	/**
	 * 
	 * @returns the type of the Piece (Rook, Knight, Bishop, Queen, King, Pawn)
	 */
	public Type getType() {
		return this.type;
	};

	/**
	 * 
	 * @returns the start position of the Piece (Rook, Knight, Bishop, Queen, King,
	 *          Pawn)
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * 
	 * @param targetPos
	 *            the position where the Piece is considering moving to
	 * @return a boolean indicating if the path is valid
	 */
	public abstract boolean isValidPath(Position targetPos);

	/**
	 * Creates an array of all positions that the piece can move if the board was
	 * empty.
	 * 
	 * @return the array of possible moves
	 */
	public abstract Position[] generatePseudoLegalMoves();

	/**
	 * @return a string visually representing the chessboard with all of its spaces
	 *         and pieces
	 */
	@Override
	public String toString() {
		return String.format("{%s, (%s, %s), %s}", type, pos.getCol(), pos.getRow(), isWhite);
	}

	/**
	 * @param obj
	 *            an object to check if its equal
	 * @return true if the chess piece is equal(same position, same color). false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		ChessPiece other = (ChessPiece) obj;
		return (this.getPosition().equals(other.getPosition()) && this.isWhite() == other.isWhite());
	}
}