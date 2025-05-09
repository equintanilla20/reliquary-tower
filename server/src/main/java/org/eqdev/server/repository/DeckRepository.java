package org.eqdev.server.repository;

import java.util.List;
import org.eqdev.server.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long>{
    List<Deck> findByDeckName(String deckName);
    List<Deck> findByCardIdsContains(Long cardId);
    List<Deck> findByCardIdsContainsAndDeckName(Long cardId, String deckName);
    List<Deck> findByDeckNameUserId(String deckName, Long userId);
    List<Deck> findByUserID(Long userId);
}
