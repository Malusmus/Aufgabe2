package de.dis2011.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import de.dis2011.Gui;
import de.dis2011.UseCaseHandler;

public class Login{
		
	UseCaseHandler uch;
	private JPanel _panel;

	private JTextField _eingabeName;
	private JTextField _passwortEingabe;
	private JButton _login;

	private JDialog _estateDialog;
	private JPanel _estatePanel;
	private JSpinner _estateType;

	protected JButton _hammer;
	
	public Login(JFrame main, final UseCaseHandler uch) {
		this.uch = uch;
		_panel = new JPanel();

		_eingabeName = new JTextField(10);
		_passwortEingabe = new JTextField(10);

		_panel.add(new JLabel("Name: "));
		_panel.add(_eingabeName);
		_panel.add(new JLabel("Passwort: "));
		_panel.add(_passwortEingabe);

		_login = new JButton("Login");
		_panel.add(_login);

		_hammer = new JButton();

		_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String login = _eingabeName.getText();
				String passwort = _passwortEingabe.getText();
				//FIXME Keine Passwortabfrage!!!
				//if (uch.checkPasswordForMakler(login, passwort)) {
				//System.out.print("Passwort funzt.");
					new Overview();
			//	} else {
			//	System.err.print("Passwort funzt nicht.");
			//	}

			}

		});

		// buildEstateMenu();

		main.add(_panel);
		_panel.setVisible(true);
		main.setVisible(true);
	}
}
