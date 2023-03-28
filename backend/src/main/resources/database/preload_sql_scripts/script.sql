CREATE DATABASE wiss_todos;
USE wiss_todos;
CREATE USER wiss_todo IDENTIFIED BY wiss_todo123;
GRANT ALL ON wiss_todos.* TO wiss_todo;