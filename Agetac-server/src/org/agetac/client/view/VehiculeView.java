package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.agetac.client.controller.VehiculeController;
import org.agetac.client.model.VehiculeModel;

public class VehiculeView extends JFrame {

	private VehiculeController controller;
	private VehiculeModel model;
	
	private JTable table;
	private JTextField txtId, txtNom, txtPosition, txtCaserne,txtEtat,txtGroupe;
	private JButton addBut, delBut;

	public VehiculeView(VehiculeModel model) {
		
		this.model = model;
		this.controller = new VehiculeController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des vehicules
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout());

		// Ajouter
		addBut = new JButton("Ajouter un vehicule");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Supprimer
		delBut = new JButton("Supprimer un vehicule");
		delBut.addActionListener(this.controller);
		panelButton.add(delBut);
		
		

		//Champs
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());
		
		// Champ ID
		txtId = new JTextField("ID");
		panelChamps.add(txtId);
		
		// Champ Nom
		txtNom = new JTextField("Nom");
		panelChamps.add(txtNom);
		
		// Champ Position
		txtPosition = new JTextField("Position");
		panelChamps.add(txtPosition);

		// Champ CaserneName
		txtCaserne = new JTextField("Caserne");
		panelChamps.add(txtCaserne);

		// Champ Etat
		txtEtat = new JTextField("Etat");
		panelChamps.add(txtEtat);

		// Champ Groupe
		txtGroupe = new JTextField("Groupe");
		panelChamps.add(txtGroupe);

		// Panel Champs & Buttons
		JPanel CandB = new JPanel();
		CandB.setLayout(new BorderLayout());
		CandB.add(panelButton,BorderLayout.NORTH);
        CandB.add(panelChamps,BorderLayout.SOUTH);

		
		//ajout de la table et du panel des boutons
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
        panel.add(CandB,BorderLayout.SOUTH);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des vehicules");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public String getID(){
		return txtId.getText();
	}
	
	public String getNom(){
		return txtNom.getText();
	}
	
	public String getPosition(){
		return txtPosition.getText();
	}
	
	public String getCaserne(){
		return txtCaserne.getText();
	}
	
	public String getEtat(){
		return txtEtat.getText();
	}
	
	public String getGroupe(){
		return txtGroupe.getText();
	}
	
	public void resetTxtFields(){
		txtId.setText("ID");
		txtNom.setText("Nom");
		txtPosition.setText("Position");
		txtCaserne.setText("Caserne");
		txtEtat.setText("Etat");
		txtGroupe.setText("Groupe");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
	
	
	
}
