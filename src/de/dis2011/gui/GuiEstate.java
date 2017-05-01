package de.dis2011.gui;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import de.dis2011.data.Apartment;
import de.dis2011.data.Estate;
import de.dis2011.data.House;

public class GuiEstate  {

	static JDialog dialogCreate;
	static JDialog dialogChange;
	static JDialog dialogDelete;
	
	public final static UseCaseHandler uch = Main.uch;
	public static JFrame _main;

	public static ActionListener createEstate() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dialogCreate = new JDialog(_main);
				final JPanel pane = new JPanel();
				dialogCreate.setSize(600, 500);
				dialogCreate.add(pane);

				// Fixme sollte Auswahl sein
				// Agent
				pane.add(new JLabel("EstateAgent"));
				final JTextField makler = new JTextField(20);
				pane.add(makler);
				// Stadt
				pane.add(new JLabel("City"));
				final JTextField city = new JTextField(20);
				pane.add(city);
				// Postleitzahl
				pane.add(new JLabel("PostalCode"));
				final JTextField PostalCode = new JTextField(20);
				pane.add(PostalCode);
				// Straße
				pane.add(new JLabel("Street"));
				final JTextField street = new JTextField(20);
				pane.add(street);
				// Hausnummer
				pane.add(new JLabel("StreetNr"));
				final JTextField streetnr = new JTextField(20);
				pane.add(streetnr);
				// Quadratmeter
				pane.add(new JLabel("SquareArea"));
				final JTextField area = new JTextField(20);
				pane.add(area);
				// Typabfrage
				final Checkbox isHouse = new Checkbox("check for a house, don't for an apartment", true);
				pane.add(isHouse);

				final JPanel haus = new JPanel();
				final JPanel flat = new JPanel();
				
				createHouse(haus, makler, city, PostalCode, street, streetnr, area);
				createApartment(flat, makler, city, PostalCode, street, streetnr, area);
				
				haus.setVisible(true);
				flat.setVisible(false);

				/*
				 * if (isHouse.getState()) { createHouse(dialog, extraPane,
				 * haus, flat, makler, city, PostalCode, street, streetnr,
				 * area); } else { createApartment(dialog, extraPane, haus,
				 * flat, makler, city, PostalCode, street, streetnr, area); }
				 */

				ItemListener boxi = new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent arg0) {
						if (isHouse.getState()) {
							haus.setVisible(true); flat.setVisible(false);
						} else {
							haus.setVisible(false); flat.setVisible(true);
						}
					}
				};

				isHouse.addItemListener(boxi);

				pane.setVisible(true);
				
				pane.add(haus);
				pane.add(flat);

				
				dialogCreate.setVisible(true);

			}
		};
	}

	private static void createHouse(JPanel haus, final JTextField makler, final JTextField city,
			final JTextField PostalCode, final JTextField street, final JTextField streetnr, final JTextField area) {
		{
			// Log
			System.out.println("Haus!!");
			haus.setLayout(new BoxLayout(haus, BoxLayout.Y_AXIS));

			final JTextField floors = new JTextField(20);
			haus.add(new JLabel("Anzahl Geschosse."));
			haus.add(floors);

			final JTextField price = new JTextField(20);
			haus.add(new JLabel("Preis"));
			haus.add(price);

			final Checkbox hasGarden = new Checkbox("Hat einen Garten", false);
			haus.add(new JLabel("Has Garden"));
			haus.add(hasGarden);

			JButton createHouse = new JButton();
			createHouse.setText("Create Haus");
			createHouse.addActionListener(
					createHouseButton(makler, city, PostalCode, street, streetnr, area, floors, price, hasGarden));
			haus.add(createHouse);
		}
	}

	private static ActionListener createHouseButton(final JTextField makler, final JTextField city,
			final JTextField PostalCode, final JTextField street, final JTextField streetnr, final JTextField area,
			final JTextField floors, final JTextField price, final Checkbox hasGarden) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uch.createHouse(Integer.parseInt(makler.getText()), city.getText(), PostalCode.getText(),
						street.getText(), streetnr.getText(), Double.parseDouble(area.getText()),
						Integer.parseInt(price.getText()), Integer.parseInt(floors.getText()), hasGarden.getState());
				JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Haus",
						JOptionPane.INFORMATION_MESSAGE);
				dialogCreate.dispose();
			}
		};
	}

	private static void createApartment(JPanel flat, final JTextField makler, final JTextField city,
			final JTextField PostalCode, final JTextField street, final JTextField streetnr, final JTextField area) {

		flat.setLayout(new BoxLayout(flat, BoxLayout.Y_AXIS));
		// Stockwerk
		flat.add(new JLabel("Geschoss."));
		final JTextField floor = new JTextField(20);
		flat.add(floor);
		// Miete
		final JTextField rent = new JTextField(20);
		flat.add(new JLabel("Miete"));
		flat.add(rent);
		// Anzahl Räume
		final JTextField rooms = new JTextField(20);
		flat.add(new JLabel("Räume"));
		flat.add(rooms);
		// Hat Balkon?
		final JCheckBox hasBalcony = new JCheckBox("Hat einen Balkon", false);
		flat.add(new JLabel("Balkon"));
		flat.add(hasBalcony);
		// Hat Einbauküche?
		final JCheckBox hasKitchen = new JCheckBox("Hat eine Einbauküche", false);
		flat.add(new JLabel("Küche"));
		flat.add(hasKitchen);
		// Button
		JButton createApartment = new JButton();
		createApartment.setText("Create Apartment");
		createApartment.addActionListener(createApartmentButton(makler, city, PostalCode, street, streetnr, area, floor,
				rent, rooms, hasBalcony, hasKitchen));
		flat.add(createApartment);
	}

	private static ActionListener createApartmentButton(final JTextField makler, final JTextField city,
			final JTextField PostalCode, final JTextField street, final JTextField streetnr, final JTextField area,
			final JTextField floor, final JTextField rent, final JTextField rooms, final JCheckBox hasBalcony,
			final JCheckBox hasKitchen) {

		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uch.createApartment(Integer.parseInt(makler.getText()), city.getText(), PostalCode.getText(),
						street.getText(), streetnr.getText(), Double.parseDouble(area.getText()),
						Integer.parseInt(floor.getText()), Integer.parseInt(rent.getText()),
						Integer.parseInt(rooms.getText()), hasBalcony.isSelected(), hasKitchen.isSelected());

				JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Apartment",
						JOptionPane.INFORMATION_MESSAGE);
				dialogCreate.dispose();
			}
		};
	}

	public static ActionListener changeEstate() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogChange = new JDialog(_main);
				JPanel pane = new JPanel();
				dialogChange.add(pane);

				final JComboBox<Estate> estates = new JComboBox<Estate>();
				estates.setSize(300, 100);
				pane.add(estates);

				ArrayList<Estate> estateListe = new ArrayList<Estate>();

				for (House h : uch.getHouses()) {
					estateListe.add((Estate) h);
				}

				for (Apartment a : uch.getApartments()) {
					estateListe.add((Estate) a);
				}

				for (Estate es : estateListe) {
					estates.addItem(es);
				}

				JButton changeButton = new JButton();
				changeButton.setText("Change the chosen estate");
				changeButton.addActionListener(changeButtonListener(estates));
				pane.add(changeButton);

				dialogChange.setSize(500, 600);
				dialogChange.setVisible(true);
			}
		};
	}

	private static ActionListener changeButtonListener(final JComboBox<Estate> estates) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogChange.dispose();
				dialogChange = new JDialog(_main);
				JPanel pane = new JPanel();
				dialogChange.setSize(600, 500);
				dialogChange.add(pane);

				// Aktuelles Estate
				final Estate current = (Estate) estates.getSelectedItem();
				// Agent
				pane.add(new JLabel("EstateAgent"));
				String maklerId = current.getEstateAgentId() + "";
				final JTextField makler = new JTextField(maklerId, 20);
				pane.add(makler);
				// Stadt
				pane.add(new JLabel("City"));
				String estateCity = current.getCity();
				final JTextField city = new JTextField(estateCity, 20);
				pane.add(city);
				// Postleitzahl
				pane.add(new JLabel("PostalCode"));
				String estatePostalCode = current.getPostalCode();
				final JTextField PostalCode = new JTextField(estatePostalCode, 20);
				pane.add(PostalCode);
				// Straße
				pane.add(new JLabel("Street"));
				String estateStreet = current.getStreet();
				final JTextField street = new JTextField(estateStreet, 20);
				pane.add(street);
				// Hausnummer
				pane.add(new JLabel("StreetNr"));
				String estateStreetNr = current.getStreetNumber();
				final JTextField streetnr = new JTextField(estateStreetNr, 20);
				pane.add(streetnr);
				// Quadratmeter
				pane.add(new JLabel("SquareArea"));
				String estateSquareArea = current.getSquareArea() + "";
				final JTextField area = new JTextField(estateSquareArea, 20);
				pane.add(area);
				// Typabfrage
				boolean check;
				if (current instanceof House) {
					check = true;
				} else {
					check = false;
				}

				final JPanel extraPane = new JPanel();
				pane.add(extraPane);
				final JPanel haus = new JPanel();
				final JPanel flat = new JPanel();

				if (check) {
					changeHouse((House) current, extraPane, haus, makler, city, PostalCode, street, streetnr, area);
				} else {
					changeApartment((Apartment) current, extraPane, flat, makler, city, PostalCode, street, streetnr,
							area);
				}

				pane.setVisible(true);
				extraPane.setVisible(true);
				pane.add(extraPane);
				dialogChange.setVisible(true);
			}
		};
	}

	private static void changeHouse(House h, JPanel extraPane, JPanel haus, final JTextField makler, final JTextField city,
			final JTextField PostalCode, final JTextField street, final JTextField streetnr, final JTextField area) {

		haus.setLayout(new BoxLayout(haus, BoxLayout.Y_AXIS));
		// Stockwerke
		haus.add(new JLabel("Anzahl Geschosse."));
		String houseFloors = h.getFloors() + "";
		final JTextField floors = new JTextField(houseFloors, 20);
		haus.add(floors);
		// Preis
		haus.add(new JLabel("Preis"));
		String housePrice = h.getPrice() + "";
		final JTextField price = new JTextField(housePrice, 20);
		haus.add(price);
		// Garten?
		haus.add(new JLabel("Has Garden"));
		final Checkbox hasGarden = new Checkbox("Hat einen Garten", h.isHasGarden());
		haus.add(hasGarden);
		// Button
		JButton createHouse = new JButton();
		createHouse.setText("Change Haus");
		createHouse.addActionListener(changeHouseButton(h.getEstateId(), makler, city, PostalCode, street, streetnr,
				area, floors, price, hasGarden));
		haus.add(createHouse);

		extraPane.add(haus);
	}

	private static ActionListener changeHouseButton(final int estateID, final JTextField makler, final JTextField city,
			final JTextField PostalCode, final JTextField street, final JTextField streetnr, final JTextField area,
			final JTextField floors, final JTextField price, final Checkbox hasGarden) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uch.killHouse(estateID);
				uch.createHouse(Integer.parseInt(makler.getText()), city.getText(), PostalCode.getText(),
						street.getText(), streetnr.getText(), Double.parseDouble(area.getText()),
						Integer.parseInt(price.getText()), Integer.parseInt(floors.getText()), hasGarden.getState());
				JOptionPane.showMessageDialog(null, "House successfully changed", "InfoBox: Change Haus",
						JOptionPane.INFORMATION_MESSAGE);
				dialogChange.dispose();
			}
		};
	}

	private static void changeApartment(Apartment a, JPanel extraPane, JPanel flat, final JTextField makler,
			final JTextField city, final JTextField PostalCode, final JTextField street, final JTextField streetnr,
			final JTextField area) {

		flat.setLayout(new BoxLayout(flat, BoxLayout.Y_AXIS));
		// Stockwerk
		flat.add(new JLabel("Geschoss."));
		String apartmentFloor = a.getFloor() + "";
		final JTextField floor = new JTextField(apartmentFloor, 20);
		flat.add(floor);
		// Miete
		flat.add(new JLabel("Miete"));
		String apartmentRent = a.getRent() + "";
		final JTextField rent = new JTextField(apartmentRent, 20);
		flat.add(rent);
		// Anzahl Räume
		flat.add(new JLabel("Räume"));
		String apartmentRooms = a.getRooms() + "";
		final JTextField rooms = new JTextField(apartmentRooms, 20);
		flat.add(rooms);
		// Hat Balkon?
		final JCheckBox hasBalcony = new JCheckBox("Hat einen Balkon", a.isHasBalcony());
		flat.add(new JLabel("Balkon"));
		flat.add(hasBalcony);
		// Hat Einbauküche?
		final JCheckBox hasKitchen = new JCheckBox("Hat eine Einbauküche", a.isBuiltInKitchen());
		flat.add(new JLabel("Küche"));
		flat.add(hasKitchen);
		// Button
		JButton changeApartment = new JButton();
		changeApartment.setText("Change Apartment");
		changeApartment.addActionListener(changeApartmentButton(a.getEstateId(), makler, city, PostalCode, street,
				streetnr, area, floor, rent, rooms, hasBalcony, hasKitchen));
		flat.add(changeApartment);

		extraPane.add(flat);
	}

	private static ActionListener changeApartmentButton(final int estateID, final JTextField makler,
			final JTextField city, final JTextField PostalCode, final JTextField street, final JTextField streetnr,
			final JTextField area, final JTextField floor, final JTextField rent, final JTextField rooms,
			final JCheckBox hasBalcony, final JCheckBox hasKitchen) {

		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uch.killApartment(estateID);
				uch.createApartment(Integer.parseInt(makler.getText()), city.getText(), PostalCode.getText(),
						street.getText(), streetnr.getText(), Double.parseDouble(area.getText()),
						Integer.parseInt(floor.getText()), Integer.parseInt(rent.getText()),
						Integer.parseInt(rooms.getText()), hasBalcony.isSelected(), hasKitchen.isSelected());

				JOptionPane.showMessageDialog(null, "Apartment successfully changed", "InfoBox: Change Apartment",
						JOptionPane.INFORMATION_MESSAGE);
				dialogChange.dispose();
			}
		};
	}

	public static ActionListener deleteEstate() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogDelete = new JDialog(_main);
				JPanel pane = new JPanel();
				dialogDelete.add(pane);

				final JComboBox<Estate> estates = new JComboBox<Estate>();
				estates.setSize(300, 100);
				pane.add(estates);

				ArrayList<Estate> estateListe = new ArrayList<Estate>();

				for (House h : uch.getHouses()) {
					estateListe.add((Estate) h);
				}

				for (Apartment a : uch.getApartments()) {
					estateListe.add((Estate) a);
				}

				for (Estate es : estateListe) {
					estates.addItem(es);
				}

				JButton deleteButton = new JButton();
				deleteButton.setText("Delete the chosen estate");
				deleteButton.addActionListener(deleteButtonListener(estates));
				pane.add(deleteButton);

				dialogDelete.setSize(500, 600);
				dialogDelete.setVisible(true);
			}
		};
	}

	private static ActionListener deleteButtonListener(final JComboBox<Estate> estates) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Estate entfernt = (Estate) estates.getSelectedItem();
				try {
					uch.killApartment(entfernt.getEstateId());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					uch.killHouse(entfernt.getEstateId());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialogDelete.dispose();
			}
		};
	}

}
