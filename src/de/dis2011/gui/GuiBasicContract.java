package de.dis2011.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.Gui;
import de.dis2011.Main;
import de.dis2011.UseCaseHandler;
import de.dis2011.data.Contract;
import de.dis2011.data.PurchaseContract;
import de.dis2011.data.TenancyContract;

public class GuiBasicContract {

	public final static UseCaseHandler uch = Main.uch;
	public static JFrame _main;
	
	static JDialog contractDialog;
	static JPanel contractPanel = new JPanel();
	static JPanel createContractPanel = new JPanel();
	static JPanel deleteContractPanel = new JPanel();

	static JPanel purchaseContract;
	static JPanel tenancyContract;
	static JLabel nurVermietungLabel = new JLabel("ungecheckt für Verkauf");
	static JCheckBox nurVermietung = new JCheckBox("Nur eine Vermietung", true);

	static JButton createContractButton = new JButton("Create Contract");
	static JButton deleteContractButton = new JButton("Delete Contract");

	static JLabel contractNumberLabel = new JLabel("Vertragsnummer");
	static JTextField contractNumber = new JTextField(20);

	static JLabel dateLabel = new JLabel("Date");
	static JTextField date = new JTextField();

	static JLabel placeLabel = new JLabel("Ort");
	static JTextField place = new JTextField(20);

	static JComboBox<Contract> contractBox = new JComboBox<Contract>();

	static JLabel startDateLabel = new JLabel("erster Miettag");
	static JTextField startDate = new JTextField(20);

	static JLabel durationLabel = new JLabel("letzter Miettag");
	static JTextField duration = new JTextField(20);

	static JLabel additionalCostsLabel = new JLabel("Nebenkosten");
	static JTextField additionalCosts = new JTextField(20);

	static JLabel installmentsNumberLabel = new JLabel("Anzahl Raten");
	static JTextField installmentsNumber = new JTextField(20);

	static JLabel interestRateLabel = new JLabel("Zinsen");
	static JTextField interestRate = new JTextField(20);

	public static void createContract(boolean createNotDelete) {
		contractDialog = new JDialog(_main);
		
		purchaseContract = new JPanel();
		tenancyContract = new JPanel();

		fillTenancyPanel();
		fillPurchasePanel();

		fillCreatePanel();
		fillDeletePanel();

		contractDialog.add(contractPanel);
		contractPanel.add(createContractPanel);
		contractPanel.add(deleteContractPanel);

		createContractPanel.setVisible(createNotDelete);
		deleteContractPanel.setVisible(!createNotDelete);

		contractDialog.pack();
		contractPanel.setVisible(true);
		contractDialog.setVisible(true);
	}

	private static void fillCreatePanel() {
		createContractPanel.setLayout(new BoxLayout(createContractPanel,BoxLayout.PAGE_AXIS));
		createContractPanel.add(contractNumberLabel);
		createContractPanel.add(contractNumber);

		createContractPanel.add(dateLabel);
		createContractPanel.add(date);

		createContractPanel.add(placeLabel);
		createContractPanel.add(place);

		createContractPanel.add(nurVermietungLabel);
		nurVermietung.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tenancyContract.setVisible(nurVermietung.isSelected());
				purchaseContract.setVisible(!nurVermietung.isSelected());
			}
		});
		createContractPanel.add(nurVermietung);

		createContractPanel.add(tenancyContract);
		createContractPanel.add(purchaseContract);

		createContractButton.addActionListener(createButtonListener(nurVermietung.isSelected()));
		createContractPanel.add(createContractButton);
	}

	private static void fillTenancyPanel() {
		tenancyContract.add(startDateLabel);
		tenancyContract.add(startDate);

		tenancyContract.add(durationLabel);
		tenancyContract.add(duration);

		tenancyContract.add(additionalCostsLabel);
		tenancyContract.add(additionalCosts);
	}

	private static void fillPurchasePanel() {
		purchaseContract.add(installmentsNumberLabel);
		purchaseContract.add(installmentsNumber);

		purchaseContract.add(interestRateLabel);
		purchaseContract.add(interestRate);
	}

	private static void fillDeletePanel() {

		for (Contract c : uch.getContracts()) {
			contractBox.addItem((Contract) c);
		}
		deleteContractPanel.add(contractBox);
		deleteContractButton.addActionListener(deleteButtonListener());
		deleteContractPanel.add(deleteContractButton);
	}

	private static ActionListener createButtonListener(final boolean istNurZurMiete) {
		return new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				if (istNurZurMiete) {
					uch.createContract(new TenancyContract(Integer.parseInt(contractNumber.getText()),
							new Date(date.getText()), place.getText(), new Date(startDate.getText()),
							new Date(duration.getText()), Double.parseDouble(additionalCosts.getText())));
				} else {
					uch.createContract(new PurchaseContract(Integer.parseInt(contractNumber.getText()),
							new Date(date.getText()), place.getText(), Integer.parseInt(installmentsNumber.getText()),
							Double.parseDouble(interestRate.getText())));
				}
				contractDialog.dispose();
			}
		};
	}

	private static ActionListener deleteButtonListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contract c = (Contract) contractBox.getSelectedItem();
				uch.deleteContract(c.getContractNo());
				contractDialog.dispose();
			}
		};
	}
		
		
		public static ActionListener createContractListener(final boolean create) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					createContract(create);
				}				
			};
	}
}