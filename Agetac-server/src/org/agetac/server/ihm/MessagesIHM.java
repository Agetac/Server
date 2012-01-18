package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.agetac.common.Message;
import org.agetac.observer.Observer;
import org.agetac.observer.Subject;
import org.agetac.server.db.Messages;

public class MessagesIHM extends JFrame implements Observer {

	private static final long serialVersionUID = 8938525932293876472L;

	private MessageTableModel modele = new MessageTableModel();
	private JTable tableau;

	public MessagesIHM() {

		super();

		setTitle("Liste des messages");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTable tableau = new JTable(modele);

		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		JPanel boutons = new JPanel();

		boutons.add(new JButton(new AddAction()));

		getContentPane().add(boutons, BorderLayout.SOUTH);

		pack();
	}

	@Override
	public void update(Subject s) {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MessagesIHM().setVisible(true);
	}

	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter");
		}

		public void actionPerformed(ActionEvent e) {
			modele.addMessage(new Message("Mon message", "12345"));
		}
	}
}
