package introSwing.gui;


import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Fenetre1 extends JFrame  implements ActionListener{

	private JButton bouton1;
	private JTextField champtexte1;
	private JCheckBox caseCoche1;
	private JLabel label1;
	private JTextArea champMultiligne;
	private MonListener1 listener1;
	
	public Fenetre1() throws HeadlessException {
		super("Fenetre 1");
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		setLayout(new FlowLayout());
		bouton1= new JButton("cliquez moi !!");
		add(bouton1);
		
		champtexte1= new JTextField(30);
		add(champtexte1);
	
		caseCoche1= new JCheckBox("cocher moi!");
		add(caseCoche1);
		
		label1= new JLabel("je suis un label très intéressant");
		add(label1);
		
		champMultiligne= new JTextArea(5, 25);
		add(champMultiligne);
		
		bouton1.addActionListener(this);
		listener1 = new MonListener1("premier listener externe");
		caseCoche1.addActionListener(listener1);
		caseCoche1.addActionListener(new MonListener2("deuxième listener interne"));
//		bouton1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				label1.setText("yolo classe anonyme");				
//			}
//		});
		
		bouton1.addActionListener(e -> label1.setText("yoyo le lambda"));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "vous avez cliquer!");
		
	}
	
	public class MonListener2 implements ActionListener{
		private String name;

		public String getName() {return name;}
		public void setName(String name) {this.name = name;}

		public MonListener2(String name) {
			super();
			setName(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "action de "+getName());
			label1.setText(getName()+" was here on "+ new Date());
		}
	}

}
