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

-- Test data
INSERT INTO book (isbn, title, author) VALUES
('9780134685991', 'Effective Java', 'Joshua Bloch'),
('9781617294945', 'Spring in Action', 'Craig Walls'),
('9780596009205', 'Head First Java', 'Kathy Sierra');

INSERT INTO borrower (name, email) VALUES
('Alice Johnson', 'alice@example.com'),
('Bob Smith', 'bob@example.com');

-- Borrow records: link borrowers with books
INSERT INTO borrow_record (borrower_id, book_id, isbn) VALUES
(1, 1, '9780134685991'),  -- Alice borrowed Effective Java
(2, 2, '9781617294945');  -- Bob borrowed Spring in Action