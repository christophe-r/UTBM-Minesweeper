package fr.utbm.lp24.minesweeper;

import java.util.prefs.Preferences;

/**
 * To manage the user preferences
 * @author Christophe
 */
public class PreferencesManager {

	private Preferences prefs;

	public PreferencesManager(){
		// Retrieve the user preference node for this package
		prefs = Preferences.userNodeForPackage(fr.utbm.lp24.minesweeper.Main.class);
	}

	/**
	 * This method is used to set an user preference
	 * @param pref_key The key of the reference
	 * @param pref_value The value associated to the preference key
	 */
	public void setPref(String pref_key, String pref_value){
		prefs.put(pref_key, pref_value);
	}

	/**
	 * This method allows us to get an user preference
	 * @param pref_key The key of the wanted preference value
	 * @param default_value Which is returned if the preference record doesn't exist
	 * @return The preference or the default value
	 */
	public String getPref(String pref_key, String default_value){
		return prefs.get(pref_key, default_value);
	}

}
