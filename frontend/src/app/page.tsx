'use client'

import Image from "next/image";
// import { useRouter } from "next/navigation";
import { request } from "./lib/graphqlClient";
import { useEffect, useState } from "react";
import { GET_CARD_BY_NAME } from "./lib/queries";

type Card = {
    cardName: string;
    cardRarity: string;
    cardType: string;
    cmc: number;
    cardColors: string;
    cardColorIdentity: string;
    cardSet: string;
    cardSetName: string;
    cardText: string;
    cardArtist: string;
    cardImageUrlSmall: string;
    cardImageUrlNormal: string;
    cardImageUrlLarge: string;
    cardImageUrlPng: string;
    cardImageUrlArtCrop: string;
    cardImageUrlBorderCrop: string;
}

export default function Home() {

    // const router = useRouter();
    // const { cardName } = router.query;
    const cardName = "Into the Flood Maw"; // Replace with the actual card name you want to search for
    const [cardData, setCardData] = useState<Card[] | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (cardName) {
            setLoading(true);
            request<{ getCardByName: Card[] }>(GET_CARD_BY_NAME, { cardName })
                .then((data) => {
                    if (data.getCardByName.length > 0) {
                        setCardData(data.getCardByName);
                    } else {
                        setError("Card not found");
                    }
                })
                .catch((err) => {
                    console.error(err);
                    setError("An error occurred while fetching the card data.");
                })
                .finally(() => {
                    setLoading(false);
                }
            );
        }
    }, [cardName]);

    if (loading) { return <p>Loading...</p>; }
    if (error) { return <p>Error: {error}</p>; }
    if (!cardData) { return <p>Card not found.</p>; }

    return (
        <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
            <main className="flex flex-col gap-[32px] row-start-2 items-center sm:items-start">
                <div>
                    <h1>{cardData[0].cardName}</h1>
                    <Image 
                        src={cardData[0].cardImageUrlPng} 
                        alt={cardData[0].cardName} 
                        width={300} 
                        height={300} 
                    />
                    <p>Text: {cardData[0].cardText}</p>
                </div>
            </main>
        </div>
    );
};
