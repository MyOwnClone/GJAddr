package cz.vutbr.fit.gja.gjaddr.gui;

import cz.vutbr.fit.gja.gjaddr.persistancelayer.*;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import javax.swing.*;

/**
 * Panel with contact detail
 *
 * @author Bc. Jan Kaláb <xkalab00@stud.fit,vutbr.cz>
 */
class DetailPanel extends JPanel {
	static final long serialVersionUID = 0;
	private final JLabel name = new JLabel();
	private final JPanel address = new JPanel();
	private final JLabel emails = new JLabel();
	private final JLabel phones = new JLabel();
	private final JLabel webs = new JLabel();
	private final JLabel birthday = new JLabel();
	private final JLabel note = new JLabel();
	private final JLabel nameIcon = new JLabel();
	private final JLabel photo = new JLabel();
	JScrollPane detailScrollPane;

	/**
	 * Constructor
	 */
	public DetailPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		final JLabel label = new JLabel("Detail");
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);

		// create panel with name and with birthday icon
		final JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		nameIcon.setIcon(new ImageIcon(getClass().getResource("/res/present.png")));
		nameIcon.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		nameIcon.setVisible(false);
		namePanel.add(nameIcon);
		namePanel.add(name);
		namePanel.add(photo);
		/*
		namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		namePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		*/
		add(namePanel);

		final JPanel detailPanel = new JPanel(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		detailPanel.add(new JLabel("<html><b>Address:</b></html>"), c);
		c.gridx = 1;
		address.setLayout(new BoxLayout(address, BoxLayout.PAGE_AXIS));
		detailPanel.add(address, c);
		c.gridx = 0;
		c.gridy++;
		detailPanel.add(new JLabel("<html><b>Email:</b></html>"), c);
		c.gridx = 1;
		detailPanel.add(emails, c);
		c.gridx = 0;
		c.gridy++;
		detailPanel.add(new JLabel("<html><b>Phone:</b></html>"), c);
		c.gridx = 1;
		detailPanel.add(phones, c);
		c.gridx = 0;
		c.gridy++;
		detailPanel.add(new JLabel("<html><b>Webs:</b></html>"), c);
		c.gridx = 1;
		detailPanel.add(webs, c);
		c.gridx = 0;
		c.gridy++;
		detailPanel.add(new JLabel("<html><b>Birthday:</b></html>"), c);
		c.gridx = 1;
		detailPanel.add(birthday, c);
		c.gridx = 0;
		c.gridy++;
		detailPanel.add(new JLabel("<html><b>Note:</b></html>"), c);
		c.gridx = 1;
		detailPanel.add(note, c);
		c.gridy++;
		c.weighty = 1;
		detailPanel.add(Box.createVerticalGlue(), c);
		detailScrollPane = new JScrollPane(detailPanel);
		detailScrollPane.setBorder(BorderFactory.createEmptyBorder());
		detailScrollPane.setVisible(false);
		add(detailScrollPane);
	}

	void show(Contact contact) {
		if (contact != null) {
			if (contact.hasBirthday()) {
				nameIcon.setVisible(true);
			} else {
				nameIcon.setVisible(false);
			}
			if (contact.getPhoto() != null) {
				photo.setIcon(contact.getPhoto());
				photo.setVisible(true);
			} else {
				photo.setVisible(false);
			}
			name.setText(String.format("<html><h1>%s %s</h1></html>", contact.getFirstName(), contact.getSurName()));
			address.removeAll();
			for (Address a : contact.getAdresses()) {
				if (!a.getAddress().isEmpty()){
					//System.out.println(a);
					JLabel l = new JLabel();
					l.setVerticalTextPosition(JLabel.TOP);
					l.setHorizontalTextPosition(JLabel.CENTER);
					l.setAlignmentX(Component.LEFT_ALIGNMENT);
					l.setText(a.getAddress());
					try {
						l.setIcon(new ImageIcon(new URL("http://maps.google.com/maps/api/staticmap?size=128x128&sensor=false&markers=" + URLEncoder.encode(l.getText(), "utf8"))));
					} catch (IOException e) {
						System.err.println(e);
					}
					address.add(l);
				}
			}
			emails.setText("<html>" + contact.getAllEmails().replaceAll(", ", "<br>") + "</html>");
			phones.setText("<html>" + contact.getAllPhones().replaceAll(", ", "<br>") + "</html>");
			if (contact.getDateOfBirth() != null) {
				birthday.setText(DateFormat.getDateInstance().format(contact.getDateOfBirth()));
			} else {
				birthday.setText("");
			}
			note.setText(contact.getNote());
			detailScrollPane.setVisible(true);
		}
	}
}
