package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.UfDAO;
import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.UfServices;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementação padrão de {@link UfServices}.
 */
public class UfServicesImpl implements UfServices {
  
  /**
   * DAO de Uf.
   */
  @Inject
  private UfDAO ufDAO;

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Uf entidade) throws EntidadeServicesException {
    try {
      return ufDAO.inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Uf", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Uf getPeloId(int id) throws EntidadeServicesException {
    try {
      return ufDAO.getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter o Uf pelo ID " + id, daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Uf> listar() throws EntidadeServicesException {
    try {
      return ufDAO.listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Ufs", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Uf entidade) throws EntidadeServicesException {
    try {
      ufDAO.atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Uf", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Uf entidade) throws EntidadeServicesException {
    try {
      ufDAO.remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Uf", daoe);
    }
  }
}