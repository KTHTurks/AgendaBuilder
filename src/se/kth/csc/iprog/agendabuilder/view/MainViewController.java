package se.kth.csc.iprog.agendabuilder.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.agendabuilder.controller.AgendaMain;

public class MainViewController implements ActionListener{
	
	MainView mV;
	AgendaMain agenM;
	
	public MainViewController(AgendaMain agenM, MainView mV)
	{
		this.mV = mV;
		this.agenM = agenM;
		//mV.addActButton.addActionListener(this);
		//mV.addDayButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*if(e.getSource() == mV.addActButton)
		{
			agenM.addActivity();
		}*/
		
	}

}
