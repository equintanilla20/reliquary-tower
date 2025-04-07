package org.eqdev.server.repository;

// import java.util.List;

import org.eqdev.server.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    // List<Card> findByCardSet(String set);
    // This interface will automatically provide CRUD operations for the Card entity.
    // You can add custom query methods here if needed.
}