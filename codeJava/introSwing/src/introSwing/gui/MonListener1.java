package introSwing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MonListener1 implements ActionListener {
	
	private String name;
	

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public MonListener1(String name) {
		super();
		this.name = name;}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "action déclenchée chez "+getName());

	}

}
