
-- Create the "player" table
CREATE TABLE player (
                        player_id SERIAL PRIMARY KEY,
                        player_name VARCHAR(100),
                        player_surname VARCHAR(100),
                        age INTEGER,
                        height_cm FLOAT,
                        weight_kg FLOAT,
                        gender VARCHAR(10)
);

-- Create the "playground" table
CREATE TABLE playground (
                            playground_id SERIAL PRIMARY KEY,
                            playground_name VARCHAR(50),
                            location VARCHAR(100)
);

-- Create the "sportType" table
CREATE TABLE sportType (
                           sportType_id SERIAL PRIMARY KEY,
                           sport_name VARCHAR(50)
);

-- Create the "user" table
CREATE TABLE users (
                      user_id SERIAL PRIMARY KEY,
                      username VARCHAR(100),
                      usersurname VARCHAR(100),
                      email VARCHAR(50),
                      password VARCHAR(100)
);

-- Create the "team" table
CREATE TABLE team (
                      team_id SERIAL PRIMARY KEY,
                      team_name VARCHAR(50),
                      captain_id INTEGER REFERENCES player(player_id) ON DELETE SET NULL
);

-- Create the "teamManager" table
CREATE TABLE teamManager (
                             teamManager_id SERIAL PRIMARY KEY,
                             manager_name VARCHAR(100),
                             manager_surname VARCHAR(100),
                             team_id INTEGER REFERENCES team(team_id) ON DELETE SET NULL
);

-- Create the "role" table
CREATE TABLE role (
                      role_id SERIAL PRIMARY KEY,
                      role_name VARCHAR(50)
);

-- Create the "playgroundAvailability" table
CREATE TABLE playgroundAvailability (
                                        playgroundAvailability_id SERIAL PRIMARY KEY,
                                        playground_id INTEGER REFERENCES playground(playground_id) ON DELETE CASCADE,
                                        sportType_id INTEGER REFERENCES sportType(sportType_id) ON DELETE CASCADE,
                                        available_from DATE,
                                        available_to DATE
);

-- Create the "booking" table
CREATE TABLE booking (
                         booking_id SERIAL PRIMARY KEY,
                         playgroundAvailability_id INTEGER REFERENCES playgroundAvailability(playgroundAvailability_id) ON DELETE CASCADE,
                         player_id INTEGER REFERENCES player(player_id) ON DELETE SET NULL,
                         booking_date DATE,
                         booking_time TIME
);

-- Create the "admin" table
CREATE TABLE admin (
                       admin_id SERIAL PRIMARY KEY,
                       admin_name VARCHAR(50),
                       user_id INTEGER REFERENCES users(user_id) ON DELETE SET NULL
);

-- Create the many-to-many relationship table between "player" and "team"
CREATE TABLE player_team (
                             player_id INTEGER REFERENCES player(player_id) ON DELETE CASCADE,
                             team_id INTEGER REFERENCES team(team_id) ON DELETE CASCADE,
                             PRIMARY KEY (player_id, team_id)
);

-- Create the many-to-many relationship table between "player" and "role"
CREATE TABLE player_role (
                             player_id INTEGER REFERENCES player(player_id) ON DELETE CASCADE,
                             role_id INTEGER REFERENCES role(role_id) ON DELETE CASCADE,
                             PRIMARY KEY (player_id, role_id)
);