cat > src/main/resources/import.sql << 'EOF'
-- Sample polls for testing pagination

-- Poll 1
insert into poll (poll_id, question) values (1, 'What is your favorite color?');
insert into option (option_id, option_value, poll_id) values (1, 'Red', 1);
insert into option (option_id, option_value, poll_id) values (2, 'Blue', 1);
insert into option (option_id, option_value, poll_id) values (3, 'Green', 1);

-- Poll 2
insert into poll (poll_id, question) values (2, 'What is your favorite programming language?');
insert into option (option_id, option_value, poll_id) values (4, 'Java', 2);
insert into option (option_id, option_value, poll_id) values (5, 'Python', 2);
insert into option (option_id, option_value, poll_id) values (6, 'JavaScript', 2);

-- Poll 3
insert into poll (poll_id, question) values (3, 'What is your favorite season?');
insert into option (option_id, option_value, poll_id) values (7, 'Spring', 3);
insert into option (option_id, option_value, poll_id) values (8, 'Summer', 3);
insert into option (option_id, option_value, poll_id) values (9, 'Fall', 3);
insert into option (option_id, option_value, poll_id) values (10, 'Winter', 3);

-- Poll 4
insert into poll (poll_id, question) values (4, 'What is your favorite food?');
insert into option (option_id, option_value, poll_id) values (11, 'Pizza', 4);
insert into option (option_id, option_value, poll_id) values (12, 'Sushi', 4);
insert into option (option_id, option_value, poll_id) values (13, 'Tacos', 4);

-- Poll 5
insert into poll (poll_id, question) values (5, 'What is your favorite sport?');
insert into option (option_id, option_value, poll_id) values (14, 'Soccer', 5);
insert into option (option_id, option_value, poll_id) values (15, 'Basketball', 5);
insert into option (option_id, option_value, poll_id) values (16, 'Tennis', 5);

-- Poll 6
insert into poll (poll_id, question) values (6, 'What is your favorite movie genre?');
insert into option (option_id, option_value, poll_id) values (17, 'Action', 6);
insert into option (option_id, option_value, poll_id) values (18, 'Comedy', 6);
insert into option (option_id, option_value, poll_id) values (19, 'Drama', 6);
insert into option (option_id, option_value, poll_id) values (20, 'Sci-Fi', 6);

-- Poll 7
insert into poll (poll_id, question) values (7, 'What is your favorite music genre?');
insert into option (option_id, option_value, poll_id) values (21, 'Rock', 7);
insert into option (option_id, option_value, poll_id) values (22, 'Pop', 7);
insert into option (option_id, option_value, poll_id) values (23, 'Hip-Hop', 7);

-- Poll 8
insert into poll (poll_id, question) values (8, 'What is your favorite pet?');
insert into option (option_id, option_value, poll_id) values (24, 'Dog', 8);
insert into option (option_id, option_value, poll_id) values (25, 'Cat', 8);
insert into option (option_id, option_value, poll_id) values (26, 'Bird', 8);

-- Poll 9
insert into poll (poll_id, question) values (9, 'What is your favorite beverage?');
insert into option (option_id, option_value, poll_id) values (27, 'Coffee', 9);
insert into option (option_id, option_value, poll_id) values (28, 'Tea', 9);
insert into option (option_id, option_value, poll_id) values (29, 'Water', 9);

-- Poll 10
insert into poll (poll_id, question) values (10, 'What is your favorite day of the week?');
insert into option (option_id, option_value, poll_id) values (30, 'Monday', 10);
insert into option (option_id, option_value, poll_id) values (31, 'Friday', 10);
insert into option (option_id, option_value, poll_id) values (32, 'Saturday', 10);

-- Poll 11
insert into poll (poll_id, question) values (11, 'What is your favorite social media?');
insert into option (option_id, option_value, poll_id) values (33, 'Twitter', 11);
insert into option (option_id, option_value, poll_id) values (34, 'Instagram', 11);
insert into option (option_id, option_value, poll_id) values (35, 'TikTok', 11);

-- Poll 12
insert into poll (poll_id, question) values (12, 'What is your favorite car brand?');
insert into option (option_id, option_value, poll_id) values (36, 'Tesla', 12);
insert into option (option_id, option_value, poll_id) values (37, 'Toyota', 12);
insert into option (option_id, option_value, poll_id) values (38, 'BMW', 12);

-- Poll 13
insert into poll (poll_id, question) values (13, 'What is your favorite Netflix show?');
insert into option (option_id, option_value, poll_id) values (39, 'Stranger Things', 13);
insert into option (option_id, option_value, poll_id) values (40, 'Black Mirror', 13);
insert into option (option_id, option_value, poll_id) values (41, 'The Crown', 13);

-- Poll 14
insert into poll (poll_id, question) values (14, 'What is your favorite gaming platform?');
insert into option (option_id, option_value, poll_id) values (42, 'PlayStation', 14);
insert into option (option_id, option_value, poll_id) values (43, 'Xbox', 14);
insert into option (option_id, option_value, poll_id) values (44, 'PC', 14);

-- Poll 15
insert into poll (poll_id, question) values (15, 'What is your favorite holiday?');
insert into option (option_id, option_value, poll_id) values (45, 'Christmas', 15);
insert into option (option_id, option_value, poll_id) values (46, 'Halloween', 15);
insert into option (option_id, option_value, poll_id) values (47, 'Thanksgiving', 15);
EOF