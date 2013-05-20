package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.EntidadeUtils;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.CasaServices;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementação padrão de {@link CasaServices}.
 */
public class CasaServicesImpl implements CasaServices {
  
  /**
   * DAO de Casa.
   */
  @Inject
  private CasaDAO casaDAO;

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Casa entidade) throws EntidadeServicesException {
    try {
      return casaDAO.inserir(entidade);
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
      return casaDAO.getPeloId(id);
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
      return casaDAO.listar();
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
      casaDAO.atualizar(entidade);
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
      casaDAO.remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Casa", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Casa> getCasas(List<Cidade> cidades) throws EntidadeServicesException {
    int[] idsCidades = null;

    // cidades filtradas
    if (cidades != null && !cidades.isEmpty()) {
      idsCidades = EntidadeUtils.getIDs(cidades);
    }

    try {
      return casaDAO.getCasas(idsCidades);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para listar Casas filtradas", daoe);
    }
  }
}