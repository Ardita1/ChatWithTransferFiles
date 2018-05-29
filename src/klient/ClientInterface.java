package klient;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote{
	
	public void sendFile(String file, byte[] data)throws RemoteException;
	public void sendMessage(String username, String Message)throws RemoteException;
	public String getName()throws RemoteException;
	
}
