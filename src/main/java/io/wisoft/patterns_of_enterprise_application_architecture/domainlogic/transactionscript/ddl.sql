CREATE TABLE products
(
    id   int primary key,
    name varchar,
    type varchar
);

CREATE TABLE contracts
(
    id         int primary key,
    product    int,
    revenue    decimal,
    dateSigned date
);

CREATE TABLE revenueRecognitions
(
    contract     int,
    amount       decimal,
    recognizedOn date,
    PRIMARY KEY (contract, recognizedOn)
)