package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import klient.ClientInterface;

public class Server extends UnicastRemoteObject implements ServerInterface{

	private final ArrayList<ClientInterface> clients = new ArrayList<>();
	
	public Server() throws RemoteException{	
	}
	
   @Override
	public void addClient(ClientInterface client)throws RemoteException {
	   	    this.clients.add(client);
	}

	@Override
	public List<ClientInterface> getClient() throws RemoteException {
		return clients;
	}
}
