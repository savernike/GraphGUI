package it.unirc.sapafi.gui.window;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SplitPaneController {

	private JSplitPane splitPaneMain;
	private JSplitPane splitPaneLeft;
	private JSplitPane splitPaneRight;
	private DashboardController dashboardCtrl;
	private PaletteController paletteCtrl;
	private ConsoleController consoleCtrl;
	private ImplMethodController implMethodCtrl;

	public SplitPaneController(JPanel contentPane) {

		splitPaneMain = new JSplitPane();
		splitPaneMain.setDividerSize(3);
		splitPaneMain.setResizeWeight(0.8);
		contentPane.add(splitPaneMain);

		splitPaneLeft = new JSplitPane();
		splitPaneLeft.setDividerSize(3);
		splitPaneLeft.setResizeWeight(0.65);
		splitPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneMain.setLeftComponent(splitPaneLeft);

		splitPaneRight = new JSplitPane();
		splitPaneRight.setDividerSize(3);
		splitPaneRight.setResizeWeight(0.5);
		splitPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneMain.setRightComponent(splitPaneRight);

		dashboardCtrl = new DashboardController(splitPaneLeft);

		paletteCtrl = new PaletteController(splitPaneRight);

		consoleCtrl = new ConsoleController(splitPaneLeft);

		implMethodCtrl = new ImplMethodController(splitPaneRight);

	}

	public DashboardController getDashboardCtrl() {
		return dashboardCtrl;
	}

	public PaletteController getPaletteCtrl() {
		return paletteCtrl;
	}

	public ConsoleController getConsoleCtrl() {
		return consoleCtrl;
	}

	public ImplMethodController getImplMethodCtrl() {
		return implMethodCtrl;
	}

}
