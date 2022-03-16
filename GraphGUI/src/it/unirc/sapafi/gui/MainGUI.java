package it.unirc.sapafi.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import it.unirc.sapafi.gui.menu.MenuController;
import it.unirc.sapafi.gui.window.SplitPaneController;
import java.awt.Toolkit;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	public SplitPaneController splitPaneCtrl;
	public MenuController menuCtrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/it/unirc/sapafi/img/main_icon.jpg")));
		setTitle("GraphGUI");
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 425);
		setLocationRelativeTo(null);

		menuCtrl = new MenuController(this);

		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		splitPaneCtrl = new SplitPaneController(contentPane);

	}
}
