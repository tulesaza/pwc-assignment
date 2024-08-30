package com.github.tulesaza.pwcassignment.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private String countriesUrl;

    public String getCountriesUrl() {
        return countriesUrl;
    }

    public void setCountriesUrl(String countriesUrl) {
        this.countriesUrl = countriesUrl;
    }
}
