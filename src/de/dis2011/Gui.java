package de.dis2011;

import java.awt.Checkbox;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.WindowConstants;

import de.dis2011.data.EstateAgent;
import de.dis2011.data.Person;

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

		addCreateMaklerListener(createMakler);
		addDeleteMarklerListenener(deleteMakler);
		addPersonenCreateListener(createKunde);
		addPersonenDeleteListener(deleteKunde);
		addEstateCreateListener(createKunde);
		addEstateDeleteListener(deleteKunde);

	}

	private void addCreateMaklerListener(JButton createMakler) {
		createMakler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final JDialog dialog = new JDialog(_main);
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
						dialog.dispose();

					}
				});
				pane.add(_hammer);
				dialog.setVisible(true);
				pane.setVisible(true);
			}
		});
	}

	private void addDeleteMarklerListenener(JButton deleteMakler) {
		deleteMakler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMakler();
			}

			private void deleteMakler() {
				final JDialog dialog = new JDialog(_main);
				JPanel pane = new JPanel();
				dialog.add(pane);

				int current;

				final JComboBox<EstateAgent> makler = new JComboBox<EstateAgent>();

				makler.setSize(300, 100);

				pane.add(makler);

				ArrayList<EstateAgent> maklerListe = uch.getEstateAgents();

				for (EstateAgent e : maklerListe) {
					makler.addItem(e);
				}

				_hammer.setText("Delete the chosen estate agent");
				_hammer.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						EstateAgent gefeuert = (EstateAgent) makler.getSelectedItem();
						uch.fireEstateAgent(gefeuert.getEstateAgentId());
						dialog.dispose();
					}
				});

				pane.add(_hammer);

				dialog.setSize(500, 600);
				dialog.setVisible(true);
			}
		});
	}

	private void addPersonenCreateListener(JButton createKunde) {
		createKunde.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JDialog dialog = new JDialog(_main);
				JPanel pane = new JPanel();
				dialog.setSize(600, 500);
				dialog.add(pane);
				pane.add(new JLabel("Name"));
				final JTextField name = new JTextField(20);
				pane.add(name);
				pane.add(new JLabel("Vorname"));
				final JTextField vorname = new JTextField(20);
				pane.add(vorname);
				pane.add(new JLabel("Adresse"));
				final JTextField adresse = new JTextField(20);
				pane.add(adresse);

				_hammer.setText("Create Person");
				_hammer.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						uch.createPerson(name.getText(), vorname.getText(), adresse.getText());
						JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Makler",
								JOptionPane.INFORMATION_MESSAGE);
						dialog.dispose();

					}
				});
				pane.add(_hammer);
				dialog.setVisible(true);
				pane.setVisible(true);
			}
		});
	}

	private void addPersonenDeleteListener(JButton deleteKunde) {
		deleteKunde.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteKunde();
			}

			private void deleteKunde() {
				final JDialog dialog = new JDialog(_main);
				JPanel pane = new JPanel();
				dialog.add(pane);

				final JComboBox<Person> kunde = new JComboBox<Person>();

				kunde.setSize(300, 100);

				pane.add(kunde);

				ArrayList<Person> kundenListe = uch.getPersonen();

				for (Person p : kundenListe) {
					kunde.addItem(p);
				}

				_hammer.setText("Delete the chosen person");
				_hammer.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Person gefeuert = (Person) kunde.getSelectedItem();
						uch.killPerson(gefeuert.getId());
						dialog.dispose();
					}
				});

				pane.add(_hammer);

				dialog.setSize(500, 600);
				dialog.setVisible(true);
			}
		});
	}

	private void addEstateCreateListener(JButton createEstate) {
		createEstate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JDialog dialog = new JDialog(_main);
				JPanel pane = new JPanel();
				dialog.setSize(600, 500);
				dialog.add(pane);
				//id agent city postalcode street streetnr squarearea
				pane.add(new JLabel("EstateAgent"));
				final JTextField makler = new JTextField(20);
				pane.add(makler);
				pane.add(new JLabel("City"));
				final JTextField city = new JTextField(20);
				pane.add(city);
				pane.add(new JLabel("PostalCode"));
				final JTextField PostalCode = new JTextField(20);
				pane.add(PostalCode);
				pane.add(new JLabel("Street"));
				final JTextField street = new JTextField(20);
				pane.add(street);
				pane.add(new JLabel("StreetNr"));
				final JTextField streetnr = new JTextField(20);
				pane.add(streetnr);
				pane.add(new JLabel("SquareArea"));
				final JTextField area = new JTextField(20);
				pane.add(area);
				Checkbox isHouse = new Checkbox("check for a house, don't for an apartment", true);
				pane.add(isHouse);
				if (isHouse.getState()){
					JPanel haus = new JPanel();
					// floors price hasGarden
					JLabel floors = new JLabel("Anzahl Geschosse.");
					JTextField tfloors = new JTextField();
					JLabel price = new JLabel("Preis");
					JTextField tprice = new JTextField();
					JLabel garden = new JLabel("garden");
					Checkbox hasGarden = new Checkbox("Hat einen Garten", false);
					haus.add(floors);
					haus.add(tfloors);
					haus.add(price);
					haus.add(tprice);
					haus.add(garden);
					haus.add(hasGarden);
					
					_hammer.setText("Create Haus");
					_hammer.addActionListener(new ActionListener() {
					
						@Override
						public void actionPerformed(ActionEvent e) {
							uch.createHouse(makler.getText(), city.getText(), PostalCode.getText(), street.getText(), streetnr.getText(), Double.parseDouble(area.getText()), Integer.parseInt(tprice.getText()), Integer.parseInt(tfloors.getText()), hasGarden.getState());
							JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Haus",
									JOptionPane.INFORMATION_MESSAGE);
							dialog.dispose();

						}
					});
					flat.add(_hammer);
					pane.add(haus);				} else {
JPanel flat = new JPanel();
					JLabel floor = new JLabel("Geschoss.");
					JTextField tfloor = new JTextField();
					JLabel price = new JLabel("Miete");
					JTextField tprice = new JTextField();
					JLabel rooms = new JLabel("Räume");
					JTextField trooms = new JTextField();
					JLabel balcony = new JLabel("Balkon");
					Checkbox hasBalcony = new Checkbox("Hat einen Balkon", false);
					JLabel kitchen = new JLabel("Küche");
					Checkbox hasKitchen = new Checkbox("Hat eine Einbauküche", false);
					
					flat.add(floor);
					flat.add(tfloor);
					flat.add(price);
					flat.add(tprice);
					flat.add(rooms);
					flat.add(trooms);
					flat.add(balcony);
					flat.add(hasBalcony);
					flat.add(kitchen);
					flat.add(hasKitchen);
					
					_hammer.setText("Create Apartment");
					flat.add(_hammer);
					pane.add(flat);
					_hammer.addActionListener(new ActionListener() {
						// floorInt rentInt roomsInt hasBalcony builtInKitchen

						@Override
						public void actionPerformed(ActionEvent e) {
							uch.createApartment(EstateAgent.getText(), city.getText(), PostalCode.getText(), street.getText(), streetnr.getText(), Double.parseDouble(area.getText()), 
									Integer.parseInt(tfloor.getText()), Integer.parseInt(tprice.getText()), Integer.parseInt(trooms.getText()), hasBalcony.getState(), hasKitchen.getState());
							JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Haus",
									JOptionPane.INFORMATION_MESSAGE);
							dialog.dispose();

						}
					});

					
				}

	dialog.setVisible(true);pane.setVisible(true);

	}});}

	private void addEstateDeleteListener(JButton deleteKunde) {
}
