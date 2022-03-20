package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import it.unirc.sapafi.service.FrameService;

public class ImplMethodController {

	private JInternalFrame internalFrameMethod;

	public ImplMethodController(JSplitPane splitPane) {
		internalFrameMethod = new JInternalFrame("Graph Implemented Method");
		internalFrameMethod.setClosable(true);
		internalFrameMethod.setVisible(true);
		splitPane.setRightComponent(internalFrameMethod);
		
		FrameService frameService = new FrameService();
		frameService.getListFrames().add(internalFrameMethod);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Implemented Methods") {
				{
					add(new DefaultMutableTreeNode("m1 (return int; int arg0)"));
					add(new DefaultMutableTreeNode("m2"));
					add(new DefaultMutableTreeNode("m3"));
				}
			}
		));
		internalFrameMethod.getContentPane().add(tree, BorderLayout.CENTER);
	}

}
