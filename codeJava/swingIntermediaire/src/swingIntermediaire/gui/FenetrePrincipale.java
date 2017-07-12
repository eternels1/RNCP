package swingIntermediaire.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swingIntermediaire.metier.Produit;

public class FenetrePrincipale extends JFrame implements ListSelectionListener, ActionListener {
	
	public static final String TRI_NOM_COMMAND = "nom_tri";
	public static final String TRI_PRIX_COMMAND = "nom_prix";
	public static final String TRI_POIDS_COMMAND = "nom_poids";
	private JPanel paneltri;
	private JList<String> listecategories;
	private Predicate<Produit> currentFilter;
	private Comparator<Produit> currentSort;
	private JButton triNom;
	private JButton triPrixBt;
	private JButton triPoidsBt;
	
	private DefaultListModel<Produit> produitsVisiblesData;
	private JList<Produit> produitsVisibles;
	
	private ArrayList<Produit> produitFullData;
	
	public FenetrePrincipale() throws HeadlessException {
		super("ma super application");
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		paneltri = new JPanel();
		BoxLayout bl = new BoxLayout(paneltri, BoxLayout.X_AXIS);
		paneltri.setLayout(bl);
		
		
		triNom= new JButton("trier par nom");
		paneltri.add(triNom);
		triPrixBt= new JButton("trier par prix");
		paneltri.add(triPrixBt);
		triPoidsBt=new JButton("trier par poids");
		paneltri.add(triPoidsBt);
		
		//partie haute
		add(paneltri, BorderLayout.NORTH);
		
		//partie gauche
		listecategories= new JList<>(new String[] {"viandes","cereales","fromages",
				"legumes","divers","toutes"});
		
		add(new JScrollPane(listecategories), BorderLayout.WEST);
		
		produitsVisiblesData = new DefaultListModel<>();
		produitsVisibles= new JList<>(produitsVisiblesData);
		
		add(new JScrollPane(produitsVisibles));
		
		produitFullData= new ArrayList<>();
		
		
		produitFullData.add(new Produit(1,"steak de lama",49.99,1.2,"viandes"));
		produitFullData.add(new Produit(2,"tofu tout fou",29.99,0.750,"fromages"));
		produitFullData.add(new Produit(3,"quinoa des andes",39.99,1.0,"cereales"));
		produitFullData.add(new Produit(4,"miel des carpathes",59.99,1.0,"divers"));
		produitFullData.add(new Produit(5,"oignon rouge",9.99,1.1,"legumes"));
		produitFullData.add(new Produit(6,"brebis des alpes",15.99,0.35,"fromages"));
		produitFullData.add(new Produit(7,"beuf yogique",69.99,0.75,"viandes"));
		produitFullData.add(new Produit(8,"ble sans gluten",27.50,2.0,"cereales"));
		produitFullData.add(new Produit(9,"tomates vertes",8.99,3.0,"divers"));
		produitFullData.add(new Produit(10,"poulet plein air",17.50,1.8,"viandes"));
		
		
//		produitFullData.stream()
//							.filter(p-> p.getCategorie().equals("viandes"))
//							.sorted((p1,p2) -> Double.compare(p1.getPrix(),p2.getPrix()))
//							.forEach(p -> produitsVisiblesData.addElement(p));
//		
		
		currentFilter= Produit.ALL_CATEGORIES_FILTER;
		currentSort=Produit.ID_SORT;
		
		refreshlist();
		
		listecategories.addListSelectionListener(this);
		
		triNom.addActionListener(this);
		triNom.setActionCommand(TRI_NOM_COMMAND);
		triPoidsBt.addActionListener(this);
		triPoidsBt.setActionCommand(TRI_POIDS_COMMAND);
		triPrixBt.addActionListener(this);
		triPrixBt.setActionCommand(TRI_PRIX_COMMAND);
		
		
		
		
	}
	
	
	
	
	
	private void refreshlist() {
		produitsVisiblesData.clear();
				
		produitFullData.stream()
		.filter(currentFilter)
		.sorted(currentSort)
		.forEach(p -> produitsVisiblesData.addElement(p));
		
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch(listecategories.getSelectedValue()) {
		case "viandes": currentFilter= Produit.VIANDES_CATEGORIES_FILTER;break;
		case "legumes": currentFilter= Produit.LEGUMES_CATEGORIES_FILTER;break;
		case "cereales": currentFilter= Produit.CEREALES_CATEGORIES_FILTER;break;
		case "fromages": currentFilter= Produit.FROMAGES_CATEGORIES_FILTER;break;
		case "divers": currentFilter= Produit.DIVERS_CATEGORIES_FILTER;break;
		default : currentFilter= Produit.ALL_CATEGORIES_FILTER;break;
		}
		
		refreshlist();
		
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case TRI_NOM_COMMAND: currentSort= Produit.NOM_SORT;break;
		case TRI_PRIX_COMMAND: currentSort= Produit.PRIX_SORT;break;
		case TRI_POIDS_COMMAND: currentSort= Produit.POIDS_SORT;break;
		default: currentSort= Produit.ID_SORT;break;
		}
		
		refreshlist();
		
	}
	
	
	

}
