package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

public class PaletteController {

	private JInternalFrame internalFrameGraphPalette;

	public PaletteController(JSplitPane splitPane) {
		internalFrameGraphPalette = new JInternalFrame("Graph Palette");
		internalFrameGraphPalette.setClosable(true);
		internalFrameGraphPalette.setVisible(true);
		splitPane.setLeftComponent(internalFrameGraphPalette);
	}

	
}
