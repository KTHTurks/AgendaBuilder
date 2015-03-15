package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JPanel;

import se.kth.csc.iprog.agendabuilder.controller.MyDropTargetListener;
import se.kth.csc.iprog.agendabuilder.controller.NewActivityButtonActionListener;
import se.kth.csc.iprog.agendabuilder.controller.NewDayButtonActionListener;
import se.kth.csc.iprog.agendabuilder.controller.SaveButtonActionListener;
import se.kth.csc.iprog.agendabuilder.controller.TextFieldListener;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;

public class MainView extends JPanel {
	AgendaModel model;
	
	public MainView(AgendaModel m){
		model = m;
		
		setBounds(0, 0, 850, 500);
		setLayout(null);
		ActivityPanel ap = new ActivityPanel();
		ap.setBounds(0, 0, 300, 475);
		add(ap);
		DaysPanel dp = new DaysPanel();
		dp.setBounds(330, 0, 845, 475);
		add(dp);
		model.addObserver(ap);
		model.addObserver(dp);
		
		
		//New Button and Save button Listeners.
		SaveButtonActionListener sb = new SaveButtonActionListener();
		TextFieldListener tf = new TextFieldListener();
		NewActivityButtonActionListener ab = new NewActivityButtonActionListener(sb, tf);
		NewDayButtonActionListener db = new NewDayButtonActionListener();
		MyDropTargetListener myDrop = new MyDropTargetListener();
		
		myDrop.addModel(model);
		dp.addDropListener(myDrop);
		ab.addModel(model);
		ab.addView(ap);
		ap.addNewActivityListener(ab);
		
		db.addModel(model);
		db.addView(dp);
		dp.addNewDayListener(db);
		
		
		//Adding the first day
		
		ap.addDropListener(myDrop);
		dp.addDay(model.addDay(8, 10));
		
	}
}
