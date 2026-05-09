CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Basic MTG Card Table
CREATE TABLE Card(
    card_id BIGSERIAL PRIMARY KEY,
    card_name TEXT NOT NULL, 
    card_rarity TEXT, 
    card_type TEXT, 
    cmc FLOAT, 
    card_colors TEXT, 
    card_color_identity TEXT, 
    card_set TEXT, 
    card_set_name TEXT,
    card_keywords TEXT, 
    card_text TEXT,
    legalities JSONB, 
    card_artist TEXT, 
    card_image_uri_small TEXT, 
    card_image_uri_normal TEXT,
    card_image_uri_large TEXT, 
    card_image_uri_png TEXT, 
    card_image_uri_art_crop TEXT, 
    card_image_uri_border_crop TEXT
);

CREATE INDEX idx_card_name ON Card(card_name);
CREATE INDEX idx_card_type ON Card(card_type);
CREATE INDEX idx_card_rarity ON Card(card_rarity);
CREATE INDEX idx_card_colors ON Card(card_colors);
CREATE INDEX idx_card_set ON Card(card_set);

-- Deck Table
CREATE TABLE Deck(
    deck_id BIGSERIAL PRIMARY KEY,
    deck_name TEXT NOT NULL,
    user_id BIGSERIAL REFERENCES app_user(id),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Join Table for Decks and Cards
CREATE TABLE Deck_Card(
    deck_id BIGSERIAL REFERENCES Deck(deck_id) ON DELETE CASCADE,
    card_id BIGSERIAL REFERENCES Card(card_id),
    quantity INT DEFAULT 1,
    PRIMARY KEY (deck_id, card_id)
);

-- User Personal Collection Table
CREATE TABLE User_Collection(
    user_id BIGSERIAL REFERENCES app_user(id),
    card_id BIGSERIAL REFERENCES Card(card_id),
    quantity INT DEFAULT 1,
    PRIMARY KEY (user_id, card_id)
);
