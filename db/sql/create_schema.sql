CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255),
    identity_doc VARCHAR(255),
    is_lock BOOLEAN NOT NULL,
    name VARCHAR(255),
    password VARCHAR(255),
    phone VARCHAR(255),
    reg_date TIMESTAMP(6),
    username VARCHAR(255)
);
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);
CREATE TABLE account (
    account_number VARCHAR(255) PRIMARY KEY,
    alias VARCHAR(255),
    balance NUMERIC(38, 2),
    cbu VARCHAR(255),
    user_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE transaction (
    operation_number VARCHAR(255) PRIMARY KEY,
    amount NUMERIC(38, 2),
    date TIMESTAMP(6),
    description VARCHAR(255),
    destination_account VARCHAR(255),
    notification VARCHAR(255),
    source_account VARCHAR(255),
    CONSTRAINT fk_transaction_account FOREIGN KEY (source_account) REFERENCES account (account_number)
);