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

public class DayPanel extends JPanel implements DragGestureListener, java.util.Observer{
	private JTextField textField;
	private JPanel panel;
	JScrollPane scrollPane;
	Day day;
	MyDropTargetListener dropListener;
	Time time;
	int length;
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
		textField.setText("  " + t.toString().substring(0, 5));
		textField.setBounds(139, 24, 51, 28);
		add(textField);
		textField.setColumns(10);
		
		//Textfield a listener eklemeyi unutma
		
		time = t;
		
		endTime = new JLabel(time.toString().substring(0, 5));
		endTime.setBounds(146, 58, 61, 16);
		add(endTime);
		
		length = 0;
		lengthLabel = new JLabel(length + "  min");
		lengthLabel.setBounds(146, 86, 61, 16);
		add(lengthLabel);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(257,330));
		
		scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(21, 114, 257, 330);
		add(scrollPane);
		
		colorPanel = new JPanel();
		colorPanel.setBounds(195, 25, 100, 80);
		//colorPanel.setBackground(MainView.background);
		colorPanel.setLayout(null);
		add(colorPanel);
		
		colorP_Type1 = new JPanel();
		colorP_Type1.setBackground(ActivityDisplay.colorType1);
		//colorType1.setVisible(false);
		//colorType1.setBounds(0, 0, 30, 30);
		colorPanel.add(colorP_Type1);
		
		colorP_Type2 = new JPanel();
		colorP_Type2.setBackground(ActivityDisplay.colorType2);
		//colorType2.setVisible(false);
		colorPanel.add(colorP_Type2);
		
		colorP_Type3 = new JPanel();
		colorP_Type3.setBackground(ActivityDisplay.colorType3);
		//colorType3.setVisible(false);
		colorPanel.add(colorP_Type3);
		
		colorP_Type4 = new JPanel();
		colorP_Type4.setBackground(ActivityDisplay.colorType4);
		//colorType4.setVisible(false);
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
		if(arg.equals("ActivityAdded") || arg.equals("ActivityRemoved") )
		{
			remove(scrollPane);
			panel.removeAll();
			panel.setPreferredSize(new Dimension(257,330));
			panel.setLayout(new GridLayout(day.activities.size(),1,0,0));
			dropListener = new MyDropTargetListener(dropListener);
			dropListener.addView(this);
			dropListener.startDropListen();
			Activity b = null ;
			
			time = new Time(25200000);
			length = 0;
			for(Activity a : day.activities)
			{	
				ActivityDisplay temp = new ActivityDisplay(a);
				DragSource ds = new DragSource();
				ds.createDefaultDragGestureRecognizer((JPanel)temp,DnDConstants.ACTION_COPY, this);
				panel.add(temp);
				b = a;
				int min = b.getLength();
				length = length + min;
				int hour = time.getHours();
				if(min + time.getMinutes()>=60)
				{
					min = min - 60;
					hour = hour + 1;
					time.setHours(hour);
				}
				time.setMinutes(min + time.getMinutes());
			}
			
				
			endTime.setText(time.toString().substring(0, 5));
			lengthLabel.setText(length + "  min");
			
			scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(21, 114, 257, 330);
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
		System.out.println(" Type1 time " + timeType1+ " Type2 time " + timeType2+ " Type3 time " + timeType3+ " Type4 time " + timeType4 );
		double sum = timeType1 + timeType2 + timeType3 + timeType4 ;
		System.out.println("----sum " + sum + " coloePanel height " + colorPanel.getHeight());
		if(sum >0){
			timeType1 = (timeType1/sum) * colorPanel.getHeight();
			timeType2 = (timeType2/sum) * colorPanel.getHeight();
			timeType3 = (timeType3/sum) * colorPanel.getHeight();
			timeType4 = (timeType4/sum) * colorPanel.getHeight();
		}
		
		System.out.println("******* Type1 time " + timeType1+ " Type2 time " + timeType2+ " Type3 time " + timeType3+ " Type4 time " + timeType4 );
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
