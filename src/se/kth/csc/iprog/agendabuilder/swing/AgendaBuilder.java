package se.kth.csc.iprog.agendabuilder.swing;

import java.awt.Dimension;

import javax.swing.JFrame;

import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.swing.view.MainView;

public class AgendaBuilder extends JFrame {
	
	public AgendaBuilder() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private AgendaModel model = new AgendaModel();

	public AgendaModel getModel() {
		return model;
	}

	public void setModel(AgendaModel model) {
		this.model = model;
	}

	public static AgendaBuilder agendaBuilder = new AgendaBuilder();

	public static void main(String[] args) {
		//Initiating the main JFrame
		agendaBuilder = new AgendaBuilder();
		agendaBuilder.setTitle("Agenda Builder");
		MainView mainView = new MainView(agendaBuilder.getModel());
		
		agendaBuilder.getContentPane().add(mainView);
		agendaBuilder.getContentPane().setPreferredSize(new Dimension(850,500));
		agendaBuilder.pack();
		agendaBuilder.setVisible(true);

	}

}
