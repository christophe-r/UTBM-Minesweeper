package fr.utbm.lp24.minesweeper;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StatisticsWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Create the dialog.
	 */
	public StatisticsWindow() {
		setResizable(false);
		setTitle("Statistics");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 233, 272);
		
		getContentPane().setLayout(null);
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					setVisible(false);
				}
			});
			okButton.setBounds(119, 199, 90, 23);
			getContentPane().add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 199, 180);
		getContentPane().add(tabbedPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Local statistics", null, layeredPane, null);
		layeredPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total time:");
		lblNewLabel.setBounds(10, 11, 82, 14);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Games played:");
		lblNewLabel_1.setBounds(10, 29, 82, 14);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Games won:");
		lblNewLabel_2.setBounds(10, 48, 82, 14);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblWonPercentage = new JLabel("Win percentage:");
		lblWonPercentage.setBounds(10, 68, 82, 14);
		layeredPane.add(lblWonPercentage);
		
		JLabel lblNewLabel_3 = new JLabel("Best score:");
		lblNewLabel_3.setBounds(10, 89, 82, 14);
		layeredPane.add(lblNewLabel_3);
		
		JLabel label = new JLabel("0");
		label.setBounds(97, 11, 46, 14);
		layeredPane.add(label);
		
		JLabel label_1 = new JLabel("0");
		label_1.setBounds(97, 29, 46, 14);
		layeredPane.add(label_1);
		
		JLabel label_2 = new JLabel("0");
		label_2.setBounds(97, 48, 46, 14);
		layeredPane.add(label_2);
		
		JLabel label_3 = new JLabel("0");
		label_3.setBounds(97, 68, 46, 14);
		layeredPane.add(label_3);
		
		JLabel label_4 = new JLabel("0");
		label_4.setBounds(97, 89, 46, 14);
		layeredPane.add(label_4);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Global ranking", null, layeredPane_1, null);
		layeredPane_1.setLayout(null);
		
		
		setVisible(true);
	}
}
