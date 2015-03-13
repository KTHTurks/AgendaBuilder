package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.swing.AgendaBuilder;
import se.kth.csc.iprog.agendabuilder.controller.MyDropTargetListener;

import java.util.Observer;
import java.util.Observable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class DayPanel extends JPanel implements java.util.Observer{
	private JTextField textField;
	private JPanel panel;
	JScrollPane scrollPane;
	Day day;
	MyDropTargetListener dropListener;
	public DayPanel(Day d) {
		day = d;
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
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(257,330));
		
		scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(21, 114, 257, 330);
		add(scrollPane);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	public Day getDay(){
		return day;
	}
	
	public void addDropListener(MyDropTargetListener mydrop)
	{
		dropListener = mydrop;
		dropListener.startDropListen();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("ActivityAdded"))
		{
			remove(scrollPane);
			panel.removeAll();
			panel.setPreferredSize(new Dimension(257,330));
			panel.setLayout(new GridLayout(day.activities.size(),1,0,0));
			dropListener.startDropListen();
			
			for(Activity a : day.activities)
			{	
				ActivityDisplay temp = new ActivityDisplay(a);
				panel.add(temp);
			}

			scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(21, 114, 257, 330);
			add(scrollPane);
			AgendaBuilder.agendaBuilder.pack();
			AgendaBuilder.agendaBuilder.setVisible(true);
		}
		
	}
}
