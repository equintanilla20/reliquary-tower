package org.eqdev.resolver;

import org.eqdev.model.Card;
import org.eqdev.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {
    private CardRepository cardRepository;

    @Autowired
    public Mutation(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(String card_name, String card_rarity, String card_set, String card_text, String card_image_url) {
        Card newCard = new Card(card_name, card_rarity, card_set, card_text, card_image_url);
        cardRepository.save(newCard);
        return newCard;
    }

    public boolean deleteCard(Long id) {
        cardRepository.deleteById(id);
        return true;
    }
}