SELECT setval('public.banda_id_seq', (select coalesce(max(id)) + 1 from banda), true);
SELECT setval('public.casa_id_seq', (select coalesce(max(id)) + 1 from casa), true);
SELECT setval('public.cidade_id_seq', (select coalesce(max(id)) + 1 from cidade), true);
SELECT setval('public.contato_id_seq', (select coalesce(max(id)) + 1 from contato), true);
SELECT setval('public.endereco_id_seq', (select coalesce(max(id)) + 1 from endereco), true);
SELECT setval('public.evento_id_seq', (select coalesce(max(id)) + 1 from evento), true);
SELECT setval('public.eventobanda_id_seq', (select coalesce(max(id)) + 1 from eventobanda), true);
SELECT setval('public.musico_id_seq', (select coalesce(max(id)) + 1 from musico), true);
SELECT setval('public.musicobanda_id_seq', (select coalesce(max(id)) + 1 from musicobanda), true);
SELECT setval('public.uf_id_seq', (select coalesce(max(id)) + 1 from uf), true);
SELECT setval('public.usuario_id_seq', (select coalesce(max(id)) + 1 from usuario), true);