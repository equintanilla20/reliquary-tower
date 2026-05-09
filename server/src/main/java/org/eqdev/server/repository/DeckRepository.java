package org.eqdev.server.repository;

import java.util.List;

import org.eqdev.server.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import graphql.com.google.common.base.Optional;

public interface DeckRepository extends JpaRepository<Deck, Long>, JpaSpecificationExecutor<Deck> {
    List<Deck> findByUserUsername(String username);
    Optional<Deck> findByDeckIdAndUserUsername(Long deckId, String username);
    
}
