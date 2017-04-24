package de.dis2011;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.WindowConstants;

public class Gui {

	UseCaseHandler uch = new DummyHandler();

	JFrame _main;
	JPanel _panel;

	JTextField _eingabeName;
	JTextField _passwortEingabe;
	JButton _login;

	JDialog _estateDialog;
	JPanel _estatePanel;
	JSpinner _estateType;

	public Gui() {
		_main = new JFrame();
		_panel = new JPanel();

		_main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		_main.setSize(500, 500);

		_eingabeName = new JTextField(10);
		_passwortEingabe = new JTextField(10);

		_panel.add(new JLabel("Name: "));
		_panel.add(_eingabeName);
		_panel.add(new JLabel("Passwort: "));
		_panel.add(_passwortEingabe);

		_login = new JButton("Login");
		_panel.add(_login);

		_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String login = _eingabeName.getText();
				String passwort = _passwortEingabe.getText();

				if (uch.checkPasswordForMakler(login, passwort)) {
					System.out.print("Passwort funzt.");
					buildEstateMenu();
				} else {
					System.err.print("Passwort funzt nicht.");
				}

			}

		});

		buildEstateMenu();
		

		_main.add(_panel);
		_panel.setVisible(true);
		_main.setVisible(true);
	}

	private void buildEstateMenu() {
		_estateDialog = new JDialog();
		_estatePanel = new JPanel();
		_estatePanel.setVisible(true);
		_estateDialog.add(_estatePanel);
		_estateType = new JSpinner(new SpinnerListModel(new String[] { "Apartment", "House" }));
		_estatePanel.add(_estateType);
		_estateDialog.setSize(800, 500);
		_estateDialog.setVisible(true);
	}



}
