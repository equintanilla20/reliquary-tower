package org.eqdev.server.repository;

import java.util.List;
import java.util.Optional;

import org.eqdev.server.model.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardRepository extends JpaRepository<Card, Long>, JpaSpecificationExecutor<Card> {
    List<Card> findByCardNameContainingIgnoreCase(String cardName, Pageable pageable);
    Optional<Card> findCardByCardId(Long cardId);

}
