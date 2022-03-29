package it.unirc.sapafi.gui.window;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SplitPaneController {

	private static JSplitPane splitPaneMain;
	private static JSplitPane splitPaneLeft;
	private static JSplitPane splitPaneRight;
	private DashboardController dashboardCtrl;
	private PaletteController paletteCtrl;
	private ConsoleController consoleCtrl;
	private ImplMethodController implMethodCtrl;

	public SplitPaneController(JPanel contentPane) {

		splitPaneMain = new JSplitPane();
		splitPaneMain.setName("mainPane");
		splitPaneMain.setDividerSize(3);
		splitPaneMain.setResizeWeight(0.8);
		contentPane.add(splitPaneMain);

		splitPaneLeft = new JSplitPane();
		splitPaneLeft.setName("paneLeft");
		splitPaneLeft.setDividerSize(3);
		splitPaneLeft.setResizeWeight(0.65);
		splitPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneMain.setLeftComponent(splitPaneLeft);

		splitPaneRight = new JSplitPane();
		splitPaneRight.setName("paneRight");
		splitPaneRight.setDividerSize(3);
		splitPaneRight.setResizeWeight(0.5);
		splitPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneMain.setRightComponent(splitPaneRight);

		dashboardCtrl = new DashboardController(splitPaneLeft);

		paletteCtrl = new PaletteController(splitPaneRight);

		consoleCtrl = new ConsoleController(splitPaneLeft);

		implMethodCtrl = new ImplMethodController(splitPaneRight);
	}

	public static JSplitPane getSplitPaneMain() {
		return splitPaneMain;
	}

	public static JSplitPane getSplitPaneLeft() {
		return splitPaneLeft;
	}

	public static JSplitPane getSplitPaneRight() {
		return splitPaneRight;
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
