package klient;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
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

import server.LogIn;
import server.ServerInterface;
import server.Server;
import klient.ChatTransferFiles;

public class WindowForLogIn  extends JFrame{
	
	private JButton logNow;
    private  JLabel ip;
    private  JTextField IP;
    private JButton end;
    
    
	public WindowForLogIn(){
		showWindow();
	}
	
	private void showWindow(){
	ip = new JLabel();
	IP = new JTextField();
	LogIn.username = new JLabel();
	LogIn.user = new JTextField();
	logNow = new JButton();
	end = new JButton();
	

			ip.setText("Write IP: ");
			ip.setFont(new Font("Calibri",Font.PLAIN,20));
			LogIn.username.setText("Write your name: ");
			LogIn.username.setFont(new Font("Calibri",Font.PLAIN,20));
		
			logNow.setText("Start conversation"); 
			logNow.setFont(new Font("Calibri",Font.PLAIN,20));
		    logNow.addActionListener(new java.awt.event.ActionListener() {
			    public void actionPerformed(java.awt.event.ActionEvent evt) {
			        log(evt); 
			          }
			  });
			     
			 
		     end.setText("End"); 
			 end.setFont(new Font("Calibri",Font.PLAIN,20));
		     end.addActionListener(new java.awt.event.ActionListener() {
			    public void actionPerformed(java.awt.event.ActionEvent evt) {
			       exit(evt); 
			          }
			  });
			   
		     
		     Container cp=getContentPane();
		     cp.setLayout(new GridLayout(0,2));
		     cp.add(ip);
		     cp.add(IP);
		     cp.add(LogIn.username);
		     cp.add(LogIn.user);
		     cp.add(logNow);
		     cp.add(end);
		     cp.setBackground(new Color(239, 255, 219));
		     pack();
		     setLocationRelativeTo(null);
		     setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	 
	 private void exit(java.awt.event.ActionEvent evt){
		 setVisible(false);
	 }
	
    private void log(java.awt.event.ActionEvent evt){
	     try {
          String name = LogIn.user.getText(); 
          if(name.trim().length() < 2) { 
              JOptionPane.showMessageDialog(this, "You should write a name with three or more characters!");
              return; 
       } if(name.trim().length() == 0) { 
               JOptionPane.showMessageDialog(this, "You should write a name!");
               return; 
        }
       
      
          //lidhja me server
          Registry registry = LocateRegistry.getRegistry(IP.getText(), LogIn.porti);
          System.out.println(LogIn.porti);
          ServerInterface server = (ServerInterface) registry.lookup("Connection");
       
        if (server == null) {
        JOptionPane.showMessageDialog(this, "Can not connect to server!"); 
            return;
        }
          
        setVisible(false); 
        new ChatTransferFiles(name,server).setVisible(true);
        } catch (NotBoundException ex) {
          JOptionPane.showMessageDialog(null, ex, "Error, can not connect to server!", JOptionPane.ERROR_MESSAGE);
          Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }catch (RemoteException ex) {
         JOptionPane.showMessageDialog(null, ex, "Error, can not connect to server!!", JOptionPane.ERROR_MESSAGE);
         Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }	
        System.out.println("Butoni kycu u kliku");
	 }
}

