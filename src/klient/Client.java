package klient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Client extends UnicastRemoteObject implements ClientInterface{

	private final JTextArea message;
	private final JTextArea online;
    private final String name;
  
	
	public Client(String name,JTextArea message,JTextArea online) throws RemoteException{
	this.message=message;
	this.online=online;
    this.name=name;
	}
	
	  @Override
	public void sendMessage(String username,String Message)throws RemoteException{
		// TODO Auto-generated method stub
		String messages=message.getText();
		messages +=  username+":"+Message+"\n";
		message.setText(messages);
	}
	
	@Override
	public void sendFile(String file, byte[] data)throws RemoteException {
		try{// TODO Auto-generated method stub
			File directorium =new File("C:\\" + file);
		    FileOutputStream copyOfFile=new FileOutputStream(directorium+"\\"+file);
			copyOfFile.write(data);
			copyOfFile.close();				
		}catch(IOException ex){
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return name;
	}
}
