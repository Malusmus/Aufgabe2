package de.dis2011;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.dis2011.data.EstateAgent;

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

	JButton _hammer;

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

		_hammer = new JButton();

		_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String login = _eingabeName.getText();
				String passwort = _passwortEingabe.getText();

				if (uch.checkPasswordForMakler(login, passwort)) {
					System.out.print("Passwort funzt.");
					buildChoiceMenu();
				} else {
					System.err.print("Passwort funzt nicht.");
				}

			}

		});

		// buildEstateMenu();

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

	private void buildChoiceMenu() {
		JDialog choiceMenu = new JDialog();
		choiceMenu.setSize(600, 500);
		JPanel choicePanel = new JPanel();
		choiceMenu.add(choicePanel);

		choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));

		choicePanel.add(new JLabel("Handle Stuff"));
		JButton createMakler = new JButton("Create Makler");
		JButton deleteMakler = new JButton("Delete Makler");
		JButton createKunde = new JButton("Create Customer");
		JButton deleteKunde = new JButton("Delete Kunde");
		JButton createEstate = new JButton("Create Estate");
		JButton deleteEstate = new JButton("Delete Estate");
		JButton createContract = new JButton("Create Basic Contract");
		JButton deleteContract = new JButton("Delete Basic Contract");
		JButton createFinalContract = new JButton("Create Final Contract");
		JButton deleteFinalContract = new JButton("Delete Final Contract");

		JPanel pMakler = new JPanel();
		pMakler.add(createMakler);
		pMakler.add(deleteMakler);
		pMakler.setBackground(new Color(0, 70, 70));
		choicePanel.add(pMakler);

		JPanel pKunde = new JPanel();
		pKunde.add(createKunde);
		pKunde.add(deleteKunde);
		pKunde.setBackground(new Color(0, 60, 60));
		choicePanel.add(pKunde);

		JPanel pEstate = new JPanel();
		pEstate.add(createEstate);
		pEstate.add(deleteEstate);
		pEstate.setBackground(new Color(0, 80, 80));
		choicePanel.add(pEstate);

		JPanel pContract = new JPanel();
		pContract.add(createContract);
		pContract.add(deleteContract);
		pContract.setBackground(new Color(0, 50, 50));
		choicePanel.add(pContract);

		JPanel pFinalContract = new JPanel();
		pFinalContract.add(createFinalContract);
		pFinalContract.add(deleteFinalContract);
		pFinalContract.setBackground(new Color(0, 90, 90));
		choicePanel.add(pFinalContract);

		choiceMenu.setVisible(true);
		choicePanel.setVisible(true);

		createMakler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createMakler();
			}

			private void createMakler() {
				JDialog dialog = new JDialog(_main);
				JPanel pane = new JPanel();
				dialog.setSize(600, 500);
				dialog.add(pane);
				pane.add(new JLabel("Name"));
				final JTextField name = new JTextField(20);
				pane.add(name);
				pane.add(new JLabel("Adresse"));
				final JTextField adresse = new JTextField(20);
				pane.add(adresse);
				pane.add(new JLabel("Login"));
				final JTextField login = new JTextField(20);
				pane.add(login);
				pane.add(new JLabel("Passwort"));
				final JTextField passwort = new JTextField(20);
				pane.add(passwort);
				_hammer.setText("Create Makler");
				_hammer.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						uch.createAccount(login.getText(), name.getText(), adresse.getText(), passwort.getText());
						JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Makler",
								JOptionPane.INFORMATION_MESSAGE);
					}
				});
				pane.add(_hammer);
				dialog.setVisible(true);
				pane.setVisible(true);
			}
		});

		deleteMakler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMakler();
			}

			private void deleteMakler() {
				JDialog dialog = new JDialog(_main);
				JPanel pane = new JPanel();
				dialog.add(pane);
				
				EstateAgent current;
				JSpinner makler = new JSpinner(new SpinnerListModel(uch.getAllEstateAgents()));
				
				
				_hammer.setText("Delete the chosen estate agent");
				_hammer.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						uch.getAllEstateAgents().remove(current);
					}
				});
				
				pane.add(_hammer);
				
				
				dialog.setSize(500, 600);
				dialog.setVisible(true);
			}
		});

	}

}
