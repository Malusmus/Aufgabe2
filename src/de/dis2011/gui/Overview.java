package de.dis2011.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.Gui;
import de.dis2011.data.EstateAgent;

public class Overview {

	public static JFrame _main;

	public Overview() {
		JDialog choiceMenu = new JDialog(_main);
		choiceMenu.setSize(600, 500);
		JPanel choicePanel = new JPanel();
		choiceMenu.add(choicePanel);

		choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));

		choicePanel.add(new JLabel("Handle Stuff"));
		// Makler
		JButton createMakler = new JButton("Create Makler");
		JButton changeMakler = new JButton("Change Makler");
		JButton deleteMakler = new JButton("Delete Makler");

		JPanel pMakler = new JPanel();
		pMakler.add(createMakler);
		pMakler.add(changeMakler);
		pMakler.add(deleteMakler);
		pMakler.setBackground(new Color(0, 70, 70));
		choicePanel.add(pMakler);

		// JButton createKunde = new JButton("Create Kunde");
		// JButton deleteKunde = new JButton("Delete Kunde");

		// JPanel pKunde = new JPanel();
		// pKunde.add(createKunde);
		// pKunde.add(deleteKunde);
		// pKunde.setBackground(new Color(0, 60, 60));
		// choicePanel.add(pKunde);

		JButton createEstate = new JButton("Create Estate");
		JButton changeEstate = new JButton("Change Estate");
		JButton deleteEstate = new JButton("Delete Estate");

		JPanel pEstate = new JPanel();
		pEstate.add(createEstate);
		pEstate.add(changeEstate);
		pEstate.add(deleteEstate);
		pEstate.setBackground(new Color(0, 80, 80));
		choicePanel.add(pEstate);

		JButton createContract = new JButton("Create Basic Contract");
		JButton deleteContract = new JButton("Delete Basic Contract");
		JButton createFinalContract = new JButton("Create Final Contract");
		JButton deleteFinalContract = new JButton("Delete Final Contract");
		JButton showContractOverview = new JButton("Show Overview");

		JPanel pContract = new JPanel();
		pContract.add(createContract);
		pContract.add(deleteContract);
		pContract.setBackground(new Color(0, 50, 50));
		choicePanel.add(pContract);

		JPanel pFinalContract = new JPanel();
		pFinalContract.add(createFinalContract);
		pFinalContract.add(deleteFinalContract);
		pFinalContract.add(showContractOverview);
		pFinalContract.setBackground(new Color(0, 90, 90));
		choicePanel.add(pFinalContract);

		choiceMenu.setVisible(true);
		choicePanel.setVisible(true);

		addCreateMaklerListener(createMakler);
		addChangeMaklerListener(changeMakler);
		addDeleteMaklerListener(deleteMakler);

		addCreateEstateListener(createEstate);
		addChangeEstateListener(changeEstate);
		addDeleteEstateListener(deleteEstate);

		addCreateBasicContractListener(createContract);
		addDeleteBasicContractListener(deleteContract);

		addCreateSpecificContractListener(createFinalContract);
		addDeleteSpecificContractListemer(deleteFinalContract);
		addShowContractOvervierListener(showContractOverview);
	}

	private void addShowContractOvervierListener(JButton showContractOverview) {
		showContractOverview.addActionListener(GuiSpecificContract.showUs());
	}

	private void addDeleteSpecificContractListemer(JButton deleteFinalContract) {
		deleteFinalContract.addActionListener(GuiSpecificContract.deleteSpecificContract());
	}

	private void addCreateSpecificContractListener(JButton createFinalContract) {
		createFinalContract.addActionListener(GuiSpecificContract.createSpecificContract());
	}

	private void addCreateBasicContractListener(JButton createContract) {
		createContract.addActionListener(GuiBasicContract.createContractListener(true));
	}

	private void addDeleteBasicContractListener(JButton deleteContract) {
		deleteContract.addActionListener(GuiBasicContract.createContractListener(false));
	}

	private void addCreateMaklerListener(JButton createMakler) {
		createMakler.addActionListener(GuiMakler.createMakler());
	}

	private void addChangeMaklerListener(JButton changeMakler) {
		changeMakler.addActionListener(GuiMakler.changeMakler());
	}

	private void addDeleteMaklerListener(JButton deleteMakler) {
		deleteMakler.addActionListener(GuiMakler.deleteMakler());
	}

	private void addCreateEstateListener(JButton createEstate) {
		createEstate.addActionListener(GuiEstate.createEstate());
	}

	private void addChangeEstateListener(JButton changeEstate) {
		changeEstate.addActionListener(GuiEstate.changeEstate());
	}

	private void addDeleteEstateListener(JButton deleteEstate) {
		deleteEstate.addActionListener(GuiEstate.deleteEstate());
	}

}
