package com.logos.gutensearch.dto;

import java.util.List;

public record GutendexBookData(
    String title,
    List<GutendexAutor> authors,
    List<String> languages,
    Integer download_count
) {}