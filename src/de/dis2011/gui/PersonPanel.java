package de.dis2011.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.data.Person;

public class PersonPanel extends JPanel{

	Person p;
	
	JLabel idLabel = new JLabel("id");
	JTextField id;

	JLabel firstNameLabel = new JLabel("Vorname");
	JTextField firstName;

	JLabel nameLabel = new JLabel("Nachname");
	JTextField name;

	JLabel adressLabel = new JLabel("Adresse");
	JTextField adress;

	public PersonPanel(Person p) {
		this.p = p;

		id = new JTextField(p.getId() + "");
		firstName = new JTextField(p.getFirstName());
		name = new JTextField(p.getName());
		adress = new JTextField(p.getAddress());

		add(idLabel);
		add(id);
		add(firstNameLabel);
		add(firstName);
		add(nameLabel);
		add(name);
		add(adressLabel);
		add(adress);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	public JPanel getPersonPanel() {
		return this;
	}
	
	public Person getPersonPanelPerson(){
		return p;
	}
}
