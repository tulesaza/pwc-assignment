package com.github.tulesaza.pwcassignment.controller;

import com.github.tulesaza.pwcassignment.model.RouteDTO;
import com.github.tulesaza.pwcassignment.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// TODO use open api instead
// TODO use controller advice map exceptions to HTTP status codes
@RestController
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routing/{origin}/{destination}")
    public RouteDTO calculateRoute(@PathVariable String origin, @PathVariable String destination) {
        return new RouteDTO(routeService.route(origin, destination));
    }
}
