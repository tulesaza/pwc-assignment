package com.github.tulesaza.pwcassignment.service;

import com.github.tulesaza.pwcassignment.exception.NoLandCrossedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private PathResolver resolver;

    @Mock
    private GraphFetcher graphFetcher;

    @InjectMocks
    private RouteService sut;

    @Test
    void noRouteExpectedException() {
        when(graphFetcher.graph()).thenReturn(Map.of("A", List.of("B")));
        when(resolver.path(eq("A"), eq("C"), anyMap())).thenReturn(List.of());

        assertThrows(NoLandCrossedException.class, () -> sut.route("A", "C"));
    }

    @Test
    void routeExists() {
        when(graphFetcher.graph()).thenReturn(Map.of("A", List.of("B"), "B", List.of("A")));
        when(resolver.path(eq("A"), eq("B"), anyMap())).thenReturn(List.of("A", "B"));

        assertEquals(List.of("A", "B"), sut.route("A", "B"));
    }
}