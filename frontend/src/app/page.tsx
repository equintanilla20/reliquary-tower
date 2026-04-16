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
    const [cardText, setCardText] = useState("");
    const [cardData, setCardData] = useState<Card[] | null>(null);
    const [searchTrigger, setSearchTrigger] = useState(0);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (cardName) {
            setError(null);
            setCardData(null);
            request<{ getCardByName: Card[] }>(GET_CARD_BY_NAME, { cardName })
                .then((data) => {
                    if (data.getCardByName.length > 0) {
                        setCardData(data.getCardByName);
                        const receivedCardText = data.getCardByName[0].cardText;
                        console.log('Original card text from API:', receivedCardText);
                        console.log('Does it contain \\n (literal backslash n)?', receivedCardText.includes('\\n'));
                        console.log('Does it contain a true newline character?', receivedCardText.includes('\n')); // This checks for the actual newline char
                        setCardText(data.getCardByName[0].cardText.replace(/\\n/g, '\n\n'));
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
    }, [searchTrigger, cardName]);

    const handleSearch = (e: React.FormEvent) => {
        e.preventDefault();
        const trimmedInputValue = inputValue.trim();

        // Only proceed if the input value is not empty
        if (!trimmedInputValue) {
            setError("Please enter a card name.");
            setCardData(null); // Clear previous card data if input is empty
            setCardText("");   // Clear previous card text
            return;
        }

        if (trimmedInputValue !== cardName) {
            setCardName(trimmedInputValue);
            setSearchTrigger(prev => prev + 1); // Also increment to ensure first fetch on new name
        }
    }

    return (
        <div className="flex flex-col items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
            {/* Removed row-start-2 from main, added w-full for centering its children */}
            <main className="flex flex-col items-center w-full">
                {/* Centered the form using mx-auto and ensured it doesn't take full width */}
                <form onSubmit={handleSearch} className="flex gap-2 mb-4 mx-auto">
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
                {cardData && cardData.length > 0 && (
                    <div className="flex flex-col items-center" > {/* Added flex-col and items-center here */}
                        <h1 className="font-bold text-3xl" >{cardData[0].cardName}</h1>
                        <div className="flex flex-col md:flex-row items-center md:items-start gap-4">
                            <Image
                                src={cardData[0].cardImageUrlPng}
                                alt={cardData[0].cardName}
                                width={300}
                                height={300}
                            />
                            <div className="p-4 max-w-sm"> {/* Added max-w-sm to constrain text width on larger screens */}
                                <p style={{ whiteSpace: 'pre-wrap' }} >
                                    {cardText}
                                </p>
                            </div>
                        </div>
                    </div>
                )}
            </main>
        </div>
    );
};
