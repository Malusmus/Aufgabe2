package de.dis2011.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.Gui;
import de.dis2011.data.EstateAgent;

public class GuiMakler extends Gui {

	final static JDialog dialog = new JDialog(_main);

	static ActionListener createMakler() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel paneCreate = new JPanel();
				dialog.setSize(600, 500);
				dialog.add(paneCreate);
				paneCreate.add(new JLabel("Name"));
				final JTextField name = new JTextField(20);
				paneCreate.add(name);
				paneCreate.add(new JLabel("Adresse"));
				final JTextField adresse = new JTextField(20);
				paneCreate.add(adresse);
				paneCreate.add(new JLabel("Login"));
				final JTextField login = new JTextField(20);
				paneCreate.add(login);
				paneCreate.add(new JLabel("Passwort"));
				final JTextField passwort = new JTextField(20);
				paneCreate.add(passwort);
				JButton createButton = new JButton();
				createButton.setText("Create Makler");
				createButton.addActionListener(createButtonListener(login, name, adresse, passwort));
				paneCreate.add(createButton);
				dialog.setVisible(true);
				paneCreate.setVisible(true);
			}
		};
	}

	private static ActionListener createButtonListener(final JTextField login, final JTextField name,
			final JTextField adresse, final JTextField passwort) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uch.createAccount(login.getText(), name.getText(), adresse.getText(), passwort.getText());
				JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Makler",
						JOptionPane.INFORMATION_MESSAGE);
				dialog.removeAll();
				dialog.dispose();
			}
		};
	}

	static ActionListener changeMakler() {
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel paneChange = new JPanel();
				dialog.add(paneChange);

				final JComboBox<EstateAgent> makler = new JComboBox<EstateAgent>();
				makler.setSize(300, 100);
				paneChange.add(makler);
				
				ArrayList<EstateAgent> maklerListe = uch.getEstateAgents();
				for (EstateAgent ea : maklerListe) {
					makler.addItem(ea);
				}

				JButton changeButton = new JButton();
				changeButton.setText("Delete the chosen estate agent");
				changeButton.addActionListener(changeButtonListener(makler));
				paneChange.add(changeButton);

				dialog.setSize(500, 600);
				dialog.setVisible(true);
			}};
	}
	
	private static ActionListener changeButtonListener(final JComboBox<EstateAgent> makler){
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel pane = new JPanel();
				dialog.setSize(600, 500);
				dialog.add(pane);
				
				//Aktueller Makler
				EstateAgent current = (EstateAgent) makler.getSelectedItem();
				//Sein Name
				pane.add(new JLabel("Name"));
				String maklerName = current.getName();
				final JTextField name = new JTextField(maklerName, 20);
				pane.add(name);
				//Seine Adresse
				pane.add(new JLabel("Adresse"));
				String maklerAdresse = current.getAddress();
				final JTextField adresse = new JTextField(maklerAdresse, 20);
				pane.add(adresse);
				//Sein Login
				pane.add(new JLabel("Login"));
				String maklerLogin = current.getAddress();
				final JTextField login = new JTextField(maklerLogin, 20);
				pane.add(login);
				//Sein Passwort
				pane.add(new JLabel("Passwort"));
				String maklerPasswort = current.getAddress();
				final JTextField passwort = new JTextField(maklerPasswort, 20);
				pane.add(passwort);
				//Button zum changen
				JButton changeButton = new JButton();
				changeButton.setText("Create Makler");
				changeButton.addActionListener(changeButtonListener(current, login, name, adresse, passwort));
				pane.add(changeButton);
				
				dialog.setVisible(true);
				pane.setVisible(true);
			}
		};
	}
	
	private static ActionListener changeButtonListener(final EstateAgent makler, final JTextField login, final JTextField name, final JTextField adresse, final JTextField passwort){

		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					uch.fireEstateAgent(makler.getEstateAgentId());
					uch.createAccount(login.getText(), name.getText(), adresse.getText(), passwort.getText());
					JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Makler",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.removeAll();
					dialog.dispose();
				}
			};
		
	}

	static ActionListener deleteMakler() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel paneDelete = new JPanel();
				dialog.add(paneDelete);

				final JComboBox<EstateAgent> makler = new JComboBox<EstateAgent>();
				makler.setSize(300, 100);
				paneDelete.add(makler);

				ArrayList<EstateAgent> maklerListe = uch.getEstateAgents();
				for (EstateAgent ea : maklerListe) {
					makler.addItem(ea);
				}

				JButton deleteButton = new JButton();
				deleteButton.setText("Delete the chosen estate agent");
				deleteButton.addActionListener(deleteButtonListener(makler));
				paneDelete.add(deleteButton);

				dialog.setSize(500, 600);
				dialog.setVisible(true);
			}
		};
	}

	private static ActionListener deleteButtonListener(final JComboBox<EstateAgent> makler) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EstateAgent gefeuert = (EstateAgent) makler.getSelectedItem();
				uch.fireEstateAgent(gefeuert.getEstateAgentId());
				dialog.removeAll();
				dialog.dispose();
			}
		};
	}

}
