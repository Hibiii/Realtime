package hibi.realtime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import net.fabricmc.loader.api.FabricLoader;

public final class Config {

	public static boolean enabled = false;
	public static boolean syncSunTimes = false;

	public static void load() {
		try {
			if(file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = br.readLine();
				do {
					if(line.startsWith("enabled "))
						enabled = Boolean.parseBoolean(line.substring(8));
					if(line.startsWith("syncSunTimes "))
						syncSunTimes = Boolean.parseBoolean(line.substring(13));
					line = br.readLine();
				} while (line != null);
				br.close();
			}
		}
		catch (Throwable e) {
			// Empty catch block
		}
	}

	public static void save() {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write("enabled " + Boolean.toString(enabled) + "\n");
			writer.write("syncSunTimes " + Boolean.toString(syncSunTimes) + "\n");
			writer.close();
		}
		catch (Throwable e) {
			// Empty catch block
		}
	}

	private static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "realtime.properties");
}
