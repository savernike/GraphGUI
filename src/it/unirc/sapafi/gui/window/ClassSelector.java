package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import it.unirc.sapafi.utils.Utils;

public class ClassSelector extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2114450526089201738L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JComboBox<String> comboBoxClasses;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	@SuppressWarnings("rawtypes")
	private Map<Class, String> chosenClass;
	public static Integer indexSelectedClass;

	@SuppressWarnings("rawtypes")
	public ClassSelector(Map<Class, String> chosenClass) {
		this.chosenClass = chosenClass;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				chooseOnExit(false);
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Select a class");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ClassSelector.class.getResource("/it/unirc/sapafi/img/main_icon.jpg")));
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblNewLabel = new JLabel("Select one of these " + chosenClass.size() + " classes");
		lblNewLabel.setBounds(10, 11, 414, 24);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPanel.add(lblNewLabel);

		comboBoxClasses = new JComboBox<String>();
		comboBoxClasses.setModel(new DefaultComboBoxModel<String>(printClassAndValues()));
		comboBoxClasses.setBounds(10, 103, 414, 22);
		contentPanel.add(comboBoxClasses);
		setLocationRelativeTo(null);

		buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(3, 0, 0, 0, (Color) UIManager.getColor("Button.light")));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseOnExit(true);
			}
		});
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseOnExit(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private void chooseOnExit(boolean selected) {
		if(!selected) {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wanna abort the choice?", "Abort JAR Import", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION)
				return;
		}
		indexSelectedClass = selected ? comboBoxClasses.getSelectedIndex() : null;
		dispose();
	}

	@SuppressWarnings("rawtypes")
	private Vector<String> printClassAndValues() {
		Vector<String> res = new Vector<String>();
		Utils utils = new Utils();
		for(Class k : chosenClass.keySet()) {
			String item = k.getName() + " - " + utils.removePackage(chosenClass.get(k));
			res.add(item);
		}
		return res;
	}
}
