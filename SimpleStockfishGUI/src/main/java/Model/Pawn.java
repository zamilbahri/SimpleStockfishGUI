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
class Pawn extends ChessPiece {

	public Pawn(boolean isWhite, Position pos) {
		super(isWhite, pos);
		this.type = Type.PAWN;
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
	 * @returns the type of the Piece ( Pawn)
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
	 * @returns the start position of the Piece ( Pawn)
	 */
	@Override
	public Position getPosition() {
		return super.getPosition();
	}

}
