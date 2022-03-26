package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;

public class ImplMethodController {

	private JInternalFrame internalFrameMethod;

	/**
	 * @wbp.parser.constructor
	 */
	public ImplMethodController(JSplitPane splitPane) {
		internalFrameMethod = new JInternalFrame("Graph Implemented Method");
		internalFrameMethod.setFrameIcon(
				new ImageIcon(ImplMethodController.class.getResource("/it/unirc/sapafi/img/impl_method_icon.png")));
		internalFrameMethod.setClosable(true);
		internalFrameMethod.setVisible(true);
		splitPane.setRightComponent(internalFrameMethod);

		FrameService frameService = new FrameService();
		frameService.getListFrames().add(internalFrameMethod);
		internalFrameMethod.getContentPane().setLayout(new BorderLayout(0, 0));

	}

}
