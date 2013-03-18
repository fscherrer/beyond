update usuario set login = ?, senha = ?, nome = ?, casaid = ? where id = ?;
update eventobanda set eventoid = ?, bandaid = ? where id = ?;
update evento set dataHora = ?, valor = ?, casaid = ? where id = ?;
update musicobanda set musicoid = ?, bandaid = ?, funcao = ? where id = ?;
update contatomusico set musicoid = ?, contatoid = ? where id = ?;
update musico set nome = ? where id = ?;
update contatobanda set bandaid = ?, contatoid = ? where id = ?;
update banda set nome = ?, dataFormacao = ? where id = ?;
update contatocasa set contatoid = ?, casaid = ? where id = ?;
update contato set telefone = ?, fax = ?, email = ?, site = ?, facebook = ?, twitter = ? where id = ?;
update endereco set cidadeid = ?, bairro = ?, cep = ?, logradouro = ?, complemento = ?, numero = ? where id = ?;
update cidade set nome = ?, ufid = ? where id = ?;
update uf set sigla = ?, nome = ? where id = ?;
update casa set nome = ?, enderecoid = ?, matrizid = ?, cnpj = ?, responsavel = ? where id = ?;

