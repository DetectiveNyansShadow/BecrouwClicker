package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class Main 
{
	JLabel compterLabel, parSecLabel;
	JButton bouton1,bouton2,bouton3,bouton4;
	double becrouwCompteur; 
	int timerSpeed, curseurNombre, curseurPrix, natsukiNombre, natsukiPrix;
	double parSeconde;
	boolean timerOn, natsukiUnlocked;
	Font font1, font2;
	BecrouwHandler bHandler = new BecrouwHandler();
	Timer time;
	JTextArea descriptionTexte;
	MouseHandler mHandler= new MouseHandler();
	
    public static void main(String[] args) 
    {
 
    	new Main();

    }
    
    public Main()
    {

    	timerOn = false;
    	parSeconde = 0;
    	becrouwCompteur = 300;
    	curseurNombre=0;
    	curseurPrix=10;
    	natsukiNombre=0;
    	natsukiPrix=100;
    	natsukiUnlocked=false;
    	createFont();
    	createUI();
    }
    
    public void createFont() {
    	font1 = new Font("Comic Sans MS", Font.PLAIN,32);
    	font2 = new Font("Comic Sans MS", Font.PLAIN,15);
    }
    
    public void createUI()
    {
    	JFrame window = new JFrame();
    	window.setSize(800,600);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.getContentPane().setBackground(Color.black);
    	window.setLayout(null);
    	
    	JPanel becrouwPanel = new JPanel();
    	becrouwPanel.setBounds(100,220,200,200);
    	becrouwPanel.setBackground(Color.black);
    	window.add(becrouwPanel);
    	
    	ImageIcon renard = new ImageIcon(getClass().getClassLoader().getResource("renard.png"));
    	
    	JButton becrouwButton = new JButton();
    	becrouwButton.setBackground(Color.black);
    	becrouwButton.setFocusPainted(false);
    	becrouwButton.setBorder(null);
    	becrouwButton.setIcon(renard);
    	becrouwButton.addActionListener(bHandler);
    	becrouwButton.setActionCommand("becrouw"); // En lien avec public class BecrouwHandler implements ActionListener case becrouw
    	becrouwPanel.add(becrouwButton);
    	
    	JPanel nombrePanel = new JPanel();
    	nombrePanel.setBounds(100,100,200,100);
    	nombrePanel.setBackground(Color.black);
    	nombrePanel.setLayout(new GridLayout(2,1));
    	window.add(nombrePanel);
    	
    	compterLabel =new JLabel(becrouwCompteur+ " BeCrouws");
    	compterLabel.setForeground(Color.white);
    	compterLabel.setFont(font1);
    	nombrePanel.add(compterLabel);
    	
    	parSecLabel= new JLabel();
    	parSecLabel.setForeground(Color.white);
    	compterLabel.setFont(font2);
    	nombrePanel.add(parSecLabel);
    	
    	JPanel fournisseurPanel = new JPanel();
    	fournisseurPanel.setBounds(500, 170, 250, 250);
    	fournisseurPanel.setBackground(Color.black);
    	fournisseurPanel.setLayout(new GridLayout(4,1));
    	window.add(fournisseurPanel);
    	
    	bouton1 = new JButton("Goldboy");
    	bouton1.setFont(font1);
    	bouton1.setFocusPainted(false);
    	bouton1.addActionListener(bHandler);
    	bouton1.setActionCommand("Cursor");
    	bouton1.addMouseListener(mHandler);
    	fournisseurPanel.add(bouton1);
    	bouton2 = new JButton("?");
    	bouton2.setFont(font1);
    	bouton2.setFocusPainted(false);
    	bouton2.addActionListener(bHandler);
    	bouton2.setActionCommand("Natsuki");
    	bouton2.addMouseListener(mHandler);
    	fournisseurPanel.add(bouton2);
    	bouton3 = new JButton("A venir");
    	bouton3.setFont(font1);
    	bouton3.setFocusPainted(false);
    	bouton3.addActionListener(bHandler);
    	bouton3.setActionCommand("");
    	bouton3.addMouseListener(mHandler);
    	fournisseurPanel.add(bouton3);
    	bouton4 = new JButton("A venir");
    	bouton4.setFont(font1);
    	bouton4.setFocusPainted(false);
    	bouton4.addActionListener(bHandler);
    	bouton4.setActionCommand("");
    	bouton4.addMouseListener(mHandler);
    	fournisseurPanel.add(bouton4);
    	
    	JPanel description = new JPanel();
    	description.setBounds(500, 70, 250, 150);
    	description.setBackground(Color.black);
    	window.add(description);
    	
    	descriptionTexte = new JTextArea();
    	descriptionTexte.setBounds(500, 70, 250, 150);
    	descriptionTexte.setForeground(Color.white);
    	descriptionTexte.setBackground(Color.black);
    	descriptionTexte.setFont(font2);
    	descriptionTexte.setLineWrap(true);
    	descriptionTexte.setWrapStyleWord(true);
    	descriptionTexte.setEditable(false);
    	description.add(descriptionTexte);    	
    
    	
    	
    	window.setVisible(true);
    }
    
    public void setTimer()
    {
    	time = new Timer(timerSpeed, new ActionListener() 
    	{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				becrouwCompteur++;	
				compterLabel.setText(becrouwCompteur+ " BeCrouws");
				
				if (natsukiUnlocked==false) {
					if(becrouwCompteur>=100) {
						natsukiUnlocked=true;
						bouton2.setText(natsukiNombre+" Natsuki");
						
					}
				}
					
			}
    	});
    }
    
    public void timerUpdate() {
    	
    	if (timerOn==false)
    	{
    		timerOn= true;
    	}
    	else if (timerOn==true)
    	{
    		time.stop();
    	}
    	double speed = 1/parSeconde*1000;
    	timerSpeed = (int)Math.round(speed);
    	String texte;
    	texte = String.format("BeCrouws par seconde: %2.1f", parSeconde);
    	parSecLabel.setText(texte);
    	
    	setTimer();
    	time.start();
    }
    
    
    public class BecrouwHandler implements ActionListener{
    	public void actionPerformed(ActionEvent event) {
    		
    		String action = event.getActionCommand();
    		
    		switch(action) {
    		case "becrouw":
	    		becrouwCompteur++;
	    		compterLabel.setText(becrouwCompteur+ " BeCrouws");
	    		break;
    		case "Cursor":
    			if (becrouwCompteur>=curseurPrix)
    			{
    		    	File Clap = new File("savun.wav");
    		    	PlaySound(Clap);
    				becrouwCompteur = becrouwCompteur - curseurPrix;
    				curseurPrix+=4;
    				compterLabel.setText(becrouwCompteur+ " BeCrouws");
    				
    				curseurNombre++;
    				bouton1.setText(curseurNombre+" Goldboy"  );
    				descriptionTexte.setText("Goldboy | Prix: "+ curseurPrix + "\nClick automatiquement 1 fois toutes les 2 secondes ");
        			parSeconde = parSeconde +0.5;
        			timerUpdate();
    			}
    			else
    			{
    				descriptionTexte.setText("Vous avez besoin de plus de BeCrouws!");
    			}
    			break;
    		case "Natsuki":	
    			if (becrouwCompteur>=natsukiPrix)
    			{
    				becrouwCompteur = becrouwCompteur - natsukiPrix;
    				natsukiPrix+=20;
    				compterLabel.setText(becrouwCompteur+ " BeCrouws");
    				
    				natsukiNombre++;
    				bouton2.setText(natsukiNombre+" Natsuki"  );
    				descriptionTexte.setText("Natsuki | Prix: "+ natsukiPrix + "\nClick automatiquement 50 fois par secondes ");
        			parSeconde = parSeconde +5;
        			timerUpdate();
    			}
    			else
    			{
    				descriptionTexte.setText("Vous avez besoin de plus de BeCrouws!");
    			}
    			break;
    		}
    	}
    }
    
    public class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton bouton = (JButton)e.getSource();
			
			if(bouton == bouton1)
			{
				descriptionTexte.setText("Goldboy | Prix: "+ curseurPrix + "\nClick automatiquement 1 fois toutes les 2 secondes ");
			}
			else if (bouton == bouton2)
			{
				if(natsukiUnlocked==false)
				{
					descriptionTexte.setText("L'item est pour le moment bloqué mais vous pouvez le débloquer");
				}
				else
				{
					descriptionTexte.setText("Natsuki | Prix: "+ natsukiPrix + "\nClick automatiquement 50 fois par secondes ");
				}
			}
			else if (bouton == bouton3)
			{
				descriptionTexte.setText("L'item n'existe pas encore");
			}
			else if (bouton == bouton4)
			{
				descriptionTexte.setText("L'item n'existe pas encore");
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton bouton = (JButton)e.getSource();
			
			if(bouton==bouton1)
			{
				descriptionTexte.setText(null);
			}
			else if (bouton == bouton2)
			{
				descriptionTexte.setText(null);
			}
			else if (bouton == bouton3)
			{
				descriptionTexte.setText(null);
			}
			else if (bouton == bouton4)
			{
				descriptionTexte.setText(null);
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
  
    }
    
    static void PlaySound(File Sound)
    {
    	try 
    	{
    		Clip clip = AudioSystem.getClip();
    		System.out.println(clip+" "+Sound);
    		clip.open(AudioSystem.getAudioInputStream(Sound));
    		clip.start();

    		Thread.sleep(clip.getMicrosecondLength()/10);
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    

}
