import java.util.Stack;


public class GameLogic {
	
	private int[][] board;
	private GameBoard boardDisplay;
	
	private Stack<MoveRecord> solutionStack;


	public GameLogic(int[][] startingSetup) {
		
		board = startingSetup;
		solutionStack = new Stack<MoveRecord>();
		
	}
	
	
	public GameLogic(int[][] startingSetup, GameBoard displayHelper) {
		
		board = startingSetup;
		solutionStack = new Stack<MoveRecord>();
		
		boardDisplay = displayHelper;
	}
	
	
	public int[][] getBoard(){ return board; }
	

	public int countPegs() {
		//make tally of all pegs on the board//

		int totalPegs = 0;
		
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				//if(board[x][y] == pegState.valueOf("OCCUPIED").ordinal()) { totalPegs++; }
				if(board[x][y] == PegState.OCCUPIED.ordinal()) { totalPegs++; }
				
			}		
		}
		
		return totalPegs;
	}
	
	
	public boolean searchDF(int pegsGiven) {
		//recursive depth-first search//
		
		
		//stopping condition: if only one peg left and it's in the center, then the search is over
		if (pegsGiven == 1) {
			
			//if (board[4][4] == pegState.valueOf("OCCUPIED").ordinal()) { return true; }
			if (board[4][4] == PegState.OCCUPIED.ordinal()) { return true; }
			else { return false; }
				
		}

	
		//for each x-y coordinates
		//if there exists a peg, then check for all four direction whether jump can be made,
		//by checking: 1) if there is a peg next to it, 2) there a free space one after.
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				
				if (board[x][y] == PegState.OCCUPIED.ordinal()) { 
					
					for (Direction dir: Direction.values()) {
						
						int xmod = dir.getXMod();
						int ymod = dir.getYMod();
						
						int adjacentX = x + xmod;
						int adjacentY = y + ymod;
						
						if(board[adjacentX][adjacentY] == PegState.OCCUPIED.ordinal()) {
							
							int destinationX = adjacentX + xmod;
							int destinationY = adjacentY + ymod;
							
							if (board[destinationX][destinationY] == PegState.FREE.ordinal()) {
								
								//if the above conditions are satisfied,
								//then make a move, decrease the number of pegs, push its movement record into stack, and make a recursive call.
								//later, if a solution is found downstream, then chain it back up.
								board[x][y] = PegState.FREE.ordinal();
								board[adjacentX][adjacentY] = PegState.FREE.ordinal();
								board[destinationX][destinationY] = PegState.OCCUPIED.ordinal();								
								pegsGiven--;
								
								solutionStack.push(new MoveRecord(board, x, y, dir));
								
								if(searchDF(pegsGiven)) { return true; }
								
								
								//if no solution is found downstream, then this path is no good. 
								//so undo the move, restore peg number and location settings, remove the record from stack,
								//then continue with next search iteration in this level
								board[x][y] = PegState.OCCUPIED.ordinal();
								board[adjacentX][adjacentY] = PegState.OCCUPIED.ordinal();
								board[destinationX][destinationY] = PegState.FREE.ordinal();	
								pegsGiven++;
								
								solutionStack.pop();	
								
							}		
						}
					}
				}
			}		
		}
		
		
		return false;  //when nothing left to search//
	}
	
	
	public void displaySolution(boolean verboseMode) {
			
		for(int i = 0; i < solutionStack.size(); i++) {
			
			MoveRecord step = solutionStack.get(i);
			int pegX = step.getX();
			int pegY = step.getY();
			String toWhere = step.getMoved().getColloquial();
			
			if(verboseMode == false) {
				System.out.print(i + ": Peg(" + pegX + ", " + pegY + ") moves " + toWhere + "; ");
				if ((i + 1) % 3 == 0) { System.out.println(); }
				
			}
			else {
				System.out.println(i + ": Peg(" + pegX + ", " + pegY + ") moves " + toWhere + "; ");
				boardDisplay.displayBoard(step.getBoardState());
				System.out.println("------------------------------");
			}
		}		
		
		if(verboseMode == false) { System.out.println(); }
	}
	
	
	enum PegState { FREE, OCCUPIED, WALL }
	

	enum Direction { 
		NORTH(-1, 0, "Up"), 
		EAST(0, 1, "Right"), 
		SOUTH(1, 0, "Down"), 
		WEST(0, -1, "Left");
		
		
		private final int x_modifier;
		private final int y_modifier;
		private final String colloquial; //common description//
		
		Direction(int x_modifier, int y_modifier, String colloquial){
			this.x_modifier = x_modifier;
			this.y_modifier = y_modifier;
			this.colloquial = colloquial;			
		}
		
		//Not going to map (= caching the lookup values) at this time//
		
		
		int getXMod() { return x_modifier; }
		
		int getYMod() { return y_modifier; }
		
		String getColloquial() { return colloquial; }
		
	}
	

	private class MoveRecord{
		
		private int[][] boardState;
		private int x; //peg x-coordinate
		private int y; //peg y-coordinate
		private Direction moved;
		
		public MoveRecord(int[][] boardState, int x, int y, Direction moved) {
			
			//this.boardState = boardState;
			
			this.boardState = new int[boardState.length][boardState[0].length];
			
			for (int i = 0; i < boardState.length; i++) {
				for (int j = 0; j < boardState[i].length; j++) {
					
					this.boardState[i][j] = boardState[i][j];
				}
			}
			
			
			this.x = x;
			this.y = y;
			this.moved = moved;
		}
		
		public int[][] getBoardState() { return boardState; }
		
		public int getX() { return x; }
		
		public int getY() { return y; }
		
		public Direction getMoved() { return moved; }
		
	}


}
