/**
 * 
 */
package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoPlayerStatistics;

/**
 * @author srramadan
 *
 */
public class JumpMove extends Move {

	public JumpMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.JUMP);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws HantoMoveException 
	 */
	@Override
	public boolean validate(HantoPlayerStatistics currentPlayer, HantoBoard board) throws HantoException {
		HantoCoordinate vector = board.getVector(this.getFrom(), this.getTo());
		HantoCoordinate unitVector = board.getUnitVector(vector);
		//System.out.println("Vector: "+vector.getX()+" "+vector.getY());
		//System.out.println("Unit: "+unitVector.getX()+" "+unitVector.getY());
			
		return super.validate(currentPlayer, board)
				&& board.pieceMatchesAtCell(this.getColor(), this.getPieceType(), this.getFrom())
				&& isStraightLine(unitVector)
				&& hasPiecesAlongPath(unitVector, board)
				&& canPieceBeMovedWithoutBreakingTheStructure(board);
	}

	private boolean isStraightLine(HantoCoordinate unitVector) {
		return unitVector.getX() ==0 
				|| unitVector.getY() == 0 
				|| unitVector.equals(new HantoBoardCoordinate(-1, 1)) 
				|| unitVector.equals(new HantoBoardCoordinate(1, -1));
	}
	
	private boolean hasPiecesAlongPath(HantoCoordinate unitVector, HantoBoard board) {
		HantoCoordinate currentCell = from;
		
		boolean rtn = true;
		int pathCounter = 0;

		//System.out.println("From: "+from.getX()+" "+from.getY());
		//System.out.println("To: "+to.getX()+" "+to.getY());
		
		while(!currentCell.equals(to)) {
			if (board.isCellEmpty(currentCell)) {
				rtn = false;
				break;
			}

			//System.out.println("Stuck in an infinite loop...");
			
			currentCell = new HantoBoardCoordinate(currentCell.getX() + unitVector.getX(), currentCell.getY() + unitVector.getY());
			pathCounter++;
		}
		
		if(pathCounter < 2) rtn = false;
		
		return rtn;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(HantoBoard board) {
		board.movePiece(this.getFrom(), this.getTo());
	}

}
