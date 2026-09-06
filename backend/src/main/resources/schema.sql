CREATE TABLE cinema (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    diretor VARCHAR(100) NOT NULL,
    ano_lancamento INT NOT NULL,
    genero VARCHAR(50) NOT NULL,
    nota INT NOT NULL
);