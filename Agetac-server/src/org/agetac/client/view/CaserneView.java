package org.agetac.client.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.agetac.client.controler.CaserneControler;
import org.agetac.client.model.CaserneModel;

public class CaserneView extends JFrame {

	private CaserneControler controler;
	private CaserneModel model;
	
	private JTable table;

	public CaserneView(CaserneModel model) {
		
		this.model = model;
		this.controler = new CaserneControler(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des agents
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter une caserne");
		addBut.addActionListener(this.controler);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer une caserne");
		delBut.addActionListener(this.controler);
		panel2.add(delBut);
		
		//ajout du panel
        panel.add(panel2,BorderLayout.CENTER);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des casernes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public void refreshCasernes(){
			
	}
	
	
}
