package org.agetac.server.ihm;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AgetacServerGUI_Lieu extends JPanel{

	private static final long serialVersionUID = -3752913273969239530L;
	
	private JLabel lb_Longitude = new JLabel("Longitude: ");
	private JTextField tf_Longitude = new JTextField("°",6);
	private JLabel lb_Latitude = new JLabel("Latitude: ");
	private JTextField tf_Latitude = new JTextField("°",6);
	
	private TitledBorder titledborder = new TitledBorder("Lieu");
	
	public AgetacServerGUI_Lieu() {
		
		super();
		
		setLayout(new FlowLayout());
		
		add(lb_Longitude);
		add(tf_Longitude);
		add(lb_Latitude);
		add(tf_Latitude);

		setBorder(titledborder);
		
	}
}
