CREATE TABLE USER
(
    id            BIGINT       NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    email         VARCHAR(50)  NOT NULL,
    username      VARCHAR(30)  NOT NULL,
    password      VARCHAR(60)  NOT NULL,
    creation_date DATETIME(6)  NOT NULL,
    enabled       BIT          NOT NULL,
    locked        BIT          NOT NULL,

    CONSTRAINT UNIQUE_USER_USERNAME_EMAIL UNIQUE (username, email)
);

CREATE TABLE ADDRESS
(
    id               BIGINT      NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT      NOT NULL,
    country          VARCHAR(20) NOT NULL,
    province         VARCHAR(20) NOT NULL,
    location         VARCHAR(30) NOT NULL,
    zip_code         VARCHAR(6)  NOT NULL,
    street           VARCHAR(25) NOT NULL,
    apartment_number VARCHAR(10) NOT NULL,
    delivery_address BIT         NOT NULL,

    CONSTRAINT FOREIGN_KEY_ADDRESS_USER FOREIGN KEY (user_id) REFERENCES USER (id)
);

CREATE TABLE PERSON
(
    user_id       BIGINT      NOT NULL
        PRIMARY KEY,
    name          VARCHAR(15) NOT NULL,
    surname       VARCHAR(20) NOT NULL,
    phone_number  VARCHAR(9)  NOT NULL,
    date_of_birth DATE        NOT NULL,

    CONSTRAINT UNIQUE_PERSON_PHONE_NUMBER UNIQUE (phone_number),
    CONSTRAINT FOREIGN_KEY_PERSON_USER FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE
);

CREATE TABLE TOKEN
(
    user_id BIGINT      NOT NULL
        PRIMARY KEY,
    value   VARCHAR(40) NOT NULL,

    CONSTRAINT UNIQUE_VALUE UNIQUE (value),
    CONSTRAINT FOREIGN_KEY_TOKEN_USER FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE
);

CREATE TABLE AUTHORITY
(
    id             BIGINT      NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    authority_type VARCHAR(15) NOT NULL,

    CONSTRAINT UNIQUE_AUTHORITY_AUTHORITY_TYPE UNIQUE (authority_type)
);

CREATE TABLE USER_AUTHORITY
(
    user_id      BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, authority_id),
    CONSTRAINT FOREIGN_KEY_USER_AUTHORITY_USER FOREIGN KEY (user_id) REFERENCES USER (id),
    CONSTRAINT FOREIGN_KEY_USER_AUTHORITY_AUTHORITY FOREIGN KEY (authority_id) REFERENCES AUTHORITY (id)
);

CREATE TABLE CATEGORY
(
    id        BIGINT      NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT      NULL,
    name      VARCHAR(30) NOT NULL,

    CONSTRAINT UNIQUE_CATEGORY_NAME UNIQUE (name),
    CONSTRAINT FOREIGN_KEY_CATEGORY_PARENT FOREIGN KEY (parent_id) REFERENCES CATEGORY (id) ON DELETE CASCADE
);

CREATE TABLE PROMOTION
(
    id            BIGINT      NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(30) NOT NULL,
    percent_value TINYINT     NOT NULL
        CHECK (percent_value BETWEEN 0 AND 100),
    start_date    DATE        NOT NULL,
    end_date      DATE        NOT NULL,

    CONSTRAINT UNIQUE_PROMOTION_NAME UNIQUE (name)
);

CREATE TABLE PRODUCT
(
    id          BIGINT         NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(60)    NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    model       VARCHAR(20)    NOT NULL,
    brand       VARCHAR(35)    NOT NULL,
    description VARCHAR(1000)  NOT NULL
);

CREATE TABLE PRODUCT_CATEGORY
(
    product_id  BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (product_id, category_id),
    CONSTRAINT FOREIGN_KEY_PRODUCT_CATEGORY_PRODUCT FOREIGN KEY (product_id) REFERENCES PRODUCT (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN_KEY_PRODUCT_CATEGORY_CATEGORY FOREIGN KEY (category_id) REFERENCES CATEGORY (id) ON DELETE CASCADE
);

CREATE TABLE PRODUCT_PROMOTION
(
    product_id   BIGINT NOT NULL,
    promotion_id BIGINT NOT NULL,

    PRIMARY KEY (product_id, promotion_id),
    CONSTRAINT FOREIGN_KEY_PRODUCT_PROMOTION_PRODUCT FOREIGN KEY (product_id) REFERENCES PRODUCT (id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN_KEY_PRODUCT_PROMOTION_PROMOTION FOREIGN KEY (promotion_id) REFERENCES PROMOTION (id) ON DELETE CASCADE
);

CREATE TABLE IMAGE
(
    id         BIGINT      NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT      NOT NULL,
    name       VARCHAR(30) NOT NULL,
    type       VARCHAR(50) NOT NULL,
    pic_byte   LONGBLOB    NOT NULL,

    CONSTRAINT FOREIGN_KEY_IMAGE_PRODUCT FOREIGN KEY (product_id) REFERENCES PRODUCT (id) ON DELETE CASCADE
);

CREATE TABLE REVIEW
(
    id             BIGINT       NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    user_id        BIGINT       NOT NULL,
    product_id     BIGINT       NOT NULL,
    plus_rate      SMALLINT     NOT NULL,
    minus_rate     SMALLINT     NOT NULL,
    review_comment VARCHAR(255) NOT NULL,
    creation_date  DATETIME(6)  NOT NULL,

    CONSTRAINT FOREIGN_KEY_REVIEW_USER FOREIGN KEY (user_id) REFERENCES USER (id),
    CONSTRAINT FOREIGN_KEY_REVIEW_PRODUCT FOREIGN KEY (product_id) REFERENCES PRODUCT (id) ON DELETE CASCADE
);

CREATE TABLE OFFER
(
    product_id    BIGINT      NOT NULL
        PRIMARY KEY,
    quantity      INT         NOT NULL,
    creation_date DATETIME(6) NOT NULL,
    end_date      DATE        NOT NULL,

    CONSTRAINT FOREIGN_KEY_OFFER_PRODUCT FOREIGN KEY (product_id) REFERENCES PRODUCT (id) ON DELETE CASCADE
);

CREATE TABLE CART
(
    user_id     BIGINT         NOT NULL
        PRIMARY KEY,
    capacity    INT            NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,

    CONSTRAINT FOREIGN_KEY_CART_PRODUCT FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE
);

CREATE TABLE PURCHASE
(
    id              BIGINT NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    cart_id         BIGINT NOT NULL,
    offer_id        BIGINT NOT NULL,
    products_amount INT    NOT NULL,

    CONSTRAINT FOREIGN_KEY_PURCHASE_CART FOREIGN KEY (cart_id) REFERENCES CART (user_id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN_KEY_PURCHASE_OFFER FOREIGN KEY (offer_id) REFERENCES OFFER (product_id) ON DELETE CASCADE
);

CREATE TABLE TRANSACTION
(
    id                          BIGINT         NOT NULL
        AUTO_INCREMENT PRIMARY KEY,
    user_id                     BIGINT         NOT NULL,
    total_price                 DECIMAL(10, 2) NOT NULL,
    transaction_date            DATETIME(6)    NOT NULL,
    transaction_products_amount INT            NOT NULL,

    CONSTRAINT FOREIGN_KEY_TRANSACTION_USER FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE
);

CREATE TABLE TRANSACTION_PRODUCT
(
    transaction_id BIGINT NOT NULL,
    product_id     BIGINT NOT NULL,

    PRIMARY KEY (transaction_id, product_id),
    CONSTRAINT FOREIGN_KEY_TRANSACTION_PRODUCT_TRANSACTION FOREIGN KEY (transaction_id) REFERENCES TRANSACTION (id),
    CONSTRAINT FOREIGN_KEY_TRANSACTION_PRODUCT_PRODUCT FOREIGN KEY (product_id) REFERENCES PRODUCT (id) ON DELETE CASCADE
);