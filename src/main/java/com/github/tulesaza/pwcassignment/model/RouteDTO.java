package com.github.tulesaza.pwcassignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteDTO {
    private List<String> route = null;

    public RouteDTO(List<String> route) {
        this.route = route;
    }

    public RouteDTO() {
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }
}
