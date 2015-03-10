package se.kth.csc.iprog.agendabuilder.swing.view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import se.kth.csc.iprog.agendabuilder.model.Activity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AddActivityFrame extends JFrame {
	private JTextField txtName;
	private JTextField txtLength;
	public Activity activity;
	String name;
	String description;
	int length;
	int type;
	public AddActivityFrame() {
		getContentPane().setPreferredSize(new Dimension(280,333));
		getContentPane().setLayout(null);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(20, 16, 134, 28);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtLength = new JTextField();
		txtLength.setText("Length");
		txtLength.setBounds(20, 54, 81, 28);
		getContentPane().add(txtLength);
		txtLength.setColumns(10);
		
		JLabel lblMin = new JLabel("min");
		lblMin.setBounds(106, 60, 61, 16);
		getContentPane().add(lblMin);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Presentation", "Group Work", "Discussion", "Break"}));
		comboBox.setBounds(20, 94, 134, 27);
		getContentPane().add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("Description");
		textArea.setBounds(30, 133, 215, 136);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		getContentPane().add(textArea);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(40, 281, 101, 29);
		getContentPane().add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				name = txtName.getText();
				description = textArea.getText();
				try{
					length = Integer.parseInt(txtLength.getText());
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null,"Length should be an integer.");
				}
				type = comboBox.getSelectedIndex();
				activity = new Activity(name,description,length,type);
			}
		});
		btnSave.setBounds(144, 281, 101, 29);
		getContentPane().add(btnSave);
		
		pack();
		setVisible(true);
	}
}
