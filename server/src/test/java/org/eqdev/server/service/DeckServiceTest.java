package org.eqdev.server.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.eqdev.server.model.*;
import org.eqdev.server.repository.CardRepository;
import org.eqdev.server.repository.DeckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeckServiceTest {

    @Mock
    private DeckRepository deckRepository;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private DeckService deckService;

    private Deck testDeck;
    private Card testCard;
    private AppUser testUser;

    @BeforeEach
    void setUp() {
        testUser = new AppUser();
        testUser.setUsername("mtg_pro");

        testDeck = new Deck();
        testDeck.setDeckId(1L);
        testDeck.setDeckName("Sultai Midrange");
        testDeck.setUser(testUser);
        testDeck.setDeckCards(new ArrayList<>());

        testCard = new Card();
        testCard.setCardId(100L);
        testCard.setCardName("Lightning Bolt");
    }

    @Test
    void addCardToDeck_NewCard_ShouldCreateDeckCard() {

        when(deckRepository.findById(1L)).thenReturn(Optional.of(testDeck));
        when(cardRepository.findById(100L)).thenReturn(Optional.of(testCard));
        when(deckRepository.save(any(Deck.class))).thenReturn(testDeck);

        Deck result = deckService.addCardToDeck(1L, 100L, 4, "mtg_pro");

        assertEquals(1, result.getDeckCards().size());
        assertEquals(4, result.getDeckCards().get(0).getQuantity());
        verify(deckRepository, times(1)).save(testDeck);
        System.out.println("Test passed: New card added to deck successfully.");
    }

    @Test
    void addCardToDeck_ExistingCard_ShouldIncrementQuantity() {

        DeckCard existingEntry = new DeckCard(testDeck, testCard, 1);
        testDeck.getDeckCards().add(existingEntry);

        when(deckRepository.findById(1L)).thenReturn(Optional.of(testDeck));
        when(cardRepository.findById(100L)).thenReturn(Optional.of(testCard));
        when(deckRepository.save(any(Deck.class))).thenReturn(testDeck);

        Deck result = deckService.addCardToDeck(1L, 100L, 3, "mtg_pro");

        assertEquals(1, result.getDeckCards().size());
        assertEquals(4, result.getDeckCards().get(0).getQuantity());
        System.out.println("Test passed: Existing card quantity incremented successfully.");
    }

    @Test
    void removeCardFromDeck_ReduceQuantity_ShouldWork() {
        DeckCard existingEntry = new DeckCard(testDeck, testCard, 4);
        testDeck.getDeckCards().add(existingEntry);

        when(deckRepository.findById(1L)).thenReturn(Optional.of(testDeck));
        when(deckRepository.save(any(Deck.class))).thenReturn(testDeck);

        Deck result = deckService.removeCardFromDeck(1L, 100L, 1, "mtg_pro");

        assertEquals(3, result.getDeckCards().get(0).getQuantity());
        System.out.println("Test passed: Card quantity reduced successfully.");
    }

    @Test
    void removeCardFromDeck_RemoveAll_ShouldOrphanEntry() {
        DeckCard existingEntry = new DeckCard(testDeck, testCard, 1);
        testDeck.getDeckCards().add(existingEntry);

        when(deckRepository.findById(1L)).thenReturn(Optional.of(testDeck));
        when(deckRepository.save(any(Deck.class))).thenReturn(testDeck);

        Deck result = deckService.removeCardFromDeck(1L, 100L, 1, "mtg_pro");

        assertTrue(result.getDeckCards().isEmpty());
        System.out.println("Test passed: Card removed from deck successfully when quantity reduced to zero.");
    }

    @Test
    void addCardToDeck_WrongUser_ShouldThrowException() {
        when(deckRepository.findById(1L)).thenReturn(Optional.of(testDeck));

        assertThrows(RuntimeException.class, () -> {
            deckService.addCardToDeck(1L, 100L, 1, "hacker_user");
        });
        System.out.println("Test passed: Unauthorized user cannot add card to deck.");
    }
}