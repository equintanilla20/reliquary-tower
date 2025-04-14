export const GET_CARD_BY_NAME = `
    query {
        getCardByName($cardName: String!) {
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