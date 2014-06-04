package fr.utbm.lp24.minesweeper;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;

public class StatisticsWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLayeredPane layeredPane_localStats;

	private JLabel label_totalTime;
	private JLabel label_gamesPlayed;
	private JLabel label_gamesWon;
	private JLabel label_winPercentage;
	private JLabel label_bestScore;

	public StatisticsWindow() {
		setResizable(false);
		setTitle("Statistics");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 270, 340);

		getContentPane().setLayout(null);
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					setVisible(false);
				}
			});
			okButton.setBounds(164, 277, 90, 23);
			getContentPane().add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 244, 255);
		getContentPane().add(tabbedPane);


		/************* Stats tab ******************/

		layeredPane_localStats = new JLayeredPane();
		tabbedPane.addTab("Local statistics", null, layeredPane_localStats, null);
		layeredPane_localStats.setLayout(null);

		JLabel totalTime = new JLabel("Total time:");
		totalTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		totalTime.setBounds(20, 29, 82, 20);
		layeredPane_localStats.add(totalTime);

		JLabel gamePlayed = new JLabel("Games played:");
		gamePlayed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gamePlayed.setBounds(20, 60, 129, 20);
		layeredPane_localStats.add(gamePlayed);

		JLabel gamesWon = new JLabel("Games won:");
		gamesWon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gamesWon.setBounds(20, 90, 115, 20);
		layeredPane_localStats.add(gamesWon);

		JLabel winPercentage = new JLabel("Win percentage:");
		winPercentage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		winPercentage.setBounds(20, 120, 115, 20);
		layeredPane_localStats.add(winPercentage);

		JLabel bestScore = new JLabel("Best score:");
		bestScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bestScore.setBounds(20, 150, 115, 20);
		layeredPane_localStats.add(bestScore);

		label_totalTime = new JLabel("00:00:00");
		label_totalTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_totalTime.setBounds(159, 29, 70, 20);
		layeredPane_localStats.add(label_totalTime);

		label_gamesPlayed = new JLabel("0");
		label_gamesPlayed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_gamesPlayed.setBounds(159, 60, 70, 20);
		layeredPane_localStats.add(label_gamesPlayed);

		label_gamesWon = new JLabel("0");
		label_gamesWon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_gamesWon.setBounds(159, 90, 70, 20);
		layeredPane_localStats.add(label_gamesWon);

		label_winPercentage = new JLabel("0 %");
		label_winPercentage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_winPercentage.setBounds(159, 120, 70, 20);
		layeredPane_localStats.add(label_winPercentage);

		label_bestScore = new JLabel("0");
		label_bestScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_bestScore.setBounds(159, 150, 70, 20);
		layeredPane_localStats.add(label_bestScore);

		updateStatistics();

		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StatisticsManager playerStats = new StatisticsManager();
				playerStats.reset();
				updateStatistics();
			}
		});

		btnReset.setBounds(88, 193, 61, 23);
		layeredPane_localStats.add(btnReset);


		/************** Ranking tab ***************/

		JLayeredPane layeredPane_globalRanking = new JLayeredPane();
		tabbedPane.addTab("Global ranking", null, layeredPane_globalRanking, null);
		layeredPane_globalRanking.setLayout(null);

		JLabel rank = new JLabel("#");
		rank.setFont(new Font("Tahoma", Font.BOLD, 11));
		rank.setBounds(10, 10, 14, 14);
		layeredPane_globalRanking.add(rank);

		JLabel date = new JLabel("Date");
		date.setFont(new Font("Tahoma", Font.BOLD, 11));
		date.setBounds(30, 10, 37, 14);
		layeredPane_globalRanking.add(date);

		JLabel playername = new JLabel("Player name");
		playername.setFont(new Font("Tahoma", Font.BOLD, 11));
		playername.setBounds(105, 10, 82, 14);
		layeredPane_globalRanking.add(playername);

		JLabel score = new JLabel("Score");
		score.setFont(new Font("Tahoma", Font.BOLD, 11));
		score.setBounds(185, 10, 44, 14);
		layeredPane_globalRanking.add(score);

		ArrayList<ArrayList<String>> globalScore = new ScoreManager().getScores();

		int y = 30;
		for( ArrayList<String> line : globalScore ){
			int x = 10;
			int nbCol = 0;
			for( String field : line ){
				JLabel label = new JLabel(""+field);
				label.setBounds(x, y, 60, 14);
				layeredPane_globalRanking.add(label);

				switch(nbCol){
				case 0: x += 20; break;
				case 1: x += 75; break;
				case 2: x += 80; break;
				}

				nbCol++;
			}

			y += 20;
		}

		setLocationRelativeTo(null);
		setVisible(true);
	}




	private void updateStatistics(){
		StatisticsManager playerStats = new StatisticsManager();

		label_totalTime.setText(playerStats.getTotalTimePlayed());
		label_gamesPlayed.setText(playerStats.getGamesPlayed()+"");
		label_gamesWon.setText(playerStats.getGamesWon()+"");

		float winPercentage_value = 0;

		if( playerStats.getGamesPlayed() != 0 ){
			winPercentage_value = (float)Math.round((float)playerStats.getGamesWon()/playerStats.getGamesPlayed()*100*10)/10;
		}

		label_winPercentage.setText(winPercentage_value+" %");
		label_bestScore.setText(playerStats.getBestScore()+"");

	}




}
