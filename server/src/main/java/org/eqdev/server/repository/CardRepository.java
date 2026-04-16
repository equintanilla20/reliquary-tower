package org.eqdev.server.repository;

import java.util.List;
import java.util.Optional;

import org.eqdev.server.model.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Optional<Card> findById(Long id);
}
