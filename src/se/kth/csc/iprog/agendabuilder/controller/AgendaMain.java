package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import se.kth.csc.iprog.agendabuilder.view.MainView;

public class AgendaMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AgendaMain agendaM = new AgendaMain();
		MainView mV = new MainView();
		agendaM.add(mV);
	}

	/**
	 * Create the frame.
	 */
	public AgendaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(true);
		setVisible(true);
		setTitle("Meeting Agenda");
	}

}
