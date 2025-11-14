use basketball_app;

INSERT INTO enthusiast (username, lastname, firstname, middlename, sex, date_of_birth)
VALUES
('ballfan01', 'Garcia', 'Miguel', 'Santos', 'Male', '1998-04-21'),
('hooplover', 'Reyes', 'Angela', 'Dizon', 'Female', '2001-09-13'),
('courtking', 'Tan', 'Joseph', 'Lim', 'Male', '1995-12-02'),
('dribblequeen', 'Lopez', 'Katrina', 'Mae', 'Female', '2000-06-28'),
('fastbreakfan', 'Cruz', 'Jonathan', 'P.', 'Male', '1999-01-17'),
('threepointer', 'Torres', 'Bea', 'Ann', 'Female', '2002-11-04'),
('dunkmaster', 'Chua', 'Ryan', 'Lee', 'Male', '1996-08-19'),
('rebounder', 'Navarro', 'Paolo', 'R.', 'Male', '1997-05-05'),
('slamfanatic', 'Santos', 'Janelle', 'T.', 'Female', '2003-02-10'),
('bballenthusiast', 'Mendoza', 'Kevin', 'C.', 'Male', '1994-07-22');

INSERT INTO player (lastname, firstname, middlename, sex, date_of_birth, height, weight, rStatus)
VALUES
('Fernandez', 'Mark', 'A.', 'Male', '1998-05-12', 185, 80, TRUE),
('Delos Santos', 'Luis', 'M.', 'Male', '1997-10-20', 178, 75, TRUE),
('Villanueva', 'Carlos', 'P.', 'Male', '1995-09-30', 190, 88, TRUE),
('Gonzales', 'Ramon', 'E.', 'Male', '2000-03-15', 175, 70, FALSE),
('Reyes', 'John', 'T.', 'Male', '1999-11-22', 182, 77, TRUE),
('Lim', 'Patrick', 'J.', 'Male', '2001-06-05', 188, 84, TRUE),
('Torres', 'Samuel', 'V.', 'Male', '1996-02-18', 193, 90, TRUE),
('Santiago', 'Leo', 'R.', 'Male', '1998-08-11', 180, 73, FALSE),
('Chavez', 'Ryan', 'B.', 'Male', '1997-01-29', 176, 68, TRUE),
('Lopez', 'Adrian', 'N.', 'Male', '1999-04-14', 184, 81, TRUE);

INSERT INTO coach (lastname, firstname, middlename, sex, date_of_birth, start_year, end_year, years_in_field, status)
VALUES
('Santos', 'Roberto', 'M.', 'Male', '1978-02-14', 2005, NULL, 20, TRUE),
('Garcia', 'Leonard', 'T.', 'Male', '1980-07-22', 2008, NULL, 17, TRUE),
('Ramos', 'Antonio', 'L.', 'Male', '1975-10-09', 2003, 2020, 17, FALSE),
('Cruz', 'Daniel', 'S.', 'Male', '1983-11-30', 2010, NULL, 15, TRUE),
('Lopez', 'Carmen', 'A.', 'Female', '1979-04-05', 2007, 2021, 14, FALSE),
('Reyes', 'Julian', 'E.', 'Male', '1985-12-19', 2012, NULL, 13, TRUE),
('Tan', 'Marissa', 'G.', 'Female', '1981-09-01', 2009, 2022, 13, FALSE),
('Villanueva', 'Arnold', 'D.', 'Male', '1977-06-25', 2004, NULL, 21, TRUE),
('Delos Reyes', 'Erika', 'B.', 'Female', '1984-08-16', 2011, NULL, 14, TRUE),
('Navarro', 'Greg', 'C.', 'Male', '1976-03-11', 2002, 2019, 17, FALSE);
INSERT INTO tournament (tournament_name, season_year, tournament_type, start_date, end_date)
VALUES
('Metro Manila Basketball Cup', 2023, 'Regional', '2023-03-10', '2023-06-25'),
('Philippine Invitational League', 2022, 'National', '2022-04-15', '2022-08-30'),
('Summer Hoops Classic', 2024, 'Friendly', '2024-05-01', '2024-05-30'),
('Collegiate Championship', 2021, 'Collegiate', '2021-09-01', '2021-12-10'),
('Youth Development League', 2023, 'Junior', '2023-01-20', '2023-04-15'),
('Mindanao Invitational', 2024, 'Regional', '2024-07-05', '2024-09-22'),
('National Open Cup', 2022, 'National', '2022-10-12', '2022-12-18'),
('All-Star Invitational', 2025, 'Exhibition', '2025-03-05', '2025-03-15'),
('Community Basketball Fest', 2023, 'Friendly', '2023-08-01', '2023-08-25'),
('Legends Reunion Tournament', 2024, 'Exhibition', '2024-11-10', '2024-11-20');


INSERT INTO team (team_ID, coach_id, team_name, number_of_players, registry_status, tCreated_at)
VALUES
(1, 1, 'Green Archers', 15, TRUE, '2024-01-10 09:15:00'),
(2, 2, 'Blue Eagles', 14, TRUE, '2024-02-05 13:30:00'),
(3, 3, 'Red Warriors', 13, FALSE, '2024-03-12 16:45:00'),
(4, 1, 'Golden Tigers', 16, TRUE, '2024-04-01 10:00:00'),
(5, 4, 'Maroon Falcons', 12, TRUE, '2024-04-15 11:20:00'),
(6, 2, 'Silver Knights', 11, FALSE, '2024-05-02 08:40:00'),
(7, 5, 'Black Panthers', 15, TRUE, '2024-06-18 14:55:00'),
(8, 3, 'White Wolves', 14, TRUE, '2024-07-09 17:10:00'),
(9, 4, 'Crimson Foxes', 13, FALSE, '2024-08-21 12:05:00'),
(10, 5, 'Iron Sharks', 16, TRUE, '2024-09-30 19:25:00');

