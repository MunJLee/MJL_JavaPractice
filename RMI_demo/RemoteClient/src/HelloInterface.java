import java.rmi.*;


public interface HelloInterface extends Remote {
	
	public char[] board(char[] a) throws RemoteException;

}
