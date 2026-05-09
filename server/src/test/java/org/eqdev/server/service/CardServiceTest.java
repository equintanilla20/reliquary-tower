package org.eqdev.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

import org.eqdev.server.dto.CardFilter;
import org.eqdev.server.model.Card;
import org.eqdev.server.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    public void shouldReturnCardsWhenSearchingByName() {
        Card heartfireHero = new Card();
        heartfireHero.setCardName("Heartfire Hero");

        when(cardRepository.findByCardNameContainingIgnoreCase(anyString(), any(Pageable.class)))
            .thenReturn(List.of(heartfireHero));

        List<Card> results = cardService.searchCardsByName("Heartfire Hero", 0, 1);

        assertEquals(1, results.size());
        assertEquals("Heartfire Hero", results.get(0).getCardName());

        verify(cardRepository, times(1)).findByCardNameContainingIgnoreCase(eq("Heartfire Hero"), any(Pageable.class));
        System.out.println("Test passed: Card search by name returned expected results.");
    }

    @Test
    void searchCardsAdvanced_ShouldRemoveDuplicateNames() {
        Card oculus1 = new Card();
        oculus1.setCardName("Abhorrent Oculus");
        oculus1.setCardSet("DSK"); // Main set

        Card oculus2 = new Card();
        oculus2.setCardName("Abhorrent Oculus");
        oculus2.setCardSet("PDSK"); // Promo set

        Card ajani = new Card();
        ajani.setCardName("Ajani, Caller of the Pride");

        List<Card> mockPrintings = List.of(oculus1, oculus2, ajani);
        
        when(cardRepository.findAll(org.mockito.ArgumentMatchers.<Specification<Card>>any(), any(PageRequest.class)))
            .thenReturn(new PageImpl<>(mockPrintings));

        CardFilter filter = new CardFilter(null, null, null, null, null, null, null, null, null);
        List<Card> results = cardService.searchCardsAdvanced(filter, 0, 10);

        assertEquals(2, results.size(), "Should have filtered out the duplicate printing");
        assertEquals("Abhorrent Oculus", results.get(0).getCardName());
        assertEquals("Ajani, Caller of the Pride", results.get(1).getCardName());
        System.out.println("Test passed: Duplicate card names were filtered out successfully.");
    }

    @Test
    void searchCardsAdvanced_ShouldReturnEmptyList_WhenNoMatchFound() {
        when(cardRepository.findAll(org.mockito.ArgumentMatchers.<Specification<Card>>any(), any(PageRequest.class)))
            .thenReturn(new PageImpl<>(List.of()));

        CardFilter filter = new CardFilter("NonExistentCard", null, null, null, null, null, null, null, null);
        List<Card> results = cardService.searchCardsAdvanced(filter, 0, 10);

        assertEquals(0, results.size());
        System.out.println("Test passed: Empty list returned when no matches found.");
    }
}
