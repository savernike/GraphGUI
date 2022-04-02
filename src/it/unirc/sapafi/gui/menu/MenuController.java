package it.unirc.sapafi.gui.menu;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import it.unirc.sapafi.gui.window.ImportFile;
import it.unirc.sapafi.gui.window.SplitPaneController;
import it.unirc.sapafi.utils.Utils;

public class MenuController {
	private JMenu mnImport;
	private JMenu mnWindows;
	private static JMenuItem mntmConsole;
	private static JMenuItem mntmGraphPalette;
	private static JMenuItem mntmMethod;
	private JMenu mnHelp;
	private JMenu mnPreferences;
	private JMenu mnNewMenuInfo;
	private JMenuBar menuBar;
	private JMenuItem mntmLayout;
	private JFrame frame;
	
	public MenuController() {}

	/**
	 * @wbp.parser.constructor
	 */
	public MenuController(JFrame frame) {
		this.frame = frame;
		menuBar = new JMenuBar();
		this.frame.setJMenuBar(menuBar);

		mnImport = new JMenu("Import");
		mnImport.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				try {
					@SuppressWarnings("unused")
					ImportFile jfc = new ImportFile();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		menuBar.add(mnImport);

		mnWindows = new JMenu("Window");
		mnWindows.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnWindows);

		mntmConsole = new JCheckBoxMenuItem("Console");
		mntmConsole.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmConsole.setName(mntmConsole.getText());
		mntmConsole.setSelected(true);
		mntmConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toogleConsoleFrame();
			}
		});
		mnWindows.add(mntmConsole);

		mntmGraphPalette = new JCheckBoxMenuItem("Graph palette");
		mntmGraphPalette.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmGraphPalette.setName(mntmGraphPalette.getText());
		mntmGraphPalette.setSelected(true);
		mntmGraphPalette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tooglePaletteFrame();
			}

		});
		mnWindows.add(mntmGraphPalette);

		mntmMethod = new JCheckBoxMenuItem("Method");
		mntmMethod.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmMethod.setName(mntmMethod.getText());
		mntmMethod.setSelected(true);
		mntmMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toogleImplMethodsFrame();
			}
		});
		mnWindows.add(mntmMethod);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mnPreferences = new JMenu("Preferences");
		menuBar.add(mnPreferences);

		mntmLayout = new JMenuItem("Layout");
		mntmLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnPreferences.add(mntmLayout);

		mnNewMenuInfo = new JMenu("Info");
		menuBar.add(mnNewMenuInfo);
	}

	protected void toogleImplMethodsFrame() {
		Utils utils = new Utils();
		utils.toogleInternalFrame("Graph Implemented Method", SplitPaneController.getSplitPaneRight(), true);
	}

	private void tooglePaletteFrame() {
		Utils utils = new Utils();
		utils.toogleInternalFrame("Graph Palette", SplitPaneController.getSplitPaneRight(), false);
	}

	private void toogleConsoleFrame() {
		Utils utils = new Utils();
		utils.toogleInternalFrame("Console", SplitPaneController.getSplitPaneLeft(), true);
	}
	
	public static void toogleMenuItem(int menuItem, boolean state) {
		switch(menuItem) {
		case 0:
			mntmConsole.setSelected(state);
			break;
		case 1:
			mntmGraphPalette.setSelected(state);
			break;
		case 2:
			mntmMethod.setSelected(state);
			break;
		default:
			throw new IndexOutOfBoundsException("You have to insert a number between 0 and 2");
		}
	}
}
