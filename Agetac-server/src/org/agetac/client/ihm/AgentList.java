package org.agetac.client.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.agetac.client.model.AgentModel;
import org.agetac.common.Agent;
import org.agetac.common.Aptitude;



public class AgentList extends JFrame{

	private static final long serialVersionUID = 8938525932293876472L;

	private AgentModel modele = new AgentModel();
	private JTable tableau;

	public AgentList(AgentModel model) {

		super();
		
		modele = model;
		setTitle("Liste des agents");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tableau = new JTable(modele);

		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		JPanel boutons = new JPanel();

		boutons.add(new JButton(new AddAction()));

		getContentPane().add(boutons, BorderLayout.SOUTH);

		pack();
		
	}



	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter");
		}

		public void actionPerformed(ActionEvent e) {
			Aptitude apt = new Aptitude("COS");
			List<Agent> lag = new ArrayList<Agent>();
			modele.addAgent(new Agent("007", "James", apt,lag));
		}
	}

}
