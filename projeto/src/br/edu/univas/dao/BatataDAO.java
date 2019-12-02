package br.edu.univas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.edu.univas.view.LoginFrame;
import br.edu.univas.view.TableFrame;
import br.edu.univas.vo.Criativo;

public class BatataDAO {

	private Connection connection;
	
	public BatataDAO() {
		connection = ConnectionUtil.getConnection();
	}
	
	public void save(Criativo criativo) {
		String sql = "insert into divida (nome, valor, data_de_vencimento) "
				+ "values (?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int index = 1;
			statement.setString(index++, criativo.getName());
			statement.setFloat(index++, criativo.getValor());
			statement.setString(index++, criativo.getData());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "O nome não pode ser repetido");
		} finally {
			
		}
	}
	
	public void del(String nome) {
		String sql = "DELETE FROM divida WHERE nome = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public void update(String nome, Float valor, String data) {
		String sql = "UPDATE divida " 
					+ " SET nome = ? , valor = ? , data_de_vencimento = ? " 
				    + " WHERE nome = ? AND valor = ? AND data_de_vencimento = ?";
		
		String sql2 = "SELECT * FROM tmp";
		
		String del = "DELETE FROM tmp WHERE nome = ?";
		
		String Upnome = null;
		Float UPval = null;
		String UPdata = null;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql2);
			
			while (result.next()) {
				Upnome = result.getString("nome");
				UPval = result.getFloat("valor");
				UPdata = result.getString("data_de_vencimento");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int index = 1;
			
			statement.setString(index++, nome);
			statement.setFloat(index++, valor);
			statement.setString(index++, data);
			statement.setString(index++, Upnome);
			statement.setFloat(index++, UPval);
			statement.setString(index++, UPdata);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(del);
			statement.setString(1, Upnome);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public List<Criativo> getAll() {
		List<Criativo> criativos = new ArrayList<Criativo>();
		
		String sql = "select * from divida";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Criativo criativo = new Criativo();
				criativo.setName(result.getString("nome"));
				criativo.setValor(result.getFloat("valor"));
				criativo.setData(result.getString("data_de_vencimento"));
				criativos.add(criativo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return criativos;
	}
	
	public List<Criativo> login() {
		List<Criativo> criativos = new ArrayList<Criativo>();
		
		String sql = "select * from login";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Criativo criativo = new Criativo();
				criativo.setLogin(result.getString("adm"));
				criativo.setSenha(result.getString("senha"));
				criativos.add(criativo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return criativos;
	}
	
	public void gamb(String nome, float valor, String data) {
		String sql = "insert into tmp (nome, valor, data_de_vencimento) "
				+ "values (?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int index = 1;
			statement.setString(index++, nome);
			statement.setFloat(index++, valor);
			statement.setString(index++, data);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}