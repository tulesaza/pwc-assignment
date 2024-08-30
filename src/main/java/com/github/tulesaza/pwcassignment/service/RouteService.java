package com.github.tulesaza.pwcassignment.service;

import com.github.tulesaza.pwcassignment.exception.NoLandCrossedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    private final GraphFetcher graphFetcher;
    private final PathResolver pathResolver;

    @Autowired
    public RouteService(GraphFetcher graphFetcher, PathResolver pathResolver) {
        this.graphFetcher = graphFetcher;
        this.pathResolver = pathResolver;
    }

    public List<String> route(String origin, String destination) {
        final var graph = graphFetcher.graph();
        final var path = pathResolver.path(origin, destination, graph);
        if (path.isEmpty()) {
            throw new NoLandCrossedException("Route from %s to %s does not exist".formatted(origin, destination));
        }
        return path;
    }
}
