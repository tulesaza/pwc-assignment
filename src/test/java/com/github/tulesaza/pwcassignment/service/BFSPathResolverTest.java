package com.github.tulesaza.pwcassignment.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BFSPathResolverTest {

    private final PathResolver sut = new BFSPathResolver();

    @ParameterizedTest
    @MethodSource("dataProvider")
    void path(String origin, String destination, Map<String, List<String>> graph, List<String> expected) {
        var actual = sut.path(origin, destination, graph);
        assertEquals(expected, actual);
    }


    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                // A B
                Arguments.of("A", "B", Map.of(), List.of()),
                // A-B
                Arguments.of("A", "B", Map.of("A", List.of("B"), "B", List.of("A")), List.of("A", "B")),
                // A-C-B
                Arguments.of("A", "B", Map.of("A", List.of("C"), "B", List.of("C"), "C", List.of("A", "B")), List.of("A", "C", "B")),
                // A-C Y-Z
                Arguments.of("A", "Z", Map.of("A", List.of("C"), "Z", List.of("Y"), "C", List.of("A"), "Y", List.of("Z")), List.of()),
                // A - B - C - D
                //  \  |      /
                //   --E ____/
                Arguments.of("A", "D", Map.of("A", List.of("B", "E"), "B", List.of("A", "E", "C"), "C", List.of("B", "D"), "D", List.of("C", "E"),
                        "E", List.of("A", "B", "D")), List.of("A", "E", "D"))
        );
    }
}