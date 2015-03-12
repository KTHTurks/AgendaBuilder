package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.swing.view.DaysPanel;


public class NewDayButtonActionListener implements ActionListener {

	AgendaModel model;
	DaysPanel dp;
	
	public void addModel(AgendaModel model){
		this.model = model;
	}
	
	public void addView(DaysPanel dp){
		this.dp = dp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dp.addDay(model.addDay(8, 10));
	}

}
