package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.dto.CidadeDTO;

/**
 * Define o DAO de {@link Cidade}.
 */
public interface CidadeDAO extends EntidadeDAO<Cidade>, EntidadeDTODAO<CidadeDTO> {
}