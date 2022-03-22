package it.unirc.sapafi.gui.window;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportFile {

	private JFileChooser fileChooser;

	public ImportFile() {
		int returnValue = -1;
		boolean correctExt = false;
		do {
			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JAR Files", "jar");
			fileChooser.setFileFilter(filter);
			returnValue = fileChooser.showDialog(null, "Select JAR");
			System.out.println(returnValue);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				String nameFile = fileChooser.getSelectedFile().getName();
				String absolutePathFile = fileChooser.getSelectedFile().getAbsolutePath();
				String[] extension = { "jar" };
				correctExt = checkExtensions(extension, nameFile);
				if (!correctExt) {
					JOptionPane.showMessageDialog(null, "L'unica estensione possibile è .jar", "Estensione non valida", JOptionPane.ERROR_MESSAGE);
				}
			}
		} while ((returnValue == JFileChooser.APPROVE_OPTION) && !correctExt);
	}
	
	private boolean checkExtensions(String[] extension, String nameFile) {
		boolean res = false;
		for (String ext : extension) {
			int lastDot = nameFile.lastIndexOf(".");
			String nameExt = nameFile.substring(lastDot + 1);
			if(nameExt.equals(ext)) {
				res = true;
				break;
			} 
		}
		return res;
	}
	
	
	

}
