package br.edu.univas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class TableFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPanel;
	private ListaBoletos listPanel;
	private AddBoletos addPanel;
	
	public TableFrame() {
		setSize(800, 640);
		setTitle("Cadastro de Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initialize();
	}
	
	private void initialize() {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		try {
			addPanel = new AddBoletos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createNorthPanel();
	}
	
	private void createNorthPanel() {
		JPanel northPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) northPanel.getLayout();
		northPanel.setPreferredSize(new Dimension(800, 140));
		northPanel.setBackground(Color.GRAY);
		
		JButton boletosButton = new JButton();
		boletosButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon imgF = new ImageIcon(getClass().getResource("/fatura.png"));
		boletosButton.setIcon(imgF);
		boletosButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.removeAll();
				centerPanel.add(addPanel);
				centerPanel.revalidate();
				TableFrame.this.repaint();
			}
		});
		northPanel.add(boletosButton);
		ImageIcon imgO = new ImageIcon(getClass().getResource("/logout.png"));
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.NORTH);
		centerPanel.setLayout(new BorderLayout());
		try {
			listPanel = new ListaBoletos();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		centerPanel.add(listPanel, BorderLayout.NORTH);
		GridBagLayout gridBagLayout = (GridBagLayout) listPanel.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		listPanel.updateTable();
		
		contentPane.add(northPanel, BorderLayout.SOUTH);
		
		JButton listaButton = new JButton("");
		ImageIcon imgL = new ImageIcon(getClass().getResource("/list.png"));
		listaButton.setIcon(imgL);
		listaButton.setPreferredSize(new Dimension(100, 100));
		listaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel.updateTable();
				centerPanel.removeAll();
				centerPanel.add(listPanel);
				centerPanel.revalidate();
				TableFrame.this.repaint();
			}
		});
		northPanel.add(listaButton);
		
		JButton logoutButton = new JButton();
		logoutButton.setPreferredSize(new Dimension(100, 100));
		logoutButton.setIcon(imgO);
		logoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame().setVisible(true);
				dispose();
			}
		});
		northPanel.add(logoutButton);
	}
	
}
