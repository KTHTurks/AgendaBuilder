package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class DayPanel extends JPanel implements Observer{
	private JTextField textField;
	public DayPanel() {
		setLayout(null);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setBounds(57, 30, 84, 16);
		add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(57, 58, 84, 16);
		add(lblEndTime);
		
		JLabel lblTotalLength = new JLabel("Total Length:");
		lblTotalLength.setBounds(57, 86, 84, 16);
		add(lblTotalLength);
		
		textField = new JTextField();
		textField.setText("08:00");
		textField.setBounds(139, 24, 51, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("08:00");
		lblNewLabel.setBounds(146, 58, 61, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setBounds(146, 86, 61, 16);
		add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(21, 114, 257, 341);
		add(panel);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
