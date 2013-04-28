Essa é a implementação padrão de beyond-dao, manipulando os dados em base de dados PostgreSQL.
Nenhuma referência a PostgreSQL é realizada no nome das implementações de DAO, como UfDAOImpl, nem
no nome do projeoto (beyond-dao-impl), pelo fato das queries não estarem fazendo uso de recursos 
específicos do PostgreSQL. Na teorica, essa implementação pode migrar de PostgreSQL para Firebird ou
outro SGBD sem a necessidade de alteração nas implementações, apenas definição das propriedades de
conexão no arquivo bancodados.properties.