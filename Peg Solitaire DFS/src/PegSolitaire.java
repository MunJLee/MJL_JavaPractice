
public class PegSolitaire {

	public static void main(String[] args) {
		
		//set up variables
		GameBoard boardSetting = new GameBoard();
		//GameLogic currentGame = new GameLogic(boardSetting.solitairePattern());
		GameLogic currentGame = new GameLogic(boardSetting.solitairePattern(), boardSetting);
		
		
		//display the initial board setup
		System.out.println("PEG SOLITAIRE - START");
		boardSetting.displayBoard(currentGame.getBoard());

		
		//make a recursive call to find a solution
		boolean playResult = currentGame.searchDF(currentGame.countPegs());
		
		
		//if successful, print the solution path
		//otherwise, admit failure
		if (playResult == true) {
			
			System.out.println("\nSOLUTION: ");
			currentGame.displaySolution(true);
			
		}
		else { System.out.println("\nNO SOLUTION WAS FOUND"); }
		
		System.out.println("\nPEG SOLITAIRE - ENDGAME");
		boardSetting.displayBoard(currentGame.getBoard());

	}

}
