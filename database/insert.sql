insert into usuario(id, login, senha, nome, casaid) values (?, ?, ?, ?, ?);
insert into eventobanda(id, eventoid, bandaid) values (?, ?, ?);
insert into evento(id, dataHora, valor, casaid) values (?, ?, ?, ?);
insert into musicobanda(id, musicoid, bandaid, funcao) values (?, ?, ?, ?);
insert into contatomusico(id, musicoid, contatoid) values (?, ?, ?);
insert into musico(id, nome) values (?, ?);
insert into contatobanda(id, bandaid, contatoid) values (?, ?, ?);
insert into banda(id, nome, dataFormacao) values (?, ?, ?);
insert into contatocasa(id, contatoid, casaid) values (?, ?, ?);
insert into contato(id, telefone, fax, email, site, facebook, twitter) values (?, ?, ?, ?, ?, ?, ?);
insert into endereco(id, cidadeid, bairro, cep, logradouro, complemento, numero) values (?, ?, ?, ?, ?, ?, ?);
insert into cidade(id, nome, ufid) values (?, ?, ?);
insert into uf(id, sigla, nome) values (?, ?, ?);
insert into casa(id, nome, enderecoid, matrizid, cnpj, responsavel) values (?, ?, ?, ?, ?, ?);

