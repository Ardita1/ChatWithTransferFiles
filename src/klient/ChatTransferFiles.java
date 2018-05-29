package klient;

import server.ServerInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class ChatTransferFiles extends JFrame {	
	   
	   
	    private String username;
	    private  ServerInterface server;
	    private  Client client;
	    
	    private JScrollPane paneli1; 
	    private JScrollPane paneli2;
	    private JScrollPane paneli3;
	    
	    private JTextArea message;
	    private JTextArea onlineClient; 
	    private JTextArea CONSOLE;
	    private JButton sendButton;
	    private JButton onlineButton;
	    private JButton attachButton;
  
	    
	   public ChatTransferFiles(String username, ServerInterface serveri) {
		   chatTransferFilesh(); 
	       this.username = username; 
	       this.server = serveri;  
	        try {
	            client = new Client(username,CONSOLE,onlineClient); 
	            serveri.addClient(client);//Shtohet klienti nga serveri 
	        }catch (RemoteException ex) {
	            Logger.getLogger(ChatTransferFiles.class.getName()).log(Level.SEVERE, null, ex); 
	        }
	    }

	
	    private void chatTransferFilesh(){

	    	 CONSOLE = new javax.swing.JTextArea(); 
		     message = new javax.swing.JTextArea(); 
		     onlineClient=new javax.swing.JTextArea();
	    	
	         paneli1 = new javax.swing.JScrollPane();
	         paneli2 = new javax.swing.JScrollPane();
	         paneli3 = new javax.swing.JScrollPane(); 
	        
	         sendButton = new javax.swing.JButton(); 
	         onlineButton = new javax.swing.JButton();
	         attachButton = new javax.swing.JButton();

	         sendButton.setBackground(new Color(224,224,250));
	         onlineButton.setBackground(new Color(224,224,250));
	         attachButton.setBackground(new Color(224,224,250));
	       
	         CONSOLE.setEditable(false); 
	         CONSOLE.setColumns(40);
	         CONSOLE.setRows(10); 
	         paneli1.setViewportView(CONSOLE);

	         message.setColumns(20);  
	         message.setRows(4);  
	         paneli2.setViewportView(message); 
	        
	         onlineClient.setEditable(false);
	         onlineClient.setColumns(10);  
	         onlineClient.setRows(10);  
	         paneli3.setViewportView(onlineClient);
	        
	         
	        sendButton.setText("Send Message");  
	        sendButton.addActionListener(new java.awt.event.ActionListener() { 
	            public void actionPerformed(java.awt.event.ActionEvent evt) { 
	            	send(); //Pas shtypjes se butonit Send Message, thirret metoda send
	            	message.setText("");
	            }
	        });
	        
	        message.addKeyListener(new KeyAdapter(){ 
	            public void keyPressed(KeyEvent evt) { 
	            	if(evt.getKeyCode() == KeyEvent.VK_ENTER)
	                {
	            		send();
		            	message.setText("");
	                }
	            }
	        });
	        
	        
	        onlineButton.setText("Online");
	        onlineButton.addActionListener(new java.awt.event.ActionListener() { 
	            public void actionPerformed(java.awt.event.ActionEvent evt) { 
	            	onlineClient.setText("");
	                Online(evt); 
	            }
	        });
	      
	        
	        attachButton.setText("Attach File"); 
	        attachButton.addActionListener(new java.awt.event.ActionListener() { 
	            public void actionPerformed(java.awt.event.ActionEvent evt) { 
	                try { 								
	                	attach(evt);//Pas shtypjes se butonit Attach File, thirret metoda attach	
					} catch (IOException e) {
						e.printStackTrace();			
					}
	            }
	        });
	        
	      
	        JLabel label1=new JLabel();
            label1.setText("Console");
            
	        JLabel label2=new JLabel();
	        label2.setText("Message:");

	       
	        JPanel panel4 = new JPanel(new FlowLayout());		
	       	panel4.add(label2,BorderLayout.WEST);
	        panel4.add(paneli2);
	        panel4.add(sendButton, BorderLayout.WEST);
	        panel4.add(attachButton, BorderLayout.CENTER);
	        panel4.add(onlineButton,BorderLayout.EAST);
	        panel4.setBackground(new Color(239, 255, 219));
	     
	        
	        JPanel panel5 = new JPanel(new BorderLayout());
	        panel5.add(label1,BorderLayout.LINE_START);
	        panel5.add(paneli1,BorderLayout.CENTER);
	        panel5.add(paneli3,BorderLayout.LINE_END);
	        panel5.setBackground(new Color(239, 255, 219));
	        
		    Container cp=getContentPane();
	        cp.setLayout(new GridLayout(0,1));
	        cp.add(panel5);
	        cp.add(panel4);
	        
	        JFrame frame = new JFrame();
	        frame.setTitle(username + "'s console");
	        cp.setBackground(new Color(239, 255, 219));
	        pack(); 
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setResizable(false); 

	    }
	    
	    
	    
	    private void send() {                        
	    	if(message.getText().trim().length()>0){ 
	            try {
	                List<ClientInterface> clients = server.getClient(); 
	                
	                	for (ClientInterface CLIENT : clients)
	                	{
	                		CLIENT.sendMessage(username, message.getText()); 
	                	}
	                }catch (RemoteException ex) { 
	                JOptionPane.showMessageDialog(this, ex);
	                Logger.getLogger(ChatTransferFiles.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }}
	    
	 
	    private void Online(ActionEvent evt) {     
	        	 try {
	                 List<ClientInterface> client = server.getClient(); 
	                 String online="";
	                 	for (ClientInterface CLIENT : client) 
	                 	{
	                 		online=online+CLIENT.getName()+"\n"; 
	                 	}
	                 onlineClient.setText(online);
	                }catch (RemoteException ex) {  
	                 JOptionPane.showMessageDialog(this, ex);
	                 Logger.getLogger(ChatTransferFiles.class.getName()).log(Level.SEVERE, null, ex);
	             }
	        	}
	        
	     
	    private void attach(java.awt.event.ActionEvent event) throws IOException{
	    	  JFileChooser selectFile = new JFileChooser(); 
	    	  selectFile.showOpenDialog(null); 
	    	  File File = selectFile.getSelectedFile();
	          String name = File.getName(); 
	          Path locationOfFile = Paths.get( File.getAbsolutePath());
	          byte[] data = Files.readAllBytes(locationOfFile);//Behet ruajta e file-s ne varg te byteve
	    		
	    	List<ClientInterface> client = server.getClient(); 
	             for  (ClientInterface CLIENT: client)  
	              {
	            	CLIENT.sendFile(name, data);//dergon fajllin
	        	    CLIENT.sendMessage(username," "+File.getName());//Shfaqet message me emrin e file-s
	             }
	    }
}
