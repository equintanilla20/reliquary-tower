package org.eqdev.server.dto;

import java.util.List;

public record CardFilter (
    String cardName,
    String cardRarity,
    String cardType,
    Double cmc,
    List<String> cardColors,
    String cardSet,
    List<String> cardKeywords,
    String format,
    String status
) {}
