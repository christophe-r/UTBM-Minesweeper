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
		setBounds(100, 100, 410, 260);
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
			exitButton.setBounds(10, 201, 120, 23);
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
			playAgainButton.setBounds(274, 201, 120, 23);
			contentPanel.add(playAgainButton);
			playAgainButton.setActionCommand("Play again");
		}


		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				playAgain(controller);
			}
		});


		JLabel lblSorry = new JLabel("Sorry, you lost this game. Better luck next time!");
		lblSorry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSorry.setHorizontalAlignment(SwingConstants.CENTER);
		lblSorry.setBounds(10, 21, 384, 23);
		contentPanel.add(lblSorry);

		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(10, 78, 26, 14);
		contentPanel.add(lblTime);

		JLabel lblSeconds = new JLabel("");
		lblSeconds.setBounds(40, 78, 89, 14);
		lblSeconds.setText(time+" seconds");
		contentPanel.add(lblSeconds);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(179, 78, 27, 14);
		contentPanel.add(lblDate);

		JLabel lblTDate = new JLabel("00-Mon-00");
		lblTDate.setBounds(212, 78, 73, 14);
		contentPanel.add(lblTDate);

		JButton btnRestartThisGame = new JButton("Restart this game");
		btnRestartThisGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restartGame(controller);
			}
		});
		btnRestartThisGame.setBounds(142, 201, 120, 23);
		contentPanel.add(btnRestartThisGame);

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

}
