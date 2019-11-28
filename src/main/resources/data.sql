INSERT INTO tb_estado (id_estado, ds_sigla) VALUES (1, 'MG');
INSERT INTO tb_estado (id_estado, ds_sigla) VALUES (2, 'BA');

INSERT INTO tb_cidade (id_cidade, id_estado, ds_nome) VALUES (1, 1, 'BELO HORIZONTE');
INSERT INTO tb_bairro (id_bairro, id_cidade, ds_nome) VALUES (1, 1, 'CENTRO'), (2, 1, 'FUNCIONARIOS');

INSERT INTO tb_endereco (id_endereco, en_tipo_local, ds_logradouro) VALUES (1, 'AVENIDA', 'AFONSO PENA');
INSERT INTO tb_complemento (id_complemento, id_endereco, ds_descricao, nm_numero) VALUES (1, 1, 'CASA', 3500), (2, 1, 'SALA COMERCIAL', 981);
INSERT INTO tb_cep (id_cep, id_endereco, nm_numero) VALUES (1, 1, 30130002), (2, 1, 30130008);

INSERT INTO tb_endereco_bairro (id_endereco, id_bairro) VALUES (1, 1); 
INSERT INTO tb_bairro_endereco (id_bairro, id_endereco) VALUES (1, 2); 