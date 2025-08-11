package com.logos.gutensearch.dto;

import java.util.List;

public record GutendexResponse(
    int count,
    String next,
    String previous,
    List<GutendexBookData> results) {}