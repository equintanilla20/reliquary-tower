package org.eqdev.server.dto;

import java.util.List;

public record CardFilter (
    String cardName,
    String rarity,
    String type,
    Double cmc,
    List<String> colors,
    String set,
    List<String> keywords,
    String format,
    String status
) {}
