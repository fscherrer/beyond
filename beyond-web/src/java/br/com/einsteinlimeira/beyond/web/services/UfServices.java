package br.com.einsteinlimeira.beyond.web.services;

import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.model.dao.UfDAO;
import java.util.List;

/**
 * Serviço relacionados a entidade {@link Uf}.
 */
public class UfServices {
  
  // TODO: não está legal um serviço lançar DAOException

  public List<Uf> getUfs() throws DAOException {
    return new UfDAO().listar();
  }
  
  public Uf getUf(int id) throws DAOException{
    return new UfDAO().getPeloId(id);
  }
}