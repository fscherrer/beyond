package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.EventoServices;
import java.util.List;

/**
 * Implementação padrão de {@link EventoServices}.
 */
public class EventoServicesImpl implements EventoServices {

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Evento entidade) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getEventoDAO().inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Evento", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Evento getPeloId(int id) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getEventoDAO().getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter o Evento pelo ID " + id, daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Evento> listar() throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getEventoDAO().listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Eventos", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Evento entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getEventoDAO().atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Evento", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Evento entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getEventoDAO().remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Evento", daoe);
    }
  }
}