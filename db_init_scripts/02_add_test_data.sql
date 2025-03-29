INSERT INTO "user" (username, password) 
VALUES 
('admin', 'admin');

INSERT INTO Card(card_name, card_rarity, card_set, card_text, card_image_url)
VALUES 
(
    'Black Lotus', 
    'mythic', 
    'alpha', 
    'Add three mana of any one color.', 
    'https://www.mtgpics.com/pics/big/bet/018.jpg'
),
(
    'Tamiyo, Inquisitive Student / Tamiyo, Seasoned Scholar', 
    'mythic', 
    'modern horizons 3', 
    'Flying\nWhenever Tamiyo, Inquisitive Studentattacks, investigate.\nWhen you draw your third card in a turn, exile Tamiyo, then return her to the battlefield transformed under her owner''s control.\n/\n+2: Until your next turn, whenever a creature attacks you or a planeswalker you control, it gets -1/0 until end of turn.\n-3: Return target instant or sorcery card from your graveyard to your hand. If it''s a greed card, add one mana of any color.\n-7: Draw cards equal to half the numer of cards in your library, rounded up. You get an emblem with "You have no maximum hand size."', 
    'https://www.mtgpics.com/pics/big/mh3/242.jpg'
);