package org.agetac.server.ihm;

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

import org.agetac.common.Agent;
import org.agetac.common.Aptitude;
import org.agetac.observer.Observer;
import org.agetac.observer.Subject;
import org.agetac.server.db.Agents;

public class AgentsIHM extends JFrame implements Observer {

private static final long serialVersionUID = 1L;
	
	private AgentTableModel modele = new AgentTableModel();
	private JTable tableau;

	
	public AgentsIHM() {

		super();

		setTitle("Liste des agents");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tableau = new JTable(modele);

		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		JPanel boutons = new JPanel();

		boutons.add(new JButton(new AddAction()));

		getContentPane().add(boutons, BorderLayout.SOUTH);

		pack();
		
	}
	

	@Override
	public void update(Subject s) {
		
	}

	public static void main(String[] args) {
		new AgentsIHM().setVisible(true);
	}
	
	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter");
		}

		public void actionPerformed(ActionEvent e) {
			List<Agent> la = new ArrayList();
			Agent bob = new Agent("mon ID", "mon Nom", new Aptitude("CDC"), la);
			modele.addAgent(bob);
		}
	}
	
}
