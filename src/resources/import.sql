INSERT INTO CUSTOMER(customer_id, customerName) VALUES (1, 'User1');
INSERT INTO CUSTOMER_CARDS(card_id, currentBalance, pin, isLocked, customer_id) VALUES (1, 100, 1111, false, 1);

INSERT INTO CUSTOMER_TRANSACTIONS(cardTransaction_id, cardTransactionCode, cardTransactionDate, amount, card_id) VALUES (1, '0', '2016-06-17', 10, null);

