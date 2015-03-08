package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.BorderLayout;
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
	private static AgendaMain agendaM;

	/**
	 * Launch the application.
	 */
	
	
	public AgendaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(true);
		setVisible(true);
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
		//MainViewController mVC = new MainViewController(agendaM,mV);
		agendaM.add(mV);
		
	}

	/**
	 * Create the frame.
	 */
	
	

}
