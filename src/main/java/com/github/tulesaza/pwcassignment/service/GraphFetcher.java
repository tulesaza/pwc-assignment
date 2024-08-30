package com.github.tulesaza.pwcassignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface GraphFetcher {
    Map<String, List<String>> graph();
}
