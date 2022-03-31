package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.BoxLayout;

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
		internalFrameGraphPalette.setFrameIcon(new ImageIcon(PaletteController.class.getResource("/it/unirc/sapafi/img/palette_icon.png")));
		internalFrameGraphPalette.setClosable(true);
		internalFrameGraphPalette.setVisible(true);
		splitPane.setLeftComponent(internalFrameGraphPalette);
		
		FrameService frameService = new FrameService();
		frameService.getListFrames().add(internalFrameGraphPalette);
		internalFrameGraphPalette.getContentPane().setLayout(new BoxLayout(internalFrameGraphPalette.getContentPane(), BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		internalFrameGraphPalette.getContentPane().add(scrollPane);
		
		tree = new JTree();
		tree.setVisible(false);
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);
	}
}
