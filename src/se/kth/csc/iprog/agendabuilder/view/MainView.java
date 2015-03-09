package se.kth.csc.iprog.agendabuilder.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Canvas;

public class MainView extends JPanel {
	private JTextField textField;
	public JButton addActButton;
	public JButton addDayButton;

	/**
	 * Create the panel.
	 */
	public MainView() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 71, 155, 362);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMin = new JLabel("10 min");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMin.setBounds(10, 11, 46, 14);
		panel.add(lblMin);

		
		
		
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
		
		addDayButton = new JButton("+ Add a Day");
		addDayButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addNewDay();
			}
		});
		addDayButton.setBounds(360, 256, 120, 69);
		add(addDayButton);
		
		addActButton = new JButton("+ Add Activity");
		addActButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActityPage p = new AddActityPage();
				JFrame frame = new JFrame("Add a New Activity");
				frame.setBounds(100, 100, 545, 400);
				frame.setVisible(true);
				frame.setResizable(false);
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(p);
			}
		});
		addActButton.setBounds(10, 37, 155, 23);
		add(addActButton);
		
		
	}
	
	public void addNewDay()
	{
		JLabel lblStartTime2 = new JLabel("Start Time:");
		lblStartTime2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartTime2.setBounds(355, 47, 82, 23);
		add(lblStartTime2);
		
		JTextField textField2 = new JTextField(" 08:00 ");
		textField2.setBounds(431, 49, 40, 20);
		add(textField2);
		textField2.setColumns(10);
		
		JLabel label = new JLabel("End Time:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(355, 71, 82, 23);
		add(label);
		
		JLabel lblNewLabel = new JLabel(" 08:00 ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(425, 75, 46, 14);
		add(lblNewLabel);
		
		JLabel label_1 = new JLabel("Total Length:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(355, 92, 82, 23);
		add(label_1);
		
		JLabel label_2 = new JLabel(" 0 min ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(435, 97, 46, 14);
		add(label_2);
		
		remove(addDayButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(355, 126, 155, 306);
		add(panel_1);
		
		addDayButton = new JButton("+ Add a Day");
		addDayButton.setBounds(530, 256, 120, 69);
		add(addDayButton);
	}
	
	
	public void paintComponent(Graphics g)
	{
		g.repaint();
		
	}
}
