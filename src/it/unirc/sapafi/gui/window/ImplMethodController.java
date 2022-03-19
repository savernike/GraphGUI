package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;

public class ImplMethodController {

	private JInternalFrame internalFrameMethod;

	public ImplMethodController(JSplitPane splitPane) {
		internalFrameMethod = new JInternalFrame("Graph Implemented Method");
		internalFrameMethod.setClosable(true);
		internalFrameMethod.setVisible(true);
		splitPane.setRightComponent(internalFrameMethod);
		
		FrameService frameService = new FrameService();
		frameService.getSplitPaneParent().put(internalFrameMethod, splitPane);
		frameService.getListFrames().add(internalFrameMethod);
	}

}
