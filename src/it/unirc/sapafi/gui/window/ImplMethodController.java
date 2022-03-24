package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import it.unirc.sapafi.service.FrameService;
import javax.swing.ImageIcon;

public class ImplMethodController {

	private JInternalFrame internalFrameMethod;
	private JTree tree;
	
	/**
	 * @wbp.parser.constructor
	 */
	public ImplMethodController(JSplitPane splitPane) {
		internalFrameMethod = new JInternalFrame("Graph Implemented Method");
		internalFrameMethod.setFrameIcon(new ImageIcon(ImplMethodController.class.getResource("/it/unirc/sapafi/img/impl_method_icon.png")));
		internalFrameMethod.setClosable(true);
		internalFrameMethod.setVisible(true);
		splitPane.setRightComponent(internalFrameMethod);
		
		FrameService frameService = new FrameService();
		frameService.getListFrames().add(internalFrameMethod);
		
//		tree = new JTree();
//		tree.setModel(new DefaultTreeModel(
//			new DefaultMutableTreeNode("Implemented Methods") {
//				{
//					add(new DefaultMutableTreeNode("m1 (return int; int arg0)"));
//					add(new DefaultMutableTreeNode("m2"));
//					add(new DefaultMutableTreeNode("m3"));
//				}
//			}
//		));
//		internalFrameMethod.getContentPane().add(tree, BorderLayout.CENTER);
	}
	
	

}
