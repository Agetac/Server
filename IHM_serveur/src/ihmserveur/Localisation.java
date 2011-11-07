package ihmserveur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Localisation extends JPanel{
	
	private JLabel lCommune = new JLabel("Commune: ");
	private JComboBox cbCommune = initCommuneComboBox();
	private JLabel lZone = new JLabel("Zone: ");
	private JComboBox cbZone = initZoneComboBox();
	
	private JLabel lQuartier = new JLabel("Quartier: ");
	private JComboBox cbQuartier = initQuartierComboBox();
	
	private JLabel lCoordonnees = new JLabel("Coordonnées: ");
	private JTextField tCoordonnees = new JTextField(20);
	private JButton GoogleMap = new JButton(new ImageIcon("./ressources/GoogleMap.jpg"));
	
	private JLabel lEtare = new JLabel("Etare: ");
	private JComboBox cbEtare = initEtareComboBox();
	
	private JLabel lVoie = new JLabel("Voie: ");
	private JTextField tVoieNum = new JTextField(3);
	private JTextField tVoieBis = new JTextField(3);
	private JTextField tVoieType = new JTextField(6);
	private JTextField tVoieNom = new JTextField(20);
	
	private JLabel lResidence = new JLabel("Résidence: ");
	private JTextField tResidence = new JTextField(20);
	private JLabel lCommentaires = new JLabel("Commentaires: ");
	private JTextField tCommentaires = new JTextField(30);
	
	private JLabel lBatiment = new JLabel("Batiment: ");
	private JTextField tBatiment = new JTextField(20);
	private JLabel lCage = new JLabel("Cage: ");
	private JTextField tCage = new JTextField(20);
	
	private JLabel lEtage = new JLabel("Etage: ");
	private JTextField tEtage = new JTextField(20);
	private JLabel lPorte = new JLabel("Porte: ");
	private JTextField tPorte = new JTextField(20);
	private JLabel lCode = new JLabel("Code: ");
	private JTextField tCode = new JTextField(20);
	
	
	
	private TitledBorder titledborder = new TitledBorder("Localisation");
	
	
	
	public Localisation (){
		new JPanel();
		Box h1 = Box.createHorizontalBox();
		Box h2 = Box.createHorizontalBox();
		Box h3 = Box.createHorizontalBox();
		Box h4 = Box.createHorizontalBox();
		Box h5 = Box.createHorizontalBox();
		Box h6 = Box.createHorizontalBox();
		Box h7 = Box.createHorizontalBox();
		Box vbox = Box.createVerticalBox();
		
		h1.add(lCommune);
		h1.add(cbCommune);
		h1.add(lZone);
		h1.add(cbZone);
		
		h2.add(lQuartier);
		h2.add(cbQuartier);
		
		h3.add(lEtare);
		h3.add(cbEtare);
		h3.add(lCoordonnees);
		h3.add(tCoordonnees);
		h3.add(GoogleMap);
		
		h4.add(lVoie);
		h4.add(tVoieNum);
		h4.add(tVoieBis);
		h4.add(tVoieType);
		h4.add(tVoieNom);
		
		h5.add(lResidence);
		h5.add(tResidence);
		h5.add(lCommentaires);
		h5.add(tCommentaires);
		
		h6.add(lBatiment);
		h6.add(tBatiment);
		h6.add(lCage);
		h6.add(tCage);
		
		h7.add(lEtage);
		h7.add(tEtage);
		h7.add(lPorte);
		h7.add(tPorte);
		h7.add(lCode);
		h7.add(tCode);
		
		vbox.add(h1);
		vbox.add(h2);
		vbox.add(h3);
		vbox.add(h4);
		vbox.add(h5);
		vbox.add(h6);
		vbox.add(h7);
		
		add(vbox,BorderLayout.CENTER);
		setBorder(titledborder);
	}
	
	
	
	private JComboBox initCommuneComboBox() {
		JComboBox combo = new JComboBox();
		combo.addItem("");
		combo.addItem("000");
		combo.addItem("001");
		combo.addItem("002");
		combo.addItem("003");
		combo.addItem("004");
		combo.addItem("005");
		combo.addItem("006");
		combo.addItem("007");
		return combo;
		
	}
	
	private JComboBox initZoneComboBox() {
		JComboBox combo = new JComboBox();
		combo.addItem("");
		combo.addItem("000");
		combo.addItem("001");
		combo.addItem("002");
		combo.addItem("003");
		combo.addItem("004");
		combo.addItem("005");
		combo.addItem("006");
		combo.addItem("007");
		return combo;
		
	}
	
	private JComboBox initQuartierComboBox() {
		JComboBox combo = new JComboBox();
		combo.addItem("");
		combo.addItem("000");
		combo.addItem("001");
		combo.addItem("002");
		combo.addItem("003");
		combo.addItem("004");
		combo.addItem("005");
		combo.addItem("006");
		combo.addItem("007");
		return combo;
		
	}
	
	private JComboBox initEtareComboBox() {
		JComboBox combo = new JComboBox();
		combo.addItem("");
		combo.addItem("000");
		combo.addItem("001");
		combo.addItem("002");
		combo.addItem("003");
		combo.addItem("004");
		combo.addItem("005");
		combo.addItem("006");
		combo.addItem("007");
		return combo;
		
	}

}
