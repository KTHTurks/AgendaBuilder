
package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import se.kth.csc.iprog.agendabuilder.controller.MyDropTargetListener;
import se.kth.csc.iprog.agendabuilder.controller.NewActivityButtonActionListener;
import se.kth.csc.iprog.agendabuilder.controller.SaveButtonActionListener;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.swing.AgendaBuilder;

import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class ActivityPanel extends JPanel implements DragGestureListener, java.util.Observer {
	List<Activity> activities = new ArrayList<Activity>();
	JScrollPane activityScrollPane;
	JButton btnAddActivity;
	JPanel panel;
	JFrame addActivityFrame;
	SaveButtonActionListener sb;
	MyDropTargetListener dropListener;
	boolean isOpen = false;
	public ActivityPanel() {
		setLayout(null);
		
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
	
	public void addDropListener(MyDropTargetListener mydrop)
	{
		dropListener = mydrop;
		dropListener.startParkedDropListen(this);
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
	
	@Override
	public void update(Observable o, Object arg) {
		
		//System.out.println(arg.getClass().toString());
		//if(arg.equals("ActivityParked"))
		if(arg instanceof ArrayList )
		{
			//System.out.println("se");
			remove(activityScrollPane);
			panel.removeAll();
			this.remove(activityScrollPane);
			activities = ((ArrayList<Activity>) arg);
			int heigth = activities.size()*44;
			int row = activities.size();
			panel.setPreferredSize(new Dimension(237,heigth));
			dropListener.startDropListen();
			if(row == 0)
				row = 1;
			panel.setLayout(new GridLayout(row,heigth,0,0));

			for(Activity a : activities){
				//System.out.println("yma");
				ActivityDisplay temp = new ActivityDisplay(a);
				DragSource ds = new DragSource();
				ds.createDefaultDragGestureRecognizer((JPanel)temp,DnDConstants.ACTION_COPY, this);
				panel.add(temp);
			}

			activityScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			int height = activities.size()*48;
			height = Math.min(399,height);
			activityScrollPane.setBounds(22, 57, 259, height);
			add(activityScrollPane);
			AgendaBuilder.agendaBuilder.pack();
			AgendaBuilder.agendaBuilder.setVisible(true);
			
		}else if(arg instanceof Activity){
			
			Component remove = null;
			for(Component c : panel.getComponents()){
				if(c instanceof ActivityDisplay & ((ActivityDisplay)c).getID() == ((Activity)arg).getID()){
					c.setVisible(false);
					remove = c;
					break;
				}
			}
			if(remove != null){
				panel.remove(remove);
				activityScrollPane.setBounds(22, 57, 259, Math.min(399,(activities.size()+1)*48));
				AgendaBuilder.agendaBuilder.pack();
				AgendaBuilder.agendaBuilder.setVisible(true);
			}
			
			
		}

	}
	
}
