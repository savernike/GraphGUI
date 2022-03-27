package it.unirc.sapafi.gui.window;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
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
	private static List<Class> classesLoaded;

	public ImportFile() throws Exception {
		
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
					JOptionPane.showMessageDialog(null, "L'unica estensione possibile \u00E8 .jar", "Estensione non valida",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Load class from JAR file
					try {
						loaderJAR(absolutePathFile);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					
					Class selectedClass = checkClassToSelect();
					
					
					FrameService frameService = new FrameService();
					try {
						frameService.insertImplMethod(classesLoaded);
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}
				}
			}
		} while ((returnValue == JFileChooser.APPROVE_OPTION) && !correctExt);
	}

	@SuppressWarnings("rawtypes")
	private Class checkClassToSelect() throws Exception {
		List<Class> classFiltered = lookForClassesWithGraph(classesLoaded);
		Class res = null;
		if(classFiltered.size() == 0)
			throw new Exception("No graph implemented in this JAR project");
		else if (classFiltered.size() == 1)
			return res;
		ClassSelector classSelector = new ClassSelector(classFiltered);
		classSelector.setVisible(true);
		return res; //TODO
	}

	@SuppressWarnings("rawtypes")
	private List<Class> lookForClassesWithGraph(List<Class> classLoaded) {
		List<Class> res = new ArrayList<Class>();
		for (Class c : classLoaded) {
			String typeGraph = new Utils().lookForGraph(c);
			if(!(typeGraph.equals("")))
				res.add(c);
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
