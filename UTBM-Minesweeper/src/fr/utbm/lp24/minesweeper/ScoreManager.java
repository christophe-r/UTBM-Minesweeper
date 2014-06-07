package fr.utbm.lp24.minesweeper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Interface to manage listeners
 * @author vincent
 */
interface ScoreListener { 
	public void getScore(ArrayList<ArrayList<String>> globalScore);
	public void addScore(Boolean check);
	public void getRank(int rank); 
}


/**
 * Class which gets informations from the Internet API
 * @author Vincent and Christophe
 */
public class ScoreManager implements Runnable {

	private static String apiUrl = "http://lp24.christophe-ribot.fr/api/";
	private ScoreListener listeners;
	private String action;
	private String name = null;
	private int score = 0;


	/**
	 * Constructor
	 * @param toAdd link the class with the ScoreManager
	 * @param action 
	 */ 
	public ScoreManager(ScoreListener toAdd, String action){
		this.action = action;
		listeners = toAdd; // link listener
	}

	/**
	 * Constructor
	 * @param toAdd
	 * @param action
	 * @param score
	 */
	public ScoreManager(ScoreListener toAdd, String action, int score){
		this.action = action;
		this.score = score;
		listeners = toAdd; // link listener
	}

	/**
	 * Constructor
	 * @param toAdd link the class with the ScoreManager
	 * @param action 
	 */ 
	public ScoreManager(ScoreListener toAdd, String action, String name, int score){
		this.action = action;
		this.name = name;
		this.score = score;
		listeners = toAdd; // link listener
	}


	/**
	 * Get the 10 firsts scores and return an array
	 */
	public void getScores(){
		System.out.println("Internet API: Starting to load scores.");
		String result = this.getAddress("getScores", "");
		if(result == null){
			listeners.getScore(null);
			return;
		} else {

			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder db = dbFactory.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result));
				Document doc = db.parse(is);
				doc.getDocumentElement().normalize();

				String responseCode = doc.getElementsByTagName("code").item(0).getTextContent();


				if( responseCode.equals("1") == false){
					listeners.getScore(null); //  send a notification to the listener
					return;
				}


				ArrayList<ArrayList<String>> scores = new ArrayList<ArrayList<String>>();

				NodeList nodes = doc.getElementsByTagName("line");
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						ArrayList<String> score = new ArrayList<String>();
						score.add(getValue("rank", element));
						score.add(getValue("date", element));
						score.add(getValue("playername", element));
						score.add(getValue("score", element));

						scores.add(score);

						/*System.out.println("rank: "+getValue("rank", element));
						System.out.println("date: "+getValue("date", element));
						System.out.println("playername: "+getValue("playername", element));
						System.out.println("score: "+getValue("score", element));
						System.out.println("");*/
					}
				}

				System.out.println("Internet API: Scores load complete.");
				listeners.getScore(scores); //  send a notification to the listener

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Internet API: Internet scores failed to load.");
			}

			//listeners.getScore(null);
		}

	}


	/**
	 * Add a new score on the Internet API
	 * @param playerName
	 * @param score
	 * @return the rank of this new score
	 */
	public void setScore(String playerName, int score){	
		System.out.println("Internet API: Starting to add score.");
		String data = "s=" + score + "&pn=" + playerName;
		String result = this.getAddress("setScore", data);
		if(result == null){
			listeners.addScore(false);
		} else {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder db = dbFactory.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result));
				Document doc = db.parse(is);
				doc.getDocumentElement().normalize();

				String responseCode = doc.getElementsByTagName("code").item(0).getTextContent();

				if( responseCode.equals("1") == false){
					listeners.addScore(false);
					return;
				}
				/*String rank = doc.getElementsByTagName("rank").item(0).getTextContent();// after code update this part is no longer used

				System.out.println("code: "+responseCode);
				System.out.println("rank: "+rank);

				rank = rank.replaceAll("[^0-9]", "");
				int rankint =  rank.equals("")?0:Integer.parseInt(rank);*/
				listeners.addScore(true);
				System.out.println("Internet API: Finished to add score.");

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Internet API: Failed to add score.");
			}

		}
	}


	/**
	 * Get a rank from a score from the Internet API
	 * @param score
	 */
	public void getRank(int score){	
		System.out.println("Internet API: Starting to get rank.");
		String data = "s=" + score;
		String result = this.getAddress("getRank", data);
		if(result == null){
			listeners.addScore(false);
		} else {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder db = dbFactory.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result));
				Document doc = db.parse(is);
				doc.getDocumentElement().normalize();

				String responseCode = doc.getElementsByTagName("code").item(0).getTextContent();

				if( responseCode.equals("1") == false ){
					System.out.println("Internet API: Failed to get rank.");
					listeners.getRank(0);
					return;
				}

				String rank = doc.getElementsByTagName("rank").item(0).getTextContent();

				rank = rank.replaceAll("[^0-9]", "");
				int rankInt =  rank.equals("")?0:Integer.parseInt(rank);

				listeners.getRank(rankInt);

				System.out.println("Internet API: Finished to get rank.");

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Internet API: Failed to get rank.");
			}

		}
	}


	/**
	 * Get the value of a element in a document
	 * @param tag
	 * @param element
	 * @return
	 */
	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}


	/**
	 * Send a request to the Internet API
	 * @param action
	 * @param urlParameters
	 * @return A String contains the results of the request
	 */
	private String getAddress(String action, String urlParameters){

		HttpURLConnection connection = null;  
		try {

			URL obj = new URL(apiUrl+action);
			connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			// optional default is POST
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "UTBM Minesweeper");

			// Send post request
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = connection.getResponseCode();

			if (responseCode != 200){
				return null;
			}

			// get results
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			return response.toString();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Internet connection error.");
			return null;

		} finally {
			if(connection != null) {
				connection.disconnect(); 
			}
		}

	}


	/**
	 * Main method of the thread
	 */
	public void run() {		
		if( action.equals("getScore") ){
			getScores();
		}

		if( action.equals("setScore") ){
			if (name != null || this.score != 0)
				setScore(name,this.score);
		}

		if( action.equals("getRank") ){
			getRank(this.score);
		}

	}

}