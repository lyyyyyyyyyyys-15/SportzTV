package com.sportztv;

public class Channel {
    public String name;
    public String category;
    public String logo;
    public String encryptedUrl;
    public String encryptedReferer;
    public String encryptedOrigin;
    public String encryptedUserAgent;

    public Channel(String name, String category, String logo,
                   String url, String referer, String origin, String userAgent) {
        this.name = name;
        this.category = category;
        this.logo = logo;
        this.encryptedUrl = url;
        this.encryptedReferer = referer;
        this.encryptedOrigin = origin;
        this.encryptedUserAgent = userAgent;
    }
}
