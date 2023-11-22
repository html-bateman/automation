package commonLibs.utilities;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigUtilities {
	public static Properties readProperties(String filename) throws Exception {
		filename = filename.trim();
		InputStream fileReader = new FileInputStream(filename);
		Properties property = new Properties();
		property.load(fileReader);
		return property;
	}
}
