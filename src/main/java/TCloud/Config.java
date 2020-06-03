package TCloud;

import java.io.FileReader;

public class Config {
    String secId;
    String secKey;
    String nif;
    String domain;
    String subDomain;

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    @Override
    public String toString() {
        return "Config{" +
                "secId='" + secId + '\'' +
                ", secKey='" + secKey + '\'' +
                ", nif='" + nif + '\'' +
                ", domain='" + domain + '\'' +
                ", subDomain='" + subDomain + '\'' +
                '}';
    }
}
