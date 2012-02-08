package org.agetac.client.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	
	JButton b;
	JTextField t;

	public MyFrame() {
		
		this.setSize(300,300);
		this.setTitle("TitleFrame");
		this.setVisible(true);
		this.setLayout(new GridLayout(5, 1));
		
		
		
		b = new JButton("bouton");
		b.addActionListener(this);
		this.add(b);
		t = new JTextField();
		this.add(t);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(b)) {
			t.setText("hello world");
		}
		
	}
	
	public static void main (String[] args) {
		MyFrame f = new MyFrame();
	}

}
