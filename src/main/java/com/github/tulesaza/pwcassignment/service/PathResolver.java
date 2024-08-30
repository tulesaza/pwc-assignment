package com.github.tulesaza.pwcassignment.service;

import java.util.List;
import java.util.Map;

public interface PathResolver {
    List<String> path(String origin, String destination, Map<String, List<String>> graph);
}
