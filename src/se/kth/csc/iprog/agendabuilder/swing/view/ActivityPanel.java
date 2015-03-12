
package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import se.kth.csc.iprog.agendabuilder.controller.NewActivityButtonActionListener;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.swing.AgendaBuilder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActivityPanel extends JPanel implements DragGestureListener, java.util.Observer {
	Set<Activity> activities = new HashSet<Activity>();
	JScrollPane activityScrollPane;
	JButton btnAddActivity;
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
		btnAddActivity = new JButton("Add Activity");
		btnAddActivity.setBounds(78, 18, 146, 29);
		add(btnAddActivity);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(237,342));
		activityScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		activityScrollPane.setBounds(22, 57, 259, 399);
		add(activityScrollPane);
		
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
		if(arg.equals("ActivityParked"))
		{
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
		        ds.createDefaultDragGestureRecognizer((JPanel)temp,DnDConstants.ACTION_COPY, this);
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
	
	public void addNewActivityListener(NewActivityButtonActionListener nb)
	{
		this.btnAddActivity.addActionListener(nb);
	}
	
	
	
	
	public static class TransferableActivity implements Transferable {

		public static DataFlavor activityFlavor = new DataFlavor(Activity.class, "A Activity Object");

		public static  DataFlavor[] supportedFlavors = {
			activityFlavor,
			DataFlavor.stringFlavor,
		};

		Activity activity;

		public TransferableActivity(Activity a) { this.activity = a; }

		public DataFlavor[] getTransferDataFlavors() { return supportedFlavors; }

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			if (flavor.equals(activityFlavor) || 
					flavor.equals(DataFlavor.stringFlavor)) return true;
			return false;
		}


		public Object getTransferData(DataFlavor flavor) 
				throws UnsupportedFlavorException
		{
			if (flavor.equals(activityFlavor))
				return activity;
			else if (flavor.equals(DataFlavor.stringFlavor)) 
				return activity.toString();
			else 
				throw new UnsupportedFlavorException(flavor);
		}
	}
}
