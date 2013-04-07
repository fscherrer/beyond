alter table evento add column nome varchar(50) not null;
alter table evento alter dataHora type timestamp


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
  '2013-04-15 21:30:00',
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
  2,
  '2013-04-17 22:00:00',
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
  4,
  '2013-05-01 23:00:00',
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
  3,
  '2013-09-09 23:48:00',
  200.0,
  5,
  'JavaMetal'
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
  '2013-09-11 20:00:00',
  200.0,
  4,
  'September'
);



