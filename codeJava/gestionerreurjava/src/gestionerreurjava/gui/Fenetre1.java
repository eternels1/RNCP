package gestionerreurjava.gui;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Fenetre1 extends JFrame {

	private JButton bouton1;
	
	public Fenetre1() throws HeadlessException {
		super("Fenetre 1");
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bouton1= new JButton("cliquez moi !!");
		add(bouton1);
		
		
	}

}
