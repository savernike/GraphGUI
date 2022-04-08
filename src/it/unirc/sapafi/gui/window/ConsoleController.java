package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import it.unirc.sapafi.gui.menu.MenuController;
import it.unirc.sapafi.service.FrameService;

public class ConsoleController {

	private JInternalFrame internalFrameConsole;
	private static Style style;
	private static StyledDocument doc;
	private static JTextPane consoleLog;

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
		
		consoleLog = new JTextPane();
		consoleLog.setText("Benvenuto in GraphGUI\n");
		consoleLog.setFont(new Font("Times New Roman", Font.BOLD, 16));
		consoleLog.setEditable(false);
		scrollPane.setViewportView(consoleLog);
		
		doc = consoleLog.getStyledDocument();
		style = consoleLog.addStyle("Console styles", null);
	}
	
	/**This method allows the user to write a certain message into the console using a determined color.
	 * Every time a message is added to the console with this method, it will be appended at the bottom
	 * @param text - The text to append at the end of the console
	 * @param color - The color of the <code>text</code> appended
	 */
	public static void writeInConsole(String text, Color color) {
		StyleConstants.setForeground(style, color);
		try {
			doc.insertString(doc.getLength(), text + "\n", style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	

	
	
}
