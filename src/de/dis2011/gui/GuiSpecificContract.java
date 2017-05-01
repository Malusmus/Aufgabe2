package de.dis2011.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dis2011.Main;
import de.dis2011.data.Apartment;
import de.dis2011.data.Contract;
import de.dis2011.data.Estate;
import de.dis2011.data.House;
import de.dis2011.data.Person;
import de.dis2011.data.PurchaseContract;
import de.dis2011.data.Rents;
import de.dis2011.data.Sells;
import de.dis2011.data.TenancyContract;

public class GuiSpecificContract {

	static int width = 500;
	static int height = 500;

	static JDialog createSpecificContract = new JDialog();
	static JDialog deleteSpecificContract = new JDialog();
	static JDialog showUsEverything = new JDialog();

	// Sells(PurchaseContract purchaseContract, House house, Person person)
	// Rents(TenancyContract tenancy, Apartment flat, Person renter)

	static JComboBox<Contract> contracts = new JComboBox<Contract>();
	static JComboBox<Estate> estates = new JComboBox<Estate>();

	static JComboBox<Person> persons = new JComboBox<Person>();

	static JButton addChoosenPerson = new JButton("Add the chosen Person");
	static JButton createNewPerson = new JButton("Create a new Person");
	static JPanel choosenPersons = new JPanel();

	static JButton makeContract = new JButton("Create Sale respectively Rent");
	static JLabel warnung = new JLabel("");

	static public ActionListener createSpecificContract() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fillCreatePanel();
			}
		};
	}

	static public ActionListener deleteSpecificContract() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fillDeletePanel();
			}
		};
	}

	static public ActionListener showUs() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showAll();
			}
		};
	}

	static private void fillCreatePanel() {
		createSpecificContract = new JDialog();
		createSpecificContract.setSize(width, height);
		JPanel pane = new JPanel();
		createSpecificContract.add(pane);

		contracts = new JComboBox<Contract>();
		for (Contract c : Main.uch.getContracts()) {
			contracts.addItem(c);
		}
		pane.add(contracts);
		estates = new JComboBox<Estate>();
		for (Apartment a : Main.uch.getApartments()) {
			estates.addItem((Estate) a);
		}

		for (House h : Main.uch.getHouses()) {
			estates.addItem((Estate) h);
		}

		pane.add(estates);
		persons = new JComboBox<Person>();
		for (Person p : Main.uch.getPersonen()) {
			persons.addItem(p);
		}

		pane.add(persons);

		pane.add(addChoosenPerson);
		pane.add(createNewPerson);
		pane.add(choosenPersons);
		pane.add(makeContract);
		pane.add(warnung);

		addChoosenPerson.addActionListener(addChosenOne());
		createNewPerson.addActionListener(createNewPerson());
		makeContract.addActionListener(makeContract());

		createSpecificContract.setVisible(true);
	}

	private static ActionListener addChosenOne() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PersonPanel persopan = new PersonPanel((Person) persons.getSelectedItem());
				choosenPersons.add(persopan.getPersonPanel());
				createSpecificContract.repaint();
			}

		};
	}

	private static ActionListener createNewPerson() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GuiPerson();
				createSpecificContract.dispose();
			}
		};
	}

	private static ActionListener makeContract() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Contract c = (Contract) contracts.getSelectedItem();
				Estate es = (Estate) estates.getSelectedItem();

				ArrayList<Person> vertragspartner = new ArrayList<Person>();
				ArrayList<PersonPanel> gotten = new ArrayList<PersonPanel>();
				for (int i = 0; i < choosenPersons.getComponents().length; ++i) {
					gotten.add((PersonPanel) choosenPersons.getComponents()[i]);
				}
				for (PersonPanel pp : gotten) {
					vertragspartner.add(pp.getPersonPanelPerson());
				}

				if ((c instanceof PurchaseContract && es instanceof House)
						|| (c instanceof TenancyContract && es instanceof Apartment) || vertragspartner.size() < 1) {

					if (es instanceof House) {
						for (Person perso : vertragspartner) {
							Main.uch.createSale(new Sells((PurchaseContract) c, (House) es, perso));
						}
					} else {
						for (Person perso : vertragspartner) {
							Main.uch.createRent(new Rents((TenancyContract) c, (Apartment) es, perso));
						}
						choosenPersons = new JPanel();
						createSpecificContract.repaint();
						createSpecificContract.dispose();
					}
				} else {
					warnung.setText(
							"Das war nicht korrekt. Kombiniere einen Kaufvertrag mit einem Haus oder einen Mietvertrag mit einer Wohnung, während mehr als eine Person zugeordnet ist.");
				}

			}
		};
	}

	static private void fillDeletePanel() {
		JComboBox<Object> allOfEm = new JComboBox();
		ArrayList<Sells> liste = Main.uch.getSells();
		for (Sells verkauf : liste) {
			allOfEm.addItem(verkauf);
		}
		ArrayList<Rents> listeliste = Main.uch.getRents();
		for (Rents vermietung : listeliste) {
			allOfEm.addItem(vermietung);
		}
		deleteSpecificContract = new JDialog();
		deleteSpecificContract.setSize(width, height);
		JPanel pane = new JPanel();
		pane.add(allOfEm);
		JButton deleteThis = new JButton("Ausgewähltes Zerschreddern.");
		pane.add(deleteThis);
		deleteThis.addActionListener(deleteContract(allOfEm));
		deleteSpecificContract.add(pane);
		deleteSpecificContract.setVisible(true);
	}

	private static ActionListener deleteContract(final JComboBox<Object> box) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object o = box.getSelectedItem();
				if (o instanceof Sells) {
					Main.uch.deleteSale((Sells) o);
				} else {
					Main.uch.deleteRent((Rents) o);
				}
				deleteSpecificContract.dispose();
			}
		};
	}

	private static void showAll() {
		JComboBox<Object> allOfEm = new JComboBox();
		ArrayList<Sells> liste = Main.uch.getSells();
		for (Sells verkauf : liste) {
			allOfEm.addItem(verkauf);
		}
		ArrayList<Rents> listeliste = Main.uch.getRents();
		for (Rents vermietung : listeliste) {
			allOfEm.addItem(vermietung);
		}
		showUsEverything = new JDialog();
		showUsEverything.setSize(width, height);
		JPanel pane = new JPanel();
		pane.add(allOfEm);
		showUsEverything.add(pane);
		showUsEverything.setVisible(true);
	}
}
