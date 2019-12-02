package br.edu.univas.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.univas.dao.BatataDAO;
import br.edu.univas.vo.Criativo;

public class AddBoletos extends JPanel {

	private JTextField nameTextField;
	private JTextField valorTextField; 
	private JFormattedTextField DataTextField;
	private BatataDAO batataDAO;
	
	private void formatarCampo() {
		try {
			MaskFormatter mask = new MaskFormatter ("##/##/####");
			
			mask.install(DataTextField);
			
		} catch (ParseException ex) {
		JOptionPane.showMessageDialog(null, "Formato de data inválido", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public AddBoletos() throws SQLException {
		batataDAO = new BatataDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Nome:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.add(nameLabel, gbc);
		
		nameTextField = new JTextField();
		nameTextField.setDocument(new SoLetras());
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(nameTextField, gbc);
		
		JLabel valorLabel = new JLabel();
		valorLabel.setText("Valor:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(valorLabel, gbc);
		
		valorTextField = new JTextField();
		valorTextField.setDocument(new SoNumeros());
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(valorTextField, gbc);
		
		JLabel dataLabel = new JLabel();
		dataLabel.setText("Data de Vencimento:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(dataLabel, gbc);
		
		DataTextField = new JFormattedTextField();
		formatarCampo();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(DataTextField, gbc);
		
		JButton saveButton = new JButton();
		saveButton.setText("Salvar");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveNewStudent();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(saveButton, gbc);		
	}
	
	private void saveNewStudent() {
		if (validateFields()) {

			Criativo criativo = new Criativo();
			criativo.setName(nameTextField.getText());
			criativo.setValor(Float.parseFloat(valorTextField.getText()));
			criativo.setData(DataTextField.getText());
			batataDAO.save(criativo);
			
			clearFields();
			JOptionPane.showMessageDialog(this, "Boleto adicionado com sucesso",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private boolean validateFields() {
		if (nameTextField.getText() == null || nameTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o nome", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			nameTextField.requestFocus();
			return false;
		}
		if (valorTextField.getText() == null || valorTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o valor", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			valorTextField.requestFocus();
			return false;
		}
		if (DataTextField.getText() == null || DataTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a data de vencimento", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			DataTextField.requestFocus();
			return false;
		}
		return true;
	}

	private void clearFields() {
		nameTextField.requestFocus();
		nameTextField.setText(null);
		valorTextField.setText(null);
		DataTextField.setText(null);
	}
}
