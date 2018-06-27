package com.github.icarohs7.swing.controls;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@SuppressWarnings("WeakerAccess")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class NXFieldTest {
	
	NXField nxf = new NXField("Teste:");
	
	@Test
	@DisplayName("Binding do texto do field para a property")
	public void textBindingTest_fieldToProperty() {
		assertEquals(nxf.getField().getText(), nxf.getText(), "Texto Inicial igual à property inicial");
		
		nxf.getField().setText("Olá, Mundo!");
		assertEquals(nxf.getField().getText(), nxf.getText(), "Verificar binding field -> property");
	}
	
	@Test
	@DisplayName("Binding da property para o texto do field")
	public void textBindingTest_propertyToField() {
		assertEquals(nxf.getText(), nxf.getField().getText(), "Texto Inicial igual à property inicial");
		
		nxf.setText("Olá, Mundo!");
		assertEquals(nxf.getText(), nxf.getField().getText(), "Verificar binding property -> field");
	}
	
	@Test
	@DisplayName("Binding da property enabled para a ativação do field")
	public void enabledBindingTest() {
		assertEquals(nxf.isEnabled(), nxf.getField().isEnabled(), "Enabled inicial igual à propriedade inicial");
		
		nxf.setEnabled(false);
		assertEquals(nxf.isEnabled(), nxf.getField().isEnabled(), "Alteração da property afetando o field");
	}
}
