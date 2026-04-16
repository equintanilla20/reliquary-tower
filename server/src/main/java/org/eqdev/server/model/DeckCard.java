package org.eqdev.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "DeckCard")
public class DeckCard {
    /*
    CREATE TABLE Deck_Card(
        deck_id INT REFERENCES Deck(id) ON DELETE CASCADE,
        card_id BIGINT REFERENCES Card(id),
        quantity INT DEFAULT 1,
        PRIMARY KEY (deck_id, card_id)
    );
    */

    @EmbeddedId
    private DeckCardId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("deckId")
    private Deck deck;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cardId")
    private Card card;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
