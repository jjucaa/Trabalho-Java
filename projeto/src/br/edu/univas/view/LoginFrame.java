package br.edu.univas.view;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import br.edu.univas.dao.BatataDAO;
import br.edu.univas.dao.ConnectionUtil;
import br.edu.univas.view.TableFrame;
import br.edu.univas.vo.Criativo;

public class LoginFrame extends JFrame {
	private JTextField textField;
	private final JLabel lblNewLabel = new JLabel("Senha");
	private JPasswordField passwordField;
	private BatataDAO batataDAO;
	private Connection connection;
	public javax.swing.JLabel welcomeLabel;
	
	public LoginFrame() {
		connection = ConnectionUtil.getConnection();
		setSize(460, 319);
		setTitle("Gerenciador de Boletos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{97, 266, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblMesmoNomeAqui = new JLabel("BOLETOS");
		lblMesmoNomeAqui.setFont(new Font("Consolas", Font.PLAIN, 30));
		GridBagConstraints gbc_lblMesmoNomeAqui = new GridBagConstraints();
		gbc_lblMesmoNomeAqui.insets = new Insets(0, 0, 5, 0);
		gbc_lblMesmoNomeAqui.gridx = 1;
		gbc_lblMesmoNomeAqui.gridy = 1;
		getContentPane().add(lblMesmoNomeAqui, gbc_lblMesmoNomeAqui);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Consolas", Font.BOLD, 16));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 3;
		getContentPane().add(lblLogin, gbc_lblLogin);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		GridBagConstraints loginField = new GridBagConstraints();
		loginField.insets = new Insets(15, 15, 15, 15);
		loginField.fill = GridBagConstraints.HORIZONTAL;
		loginField.gridx = 1;
		loginField.gridy = 3;
		getContentPane().add(textField, loginField);
		textField.setColumns(10);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 16));
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 16));
		GridBagConstraints passField = new GridBagConstraints();
		passField.insets = new Insets(15, 15, 15, 15);
		passField.fill = GridBagConstraints.HORIZONTAL;
		passField.gridx = 1;
		passField.gridy = 4;
		getContentPane().add(passwordField, passField);
		passwordField.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				confirmarLogin(evt);
			}
		});
		btnEntrar.setFont(new Font("Consolas", Font.BOLD, 16));
		GridBagConstraints gbc_btnEntrar = new GridBagConstraints();
		gbc_btnEntrar.anchor = GridBagConstraints.SOUTH;
		gbc_btnEntrar.gridx = 1;
		gbc_btnEntrar.gridy = 6;
		getContentPane().add(btnEntrar, gbc_btnEntrar);
		initialize();
	}

	private void confirmarLogin(java.awt.event.ActionEvent evt) {
	       
		 String user = textField.getText();
		 String pass = String.valueOf(passwordField.getPassword());
	        
	     String sql = "SELECT * FROM login WHERE adm =? AND senha =?";
	        
        try {
        	PreparedStatement statement = connection.prepareStatement(sql);
        	ResultSet rs;
        	statement.setString(1, user);
        	statement.setString(2, pass);
            
            rs = statement.executeQuery();
            
            if(rs.next()){
            	JOptionPane.showMessageDialog(null, "Ben-Vindo " + user);
            		new TableFrame().setVisible(true);
                    dispose();
            } else {
                    JOptionPane.showMessageDialog(null, "Senha ou Usuário incorreto");
              }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
	
	private void initialize() {
		
	}
}
