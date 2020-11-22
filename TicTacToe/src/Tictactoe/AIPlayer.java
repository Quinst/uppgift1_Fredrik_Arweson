package Tictactoe;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer  extends Player {
	//En påbyggnad på spelarklassen men som styr datorn
	
	public AIPlayer(String name, String symbol) {
		super(name, symbol);
		
	}
	
	public void playMove(Game game) {
		
		String[][] board = game.getBoard();
		Move bestMove = new Move(-1, -1, Integer.MIN_VALUE);
		int gridSize = board.length;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (board[i][j].equals(Game.FILLER)) {
					board[i][j] = getSymbol();
					Move move = new Move(i, j, calculateMoveCost(getSymbol(), board));
					if (move.getScore() > bestMove.getScore()) {
						bestMove = move;
					}
					board[i][j] = Game.FILLER;
					//Här har den bestämt vilket som är det bästa draget
				}
			}
		}
		game.move(bestMove.getRow(), bestMove.getColumn());
	}
	
	private int calculateMoveCost(final String symbol, String[][] board) {
		int size = board.length;
		List<Scorer> scorerList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Scorer rowScorer = new Scorer();
			Scorer colScorer = new Scorer();
			for (int j = 0; j < size; j++) {
				scoreBasedOnSymbol(symbol, board[i][j], rowScorer);
				scoreBasedOnSymbol(symbol, board[i][j], colScorer);
				//Här beräknas "kostnaden" för draget för att avgöra om det är värt det
			}
			scorerList.add(rowScorer);
			scorerList.add(colScorer);
			
		}
		
		Scorer diagonal1Scorer = new Scorer();
		Scorer diagonal2Scorer = new Scorer();
		for (int i = 0; i < size; i++) {
			scoreBasedOnSymbol(symbol, board[i][i], diagonal1Scorer);
			scoreBasedOnSymbol(symbol, board[i][size -i -1], diagonal2Scorer);
			
		}
		scorerList.add(diagonal1Scorer);
		scorerList.add(diagonal2Scorer);
		
		int score = 0;
		for (Scorer scorer : scorerList) {
			score += scorer.getScore(size);
			
		}
		
		return score;
	}
	
	private void scoreBasedOnSymbol(String symbol, String symbolToCompare, Scorer scorer) {
		if (symbol.equals(symbolToCompare))
			scorer.addWin();
		else if (Game.FILLER.equals(symbolToCompare))
			scorer.addTie();
		else
			scorer.addLoss();
	}

}
