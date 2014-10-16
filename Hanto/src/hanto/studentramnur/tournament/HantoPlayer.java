/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentramnur.tournament;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.AbstractHantoGame;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.HantoBoardCoordinate;
import hanto.studentramnur.common.HantoGameFactory;
import hanto.studentramnur.common.HantoPlayerStatistics;
import hanto.studentramnur.common.PieceCoordinatePair;
import hanto.studentramnur.common.move.Move;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * The class that implements logic for the player.
 * 
 * @author Batyr
 *
 */
public class HantoPlayer implements HantoGamePlayer {
	private AbstractHantoGame game;
	private HantoPlayerColor myColor;
	private HantoPlayerColor opponentColor;
	private HantoMoveRecord opponentsMove;
	private HantoMoveRecord result;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst) {
		this.myColor = myColor;
		opponentColor = (myColor == HantoPlayerColor.BLUE) ? HantoPlayerColor.RED : HantoPlayerColor.BLUE;
		 
		if (doIMoveFirst) {
			game = (AbstractHantoGame) HantoGameFactory.getInstance().makeHantoGame(version, myColor);
		} else {
			game = (AbstractHantoGame)HantoGameFactory.getInstance().makeHantoGame(version, opponentColor);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		this.opponentsMove = opponentsMove;
		
		try {
			MoveResult moveResult = makeOpponentsMove();
			
			if (moveResult == MoveResult.OK) {
				makeMyMove();
			} else {
				System.out.println("WARNING! The tournament runner tried to ask for another move when the game was already over!");
			}
		} catch(HantoException e) {
			System.out.println("WARNING! Caught exception: " + e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * Makes the opponents move in the current game.
	 * 
	 * @param opponentsMove
	 * @throws HantoException
	 */
	private MoveResult makeOpponentsMove() throws HantoException {
		MoveResult moveResult;
		
		if (opponentsMove != null) {
			moveResult = game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
		}
		else {
			moveResult = MoveResult.OK;
		}
		
		return moveResult;
	}
	
	/**
	 * Makes the next move of the current player.
	 * 
	 * @throws HantoException
	 */
	private void makeMyMove() throws HantoException {
		HantoPlayerStatistics currentPlayer = game.getPlayerStats(myColor);
		HantoBoard board = game.getBoard();
		
		if (opponentsMove == null) {
			game.makeMove(HantoPieceType.CRAB, null, new HantoBoardCoordinate(0, 0));
			result = new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoBoardCoordinate(0, 0));
		} else {
			Collection<Move> allAvailableMoves = Move.getAllAvailableMoves(currentPlayer, board);
			
			if (allAvailableMoves.size() == 0) { //if we don't have any moves available, then forfeit
				game.makeMove(null, null, null);
				result = new HantoMoveRecord(null, null, null);
			} else {
				Move selectedMove = selectMove(allAvailableMoves, currentPlayer, board); //involves move selection techniques (AI)
				
				if (selectedMove == null) {
					game.makeMove(null, null, null);
					result = new HantoMoveRecord(null, null, null);
				} else {
					game.makeMove(selectedMove.getPieceType(), selectedMove.getFrom(), selectedMove.getTo());								
					result = new HantoMoveRecord(selectedMove.getPieceType(), selectedMove.getFrom(), selectedMove.getTo());
				}
			}
		}
	}

	/**
	 * Selects the appropriate move based on created set of rules (AI).
	 * 
	 * @param allAvailableMoves the list of all available moves
	 * @param currentPlayer the current player statistics
	 * @param board the board
	 * @return the next move selected from the list
	 */
	private Move selectMove(Collection<Move> allAvailableMoves, HantoPlayerStatistics currentPlayer, HantoBoard board) {
		Move resultingMove = null;
		
		HantoCoordinate opponentsButterflyCoor = getButterflyCoor(board, opponentColor);
		HantoCoordinate myButterflyCoor = getButterflyCoor(board, myColor);

		if (!currentPlayer.hasPlacedButterfly()) { //if we haven't placed the butterfly, then count the number of turns
				if (currentPlayer.getMovesMade() >= 2) {//place butterfly anywhere on the board
					for (Move move : getAvailableMovesForButterflyAt(null, allAvailableMoves)) {
					if (move.getPieceType() == HantoPieceType.BUTTERFLY) {
						resultingMove = move;
					}
				}
				
				if (resultingMove == null) {
					return null; //we cannot place butterfly, we will forfeit implicitly (null is considered as forfeit by the upper layer function)
				}
			}
		}
		else {
			if (board.getOccupiedNeighbors(myButterflyCoor).size() > 2) {
				Collection<Move> movesForButterfly = getAvailableMovesForButterflyAt(myButterflyCoor, allAvailableMoves);
				if (movesForButterfly.size() > 0) { //if the butterfly can move, then move it to the coordinate with the least number of neighbors
					int leastNumberOfNeighbors = board.getOccupiedNeighbors(myButterflyCoor).size();
					
					int availableMoveNeighbors;
					for (Move move : movesForButterfly) {
						availableMoveNeighbors = board.getOccupiedNeighbors(move.getTo()).size();
						if (availableMoveNeighbors < leastNumberOfNeighbors) {
							leastNumberOfNeighbors = availableMoveNeighbors;
							resultingMove = move;
						}
					}
				}
			}
		}
		
		if (resultingMove == null) {
			int positiveScore; //the distance from the my butterfly to the next coordinate
			int negativeScore; //the distance from the opponent's butterfly to the next coordinate
			
			int currentScore; //calculated as positiveScore - negativeScore
			int previousScore; //the same as currentScore, but for the from coordinate
			
			int largestScore = -100000;//set the last largest score to large negative value
			
			for (Move move : allAvailableMoves) { //iterate through all the moves
				if (move.getPieceType() != HantoPieceType.BUTTERFLY || move.getFrom() == null) { //we don't try to move butterfly in this section (however, we can add it)
					positiveScore = (myButterflyCoor == null) ? 0 : board.getCellDistance(myButterflyCoor, move.getTo());
					negativeScore = (opponentsButterflyCoor == null) ? 0 : board.getCellDistance(opponentsButterflyCoor, move.getTo());
					
					currentScore = positiveScore - negativeScore;
					if (currentScore > largestScore) {
						if(move.getFrom() == null) { //if we add, then there is no way to calculate score for from coordinate
							resultingMove = move;
							largestScore = currentScore;
						} else {
							positiveScore = (myButterflyCoor == null) ? 0 : board.getCellDistance(myButterflyCoor, move.getFrom());
							negativeScore = (opponentsButterflyCoor == null) ? 0 : board.getCellDistance(opponentsButterflyCoor, move.getFrom());
							
							previousScore = positiveScore - negativeScore;
							
							if (previousScore < currentScore) { //make sure that we don't make moves that reduces the score (if the score for from coordinate is larger than the one for to coordinate)
								resultingMove = move;
								largestScore = currentScore;
							}
						}
					}
				}
			}
			
			if (resultingMove == null) { // if by the end of this operation the move was not selected, then select the random move from the list
				Random randomGenerator = new Random();
				
				resultingMove = (Move)allAvailableMoves.toArray()[randomGenerator.nextInt(allAvailableMoves.size())];
			}
		} 
		
		return resultingMove;
	}
	
	/**
	 * Filters the available moves list so that it only contains available moves for the piece at the specific coordinate.
	 * 
	 * @param coordinate the coordinate
	 * @param allAvailableMoves the list of all available moves
	 * @return the filtered list of available moves
	 */
	private Collection<Move> getAvailableMovesForButterflyAt(HantoCoordinate coordinate, Collection<Move> allAvailableMoves) {
		Collection<Move> availableMovesForPiece = new ArrayList<Move>();
		
		for (Move move : allAvailableMoves) {
			if (move.getFrom() != null) {
				if (move.getFrom().equals(coordinate)) {
					availableMovesForPiece.add(move);
				}
			}
			else {
				if (move.getPieceType() == HantoPieceType.BUTTERFLY) {
					availableMovesForPiece.add(move);
				}
			}
		}
		
		return availableMovesForPiece;
	}

	/**
	 * Gets the coordinate of the butterfly with the specified color. 
	 * 
	 * @param board the board
	 * @param color the color
	 * @return the coordinate of the butterfly with the specified color. Returns null if the butterfly haven't been placed yet.
	 */
	private HantoCoordinate getButterflyCoor(HantoBoard board, HantoPlayerColor color) {
		HantoCoordinate butterflyCoor = null;
		
		Collection<PieceCoordinatePair> pieceLocationPairs = board.getPlayerPieces(color);
		for (PieceCoordinatePair plp : pieceLocationPairs) {
			if (plp.getPiece().getType() == HantoPieceType.BUTTERFLY) {
				butterflyCoor = plp.getLocation();
				break;
			}
		}
		
		return butterflyCoor;
	}
}
