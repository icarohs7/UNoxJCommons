package com.github.icarohs7.swing.tables;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@SuppressWarnings("WeakerAccess")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class NXTableModelTest {
	
	Scenery scenery = new Scenery();
	
	NXTable table;
	
	NXTableModel model;
	
	{
		table = scenery.getTable();
		model = scenery.getModel();
	}
	
	@Test
	@DisplayName("Teste de inserção de dados")
	public void insertDataTest() {
		addTwoExampleRows();
		
		Assertions.assertEquals(model.getValueAt(0, 0), "1", "Valor id da célula 0 0");
		
		Assertions.assertEquals(model.getValueAt(0, 1), "Icaro", "Valor nome da célula 0 1");
		
		Assertions.assertEquals(model.getValueAt(0, 2), "21", "Valor idade da célula 0 2");
	}
	
	@Test
	public void removeDataTest() {
		addTwoExampleRows();
		
		model.removeRow(0);
		
		Assertions.assertEquals(model.getRowCount(), 1, "Número de linhas após remoção");
	}
	
	@Test
	public void modifyDataTest() {
		addTwoExampleRows();
		
		Assertions.assertEquals(model.getValueAt(0, 1), "Icaro", "Valor antes da alteração");
		
		model.setValueAt("José", 0, 1);
		
		Assertions.assertEquals(model.getValueAt(0, 1), "José", "Valor após alteração");
	}
	
	private void addTwoExampleRows() {
		model.addRow(new String[] { "1", "Icaro", "21" });
		model.addRow(new String[] { "2", "Daniel", "20" });
		
		Assertions.assertEquals(model.getRowCount(), 2, "Teste do método utilitário de inserção de linhas");
	}
}
