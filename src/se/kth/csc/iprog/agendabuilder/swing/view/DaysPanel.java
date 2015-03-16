package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import se.kth.csc.iprog.agendabuilder.controller.MyDropTargetListener;
import se.kth.csc.iprog.agendabuilder.controller.NewDayButtonActionListener;
import se.kth.csc.iprog.agendabuilder.controller.StartTimeListener;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.swing.AgendaBuilder;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.border.LineBorder;

import java.awt.Color;

public class DaysPanel extends JPanel implements Observer {
	List<DayPanel> days = new ArrayList<DayPanel>();
	JScrollPane daysScrollPane;
	JPanel panel;
	JButton btnNewButton;
	MyDropTargetListener mydrop;
	StartTimeListener sl;
	
	public DaysPanel() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(300,450));
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		daysScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		daysScrollPane.setBounds(6, 6, 300, 450);
		add(daysScrollPane);
		
		btnNewButton = new JButton("+ Add a day ");
		btnNewButton.setBounds(358, 196, 117, 76);
		add(btnNewButton);
	}
	
	public void addDay(Day d){
		DayPanel temp = new DayPanel(d);
		mydrop.addView(temp);
		MyDropTargetListener tempList= new MyDropTargetListener(mydrop);
		tempList.addView(temp);
		temp.addDropListener(tempList);
		temp.addStartTimeListener(sl);
		sl.addView(temp);
		
		days.add(temp);
		
		remove(daysScrollPane);
		if(days.size() == 2)
			btnNewButton.setBounds(660, 196, 117, 76);
		panel.removeAll();
		panel.setPreferredSize(new Dimension(300*days.size(),450));
		panel.setLayout(new GridLayout(1,days.size(),0,0));
		
		for(DayPanel p : days){
			temp = p;
			tempList= new MyDropTargetListener(mydrop);
			tempList.addView(temp);
			temp.addDropListener(tempList);
			panel.add(temp);
		}

		daysScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		int row = 1;
		if(days.size() >= 2){
			row = 2;
		}
		daysScrollPane.setBounds(6, 6, 310*row, 460);
		add(daysScrollPane);
		AgendaBuilder.agendaBuilder.getContentPane().setPreferredSize(new Dimension(1165,500));
		AgendaBuilder.agendaBuilder.pack();
		AgendaBuilder.agendaBuilder.setVisible(true);
	}
	
	public void addNewDayListener(NewDayButtonActionListener dl){
		btnNewButton.addActionListener(dl);
	}
	
	public void addDropListener(MyDropTargetListener mydrop)
	{
		this.mydrop = mydrop;
		
	}
	
	public void addStartTimeListener(StartTimeListener sl){
		this.sl = sl;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof Day){
			for(DayPanel dPanel : days){
				if(((Day)arg).getID() == dPanel.getDay().getID()){
					dPanel.update(dPanel.getDay(), "ActivityAdded");
				}
			}
			
		}else if((arg.toString().equals("ActivityRemoved"))){
			for(DayPanel dPanel : days){
				dPanel.update(null, "ActivityRemoved");
			}
		}
		else if(arg.toString().equals("ActivityMoved")){
			for(DayPanel dPanel : days){
				dPanel.update(null, "ActivityMoved");
			}
		}
		
	}
	

}
