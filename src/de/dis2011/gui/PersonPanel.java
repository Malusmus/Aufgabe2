package de.dis2011.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.data.Person;

public class PersonPanel {

	Person p;
	
	JPanel person = new JPanel();

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

		person.add(idLabel);
		person.add(id);
		person.add(firstNameLabel);
		person.add(firstName);
		person.add(nameLabel);
		person.add(name);
		person.add(adressLabel);
		person.add(adress);

		person.setLayout(new BoxLayout(person, BoxLayout.Y_AXIS));
		person.setVisible(true);
	}

	public JPanel getPersonPanel() {
		return person;
	}
	
	public Person getPersonPanelPerson(){
		return p;
	}
}
