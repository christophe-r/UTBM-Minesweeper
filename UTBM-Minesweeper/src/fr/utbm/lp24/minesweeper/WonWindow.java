package fr.utbm.lp24.minesweeper;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WonWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public WonWindow() {
		setTitle("Game Won");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 320, 260);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton exitButton = new JButton("Exit");
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			exitButton.setBounds(10, 201, 125, 23);
			contentPanel.add(exitButton);
			exitButton.setActionCommand("Exit");
			getRootPane().setDefaultButton(exitButton);
		}
		{
			JButton playAgainButton = new JButton("Play again");
			playAgainButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					playAgain();
				}
			});
			playAgainButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
			playAgainButton.setBounds(179, 201, 125, 23);
			contentPanel.add(playAgainButton);
			playAgainButton.setActionCommand("Play again");
		}
		
		
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   playAgain();
			   }
		});
		
		
		JLabel lblCongrats = new JLabel("Congratulations, you won the game!");
		lblCongrats.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCongrats.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongrats.setBounds(10, 21, 294, 23);
		contentPanel.add(lblCongrats);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(10, 78, 26, 14);
		contentPanel.add(lblTime);
		
		JLabel lblSeconds = new JLabel("9999 seconds");
		lblSeconds.setBounds(40, 78, 89, 14);
		contentPanel.add(lblSeconds);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(179, 78, 27, 14);
		contentPanel.add(lblDate);
		
		JLabel lblTDate = new JLabel("00-Mon-00");
		lblTDate.setBounds(212, 78, 73, 14);
		contentPanel.add(lblTDate);
		
		
		setVisible(true);
		
	}
	
	private void playAgain(){
		// TODO
		//controller.newGame();

		// Close the window
		this.dispose();
	}
	
}
