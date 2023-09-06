package model;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConcertConfigurator {
	 private static final String CONFIG_FILE = "/config.properties";

	    public Properties loadConfigurations() {
	        Properties properties = new Properties();

	        try (InputStream inputStream = ConcertConfigurator.class.getResourceAsStream(CONFIG_FILE)) {
	            properties.load(inputStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return properties;
	    }
}
	
