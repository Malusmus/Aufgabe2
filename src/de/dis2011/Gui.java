package de.dis2011;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.WindowConstants;

import de.dis2011.data.Apartment;
import de.dis2011.data.Estate;
import de.dis2011.data.House;
import de.dis2011.data.Person;
import de.dis2011.gui.Login;

public class Gui {

	public final static UseCaseHandler uch = Main.uch;

	public static JFrame _main;
	

	public Gui() {
		_main = new JFrame();
		_main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		_main.setSize(500, 500);
		new Login(_main, uch);
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

				JButton _hammer = new JButton();
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

				JButton _hammer = new JButton();
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
	};

	



}
