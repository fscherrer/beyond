package br.com.einsteinlimeira.beyond.mobile.services;

import br.com.einsteinlimeira.beyond.mobile.dao.CidadeDAO;
import br.com.einsteinlimeira.beyond.mobile.dao.EntidadeDAO;
import br.com.einsteinlimeira.beyond.model.dto.CidadeDTO;

/**
 * Provê serviços relacionadas à Casa.
 */
public class CidadeServices extends EntidadeServices<CidadeDTO> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeDAO<CidadeDTO> getDAO() {
    return new CidadeDAO();
  }
}
