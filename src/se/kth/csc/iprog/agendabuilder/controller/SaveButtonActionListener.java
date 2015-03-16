package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.swing.view.ActivityPanel;
import se.kth.csc.iprog.agendabuilder.swing.view.AddActivityFrame;

public class SaveButtonActionListener implements ActionListener{

	public AgendaModel model;
	AddActivityFrame frame;
	ActivityPanel panel;
	
	public void addFrame(AddActivityFrame f){
		frame = f;
	}
	
	public void addModel(AgendaModel model)
	{
		this.model = model;
	}
	
	public void addView(ActivityPanel ap){
		panel = ap;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.getLength() > 0){ // if the given length is valid add activity
			String name = frame.getName();
			String description = frame.getDescription();
			int length = frame.getLength();
			int type = frame.getSelectedType() +1;
			Activity activity = new Activity(name,description,length,type);
			
			model.addParkedActivity(activity);
			frame.dispose();
		}
	}
}
