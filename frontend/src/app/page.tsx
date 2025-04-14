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
    const cardName = "Season of Weaving";
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
                <Image
                    className="dark:invert"
                    src="/next.svg"
                    alt="Next.js logo"
                    width={180}
                    height={38}
                    priority
                />
                <ol className="list-inside list-decimal text-sm/6 text-center sm:text-left font-[family-name:var(--font-geist-mono)]">
                    <li className="mb-2 tracking-[-.01em]">
                        Get started by editing{" "}
                        <code className="bg-black/[.05] dark:bg-white/[.06] px-1 py-0.5 rounded font-[family-name:var(--font-geist-mono)] font-semibold">
                        src/app/page.tsx
                        </code>
                        .
                    </li>
                    <li className="tracking-[-.01em]">
                        Save and see your changes instantly.
                    </li>
                </ol>

                <div className="flex gap-4 items-center flex-col sm:flex-row">
                    <a
                        className="rounded-full border border-solid border-transparent transition-colors flex items-center justify-center bg-foreground text-background gap-2 hover:bg-[#383838] dark:hover:bg-[#ccc] font-medium text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5 sm:w-auto"
                        href="https://vercel.com/new?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        <Image
                            className="dark:invert"
                            src="/vercel.svg"
                            alt="Vercel logomark"
                            width={20}
                            height={20}
                        />
                        Deploy now
                    </a>
                    <a
                        className="rounded-full border border-solid border-black/[.08] dark:border-white/[.145] transition-colors flex items-center justify-center hover:bg-[#f2f2f2] dark:hover:bg-[#1a1a1a] hover:border-transparent font-medium text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5 w-full sm:w-auto md:w-[158px]"
                        href="https://nextjs.org/docs?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        Read our docs
                    </a>
                </div>
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
            <footer className="row-start-3 flex gap-[24px] flex-wrap items-center justify-center">
                <a
                className="flex items-center gap-2 hover:underline hover:underline-offset-4"
                href="https://nextjs.org/learn?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
                target="_blank"
                rel="noopener noreferrer"
                >
                <Image
                    aria-hidden
                    src="/file.svg"
                    alt="File icon"
                    width={16}
                    height={16}
                />
                Learn
                </a>
                <a
                className="flex items-center gap-2 hover:underline hover:underline-offset-4"
                href="https://vercel.com/templates?framework=next.js&utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
                target="_blank"
                rel="noopener noreferrer"
                >
                <Image
                    aria-hidden
                    src="/window.svg"
                    alt="Window icon"
                    width={16}
                    height={16}
                />
                Examples
                </a>
                <a
                className="flex items-center gap-2 hover:underline hover:underline-offset-4"
                href="https://nextjs.org?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
                target="_blank"
                rel="noopener noreferrer"
                >
                <Image
                    aria-hidden
                    src="/globe.svg"
                    alt="Globe icon"
                    width={16}
                    height={16}
                />
                Go to nextjs.org →
                </a>
            </footer>
        </div>
    );
}
