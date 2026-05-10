package org.eqdev.server.service;

import java.util.List;
import java.util.Optional;

import org.eqdev.server.model.Deck;
import org.eqdev.server.model.DeckCard;
import org.eqdev.server.model.DeckCardId;
import org.eqdev.server.model.Card;
import org.eqdev.server.repository.AppUserRepository;
import org.eqdev.server.repository.CardRepository;
import org.eqdev.server.repository.DeckRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class DeckService {
    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public DeckService(DeckRepository deckRepository, AppUserRepository appUserRepository, CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    public List<Deck> allDecks() {
        return deckRepository.findAll();
    }

    public List<Deck> allDecksForUser(String username) {
        return deckRepository.findByUserUsername(username);
    }

    public Deck createDeck(String deckName, String username) {
        Deck deck = new Deck();
        deck.setDeckName(deckName);
        deck.setUser(username);
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
        } else {
            throw new RuntimeException("Deck not found or unauthorized");
        }
    }

    public Deck addCardToDeck(Long deckId, Long cardId, int quantity, String username) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found"));

        if (!deck.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized access to deck");
        }

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        Optional<DeckCard> existing = deck.getDeckCards().stream()
                .filter(dc -> dc.getId().getCardId().equals(cardId))
                .findFirst();

        if (existing.isPresent()) {
            DeckCard dc = existing.get();
            dc.setQuantity(dc.getQuantity() + quantity);
        } else {
            DeckCardId dcId = new DeckCardId(deckId, cardId);
            
            DeckCard deckCard = new DeckCard();
            deckCard.setId(dcId);
            deckCard.setDeck(deck);
            deckCard.setCard(card);
            deckCard.setQuantity(quantity);
            
            deck.getDeckCards().add(deckCard);
        }

        return deckRepository.save(deck);
    }

    public Deck removeCardFromDeck(Long deckId, Long cardId, int quantityToRemove, String username) {
        Deck deck = deckRepository.findById(deckId)
            .orElseThrow(() -> new RuntimeException("Deck not found"));

        if (!deck.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        Optional<DeckCard> existing = deck.getDeckCards().stream()
            .filter(dc -> dc.getId().getCardId().equals(cardId))
            .findFirst();

        if (existing.isPresent()) {
            DeckCard dc = existing.get();
            int newQuantity = dc.getQuantity() - quantityToRemove;

            if (newQuantity > 0) {
                dc.setQuantity(newQuantity);
            } else {
                deck.getDeckCards().remove(dc);
            }
        } else {
            throw new RuntimeException("Card not found in this deck");
        }

        return deckRepository.save(deck);
    }

    public Deck getDeckById(Long deckId) {
        return deckRepository.findById(deckId)
            .orElseThrow(() -> new RuntimeException("Deck with ID " + deckId + " not found"));
    }
}
