INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('doaa@kimchiinc.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('thekimchster@kimchiinc.com', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 

 
INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (1, 2);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

INSERT INTO userpubpost (email, userPost, sub_date, sub_time)
VALUES ('thekimchster@kimchiinc.com', 'Hi everyone, just a reminder that we have to create the next app storyboard collection by Tuesday this coming week... I hope someone here knows how to use Adobe XD.', '2020-11-02', '10:03'),
('fatimakhaled@kimchiinc.com', 'I am not really feeling the colour choices that were proposed in the meeting today, salmon and olive green are not super "in" right now... might I suggest something a little different? Blue x white is a popular colour choice!', '2020-11-03', '11:43'),
('thekimchster@kimchiinc.com', 'Fatima I have no idea why anyone suggested salmon and olive green in the first place! What a disaster!', '2020-11-03', '11:45'),
('doaa@kimchiinc.com', 'The client is super unhappy with the login screen being the first thing they see when they open the app... they want to see intro screens every time they open it, can we ignore that request or do we have to comply? It seems so ridiculous.', '2020-11-23', '09:15');

