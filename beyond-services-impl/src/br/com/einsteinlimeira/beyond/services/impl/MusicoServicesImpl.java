package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.model.Musico;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.MusicoServices;
import java.util.List;

/**
 * Implementação padrão de {@link MusicoServices}.
 */
public class MusicoServicesImpl implements MusicoServices {

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Musico entidade) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getMusicoDAO().inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Musico", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Musico getPeloId(int id) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getMusicoDAO().getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter o Musico pelo ID " + id, daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Musico> listar() throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getMusicoDAO().listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Musicos", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Musico entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getMusicoDAO().atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Musico", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Musico entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getMusicoDAO().remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Musico", daoe);
    }
  }
}