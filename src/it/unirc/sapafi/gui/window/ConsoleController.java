package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class ConsoleController {

	private JInternalFrame internalFrameConsole;

	public ConsoleController(JSplitPane splitPane) {
		internalFrameConsole = new JInternalFrame("Console");
		internalFrameConsole.setFrameIcon(new ImageIcon(ConsoleController.class.getResource("/it/unirc/sapafi/img/console_icon.png")));
		internalFrameConsole.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		internalFrameConsole.setClosable(true);
		internalFrameConsole.setVisible(true);
		splitPane.setRightComponent(internalFrameConsole);
		
		FrameService frameService = new FrameService();
		frameService.getListFrames().add(internalFrameConsole);
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrameConsole.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		//ternary operation (another way to do if/else) (condition ? isTrue : isFalse)
		textPane.setCursor(Cursor.getPredefinedCursor(textPane.getText().length() != 0 ? Cursor.TEXT_CURSOR : Cursor.DEFAULT_CURSOR));
		textPane.setDropMode(DropMode.INSERT);
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}
	
}
