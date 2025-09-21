CREATE TABLE IF NOT EXISTS book (
    id BIGSERIAL PRIMARY KEY,
    isbn VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS borrower (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS borrow_record (
    id BIGSERIAL PRIMARY KEY,
    borrower_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    CONSTRAINT fk_borrower FOREIGN KEY (borrower_id) REFERENCES borrower (id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book (id)
    );

INSERT INTO book (isbn, title, author) VALUES
                                           ('978-0134685991', 'Effective Java', 'Joshua Bloch'),
                                           ('978-0596009205', 'Head First Java', 'Kathy Sierra'),
                                           ('978-1617294945', 'Spring in Action', 'Craig Walls');