package cz.vutbr.fit.gja.gjaddr.persistancelayer;

import java.io.File;

/**
 * Class whit user settings configuration.
 * 
 * @author Bc. Radek Gajdušek <xgajdu07@stud.fit.vutbr.cz>
 */
public class Settings {
	
	private static final String userHomeDir = System.getProperty("user.home");	
	
	public static String getDataDir()	{
		
		File dataDir = new File(userHomeDir, "/.gjaaddr/data");
		
		if (!dataDir.exists()) {
			dataDir.mkdirs();
		}
		
		return dataDir.getPath();
	}
}
