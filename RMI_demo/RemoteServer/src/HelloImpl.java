import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


public class HelloImpl extends UnicastRemoteObject implements HelloInterface {
	//Complete this class
	  public HelloImpl() throws RemoteException {
	    super( );
	  }
	  
	  public char[] board(char[] a) throws RemoteException {
	      //Complete this method
		  
		  
		  //setup
		  TicTacToeLogic serverLogic = new TicTacToeLogic();
		  Scanner keyboard = new Scanner(System.in);
		  
		  
		  //display what came over		  
		  serverLogic.drawBoard(a);
			
		  
		  //ask to select a number
		  boolean properInputObtained;
				
		  do { 
			  
			  properInputObtained = false;			 
			  System.out.print("Enter a cell number: "); 
					
			  if (keyboard.hasNextInt() == false) {
				  System.out.println("WARNING: NOT A NUMBER. PLEASE TRY AGAIN.");
				  keyboard.next();
				  
			  }										
			  else {
				  
				  int userInput = keyboard.nextInt();
				  
				  if ((userInput < 0) || (userInput > 8))
				  	{System.out.println("WARNING: OUT OF RANGE [0~8]. PLEASE TRY AGAIN."); }	
				  
				  else if ((a[userInput] == 'X') || (a[userInput] == 'O')) 					
				  	{ System.out.println("WARNING: ALREADY CHOSEN. PLEASE TRY AGAIN."); }
				  
				  else { 
					  properInputObtained = true;
					  a[userInput] = 'O'; 
				  }
			  }
			  
		  }while(properInputObtained == false);
		  
		  //No Scanner.close() will be used here at this time
		  //because it will close the stream for good and block the input beyond 1st cycle of server operation
		  	  
		  
		  //send the update back
		  return a;

	  }   

}
