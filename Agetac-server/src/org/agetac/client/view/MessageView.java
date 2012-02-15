package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.agetac.client.controller.MessageController;
import org.agetac.client.model.MessageModel;

public class MessageView extends JFrame {

	private MessageController controller;
	private MessageModel model;
	
	private JPanel panel;
	private JTable table;
	private JTextField txtId, txtGroupeHoraire, txtMessage;
	private JButton addBut, delBut;

	public MessageView(MessageModel model) {
		
		this.model = model;
		this.controller = new MessageController(this, this.model);

		// Conteneur principal
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des messages
		this.table = new JTable(this.model);
		
		// Tri tableau des messages
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());   
		sorter.setSortable(0, false);
		sorter.setSortable(1, true);
		sorter.setSortable(2, false);
		sorter.setSortsOnUpdates(true);
		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(1);

		// Les boutons
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout());

		// Ajouter
		addBut = new JButton("Ajouter un message");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Supprimer
		delBut = new JButton("Supprimer un message");
		delBut.addActionListener(this.controller);
		panelButton.add(delBut);
		
		
		
		//Champs
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());
		
		// Champ ID
		txtId = new JTextField("ID");
		panelChamps.add(txtId);
		
		// Champ GH
		txtGroupeHoraire = new JTextField("Groupe Horaire");
		panelChamps.add(txtGroupeHoraire);
		
		// Champ Message
		txtMessage = new JTextField("Message");
		panelChamps.add(txtMessage);
		

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
		setTitle("Liste des messages");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public String getID(){
		return txtId.getText();
	}
	
	public String getGH(){
		return txtGroupeHoraire.getText();
	}
	
	public String getMessage(){
		return txtMessage.getText();
	}
	
	public void resetTxtFields(){
		txtId.setText("ID");
		txtGroupeHoraire.setText("Groupe Horaire");
		txtMessage.setText("Message");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
		
}
