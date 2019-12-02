package br.edu.univas.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.edu.univas.dao.BatataDAO;
import br.edu.univas.vo.Criativo;

public class ExcluirButton extends AbstractCellEditor
implements TableCellRenderer, TableCellEditor, ActionListener {
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text;
	
	BatataDAO batataDAO = new BatataDAO();
	
	private String nome = "adv";
	
	public ExcluirButton(JTable table, int column) {
	    super();
	    this.table = table;
	    renderButton = new JButton();
	
	    editButton = new JButton("Excluir");
	    editButton.setFocusPainted( false );
	    editButton.addActionListener( this );
	
	    TableColumnModel columnModel = table.getColumnModel();
	    columnModel.getColumn(column).setCellRenderer( this );
	    columnModel.getColumn(column).setCellEditor( this );
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    if (hasFocus) {
	        renderButton.setForeground(table.getForeground());
	        renderButton.setBackground(UIManager.getColor("Button.background"));
	    } else if (isSelected) {
	        renderButton.setForeground(table.getSelectionForeground());
	         renderButton.setBackground(table.getSelectionBackground());
	    } else {
	        renderButton.setForeground(table.getForeground());
	        renderButton.setBackground(UIManager.getColor("Button.background"));
	    }
	
	    renderButton.setText( (value == null) ? "Excluir" : value.toString() );
	    return renderButton;
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    text = (value == null) ? "Excluir" : value.toString();
	    editButton.setText( text );
	    return editButton;
	}
	
	public Object getCellEditorValue() {
	    return text;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    fireEditingStopped();
	    int l = table.getSelectedRow();
	    int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão ?");
	    
	    if(result == JOptionPane.YES_OPTION){
            batataDAO.del(table.getValueAt(l, 0).toString());
            JOptionPane.showMessageDialog(null, "Atualize a tabela");
         }else if (result == JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null, "Operação Não");
         }else {
        	 JOptionPane.showMessageDialog(null, "Operação Cancelada");
         }
	}
}