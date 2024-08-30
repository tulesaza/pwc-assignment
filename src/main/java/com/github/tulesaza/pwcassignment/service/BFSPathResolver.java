package com.github.tulesaza.pwcassignment.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Since the graph is unweighted and unidirectional, we can use BFS to find the shortest path.
 */
@Service
public class BFSPathResolver implements PathResolver {

    @Override
    public List<String> path(String origin, String destination, Map<String, List<String>> graph) {
        if (!graph.containsKey(origin) && !graph.containsKey(destination)) {
            return List.of();
        }
        var paths = new LinkedList<LinkedList<String>>();
        var visited = new HashSet<String>();
        paths.offer(new LinkedList<>(List.of(origin)));
        while (!paths.isEmpty()) {
            var currentPath = paths.poll();
            var lastCountry = currentPath.getLast();
            if (lastCountry.equals(destination)) {
                return currentPath;
            }
            visited.add(lastCountry);
            for (var neighborCountry : graph.getOrDefault(lastCountry, List.of())) {
                if (!visited.contains(neighborCountry)) {
                    var newPath = new LinkedList<>(currentPath);
                    newPath.offer(neighborCountry);
                    paths.offer(newPath);
                }
            }
        }
        return List.of();
    }
}
