package org.eqdev.server.resolver;

import org.eqdev.model.Card;
import org.eqdev.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.graphql.resolvers.query.GraphQLQueryResolver;
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

    public Iterable<Card> findAllCards() {
        return cardRepository.findAll();
    }

    public long countCards() {
        return cardRepository.count();
    }
}