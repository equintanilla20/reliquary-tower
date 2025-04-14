export const GET_CARD_BY_NAME = `
    query GetCardByName($cardName: String!) {
        getCardByName(cardName: $cardName) {
            cardName
            cardRarity
            cardType
            cmc
            cardColors
            cardColorIdentity
            cardSet
            cardSetName
            cardText
            cardArtist
            cardImageUrlSmall
            cardImageUrlNormal
            cardImageUrlLarge
            cardImageUrlPng
            cardImageUrlArtCrop
            cardImageUrlBorderCrop
        }
    }
`;