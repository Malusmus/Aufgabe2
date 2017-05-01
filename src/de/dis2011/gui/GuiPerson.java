package de.dis2011.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dis2011.Main;

public class GuiPerson {

	final JDialog dialog = new JDialog();
	JPanel pane = new JPanel();

	public GuiPerson() {

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

		JButton create = new JButton();
		create.setText("Create Person");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.uch.createPerson(name.getText(), vorname.getText(), adresse.getText());
				JOptionPane.showMessageDialog(null, "Successfully created", "InfoBox: Create Makler",
						JOptionPane.INFORMATION_MESSAGE);
				dialog.dispose();

			}
		});
		pane.add(create);
		dialog.setVisible(true);
		pane.setVisible(true);
	}
}
