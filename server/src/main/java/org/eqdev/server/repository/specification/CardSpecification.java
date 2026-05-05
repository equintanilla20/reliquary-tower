package org.eqdev.server.repository.specification;

import org.eqdev.server.model.Card;
import org.springframework.data.jpa.domain.Specification;

public class CardSpecification {

    public static Specification<Card> hasCardName(String cardName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(criteriaBuilder.lower(root.get("cardName")), "%" + cardName.toLowerCase() + "%");
    }

    public static Specification<Card> hasCmc(Double cmc) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("cmc"), cmc);
    }

    public static Specification<Card> hasCardRarity(String rarity) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(criteriaBuilder.lower(root.get("cardRarity")), rarity.toLowerCase());
    }

    public static Specification<Card> hasCardType(String type) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(criteriaBuilder.lower(root.get("cardType")), type.toLowerCase());
    }

    public static Specification<Card> hasCardColors(String color) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(criteriaBuilder.lower(root.get("cardColors")), "%" + color.toLowerCase() + "%");
    }

    public static Specification<Card> hasCardSet(String set) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(criteriaBuilder.lower(root.get("cardSet")), set.toLowerCase());
    }

    public static Specification<Card> hasCardKeywords(String keywords) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(criteriaBuilder.lower(root.get("cardKeywords")), "%" + keywords.toLowerCase() + "%");
    }

    public static Specification<Card> isLegalIn(String format, String status) {
        return (root, query, criteriaBuilder) -> {
            if (format == null || status == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(
                criteriaBuilder.function("jsonb_extract_path_text", String.class,
                    root.get("legalities"), criteriaBuilder.literal(format)),
                status
            );
        };
    }
}
