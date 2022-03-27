package it.unirc.sapafi.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import it.unirc.sapafi.gui.window.ImportFile;
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
	private JMenuItem mntmLayout;
	private JFrame frame;

	public MenuController(JFrame frame) {
		this.frame = frame;
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnImport = new JMenu("Import");
		mnImport.addMouseListener(new MouseAdapter() {
			
				

			public void mousePressed(MouseEvent e) {
				ImportFile jfc = new ImportFile();
			}
		});
		
		menuBar.add(mnImport);

		mnNewMenuWindow = new JMenu("Window");
		menuBar.add(mnNewMenuWindow);

		mntmConsole = new JCheckBoxMenuItem("Console");
		mntmConsole.setSelected(true);
		mntmConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minimizeConsole();
			}
		});
		mnNewMenuWindow.add(mntmConsole);

		mntmGraphPalette = new JCheckBoxMenuItem("Graph palette");
		mntmGraphPalette.setSelected(true);
		mntmGraphPalette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});
		mnNewMenuWindow.add(mntmGraphPalette);

		mntmMethod = new JCheckBoxMenuItem("Method");
		mntmMethod.setSelected(true);
		mntmMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenuWindow.add(mntmMethod);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mnPreferences = new JMenu("Preferences");
		menuBar.add(mnPreferences);
		
		mntmLayout = new JMenuItem("Layout");
		mntmLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setWindowsLayout();
			}
		});
		mnPreferences.add(mntmLayout);

		mnNewMenuInfo = new JMenu("Info");
		menuBar.add(mnNewMenuInfo);
	}
	
	
	protected void setWindowsLayout() {
		
	}


	private void minimizeConsole() {
		FrameService frameService = new FrameService();
		JInternalFrame console = frameService.getListFrames().get(frameService.getFramePosInList("Console"));
		// TODO
	}
}
