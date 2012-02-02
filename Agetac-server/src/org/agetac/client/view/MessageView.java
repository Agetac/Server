package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.agetac.client.controler.MessageControler;
import org.agetac.client.model.MessageModel;

public class MessageView extends JFrame {

	private MessageControler controler;
	private MessageModel model;
	
	private JTable table;

	public MessageView(MessageModel model) {
		
		this.model = model;
		this.controler = new MessageControler(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des messages
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter un message");
		addBut.addActionListener(this.controler);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer un message");
		delBut.addActionListener(this.controler);
		panel2.add(delBut);
		
		//ajout du panel
        panel.add(panel2,BorderLayout.CENTER);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des messages");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public void refreshMessages(){
			
	}
	
	
}
