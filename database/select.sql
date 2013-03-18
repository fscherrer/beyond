select id, login, senha, nome, casaid from usuario;
select id, eventoid, bandaid from eventobanda;
select id, dataHora, valor, casaid from evento;
select id, musicoid, bandaid, funcao from musicobanda;
select id, musicoid, contatoid from contatomusico;
select id, nome from musico;
select id, bandaid, contatoid from contatobanda;
select id, nome, dataFormacao from banda;
select id, contatoid, casaid from contatocasa;
select id, telefone, fax, email, site, facebook, twitter from contato;
select id, cidadeid, bairro, cep, logradouro, complemento, numero from endereco;
select id, nome, ufid from cidade;
select id, sigla, nome from uf;
select id, nome, enderecoid, matrizid, cnpj, responsavel from casa;

