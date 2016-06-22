--CREATE SCHEMA
DROP TABLE IF EXISTS CUSTOMER_CARDS CASCADE;
CREATE TABLE CUSTOMER_CARDS (
  card_id bigint,
  currentBalance DECIMAL(30, 2),
  pin SMALLINT,
  isLocked BOOLEAN,
  customer_id bigint,
  PRIMARY KEY (card_id)
);

DROP TABLE IF EXISTS CARD_TRANSACTIONS CASCADE;
CREATE TABLE CARD_TRANSACTIONS (
  cardTransaction_id bigint auto_increment,
  cardTransactionCode VARCHAR(1),
  cardTransactionDate DATE,
  amount DECIMAL(30, 2),
  card_id bigint,
  PRIMARY KEY (cardTransaction_id)
);

DROP TABLE IF EXISTS CUSTOMER CASCADE;
CREATE TABLE CUSTOMER (
  customer_id bigint auto_increment,
  customerName VARCHAR(30),
  PRIMARY KEY (customer_id)
);
