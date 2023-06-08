INSERT INTO cards (id, balance, name)
VALUES (1,100,'a'),
       (2,200,'b'),
       (3,300,'c');

INSERT INTO transactions (id, amount, type, card_id)
VALUES (1,100,'INCOME',1),
       (2,200,'INCOME',2),
       (3,300,'INCOME',3);