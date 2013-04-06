alter table evento add column nome varchar(50) not null;
INSERT INTO evento
(
  id,
  datahora,
  valor,
  casaid,
  nome
)
VALUES
(
  1,
  '2013-04-16',
  120.0,
  1,
  'Noite do Metal'
);

INSERT INTO evento
(
  id,
  datahora,
  valor,
  casaid,
  nome
)
VALUES
(
  4,
  '2013-05-01',
  220.0,
  3,
  'Maiden'
);

INSERT INTO evento
(
  id,
  datahora,
  valor,
  casaid,
  nome
)
VALUES
(
  2,
  '2013-04-17',
  125.0,
  2,
  'Heavy Metal'
);

INSERT INTO evento
(
  id,
  datahora,
  valor,
  casaid,
  nome
)
VALUES
(
  5,
  '2013-09-11',
  200.0,
  4,
  'September'
);

INSERT INTO evento
(
  id,
  datahora,
  valor,
  casaid,
  nome
)
VALUES
(
  3,
  '2013-09-09',
  200.0,
  5,
  'JavaMetal'
);


