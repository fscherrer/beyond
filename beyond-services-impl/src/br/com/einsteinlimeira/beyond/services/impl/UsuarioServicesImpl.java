package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.services.DominioException;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.UsuarioServices;
import java.util.List;

/**
 * Implementação padrão de {@link UsuarioServices}.
 */
public class UsuarioServicesImpl implements UsuarioServices {

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Usuario entidade) throws EntidadeServicesException, DominioException {
    String loginOriginal = getPeloId(entidade.getId()).getLogin();
    
    if (loginOriginal.equals(ServicesImplConstantes.ADMIN)) {
      throw new DominioException("O usuário 'admin' não poder ser editado.");
    }

    try {
      DAOFactory.getFactory().getUsuarioDAO().atualizar(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para atualizar Usuario", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Usuario entidade) throws EntidadeServicesException, DominioException {
    if (entidade.getLogin().equals(ServicesImplConstantes.ADMIN)) {
      throw new DominioException("O usuário 'admin' não poder ser excluído.");
    }

    try {
      DAOFactory.getFactory().getUsuarioDAO().remover(entidade);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para remover Usuario", daoe);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Usuario getUsuario(String login, String senha) throws EntidadeServicesException {
    try {
      return DAOFactory.getFactory().getUsuarioDAO().getUsuario(login, senha);
    }
    catch (DAOException daoe) {
      throw new EntidadeServicesException(
          "Falha na chamada à camada de acesso a dados para obter Usuario através de login e senha",
          daoe);
    }
  }
}