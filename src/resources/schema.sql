--CREATE SCHEMA
DROP TABLE IF EXISTS CUSTOMER_CARDS CASCADE;
CREATE TABLE CUSTOMER_CARDS (
  card_id bigint auto_increment,
  currentBalance DECIMAL(30, 2),
  pin SMALLINT,
  isLocked BOOLEAN,
  PRIMARY KEY (card_id)
);

DROP TABLE IF EXISTS CUSTOMER_TRANSACTIONS CASCADE;
CREATE TABLE CUSTOMER_TRANSACTIONS (
  cardTransaction_id bigint auto_increment,
  cardTransactionCode VARCHAR(20),
  cardTransactionDate DATE,
  amount DECIMAL(30, 2),
  card_id bigint,
  FOREIGN KEY (card_id) REFERENCES CUSTOMER_CARDS(card_id),
  PRIMARY KEY (cardTransaction_id)
);

DROP TABLE IF EXISTS CUSTOMER CASCADE;
CREATE TABLE CUSTOMER (
  customer_id bigint auto_increment,
  customerName VARCHAR(30),
  card_id bigint,
  FOREIGN KEY (card_id) REFERENCES CUSTOMER_CARDS(card_id),
  PRIMARY KEY (customer_id)
);