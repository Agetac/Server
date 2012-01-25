package org.agetac.server.ihm;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AgetacServerGUI_Moyens extends JPanel{

	private static final long serialVersionUID = -2592825278549610994L;
	
	private JLabel lb_Nom = new JLabel("Nom: ");
	private JTextField tf_Nom = new JTextField(10);
	private JLabel lb_Caserne = new JLabel("Caserne: ");
	private JTextField tf_Caserne = new JTextField(10);
	private JLabel lb_Position = new JLabel("Position: ");
	private JLabel lb_Longitude = new JLabel("Longitude: ");
	private JTextField tf_Longitude = new JTextField("°",6);
	private JLabel lb_Latitude = new JLabel("Latitude: ");
	private JTextField tf_Latitude = new JTextField("°",6);
	private JButton bt_Ajouter = new JButton("Ajouter");
	
	private TitledBorder titledborder = new TitledBorder("Moyens");
	
	public AgetacServerGUI_Moyens() {
		
		super();
		
		setLayout(new FlowLayout());
		
		add(lb_Nom);
		add(tf_Nom);
		add(lb_Caserne);
		add(tf_Caserne);
		add(lb_Position);		
		add(lb_Longitude);
		add(tf_Longitude);
		add(lb_Latitude);
		add(tf_Latitude);
		add(bt_Ajouter);

		setBorder(titledborder);
		
	}
	
	

}
