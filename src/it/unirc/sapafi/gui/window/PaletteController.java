package it.unirc.sapafi.gui.window;

import java.awt.Cursor;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import it.unirc.sapafi.gui.menu.MenuController;
import it.unirc.sapafi.service.FrameService;

public class PaletteController {

	private JInternalFrame internalFrameGraphPalette;
	private static JScrollPane scrollPane;
	
	public static JScrollPane getScrollPane() {
		return scrollPane;
	}

	private static JTree tree;
	
	
	public static JTree getTree() {
		return tree;
	}

	/**
	 * @wbp.parser.constructor
	 */
	public PaletteController(JSplitPane splitPane) {	
		internalFrameGraphPalette = new JInternalFrame("Graph Palette");
		internalFrameGraphPalette.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				internalFrameGraphPalette.setVisible(false);
				MenuController.toogleMenuItem(1, false);
			}
		});
		internalFrameGraphPalette.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		internalFrameGraphPalette.setFrameIcon(new ImageIcon(PaletteController.class.getResource("/it/unirc/sapafi/img/palette_icon.png")));
		internalFrameGraphPalette.setClosable(true);
		internalFrameGraphPalette.setVisible(true);
		splitPane.setLeftComponent(internalFrameGraphPalette);
		
		FrameService.getListFrames().add(internalFrameGraphPalette);
		internalFrameGraphPalette.getContentPane().setLayout(new BoxLayout(internalFrameGraphPalette.getContentPane(), BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		internalFrameGraphPalette.getContentPane().add(scrollPane);
		
		tree = new JTree();
		tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tree.setVisible(false);
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);
	}
}
