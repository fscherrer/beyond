package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.UfServices;
import java.util.List;

/**
 * Implementação padrão de {@link UfServices}.
 */
public class UfServicesImpl implements UfServices {

  @Override
  public int inserir(Uf entidade) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getUfDAO().inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Uf", daoe);
    }
  }

  @Override
  public Uf getPeloId(int id) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getUfDAO().getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter o Uf pelo ID " + id, daoe);
    }
  }

  @Override
  public List<Uf> listar() throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getUfDAO().listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Ufs", daoe);
    }
  }

  @Override
  public void atualizar(Uf entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getUfDAO().atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Uf", daoe);
    }
  }

  @Override
  public void remover(Uf entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getUfDAO().remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Uf", daoe);
    }
  }
}