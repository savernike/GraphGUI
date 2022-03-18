package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.util.FrameService;

import java.util.HashMap;

import javax.swing.JFrame;

public class ConsoleController {

	private JInternalFrame internalFrameConsole;

	public ConsoleController(JSplitPane splitPane) {
		internalFrameConsole = new JInternalFrame("Console");
		internalFrameConsole.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		internalFrameConsole.setClosable(true);
		internalFrameConsole.setVisible(true);
		splitPane.setRightComponent(internalFrameConsole);
		
		FrameService frameService = new FrameService();
		frameService.getSplitPaneParent().put(internalFrameConsole, splitPane);
		frameService.getListFrames().add(internalFrameConsole);
	}
	
}
