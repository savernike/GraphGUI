package it.unirc.sapafi.service;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import it.unirc.sapafi.utils.Utils;

public class FrameService {
	private static List<JInternalFrame> listFrames = new LinkedList<JInternalFrame>();

	public static List<JInternalFrame> getListFrames() {
		return listFrames;
	}

	public static int getFramePosInList(String title) {
		int res = -1;
		for (int i = 0; i < listFrames.size(); i++) {
			if (title.equals(listFrames.get(i).getTitle())) {
				res = i;
				break;
			}
		}
		return res;
	}

	public static JInternalFrame getFrameInList(String title) {
		return listFrames.get(getFramePosInList(title));
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	public void insertImplMethod(Class classLoaded) throws PropertyVetoException {
		JInternalFrame frame = getFrameInList("Graph Implemented Method");
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();

		if (classLoaded == null) {
			return;
		}

		JTree tree = new JTree();

		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Implemented Methods") {
			{
				DefaultMutableTreeNode method = new DefaultMutableTreeNode("Method from " + classLoaded.getName());
				for (Method m : classLoaded.getDeclaredMethods()) {
					method.add(new DefaultMutableTreeNode(new Utils().printMethod(m, false)));
				}
				add(method);
			}
		}));
		tree.expandRow(1);
		frame.getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	public void insertBeans(List<Class> classesLoaded, JTree paletteTree) throws PropertyVetoException {
		JInternalFrame frame = getFrameInList("Graph Palette");
		paletteTree.removeAll();
		paletteTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root") {
			{
				for (Class c1 : classesLoaded) {
					DefaultMutableTreeNode bean = new DefaultMutableTreeNode(c1.getName());
					{
						DefaultMutableTreeNode instance = new DefaultMutableTreeNode("Insert a new instance");
						bean.add(instance);
					}
					add(bean);
				}
			}
		}));
		paletteTree.setVisible(true);
		frame.revalidate();
	}

}
