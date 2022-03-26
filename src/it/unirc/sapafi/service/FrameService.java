package it.unirc.sapafi.service;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import it.unirc.sapafi.utils.Utils;

public class FrameService {
	private static List<JInternalFrame> listFrames = new LinkedList<JInternalFrame>();

	public List<JInternalFrame> getListFrames() {
		return listFrames;
	}

	public int getFramePosInList(String title) {
		int res = -1;
		for (int i = 0; i < listFrames.size(); i++) {
			if (title.equals(listFrames.get(i).getTitle())) {
				res = i;
				break;
			}
		}
		return res;
	}

	public JInternalFrame getFrameInList(String title) {
		return listFrames.get(getFramePosInList(title));
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	public void insertImplMethod(List<Class> classLoaded) throws PropertyVetoException {
		JInternalFrame frame = getFrameInList("Graph Implemented Method");
		frame.setSelected(true);
		JTree tree = new JTree();

		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Implemented Methods") {
			{
				for (Class c : classLoaded) {
					for (Method m : c.getDeclaredMethods()) {
						add(new DefaultMutableTreeNode(new Utils().printMethod(m, true)));
					}
				}

			}
		}));
		frame.getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
	}

}
