package org.eqdev.server.service;

import java.util.List;

import org.eqdev.server.model.Deck;
import org.eqdev.server.model.Card;
import org.eqdev.server.repository.AppUserRepository;
import org.eqdev.server.repository.DeckRepository;
import org.springframework.stereotype.Service;

@Service
public class DeckService {
    private final DeckRepository deckRepository;
    private final AppUserRepository appUserRepository;

    public DeckService(DeckRepository deckRepository, AppUserRepository appUserRepository) {
        this.deckRepository = deckRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Deck> allDecksForUser(String username) {
        return deckRepository.findByUserUsername(username);
    }

    public Deck createDeck(String deckName, String username) {
        Deck deck = new Deck();
        deck.setDeckName(deckName);
        deck.setUser(appUserRepository.findByUsername(username));
        return deckRepository.save(deck);
    }

    public boolean deleteDeck(Long deckId, String username) {
        Deck deck = getDeckById(deckId);
        if (deck != null && deck.getUser().getUsername().equals(username)) {
            deckRepository.deleteById(deckId);
            return true;
        }
        return false;
    }

    public Deck updateDeck(Long deckId, String deckName, String username) {
        Deck deck = getDeckById(deckId);
        if (deck != null && deck.getUser().getUsername().equals(username)) {
            deck.setDeckName(deckName);
            return deckRepository.save(deck);
        }
        return null;
    }

    public Deck addCardToDeck(Long deckId, Long cardId, int quantity, String username) {
        Deck deck = getDeckById(deckId);
        if (deck != null && deck.getUser().getUsername().equals(username)) {
            // Logic to add card to deck
            // This would involve creating a new DeckCard entity and saving it
            // For simplicity, this logic is not fully implemented here
            return deckRepository.save(deck);
        }
        return null;
    }

    public Deck removeCardFromDeck(Long deckId, Long cardId, String username) {
        Deck deck = getDeckById(deckId);
        if (deck != null && deck.getUser().getUsername().equals(username)) {
            // Logic to remove card from deck
            // This would involve deleting a DeckCard entity
            // For simplicity, this logic is not fully implemented here
            return deckRepository.save(deck);
        }
        return null;
    }

    public Deck getDeckById(Long deckId) {
        return deckRepository.findById(deckId).orElse(null);
    }
}
