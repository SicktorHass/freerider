# freerider
bht

F1. 
  a) Eine Transaktion ist eine Kette von Datenbankoperationen, die die Datenbank von einem Zustand in einen anderen (konsistent) überführt oder den Zustand              unverändert belässt.
  b) Atomarität, Konsistenz, Isolation, Dauerhaftigkeit. Bewirkt, dass die relationale Datenbank widerspruchsfreie Zustände erzeugt und mit konkurrierenden Anfragen      umgehen kann.
  c) Das Schema der Datenbank beschreibt die darin enthaltenen Tabellen mit ihren verwalteten Datentypen.
  d) relational = MySQl, postgresql, Oracle‐DB ; dokumentbasiert= mongoDB, amazon DynamoDB, Aerospike
  e) SELECT * FROM CUSTOMER WHERE ID = 4
  f) CREATE TABLE CUSTOMER (
      ID long NOT NULL,
      NAME varchar(255) NOT NULL,
      CONTACT varchar(255),
      LAST_LOGIN timestamp,
      PRIMARY KEY (ID)
      );
  g) INSERT INTO CUSTOMER VALUES (...)
  h) DELETE FROM CUSTOMER WHERE ID = 4
  
