package org.jiumao.example.urlFilter;



public class URLSrvConfig {
    
    
    private String urlSrvHome = System.getProperty(MixAll.URLSRV_HOME,
        System.getenv(MixAll.URLSRV_HOME));
    
    public URLSrvConfig() {
    }

    public String getUrlSrvHome() {
        return urlSrvHome;
    }

    public void setUrlSrvHome(String urlSrvHome) {
        this.urlSrvHome = urlSrvHome;
    }
    
    
}
