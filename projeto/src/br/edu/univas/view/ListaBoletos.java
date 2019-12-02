package br.edu.univas.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.univas.dao.BatataDAO;
import br.edu.univas.vo.Criativo;
import br.edu.univas.view.EditarButton;

public class ListaBoletos extends JPanel {

	public JTable boletoTable;
	private BatataDAO batataDAO;
	
	public ListaBoletos() throws SQLException {
		batataDAO = new BatataDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Esquerda: Adicionar boletos || Centro: Atualizar a lista || Direita: Logout");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(titleLabel, gbc);
		
		Vector<String> nomeColuna = new Vector<String>();
		nomeColuna.add("Nome");
		nomeColuna.add("Valor");
		nomeColuna.add("Data");
		nomeColuna.add("Editar");
		nomeColuna.add("Excluir");
		Vector<? extends Vector> vector = new Vector();
		boletoTable = new JTable(vector, nomeColuna);
		JScrollPane studentTableScroll = new JScrollPane(boletoTable);
		studentTableScroll.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studentTableScroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		studentTableScroll.setMinimumSize(new Dimension(750, 300));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(studentTableScroll, gbc);
	}
	
	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) boletoTable.getModel();
		tableModel.setRowCount(0);
		
		for (Criativo criativo : batataDAO.getAll()) {
			Object[] data = {
					criativo.getName(),
					criativo.getValor(),
					criativo.getData()
			};
			EditarButton editButton = new EditarButton(boletoTable, 3);
			ExcluirButton delButton = new ExcluirButton(boletoTable, 4);
			tableModel.addRow(data);
		}
	}
	
}
