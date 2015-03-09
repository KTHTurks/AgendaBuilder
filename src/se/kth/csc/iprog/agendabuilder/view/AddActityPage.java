package se.kth.csc.iprog.agendabuilder.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class AddActityPage extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public AddActityPage() {
		setLayout(null);
		
		textField = new JTextField("Name");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(10, 11, 124, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("Length");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 42, 72, 20);
		add(textField_1);
		
		JLabel lblMin = new JLabel("min");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMin.setBounds(92, 44, 46, 14);
		add(lblMin);
		
		String[] deneme = { "Type" };
		
		JComboBox comboBox = new JComboBox(deneme);
		/*comboBox.addItem("Presentation");
		comboBox.addItem("Studying");
		comboBox.addItem("Break");*/
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(10, 73, 110, 20);
		add(comboBox);
		
		JTextPane txtpnDescribtion = new JTextPane();
		txtpnDescribtion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescribtion.setText("Description");
		txtpnDescribtion.setBounds(10, 104, 181, 185);
		add(txtpnDescribtion);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.addActivity();
			}
		});
		btnAdd.setBounds(42, 301, 117, 29);
		add(btnAdd);

	}
}
