package org.agetac.server.client.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.agetac.common.Message;


public class MessageList extends JFrame{

	private static final long serialVersionUID = 8938525932293876472L;

	private MessageTableModel modele = new MessageTableModel();
	private JTable tableau;

	public MessageList(MessageTableModel tableModel) {

		super();
		
		modele = tableModel;
		setTitle("Liste des messages");
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
			modele.addMessage(new Message("12", "Mon message", "12345"));
		}
	}

}
