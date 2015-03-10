package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.swing.AgendaBuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.dnd.DragSource;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActivityPanel extends JPanel {
	Set<Activity> activities = new HashSet<Activity>();
	JScrollPane activityScrollPane;
	JPanel panel;
	JFrame addActivityFrame;
	boolean isOpen = false;
	public ActivityPanel() {
		setLayout(null);
		/*
		activities.add(new Activity("Introduction","Intro to the meeting",10,0));
		activities.add(new Activity("Idea 1","Presenting idea 1",30,0));
		activities.add(new Activity("Working in groups","Working on business model for idea 1",35,1));
		activities.add(new Activity("Idea 1 discussion","Discussing the results of idea 1",15,2));
		activities.add(new Activity("Coffee break","Time for some coffee",20,3));
		activities.add(new Activity("Working in groups","Working on business model for idea 1",35,1));
		activities.add(new Activity("Idea 1 discussion","Discussing the results of idea 1",15,2));
		activities.add(new Activity("Coffee break","Time for some coffee",20,3));
		activities.add(new Activity("Working in groups","Working on business model for idea 1",35,1));
		activities.add(new Activity("Idea 1 discussion","Discussing the results of idea 1",15,2));
		activities.add(new Activity("Coffee break","Time for some coffee",20,3));
		*/
		JButton btnAddActivity = new JButton("Add Activity");
		btnAddActivity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(isOpen)
					addActivityFrame.setVisible(false);		//closes the old graph when new graph opens. 
				addActivityFrame = new AddActivityFrame();; 
				addActivityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				isOpen = true;
				
			}
		});
		btnAddActivity.setBounds(78, 18, 146, 29);
		add(btnAddActivity);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(237,342));
		activityScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		activityScrollPane.setBounds(22, 57, 259, 399);
		add(activityScrollPane);
		
	}
	
	public void addActivity(){
		remove(activityScrollPane);
		panel.removeAll();
		int heigth = activities.size()*44;
		int row = activities.size();
		panel.setPreferredSize(new Dimension(237,heigth));
		if(row == 0)
			row = 1;
		panel.setLayout(new GridLayout(row,heigth,0,0));
		
		for(Activity a : activities){
			ActivityDisplay temp = new ActivityDisplay(a);
			DragSource ds = new DragSource();
	        //ds.createDefaultDragGestureRecognizer((JPanel)temp,DnDConstants.ACTION_COPY, this);
			panel.add(temp);
		}

		activityScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		int height = activities.size()*45;
		height = Math.min(399,height);
		activityScrollPane.setBounds(22, 57, 259, height);
		add(activityScrollPane);
		AgendaBuilder.agendaBuilder.pack();
		AgendaBuilder.agendaBuilder.setVisible(true);

	}
}
