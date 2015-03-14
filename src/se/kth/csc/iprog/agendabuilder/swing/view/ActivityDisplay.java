package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import se.kth.csc.iprog.agendabuilder.model.Activity;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class ActivityDisplay extends JPanel {
	Activity activity;
	private int id;
	public ActivityDisplay(Activity a) {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		activity = a;
		id=activity.getID();
		setLayout(null);
		Color c;
		if(activity.getType() == 1)
			c = new Color(173, 216, 230);
		else if(activity.getType() == 2)
			c = new Color(255, 228, 225);
		else if(activity.getType() == 3)
			c = new Color(255, 248, 220);
		else
			c = new Color(153, 204, 153);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.setBackground(c);
		panel.setBounds(62, 0, 196, 44);
		panel.setName(Integer.toString(id));
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
		
		
		JLabel lblNewLabel = new JLabel(activity.getName());
		lblNewLabel.setName(Integer.toString(id));
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel(""+activity.getLength());
		label.setBounds(10, 15, 34, 16);
		label.setName(Integer.toString(id));
		add(label);
		
		setBounds(62, 0, 196, 44);
		
	}
	
	public Activity getActivity(){
		
		return activity;
	}
	
	public int getID(){
		return id;
	}
}
