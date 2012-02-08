package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Comparator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.agetac.client.controler.MessageControler;
import org.agetac.client.model.MessageModel;

public class MessageView extends JFrame {

	private MessageControler controler;
	private MessageModel model;
	
	private JPanel panel;
	private JTable table;
	private JTextField txtId, txtGroupeHoraire, txtMessage;
	private JButton addBut;

	public MessageView(MessageModel model) {
		
		this.model = model;
		this.controler = new MessageControler(this, this.model);

		// Conteneur principal
		panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(panel);

		// Tableau des messages
		this.table = new JTable(this.model);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());   
		sorter.setSortable(0, true);
		sorter.setSortable(1, false);
		sorter.setSortable(2, false);
		sorter.setSortsOnUpdates(true);
		table.setRowSorter(sorter);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		// Ajouter
		addBut = new JButton("Ajouter un message");
		addBut.addActionListener(this.controler);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer un message");
		delBut.addActionListener(this.controler);
		panel2.add(delBut);
		
		//Champs
		// -> Création Jpanel
		JPanel panel3 = new JPanel();
		Box boxChamps = Box.createVerticalBox();
		panel3.add(boxChamps);
		
		txtId = new JTextField("Id");
		//txtId.setPreferredSize(new Dimension(100,50));
		boxChamps.add(txtId);
		
		txtGroupeHoraire = new JTextField("Groupe horaire");
		//txtGroupeHoraire.setPreferredSize(new Dimension(100,50));
		boxChamps.add(txtGroupeHoraire);
		
		txtMessage = new JTextField("Messages");
		//txtMessage.setPreferredSize(new Dimension(100,50));
		boxChamps.add(txtMessage);
		
		//ajout du panel
		
		box.add(panel2);
		box.add(panel3);
		box.add(new JScrollPane(table));
		setContentPane(box);
		
		
		// Config de la JFrame
		setTitle("Liste des messages");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	public void openMenAjouter(){
		addBut.setEnabled(false);
		txtId.setVisible(true);
		txtGroupeHoraire.setVisible(true);
		txtMessage.setVisible(true);
		panel.validate();
		
	}
	
	
	public void refreshMessages(){
			
	}
	
	
}
