package it.unirc.sapafi.gui.window;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportFile {

	private JFileChooser fileChooser;
	@SuppressWarnings("rawtypes")
	private static List<Class> classLoaded = new LinkedList<Class>();

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
					JOptionPane.showMessageDialog(null, "L'unica estensione possibile è .jar", "Estensione non valida",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						loaderJAR(absolutePathFile);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					
//					FrameService frameService = new FrameService();
//					try {
//						frameService.insertImplMethod(classLoaded);
//					} catch (PropertyVetoException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
		} while ((returnValue == JFileChooser.APPROVE_OPTION) && !correctExt);
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

	@SuppressWarnings("rawtypes")
	private void loaderJAR(String path) throws IOException, ClassNotFoundException {
		JarFile jarFile = new JarFile(path);
		Enumeration<JarEntry> e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + path + "!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		while (e.hasMoreElements()) {
			JarEntry je = e.nextElement();
			if (je.isDirectory() || !je.getName().endsWith(".class")) {
				continue;
			}
			// -6 because of .class
			String className = je.getName().substring(0, je.getName().length() - 6);
			className = className.replace('/', '.');
			Class c = cl.loadClass(className);
			classLoaded.add(c);
		}
		jarFile.close();
	}

}
