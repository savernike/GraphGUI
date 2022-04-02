package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;
import java.awt.Cursor;

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

public class ImplMethodController {

	private JInternalFrame internalFrameMethod;
	private static JTree tree;

	/**
	 * @wbp.parser.constructor
	 */
	@SuppressWarnings("serial")
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

		FrameService.getListFrames().add(internalFrameMethod);
		internalFrameMethod.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrameMethod.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tree = new JTree();
		tree.setVisible(false);
		tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(tree);

	}

	public static JTree getTree() {
		return tree;
	}

}
