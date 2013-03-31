package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements EntidadeDAO<Usuario> {

  @Override
  public void inserir(Usuario entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean remover(Usuario entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Usuario getPeloId(int id) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());

  @Override
  public List<Usuario> listar() throws DAOException {
    try {
      return getUsuarios(BancoDeDados.getInstancia().executarQuery("select * from usuario"));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Usuários";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  @Override
  public boolean atualizar(Usuario entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Usuario> getUsuarios(ResultSet resultSet) throws DAOException {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    int id;
    String login;
    String senha;
    String nome;
    Integer idCasa; // pode ser null

    try {
      while (resultSet.next()) {
        id = resultSet.getInt("id");
        login = resultSet.getString("login");
        senha = resultSet.getString("senha");
        nome = resultSet.getString("nome");
        idCasa = resultSet.getInt("casaid");

        // TODO: e a casa? usar o dao de casa para pegar?
        usuarios.add(new Usuario(id, login, senha, nome, null));
      }

      return usuarios;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair usuários do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}
