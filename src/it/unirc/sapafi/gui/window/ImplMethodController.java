package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

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
