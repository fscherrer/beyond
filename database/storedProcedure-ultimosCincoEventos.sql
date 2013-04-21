create or replace function eventosNosProximosXDias(dias integer) returns setof evento AS $$
declare
  -- define uma variável chamada hoje, do tipo timestamp (data e hora) e
  -- a inicializa com o valor correspondente à data e hora atuais
  hoje timestamp = current_timestamp;
  -- define xDiasAFrente como sendo hoje (definida acima) + X dias
  xDiasAFrente timestamp = hoje + (dias || ' days')::interval;
begin
  raise notice 'Executando Stored Procedure eventosNosProximosXDias';
  raise notice 'Obtendo os eventos a acontecer nos próximos % dias', dias;
  
  return query 
    select 
      * 
    from 
      evento 
    where 
      dataHora >= hoje
        and 
      dataHora <= xDiasAFrente;
end;
$$ language plpgsql;

--select eventosNosProximosXDias(11);

insert into uf values(1000, 'AB', 'São Paulof');
select * from uf where sigla = 'AB';
select * from cidade;
select * from uf where id = 1000;
select * from endereco;
select * from cidade where id = 1;
select cidade.nome, uf.nome from cidade join uf on uf.id = ufid





