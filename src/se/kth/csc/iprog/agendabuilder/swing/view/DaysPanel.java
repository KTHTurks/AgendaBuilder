package se.kth.csc.iprog.agendabuilder.swing.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import se.kth.csc.iprog.agendabuilder.swing.AgendaBuilder;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class DaysPanel extends JPanel{
	Set<DayPanel> days = new HashSet<DayPanel>();
	JScrollPane daysScrollPane;
	public DaysPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(300,450));
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		DayPanel dp1 = new DayPanel();
		days.add(dp1);
		panel.add(dp1);
		
		daysScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		daysScrollPane.setBounds(6, 6, 315, 463);
		add(daysScrollPane);
		
		
		JButton btnNewButton = new JButton("+ Add a day ");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DayPanel temp = new DayPanel();
				days.add(temp);
				
				remove(daysScrollPane);
				if(days.size() == 2)
					btnNewButton.setBounds(660, 196, 117, 76);
				panel.removeAll();
				panel.setPreferredSize(new Dimension(310*days.size(),450));
				panel.setLayout(new GridLayout(1,days.size(),0,0));
				
				for(DayPanel p : days){
					temp = p;
					DragSource ds = new DragSource();
			        //ds.createDefaultDragGestureRecognizer((JPanel)temp,DnDConstants.ACTION_COPY, this);

					panel.add(temp);
				}

				daysScrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				daysScrollPane.setBounds(6, 6, 615, 463);
				add(daysScrollPane);
				AgendaBuilder.agendaBuilder.getContentPane().setPreferredSize(new Dimension(1165,500));;
				AgendaBuilder.agendaBuilder.pack();
				AgendaBuilder.agendaBuilder.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(358, 196, 117, 76);
		add(btnNewButton);
	}

}
