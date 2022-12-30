
public class TicTacToeLogic {
	
	public boolean win(char[] boardGiven) {
		
		boolean checkResult = false;
		
		//check rows
		if ((boardGiven[0] == boardGiven[1]) & (boardGiven[1] == boardGiven[2])) { checkResult = true; } 
		if ((boardGiven[3] == boardGiven[4]) & (boardGiven[4] == boardGiven[5])) { checkResult = true; } 
		if ((boardGiven[6] == boardGiven[7]) & (boardGiven[7] == boardGiven[8])) { checkResult = true; } 
		
		//check columns
		if ((boardGiven[0] == boardGiven[3]) & (boardGiven[3] == boardGiven[6])) { checkResult = true; } 
		if ((boardGiven[1] == boardGiven[4]) & (boardGiven[4] == boardGiven[7])) { checkResult = true; } 
		if ((boardGiven[2] == boardGiven[5]) & (boardGiven[5] == boardGiven[8])) { checkResult = true; } 
		
		//check diagonals
		if ((boardGiven[0] == boardGiven[4]) & (boardGiven[4] == boardGiven[8])) { checkResult = true; } 
		if ((boardGiven[2] == boardGiven[4]) & (boardGiven[4] == boardGiven[6])) { checkResult = true; } 
	
		return checkResult;
	}
	
	
	public boolean tie(char[] boardGiven) {
		
		boolean checkResult = true;
		
		//check if all fields are filled
		for (int i = 0; i < 9; i++) {		
			if (boardGiven[i] == Character.forDigit(i, 10)) { checkResult = false; }
	
		}
		
		return checkResult;
	}

	
	public void drawBoard(char[] boardGiven) {
		
		for(int i = 0; i < 9; i++) {
			
			if((i+1) % 3 == 0) {  System.out.println(boardGiven[i]); }
			else { System.out.print(boardGiven[i] + " "); }
		}
		
	}
	
}
