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


public class ScoreManager {

	private static String apiUrl = "http://lp24.christophe-ribot.fr/api/";

	public ScoreManager(){


	}


	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}




	public ArrayList<ArrayList<String>> getScores(){
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
				

				if( responseCode.equals("1") == false){
					return null;
				}

				System.out.println("code: "+responseCode);

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
					return null;
				}
				
				String rank = doc.getElementsByTagName("rank").item(0).getTextContent();
				
				/*System.out.println("code: "+responseCode);
				System.out.println("rank: "+rank);*/
				
				return rank;
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return null;

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