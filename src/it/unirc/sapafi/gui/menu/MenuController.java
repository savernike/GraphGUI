package it.unirc.sapafi.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.unirc.sapafi.gui.MainGUI;
import it.unirc.sapafi.gui.window.ConsoleController;
import it.unirc.sapafi.gui.window.ImportJARFile;
import it.unirc.sapafi.service.FrameService;

public class MenuController {
	private JMenu mnImport;
	private JMenu mnNewMenuWindow;
	private JMenuItem mntmConsole;
	private JMenuItem mntmGraphPalette;
	private JMenuItem mntmMethod;
	private JMenu mnHelp;
	private JMenu mnPreferences;
	private JMenu mnNewMenuInfo;
	private JMenuBar menuBar;

	public MenuController(JFrame frame) {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnImport = new JMenu("Import");
		mnImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImportJARFile jfc = new ImportJARFile();
				jfc.showOpenDialog(null);

			}
		});

		menuBar.add(mnImport);

		mnNewMenuWindow = new JMenu("Window");
		menuBar.add(mnNewMenuWindow);

		mntmConsole = new JMenuItem("Console");
		mntmConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(new FrameService().getSplitPaneParent().size());
				System.out.println(new FrameService().getListFrames().size());
			}
		});
		mnNewMenuWindow.add(mntmConsole);

		mntmGraphPalette = new JMenuItem("Graph palette");
		mntmGraphPalette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsoleController console = new MainGUI().splitPaneCtrl.getConsoleCtrl();
			}
		});
		mnNewMenuWindow.add(mntmGraphPalette);

		mntmMethod = new JMenuItem("Method");
		mntmMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenuWindow.add(mntmMethod);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mnPreferences = new JMenu("Preferences");
		menuBar.add(mnPreferences);

		mnNewMenuInfo = new JMenu("Info");
		menuBar.add(mnNewMenuInfo);
	}
}
