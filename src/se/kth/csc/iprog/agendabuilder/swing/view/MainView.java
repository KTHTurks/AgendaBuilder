package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JPanel;

import se.kth.csc.iprog.agendabuilder.controller.NewActivityButtonActionListener;
import se.kth.csc.iprog.agendabuilder.controller.SaveButtonActionListener;
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
		
		//New Button and Save button Listeners.
		SaveButtonActionListener sb = new SaveButtonActionListener();
		NewActivityButtonActionListener nb = new NewActivityButtonActionListener(sb);
		
		nb.addModel(model);
		nb.addView(ap);
		ap.addNewActivityListener(nb);
		
		model.addObserver(ap);
		
	}
}
