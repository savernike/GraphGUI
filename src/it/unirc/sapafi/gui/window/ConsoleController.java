package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JFrame;

public class ConsoleController {

	private JInternalFrame internalFrameConsole;

	public ConsoleController(JSplitPane splitPane) {
		internalFrameConsole = new JInternalFrame("Console");
		internalFrameConsole.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		internalFrameConsole.setClosable(true);
		internalFrameConsole.setVisible(true);
		splitPane.setRightComponent(internalFrameConsole);
	}
	
	public void changeVisibility() {
		internalFrameConsole.setVisible(!internalFrameConsole.isVisible());
	}
	
	
	
}
