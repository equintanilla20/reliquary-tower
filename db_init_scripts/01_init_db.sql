
CREATE TABLE "user" (
  username VARCHAR(32) NOT NULL PRIMARY KEY,
  password VARCHAR(32) NOT NULL
);

-- Basic MTG Card Table
CREATE TABLE Card(
    id BIGSERIAL PRIMARY KEY,
    card_name TEXT NOT NULL, 
    card_rarity TEXT, 
    card_type TEXT, 
    cmc FLOAT, 
    card_colors TEXT, 
    card_color_identity TEXT, 
    card_set TEXT, 
    card_set_name TEXT, 
    card_text TEXT, 
    card_artist TEXT, 
    card_image_uri_small TEXT, 
    card_image_uri_normal TEXT,
    card_image_uri_large TEXT, 
    card_image_uri_png TEXT, 
    card_image_uri_art_crop TEXT, 
    card_image_uri_border_crop TEXT
);