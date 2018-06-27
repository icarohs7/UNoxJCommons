package com.github.icarohs7.swing.controls;

import java.awt.GridLayout;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Classe representando um campo de entrada de texto
 * com suporte a binding e sua label associada
 */
public class NXField extends JPanel {
	
	/**
	 * Nome do campo
	 */
	private String fieldName;
	
	/**
	 * Campo
	 */
	private JTextField field;
	
	/**
	 * Label associada
	 */
	private JLabel label;
	
	/**
	 * Propriedade contendo o texto do campo
	 */
	private StringProperty text = new SimpleStringProperty("");
	
	/**
	 * Propriedade atrelada à ativação do campo
	 */
	private BooleanProperty enabled = new SimpleBooleanProperty(true);
	
	public NXField(String fieldName) {
		/* Inicializar valores */
		super(new GridLayout(2, 1));
		this.fieldName = fieldName;
		label = new JLabel(fieldName);
		field = new JTextField();
		registerListeners();
		
		/* Composição da interface */
		add(label);
		add(field);
	}
	
	/**
	 * Faz o binding entra a propriedade e o campo
	 */
	private void registerListeners() {
		// Sincroniza o valor do campo
		Runnable syncField = () -> {
			if (!getText().equals(field.getText())) { field.setText(getText()); }
		};
		// Sincroniza o valor da propriedade
		Runnable syncProperty = () -> {
			if (!getText().equals(field.getText())) { setText(field.getText()); }
		};
		
		// Property -> Campo
		text.addListener((observable, oldValue, newValue) -> syncField.run());
		
		// Campo -> Property
		field.getDocument().addDocumentListener(new DocumentListener() {
			// Inserção no campo
			@Override
			public void insertUpdate(DocumentEvent documentEvent) { syncProperty.run(); }
			
			// Remoção no campo
			@Override
			public void removeUpdate(DocumentEvent documentEvent) { syncProperty.run(); }
			
			// Atualização do campo
			@Override
			public void changedUpdate(DocumentEvent documentEvent) { syncProperty.run(); }
		});
		
		// Enabled listener
		enabled.addListener((observable, oldValue, newValue) -> getField().setEnabled(newValue));
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public JTextField getField() {
		return field;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public String getText() {
		return text.get();
	}
	
	public void setText(String text) {
		this.text.set(text);
	}
	
	public StringProperty textProperty() {
		return text;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled.get();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled.set(enabled);
	}
	
	public BooleanProperty enabledProperty() {
		return enabled;
	}
}
