package fr.utbm.lp24.minesweeper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPapi {
	
	private String url = "http://lp24.christophe-ribot.fr/api";
	
	public HTTPapi(){
		
	}
	public String[] getScore(){
		String result = this.getadress("getscore", "");
		if(result == null){
			return null;
		}else {
			//  TODO add xml dom parser
			return null;
		}
	}
	public int addScore(int score){
		String data = score + "";
		String result = this.getadress("addscore", data);
		if(result == null){
			return 0;
		}else {
			return 1;
		}
	}
	private String getadress(String action, String data){
		
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is POST
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "UTBM Minesweeper");
			String urlParameters = "action= " + action + "&data=" + data;
			 
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
	 
			int responseCode = con.getResponseCode();
			if (responseCode != 200){
				return null;
			}
	 
			// get results
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error connection internet");
			return null;
		}
		
	}
}
