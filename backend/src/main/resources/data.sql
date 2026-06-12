-- Initial data for account table
-- Passwords are Bcrypt hashes of the password 'pizza'.
INSERT INTO account (first_name, last_name, username, password_hash, email, role) VALUES
  ('Admin', 'User', 'admin', '$2y$05$1zMf9eQgDCWqewuTi0J6ze325QbprMBdmvROTwJ8dXqseRJy6hKwu', 'admin@example.com', 'USER'),
  ('Anna', 'Müller', 'anna', '$2y$05$1zMf9eQgDCWqewuTi0J6ze325QbprMBdmvROTwJ8dXqseRJy6hKwu', 'anna@example.com', 'USER'),
  ('James', 'Bond', 'james', '$2y$05$1zMf9eQgDCWqewuTi0J6ze325QbprMBdmvROTwJ8dXqseRJy6hKwu', 'james@example.com', 'USER');

