package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class TextFieldListener implements FocusListener{
	JTextField tField;
	@Override
	public void focusGained(FocusEvent e) {
		tField = (JTextField)e.getSource();
	    tField.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		//JTextField tField = (JTextField)e.getSource();
		if(tField.getText().equals("")){
			tField.setText(tField.getName());
		}
	}

}
