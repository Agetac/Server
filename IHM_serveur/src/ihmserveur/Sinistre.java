package ihmserveur;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Sinistre extends JPanel{
	


	private JLabel lsinistre = new JLabel("Sinistre: ");
	private JComboBox cbsinistre = initCodeComboBox();
	private JTextField tsinistre = new JTextField(20);
	private JLabel lplan = new JLabel("Plan: ");
	private JComboBox cbplan = initPlanComboBox();
	private JLabel lechelon = new JLabel("Echelon: ");
	private JComboBox cbechelon = initEchelonComboBox();
	private TitledBorder titledborder = new TitledBorder("Sinistre");
	
	
	
	public Sinistre (){
		new JPanel();
		Box h1 = Box.createHorizontalBox();
		Box h2 = Box.createHorizontalBox();
		Box vbox = Box.createVerticalBox();
		
		h1.add(lsinistre);
		h1.add(cbsinistre);
		h1.add(tsinistre);
		h2.add(lplan);
		h2.add(cbplan);
		h2.add(lechelon);
		h2.add(cbechelon);
		vbox.add(h1);
		vbox.add(h2);
		add(vbox,BorderLayout.CENTER);
		setBorder(titledborder);
	}
	
	
	private JComboBox initCodeComboBox() {
		JComboBox combo = new JComboBox();
		combo.addItem("");
		combo.addItem("1 Secours à victime");
		combo.addItem("2 Accident");
		combo.addItem("3 Feux");
		combo.addItem("4 Autres");
		combo.addItem("5 Groupes constitués");
		combo.addItem("6 Manoeuvres");
		combo.addItem("7 Plan rouge");
		return combo;
		
	}
	
	private JComboBox initPlanComboBox() {
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
	
	private JComboBox initEchelonComboBox() {
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
