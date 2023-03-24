
CREATE TABLE users (
                       user_id BIGINT GENERATED ALWAYS AS IDENTITY,
                       email VARCHAR(60) UNIQUE,
                       password VARCHAR(100)
);

CREATE TABLE user_role (
                           user_id BIGINT NOT NULL,
                           role_id BIGINT NOT NULL,
                           PRIMARY KEY(user_id, role_id),
                           FOREIGN KEY(user_id) REFERENCES users(user_id),
                           FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

CREATE TABLE roles (
                       role_id BIGINT GENERATED ALWAYS AS IDENTITY,
                       name VARCHAR(255) UNIQUE
);

CREATE TABLE courses (
                         course_id BIGINT GENERATED ALWAYS AS IDENTITY,
                         course_name VARCHAR(45) NOT NULL,
                         course_duration INTERVAL NOT NULL,
                         course_description text NOT NULL,
                         instructor_id BIGINT NOT NULL,
                         PRIMARY KEY (course_id),
                         FOREIGN KEY (instructor_id) REFERENCES instructors (instructor_id)
);

CREATE TABLE instructors (
                             instructor_id BIGINT GENERATED ALWAYS AS IDENTITY,
                             first_name VARCHAR(40) NOT NULL,
                             last_name VARCHAR(40) NOT NULL,
                             summary TEXT NOT NULL,
                             user_id BIGINT REFERENCES users(user_id)
);
