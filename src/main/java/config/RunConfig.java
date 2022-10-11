package config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class RunConfig {

    private final String finalUri;
    private final String Token;
    private Properties properties;
    public RunConfig() {
        this.finalUri = getProp("BaseUri");
        this.Token = getProp("Token");
    }

    private String getProp(String Key) {
        try {
            properties = new Properties();
            String filePath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "default.properties";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(Key);
    }

    public String getfinalUri() {
        return finalUri;
    }

    public String getToken() {
        return Token;
    }
}
