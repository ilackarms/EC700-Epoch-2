package com.ec700.flowdroidtest.flowdroidtestapp.vuln;

import java.net.URL;

/**
* Created by low-poly-Life on 3/26/2015.
*/ // Object to receive
public class JsonIP {
    private String ip;
    private String about;
    private URL url;
    public String s;

    public String getIp() {
        return ip;
    }

    public String getAbout() {
        return about;
    }

    public URL getPro() {
        return url;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setPro(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "JsonIp [ip=" + ip + ", about=" + about
                + ", Pro!=" + url + "]";
    }
}
