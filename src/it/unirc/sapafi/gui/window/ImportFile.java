package it.unirc.sapafi.gui.window;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportFile {

	public ImportFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAR Files", "jar");
		fileChooser.setFileFilter(filter);
		fileChooser.showDialog(null, "Select JAR");
	    String absolutePathFile = fileChooser.getSelectedFile().getAbsolutePath();
	    String nameFile = fileChooser.getSelectedFile().getName();
	}
	
	
	
}
