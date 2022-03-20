package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;

public class PaletteController {

	private JInternalFrame internalFrameGraphPalette;

	public PaletteController(JSplitPane splitPane) {
		internalFrameGraphPalette = new JInternalFrame("Graph Palette");
		internalFrameGraphPalette.setClosable(true);
		internalFrameGraphPalette.setVisible(true);
		splitPane.setLeftComponent(internalFrameGraphPalette);
		
		FrameService frameService = new FrameService();
		frameService.getListFrames().add(internalFrameGraphPalette);
	}

}
