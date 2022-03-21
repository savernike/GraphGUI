package it.unirc.sapafi.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import it.unirc.sapafi.gui.window.ImportFile;

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
				ImportFile importFile = new ImportFile();
			}
		});

		menuBar.add(mnImport);

		mnNewMenuWindow = new JMenu("Window");
		menuBar.add(mnNewMenuWindow);

		mntmConsole = new JMenuItem("Console");
		mntmConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minimizeConsole();
			}
		});
		mnNewMenuWindow.add(mntmConsole);

		mntmGraphPalette = new JMenuItem("Graph palette");
		mntmGraphPalette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	
	
	private void minimizeConsole() {
		
	}
}
