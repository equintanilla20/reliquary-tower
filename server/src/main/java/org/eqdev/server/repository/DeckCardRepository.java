package org.eqdev.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.eqdev.server.model.DeckCard;
import org.eqdev.server.model.DeckCardId;

public interface DeckCardRepository extends JpaRepository<DeckCard, DeckCardId> {
    
}
