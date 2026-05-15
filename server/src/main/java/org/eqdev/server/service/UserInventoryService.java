package org.eqdev.server.service;

import java.util.Optional;

import org.eqdev.server.model.AppUser;
import org.eqdev.server.model.Card;
import org.eqdev.server.model.UserInventory;
import org.eqdev.server.model.UserInventoryId;
import org.eqdev.server.repository.CardRepository;
import org.eqdev.server.repository.UserInventoryRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserInventoryService {
    private final CardRepository cardRepository;
    private final UserInventoryRepository inventoryRepository;

    public UserInventoryService(CardRepository cardRepository, UserInventoryRepository inventoryRepository) {
        this.cardRepository = cardRepository;
        this.inventoryRepository = inventoryRepository;
    }
    
    @Transactional
    public UserInventory addCardToInventory(AppUser user, Long cardId, Integer quantity) {
        Optional<Card> card = cardRepository.findCardByCardId(cardId);
        UserInventoryId id = new UserInventoryId(user.getId(), cardId);
        return inventoryRepository.findById(id)
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + quantity);
                    return inventoryRepository.save(existing);
                })
                .orElseGet(() -> {
                    UserInventory newEntry = new UserInventory(user, card.get(), quantity);
                    return inventoryRepository.save(newEntry);
                });
    }
}
