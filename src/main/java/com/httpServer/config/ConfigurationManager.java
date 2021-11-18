package com.httpServer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpServer.util.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//using singelton becuase no need more than 1 configuration manager
public class ConfigurationManager {
    private static ConfigurationManager configurationManager;

    private static Configutarion currentConfiguration;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    //    load configuration file by path provided
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
        } catch (IOException e) {
           throw new HttpConfigurationException(e);
        }
        JsonNode configuration = null;
        try {
            configuration = json.parse(stringBuffer.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error paring the configuration file");
        }
        try {
            currentConfiguration = json.fromJson(configuration, Configutarion.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error paring the configuration file, internal",e);
        }
    }

    //returns the current loaded configuration
    public Configutarion getCurrentConfiguration() {
        if (currentConfiguration ==null) {
            throw new HttpConfigurationException("No current configuration Set.");
        }
        return currentConfiguration;

    }
}
