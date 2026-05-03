package org.eqdev.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.eqdev.server.model.Card;
import org.eqdev.server.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

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
    }
    
}
