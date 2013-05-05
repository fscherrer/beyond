package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.CasaServices;
import java.util.List;

/**
 * Implementação padrão de {@link CasaServices}.
 */
public class CasaServicesImpl implements CasaServices {

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Casa entidade) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getCasaDAO().inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Casa", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Casa getPeloId(int id) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getCasaDAO().getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter a Casa pelo ID " + id, daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Casa> listar() throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getCasaDAO().listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Casas", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Casa entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getCasaDAO().atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Casa", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Casa entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getCasaDAO().remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Casa", daoe);
    }
  }
}