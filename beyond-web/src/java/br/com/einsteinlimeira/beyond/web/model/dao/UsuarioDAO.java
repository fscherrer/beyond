package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

  private static final String USUARIO_LOGIN_SENHA_QUERY = ""
      + " select "
      + "   * "
      + " from "
      + "   usuario "
      + " where "
      + "   login = ? "
      + "     and "
      + "   senha = ? ";

  public Usuario getUsuario(String login, String senha) throws DAOException {
    final String mensagem = "Falha ao autenticar usuário";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(USUARIO_LOGIN_SENHA_QUERY);
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, senha);

      List<Usuario> usuarios = getUsuarios(preparedStatement.executeQuery());

      if (!usuarios.isEmpty()) {
        return usuarios.get(0);
      }

      return null;
    }
    catch (BancoDeDadosException bdde) {
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
    catch (SQLException sqle) {
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
    finally {
      try {
        conexao.close();
      }
      catch (Exception e) {
        LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
      }
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
