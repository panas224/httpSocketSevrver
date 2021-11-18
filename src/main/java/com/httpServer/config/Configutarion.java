package com.httpServer.config;
//map json to this file
public class Configutarion {
    private int port;
    private String webroot;
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public String getWebRoot() {
        return webroot;
    }

    public void setWebRoot(String webroot) {
        this.webroot = webroot;
    }

}
