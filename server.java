package chatting;

import java.awt.*;
import java.awt.event.*;
import java.net.*;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;
public  class server extends JFrame implements ActionListener {
																				//frame coding occur only in constructor// 	
																				//all the decoration must be global b/c if panel is local then there is no scope of panel out of the constructor
																
					JPanel p2;													// global panel of name p2 used for the division 
					JTextField t1;												//for typing area
					JButton b1;													//for click button

					 static JTextArea a1;												//for the area where the written message will be show
					
					
					static ServerSocket skt;
				    static Socket s;
				    static DataInputStream din;
				    static DataOutputStream dout;
				    

			server(){													//<-Constructor
				     
					p2=new JPanel();
					p2.setLayout(null);
						p2.setBackground(new Color(27,41,110));  				 // set the panel color two are color code
						p2.setBounds(0,0,400,60);    							//here we fixing the coordinate and size of panel
						add(p2);
						
					
																				//the image does not go directly to the frame so we need a labal
																				//put image I1 into label l1
																				
																				//JLabel l1=new JLabel(I1);
																					
					ImageIcon I1= new ImageIcon(ClassLoader.getSystemResource("chatting/icon/11 (2).png")); //Take image from disk
					Image I2= I1.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT);
					ImageIcon  I3= new ImageIcon(I2);
					JLabel l1= new JLabel(I3);
																				//we use set bound to make our own layout 
					l1.setBounds(5,15,40,30);									//first two are coordinate and another two are size
					p2.add(l1);													//add function() used to add any element to the frame
					
					l1.addMouseListener(new MouseAdapter(){							//for the mouse event we add ActionListener class which is present in java.awt.event.*; file.
						public void mouseClicked(MouseEvent ae) {				//used for the back button event
							System.exit(0);
						}
					});
					
					
					ImageIcon I4= new ImageIcon(ClassLoader.getSystemResource("chatting/icon/11 (1).png")); //Take image from disk
					Image I5= I4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
					ImageIcon  I6= new ImageIcon(I5);
					JLabel l2= new JLabel(I6);
					l2.setBounds(50,10,40,40);
					p2.add(l2);	
					
					JLabel l3= new JLabel(" ISHIKA ");	
					l3.setFont(new Font("SAN_SERIF",Font.BOLD,18)); 				 //for new font
					l3.setForeground(Color.white);
					l3.setBounds(110,10,100,20);
					p2.add(l3);
					
					
					JLabel l4= new JLabel(" Online... ");	
					l4.setFont(new Font("SAN_SERIF",Font.BOLD,10));					   
					l4.setForeground(Color.white);
					l4.setBounds(112,28,100,18);
					p2.add(l4);
					
					
					ImageIcon I7= new ImageIcon(ClassLoader.getSystemResource("chatting/icon/video.png")); 
					Image I8= I7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
					ImageIcon  I9= new ImageIcon(I8);
					JLabel l5= new JLabel(I9);
					l5.setBounds(280,14,30,30);
					p2.add(l5);	
					
					
					ImageIcon I10= new ImageIcon(ClassLoader.getSystemResource("chatting/icon/phone.png")); 
					Image I11= I10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
					ImageIcon  I12= new ImageIcon(I11);
					JLabel l6= new JLabel(I12);
					l6.setBounds(320,14,30,30);
					p2.add(l6);	
					
					
					ImageIcon I13= new ImageIcon(ClassLoader.getSystemResource("chatting/icon/3icon.png")); 
					Image I14= I13.getImage().getScaledInstance(10, 20, Image.SCALE_DEFAULT);
					ImageIcon  I15= new ImageIcon(I14);
					JLabel l7= new JLabel(I15);
					l7.setBounds(360,20,10,20);
					p2.add(l7);	
					
					
					a1 = new JTextArea();
					a1.setBounds(5,70,390,470);
					a1.setBackground(Color.GRAY);
					a1.setFont(new Font("SAN_SERIF",Font.ITALIC,14));	
					a1.setForeground(Color.WHITE);
					a1.setEditable(false);
					a1.setLineWrap(true);
					a1.setWrapStyleWord(true);
					add(a1);
					
					
					
					t1=new JTextField();
					t1.setBounds(5,550,315,40);
					t1.setFont(new Font ("SAN_SERIF",Font.ITALIC,18));
					add(t1);
					
					b1= new JButton("Send");
					b1.setFont(new Font("SAN_SERIF",Font.BOLD,12));	
					b1.setForeground(Color.WHITE);
					b1.setBackground(new Color(27,41,110));
					b1.setBounds(325,550,70,40);
					b1.addActionListener(this);										//for action listener
					add(b1);
					
					
					getContentPane().setBackground(Color.gray);
					setLayout(null);  												//we want our own layout thats why we put null for swing layout
					setSize(400,600);												//set the size of frame
					setLocation(300,100);											//Set the location of frame
																					// here are all the frames are by default setVisible(false)
					setUndecorated(true);											// setUndecorated is for remove the decoration 						
					setVisible(true);												// here are all the frames are by default setVisible(false)
																					// setVisible means they are present invisibly but they do their work
																					//by default the location of the frame is (0,0) thats why it open top left corner only 
																					// the position of set visible should be in the last
					
					}
					
					public void actionPerformed(ActionEvent ae) {
						 try{
						
						String out = t1.getText();	
			            a1.setText(a1.getText()+"\n\t\t\t"+out);
			            dout.writeUTF(out);
			            t1.setText("");
			        }catch(Exception e){
			        	e.printStackTrace();
			        }																//get text from typing field/bar
					
																			    	//put previous text+new text
					}				
					
					public static void main (String[] args) {						//main function
						new server().setVisible(true);
					 String msginput = "";
					        try{
					            skt = new ServerSocket(6000);						// 0000 is the port no.
					            while(true) {
					                s = skt.accept();
					                din = new DataInputStream(s.getInputStream());
					                dout = new DataOutputStream(s.getOutputStream());
				            
						        while (true) {
				                msginput = din.readUTF();
					        		a1.setText(a1.getText()+"\n"+msginput);
						        }
					                
					            }
					            
					        }catch(Exception e){
					        	e.printStackTrace();
					        }												//here we code for frame in constructor only because we want to open the frame directly
					}
				}
