import java.rmi.*;
import java.util.*;


public class HelloClient {

	public static void main(String[] args) {
		char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8'};
		//Complete this class
		
		try {

			
			//setup
			int port = 16790;
			String host = "localhost";
			String registryURL = "rmi://" + host + ":" + port + "/hello";
			boolean gameOver = false;
			
			HelloInterface h = (HelloInterface)Naming.lookup(registryURL); 
			TicTacToeLogic gameLogic = new TicTacToeLogic();
			Scanner keyboard = new Scanner(System.in);
			System.out.println();
			
		
			do {
						
				//display the current client board
				gameLogic.drawBoard(a);
							
				
				//ask to select a number (and repeat until you do)
				boolean properInputObtained;
				
				do { 
					
					properInputObtained = false;			 
					System.out.print("Enter a cell number: "); 
					
					if (keyboard.hasNextInt() == false) {
						System.out.println("WARNING: NOT A NUMBER. PLEASE TRY AGAIN.");
						keyboard.next();  //consume the current value and be ready for next value//
						
					}										
					else {
						
						int userInput = keyboard.nextInt();
						
						if ((userInput < 0) || (userInput > 8))			
							{System.out.println("WARNING: OUT OF RANGE [0~8]. PLEASE TRY AGAIN."); }	
							
						else if ((a[userInput] == 'X') || (a[userInput] == 'O')) 					
							{ System.out.println("WARNING: ALREADY CHOSEN. PLEASE TRY AGAIN."); }
							
						else { 
							properInputObtained = true;
							a[userInput] = 'X'; 
						}			
						
					}
					
				}while(properInputObtained == false);

		
				//check if the client has won
				if(gameLogic.win(a) == true) {
					//client is winner//
					System.out.println("Winner is: X");
					gameLogic.drawBoard(a);
					gameOver = true;
					
				}
				else if (gameLogic.tie(a) == true) {
					//all spaces are marked and there is still no winner//
					gameLogic.drawBoard(a);
					System.out.println("It is a tie");
					gameOver = true;
					
				}
				else {
				
					//send it over to server (and update the gameboard with the server output)
					a = h.board(a);
					
					
					//check if the server is winner
					if (gameLogic.win(a) == true) {
						System.out.println("Winner is: O");
						gameOver = true;
						
					}
                    else if (gameLogic.tie(a) == true){
                        System.out.println("It is a tie");
                        gameOver = true;
                    }

					//display server output
					gameLogic.drawBoard(a);
					if (gameOver == false) { System.out.println("============="); }	//ONE ROUND OVER//
					
				}
				
			}while(gameOver == false);			
						
			System.out.println();
			keyboard.close();
			
		}
		catch (Exception e) {
			//I know catching the whole Exception is bad, 
			//but there is no reason to distinguish each exceptions at this time.
			System.out.println("ERROR: " + e);
			
		}

	}
	
}
