package com.st.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="druid")
public class DruidProperty {

    private String urlMapping;
    private String loginUsername;
    private String loginPassword;
    private String logSlowSql;
    private String urlPatterns;
    private String exclusions;
    private String profileEnable;

    public String getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(String urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLogSlowSql() {
        return logSlowSql;
    }

    public void setLogSlowSql(String logSlowSql) {
        this.logSlowSql = logSlowSql;
    }

    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }

    public String getProfileEnable() {
        return profileEnable;
    }

    public void setProfileEnable(String profileEnable) {
        this.profileEnable = profileEnable;
    }
}
