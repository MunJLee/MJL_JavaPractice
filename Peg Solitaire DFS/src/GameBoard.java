


public class GameBoard {
	

	//presets//
	public int[][] solitairePattern(){
		
		int[][] boardPattern = {
				{2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 1, 1, 1, 2, 2, 2},
				{2, 2, 2, 1, 1, 1, 2, 2, 2},
				{2, 1, 1, 1, 1, 1, 1, 1, 2},
				{2, 1, 1, 1, 0, 1, 1, 1, 2},
				{2, 1, 1, 1, 1, 1, 1, 1, 2},
				{2, 2, 2, 1, 1, 1, 2, 2, 2},
				{2, 2, 2, 1, 1, 1, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2},
		};
			
		return boardPattern;
	}

	
	public void displayBoard(int[][] boardGiven) {
		
		
		for (int x = 0; x < boardGiven.length; x++) {
			for (int y = 0; y < boardGiven[x].length; y++) {
				
				if(boardGiven[x][y] == 0) { System.out.print("O "); }
				else if (boardGiven[x][y] == 1) { System.out.print("# "); }
				else { System.out.print("+ "); }
			}
			
			System.out.println();
		}
		
	}
	
}
