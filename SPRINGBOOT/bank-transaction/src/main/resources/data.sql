CREATE TABLE ACCOUNT (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    ACCOUNT_NUMBER VARCHAR(255),
    BALANCE DECIMAL(10, 2)
);

INSERT INTO ACCOUNT (
    ACCOUNT_NUMBER,
    BALANCE
) VALUES (
    '123456',
    1000.00
);

INSERT INTO ACCOUNT (
    ACCOUNT_NUMBER,
    BALANCE
) VALUES (
    '654321',
    500.00
);