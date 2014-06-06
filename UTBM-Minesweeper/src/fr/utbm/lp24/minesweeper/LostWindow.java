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

public class LostWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public LostWindow(final MinesweeperGame controller, int time) {
		setTitle("Game Lost");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 340, 197);
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
			exitButton.setBounds(274, 134, 51, 23);
			contentPanel.add(exitButton);
			exitButton.setActionCommand("Exit");
			getRootPane().setDefaultButton(exitButton);
		}
		{
			JButton playAgainButton = new JButton("Play again");
			playAgainButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					playAgain(controller);
				}
			});
			playAgainButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
			playAgainButton.setBounds(10, 134, 112, 23);
			contentPanel.add(playAgainButton);
			playAgainButton.setActionCommand("Play again");
		}


		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				playAgain(controller);
			}
		});


		JLabel lblSorry = new JLabel("<html><center>Sorry, you lost this game.<br> Better luck next time!<center></html>");
		lblSorry.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSorry.setHorizontalAlignment(SwingConstants.CENTER);
		lblSorry.setBounds(10, 23, 315, 46);
		contentPanel.add(lblSorry);

		JLabel lblSeconds = new JLabel("");
		lblSeconds.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeconds.setBounds(101, 90, 112, 23);
		lblSeconds.setText(time+" seconds");
		contentPanel.add(lblSeconds);

		JButton btnRestartThisGame = new JButton("Restart this game");
		btnRestartThisGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restartGame(controller);
			}
		});
		btnRestartThisGame.setBounds(144, 134, 120, 23);
		contentPanel.add(btnRestartThisGame);

		JLabel label = new JLabel("Your time :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(10, 90, 89, 22);
		contentPanel.add(label);

		JButton statButton = new JButton("View statistics");
		statButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		statButton.setBounds(212, 91, 112, 23);
		statButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewStats();
			}
		});
		contentPanel.add(statButton);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void playAgain(MinesweeperGame controller){
		controller.newGame();
		this.dispose();
	}

	private void restartGame(MinesweeperGame controller){
		// TODO
		controller.restartGame();
		this.dispose();
	}
	private void viewStats() {
		new StatisticsWindow();
	}

}
