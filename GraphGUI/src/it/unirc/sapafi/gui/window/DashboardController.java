package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

public class DashboardController {

	private JInternalFrame internalFrameDashboard;

	public DashboardController(JSplitPane splitPane) {
		internalFrameDashboard = new JInternalFrame("Dashboard");
		internalFrameDashboard.setVisible(true);
		splitPane.setLeftComponent(internalFrameDashboard);
	}
	
	

}
