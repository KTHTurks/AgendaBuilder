package se.kth.csc.iprog.agendabuilder.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

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
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(10, 73, 72, 20);
		add(comboBox);
		
		JTextPane txtpnDescribtion = new JTextPane();
		txtpnDescribtion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescribtion.setText("Description");
		txtpnDescribtion.setBounds(10, 104, 181, 185);
		add(txtpnDescribtion);

	}
}
