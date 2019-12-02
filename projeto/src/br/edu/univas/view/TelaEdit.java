package br.edu.univas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.univas.dao.BatataDAO;
import br.edu.univas.vo.Criativo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "unused", "serial" })
public class TelaEdit extends JFrame {

	private JPanel contentPane;
	private JTextField nomeTextField;
	private JTextField valorTextField;
	private JButton btnSalvarInformaes;
	private JFormattedTextField dataTextField;
	BatataDAO batataDAO = new BatataDAO();
	private JLabel lblEdioDeDados;
	
	private void formatarCampo () {
		try {
			MaskFormatter mask = new MaskFormatter ("##/##/####");
			
			mask.install(dataTextField);
			
		} catch (ParseException ex) {
		JOptionPane.showMessageDialog(null, "Formato de data inválido", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdit frame = new TelaEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 500, 240);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{47, 112, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblEdioDeDados = new JLabel("Edi\u00E7\u00E3o de Dados");
		lblEdioDeDados.setFont(new Font("Yu Gothic", Font.BOLD, 24));
		GridBagConstraints gbc_lblEdioDeDados = new GridBagConstraints();
		gbc_lblEdioDeDados.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdioDeDados.gridx = 2;
		gbc_lblEdioDeDados.gridy = 1;
		contentPane.add(lblEdioDeDados, gbc_lblEdioDeDados);
		
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 2;
		contentPane.add(lblNome, gbc_lblNome);
		
		nomeTextField = new JTextField();
		nomeTextField.setDocument(new SoLetras());
		GridBagConstraints gbc_nomeTextField = new GridBagConstraints();
		gbc_nomeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nomeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomeTextField.gridx = 2;
		gbc_nomeTextField.gridy = 2;
		contentPane.add(nomeTextField, gbc_nomeTextField);
		nomeTextField.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		GridBagConstraints gbc_lblValor = new GridBagConstraints();
		gbc_lblValor.anchor = GridBagConstraints.EAST;
		gbc_lblValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblValor.gridx = 1;
		gbc_lblValor.gridy = 3;
		contentPane.add(lblValor, gbc_lblValor);
		
		valorTextField = new JTextField();
		valorTextField.setDocument(new SoNumeros());
		GridBagConstraints gbc_valorTextField = new GridBagConstraints();
		gbc_valorTextField.insets = new Insets(0, 0, 5, 5);
		gbc_valorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_valorTextField.gridx = 2;
		gbc_valorTextField.gridy = 3;
		contentPane.add(valorTextField, gbc_valorTextField);
		valorTextField.setColumns(10);
		
		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento");
		GridBagConstraints gbc_lblDataDeVencimento = new GridBagConstraints();
		gbc_lblDataDeVencimento.anchor = GridBagConstraints.EAST;
		gbc_lblDataDeVencimento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDeVencimento.gridx = 1;
		gbc_lblDataDeVencimento.gridy = 4;
		contentPane.add(lblDataDeVencimento, gbc_lblDataDeVencimento);
		
		dataTextField = new JFormattedTextField();
		formatarCampo();
		GridBagConstraints gbc_dataTextField = new GridBagConstraints();
		gbc_dataTextField.insets = new Insets(0, 0, 5, 5);
		gbc_dataTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dataTextField.gridx = 2;
		gbc_dataTextField.gridy = 4;
		contentPane.add(dataTextField, gbc_dataTextField);
		
		btnSalvarInformaes = new JButton("Salvar Informa\u00E7\u00F5es");
		btnSalvarInformaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = nomeTextField.getText();
				float valor = Float.parseFloat(valorTextField.getText());
				String data = dataTextField.getText();
				batataDAO.update(nome, valor, data);
				JOptionPane.showMessageDialog(null, "Atualize a tabela");
				dispose();
			}
		});
		btnSalvarInformaes.setBackground(Color.GREEN);
		GridBagConstraints gbc_btnSalvarInformaes = new GridBagConstraints();
		gbc_btnSalvarInformaes.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalvarInformaes.gridx = 2;
		gbc_btnSalvarInformaes.gridy = 5;
		contentPane.add(btnSalvarInformaes, gbc_btnSalvarInformaes);
	}

}
