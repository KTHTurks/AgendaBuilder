package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.JOptionPane;

import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.swing.view.DayPanel;
import se.kth.csc.iprog.agendabuilder.swing.view.DaysPanel;

public class StartTimeListener implements ActionListener{
	static Time time = new Time(25200000);
	AgendaModel model;
	DayPanel dp;
	
	public void addModel(AgendaModel model){
		this.model = model;
	}
	
	public void addView(DayPanel dp){
		this.dp = dp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Time t = new Time(25200000);
		try{
		if(Integer.parseInt(dp.getTextField().getText().substring(0, 2)) <= 24 
				&& Integer.parseInt(dp.getTextField().getText().substring(0, 2)) >= 00
				&& Integer.parseInt(dp.getTextField().getText().substring(3, 5)) <= 60
				&& Integer.parseInt(dp.getTextField().getText().substring(3, 5)) >= 00){
		
			
			int hour = Integer.parseInt(dp.getTextField().getText().substring(0, 2));
			int min = Integer.parseInt(dp.getTextField().getText().substring(3, 5));
			dp.getDay().setStart(hour*60+min);
			String hourText = ""+hour;
			if(hour < 10)
				hourText = "0"+hour;
			String minText = ""+min;
			if(min < 10)
				minText = "0"+min;
			dp.getTextField().setText(hourText+":"+minText);
			hourText = ""+dp.getDay().getEnd()/60;
			if(hour < 10)
				hourText = "0"+hourText;
			minText = ""+dp.getDay().getEnd()%60;
			if(min < 10)
				minText = "0"+minText;
			dp.setEndTime(hourText + ":" + minText);
		}else
			JOptionPane.showMessageDialog(null,"Time must be valid");
		}
		catch(StringIndexOutOfBoundsException ex)
		{
			JOptionPane.showMessageDialog(null,"Time must be valid");
		}
		
	}

}
