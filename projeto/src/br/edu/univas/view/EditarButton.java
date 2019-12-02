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

public class EditarButton extends AbstractCellEditor
implements TableCellRenderer, TableCellEditor, ActionListener {
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text;
    BatataDAO batataDAO = new BatataDAO();
	
	public EditarButton(JTable table, int column) {
	    super();
	    this.table = table;
	    renderButton = new JButton();
	
	    editButton = new JButton("Editar");
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
	
	    renderButton.setText( (value == null) ? "Editar" : value.toString() );
	    return renderButton;
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    text = (value == null) ? "Editar" : value.toString();
	    editButton.setText( text );
	    return editButton;
	}
	
	public Object getCellEditorValue() {
	    return text;
	}
	
	public void actionPerformed(ActionEvent e) {
	    fireEditingStopped();
	    int l = table.getSelectedRow();
	    int c = 0;
	    String name = table.getValueAt(l, c++).toString();
	    String val = table.getValueAt(l, c++).toString();
	    float valor = Float.parseFloat(val);
	    String data = table.getValueAt(l, c++).toString();
	    batataDAO.gamb(name, valor, data);
	    new TelaEdit().setVisible(true);
	}
}