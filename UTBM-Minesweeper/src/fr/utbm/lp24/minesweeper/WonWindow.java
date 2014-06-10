package fr.utbm.lp24.minesweeper;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.AbstractButton;
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
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JSeparator;

import java.awt.Color;

public class WonWindow extends JDialog implements ScoreListener {

	private int score = 0;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblInfoLoading;
	private AbstractButton sendButton;
	private Component lblPlayerName;

	private static PreferencesManager userPreferences;


	/**
	 * Create the dialog.
	 */
	public WonWindow(final MinesweeperGame controller, int time, int score ) {

		this.score = score;

		setTitle("Game Won");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 345, 320);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton exitButton = new JButton("Exit");
			exitButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			exitButton.setBounds(212, 257, 78, 23);
			contentPanel.add(exitButton);
			getRootPane().setDefaultButton(exitButton);
		}
		{
			JButton playAgainButton = new JButton("Play again");
			playAgainButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					playAgain(controller);
				}
			});
			playAgainButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			playAgainButton.setBounds(30, 257, 104, 23);
			contentPanel.add(playAgainButton);
		}


		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				playAgain(controller);
			}
		});


		JLabel lblCongrats = new JLabel("<html><center>Congratulations!<br> You won the game!</center>");
		lblCongrats.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCongrats.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongrats.setBounds(10, 8, 312, 46);
		contentPanel.add(lblCongrats);

		JLabel lblTime = new JLabel("Your time:");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTime.setBounds(30, 66, 89, 22);
		contentPanel.add(lblTime);

		JLabel lblSeconds = new JLabel("");
		lblSeconds.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeconds.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeconds.setBounds(45, 90, 96, 25);
		lblSeconds.setText(time+" seconds");
		contentPanel.add(lblSeconds);


		JLabel lblTScore = new JLabel("");
		lblTScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblTScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTScore.setBounds(30, 130, 96, 23);
		lblTScore.setText("Your score:");
		contentPanel.add(lblTScore);

		lblPlayerName = new JLabel("Player name:");
		lblPlayerName.setBounds(152, 192, 78, 14);
		contentPanel.add(lblPlayerName);

		sendButton = new JButton("Send");
		sendButton.setBounds(272, 210, 57, 23);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendScore(textField.getText());
				sendButton.setEnabled(false);
			}
		});
		contentPanel.add(sendButton);

		textField = new JTextField();

		textField.setColumns(10);
		textField.setBounds(219, 189, 110, 21);
		userPreferences = new PreferencesManager();
		String playerName = userPreferences.getPref("playerName", "");
		textField.setText(playerName);
		contentPanel.add(textField);

		JLabel lblScore = new JLabel("0 points");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblScore.setBounds(45, 156, 99, 20);
		lblScore.setText(score + " points");
		System.out.println("score : " + score);
		contentPanel.add(lblScore);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(143, 65, 2, 165);
		contentPanel.add(separator_1);

		JButton statButton = new JButton("View statistics");
		statButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		statButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewStats();
			}
		});

		statButton.setBounds(10, 200, 125, 23);
		contentPanel.add(statButton);

		lblInfoLoading = new JLabel("<html><center>Loading global scores<br> from Internet...</center>");
		lblInfoLoading.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoLoading.setBounds(149, 101, 186, 25);
		contentPanel.add(lblInfoLoading);

		JLabel lblAddYourScore = new JLabel("Save your score:");
		lblAddYourScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddYourScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddYourScore.setBounds(152, 167, 114, 23);
		contentPanel.add(lblAddYourScore);


		ScoreManager scoreManager = new ScoreManager(this, "getScore");
		(new Thread(scoreManager)).start();
		ScoreManager scoreManager2 = new ScoreManager(this, "getRank", score);
		(new Thread(scoreManager2)).start();

		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * This method handle the Play Again button
	 * @param controller Controller
	 */
	private void playAgain(MinesweeperGame controller){
		controller.newGame();
		// Close the window
		this.dispose();
	}
	
	/**
	 * Send the score on Internet
	 * @param playerName Player name
	 */
	private void sendScore(String playerName) {
		userPreferences = new PreferencesManager();
		userPreferences.setPref("playerName", playerName);
		ScoreManager scoreManager = new ScoreManager(this, "setScore", playerName, score);
		(new Thread(scoreManager)).start();
	}

	/**
	 * Open Statistics Window
	 */
	private void viewStats() {
		new StatisticsWindow();
	}


	/**
	 * Call the array of scores results is returned
	 */
	@Override
	public void getScore(ArrayList<ArrayList<String>> globalScore) {
		if(globalScore != null){
			lblInfoLoading.setText(" ");

			JLabel rank = new JLabel("#");
			rank.setFont(new Font("Tahoma", Font.BOLD, 11));
			rank.setBounds(165, 55, 14, 14);
			contentPanel.add(rank);

			JLabel playername = new JLabel("Player name");
			playername.setFont(new Font("Tahoma", Font.BOLD, 11));
			playername.setBounds(185, 55, 82, 14);
			contentPanel.add(playername);

			JLabel score = new JLabel("Score");
			score.setFont(new Font("Tahoma", Font.BOLD, 11));
			score.setBounds(260, 55, 44, 14);
			contentPanel.add(score);

			int y = 80;
			for( ArrayList<String> line : globalScore ){
				int x = 165;
				int nbCol = 0;
				for( String field : line ){
					if(field.length() > 13)
						field= field.substring(0,7) + "...";
					JLabel label = new JLabel("" + field);
					contentPanel.add(label);

					switch(nbCol){
					case 0:
						label.setBounds(x, y, 60, 14);
						break;
					case 2:
						x += 20;
						label.setBounds(x, y, 60, 14);
						break;
					case 3:
						x += 75;
						label.setBounds(x, y, 60, 14);
						break;
					}

					nbCol++;
				}

				y += 20;
				if(y >= 140 ) break;
			}
			repaint();
		}else{
			lblInfoLoading.setText("Failed to load Internet score");
		}
	}


	@Override
	public void addScore(Boolean check) {
		if( check ){
			contentPanel.remove(textField);
			contentPanel.remove(sendButton);
			contentPanel.remove(lblPlayerName);
			JLabel label = new JLabel("Score saved");
			contentPanel.add(label);
			label.setForeground(Color.GREEN);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			label.setBounds(155, 192, 140, 14);
			repaint();
		} else {
			JLabel label = new JLabel("Error score not saved");
			contentPanel.add(label);
			label.setForeground(Color.RED);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label.setBounds(160, 210, 140, 14);
			sendButton.setEnabled(true);
			repaint();
		}

	}

	@Override
	public void getRank(int rank, int total) {

		JLabel yourRank = new JLabel("Your rank : ");
		yourRank.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(yourRank);
		yourRank.setBounds(165, 145, 100, 14);

		JLabel lblScore = new JLabel("" + rank + "/" + total);
		contentPanel.add(lblScore);
		lblScore.setBounds(235, 146, 60, 14);

	}
}

