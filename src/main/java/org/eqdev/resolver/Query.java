package org.eqdev.resolver;

import org.eqdev.card.CardRepository;
import org.eqdev.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Component
public class Query implements GraphQLQueryResolver {
    private CardRepository cardRepository;

    GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
                                    .aliasedScalar(ExtendedScalars.GraphQLLong)
                                    .build();

    @Autowired
    public Query(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Iterable<Card> getCards() {
        return cardRepository.findAll();
    }
}
