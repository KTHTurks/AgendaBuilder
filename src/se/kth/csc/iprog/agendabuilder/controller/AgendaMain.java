package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import se.kth.csc.iprog.agendabuilder.view.AddActityPage;
import se.kth.csc.iprog.agendabuilder.view.MainView;
import se.kth.csc.iprog.agendabuilder.view.MainViewController;



public class AgendaMain extends JFrame {

	private JPanel contentPane;
	public static AgendaMain agendaM;

	/**
	 * Launch the application.
	 */
	
	
	public AgendaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Meeting Agenda");
		
	}
	
	
	
	/*public void addActivity()
	{
		AddActityPage p = new AddActityPage();
		JFrame frame = new JFrame("Add a New Activity");
		frame.setBounds(100, 100, 545, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(p);
	}*/
	
	public static void main(String[] args) {
		agendaM = new AgendaMain();
		
		MainView mV = new MainView();

		agendaM.getContentPane().add(mV);

		agendaM.getContentPane().setPreferredSize(new Dimension(900,600));

		agendaM.pack();
		agendaM.setVisible(true);
		//MainViewController mVC = new MainViewController(agendaM,mV);
		
	}

	/**
	 * Create the frame.
	 */
	
	

}
