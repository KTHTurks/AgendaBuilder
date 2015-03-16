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
	JLabel lengthLabel;
	JLabel startTimeLabel;
	public static final  Color colorType1 = new Color(173, 216, 230);
	public static final  Color colorType2 = new Color(255, 228, 225);
	public static final  Color colorType3 = new Color(255, 248, 220);
	public static final  Color colorType4 = new Color(153, 204, 153);
	
	public ActivityDisplay(Activity a) {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		activity = a;
		id=activity.getID();
		setLayout(null);
		Color c;
		if(activity.getType() == 1)
			c = colorType1;
		else if(activity.getType() == 2)
			c = colorType2;
		else if(activity.getType() == 3)
			c = colorType3;
		else
			c = colorType4;
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.setBackground(c);
		panel.setBounds(62, 0, 193, 44);
		panel.setName(Integer.toString(id));
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
		
		
		JLabel lblNewLabel = new JLabel(activity.getName());
		lblNewLabel.setName(Integer.toString(id));
		panel.add(lblNewLabel);
		
		lengthLabel = new JLabel(""+activity.getLength());
		lengthLabel.setBounds(10, 15, 34, 16);
		lengthLabel.setName(Integer.toString(id));
		add(lengthLabel);
		
		startTimeLabel = new JLabel();
		startTimeLabel.setBounds(10, 15, 46, 16);
		startTimeLabel.setName(Integer.toString(id));
		startTimeLabel.setVisible(false);
		add(startTimeLabel);
		
		
		setBounds(62, 0, 255, 44);
		
	}
	
	public Activity getActivity(){
		
		return activity;
	}
	
	public void changeDayMod(String sTime){
		lengthLabel.setVisible(false);
		startTimeLabel.setVisible(true);
		startTimeLabel.setText(sTime);
	}
	
	public void changeParkedMod(){
		lengthLabel.setVisible(true);
		startTimeLabel.setVisible(false);
	}
	
	
	public int getID(){
		return id;
	}
}
