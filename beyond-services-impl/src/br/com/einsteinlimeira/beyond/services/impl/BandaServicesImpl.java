package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.BandaServices;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementação padrão de {@link BandaServices}.
 */
public class BandaServicesImpl implements BandaServices {
  
  /**
   * DAO de Banda.
   */
  @Inject
  private BandaDAO bandaDAO;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Banda entidade) throws EntidadeServicesException {
    try {
      return bandaDAO.inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Banda", daoe);
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Banda getPeloId(int id) throws EntidadeServicesException {
    try {
      return bandaDAO.getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter a Banda pelo ID " + id, daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Banda> listar() throws EntidadeServicesException {
    try {
      return bandaDAO.listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Bandas", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Banda entidade) throws EntidadeServicesException {
    try {
      bandaDAO.atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Banda", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Banda entidade) throws EntidadeServicesException {
    try {
      bandaDAO.remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Banda", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getEstilos() throws EntidadeServicesException {
    try {
      return bandaDAO.getEstilos();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter os estilos das Bandas", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Banda> getBandas(List<String> estilos) throws EntidadeServicesException {
    try {
      return bandaDAO.getBandas(estilos);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para listar Bandas filtradas", daoe);
    }
  }
}