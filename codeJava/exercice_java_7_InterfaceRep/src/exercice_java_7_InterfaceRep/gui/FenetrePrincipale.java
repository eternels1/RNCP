package exercice_java_7_InterfaceRep.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exercice_java_7_InterfaceRep.metier.Contact;

public class FenetrePrincipale extends JFrame implements ListSelectionListener, ActionListener {

	
	private JPanel panelDroit;
	private JPanel panelDroitHaut;
	private JScrollPane panelDroitBas;
	
	
	private JPanel panelSud;
	
	
	
	private JButton triIdBt;
	private JButton triNomBt;	
	private JButton triPrenomBt;
	private JButton triNomPrenomBt;
	private JButton triEmailBt;
	private JButton triAgeBt;
	private JCheckBox checkboxGold;
	private JTextField textboxAge;
	private JLabel labelAge;
	private JButton validerAgeRechercheBt;
	
	private Predicate<Contact> currentFilter;
	private Predicate<Contact> currentFilterGold;
	private Comparator<Contact> currentSort;
	private Predicate<Contact> currentFilterAgeRecherche;
	
	
	private ArrayList<Contact> contactsFullData;
	private DefaultListModel<Contact> contactsVisiblesData;
	private JList<Contact> contactsVisibles;
	private JList<String> referentList;
	
	
	public static final String TRI_ID_COMMAND = "id_tri";
	public static final String TRI_NOM_COMMAND = "nom_tri";
	public static final String TRI_PRENOM_COMMAND = "prenom_tri";
	public static final String TRI_NOMPRENOM_COMMAND = "nomprenom_tri";
	public static final String TRI_EMAIL_COMMAND = "email_tri";
	public static final String TRI_AGE_COMMAND = "age_tri";
	
	public static final String BOUTON_VALIDER_AGE_COMMAND = "valider_age_boutton";
	
	
	public FenetrePrincipale() throws HeadlessException {
		super("ContactsManagment");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		contactsFullData= new ArrayList<>();
		contactsFullData.add(new Contact(1,"Abarou","Said","abarousaid@laposte.net",'m',38,true,"Nourdin"));
		contactsFullData.add(new Contact(2,"LeDoc","Hamid","ledoc@google.com",'m',36,false,"Nourdin"));
		contactsFullData.add(new Contact(3,"Abarou","Nourdin","abarounourdin@hotmail.com",'m',41,true,"Ava"));
		contactsFullData.add(new Contact(4,"Abarou","Tarik","Tarik@yahoo.fr",'m',34,true,"Nourdin"));
		contactsFullData.add(new Contact(5,"Abarou","Bouteyna","bouteyna@google.com",'f',44,true,"Abderrahim"));
		contactsFullData.add(new Contact(6,"Latifi","Abderrahim","litifi@free.fr",'m',45,true,"aucun"));
		contactsFullData.add(new Contact(7,"Cazat","Mickael","cazat@gmail.com",'m',33,false,"Alain"));
		contactsFullData.add(new Contact(8,"Sahraoui","Iltaf","sahraoui@hotmail.com",'f',34,false,"SiAhmed Sahraoui"));
		
		
		List<String> referents= contactsFullData.stream()
										.map(c -> c.getReferent())
										.distinct()
										.collect(Collectors.toList());
		
		referents.add("tous");
		
		referentList= new JList<>(referents.toArray(new String[0]));
		
		
		referentList.addListSelectionListener(this);
		
		
		panelDroit= new JPanel();
		panelSud= new JPanel();
		panelDroitHaut=new JPanel();
		
		
	
		
		
		BoxLayout bl1= new BoxLayout(panelSud, BoxLayout.X_AXIS);
		BoxLayout bl2= new BoxLayout(panelDroitHaut, BoxLayout.Y_AXIS);
		
		
		panelSud.setLayout(bl1);
		panelDroit.setLayout(new GridLayout(2, 1));
		panelDroitHaut.setLayout(bl2);
			
		
		labelAge = new JLabel("Saisir Age");
		textboxAge= new JTextField(0);
		validerAgeRechercheBt= new JButton("Valider");
		panelSud.add(labelAge);
		panelSud.add(textboxAge);
		panelSud.add(validerAgeRechercheBt);
		
		
		triIdBt= new JButton("Trier Par Id");
		panelDroitHaut.add(triIdBt);
		triNomBt= new JButton("Trier Par Nom");
		panelDroitHaut.add(triNomBt);
		triPrenomBt= new JButton("Trier Par Prénom");
		panelDroitHaut.add(triPrenomBt);
		triNomPrenomBt= new JButton("Trier Par Nom et Prénom");
		panelDroitHaut.add(triNomPrenomBt);
		triEmailBt= new JButton("Trier Par Email");
		panelDroitHaut.add(triEmailBt);
		triAgeBt= new JButton("Trier Par Age");
		panelDroitHaut.add(triAgeBt);
		
		
		triIdBt.addActionListener(this);
		triIdBt.setActionCommand(TRI_ID_COMMAND);
		triNomBt.addActionListener(this);
		triNomBt.setActionCommand(TRI_NOM_COMMAND);
		triPrenomBt.addActionListener(this);
		triPrenomBt.setActionCommand(TRI_PRENOM_COMMAND);
		triNomPrenomBt.addActionListener(this);
		triNomPrenomBt.setActionCommand(TRI_NOMPRENOM_COMMAND);
		triEmailBt.addActionListener(this);
		triEmailBt.setActionCommand(TRI_EMAIL_COMMAND);
		triAgeBt.addActionListener(this);
		triAgeBt.setActionCommand(TRI_AGE_COMMAND);
		
		validerAgeRechercheBt.addActionListener(this);
		validerAgeRechercheBt.setActionCommand(BOUTON_VALIDER_AGE_COMMAND);
		textboxAge.addActionListener(this);
		textboxAge.setActionCommand(BOUTON_VALIDER_AGE_COMMAND);
		
		
		
		
		
		panelDroitBas = new JScrollPane(referentList);
		
		checkboxGold = new JCheckBox("Contacts Gold");
		checkboxGold.addActionListener(this);
		
		
		panelSud.add(checkboxGold);
		
		panelDroit.add(panelDroitHaut);
		panelDroit.add(panelDroitBas);
		add(panelDroit, BorderLayout.EAST);
		add(panelSud, BorderLayout.SOUTH);
		
		contactsVisiblesData = new DefaultListModel<>();
		contactsVisibles = new JList<>(contactsVisiblesData);
		
		add(new JScrollPane(contactsVisibles));
		currentFilter= Contact.getFilter("tous");
		currentSort=Contact.ID_SORT;
		
		currentFilterAgeRecherche=Contact.getFilterAgeRecherche(Integer.MAX_VALUE);
		currentFilterGold = Contact.getGoldFilter(false);
		refreshList();
		
	}

	
	private void refreshList() {
		contactsVisiblesData.clear();
		
		contactsFullData.stream()
			.filter(currentFilter)
			.filter(currentFilterGold)
			.filter(currentFilterAgeRecherche)
			.sorted(currentSort)
			.forEach(p -> contactsVisiblesData.addElement(p));
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
		
		if (checkboxGold.isSelected()) {
			currentFilterGold=Contact.getGoldFilter(true);
		}
		else currentFilterGold=Contact.getGoldFilter(false);
		
		switch(e.getActionCommand()) {
		case TRI_ID_COMMAND: currentSort = Contact.ID_SORT; break;
		case TRI_NOM_COMMAND: currentSort = Contact.NOM_SORT; break;
		case TRI_PRENOM_COMMAND: currentSort = Contact.PRENOM_SORT; break;
		case TRI_NOMPRENOM_COMMAND: currentSort = Contact.NOMPRENOM_SORT; break;
		case TRI_EMAIL_COMMAND: currentSort = Contact.EMAIL_SORT; break;
		case TRI_AGE_COMMAND: currentSort = Contact.AGE_SORT; break;
		case BOUTON_VALIDER_AGE_COMMAND: currentFilterAgeRecherche=
				Contact.getFilterAgeRecherche(Integer.parseInt(textboxAge.getText()));
		textboxAge.setText("");       ;break;
			
			
	}
	refreshList();
		
		
	} 


	@Override
	public void valueChanged(ListSelectionEvent e) {
		String choix = referentList.getSelectedValue();
		if (choix == null)
			currentFilter = Contact.getFilter("tous");
		else
			currentFilter = Contact.getFilter(choix);
		refreshList();
		
	}

	

}
