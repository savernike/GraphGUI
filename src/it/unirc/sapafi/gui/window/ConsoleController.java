package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import it.unirc.sapafi.gui.menu.MenuController;
import it.unirc.sapafi.service.FrameService;

public class ConsoleController {

	private JInternalFrame internalFrameConsole;
	private JTextArea consoleText;

	public ConsoleController(JSplitPane splitPane) {
		internalFrameConsole = new JInternalFrame("Console");
		internalFrameConsole.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				internalFrameConsole.setVisible(false);
				MenuController.toogleMenuItem(0, false);
			}
		});
		internalFrameConsole.setFrameIcon(new ImageIcon(ConsoleController.class.getResource("/it/unirc/sapafi/img/console_icon.png")));
		internalFrameConsole.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		internalFrameConsole.setClosable(true);
		internalFrameConsole.setVisible(true);
		splitPane.setRightComponent(internalFrameConsole);
		
		FrameService.getListFrames().add(internalFrameConsole);
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrameConsole.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		consoleText = new JTextArea();
		consoleText.setFont(new Font("Times New Roman", Font.BOLD, 15));
		consoleText.setEditable(false);
		consoleText.setText("Benvenuto/a in GraphGUI\r\n");
		scrollPane.setViewportView(consoleText);
	}
	

	
	
}
