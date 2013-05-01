package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.UsuarioServices;
import java.util.List;

/**
 * Implementação padrão de {@link UsuarioServices}.
 */
public class UsuarioServicesImpl implements UsuarioServices {

  @Override
  public int inserir(Usuario entidade) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getUsuarioDAO().inserir(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para inclusão de Usuario", daoe);
    }
  }

  @Override
  public Usuario getPeloId(int id) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getUsuarioDAO().getPeloId(id);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter o Usuario pelo ID " + id, daoe);
    }
  }

  @Override
  public List<Usuario> listar() throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getUsuarioDAO().listar();
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para lista Usuarios", daoe);
    }
  }

  @Override
  public void atualizar(Usuario entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getUsuarioDAO().atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Usuario", daoe);
    }
  }

  @Override
  public void remover(Usuario entidade) throws EntidadeServicesException {
    try {
      DAOFactory.getFactory().getUsuarioDAO().remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Usuario", daoe);
    }
  }
}