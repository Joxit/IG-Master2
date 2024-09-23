CREATE USER 'pizzaiolo'@'%' IDENTIFIED WITH caching_sha2_password BY 'PizzaioloDePizzaHut';

GRANT ALL PRIVILEGES ON `pizzeria`.* TO 'pizzaiolo'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;