package br.com.einsteinlimeira.beyond.mobile.services;

import br.com.einsteinlimeira.beyond.mobile.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.mobile.dao.EntidadeDAO;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;

public class CasaServices extends EntidadeServices<CasaDTO> {

  @Override
  public EntidadeDAO<CasaDTO> getDAO() {
    return new CasaDAO();
  }
}
