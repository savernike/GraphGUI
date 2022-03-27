package it.unirc.sapafi.gui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ClassSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClassSelector(List<Class> classFiltered) {
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
		{
			JLabel lblNewLabel = new JLabel("Select one of these " + classFiltered.size() + " classes");
			lblNewLabel.setBounds(10, 11, 414, 24);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
			contentPanel.add(lblNewLabel);
		}

		JComboBox comboBoxClasses = new JComboBox();
		List<String> classNames = new ArrayList<String>();
		classFiltered.forEach((classItem) -> classNames.add(classItem.getName()));
		comboBoxClasses.setModel(new DefaultComboBoxModel(classNames.toArray()));
		comboBoxClasses.setBounds(10, 103, 414, 22);
		contentPanel.add(comboBoxClasses);
		setLocationRelativeTo(null);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(3, 0, 0, 0, (Color) UIManager.getColor("Button.light")));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
