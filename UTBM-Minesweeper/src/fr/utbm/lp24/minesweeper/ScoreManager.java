package fr.utbm.lp24.minesweeper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class ScoreManager {

	private static String apiUrl = "http://lp24.christophe-ribot.fr/api/";

	public ScoreManager(){


	}


	public static void main(String[] args){
		ScoreManager sm = new ScoreManager();
		System.out.println("get: "+sm.getScores());
		//System.out.println("set: "+sm.setScore("Test", 4999));
	}


	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}




	public ArrayList<HashMap<String, String>> getScores(){
		String result = this.getAddress("getScores", "");

		if(result == null){
			return null;
		} else {

			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder db = dbFactory.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result));
				Document doc = db.parse(is);
				doc.getDocumentElement().normalize();

				String responseCode = doc.getElementsByTagName("code").item(0).getTextContent();
				if( responseCode != "1" ){
					return null;
				}

				System.out.println("code: "+responseCode);

				ArrayList<HashMap<String, String>> scores = new ArrayList<HashMap<String, String>>();

				NodeList nodes = doc.getElementsByTagName("line");
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						HashMap<String, String> score = new HashMap<String, String>();
						score.put("rank", getValue("rank", element));
						score.put("date", getValue("date", element));
						score.put("playername", getValue("playername", element));
						score.put("score", getValue("score", element));

						/*System.out.println("rank: "+getValue("rank", element));
						System.out.println("date: "+getValue("date", element));
						System.out.println("playername: "+getValue("playername", element));
						System.out.println("score: "+getValue("score", element));
						System.out.println("");*/
					}
				}

				return scores;
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return null;

		}

	}




	public String setScore(String playerName, int score){
		String data = "s=" + score + "&pn=" + playerName;
		String result = this.getAddress("setScore", data);
		if(result == null){
			return null;
		}else {
			return result;
		}
	}






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


}