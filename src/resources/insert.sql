INSERT INTO CUSTOMER_CARDS(card_id, currentBalance, pin, isLocked) VALUES (1, 100, 1111, false);
INSERT INTO CUSTOMER_TRANSACTIONS(cardTransaction_id, cardTransactionCode, cardTransactionDate, amount, card_id) VALUES (1, '0', '2016-06-17', 0, 1);
INSERT INTO CUSTOMER_TRANSACTIONS(cardTransaction_id, cardTransactionCode, cardTransactionDate, amount, card_id) VALUES (2, '1', '2016-06-16', 10, 1);
INSERT INTO CUSTOMER_TRANSACTIONS(cardTransaction_id, cardTransactionCode, cardTransactionDate, amount, card_id) VALUES (3, '1', '2016-06-15', 10, 1);
INSERT INTO CUSTOMER(customer_id, customerName, card_id) VALUES (1, 'User1', 1);