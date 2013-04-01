package br.com.einsteinlimeira.beyond.web.services;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.model.dao.UsuarioDAO;
import java.util.List;

/**
 * Serviço relacionados a entidade {@link Usuario}.
 */
public class UsuarioServices {

  // TODO: não está legal um serviço lançar DAOException
  
  public List<Usuario> getUsuarios() throws DAOException {
    return new UsuarioDAO().listar();
  }

  public Usuario getUsuario(int id) throws DAOException {
    return new UsuarioDAO().getPeloId(id);
  }

  public Usuario getUsuario(String login, String senha) throws DAOException {
    return new UsuarioDAO().getUsuario(login, senha);
  }
  
  public int inserir(Usuario usuario) throws DAOException{
    return new UsuarioDAO().inserir(usuario);
  }
  
  public void atualizar(Usuario usuario) throws DAOException{
    new UsuarioDAO().atualizar(usuario);
  }
}