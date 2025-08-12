package com.logos.gutensearch.dto;

import java.util.List;


public record GutendexBookData(
    int id,
    String title,
    List<String> languages,
    Integer download_count,
    List<GutendexAutor> authors
    
) {}