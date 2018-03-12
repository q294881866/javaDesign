package org.jiumao.example.urlFilter;

import org.jiumao.example.urlFilter.data.DataFile;
import org.jiumao.example.urlFilter.data.DataFileHeader;
import org.jiumao.example.urlFilter.tree.CatalogFile;
import org.jiumao.example.urlFilter.tree.CatalogFileHeader;
import org.jiumao.example.utils.annotation.Singleton;
@Singleton
public class URLSrvConfig {
    
    private CatalogFileHeader catalogFileHeader;
    private DataFileHeader dataFileHeader;
    
    private DataFile dataFile;
    private CatalogFile catalogFile;
    
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

    public CatalogFileHeader getCatalogFileHeader() {
        return catalogFileHeader;
    }

    public void setCatalogFileHeader(CatalogFileHeader catalogFileHeader) {
        this.catalogFileHeader = catalogFileHeader;
    }

    public DataFileHeader getDataFileHeader() {
        return dataFileHeader;
    }

    public void setDataFileHeader(DataFileHeader dataFileHeader) {
        this.dataFileHeader = dataFileHeader;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    public CatalogFile getCatalogFile() {
        return catalogFile;
    }

    public void setCatalogFile(CatalogFile catalogFile) {
        this.catalogFile = catalogFile;
    }
    
    
    
    
}
