package com.logos.gutensearch.dto;

import java.util.List;
import java.util.Map;

public record GutendexBookData(
    int id,
    String title,
    List<GutendexAutor> authors,
    List<String> languages,
    List<String> translators,
    List<String> subjects,
    List<String> bookshelves,
    Integer download_count,
    Map<String, String> formats
) {}