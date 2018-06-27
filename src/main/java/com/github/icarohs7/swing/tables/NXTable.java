package com.github.icarohs7.swing.tables;


import java.awt.Dimension;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * Sobrescrita da JTable padrao para fazer o tamanho do JScroll se adaptar automaticamente a tabela
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
@SuppressWarnings("unused")
public class NXTable extends JTable {
	
	/**
	 * Instanciar uma tabela imutavel com scroll
	 * @param data        Os dados a serem inseridos na tabela
	 * @param columnNames Os nomes das colunas da tabela
	 */
	public NXTable(Object[][] data, String[] columnNames) {
		super(data, columnNames);
		/* Desativar a reordenacao das colunas */
		tableHeader.setReorderingAllowed(false);
		/* Desativar o redimensionamento das colunas */
		tableHeader.setResizingAllowed(false);
	}
	
	/**
	 * Retorna um JScrollPane contendo a tabela atual
	 * @return Um JScroll pane embrulhando a tabela
	 */
	public JScrollPane getScrollableTable() {
		return new JScrollPane(this);
	}
	
	/**
	 * Verifica se uma célula é selecionável ou não
	 * @param i  Linha da célula
	 * @param i1 Coluna da célula
	 * @return Se a célula é selecionável ou não
	 */
	@Override
	public boolean isCellSelected(int i, int i1) {
		return false;
	}
	
	/**
	 * Verifica se uma célula é editável ou não
	 * @param i  Linha da célula
	 * @param i1 Coluna da célula
	 * @return Se a célula é editável ou não
	 */
	@Override
	public boolean isCellEditable(int i, int i1) {
		return false;
	}
	
	/**
	 * Definir o tamanho da tabela quando embrulhada para o seu tamanho completo
	 * @return A dimensão preferida para o JScrollPane envolvendo a tabela
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}
	
	/**
	 * Instanciar uma tabela mutavel com scroll
	 * @param data        Os dados a serem inseridos na tabela
	 * @param columnNames Os nomes das colunas da tabela
	 * @return A tabela editável
	 */
	public static NXTable ofMutableCells(Object[][] data, String[] columnNames) {
		return ofACustomModel(new NXTableModel(data, columnNames));
	}
	
	/**
	 * Instanciar uma tabela mutavel com scroll
	 * @param data        Os dados a serem inseridos na tabela
	 * @param columnNames Os nomes das colunas da tabela
	 * @return A tabela editável
	 */
	public static NXTable ofMutableCells(List<? extends Object[]> data, String[] columnNames) {
		return ofACustomModel(new NXTableModel(data, columnNames));
	}
	
	/**
	 * Instanciar uma tabela apartir de um tablemodel personalizado
	 * @param model Tablemodel personalizado
	 * @return Instância da tabela com o modelo definido
	 */
	public static NXTable ofACustomModel(TableModel model) {
		NXTable table = new NXTable(new String[][] {}, new String[] {});
		table.setModel(model);
		return table;
	}
}
