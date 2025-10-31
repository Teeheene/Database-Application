CREATE TABLE tournaments (
  tournament_id INT AUTO_INCREMENT PRIMARY KEY,
  tournament_name VARCHAR(200) NOT NULL,
  season_year INT,
  tournament_type VARCHAR(50),
  start_date DATE,
  end_date DATE,
  team_bracket VARCHAR(255),
  fan_favorite_team_id INT NULL
);