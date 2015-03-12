package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.swing.view.AddActivityFrame;

public class SaveButtonActionListener implements ActionListener{

	public AgendaModel model;
	AddActivityFrame frame;
	
	
	public void addModel(AgendaModel model)
	{
		this.model = model;
	}
	
	public void addView(AddActivityFrame f){
		this.frame = f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = frame.getName();
		String description = frame.getDescription();
		int length = frame.getLength();
		int type = frame.getSelectedType();
		Activity activity = new Activity(name,description,length,type);
		model.addParkedActivity(activity);
	}
}
