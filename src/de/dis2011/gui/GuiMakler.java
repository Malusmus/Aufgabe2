package de.dis2011.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.Gui;
import de.dis2011.Main;
import de.dis2011.UseCaseHandler;
import de.dis2011.data.EstateAgent;

public class GuiMakler  {

	 static JDialog dialogCreate;
	 static JDialog dialogChange;
	 static JDialog dialogDelete;
	 
		public final static UseCaseHandler uch = Main.uch;
		public static JFrame _main;


	static ActionListener createMakler() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogCreate = new JDialog(_main);
				JPanel paneCreate = new JPanel();
				dialogCreate.setSize(600, 500);
				dialogCreate.add(paneCreate);
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
				dialogCreate.setVisible(true);
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
				dialogCreate.dispose();
			}
		};
	}

	static ActionListener changeMakler() {
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogChange = new JDialog(_main);
				JPanel paneChange = new JPanel();
				dialogChange.add(paneChange);

				final JComboBox<EstateAgent> makler = new JComboBox<EstateAgent>();
				makler.setSize(300, 100);
				paneChange.add(makler);
				
				ArrayList<EstateAgent> maklerListe = uch.getEstateAgents();
				for (EstateAgent ea : maklerListe) {
					makler.addItem(ea);
				}

				JButton changeButton = new JButton();
				changeButton.setText("Change the chosen estate agent");
				changeButton.addActionListener(changeButtonListener(makler));
				paneChange.add(changeButton);

				dialogChange.setSize(500, 600);
				dialogChange.setVisible(true);
			}};
	}
	
	private static ActionListener changeButtonListener(final JComboBox<EstateAgent> makler){
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dialogChange.dispose();
				dialogChange = new JDialog(_main);
				JPanel pane = new JPanel();

				
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
				String maklerLogin = current.getLogin();
				final JTextField login = new JTextField(maklerLogin, 20);
				pane.add(login);
				//Sein Passwort
				pane.add(new JLabel("Passwort"));
				String maklerPasswort = current.getPassword();
				final JTextField passwort = new JTextField(maklerPasswort, 20);
				pane.add(passwort);
				//Button zum changen
				JButton changeButton = new JButton();
				changeButton.setText("Change Makler");
				changeButton.addActionListener(changeButtonListener(current, login, name, adresse, passwort));
				pane.add(changeButton);
				
				pane.setVisible(true);
				dialogChange.setSize(600, 500);
				dialogChange.add(pane);
				dialogChange.setVisible(true);
			}
		};
	}
	
	private static ActionListener changeButtonListener(final EstateAgent makler, final JTextField login, final JTextField name, final JTextField adresse, final JTextField passwort){

		return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					uch.fireEstateAgent(makler.getEstateAgentId());
					uch.createAccount(login.getText(), name.getText(), adresse.getText(), passwort.getText());
					JOptionPane.showMessageDialog(null, "Successfully changed", "InfoBox: Change Makler",
							JOptionPane.INFORMATION_MESSAGE);
					dialogChange.dispose();
				}
			};
		
	}

	static ActionListener deleteMakler() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogDelete = new JDialog();
				JPanel paneDelete = new JPanel();
				dialogDelete.add(paneDelete);

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

				dialogDelete.setSize(500, 600);
				dialogDelete.setVisible(true);
			}
		};
	}

	private static ActionListener deleteButtonListener(final JComboBox<EstateAgent> makler) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EstateAgent gefeuert = (EstateAgent) makler.getSelectedItem();
				uch.fireEstateAgent(gefeuert.getEstateAgentId());
				dialogDelete.dispose();
			}
		};
	}

}
