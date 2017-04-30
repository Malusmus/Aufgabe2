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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.Gui;
import de.dis2011.data.Apartment;
import de.dis2011.data.Estate;
import de.dis2011.data.EstateAgent;
import de.dis2011.data.House;

public class GuiEstate extends Gui {

	final static JDialog dialog = new JDialog(_main);

	public ActionListener createEstate() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final JPanel pane = new JPanel();
				dialog.setSize(600, 500);
				dialog.add(pane);

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

				final JPanel extraPane = new JPanel();
				pane.add(extraPane);
				final JPanel haus = new JPanel();
				final JPanel flat = new JPanel();

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
							createHouse(dialog, extraPane, haus, flat, makler, city, PostalCode, street, streetnr,
									area);
						} else {
							createApartment(dialog, extraPane, haus, flat, makler, city, PostalCode, street, streetnr,
									area);
						}
					}
				};

				isHouse.addItemListener(boxi);

				pane.setVisible(true);
				extraPane.setVisible(true);
				dialog.setVisible(true);

			}
		};
	}

	private void createHouse(final JDialog dialog, JPanel pane, JPanel haus, JPanel flat, final JTextField makler,
			final JTextField city, final JTextField PostalCode, final JTextField street, final JTextField streetnr,
			final JTextField area) {
		{
			try {
				pane.removeAll();
			} catch (Exception e1) {
			} finally {
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
				try {
					pane.remove(flat);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					pane.add(haus);
				}
			}
		}
	}

	private ActionListener createHouseButton(final JTextField makler, final JTextField city,
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
				dialog.dispose();
			}
		};
	}

	private void createApartment(final JDialog dialog, JPanel pane, JPanel haus, JPanel flat, final JTextField makler,
			final JTextField city, final JTextField PostalCode, final JTextField street, final JTextField streetnr,
			final JTextField area) {

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

		try {
			pane.remove(haus);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pane.add(flat);
		}

		dialog.setVisible(true);
		pane.setVisible(true);
	}

	private ActionListener createApartmentButton(final JTextField makler, final JTextField city,
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
				dialog.dispose();
			}
		};
	}

	public ActionListener changeEstate() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel pane = new JPanel();
				dialog.add(pane);

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
				changeButton.setText("Delete the chosen estate");
				changeButton.addActionListener(changeButtonListener(estates));
				pane.add(changeButton);

				dialog.setSize(500, 600);
				dialog.setVisible(true);
			}
		};
	}

	private static ActionListener changeButtonListener(final JComboBox<Estate> estates) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel pane = new JPanel();
				dialog.setSize(600, 500);
				dialog.add(pane);

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
				final Checkbox isHouse = new Checkbox("check for a house, don't for an apartment",
						current instanceof House);
				pane.add(isHouse);

				final JPanel extraPane = new JPanel();
				pane.add(extraPane);
				final JPanel haus = new JPanel();
				final JPanel flat = new JPanel();

				ItemListener boxi = new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent arg0) {
						if (isHouse.getState()) {
							changeHouse((House) current, dialog, extraPane, haus, flat, makler, city, PostalCode,
									street, streetnr, area);
						} else {
							changeApartment((Apartment) current, dialog, extraPane, haus, flat, makler, city,
									PostalCode, street, streetnr, area);
						}
					}
				};

				isHouse.addItemListener(boxi);

				pane.setVisible(true);
				extraPane.setVisible(true);
				dialog.setVisible(true);
			}
		};
	}

	private static void changeHouse(House h, final JDialog dialog, JPanel pane, JPanel haus, JPanel flat,
			final JTextField makler, final JTextField city, final JTextField PostalCode, final JTextField street,
			final JTextField streetnr, final JTextField area) {
		{
			try {
				pane.removeAll();
			} catch (Exception e1) {
			} finally {
				haus.setLayout(new BoxLayout(haus, BoxLayout.Y_AXIS));

				haus.add(new JLabel("Anzahl Geschosse."));
				String houseFloors = h.getFloors() + "";
				final JTextField floors = new JTextField(houseFloors, 20);
				haus.add(floors);

				haus.add(new JLabel("Preis"));
				String housePrice = h.getPrice() + "";
				final JTextField price = new JTextField(20);
				haus.add(price);

				haus.add(new JLabel("Has Garden"));
				final Checkbox hasGarden = new Checkbox("Hat einen Garten", h.isHasGarden());
				haus.add(hasGarden);

				JButton createHouse = new JButton();
				createHouse.setText("Create Haus");
				createHouse.addActionListener(changeHouseButton(h.getEstateId(), makler, city, PostalCode, street,
						streetnr, area, floors, price, hasGarden));
				haus.add(createHouse);
				try {
					pane.remove(flat);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					pane.add(haus);
				}
			}
		}
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
				JOptionPane.showMessageDialog(null, "Successfully changed", "InfoBox: Change Haus",
						JOptionPane.INFORMATION_MESSAGE);
				dialog.dispose();
			}
		};
	}

	private static void changeApartment(Apartment a, final JDialog dialog, JPanel pane, JPanel haus, JPanel flat,
			final JTextField makler, final JTextField city, final JTextField PostalCode, final JTextField street,
			final JTextField streetnr, final JTextField area) {

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

		try {
			pane.remove(haus);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pane.add(flat);
		}

		dialog.setVisible(true);
		pane.setVisible(true);
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

				JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Change Apartment",
						JOptionPane.INFORMATION_MESSAGE);
				dialog.dispose();
			}
		};
	}

	public ActionListener deleteEstate() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel pane = new JPanel();
				dialog.add(pane);

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

				dialog.setSize(500, 600);
				dialog.setVisible(true);
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
				dialog.dispose();
			}
		};
	}

}
