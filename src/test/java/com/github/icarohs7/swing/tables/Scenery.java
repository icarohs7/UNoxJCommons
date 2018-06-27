package com.github.icarohs7.swing.tables;

import com.github.icarohs7.swing.TestFrame;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Classe utilizada para gerar o cenário de testes referente
 * à tabela e ao model
 */
class Scenery {
	
	private NXTable table;
	
	private NXTableModel model;
	
	private JFrame frame;
	
	Scenery() {
		String[] cols = { "ID", "Nome", "Idade" };
		List<String[]> data = new LinkedList<>();
		
		table = NXTable.ofMutableCells(data, cols);
		model = (NXTableModel) table.getModel();
		
		frame = new TestFrame(table);
		sceneryTest();
	}
	
	void show() {frame.setVisible(true);}
	
	void hide() {frame.setVisible(false);}
	
	@Test
	@DisplayName("Verificar se o cenário é o esperado")
	private void sceneryTest() {
		Assertions.assertEquals(model.getRowCount(), 0, "Número de linhas após criação da tabela");
	}
	
	public NXTable getTable() {
		return table;
	}
	
	public NXTableModel getModel() {
		return model;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
