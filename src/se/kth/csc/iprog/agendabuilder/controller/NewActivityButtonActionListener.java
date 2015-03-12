package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.swing.view.ActivityPanel;
import se.kth.csc.iprog.agendabuilder.swing.view.AddActivityFrame;

public class NewActivityButtonActionListener implements ActionListener{
	
	public AgendaModel model;
	ActivityPanel panel;
	SaveButtonActionListener sb;
	JFrame activityFrame;
	boolean isOpen = false;
	
	//SaveButtonActionListener'ý yeni frame eklemek için
	public NewActivityButtonActionListener(SaveButtonActionListener sb)
	{
		this.sb= sb;
	}
	
	public void addModel(AgendaModel model)
	{
		this.model = model;
	}
	
	public void addView(ActivityPanel v){
		this.panel = v;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isOpen)
			activityFrame.setVisible(false);		

		activityFrame = new AddActivityFrame();
		//Adding Save Button Listener
		((AddActivityFrame) activityFrame).addSaveListener(sb);
		isOpen = true;

	}
}
