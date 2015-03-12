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

import se.kth.csc.iprog.agendabuilder.controller.SaveButtonActionListener;
import se.kth.csc.iprog.agendabuilder.model.Activity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AddActivityFrame extends JFrame {
	private JTextField txtName;
	private JTextField txtLength;
	JComboBox comboBox;
	JTextArea textArea;
	public Activity activity;
	JButton btnSave;
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
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Presentation", "Group Work", "Discussion", "Break"}));
		comboBox.setBounds(20, 94, 134, 27);
		getContentPane().add(comboBox);
		
		textArea = new JTextArea();
		textArea.setText("Description");
		textArea.setBounds(30, 133, 215, 136);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		getContentPane().add(textArea);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(40, 281, 101, 29);
		getContentPane().add(btnCancel);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(144, 281, 101, 29);
		getContentPane().add(btnSave);
		
		pack();
		setVisible(true);
	}
	
	public void addSaveListener(SaveButtonActionListener sb)
	{
		btnSave.addActionListener(sb);
	}
	
	public String getName(){
		return txtName.getText();
	}
	public String getDescription(){
		return textArea.getText();
		
	}
	public int getLength(){
		try{
			return Integer.parseInt(txtLength.getText());
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null,"Length should be an integer.");
			return 0;
		}
	}
	public int getSelectedType(){
		return comboBox.getSelectedIndex();
	}
	
	
}
