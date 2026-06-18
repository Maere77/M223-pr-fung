-- Initial data for account table
-- Passwords are Bcrypt hashes of the password 'pizza'.
INSERT INTO account (first_name, last_name, username, password_hash, email, role) VALUES
  ('Admin', 'User', 'admin', '$2y$05$1zMf9eQgDCWqewuTi0J6ze325QbprMBdmvROTwJ8dXqseRJy6hKwu', 'admin@example.com', 'USER'),
  ('Anna', 'Müller', 'anna', '$2y$05$1zMf9eQgDCWqewuTi0J6ze325QbprMBdmvROTwJ8dXqseRJy6hKwu', 'anna@example.com', 'USER'),
  ('James', 'Bond', 'james', '$2y$05$1zMf9eQgDCWqewuTi0J6ze325QbprMBdmvROTwJ8dXqseRJy6hKwu', 'james@example.com', 'USER');

INSERT INTO groups (id, name, owner_id)
VALUES
    (1, 'Family', 1),
    (2, 'Friends', 2);

INSERT INTO media (id, media, group_id)
VALUES
    (1, 'family_photo.jpg', 1),
    (2, 'vacation_video.mp4', 1),
    (3, 'party_photo.png', 2);

INSERT INTO members_groups (group_id, member_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2),
    (2, 3);