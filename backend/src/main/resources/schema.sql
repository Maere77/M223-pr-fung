CREATE TABLE account
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name     VARCHAR(255),
    last_name      VARCHAR(255),
    username      VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL,
    email         VARCHAR(255) UNIQUE,
    login_token   VARCHAR(255) DEFAULT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_account_username ON account(username);
CREATE INDEX IF NOT EXISTS idx_account_email ON account(email);
