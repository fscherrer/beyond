package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.EventoDAO;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.EntidadeUtils;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.model.dto.EventoDTO;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.EventoServices;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementação padrão de {@link EventoServices}.
 */
public class EventoServicesImpl implements EventoServices {
  
  /**
   * DAO de Evento.
   */
  @Inject
  private EventoDAO eventoDAO;

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Evento entidade) throws EntidadeServicesException {
    try {
      return eventoDAO.inserir(entidade);
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
      return eventoDAO.getPeloId(id);
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
      return eventoDAO.listar();
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
      eventoDAO.atualizar(entidade);
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
      eventoDAO.remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Evento", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Evento> getEventos(List<Casa> casas, List<Banda> bandas)
      throws EntidadeServicesException {
    int[] idsCasas = null;
    int[] idsBandas = null;

    // casas filtradas
    if (casas != null && !casas.isEmpty()) {
      idsCasas = EntidadeUtils.getIDs(casas);
    }

    // bandas filtradas
    if (bandas != null && !bandas.isEmpty()) {
      idsBandas = EntidadeUtils.getIDs(bandas);
    }

    
    try {
      return eventoDAO.getEventos(idsCasas, idsBandas);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter Eventos filtrados", daoe);
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public List<EventoDTO> getDTOs() throws EntidadeServicesException {
    try {
      return eventoDAO.getDTOs();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter DTOs de Evento", daoe);
    }
  }
}