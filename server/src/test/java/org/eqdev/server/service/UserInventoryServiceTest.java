package org.eqdev.server.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.eqdev.server.model.AppUser;
import org.eqdev.server.model.Card;
import org.eqdev.server.model.UserInventory;
import org.eqdev.server.model.UserInventoryId;
import org.eqdev.server.repository.CardRepository;
import org.eqdev.server.repository.UserInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserInventoryServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private UserInventoryRepository inventoryRepository;

    @InjectMocks
    private UserInventoryService inventoryService;

    private AppUser testUser;
    private Card testCard;
    private UserInventoryId inventoryId;

    @BeforeEach
    void setUp() {
        testUser = new AppUser();
        testUser.setId(1L);

        testCard = new Card();
        testCard.setCardId(100L);
        testCard.setCardName("Black Lotus");

        inventoryId = new UserInventoryId(1L, 100L);
    }

    @Test
    void addCardToInventory_ExistingRecord_ShouldIncrementQuantity() {
        UserInventory existingInventory = new UserInventory(testUser, testCard, 2);
        
        when(cardRepository.findCardByCardId(100L)).thenReturn(Optional.of(testCard));
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.of(existingInventory));
        when(inventoryRepository.save(any(UserInventory.class))).thenAnswer(i -> i.getArguments()[0]);

        UserInventory result = inventoryService.addCardToInventory(testUser, 100L, 3);

        assertEquals(5, result.getQuantity(), "Quantity should be 2 + 3 = 5");
        verify(inventoryRepository, times(1)).save(existingInventory);
    }

    @Test
    void addCardToInventory_NewRecord_ShouldCreateEntry() {
        when(cardRepository.findCardByCardId(100L)).thenReturn(Optional.of(testCard));
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.empty());
        when(inventoryRepository.save(any(UserInventory.class))).thenAnswer(i -> i.getArguments()[0]);

        UserInventory result = inventoryService.addCardToInventory(testUser, 100L, 1);

        assertNotNull(result);
        assertEquals(1, result.getQuantity());
        assertEquals(testCard, result.getCard());
        verify(inventoryRepository, times(1)).save(any(UserInventory.class));
    }
}