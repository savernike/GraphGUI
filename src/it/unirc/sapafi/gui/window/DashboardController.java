package it.unirc.sapafi.gui.window;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

import it.unirc.sapafi.service.FrameService;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class DashboardController {

	private JInternalFrame internalFrameDashboard;

	public DashboardController(JSplitPane splitPane) {
		internalFrameDashboard = new JInternalFrame("Dashboard");
		internalFrameDashboard.setFrameIcon(new ImageIcon(DashboardController.class.getResource("/it/unirc/sapafi/img/dashboard_icon.png")));
		internalFrameDashboard.setVisible(true);
		splitPane.setLeftComponent(internalFrameDashboard);
		
		FrameService frameService = new FrameService();
		frameService.getListFrames().add(internalFrameDashboard);
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrameDashboard.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
