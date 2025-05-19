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

    const [inputValue, setInputValue] = useState("");
    const [cardName, setCardName] = useState("");
    const [cardData, setCardData] = useState<Card[] | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (cardName) {
            setError(null);
            setCardData(null);
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
                }
            );
        }
    }, [cardName]);

    const handleSearch = (e: React.FormEvent) => {
        e.preventDefault();
        setCardName(inputValue.trim());
    }

    return (
        <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
            <main className="flex flex-col gap-[32px] row-start-2 items-center sm:items-start">
                <form onSubmit={handleSearch} className="flex gap-2 mb-4">
                    <input
                        type="text"
                        value={inputValue}
                        onChange={e => setInputValue(e.target.value)}
                        placeholder="Enter card name"
                        className="border px-2 py-1 rounded"
                    />
                    <button type="submit" className="bg-blue-500 text-white px-4 py-1 rounded">
                        Search
                    </button>
                </form>
                {error && <p>Error: {error}</p>}
                {cardData && cardData.length > 0 && ( // Check if cardData has elements.
                    <div>
                        <h1>{cardData[0].cardName}</h1>
                        <Image
                            src={cardData[0].cardImageUrlPng}
                            alt={cardData[0].cardName}
                            width={300}
                            height={300}
                        />
                        <div className="p-4">
                            <p style={{ whiteSpace: 'pre-wrap' }} >
                                Text: {cardData[0].cardText.split('\n')}
                            </p>
                        </div>
                    </div>
                )}
            </main>
        </div>
    );
};
