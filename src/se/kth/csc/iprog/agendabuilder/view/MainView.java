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

import se.kth.csc.iprog.agendabuilder.controller.AgendaMain;

import java.awt.Canvas;
import java.awt.SystemColor;

public class MainView extends JPanel {
	private JTextField textField;
	public JButton addActButton;
	public JButton addDayButton;
	static JPanel panel;
	static int count = 1;

	/**
	 * Create the panel.
	 */
	public MainView() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 71, 204, 362);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartTime.setBounds(247, 48, 82, 23);
		add(lblStartTime);
		
		textField = new JTextField("08:00");
		textField.setBounds(323, 50, 63, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("End Time:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(247, 72, 82, 23);
		add(label);
		
		JLabel lblNewLabel = new JLabel("08:00");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(317, 76, 46, 14);
		add(lblNewLabel);
		
		JLabel label_1 = new JLabel("Total Length:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(247, 93, 82, 23);
		add(label_1);
		
		JLabel label_2 = new JLabel(" 0 min ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(327, 98, 46, 14);
		add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(247, 127, 155, 306);
		add(panel_1);
		
		addDayButton = new JButton("+ Add a Day");
		addDayButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addNewDay();
			}
		});
		addDayButton.setBounds(422, 257, 120, 69);
		add(addDayButton);
		
		addActButton = new JButton("+ Add Activity");
		addActButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActityPage p = new AddActityPage();
				JFrame frame = new JFrame("Add a New Activity");
				frame.setBounds(100, 100, 200, 400);
				frame.setVisible(true);
				frame.setResizable(false);
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(p);
			}
		});
		addActButton.setBounds(39, 37, 155, 23);
		add(addActButton);
		
		
	}
	
	public void addNewDay()
	{
		JLabel lblStartTime2 = new JLabel("Start Time:");
		lblStartTime2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartTime2.setBounds(430, 47, 82, 23);
		add(lblStartTime2);
		
		JTextField textField2 = new JTextField("08:00");
		textField2.setBounds(506, 50, 63, 20);
		add(textField2);
		textField2.setColumns(10);
		
		JLabel label = new JLabel("End Time:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(430, 71, 82, 23);
		add(label);
		
		JLabel lblNewLabel = new JLabel("08:00");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(500, 75, 46, 14);
		add(lblNewLabel);
		
		JLabel label_1 = new JLabel("Total Length:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(430, 92, 82, 23);
		add(label_1);
		
		JLabel label_2 = new JLabel(" 0 min ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(510, 97, 46, 14);
		add(label_2);
		
		remove(addDayButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(430, 126, 155, 306);
		add(panel_1);
		
		addDayButton = new JButton("+ Add a Day");
		addDayButton.setBounds(605, 256, 120, 69);
		add(addDayButton);
	}
	
	
	public void paintComponent(Graphics g)
	{
		repaint();
		
	}
	public static void addActivity(){
		if(count == 1){
			System.out.println("s");
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(6, 6, 192, 32);
			panel_2.setLayout(null);
			panel.add(panel_2);
			
			JLabel lblNewLabel_1 = new JLabel("10 min");
			lblNewLabel_1.setBounds(6, 6, 61, 16);
			panel_2.add(lblNewLabel_1);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(79, 0, 113, 32);
			panel_3.setBackground(new Color(255, 250, 205));
			panel_2.add(panel_3);
			
			JLabel lblNewLabel_2 = new JLabel("Presentation");
			panel_3.add(lblNewLabel_2);
			count ++;
		}
		else if(count == 2){

			JPanel panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.setBounds(6, 50, 192, 32);
			panel.add(panel_4);
			
			JLabel lblMin = new JLabel("30 min");
			lblMin.setBounds(6, 6, 61, 16);
			panel_4.add(lblMin);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBackground(Color.PINK);
			panel_5.setBounds(79, 0, 113, 32);
			panel_4.add(panel_5);
			
			JLabel lblStudying = new JLabel("Studying");
			panel_5.add(lblStudying);
			count ++;
		}
		else if(count == 3){

			
			JPanel panel_6 = new JPanel();
			panel_6.setLayout(null);
			panel_6.setBounds(6, 94, 192, 32);
			panel.add(panel_6);
			
			JLabel lblMin_1 = new JLabel("15 min");
			lblMin_1.setBounds(6, 6, 61, 16);
			panel_6.add(lblMin_1);
			
			JPanel panel_7 = new JPanel();
			panel_7.setBackground(SystemColor.textHighlight);
			panel_7.setBounds(79, 0, 113, 32);
			panel_6.add(panel_7);
			
			JLabel lblBreak = new JLabel("Break");
			panel_7.add(lblBreak);
			count ++;

		}
		AgendaMain.agendaM.pack();
		AgendaMain.agendaM.setVisible(true);
		
		
	}
}
