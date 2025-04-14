package org.eqdev.server.repository;

import java.util.List;
import org.eqdev.server.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCardName(String cardName);
    List<Card> findByCardSet(String cardSet);
    List<Card> findByCardRarity(String cardRarity);
    List<Card> findByCardRarityAndCardSet(String cardRarity, String cardSet);

}