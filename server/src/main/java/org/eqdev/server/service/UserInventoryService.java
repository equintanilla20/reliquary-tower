package org.eqdev.server.service;

import java.util.Optional;

import org.eqdev.server.model.AppUser;
import org.eqdev.server.model.Card;
import org.eqdev.server.model.UserInventory;
import org.eqdev.server.model.UserInventoryId;
import org.eqdev.server.repository.AppUserRepository;
import org.eqdev.server.repository.CardRepository;
import org.eqdev.server.repository.UserInventoryRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserInventoryService {
    private final AppUserRepository userRepository;
    private final CardRepository cardRepository;
    private final UserInventoryRepository inventoryRepository;

    public UserInventoryService(AppUserRepository userRepository, CardRepository cardRepository, UserInventoryRepository inventoryRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.inventoryRepository = inventoryRepository;
    }
    
    @Transactional
    public UserInventory addCardToInventory(String username, Long cardId, Integer quantity) {
        // Implementation to add a card to the user's inventory
        AppUser user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
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
