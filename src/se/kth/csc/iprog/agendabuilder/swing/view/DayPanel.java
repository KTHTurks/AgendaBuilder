package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.swing.AgendaBuilder;
import se.kth.csc.iprog.agendabuilder.swing.view.ActivityPanel.TransferableActivity;
import se.kth.csc.iprog.agendabuilder.controller.MyDropTargetListener;

import java.sql.Date;
import java.sql.Time;
import java.util.Observer;
import java.util.Observable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayPanel extends JPanel implements DragGestureListener, java.util.Observer{
	private JTextField textField;
	private JPanel panel;
	JScrollPane scrollPane;
	Day day;
	MyDropTargetListener dropListener;
	static Time time = new Time(25200000);
	static int length;
	JLabel endTime;
	JLabel lengthLabel;
	JPanel colorPanel;
	JPanel colorP_Type1;
	JPanel colorP_Type2;
	JPanel colorP_Type3;
	JPanel colorP_Type4;
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
		
		Time t = new Time(25200000);
		textField = new JTextField();
		textField.setText(t.toString().substring(0, 5));
		textField.setBounds(139, 24, 51, 28);
		add(textField);
		textField.setColumns(10);
		
		textField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(Integer.parseInt(textField.getText().substring(0, 2)) <= 24 
						&& Integer.parseInt(textField.getText().substring(0, 2)) >= 00
						&& Integer.parseInt(textField.getText().substring(3, 5)) <= 60
						&& Integer.parseInt(textField.getText().substring(3, 5)) >= 00)
				{
					textField.setText(textField.getText());
					int minDiff = time.getMinutes() - t.getMinutes();
					int hourDiff = time.getHours() - t.getHours();
					t.setHours(Integer.parseInt(textField.getText().substring(0, 2)));
					t.setMinutes(Integer.parseInt(textField.getText().substring(3, 5)));
					time.setHours(t.getHours() + hourDiff);
					time.setMinutes(t.getMinutes() + minDiff);
					endTime.setText(time.toString().substring(0, 5));
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Time must be valid");
				}
			}
		});
		
		endTime = new JLabel(time.toString().substring(0, 5));
		endTime.setBounds(146, 58, 61, 16);
		add(endTime);
		
		length = 0;
		lengthLabel = new JLabel(length + "  min");
		lengthLabel.setBounds(146, 86, 61, 16);
		add(lengthLabel);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(257, 10));
		
		scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		scrollPane.setBounds(21, 114, 257, 330);
		add(scrollPane);
		
		colorPanel = new JPanel();
		colorPanel.setBounds(212, 25, 67, 80);
		colorPanel.setLayout(null);
		add(colorPanel);
		
		colorP_Type1 = new JPanel();
		colorP_Type1.setBackground(ActivityDisplay.colorType1);
		colorPanel.add(colorP_Type1);
		
		colorP_Type2 = new JPanel();
		colorP_Type2.setBackground(ActivityDisplay.colorType2);
		colorPanel.add(colorP_Type2);
		
		colorP_Type3 = new JPanel();
		colorP_Type3.setBackground(ActivityDisplay.colorType3);
		colorPanel.add(colorP_Type3);
		
		colorP_Type4 = new JPanel();
		colorP_Type4.setBackground(ActivityDisplay.colorType4);
		colorPanel.add(colorP_Type4);
		
		
		
	}
	
	public JPanel getPanel(){
		return panel;
	}
	public Day getDay(){
		return day;
	}
	
	public void addDropListener(MyDropTargetListener mydrop)
	{
		dropListener = new MyDropTargetListener(mydrop);
		dropListener.startDropListen();
	}
	
	public void dragGestureRecognized(DragGestureEvent event) {

		Cursor cursor = null;
		JPanel panel = (JPanel) event.getComponent();

		Activity d = ((ActivityDisplay) panel).getActivity();
		if (event.getDragAction() == DnDConstants.ACTION_COPY) {
			cursor = DragSource.DefaultCopyDrop;
		}

		event.startDrag(cursor, new TransferableActivity(d));
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("ActivityAdded") || arg.equals("ActivityRemoved") || arg.equals("ActivityMoved") )
		{
			remove(scrollPane);
			panel.removeAll();
			panel.setPreferredSize(new Dimension(257,day.activities.size()*48));
			panel.setLayout(new GridLayout(day.activities.size(),1,0,0));
			dropListener = new MyDropTargetListener(dropListener);
			dropListener.addView(this);
			dropListener.startDropListen();
			Activity b = null ;
			
			time = new Time(25200000);
			length = 0;
			for(int i=0; i<day.activities.size(); i++){
				ActivityDisplay temp = new ActivityDisplay(day.activities.get(i));
				DragSource ds = new DragSource();
				ds.createDefaultDragGestureRecognizer((JPanel)temp,DnDConstants.ACTION_COPY, this);
				b=day.activities.get(i);
				int min = b.getLength();
				Time startTime = new Time(time.getTime());
				length = length + min;
				int hour = time.getHours();
				if(min + time.getMinutes()>=60)
				{
					min = min - 60;
					hour = hour + 1;
					time.setHours(hour);
				}
				time.setMinutes(min + time.getMinutes());
				temp.changeDayMod(startTime.toString().substring(0, 5), time.toString().substring(0, 5));
				panel.add(temp);
			}
				
			endTime.setText(time.toString().substring(0, 5));
			lengthLabel.setText(length + "  min");
			
			scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			int height = day.activities.size()*52;
			height = Math.min(330,height);
			if(height == 0)
				height = 330;
			scrollPane.setBounds(21, 114, 257, height);
			add(scrollPane);
			AgendaBuilder.agendaBuilder.pack();
			AgendaBuilder.agendaBuilder.setVisible(true);
			timeColor();
			repaint();
		
		}
		
	}
	
	public void timeColor()
	{
		double timeType1 = 0;
		double timeType2 = 0;
		double timeType3 = 0;
		double timeType4 = 0;
		for(Activity a : day.activities)
		{	
			if(a.getType() == Activity.PRESENTATION)
			{
				timeType1 += a.getLength();
			}
			else if(a.getType() == Activity.GROUP_WORK)
			{
				timeType2 += a.getLength();
			}
			else if(a.getType() == Activity.DISCUSSION)
			{
				timeType3 += a.getLength();
			}
			else if(a.getType() == Activity.BREAK)
			{
				timeType4 += a.getLength();
			}
		}
		double sum = timeType1 + timeType2 + timeType3 + timeType4 ;
		if(sum >0){
			timeType1 = (timeType1/sum) * colorPanel.getHeight();
			timeType2 = (timeType2/sum) * colorPanel.getHeight();
			timeType3 = (timeType3/sum) * colorPanel.getHeight();
			timeType4 = (timeType4/sum) * colorPanel.getHeight();
		}
		
		int width = colorPanel.getWidth();
		
		colorP_Type1.setBounds(0, 0, width, (int)timeType1);
		colorP_Type2.setBounds(0, (int)timeType1, width, (int)timeType2);
		colorP_Type3.setBounds(0, (int)(timeType1+ timeType2), width, (int)timeType3);
		colorP_Type4.setBounds(0, (int)(timeType1+ timeType2+ timeType3), width, (int)timeType4);
		
		colorP_Type1.setVisible((timeType1 > 0));
		colorP_Type2.setVisible((timeType2 > 0));
		colorP_Type3.setVisible((timeType3 > 0));
		colorP_Type4.setVisible((timeType4 > 0));
		
	}
}
