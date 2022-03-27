package it.unirc.sapafi.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import it.unirc.sapafi.gui.menu.MenuController;
import it.unirc.sapafi.gui.window.SplitPaneController;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6304487844265567838L;
	
	private JPanel contentPane;
	public SplitPaneController splitPaneCtrl;
	public MenuController menuCtrl;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
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
	@SuppressWarnings("static-access")
	public MainGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/it/unirc/sapafi/img/main_icon.jpg")));
		setTitle("GraphGUI");
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 425);
		setLocationRelativeTo(null);
		setExtendedState(this.MAXIMIZED_BOTH);

		menuCtrl = new MenuController(this);

		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		splitPaneCtrl = new SplitPaneController(contentPane);

	}
}
