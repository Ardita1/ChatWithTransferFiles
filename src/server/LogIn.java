package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import klient.ChatTransferFiles;
import klient.WindowForLogIn;

public class LogIn extends JFrame{
	
	public static JButton activeServer;
	public static JButton LogIn;
    private Container c;
    private JLabel actualPort;
    public static int porti;
    public static  JLabel username;
    public static JTextField user;
    public static  JLabel ip;
    public static  JTextField IP;

 public LogIn() {   
	 activeServer();	 
 }
 

 private void activeServer(){

	 activeServer = new JButton();
     LogIn = new JButton();
     actualPort = new JLabel();
     ip = new JLabel();
 	 IP = new JTextField();
     username = new JLabel();
     user = new JTextField();
 	 
     
   
     LogIn.setText("Log In"); 
     LogIn.setFont(new Font("Calibri",Font.PLAIN,20));
     LogIn.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
             logIn(evt); //metoda logIn thirret pas shtypjes se butonit Kycu
         }
     });

    
    porti=6006;
	actualPort.setText("  Actual Port: " + porti );
	actualPort.setFont(new Font("Calibri",Font.PLAIN,20));
	

	
	   activeServer.setText("Active Server");
	   activeServer.setFont(new Font("Calibri",Font.PLAIN,20));
	     activeServer.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(java.awt.event.ActionEvent evt) {
	             launchServer(evt); //metoda LaunchServerin thirret pas shtypjes se butonit Aktivizo Serverin
	         }
	     });
	 
	 
	      c=getContentPane();
	      c.setLayout(new GridLayout(0,1));
	      c.add(actualPort);
	      c.add(activeServer);
	      c.add(LogIn);
	      c.setBackground(new Color(239, 255, 219));
	      activeServer.setBackground(new Color(224,224,250));
          LogIn.setBackground(new Color(224,224,250));
	      pack();
	      setLocationRelativeTo(null);  
	      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 }

 

 private void launchServer(java.awt.event.ActionEvent evt) {
     Registry register; 
     try {
    	 //lidhja me server
    	  register = LocateRegistry.createRegistry(porti); 
          register.rebind("Connection", new Server()); //lidhet me klasen Server
         activeServer.setEnabled(false); 
         LogIn.setEnabled(false); 
         JOptionPane.showMessageDialog(null, "  Server is activated! ");
         }catch (RemoteException ex) { 
           JOptionPane.showMessageDialog(null, ex, "Error, can not connect!", JOptionPane.ERROR_MESSAGE);
           Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
     }
 }

 private void logIn(java.awt.event.ActionEvent evt){          
          setVisible(false); 
	      System.out.println("u shtyp");
          new WindowForLogIn().setVisible(true);;       
 }
 
  public static void main(String args[]) throws RemoteException, AlreadyBoundException {
          java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() { 
              new LogIn().setVisible(true); 
          }
      });
  }
}