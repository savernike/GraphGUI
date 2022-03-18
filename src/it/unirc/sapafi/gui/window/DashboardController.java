package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.util.FrameService;

public class DashboardController {

	private JInternalFrame internalFrameDashboard;

	public DashboardController(JSplitPane splitPane) {
		internalFrameDashboard = new JInternalFrame("Dashboard");
		internalFrameDashboard.setVisible(true);
		splitPane.setLeftComponent(internalFrameDashboard);
		
		FrameService frameService = new FrameService();
		frameService.getSplitPaneParent().put(internalFrameDashboard, splitPane);
		frameService.getListFrames().add(internalFrameDashboard);
	}

}
