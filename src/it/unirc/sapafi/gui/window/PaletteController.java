package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;

import java.util.List;

import javax.swing.ImageIcon;

public class PaletteController {

	private JInternalFrame internalFrameGraphPalette;
	
	
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
	}

	public PaletteController(JSplitPane splitPaneRight, List<Class> classLoaded) {
		// TODO Auto-generated constructor stub
	}

}
