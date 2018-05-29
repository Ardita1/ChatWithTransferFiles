package server;

import java.rmi.Remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import klient.ClientInterface;

public interface ServerInterface extends Remote{
	void addClient(ClientInterface client) throws RemoteException;
    public List<ClientInterface> getClient() throws RemoteException; 
}
