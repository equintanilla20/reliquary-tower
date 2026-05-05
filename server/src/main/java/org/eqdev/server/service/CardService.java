package org.eqdev.server.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import org.eqdev.server.dto.CardFilter;
import org.eqdev.server.model.Card;
import org.eqdev.server.repository.CardRepository;
import org.eqdev.server.repository.specification.CardSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> allCards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("cardName").ascending());
        return cardRepository.findAll(pageable).getContent();
    }

    public List<Card> searchCardsByName(String cardName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("cardName").ascending());
        return cardRepository.findByCardNameContainingIgnoreCase(cardName, pageable);
    }

    public List<Card> searchCardsAdvanced(CardFilter filter, int page, int size) {
        Specification<Card> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (filter.cardName() != null) {
            spec = spec.and(CardSpecification.hasCardName(filter.cardName()));
        }

        if (filter.rarity() != null) {
            spec = spec.and(CardSpecification.hasCardRarity(filter.rarity()));
        }

        if (filter.type() != null) {
            spec = spec.and(CardSpecification.hasCardType(filter.type()));
        }

        if (filter.colors() != null && !filter.colors().isEmpty()) {
            for (String color : filter.colors()) {
                spec = spec.and(CardSpecification.hasCardColors(color));
            }
        }

        if (filter.set() != null) {
            spec = spec.and(CardSpecification.hasCardSet(filter.set()));
        }

        if (filter.format() != null && filter.status() != null) {
            spec = spec.and(CardSpecification.isLegalIn(filter.format(), filter.status()));
        }

        if (filter.cmc() != null) {
            spec = spec.and((root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(root.get("cmc"), filter.cmc()));
        }

        if (filter.keywords() != null && !filter.keywords().isEmpty()) {
            for (String keyword : filter.keywords()) {
                spec = spec.and(CardSpecification.hasCardKeywords(keyword));
            }
        }

        if (filter.status() != null) {
            spec = spec.and((root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(
                    criteriaBuilder.function("jsonb_extract_path_text", String.class,
                        root.get("legalities"), 
                    criteriaBuilder.literal(filter.format())),
                filter.status()
            ));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("cardName").ascending());

        List<Card> allCards = cardRepository.findAll(spec, pageable).getContent();
        return allCards.stream()
                .collect(Collectors.toMap(
                    Card::getCardName, card -> card, (
                        existing, replacement) -> existing, LinkedHashMap::new))
                        .values()
                        .stream()
                        .toList();
    }

    public Optional<Card> getCardById(Long cardId) {
        return cardRepository.findCardByCardId(cardId);
    }

    public List<Card> searchCardsByRarity(String rarity) {
        return cardRepository.findAll().stream()
                .filter(card -> card.getCardRarity() != null && card.getCardRarity().equalsIgnoreCase(rarity))
                .toList();
    }

    public List<Card> searchCardsByType(String type) {
        return cardRepository.findAll().stream()
                .filter(card -> card.getCardType() != null && card.getCardType().equalsIgnoreCase(type))
                .toList();
    }

    public List<Card> searchCardsByColor(String color) {
        return cardRepository.findAll().stream()
                .filter(card -> card.getCardColors() != null && card.getCardColors().toLowerCase().contains(color.toLowerCase()))
                .toList();
    }

    public List<Card> searchCardsByColorIdentity(String colorIdentity) {
        return cardRepository.findAll().stream()
                .filter(card -> card.getCardColorIdentity() != null && card.getCardColorIdentity().toLowerCase().contains(colorIdentity.toLowerCase()))
                .toList();
    }

    public List<Card> searchCardsBySet(String set) {
        return cardRepository.findAll().stream()
                .filter(card -> card.getCardSet() != null && card.getCardSet().equalsIgnoreCase(set))
                .toList();
    }

    public List<Card> searchCardsBySetName(String setName) {
        return cardRepository.findAll().stream()
                .filter(card -> card.getCardSetName() != null && card.getCardSetName().equalsIgnoreCase(setName))
                .toList();
    }
}
