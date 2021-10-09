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
	public boolean isValidPath(Position targetPos) {
		// throw new UnsupportedOperationException("Not supported yet.");

		boolean isValid = false;
		setIsAttacking(false);
		int[] diag1 = { 0, 0 }, diag2 = { 0, 0 }, diag3 = { 0, 0 }, diag4 = { 0, 0 };

		int currentX = 0, currentY = 0, targetX = 0, targetY = 0;
		currentX = this.pos.getRow();
		currentY = this.pos.getCol();
		targetX = targetPos.getRow();
		targetY = targetPos.getCol();

		// ...
		return isValid;
	}

	@Override
	public Position[] generatePseudoLegalMoves() {
		throw new UnsupportedOperationException("Not supported yet.");
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

}