package com.github.icarohs7.swing;

import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 * JFrame utilizada para testes
 */
public class TestFrame extends JFrame {
	
	/**
	 * Inicializar o frame com um componente carregado
	 * @param component Componente mostrado no frame
	 * @throws HeadlessException Exceção em caso de erro
	 */
	public TestFrame(Component component) throws HeadlessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(component);
		pack();
		setLocationRelativeTo(null);
	}
}
