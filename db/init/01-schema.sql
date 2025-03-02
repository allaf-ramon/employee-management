CREATE TABLE IF NOT EXISTS funcionarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255),
    ramal VARCHAR(20),
    email_profissional VARCHAR(100),
    departamento VARCHAR(100),
    salario DECIMAL(19,2),
    data_admissao TIMESTAMP
);

CREATE TABLE IF NOT EXISTS funcionario_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    funcionario_id BIGINT,
    nome VARCHAR(255),
    endereco VARCHAR(255),
    ramal VARCHAR(20),
    email_profissional VARCHAR(100),
    departamento VARCHAR(100),
    salario DECIMAL(19,2),
    data_admissao TIMESTAMP,
    tipo_acao VARCHAR(20) NOT NULL,
    dados_json TEXT,
    data_log TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
