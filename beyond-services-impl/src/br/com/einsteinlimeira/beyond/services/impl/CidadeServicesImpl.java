package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.CidadeServices;
import java.util.List;

/**
 * Implementação padrão de {@link CidadeServices}.
 */
public class CidadeServicesImpl implements CidadeServices {

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Cidade entidade) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getCidadeDAO().inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Cidade", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Cidade getPeloId(int id) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getCidadeDAO().getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter a Cidade pelo ID " + id, daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Cidade> listar() throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getCidadeDAO().listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Cidades", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Cidade entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getCidadeDAO().atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Cidade", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Cidade entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getCidadeDAO().remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Cidade", daoe);
    }
  }
}