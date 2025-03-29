-- Rarity Enum
CREATE TYPE rarity AS ENUM ('common', 'uncommon', 'rare', 'mythic');

-- Set Enum
CREATE TYPE set AS ENUM ('alpha', 'beta', 'unlimited', 'modern horizons 3');

CREATE TABLE "user" (
  username VARCHAR(32) NOT NULL PRIMARY KEY,
  password VARCHAR(32) NOT NULL
);

-- Basic MTG Card Table
CREATE TABLE Card(
    card_id SERIAL PRIMARY KEY,
    card_name VARCHAR(64) NOT NULL,
    card_rarity VARCHAR(8) NOT NULL,
    card_set VARCHAR(64) NOT NULL,
    card_text TEXT NOT NULL,
    card_image_url TEXT NOT NULL
);