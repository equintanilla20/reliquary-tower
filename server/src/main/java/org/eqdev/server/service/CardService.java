package org.eqdev.server.service;

import java.util.List;
import java.util.Optional;

import org.eqdev.server.model.Card;
import org.eqdev.server.repository.CardRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("cardName").ascending());
        return cardRepository.findAll(pageable).getContent();
    }

    public List<Card> searchCardsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("cardName").ascending());
        return cardRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }
}
