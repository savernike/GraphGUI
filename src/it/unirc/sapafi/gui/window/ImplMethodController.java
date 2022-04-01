package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.gui.menu.MenuController;
import it.unirc.sapafi.service.FrameService;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class ImplMethodController {

	private JInternalFrame internalFrameMethod;

	/**
	 * @wbp.parser.constructor
	 */
	public ImplMethodController(JSplitPane splitPane) {
		internalFrameMethod = new JInternalFrame("Graph Implemented Method");
		internalFrameMethod.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				internalFrameMethod.setVisible(false);
				MenuController.toogleMenuItem(2, false);
			}
		});
		internalFrameMethod.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
