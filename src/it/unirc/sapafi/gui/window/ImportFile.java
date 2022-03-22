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
	}
	

}
