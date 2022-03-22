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
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import java.awt.Font;

public class ConsoleController {

	private JInternalFrame internalFrameConsole;
	private JTextArea testo;

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
		
		testo = new JTextArea();
		testo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		testo.setEditable(false);
		testo.setText("Benvenuto/a in GraphGUI\r\n");
		scrollPane.setViewportView(testo);
		
		
	}
	

	
	
}
