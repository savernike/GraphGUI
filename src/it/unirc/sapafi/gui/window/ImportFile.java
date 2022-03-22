package it.unirc.sapafi.gui.window;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportFile {

	private JFileChooser fileChooser;

	public ImportFile() {
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAR Files", "jar");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showDialog(null, "Select JAR");
		System.out.println(returnValue);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String nameFile = fileChooser.getSelectedFile().getName();
			String absolutePathFile = fileChooser.getSelectedFile().getAbsolutePath();
			String[] extension = { "jar" };
			if (!checkExtensions(extension, nameFile)) {
				JOptionPane.showMessageDialog(null, "Il formato selezionato è invalido");
				System.out.println(absolutePathFile);
				fileChooser = new JFileChooser();
			}
		}
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
