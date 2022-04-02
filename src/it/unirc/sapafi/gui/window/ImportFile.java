package it.unirc.sapafi.gui.window;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import it.unirc.sapafi.service.FrameService;
import it.unirc.sapafi.utils.Utils;

public class ImportFile {

	private JFileChooser fileChooser;
	@SuppressWarnings("rawtypes")
	private Map<Class, String> chosenClasses;
	@SuppressWarnings("rawtypes")
	private static List<Class> classesLoaded;

	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("rawtypes")
	public ImportFile() {

		int returnValue = -1;
		boolean correctExt = false;
		do {
			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JAR Files", "jar");
			fileChooser.setFileFilter(filter);
			returnValue = fileChooser.showDialog(null, "Select JAR");
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				String nameFile = fileChooser.getSelectedFile().getName();
				String absolutePathFile = fileChooser.getSelectedFile().getAbsolutePath();
				String[] extension = { "jar" };
				correctExt = checkExtensions(extension, nameFile);
				// System.out.println(absolutePathFile);
				if (!correctExt) {
					JOptionPane.showMessageDialog(null, "L'unica estensione possibile \u00E8 .jar",
							"Estensione non valida", JOptionPane.ERROR_MESSAGE);
				} else {
					// Load class from JAR file
					try {
						loaderJAR(absolutePathFile);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					// Choose the implemented class with graph
					Class selectedClass = null;
					try {
						selectedClass = checkClassToSelect();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
						break;
					}

					// Add the several methods and beans to the JTree in JInternalFrame
					FrameService frameService = new FrameService();
					try {
						frameService.insertImplMethod(selectedClass, ImplMethodController.getTree());
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}

					try {
						frameService.insertBeans(filterClassesWithoutGraph(), PaletteController.getTree());
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}

				}
			}
		} while ((returnValue == JFileChooser.APPROVE_OPTION) && !correctExt);
	}

	@SuppressWarnings("rawtypes")
	private List<Class> filterClassesWithoutGraph() {
		List<Class> classesWithoutGraph = classesLoaded;
		for (Class c : chosenClasses.keySet()) {
			classesWithoutGraph.remove(c);
		}
		return classesWithoutGraph;
	}

	@SuppressWarnings("rawtypes")
	private Class checkClassToSelect() throws Exception {
		Class res = null;
		chosenClasses = lookForClassesWithGraph();

		if (chosenClasses.size() == 0)
			throw new Exception("No graph implemented in this JAR project");
		else if (chosenClasses.size() == 1) {
			res = (Class) chosenClasses.keySet().toArray()[0];
			return res;
		} else {
			ClassSelector classSelector = new ClassSelector(chosenClasses);
			classSelector.setVisible(true);
			if (ClassSelector.indexSelectedClass == null) {
				throw new Exception("You have to choose at least one class with a graph to use the program");
			}
			res = (Class) chosenClasses.keySet().toArray()[ClassSelector.indexSelectedClass];
			return res;
		}
	}

	@SuppressWarnings({ "rawtypes", "static-access" })
	private Map<Class, String> lookForClassesWithGraph() {
		Map<Class, String> res = new HashMap<Class, String>();
		for (Class c : this.classesLoaded) {
			Utils utils = new Utils();
			String typeGraph = utils.removePackage(utils.lookForGraph(c));
			if (!(typeGraph.equals("")))
				res.put(c, typeGraph);
		}
		return res;
	}

	private boolean checkExtensions(String[] extension, String nameFile) {
		boolean res = false;
		for (String ext : extension) {
			int lastDot = nameFile.lastIndexOf(".");
			String nameExt = nameFile.substring(lastDot + 1);
			if (nameExt.equals(ext)) {
				res = true;
				break;
			}
		}
		return res;
	}

	@SuppressWarnings({ "rawtypes", "static-access" })
	private void loaderJAR(String path) throws IOException, ClassNotFoundException {
		JarFile jarFile = new JarFile(path);
		Enumeration<JarEntry> e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + path + "!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		this.classesLoaded = new LinkedList<Class>();
		while (e.hasMoreElements()) {
			JarEntry je = e.nextElement();
			if (je.isDirectory() || !je.getName().endsWith(".class")) {
				continue;
			}
			// -6 because of .class
			String className = je.getName().substring(0, je.getName().length() - 6);
			className = className.replace('/', '.');
			Class c = cl.loadClass(className);
			classesLoaded.add(c);
		}
		jarFile.close();
	}

}
