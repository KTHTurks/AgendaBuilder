package se.kth.csc.iprog.agendabuilder.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class MainView extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MainView() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("+ Add Activity");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 37, 155, 23);
		add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 71, 155, 362);
		add(panel);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartTime.setBounds(185, 47, 82, 23);
		add(lblStartTime);
		
		textField = new JTextField(" 08:00 ");
		textField.setBounds(261, 49, 40, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("End Time:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(185, 71, 82, 23);
		add(label);
		
		JLabel lblNewLabel = new JLabel(" 08:00 ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(255, 75, 46, 14);
		add(lblNewLabel);
		
		JLabel label_1 = new JLabel("Total Length:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(185, 92, 82, 23);
		add(label_1);
		
		JLabel label_2 = new JLabel(" 0 min ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(265, 97, 46, 14);
		add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(185, 126, 155, 306);
		add(panel_1);
		
		JButton btnNewButton_1 = new JButton("+ Add a Day");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(360, 256, 120, 69);
		add(btnNewButton_1);
		
		
	}
}
